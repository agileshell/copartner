package com.insoul.copartner.service;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.vo.ContentDetailVO;
import com.insoul.copartner.vo.ContentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContentListRequest;

public interface IContentService {

    Pagination<ContentVO> listContents(ContentListRequest requestData);

    ContentDetailVO getContent(Long contentId) throws CException;

    void likeOrUnlikeContent(Long contentId);
}
