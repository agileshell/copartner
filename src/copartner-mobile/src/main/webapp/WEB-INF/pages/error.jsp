<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="config.jsp"%>
<head>
<title>找不到页面</title>
<link rel="stylesheet" type="text/css" href="resources/css/error.css?v=${version}" />
</head>

<!-- header start -->
<header>
    <div class="header-bar">
        <div id="layout_urlblack" data-url="home" class="header-icon-back">
            <span>返回</span>
        </div>
        <div class="header-title">404</div>
    </div>
</header>
<!-- header end -->

<div class="errorpage">
    <div class="wrong">
        <p>
            <img alt="" src="resources/img/img_500.png">
        </p>
        <p class="wrong_num">404</p>
        <p class="sorry">${empty errorMessage ? '找不到页面' : errorMessage}</p>
        <a href="#" class="ec-btn ec-btn-lg ec-btn-default" type="button">回首页看看</a>
    </div>
</div>

<jsp:include page="../layout/_footer.jsp" />