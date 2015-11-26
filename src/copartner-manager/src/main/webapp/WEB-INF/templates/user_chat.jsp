<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
	<title>即时聊天</title>
	<style type="text/css">
		img.chat_icon {
			width: 40px;
			border: 0;
		}
	</style>
	<script type="text/javascript">
	Date.prototype.format = function(format) {
		var o = {
			"M+": this.getMonth() + 1,
			"d+": this.getDate(),
			"h+": this.getHours(),
			"m+": this.getMinutes(),
			"s+": this.getSeconds(),
			"q+": Math.floor((this.getMonth() + 3) / 3),
			"S": this.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		}
		for (var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	function buildIQ(mid) {
		return "{\"type\":1,\"mid\":" + mid + "}";
	};
	
	function buildMessage(mid, tid, body) {
		return "{\"type\":2,\"mid\":" + mid + ",\"tid\":" + tid + ",\"body\":\"" + body + "\",\"gmt_created\":" + new Date().getTime() + "}";
	};
	
	function buildPresence(mid) {
		return "{\"type\":3,\"mid\":" + mid + ",\"passwd\":\"123456\"}";
	};
	var ws;
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
	var mid = 9;
	var presenceMsg = buildPresence(mid);
	if (!window.WebSocket) {
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		ws = new WebSocket("ws://123.57.55.59:9099");
	}
	ws.onopen = function(event) {
		deliver(presenceMsg);
		setTimeout(function() {// 3分钟定时检查是否在线
			deliver(buildIQ(mid));
		}, 180000);
	};
	ws.onmessage = function(event) {
		var message = JSON.parse(event.data);
		console.log("接收到消息 : " + JSON.stringify(message));
		switch (message.type) {
			case 1 : {// IQ
				if (message.status == 2) {// 离线
					deliver(presenceMsg);
				}
				break;
			}
			case 2 : {// Message
				var fromMid = message.mid,// 对方的mid
					party_id = message.party_id,
					nick = message.nick,
					avatar = message.avatar,
					toMid = message.tid,
					msgBody = message.body || "",
					gmt_created = new Date(message.gmt_created);
				var receiveMessageHtml = 
					"<li class=\"by-other\">" + 
		                "<div class=\"avatar pull-left\">" + 
		                "<img src=\"" + avatar + "\" alt=\"" + nick + "\" class=\"chat_icon\"/>" + 
		              "</div>" + 
		              "<div class=\"chat-content\">" + 
		                "<div class=\"chat-meta\">" + nick + "<span class=\"pull-right\">" + gmt_created.format("yyyy-MM-dd hh:mm:ss") + "</span></div>" + 
		                	msgBody + 
		                "<div class=\"clearfix\"></div>" + 
		              "</div>" + 
		            "</li>";
				jQuery("#message-history").append(receiveMessageHtml);
				break;
			}
			case 3 : {// Presence
				if (message.status == 1) {// 成功出席
					// deliver(presenceMsg);
				} else if (message.status == 2) {// 出席失败
					// deliver(presenceMsg);
				}
				break;
			}
			case 4 : {// Register
				break;
			}
			case 5 : {// Acknowledge
				if (message.status == 1) {
				 	// plus.nativeUI.toast("发送成功");
				}
				break;
			}
			case 6 : {// Revise
				break;
			}
		}
		// end switch
		
	};
	/**
	ws.onerror = function(event) {
		
	};
	ws.onclose = function(event) {
		
	};
	**/
	$(document).ready(function() {
		$("#deliver").click(function() {
			var message = $("#message").val();
			var message = buildMessage(mid, $("#data-repo").attr("data-mid"), message);
			var replyMessageHtml = 
				"<li class=\"by-me\">" + 
		            "<div class=\"avatar pull-right\">" + 
		            "<img src=\"${cdn}image/icon.png\" alt=\"小助手\" class=\"chat_icon\"/>" + 
		          "</div>" + 
		          "<div class=\"chat-content\">" + 
		            "<div class=\"chat-meta\">" + new Date().format("yyyy-MM-dd hh:mm:ss") "<span class=\"pull-right\">小助手</span></div>" + 
		            	message + 
		            "<div class=\"clearfix\"></div>" + 
		          "</div>" + 
		        "</li>";
			jQuery("#message-history").append(replyMessageHtml);
			$("#message").val("");
			deliver(message);
		});
	});
	</script>
</head>
<body id="data-repo" data-mid="${user.imId}">
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
		                    <ul class="chats" id="message-history">
		                    
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
