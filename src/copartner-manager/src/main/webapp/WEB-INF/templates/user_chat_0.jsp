<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="author" content="andpay.me" />
	<title>创始人--即时聊天</title>
	<link rel="stylesheet" href="${cdn}css/message.css">
	<script src="${cdn}js/jquery-1.10.2.min.js" type="text/javascript"></script>
	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />
</head>
<body>
	<div class="message-history">
		<div class="message-reply">
			<div class="message-time">2014-2-21 9:32:57</div>
			<div class="message-info">
				<div class="user-info">
					<img class="user-avatar" src="${cdn}image/user_a.jpg">
				</div>
				<div class="message-content-box">
					<div class="arrow"></div>
					<div class="item-pics-box">
						<a class="ui-link" href="#"> <img class="item-pic" src="${cdn}image/item_1.jpg">
						</a>
					</div>
					<div class="message-content">这个东西不错呀！</div>
				</div>
			</div>
		</div>
		<div class="message-receive">
			<div class="message-time">2014-2-21 9:32:57</div>
			<div class="message-info">
				<div class="user-info">
					<img class="user-avatar" src="${cdn}image/user_b.gif">
				</div>
				<div class="message-content-box">
					<div class="arrow"></div>
					<div class="item-pics-box">
						<a class="ui-link" href="#"> <img class="item-pic" src="${cdn}image/item_1.jpg">
						</a>
					</div>
					<div class="message-content">Good football</div>
				</div>
			</div>
		</div>
	</div>
	<div class="send-message">
		<span class="send-msg-btn">发送</span>
		<div class="message-input-box">
			<input name="content" type="text" class="message-input" placeholder="输入消息内容">
		</div>
	</div>
	<script src="${cdn}js/message.js" type="text/javascript"></script>
</body>
</html>