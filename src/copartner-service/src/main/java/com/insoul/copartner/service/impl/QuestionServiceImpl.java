package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IAnswerDao;
import com.insoul.copartner.dao.IQuestionCategoryDao;
import com.insoul.copartner.dao.IQuestionDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.QuestionCriteria;
import com.insoul.copartner.domain.Answer;
import com.insoul.copartner.domain.Question;
import com.insoul.copartner.domain.QuestionCategory;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IQuestionService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.util.ContentUtil;
import com.insoul.copartner.vo.AnswerVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.QuestionDetailVO;
import com.insoul.copartner.vo.QuestionVO;
import com.insoul.copartner.vo.UserLeanVO;
import com.insoul.copartner.vo.request.QuestionAddRequest;
import com.insoul.copartner.vo.request.QuestionListRequest;

@Service
public class QuestionServiceImpl extends BaseServiceImpl implements IQuestionService {

    @Resource
    private IQuestionDao questionDao;

    @Resource
    private IQuestionCategoryDao questionCategoryDao;

    @Resource
    private IUserDao userDao;

    @Resource
    private IAnswerDao answerDao;

    @Override
    public Pagination<QuestionVO> listQuestions(QuestionListRequest requestData) {
        QuestionCriteria criteria = new QuestionCriteria();
        criteria.setCategoryId(requestData.getCategoryId());
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        criteria.setKeyword(requestData.getKeyword());
        criteria.setStatus(new String[] { "active" });

        long currentUserId = getUserId();
        if (0 != currentUserId) {
            User user = userDao.get(currentUserId);
            if (3 == user.getRoleId()) {// 3:导师
                criteria.setTutorId(currentUserId);
            } else {
                criteria.setUserId(currentUserId);
            }
        }

        List<Question> questions = questionDao.queryQuestion(criteria);
        List<QuestionVO> questionVOs = new ArrayList<QuestionVO>();
        for (Question question : questions) {
            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionId(question.getId());
            questionVO.setTitle(question.getTitle());
            questionVO.setContent(ContentUtil.splitAndFilterString(question.getContent(), 30));
            questionVO.setCreated(question.getCreated());

            // TODO refine
            Long categoryId = question.getCategoryId();
            questionVO.setCategoryId(categoryId);
            QuestionCategory questionCategory = questionCategoryDao.get(categoryId);
            if (null != questionCategory) {
                questionVO.setCategoryName(questionCategory.getName());
            }

            // TODO refine
            UserLeanVO questioner = new UserLeanVO();
            Long questionerId = question.getUserId();
            User questionerUser = userDao.get(questionerId);
            if (null != questionerUser) {
                questioner.setUserId(questionerUser.getId());
                questioner.setName(questionerUser.getName());
                questioner.setAvatar(CDNUtil.getFullPath(questionerUser.getAvatar()));
            }
            questionVO.setQuestioner(questioner);
        }

        Long count = questionDao.countQuestion(criteria);
        return new Pagination<QuestionVO>(questionVOs, count);
    }

    @Override
    public QuestionDetailVO getQuestion(Long questionId) throws CException {
        Question question = questionDao.get(questionId);
        if (null == question || !question.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.QUESTION_NOT_EXIST);
        }

        QuestionDetailVO questionVO = new QuestionDetailVO();
        questionVO.setQuestionId(question.getId());
        questionVO.setTitle(question.getTitle());
        questionVO.setContent(question.getContent());
        questionVO.setCreated(question.getCreated());

        Long categoryId = question.getCategoryId();
        questionVO.setCategoryId(categoryId);
        QuestionCategory questionCategory = questionCategoryDao.get(categoryId);
        if (null != questionCategory) {
            questionVO.setCategoryName(questionCategory.getName());
        }

        UserLeanVO questioner = new UserLeanVO();
        Long questionerId = question.getUserId();
        User questionerUser = userDao.get(questionerId);
        if (null != questionerUser) {
            questioner.setUserId(questionerUser.getId());
            questioner.setName(questionerUser.getName());
            questioner.setAvatar(CDNUtil.getFullPath(questionerUser.getAvatar()));
        }
        questionVO.setQuestioner(questioner);

        UserLeanVO tutor = new UserLeanVO();
        Long tutorId = question.getTutorId();
        User tutorUser = userDao.get(tutorId);
        if (null != tutorUser) {
            tutor.setUserId(tutorUser.getId());
            tutor.setName(tutorUser.getName());
            tutor.setAvatar(CDNUtil.getFullPath(tutorUser.getAvatar()));
        }
        questionVO.setTutor(tutor);

        List<AnswerVO> answerVOS = new ArrayList<AnswerVO>();
        List<Answer> answers = answerDao.findAnswers(questionId);
        for (Answer answer : answers) {
            AnswerVO answerVO = new AnswerVO();
            answerVO.setContent(answer.getContent());
            answerVO.setCreated(answer.getCreated());
            if (answer.getUserId().equals(tutorId)) {
                answerVO.setAnsweror(tutor);
            } else if (answer.getUserId().equals(questionerId)) {
                answerVO.setAnsweror(questioner);
            }

            answerVOS.add(answerVO);
        }
        questionVO.setAnswers(answerVOS);

        return questionVO;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void createQuestion(QuestionAddRequest requestData) throws CException {
        Long categoryId = requestData.getCategoryId();
        QuestionCategory questionCategory = questionCategoryDao.get(categoryId);
        if (null == questionCategory) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.QUESTION_CATEGORY_NOT_EXIST);
        }

        Long tutorId = requestData.getTutorId();
        User tutor = userDao.get(tutorId);
        if (null == tutor) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.TUTOR_NOT_EXIST);
        }

        Question question = new Question();
        question.setCategoryId(categoryId);
        question.setTitle(requestData.getTitle());
        question.setContent(requestData.getContent());
        question.setUserId(getUserId());
        question.setTutorId(tutorId);
        question.setPermission(requestData.getPermission());
        question.setCreated(new Date());

        questionDao.save(question);
    }

    @Override
    public void answer(Long questionId, String content) throws CException {
        Question question = questionDao.get(questionId);
        if (null == question || !question.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.QUESTION_NOT_EXIST);
        }

        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserId(getUserId());
        answer.setContent(content);
        answer.setCreated(new Date());

        answerDao.save(answer);
    }

}
