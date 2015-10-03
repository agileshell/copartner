<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:url value="<%=basePath%>" var="basePath" />
<c:set var="version" value="1.0.1" />

<!DOCTYPE html>
<!--[if IEMobile 7 ]>    <html class="no-js iem7"> <![endif]-->
<!--[if (gt IEMobile 7)|!(IEMobile)]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <base href="<%=basePath%>" />
    <title>我是创始人 - <decorator:title /></title>
    <meta charset="UTF-8" />
    <meta name="HandheldFriendly" content="True" />
    <meta name="MobileOptimized" content="320" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="format-detection" content="telephone = no"/>
    <meta name="format-detection" content="address=no;email=no"/>
    <meta http-equiv="cleartype" content="on" />
    <meta http-equiv="cache-control" content="no-cache">

    <meta name="description" content="我是创始人" />
    <meta name="keywords" content="我是创始人" />

    <meta name="apple-touch-fullscreen" content="yes"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="apple-mobile-web-app-title" content="我是创始人" />

    <!-- This script prevents links from opening in Mobile Safari. https://gist.github.com/1042026 -->
    <script>(function(a,b,c){if(c in b&&b[c]){var d,e=a.location,f=/^(a|html)$/i;a.addEventListener("click",function(a){d=a.target;while(!f.test(d.nodeName))d=d.parentNode;"href"in d&&(d.href.indexOf("http")||~d.href.indexOf(e.host))&&(a.preventDefault(),e.href=d.href)},!1)}})(document,window.navigator,"standalone")</script>

    <link rel="stylesheet" type="text/css" href="resources/css/main.css?v=${version}" />
    <script type="text/javascript" src="resources/js/jquery-1.11.1.min.js?v=${version}"></script>
    <script type="text/javascript" defer src="resources/js/util.js?v=${version}"></script>

    <link rel="Shortcut Icon" href="<%=basePath%>favicon.ico" />

    <decorator:head />

</head>
<body>
    <!--[if lte IE 7]>
    <p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，本系统暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
      以获得更好的体验！</p>
    <![endif]-->
    <decorator:body />
</body>
</html>
