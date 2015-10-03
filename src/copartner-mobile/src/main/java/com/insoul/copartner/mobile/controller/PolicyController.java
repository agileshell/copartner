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
import com.insoul.copartner.vo.ContentVO;
import com.insoul.copartner.vo.Pagination;
import com.insoul.copartner.vo.request.ContentListRequest;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年10月3日 下午10:26:06
 */
@Controller
public class PolicyController extends BaseController {

    @RequestMapping(value = "/api/policy", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listProducts(ContentListRequest requestData) throws CException {
        return ResponseUtil.jsonSucceed(contentService.listContents(requestData), HttpStatus.OK);
    }

    @RequestMapping(value = "/policy_list", method = RequestMethod.GET)
    public String listProducts(ContentListRequest requestData, Model model) throws CException {
        Pagination<ContentVO> contentList = contentService.listContents(requestData);
        model.addAttribute("policyList", contentList.getList());
        model.addAttribute("requestData", requestData);
        return "policy-list";
    }

    @RequestMapping(value = "/policy/{policyId}", method = RequestMethod.GET)
    public String getProductDetail(@PathVariable long policyId, Model model) throws CException {
        model.addAttribute("policy", contentService.getContent(policyId));
        return "policy-detail";
    }
}