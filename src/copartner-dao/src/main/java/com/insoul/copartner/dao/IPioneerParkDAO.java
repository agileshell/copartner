package com.insoul.copartner.dao;

import java.util.List;

import com.insoul.copartner.dao.criteria.PioneerParkCriteria;
import com.insoul.copartner.domain.PioneerPark;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年9月29日 上午11:23:10
 */
public interface IPioneerParkDAO extends IBaseDao<PioneerPark, Long> {

    List<PioneerPark> queryPioneerPark(PioneerParkCriteria criteria);

    Long countPioneerPark(PioneerParkCriteria criteria);
}