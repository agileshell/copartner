package com.insoul.copartner.service;

import java.util.List;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.TutorDetailVO;
import com.insoul.copartner.vo.TutorVO;
import com.insoul.copartner.vo.request.TutorListRequest;

public interface ITutorService {

    List<TutorVO> listTutors(TutorListRequest requestData);

    TutorDetailVO getTutor(Long tutorId) throws CException;
}
