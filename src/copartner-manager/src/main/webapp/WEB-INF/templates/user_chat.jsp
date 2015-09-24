<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<title>即时聊天</title>
	<style type="text/css">
		img.chat_icon {
			width: 40px;
			border: 0;
		}
	</style>
	<script type="text/javascript">
	var ws;
	if (!window.WebSocket) {
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		ws = new WebSocket("ws://120.24.228.100:9099/ws/lnk");
	}
	ws.onopen = function(event) {
		
	};
	ws.onmessage = function(event) {
		alert(event.data);
	};
	ws.onerror = function(event) {
		
	};
	ws.onclose = function(event) {
		
	};
	function deliver(message) {
		if (!window.WebSocket) {
			return;
		}
		if (ws.readyState == WebSocket.OPEN) {
			ws.send(message);
		} else {
			alert("The socket is not open.");
		}
	}
	$(document).ready(function() {
		$("#deliver").click(function() {
			var message = $("#message").val();
			
		});
	});
	</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<h2 class="pull-left">即时聊天</h2>
			<div class="bread-crumb pull-right">
				<a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>即时聊天
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
			<div class="container">
				<!-- row start -->
				<div class="row">
					<div class="col-md-12">
		              <div class="widget">
		                <div class="widget-head">
		                  <div class="pull-left">
		                  <img src="${cdn}image/fei.liu.jpg" alt="${user.name}" class="chat_icon"/>
		                  &nbsp;&nbsp;&nbsp;${user.name}
		                  </div>
		                  <div class="widget-icons pull-right">
		                  	<a class="wminimize" href="#"><i class="icon-chevron-up"></i></a> 
                   			<!--<a class="wclose" href="#"><i class="icon-remove"></i></a>-->
		                  </div>  
		                  <div class="clearfix"></div>
		                </div>
		                <div class="widget-content">
		                  <div class="padd">
		                    <ul class="chats">
		                    
		                      <li class="by-me">
		                        <div class="avatar pull-right">
		                          <img src="${cdn}image/icon.png" alt="小助手" class="chat_icon"/>
		                        </div>
		                        <div class="chat-content">
		                          <div class="chat-meta">2015-07-29 12:39:40<span class="pull-right">小助手</span></div>
		                          大飞哥儿好啊！O(∩_∩)O哈哈哈~😄
		                          <div class="clearfix"></div>
		                        </div>
		                      </li>
		                      
		                      <li class="by-other">
		                        <div class="avatar pull-left">
		                          <img src="${cdn}image/fei.liu.jpg" alt="${user.name}" class="chat_icon"/>
		                        </div>
		                        <div class="chat-content">
		                          <div class="chat-meta">${user.name}<span class="pull-right">2015-07-29 12:53:44</span></div>
		                          恩恩 我好着呢!!!
		                          <div class="clearfix"></div>
		                        </div>
		                      </li>
		                      
		                      <li class="by-me">
		                        <div class="avatar pull-right">
		                          <img src="${cdn}image/icon.png" alt="小助手" class="chat_icon"/>
		                        </div>
		                        <div class="chat-content">
		                          <div class="chat-meta">2015-07-29 15:53:44<span class="pull-right">小助手</span></div>
		                          我们公司目前免费提供一套成人玩具有没有兴趣啊
		                          <div class="clearfix"></div>
		                        </div>
		                      </li>
		                                                                                
		                    </ul>
		                  </div>
		                  <div class="widget-foot">
							<div class="form-group col-lg-11">
								<input id="message" type="text" class="form-control" placeholder="输入消息内容"></input>
							</div>
							<button class="btn btn-default" id="deliver">发送</button>
		                  </div>
		                </div>
		              </div>
		              <!-- end chat -->
		            </div>
				</div>
				<!-- row end -->
			</div>
		</div>
	</div>
</body>
