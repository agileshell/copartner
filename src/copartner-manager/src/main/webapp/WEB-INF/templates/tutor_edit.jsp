<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/ti-tags.tld" prefix="t"%>
<head>
    <title>编辑导师</title>
</head>
<body>
    <div class="mainbar">
        <div class="page-head">
            <h2 class="pull-left">导师管理</h2>
            <div class="bread-crumb pull-right">
                <a href="/home"><i class="icon-home"></i>首页</a><span class="divider">/</span>编辑导师
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
                                <div class="pull-left">编辑导师</div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="widget-content">
                                <div class="padd">
                                    <form id="add_form" class="form-horizontal" role="form" action="/tutor/update/${tutor.id}" method="post" enctype="multipart/form-data">
                                        <input name="tutorId" id="tutorId" type="hidden" value="${tutor.id}"></input>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">名称<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="name" id="name" type="text" class="form-control" placeholder="名称" value="${tutor.name}"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">手机号码<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="mobile" id="mobile" type="text" class="form-control" placeholder="手机号码" value="${tutor.mobile}"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">原头像:</label>
                                            <div class="col-lg-10">
	                                            <div class="gallery">
	                                                <a href="<t:cdn domain="${cdnDomain}" path="${tutor.avatar}"></t:cdn>" class="prettyPhoto[pp_gal]">
	                                                    <img src="<t:cdn domain="${cdnDomain}" path="${tutor.avatar}"></t:cdn>" width="500">
	                                                </a>
	                                            </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">新头像:</label>
                                            <div class="col-lg-10">
                                                <input name="avatar" id="avatar" type="file" class="form-control" placeholder="LOGO"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">职务<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <input name="title" id="title" type="text" class="form-control" placeholder="职务" value="${tutor.title}"></input>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">领域<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="domains" class="form-control" name="domains">
                                                    <option value="1" ${tutor.domains eq '1' ? 'selected' : ''}>互联网</option>
                                                    <option value="2" ${tutor.domains eq '2' ? 'selected' : ''}>移动互联网</option>
                                                    <option value="3" ${tutor.domains eq '3' ? 'selected' : ''}>电子商务</option>
                                                    <option value="4" ${tutor.domains eq '4' ? 'selected' : ''}>社交</option>
                                                    <option value="5" ${tutor.domains eq '5' ? 'selected' : ''}>游戏</option>
                                                    <option value="6" ${tutor.domains eq '6' ? 'selected' : ''}>云计算</option>
                                                    <option value="7" ${tutor.domains eq '7' ? 'selected' : ''}>IT服务</option>
                                                    <option value="8" ${tutor.domains eq '8' ? 'selected' : ''}>软件/工具</option>
                                                    <option value="9" ${tutor.domains eq '9' ? 'selected' : ''}>软硬件</option>
                                                    <option value="10" ${tutor.domains eq '10' ? 'selected' : ''}>数码电子</option>
                                                    <option value="12" ${tutor.domains eq '12' ? 'selected' : ''}>农业</option>
                                                    <option value="13" ${tutor.domains eq '13' ? 'selected' : ''}>环保</option>
                                                    <option value="11" ${tutor.domains eq '11' ? 'selected' : ''}>其它</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">创业经验<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="startupExp" class="form-control" name="startupExp">
                                                    <option value="一年" ${tutor.startupExp eq '一年' ? 'selected' : ''}>一年</option>
                                                    <option value="两年" ${tutor.startupExp eq '两年' ? 'selected' : ''}>两年</option>
                                                    <option value="三年~五年" ${tutor.startupExp eq '三年~五年' ? 'selected' : ''}>三年~五年</option>
                                                    <option value="五年~十年" ${tutor.startupExp eq '五年~十年' ? 'selected' : ''}>五年~十年</option>
                                                    <option value="十年以上" ${tutor.startupExp eq '十年以上' ? 'selected' : ''}>十年以上</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">管理经验<span class="cofrequired">*</span>:</label>
                                            <div class="col-lg-10">
                                                <select id="managementExp" class="form-control" name="managementExp">
                                                    <option value="一年" ${tutor.managementExp eq '一年' ? 'selected' : ''}>一年</option>
                                                    <option value="两年" ${tutor.managementExp eq '两年' ? 'selected' : ''}>两年</option>
                                                    <option value="三年~五年" ${tutor.managementExp eq '三年~五年' ? 'selected' : ''}>三年~五年</option>
                                                    <option value="五年~十年" ${tutor.managementExp eq '五年~十年' ? 'selected' : ''}>五年~十年</option>
                                                    <option value="十年以上" ${tutor.managementExp eq '十年以上' ? 'selected' : ''}>十年以上</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-2 control-label">简介:</label>
                                            <div class="col-lg-10">
                                                <textarea name="introduction" id="introduction" class="form-control" rows="3" placeholder="简介">${tutor.introduction}</textarea>
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
    <script>
        $(document).ready(function() {
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
                    introduction: {
                        required: true,
                        minlength: 2,
                        maxlength: 255
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
                    introduction: {
                        required: '简介不能为空',
                        minlength: "简介长度不能小于2个字符",
                        maxlength: "简介长度不能大于255个字符"
                    }
                },
                submitHandler: function(form) {
                    form.submit();
                }
            });
        });
    </script>
</body>
