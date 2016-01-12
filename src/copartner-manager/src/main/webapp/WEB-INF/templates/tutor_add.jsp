<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
    <title>新建导师</title>
    <link href="${cdn}js/kindeditor/themes/default/default.css" rel="stylesheet" />
</head>
<body>
    <div class="mainbar">
        <div class="page-head">
            <h2 class="pull-left">导师管理</h2>
            <div class="bread-crumb pull-right">
                <a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>新建导师
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="matter">
            <div class="container">
                <!--row start-->
                <div class="row">
                    <div class="col-md-12">
                        <div class="widget wgreen">
                            <div class="widget-head">
                                <div class="pull-left">新建导师</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="widget-content">
                                <div class="padd">
                                    <form id="add_form" class="form-horizontal" role="form" action="/tutor/save" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">名称<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="name" id="name" type="text" class="form-control" placeholder="名称"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">手机号码<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="mobile" id="mobile" type="text" class="form-control" placeholder="手机号码"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">头像:</label>
                                            <div class="col-lg-10">
                                                <input name="avatar" id="avatar" type="file" class="form-control" placeholder="LOGO"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">职务<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="title" id="title" type="text" class="form-control" placeholder="职务"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">领域<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="domains" class="form-control" name="domains">
                                                    <option value="1">互联网</option>
                                                    <option value="2">移动互联网</option>
                                                    <option value="3">电子商务</option>
                                                    <option value="4">社交</option>
                                                    <option value="5">游戏</option>
                                                    <option value="6">云计算</option>
                                                    <option value="7">IT服务</option>
                                                    <option value="8">软件/工具</option>
                                                    <option value="9">软硬件</option>
                                                    <option value="10">数码电子</option>
                                                    <option value="12">农业</option>
                                                    <option value="13">环保</option>
                                                    <option value="11">其它</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">创业经验<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="startupExp" class="form-control" name="startupExp">
                                                    <option value="一年">一年</option>
                                                    <option value="两年">两年</option>
                                                    <option value="三年~五年">三年~五年</option>
                                                    <option value="五年~十年">五年~十年</option>
                                                    <option value="十年以上">十年以上</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">管理经验<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="managementExp" class="form-control" name="managementExp">
                                                    <option value="一年">一年</option>
                                                    <option value="两年">两年</option>
                                                    <option value="三年~五年">三年~五年</option>
                                                    <option value="五年~十年">五年~十年</option>
                                                    <option value="十年以上">十年以上</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">话题:</label>
                                            <div class="col-lg-10">
                                                <textarea name="topic" id="topic" class="form-control" rows="10" placeholder="话题">${tutor.topic}</textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">简介:</label>
                                            <div class="col-lg-10">
                                                <textarea name="introduction" id="introduction" class="form-control" rows="3" placeholder="简介"></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-offset-1 col-lg-12">
                                                <button type="submit" class="btn btn-default">提交</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="widget-foot"></div>
                        </div>
                    </div>
                </div>
                <!--row end-->
            </div>
        </div>
    </div>

    <script charset="utf-8" src="${cdn}js/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${cdn}js/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${cdn}js/kindeditor/plugins/autoheight/autoheight.js"></script>
    <script>
        $(document).ready(function() {
            KindEditor.ready(function(K) {
                window.editor = K.create('#topic', {
                    langType : 'zh_CN',
                    uploadJson : '/editor/file_upload',
                    items : [
                             'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
                             'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                             'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                             'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                             'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                             'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                             'flash', 'media', 'insertfile', 'table', 'hr', 'baidumap', 'pagebreak',
                             'anchor', 'link', 'unlink'
                    ],
                    minHeight : 300,
                    width: "100%",
                    minWidth: 300,
                    autoHeightMode : true,
                    afterCreate : function() {
                        this.loadPlugin('autoheight');
                    },
                    afterBlur: function(){this.sync();}
                });
            });
            KindEditor.ready(function(K) {
                window.editor = K.create('#introduction', {
                    langType : 'zh_CN',
                    uploadJson : '/editor/file_upload',
                    items : [
                             'source', '|', 'undo', 'redo', '|', 'preview', 'template', 'code', 'cut', 'copy', 'paste',
                             'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                             'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                             'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                             'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                             'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
                             'flash', 'media', 'insertfile', 'table', 'hr', 'baidumap', 'pagebreak',
                             'anchor', 'link', 'unlink'
                    ],
                    minHeight : 300,
                    width: "100%",
                    minWidth: 300,
                    autoHeightMode : true,
                    afterCreate : function() {
                        this.loadPlugin('autoheight');
                    },
                    afterBlur: function(){this.sync();}
                });
            });

        	$.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
                var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写您的手机号码");
            $('#name').focus();
            $('#add_form').validate({
                onsubmit:true,
                  onfocusout:false,
                  onkeyup:false,
                  onkeyup:false,
                  onclick:false,
                rules: {
                	name: {
                        required: true,
                        minlength: 2,
                        maxlength: 15
                    },
                    mobile: {
                        required: true,
                        isMobile : true
                    },
                    title: {
                        required: true,
                        minlength: 2,
                        maxlength: 15
                    },
                    avatar: {
                        required: true
                    },
                    topic: {
                        required: true
                    },
                    introduction: {
                        required: true
                    }
                },
                messages: {
                	name: {
                         required: '姓名不能为空',
                         minlength: "姓名长度不能小于2个字符",
                         maxlength: "姓名长度不能大于15个字符"
                    },
                    title: {
                        required: '职务不能为空',
                        minlength: "职务长度不能小于2个字符",
                        maxlength: "职务长度不能大于15个字符"
                    },
                    mobile: {
                    	required: '手机号码不能为空',
                        isMobile: "手机号码格式不正确"
                    },
                    avatar: {
                        required: "请上传头像"
                    },
                    topic: {
                        required: '话题不能为空'
                    },
                    introduction: {
                        required: '简介不能为空'
                    }
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        });
    </script>
</body>
