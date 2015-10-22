package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.insoul.copartner.vo.QuestionCategoryVO;
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
        criteria.setStatus(new String[] {"active"});
        criteria.setTutorId(requestData.getTutorId());

        List<Question> questions = questionDao.queryQuestion(criteria);

        Long count = questionDao.countQuestion(criteria);
        return new Pagination<QuestionVO>(format(questions), count);
    }

    @Override
    public List<QuestionVO> listOwnQuestions(QuestionListRequest requestData) {
        QuestionCriteria criteria = new QuestionCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        criteria.setUserId(getUserId());
        criteria.setIsCurrentUser(true);
        criteria.setStatus(new String[] {"active", "inactive"});

        List<Question> questions = questionDao.queryQuestion(criteria);

        return format(questions);
    }

    @Override
    public List<QuestionVO> listCurrentTutorQuestions(QuestionListRequest requestData) {
        QuestionCriteria criteria = new QuestionCriteria();
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        criteria.setTutorId(getUserId());
        criteria.setIsCurrentUser(true);
        criteria.setStatus(new String[] {"active"});

        List<Question> questions = questionDao.queryQuestion(criteria);

        return format(questions);
    }

    private List<QuestionVO> format(List<Question> questions) {
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

            questionVOs.add(questionVO);
        }

        return questionVOs;
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

        List<Answer> answers = answerDao.findAnswers(questionId);
        Set<Long> userIds = new HashSet<Long>();
        for (Answer answer : answers) {
            userIds.add(answer.getUserId());
        }
        Map<Long, UserLeanVO> userIdMapUserVO = new HashMap<Long, UserLeanVO>();
        List<User> users = userDao.getUserByIds(userIds);
        for (User user : users) {
            UserLeanVO userVO = new UserLeanVO();
            userVO.setUserId(user.getId());
            userVO.setName(user.getName());
            userVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

            userIdMapUserVO.put(user.getId(), userVO);
        }

        List<AnswerVO> answerVOS = new ArrayList<AnswerVO>();
        for (Answer answer : answers) {
            AnswerVO answerVO = new AnswerVO();
            answerVO.setAnsweror(userIdMapUserVO.get(answer.getUserId()));
            answerVO.setContent(answer.getContent());
            answerVO.setCreated(answer.getCreated());

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
    @Transactional(value = "transactionManager", rollbackFor = Throwable.class)
    public void answer(Long questionId, String content) throws CException {
        Question question = questionDao.get(questionId);
        if (null == question || !question.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.QUESTION_NOT_EXIST);
        }

        long userId = getUserId();
        User user = userDao.get(userId);
        if (user == null
                || (user.getRoleId() <= 1 && (question.getTutorId() != userId || question.getUserId() != userId))) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.QUESTION_CANNOT_ANSWER);
        }

        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setUserId(userId);
        answer.setContent(content);
        answer.setCreated(new Date());

        answerDao.save(answer);
    }

    @Override
    public List<QuestionCategoryVO> listQuestionCategories() {
        List<QuestionCategoryVO> vos = new ArrayList<QuestionCategoryVO>();

        List<QuestionCategory> entities = questionCategoryDao.getAllListed();
        for (QuestionCategory questionCategory : entities) {
            QuestionCategoryVO vo = new QuestionCategoryVO();
            vo.setId(questionCategory.getId());
            vo.setName(questionCategory.getName());

            vos.add(vo);
        }

        return vos;
    }

}
