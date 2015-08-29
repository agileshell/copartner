package com.insoul.copartner.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IIndustryDomainDao;
import com.insoul.copartner.dao.IUserDao;
import com.insoul.copartner.domain.IndustryDomain;
import com.insoul.copartner.domain.User;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.ITutorService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.IndustryDomainVO;
import com.insoul.copartner.vo.Pagination;
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
    public Pagination<TutorVO> listTutors(TutorListRequest requestData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TutorDetailVO getTutor(Long tutorId) throws CException {
        User user = userDao.get(tutorId);
        if (null == user) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.TUTOR_NOT_EXIST);
        }

        TutorDetailVO userDetailVO = new TutorDetailVO();
        userDetailVO.setTutorId(user.getId());
        userDetailVO.setName(user.getName());
        userDetailVO.setMobile(user.getMobile());
        userDetailVO.setEmail(user.getEmail());
        userDetailVO.setAvatar(CDNUtil.getFullPath(user.getAvatar()));
        userDetailVO.setIntroduction(user.getIntroduction());

        String domainIds[] = user.getDomains().split(",");
        IndustryDomain industryDomain = industryDomainDao.get(Long.valueOf(domainIds[0]));
        if (null != industryDomain) {
            IndustryDomainVO industryDomainVO = new IndustryDomainVO();
            industryDomainVO.setId(industryDomain.getId());
            industryDomainVO.setName(industryDomain.getName());

            userDetailVO.setDomain(industryDomainVO);
        }

        return userDetailVO;
    }

}
