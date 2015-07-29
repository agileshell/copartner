<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
	<meta charset="UTF-8" />
	<meta name="keywords" content="dap" />
	<meta name="description" content="dap" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>创客管理平台--即时聊天</title>
	<link rel="stylesheet" href="${cdn}css/bootstrap.css"></link>
	<link rel="stylesheet" href="${cdn}css/font-awesome.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${cdn}css/fullcalendar.css"></link>
	<link rel="stylesheet" href="${cdn}css/prettyPhoto.css"></link>
	<link rel="stylesheet" href="${cdn}css/rateit.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-datetimepicker.min.css"></link>
	<link rel="stylesheet" href="${cdn}css/jquery.cleditor.css"></link>
	<link rel="stylesheet" href="${cdn}css/uniform.default.css"></link>
	<link rel="stylesheet" href="${cdn}css/bootstrap-switch.css"></link>
	<link rel="stylesheet" href="${cdn}css/style.css"></link>
	<link rel="stylesheet" href="${cdn}css/widgets.css"></link>
	<script src="${cdn}js/jquery.js"></script>
	<!--[if lt IE 9]>
	<script src="${cdn}js/html5shim.js"></script>
	<![endif]-->
	<link rel="Shortcut Icon" href="${cdn}image/icon.png" />
	<style type="text/css">
		img.chat_icon {
			width: 40px;
			border: 0;
		}
	</style>
</head>
<body>
	<jsp:include page="control/header.jsp"/>
	<div class="content">
		<jsp:include page="control/sidebar.jsp"></jsp:include>
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
			                  <div class="pull-left">${user.name}</div>
			                  <div class="widget-icons pull-right"></div>  
			                  <div class="clearfix"></div>
			                </div>
			                <div class="widget-content">
			                  <div class="padd">
			                    <ul class="chats">
			                      <li class="by-me">
			                        <div class="avatar pull-left">
			                          <img src="${cdn}image/icon.png" alt="小助手" class="chat_icon"/>
			                        </div>
			                        <div class="chat-content">
			                          <div class="chat-meta">小助手<span class="pull-right">2015-07-29 12:39:40</span></div>
			                          大飞哥儿好啊！O(∩_∩)O哈哈哈~😄
			                          <div class="clearfix"></div>
			                        </div>
			                      </li> 
			                      <li class="by-other">
			                        <div class="avatar pull-right">
			                          <img src="${cdn}image/fei.liu.jpg" alt="${user.name}" class="chat_icon"/>
			                        </div>
			                        <div class="chat-content">
			                          <div class="chat-meta">2015-07-29 12:53:44<span class="pull-right">${user.name}</span></div>
			                          恩恩 我好着呢!!!
			                          <div class="clearfix"></div>
			                        </div>
			                      </li>   
			                      <li class="by-me">
			                        <div class="avatar pull-left">
			                          <img src="${cdn}image/icon.png" alt="小助手" class="chat_icon"/>
			                        </div>
			                        <div class="chat-content">
			                          <div class="chat-meta">小助手<span class="pull-right">2015-07-29 15:53:44</span></div>
			                          我们公司目前免费提供一套成人玩具有没有兴趣啊
			                          <div class="clearfix"></div>
			                        </div>
			                      </li>                                                              
			                    </ul>
			                  </div>
			                  <div class="widget-foot">
			                      <form class="form-inline">
									<div class="form-group col-lg-11">
										<input type="text" class="form-control" placeholder="输入消息内容"></input>
									</div>
									<button type="submit" class="btn btn-default">发送</button>
			                      </form>
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
		<div class="clearfix"></div>
	</div>
	<!--
	<jsp:include page="control/copy-rights.jsp"/>
	-->
	<script src="${cdn}js/bootstrap.js"></script>
	<script src="${cdn}js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="${cdn}js/fullcalendar.min.js"></script>
	<script src="${cdn}js/jquery.rateit.min.js"></script>
	<script src="${cdn}js/jquery.prettyPhoto.js"></script>
	<script src="${cdn}js/excanvas.min.js"></script>
	<script src="${cdn}js/jquery.flot.js"></script>
	<script src="${cdn}js/jquery.flot.resize.js"></script>
	<script src="${cdn}js/jquery.flot.pie.js"></script>
	<script src="${cdn}js/jquery.flot.stack.js"></script>
	<script src="${cdn}js/jquery.noty.js"></script>
	<script src="${cdn}js/themes/default.js"></script>
	<script src="${cdn}js/layouts/bottom.js"></script>
	<script src="${cdn}js/layouts/topRight.js"></script>
	<script src="${cdn}js/layouts/top.js"></script>
	<script src="${cdn}js/sparklines.js"></script>
	<script src="${cdn}js/jquery.cleditor.min.js"></script>
	<script src="${cdn}js/bootstrap-datetimepicker.min.js"></script>
	<script src="${cdn}js/jquery.uniform.min.js"></script>
	<script src="${cdn}js/bootstrap-switch.min.js"></script>
	<script src="${cdn}js/filter.js"></script>
	<script src="${cdn}js/custom.js"></script>
	<script src="${cdn}js/charts.js"></script>
	
</body>
</html>