<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="config.jsp"%>
<head>
<title>我是创始人</title>
<script>
	var browser={  
	    versions:function(){
	        var u = navigator.userAgent, app = navigator.appVersion;
	        return {//移动终端浏览器版本信息   
	           trident: u.indexOf('Trident') > -1, //IE内核  
	           presto: u.indexOf('Presto') > -1, //opera内核  
	           webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核  
	           gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核  
	           mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端  
	           ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端  
	           android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器  
	           iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器  
	           iPad: u.indexOf('iPad') > -1, //是否iPad    
	           webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部  
	        };
	    }(),
	    language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
    if(browser.versions.mobile || browser.versions.ios || browser.versions.android || browser.versions.iPhone || browser.versions.iPad || !!1) {
        var myHTML = document.querySelector("html"), myWidth = window.innerWidth > 414 ? 414 : window.innerWidth;
        var size =  100 * myWidth / 414;

        myHTML.style.fontSize = size + 'px';
        window.onresize = function() {
            var myHTML = document.querySelector("html"),
            myWidth = window.innerWidth;
            myHTML.style.fontSize = size + 'px';
        }
    }
</script>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
img {
	max-width: 100%;
	border: 0;
}
body {
	text-align: center;
	font-size: 0.12rem;
}
.text {
	text-align: center;
	width: 2.49rem;
	padding: 0.40rem 0.83rem;
	font-size: 0.16rem;
}
a {
	
}
.ios img, .android img {
	max-width: 2.9rem;
}
.ios {
	margin-top: 0.3rem;
}
.android {
	margin-top: 0.37rem;
}
.v {
	position: absolute;
	width: 1.24rem;
	left: 2.07rem;
	margin-left: -0.62rem;
	margin-top: -0.23rem;
	font-size: 0.14rem;
	color: #2a2a2a;
}
.ios.img, .android.img {
	margin-top: -0.15rem;
	float: right;
}
</style>
</head>
<div>
    <img src="resources/img/down-head.png">
    <a id="btn-apple" href="javascript:void(0)">
        <div class="ios">
            <img src="resources/img/ios.png"><div class="v">敬请期待</div>
        </div>
    </a>
    <a id="btn-android" href="${app.android_downloadURL}">
        <div class="android">
            <img src="resources/img/android.png">
            <div class="v">v ${app.android_version}</div>
        </div>
    </a>
</div>
<script>
    function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }
    var isWeixin = is_weixin();
    var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
    console.log(winHeight);
    function loadHtml(){
        var div = document.createElement('div');
        div.id = 'weixin-tip';
        div.innerHTML = '<p><img src="resources/img/live_weixin.png" alt="微信打开"/></p>';
        document.body.appendChild(div);
    }
    function loadStyleText(cssText) {
        var style = document.createElement('style');
        style.rel = 'stylesheet';
        style.type = 'text/css';
        try {
            style.appendChild(document.createTextNode(cssText));
        } catch (e) {
            style.styleSheet.cssText = cssText; //ie9以下
        }
        var head=document.getElementsByTagName("head")[0]; //head标签之间加上style样式
        head.appendChild(style); 
    }
    var cssText = "#weixin-tip{position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} #weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}";
    if(isWeixin){
        loadHtml();
        loadStyleText(cssText);
    }
</script>