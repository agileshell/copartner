package com.insoul.copartner.mobile.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insoul.copartner.exception.CException;
import com.insoul.copartner.util.ResponseUtil;
import com.insoul.copartner.vo.NewsVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.NewsListRequest;

@Controller
public class NewsController extends BaseController {

    @RequestMapping(value = "/api/news", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listProducts(NewsListRequest requestData) throws CException {
        return ResponseUtil.jsonSucceed(newsService.listNews(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String listProducts(NewsListRequest requestData, Model model) throws CException {
        Pagination<NewsVO> news = newsService.listNews(requestData);
        model.addAttribute("newsList", news.getList());
        model.addAttribute("requestData", requestData);

        return "news-list";
    }

    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.GET)
    public String getProductDetail(@PathVariable long newsId, Model model) throws CException {
        model.addAttribute("news", newsService.getNews(newsId));

        return "news-detail";
    }
}
