package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContestDAO;
import com.insoul.copartner.dao.IContestEntryDAO;
import com.insoul.copartner.dao.criteria.ContestCriteria;
import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.Contest;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IContestService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.ContestDetailVO;
import com.insoul.copartner.vo.ContestEntryDetailVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.ContestVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestEntryListRequest;
import com.insoul.copartner.vo.request.ContestListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月1日 下午11:12:03
 */
@Service
public class ContestServiceImpl extends BaseServiceImpl implements IContestService {

    @Resource
    private IContestDAO contestDAO;

    @Resource
    private IContestEntryDAO contestEntryDAO;

    @Override
    public Pagination<ContestVO> listContests(ContestListRequest requestData) {
        ContestCriteria criteria = new ContestCriteria();
        criteria.setTitle(requestData.getKeyword());
        criteria.setStatus("active");
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom(
                (null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        Long count = contestDAO.countContest(criteria);
        List<Contest> list = contestDAO.queryContest(criteria);
        List<ContestVO> VOs = new ArrayList<ContestVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Contest p : list) {
                ContestVO vo = new ContestVO();
                vo.setId(p.getId());
                vo.setCoverImg(CDNUtil.getFullPath(p.getCoverImg()));
                vo.setTitle(p.getTitle());

                VOs.add(vo);
            }
        }

        return new Pagination<ContestVO>(VOs, count);
    }

    @Override
    public ContestDetailVO getContest(Long contestId) throws CException {
        Contest io = contestDAO.get(contestId);
        if (null == io || !io.getStatus().equals("active")) {
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

    @Override
    public ContestDetailVO getLatestContest() throws CException {
        ContestDetailVO detail = new ContestDetailVO();

        ContestCriteria criteria = new ContestCriteria();
        criteria.setStatus("active");
        criteria.setLimit(1);
        List<Contest> list = contestDAO.queryContest(criteria);
        if (null != list && list.size() > 0) {
            Contest io = list.get(0);

            detail.setId(io.getId());
            detail.setCoverImg(CDNUtil.getFullPath(io.getCoverImg()));
            detail.setIntroduction(io.getIntroduction());
            detail.setTitle(io.getTitle());
            detail.setRules(io.getRules());
            detail.setRegistration(io.getRegistration());
        }

        return detail;
    }

    @Override
    public Pagination<ContestEntryVO> listContestEntries(ContestEntryListRequest requestData) {
        ContestEntryCriteria criteria = new ContestEntryCriteria();
        criteria.setStatus("active");
        criteria.setName(requestData.getKeyword());
        criteria.setContestId(requestData.getContestId());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom(
                (null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
        criteria.setTo((null != requestData.getTo() && requestData.getTo() > 0) ? new Date(requestData.getTo()) : null);
        Long count = contestEntryDAO.countContestEntry(criteria);
        List<ContestEntry> list = contestEntryDAO.queryContestEntry(criteria);
        List<ContestEntryVO> VOs = new ArrayList<ContestEntryVO>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ContestEntry p : list) {
                ContestEntryVO vo = new ContestEntryVO();
                vo.setId(p.getId());
                vo.setCoverImg(CDNUtil.getFullPath(p.getCoverImg()));
                vo.setContact(p.getContact());
                vo.setName(p.getName());
                vo.setPraise(p.getPraise());
                vo.setContestId(p.getContestId());
                vo.setUserName(p.getUserName());
                VOs.add(vo);
            }
        }
        return new Pagination<ContestEntryVO>(VOs, count);
    }

    @Override
    public ContestEntryDetailVO getContestEntry(Long contestEntryId) throws CException {
        ContestEntry io = contestEntryDAO.get(contestEntryId);
        if (null == io || !io.getStatus().equals("active")) {
            throw CExceptionFactory.getException(CException.class, ResponseCode.CONTEST_ENTRY_NOT_EXIST);
        }

        ContestEntryDetailVO detail = new ContestEntryDetailVO();
        detail.setId(io.getId());
        detail.setCoverImg(CDNUtil.getFullPath(io.getCoverImg()));
        detail.setContact(io.getContact());
        detail.setName(io.getName());
        detail.setContestId(io.getContestId());
        detail.setPraise(io.getPraise());
        detail.setUserName(io.getUserName());
        detail.setIntroduction(io.getIntroduction());

        return detail;
    }
}
