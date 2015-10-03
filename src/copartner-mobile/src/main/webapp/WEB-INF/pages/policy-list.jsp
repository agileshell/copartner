<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="config.jsp"%>
<head>
    <title>政策解读</title>
    <link rel="stylesheet" type="text/css" href="resources/css/news-list.css?v=${version}" />
    <script type="text/javascript" src="resources/js/template.js?v=${version}"></script>
</head>

<a id="layout_top" name="top"></a>

<!-- header start -->
<header>
    <div class="header-bar">
        <div class="header-title">政策解读</div>
    </div>
</header>
<!-- header end -->

<!-- products start -->

<div id="list">
    <input type="hidden" id="current_page" value="1"/>
    <ul class="list_body">
      <c:forEach items="${policyList}" var="policy">
        <li>
            <a style="text-decoration: none;" href="policy/${policy.contentId}">
                <div class="list-thumb">
                    <img width="85" height="85" src="${policy.coverImg}">
                </div>
                <div class="list-descriptions">
                    <div class="list-descriptions-wrapper">
                        <div class="item-title">${policy.title}</div>
                        <div class="item-synopsis">
                            ${policy.synopsis}
                        </div>
                    </div>
                </div>
            </a>
        </li>
      </c:forEach>
    </ul>
    <c:choose>
        <c:when test="${fn:length(policyList) == 0}">
            <div class="not-found">
                <div class="notice">抱歉，没有找到符合条件的新闻动态</div>
            </div>
        </c:when>
        <c:when test="${fn:length(policyList) < 10}">
            <div class="load-more" style="text-align:center;">
                <span style="font-size:12px;" id="fetchMoreMsg" >没有了</span>
            </div>
        </c:when>
        <c:otherwise>
            <div class="load-more" style="text-align:center;" id="fetchMore">
                <span style="font-size:12px;" id="fetchMoreMsg" >加载更多</span>
            </div>
        </c:otherwise>
    </c:choose>
    <div style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;" id="back_to_top" class="right-opera">
        <ul>
            <li id="back-top"></li>
            <li id="go-home"></li>
        </ul>
    </div>
</div>
<script id="policy-template" type="text/html">
    {{each list}}
        <li>
            <a style="text-decoration: none;" href="policy/{{$value.contentId}}">
                <div class="list-thumb">
                    <img width="85" height="85" src="{{$value.coverImg}}">
                </div>
                <div class="list-descriptions">
                    <div class="list-descriptions-wrapper">
                        <div class="item-name">{{$value.title}}</div>
                        <div class="item-synopsis">
                            {{$value.synopsis}}
                        </div>
                    </div>
                </div>
            </a>
        </li>
    {{/each}}
</script>
<script>
    $(function() {
        $("#fetchMore").bind("click", fetchMoreItem);
    });

    var fetchMoreItem = function() {
        var limit = 10,
        current_page = $('#current_page').val(),
        offset = current_page * limit;

        $.ajax({
            url : 'api/policy',
            type : 'GET',
            data: {type: type, offset: offset, limit: limit},
            dataType : 'json',
            beforeSend : function() {
                $('#fetchMoreMsg').html('加载中');
                $('#fetchMore').unbind("click");
            },
            success : function(bdata) {
                if (bdata.status == "SUCCEED") {
                    var data = bdata.body;
                    if(null != data.list && data.list.length > 0) {
                        var html = template('policy-template', data);
                        $('.list_body').append(html);
                        $('#current_page').val(++current_page);
                        $('#fetchMoreMsg').html('加载更多');
                    } else {
                        $('#fetchMoreMsg').html('没有了');
                    }
                    $("#fetchMore").bind("click", fetchMoreItem);
                } else {
                    $('#fetchMoreMsg').html('加载失败');
                    setTimeout(function(){
                        $('#fetchMoreMsg').html('加载更多'); 
                        $("#fetchMore").bind("click", fetchMoreItem);
                    }, 2000);
                }
            },
            error : function() {
                $('#fetchMore').html('加载失败');
                setTimeout(function(){
                    $('#fetchMoreMsg').html('加载更多'); 
                    $("#fetchMore").bind("click", fetchMoreItem);
                }, 2000);
            }
        });
    };
</script>
<!-- products end -->

<!-- footer start -->
<%@ include file="../layout/_footer.jsp"%>
<!-- footer end -->