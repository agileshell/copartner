package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContestDAO;
import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.domain.Contest;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IContestService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.ContestDetailVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月1日 下午11:12:03
 */
@Service
public class DefaultContestService extends BaseServiceImpl implements IContestService {

    @Resource
    private IContestDAO contestDAO;

    @Override
    public Pagination<ContestVO> listContests(ContestListRequest requestData) {
        ContestCriteria criteria = new ContestCriteria();
        criteria.setTitle(requestData.getKeyword());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        Long count = contestDAO.countContest(criteria);
        List<Contest> list = contestDAO.queryContest(criteria);
        List<ContestVO> VOs = new ArrayList<ContestVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Contest p : list) {
                ContestVO vo = new ContestVO();
                vo.setId(p.getId());
                vo.setCoverImg(CDNUtil.getFullPath(p.getCoverImg()));
                vo.setIntroduction(p.getIntroduction());
                vo.setTitle(p.getTitle());
                VOs.add(vo);
            }
        }
        return new Pagination<ContestVO>(VOs, count);
    }

    @Override
    public ContestDetailVO getContest(Long contestId) throws CException {
        Contest io = contestDAO.get(contestId);
        if (null == io) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTEST_NOT_EXIST);
        }
        ContestDetailVO detail = new ContestDetailVO();
        detail.setId(io.getId());
        detail.setCoverImg(CDNUtil.getFullPath(io.getCoverImg()));
        detail.setIntroduction(io.getIntroduction());
        detail.setTitle(io.getTitle());
        detail.setRules(io.getRules());
        detail.setRegistration(io.getRegistration());
        return detail;
    }
}
