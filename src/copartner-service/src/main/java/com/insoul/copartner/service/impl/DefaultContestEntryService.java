package com.insoul.copartner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.insoul.copartner.constant.ResponseCode;
import com.insoul.copartner.dao.IContestEntryDAO;
import com.insoul.copartner.dao.criteria.ContestEntryCriteria;
import com.insoul.copartner.domain.ContestEntry;
import com.insoul.copartner.exception.CException;
import com.insoul.copartner.exception.CExceptionFactory;
import com.insoul.copartner.service.IContestEntryService;
import com.insoul.copartner.util.CDNUtil;
import com.insoul.copartner.vo.ContestEntryDetailVO;
import com.insoul.copartner.vo.ContestEntryVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContestEntryListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月1日 下午11:12:03
 */
@Service
public class DefaultContestEntryService extends BaseServiceImpl implements IContestEntryService {

    @Resource
    private IContestEntryDAO contestEntryDAO;

    @Override
    public Pagination<ContestEntryVO> listContestEntries(ContestEntryListRequest requestData) {
        ContestEntryCriteria criteria = new ContestEntryCriteria();
        criteria.setName(requestData.getKeyword());
        criteria.setContestId(requestData.getContestId());
        criteria.setLimit(requestData.getLimit());
        criteria.setOffset(requestData.getOffset());
        criteria.setFrom((null != requestData.getFrom() && requestData.getFrom() > 0) ? new Date(requestData.getFrom()) : null);
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
        if (null == io) {
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
