package com.insoul.ti.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.insoul.copartner.dao.criteria.QuestionCriteria;
import com.insoul.copartner.domain.Answer;
import com.insoul.copartner.domain.Question;
import com.insoul.copartner.domain.QuestionCategory;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.AnswerVO;
import com.insoul.copartner.vo.QuestionDetailVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.ti.WebBase;
import com.insoul.ti.req.PageQuery;
import com.insoul.ti.req.QuestionListRequest;
import com.insoul.ti.req.ViewRequest;
import com.insoul.ti.shiro.Permission;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年7月4日 下午11:37:15
 */
@Controller
@RequestMapping("/question")
@Permission("authc")
public class QuestionController extends WebBase {

    private static final String QUESTION_DETAIL = "question_detail";
    private static final String QUESTION_LIST = "question_list";

    @RequestMapping("/list")
    public ModelAndView list(@Valid QuestionListRequest request, BindingResult result) {
        ModelAndView mv = createModelView(QUESTION_LIST, request);
        PageQuery query = request.init().getQuery();
        QuestionCriteria criteria = new QuestionCriteria();
        criteria.setLimit(query.getPage_size());
        criteria.setOffset(Long.valueOf(query.getIndex()).intValue());
        String[] status = null;
        if (StringUtils.isNotBlank(request.getStatus())) {
            status = new String[] {request.getStatus()};
        }
        criteria.setStatus(status);
        criteria.setKeyword(request.getKeyword());
        List<Question> list = questionDAO.queryQuestion(criteria);
        mv.addObject("query", query);
        mv.addObject("questionList", list);
        mv.addObject("success", CollectionUtils.isNotEmpty(list));
        mv.addObject("req", request);
        return mv;
    }

    @RequestMapping("/update_status/{questionId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView updateStatus(@PathVariable Long questionId, @RequestParam(value = "status", required = true) String status) {
        try {
            Question question = questionDAO.get(questionId);
            question.setStatus(status);
            questionDAO.update(question);
            returnJson(true, "200", "修改成功!!");
        } catch (Exception e) {
            returnJson(true, "500", "修改失败!!");
        }
        return null;
    }
    
    @RequestMapping("/update_answer_status/{answerId}")
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public ModelAndView updateAnswerStatus(@PathVariable Long answerId, @RequestParam(value = "status", required = true) String status) {
        try {
            Answer answer = answerDAO.get(answerId);
            answer.setStatus(status);
            answerDAO.update(answer);
            returnJson(true, "200", "修改成功!!");
        } catch (Exception e) {
            returnJson(true, "500", "修改失败!!");
        }
        return null;
    }

    @RequestMapping("/detail/{questionId}")
    public ModelAndView detail(@PathVariable Long questionId, ViewRequest req) {
        ModelAndView mv = createModelView(QUESTION_DETAIL, req);
        mv.addObject("viewname", QUESTION_LIST);
        try {
            QuestionDetailVO0 question = getQuestion(questionId);
            mv.addObject("question", question);
            mv.addObject("success", question != null);
        } catch (Exception e) {
            mv.addObject("success", false);
        }
        return mv;
    }

    public QuestionDetailVO0 getQuestion(Long questionId) throws CException {
        Question question = questionDAO.get(questionId);
        QuestionDetailVO0 questionVO = new QuestionDetailVO0();
        questionVO.setQuestionId(question.getId());
        questionVO.setTitle(question.getTitle());
        questionVO.setContent(question.getContent());
        questionVO.setCreated(question.getCreated());
        Long categoryId = question.getCategoryId();
        questionVO.setCategoryId(categoryId);
        QuestionCategory questionCategory = questionCategoryDAO.get(categoryId);
        if (null != questionCategory) {
            questionVO.setCategoryName(questionCategory.getName());
        }

        UserLeanVO questioner = new UserLeanVO();
        Long questionerId = question.getUserId();
        User questionerUser = userDAO.get(questionerId);
        if (null != questionerUser) {
            questioner.setUserId(questionerUser.getId());
            questioner.setName(questionerUser.getName());
            questioner.setAvatar(CDNUtil.getFullPath(questionerUser.getAvatar()));
        }
        questionVO.setQuestioner(questioner);
        UserLeanVO tutor = new UserLeanVO();
        Long tutorId = question.getTutorId();
        User tutorUser = userDAO.get(tutorId);
        if (null != tutorUser) {
            tutor.setUserId(tutorUser.getId());
            tutor.setName(tutorUser.getName());
            tutor.setAvatar(CDNUtil.getFullPath(tutorUser.getAvatar()));
        }
        questionVO.setTutor(tutor);
        List<AnswerVO0> answerVOS = new ArrayList<AnswerVO0>();
        List<Answer> answers = answerDAO.findAnswers(questionId);
        for (Answer answer : answers) {
            AnswerVO0 answerVO = new AnswerVO0();
            answerVO.setId(answer.getId());
            answerVO.setContent(answer.getContent());
            answerVO.setCreated(answer.getCreated());
            answerVO.setStatus(answer.getStatus());
            if (answer.getUserId().equals(tutorId)) {
                answerVO.setAnsweror(tutor);
            } else if (answer.getUserId().equals(questionerId)) {
                answerVO.setAnsweror(questioner);
            }
            answerVOS.add(answerVO);
        }
        questionVO.setAnswers0(answerVOS);
        return questionVO;
    }
    
    public class QuestionDetailVO0 extends QuestionDetailVO {
        private static final long serialVersionUID = 1L;
        private List<AnswerVO0> answers0;

        public List<AnswerVO0> getAnswers0() {
            return answers0;
        }

        public void setAnswers0(List<AnswerVO0> answers0) {
            this.answers0 = answers0;
        }
    }
    
    public class AnswerVO0 extends AnswerVO {
        private static final long serialVersionUID = 1L;
        private long id;
        
        private String status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}