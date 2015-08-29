package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.TutorDetailVO;
import com.insoul.copartner.vo.TutorVO;
import com.insoul.copartner.vo.request.TutorListRequest;

public interface ITutorService {

    Pagination<TutorVO> listTutors(TutorListRequest requestData);

    TutorDetailVO getTutor(Long tutorId) throws CException;
}
