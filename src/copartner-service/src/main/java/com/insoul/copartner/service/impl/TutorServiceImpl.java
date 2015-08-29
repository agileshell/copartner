package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.dao.criteria.TutorCriteria;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.ITutorService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.TutorDetailVO;
import com.insoul.copartner.vo.TutorVO;
import com.insoul.copartner.vo.request.TutorListRequest;

@Service
public class TutorServiceImpl extends BaseServiceImpl implements ITutorService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IIndustryDomainDao industryDomainDao;

    @Override
    public List<TutorVO> listTutors(TutorListRequest requestData) {
        List<TutorVO> tutorVOs = new ArrayList<TutorVO>();

        TutorCriteria criteria = new TutorCriteria();
        criteria.setStatus(new String[] { "active" });
        criteria.setKeyword(requestData.getKeyword());
        criteria.setOffset(requestData.getOffset());
        criteria.setLimit(requestData.getLimit());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom())
                : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);

        List<User> users = userDao.queryTutor(criteria);
        for (User user : users) {
            TutorVO tutorVO = new TutorVO();
            tutorVO.setTutorId(user.getId());
            tutorVO.setName(user.getName());
            tutorVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));

            if (StringUtils.isNotBlank(user.getDomains())) {
                String domainIds[] = user.getDomains().split(",");
                IndustryDomain industryDomain = industryDomainDao.get(Long.valueOf(domainIds[0]));
                if (null != industryDomain) {
                    IndustryDomainVO industryDomainVO = new IndustryDomainVO();
                    industryDomainVO.setId(industryDomain.getId());
                    industryDomainVO.setName(industryDomain.getName());

                    tutorVO.setDomain(industryDomainVO);
                }
            }

            tutorVOs.add(tutorVO);
        }

        return tutorVOs;
    }

    @Override
    public TutorDetailVO getTutor(Long tutorId) throws CException {
        User user = userDao.get(tutorId);
        if (null == user || user.getRoleId() != 3) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.TUTOR_NOT_EXIST);
        }

        TutorDetailVO userDetailVO = new TutorDetailVO();
        userDetailVO.setTutorId(user.getId());
        userDetailVO.setName(user.getName());
        userDetailVO.setMobile(user.getMobile());
        userDetailVO.setEmail(user.getEmail());
        userDetailVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
        userDetailVO.setIntroduction(user.getIntroduction());

        if (StringUtils.isNotBlank(user.getDomains())) {
            String domainIds[] = user.getDomains().split(",");
            IndustryDomain industryDomain = industryDomainDao.get(Long.valueOf(domainIds[0]));
            if (null != industryDomain) {
                IndustryDomainVO industryDomainVO = new IndustryDomainVO();
                industryDomainVO.setId(industryDomain.getId());
                industryDomainVO.setName(industryDomain.getName());

                userDetailVO.setDomain(industryDomainVO);
            }
        }

        return userDetailVO;
    }

}
