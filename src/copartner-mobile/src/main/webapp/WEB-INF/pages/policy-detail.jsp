<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="config.jsp"%>
<head>
    <title>${policy.title}</title>
    <script type="text/javascript" src="resources/js/m.scale.js?v=${version}"></script>
    <style>
    .detail {
        position: relative;
        padding: 5px 0 0 15px;
        font-size: .75em;
        color: #5a5a5a;
    }
    .scale-box {
        display: table;
    }
    .scale-box img {
        -webkit-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }
    h2 {
        color: #bdbdbd;
        font-weight: normal;
    }
    </style>
</head>

<!-- header start -->
<header>
    <div class="header-bar">
        <div id="layout_urlblack" data-url="" class="header-icon-back">
            <span>返回</span>
        </div>
        <div class="header-title">政策详情</div>
    </div>
</header>
<!-- header end -->

<!-- description start -->
<div class="detail" id="wareInfo">
    <h1 class="article-title">${policy.title}</h1>
    <h2><span class="time">${policy.created}</span></h2>
    <div id="scale-parent">
        <div class="scale-box" id="scale-cont">
            <!-- content start -->
            ${policy.article}
            <!-- content end -->
        </div>
    </div>
</div>
<script type="text/javascript">
scale.ready(function(){
    scale.init();
});
</script>
<!-- description end -->

<!-- footer start -->
<%@ include file="../layout/_footer.jsp"%>
<!-- footer end -->
