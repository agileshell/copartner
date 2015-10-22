(function($, owner) {
	var checkEmail = function(email) {
			email = email || '';
			return (email.length > 3 && email.indexOf('@') > -1);
		},
		checkMobile = function(mobile) {
			mobile = mobile || '';
			return (mobile.length == 11 && mobile.match(/^1\d{10}$/));
		},
		checkPassword = function(password) {
			password = password || '';
			return (password.length >= 6 && password.length <= 16 && password.match(/^[0-9a-zA-Z]*$/));
		};

	owner.apiURL = 'http://192.168.4.106:8080/copartner-api/';

	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.account = loginInfo.account || '';
		loginInfo.password = loginInfo.password || '';
		if (!checkMobile(loginInfo.account)) {
			return callback('手机号码不正确');
		}
		if (loginInfo.password.length < 6) {
			return callback('密码最短为 6 个字符');
		}
		localStorage.setItem('$account', loginInfo.account);

		mui.ajax(owner.apiURL + 'account/signin', {
			data: {
				account: loginInfo.account,
				password: loginInfo.password
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (textStatus == 'success') {
					if (data.status == 'SUCCEED') {
						var state = owner.getState();
						state.userId = data.body.userId;
						state.roleId = data.body.roleId;
						state.imId = data.body.imId;
						state.name = data.body.name;
						state.avatar = data.body.avatar;
						owner.setState(state);
						return callback();
					} else {
						return callback(owner.ajaxFailedHandler(data.body.error.code));
					}
				} else {
					return callback('系统错误');
				}
			},
			error: function(xhr, type, errorThrown) {
				return callback(owner.ajaxErrorHandler(type));
			}
		})
	};

	/**
	 * 第三方登录
	 **/
	owner.logingByAuth = function(provider, authResult, authUserInfo, callback) {
		var loginInfo = {}, providerId = 0;
		if (provider == 'qq') {
			providerId = 1;
			loginInfo.nickname = authUserInfo.nickname;
			loginInfo.avatar = authUserInfo.figureurl_2;
			loginInfo.accessToken = authResult.access_token;
			loginInfo.uid = authResult.openid;
		} else if (provider == 'sinaweibo') {
			providerId = 2;
			loginInfo.nickname = authUserInfo.screen_name;
			loginInfo.avatar = authUserInfo.profile_image_url;
			loginInfo.accessToken = authResult.token;
			loginInfo.uid = authResult.uid;
		} else if (provider == 'weixin') {
			providerId = 3;
			loginInfo.nickname = authUserInfo.nickname;
			loginInfo.avatar = authUserInfo.headimgurl;
			loginInfo.accessToken = authResult.access_token;
			loginInfo.uid = authResult.unionid;
		}

		mui.ajax(owner.apiURL + 'account/signin/' + providerId, {
			data: loginInfo,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (textStatus == 'success') {
					if (data.status == 'SUCCEED') {
						var state = owner.getState();
						state.userId = data.body.userId;
						state.roleId = data.body.roleId;
						state.imId = data.body.imId;
						state.name = data.body.name;
						state.avatar = data.body.avatar;
						owner.setState(state);
						return callback();
					} else {
						return callback(owner.ajaxFailedHandler(data.body.error.code));
					}
				} else {
					return callback('系统错误');
				}
			},
			error: function(xhr, type, errorThrown) {
				return callback(owner.ajaxErrorHandler(type));
			}
		})
	};

	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};

	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		localStorage.setItem('$state', JSON.stringify(state));
	};

	owner.getUserId = function() {
		var state = owner.getState();
		if (state.userId) {
			return state.userId;
		} else {
			return 0;
		}
	};

	owner.getRoleId = function() {
		var state = owner.getState();
		if (state.roleId) {
			return state.roleId;
		} else {
			return 1;
		}
	};

	/**
	 * DeviceToken
	 **/
	owner.addDeviceToken = function(token, os, successCallback) {
		mui.ajax(owner.apiURL + 'user/device/' + os, {
			data: {
				deviceToken: token
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data) {
				successCallback();
			},
			error: function(xhr, type, errorThrown) {
				console.log(type);
			}
		})
	};

	/**
	 * 获取版本信息
	 **/
	owner.getAppVersion = function(successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'appVersion', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	/**
	 * 获取校验码
	 **/
	owner.getCode = function(mobile, type, callback) {
		if (!checkMobile(mobile)) {
			return callback('手机号码不正确');
		}
		mui.ajax(owner.apiURL + 'code/send', {
			data: {
				mobile: mobile,
				type: type
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (textStatus == 'success') {
					if (data.status == 'SUCCEED') {
						return callback();
					} else {
						return callback(owner.ajaxFailedHandler(data.body.error.code));
					}
				} else {
					return callback('系统错误');
				}
			},
			error: function(xhr, type, errorThrown) {
				return callback(owner.ajaxErrorHandler(type));
			}
		})
	};

	/**
	 * 新用户注册
	 **/
	owner.registry = function(accountInfo, callback) {
		callback = callback || $.noop;
		accountInfo = accountInfo || {};
		accountInfo.account = accountInfo.account || '';
		accountInfo.code = accountInfo.code || '';
		accountInfo.password = accountInfo.password || '';
		if (!checkMobile(accountInfo.account)) {
			return callback('手机号码不正确');
		}
		if (!checkPassword(accountInfo.password)) {
			return callback('密码格式错误');
		}
		if (accountInfo.code.length <= 0) {
			return callback('校验码不能为空');
		}
		if (accountInfo.name.length < 2 || accountInfo.name.length  > 15) {
			return callback('姓名必须是2-15个字符');
		}

		mui.ajax(owner.apiURL + 'account/signup', {
			data: {
				account: accountInfo.account,
				password: accountInfo.password,
				code: accountInfo.code,
				name: accountInfo.name,
				roleId: accountInfo.roleId
			},
			dataType: 'json',
			type: 'post',
			timeout: 10000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (textStatus == 'success') {
					if (data.status == 'SUCCEED') {
						var state = owner.getState();
						state.userId = data.body.userId;
						state.roleId = data.body.roleId;
						state.imId = data.body.imId;
						state.name = data.body.name;
						state.avatar = data.body.avatar;
						owner.setState(state);
						return callback();
					} else {
						return callback(owner.ajaxFailedHandler(data.body.error.code));
					}
				} else {
					return callback('系统错误');
				}
			},
			error: function(xhr, type, errorThrown) {
				return callback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.resetPwd = function(accountInfo, callback) {
		callback = callback || $.noop;
		accountInfo = accountInfo || {};
		accountInfo.account = accountInfo.account || '';
		accountInfo.code = accountInfo.code || '';
		accountInfo.confirmPassword = accountInfo.confirmPassword || '';
		accountInfo.password = accountInfo.password || '';
		if (!checkMobile(accountInfo.account)) {
			return callback('手机号码不正确');
		}
		if (accountInfo.code.length <= 0) {
			return callback('校验码不能为空');
		}
		if (!checkPassword(accountInfo.password)) {
			return callback('密码格式错误');
		}
		if (accountInfo.password != accountInfo.confirmPassword) {
			return callback('确认密码和密码不匹配');
		}

		mui.ajax(owner.apiURL + 'account/password/reset', {
			data: {
				account: accountInfo.account,
				password: accountInfo.password,
				code: accountInfo.code
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (textStatus == 'success') {
					if (data.status == 'SUCCEED') {
						return callback();
					} else {
						return callback(owner.ajaxFailedHandler(data.body.error.code));
					}
				} else {
					return callback('系统错误');
				}
			},
			error: function(xhr, type, errorThrown) {
				return callback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.getUserProfile = function(userId, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/' + userId + '/profile', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data, textStatus) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};
	owner.getProfile = function(successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/profile', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					errorCallback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};
	owner.updateProfile = function(profileInfo, callback) {
		callback = callback || $.noop;
		profileInfo = profileInfo || {};
		profileInfo.name = profileInfo.name || '';
		profileInfo.email = profileInfo.email || '';
		profileInfo.avatar = profileInfo.avatar || '';
		profileInfo.age = profileInfo.age || '';
		profileInfo.gender = profileInfo.gender || '';
		profileInfo.locationId = profileInfo.locationId || 0;
		profileInfo.startupStatusId = profileInfo.startupStatusId || 0;
		profileInfo.startupRoleId = profileInfo.startupRoleId || 0;
		profileInfo.domainIds = profileInfo.domainIds || 0;
		profileInfo.introduction = profileInfo.introduction || '';
		if (profileInfo.name.length <= 0) {
			return callback('姓名不能为空');
		} else if (profileInfo.name.length > 32) {
			return callback('姓名不能大于32个字符');
		}
		if (profileInfo.email.length > 0 && !checkEmail(profileInfo.email)) {
			return callback('邮件格式格式不正确');
		}
		if (roleId ==1 && profileInfo.age.length <= 0) {
			return callback('年龄不能为空');
		}
		if (profileInfo.gender.length <= 0) {
			return callback('性别不能为空');
		}
		if (profileInfo.locationId == 0) {
			return callback('地区不能为空');
		}
		if (profileInfo.startupRoleId == 0) {
			return callback('角色不能为空');
		}
		if (profileInfo.startupStatusId == 0) {
			return callback('目前状态不能为空');
		}
		if (profileInfo.domainIds == 0) {
			return callback('领域不能为空');
		}
		if (profileInfo.introduction.length <= 0) {
			return callback('简介不能为空');
		} else if (profileInfo.introduction.length > 100) {
			return callback('简介不能大于100个字符');
		}

		mui.ajax(owner.apiURL + 'user/profile', {
			data: profileInfo,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

    owner.submitAuthenticate = function(info, callback) {
		callback = callback || $.noop;
		info = info || {};
		info.roleId = info.roleId || 0;
		info.idNumber = info.idNumber || '';
		info.idPicture = info.idPicture || '';
		info.authenticationInfo = info.authenticationInfo || '';
		info.investmentOrg = info.investmentOrg || '';
		info.investmentStyle = info.investmentStyle || '';
		info.professionId = info.professionId || 0;

		if (info.roleId == 0) {
			return callback('角色不能为空');
		} 
		if (info.idNumber.length <= 0) {
			return callback('身份证号码不能为空');
		}
		if (info.idPicture.length <= 0) {
			return callback('请上传您的身份证');
		}
		if (info.roleId == 2 && info.investmentOrg.length <= 0) {
			return callback('投机机构不能为空');
		}
		if (info.roleId == 2 && info.investmentStyle.length <= 0) {
			return callback('投机风格不能为空');
		}
		if (info.roleId == 3 && info.professionId == 0) {
			return callback('导师类型不能为空');
		}

		if (info.authenticationInfo.length < 10) {
			return callback('认证说明不能小于10个字符');
		} else if (info.authenticationInfo.length > 100) {
			return callback('认证说明不能大于100个字符');
		}
		
		if (info.roleId == 1) {
			info.investmentOrg = '';
			info.investmentStyle = '';
			info.professionId = 0;
		} else if (info.roleId == 2) {
			info.professionId = 0;
		} else {
			info.investmentOrg = '';
			info.investmentStyle = '';
		}

		mui.ajax(owner.apiURL + 'user/authenticate', {
			data: info,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.commentRequirement = function(requirementId, content, callback) {
		callback = callback || $.noop;
		var requestData = {};
		requestData.content = content;

		if (content.length <= 0) {
			return callback('评论不能为空');
		} else if (content.length > 100) {
			return callback('评论不能大于100个字符');
		}

		mui.ajax(owner.apiURL + 'requirement/' + requirementId + '/comment', {
			data: requestData,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

    owner.listRequirementComments = function(requirementId, offset, limit, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'requirement/' + requirementId + '/comments', {
			data: {
				offset: offset,
				limit: limit
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.createRequirement = function(req, callback) {
		callback = callback || $.noop;
		req = req || {};
		req.type = req.type;
		req.projectId = req.projectId || 0;
		req.content = req.content || '';

		if ((req.type == 2 && req.projectId == 0) || (req.type == 3 && req.projectId == 0)) {
			return callback('请选择关联的项目');
		}
		if (req.content.length <= 0) {
			return callback('需求信息不能为空');
		} else if (req.content.length > 200) {
			return callback('需求信息不能大于200个字符');
		}

		mui.ajax(owner.apiURL + 'requirement', {
			data: {
				type: req.type,
				projectId: req.projectId,
				content: req.content
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.listUserRequirements = function(offset, limit, from, to, successCallback, errorCallback) {
		var userId = owner.getUserId();
		mui.ajax(owner.apiURL + 'user/' + userId + '/requirements', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {

				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.listRequirements = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'requirements', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.refreshRequirements = function(ids, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'requirements/refresh?ids=' + ids, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.getRequirement= function(id, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'requirement/' + id, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};
	owner.processRequirement = function(req) {
		var res = {};
		res.id = req.id;
		res.type = req.type;
		res.content = req.content;
		res.status = req.status;
		res.likeCount = req.likeCount;
		res.commentCount = req.commentCount;
		res.user = req.user;
		res.likers = req.likers;
		res.comments = req.comments;
		res.created = req.created;
		res.isliked = req.isliked;

		if (req.type == 1) {
			res.typeInfo = '寻求加入团队';
		} else if (req.type == 2) {
			res.typeInfo = '寻求搭档';
		} else if (req.type == 3) {
			res.typeInfo = '寻求融资';
		} else if (req.type == 4) {
			res.typeInfo = '寻求融智';
		} else {
			res.typeInfo = '投资项目';
		}

		if (req.project != null) {
			res.project = req.project;
			res.displayed = '';
		} else {
			res.displayed = 'display: none;';

			var project = {};
			project.id = 0;
			project.name = '';
			project.logo = '';
			project.content = '';
			project.projectPhase = '';
			project.location = '';
			project.industryDomain = '';
			project.teamSize = '';

			res.project = project;
		}

		if (req.isliked) {
			res.liked = 'selected';
		} else {
			res.liked = '';
		}
		return res;
	};

	owner.likeRequirement = function(id, callback) {
		mui.ajax(owner.apiURL + 'requirement/' + id + '/like', {
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.unlikeRequirement = function(id, callback) {
		mui.ajax(owner.apiURL + 'requirement/' + id + '/like', {
			dataType: 'json',
			type: 'delete',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.likeProject = function(id, callback) {
		mui.ajax(owner.apiURL + 'project/' + id + '/like', {
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.unlikeProject = function(id, callback) {
		mui.ajax(owner.apiURL + 'project/' + id + '/like', {
			dataType: 'json',
			type: 'delete',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.getProject = function(id, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'project/' + id, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.createProject = function(projectInfo, callback) {
		callback = callback || $.noop;
		projectInfo = projectInfo || {};
		projectInfo.logo = projectInfo.logo || '';
		projectInfo.name = projectInfo.name || '';
		projectInfo.projectPhaseId = projectInfo.projectPhaseId || 0;
		projectInfo.locationId = projectInfo.locationId || 0;
		projectInfo.industryDomainId = projectInfo.industryDomainId || 0;
		projectInfo.teamSizeId = projectInfo.teamSizeId || 0;
		projectInfo.contact = projectInfo.contact || '';
		projectInfo.contactPerson = projectInfo.contactPerson || '';
		projectInfo.advantage = projectInfo.advantage || '';
		projectInfo.content = projectInfo.content || '';

		projectInfo.registerContest = projectInfo.registerContest || 'false';
		projectInfo.hasBusinessRegistered = projectInfo.hasBusinessRegistered || 'false';
		projectInfo.businessLicense = projectInfo.businessLicense || '';
		projectInfo.businessLicenseImg = projectInfo.businessLicenseImg || '';
		projectInfo.locationCampus = projectInfo.locationCampus || '';
		projectInfo.instance = projectInfo.instance || '';
		projectInfo.legalFormation = projectInfo.legalFormation || '';
		projectInfo.employqty = projectInfo.employqty || 0;
		projectInfo.regtime = projectInfo.regtime || '';
		projectInfo.legalPerson = projectInfo.legalPerson || '';
		projectInfo.userCategory = projectInfo.userCategory || '';
		projectInfo.idNumber = projectInfo.idNumber || '';
		projectInfo.bankName = projectInfo.bankName || '';
		projectInfo.bankUserName = projectInfo.bankUserName || '';
		projectInfo.bankAccount = projectInfo.bankAccount || '';
		projectInfo.supportMoney = projectInfo.supportMoney || '';

		if (projectInfo.name.length <= 0) {
			return callback('项目名称不能为空');
		} else if (projectInfo.name.length > 50) {
			return callback('项目名称不能大于50个字符');
		}
		if (projectInfo.projectPhaseId == 0) {
			return callback('项目阶段不能为空');
		}
		if (projectInfo.locationId == 0) {
			return callback('地区不能为空');
		}
		if (projectInfo.industryDomainId == 0) {
			return callback('领域不能为空');
		}
		if (projectInfo.teamSizeId == 0) {
			return callback('团队不能为空');
		}
		if (projectInfo.contact.length > 30) {
			return callback('联系人不能大于30个字符');
		}
		if (projectInfo.contactPerson.length > 30) {
			return callback('联系电话不能大于30个字符');
		}
		if (projectInfo.advantage.length > 200) {
			return callback('优势不能大于200个字符');
		}
		if (projectInfo.content.length <= 0) {
			return callback('实施条件不能为空');
		} else if (projectInfo.content.length > 200) {
			return callback('实施条件不能大于200个字符');
		}

		if (projectInfo.registerContest == "true") {
			if (projectInfo.locationCampus.length <= 0) {
				return callback('所在市县或园区不能为空');
			}
			if (projectInfo.instance.length <= 0) {
				return callback('创业实体名称不能为空');
			}
			if (projectInfo.legalFormation.length <= 0) {
				return callback('企业法律形态不能为空');
			}
			if (projectInfo.employqty == 0) {
				return callback('吸纳就业人数不能为空');
			}
			if (projectInfo.regtime.length <= 0) {
				return callback('注册时间不能为空');
			}
			if (projectInfo.legalPerson.length <= 0) {
				return callback('法定代表人不能为空');
			}
			if (projectInfo.userCategory.length <= 0) {
				return callback('人员类别不能为空');
			}
			if (projectInfo.idNumber.length <= 0) {
				return callback('身份证不能为空');
			}
			if (projectInfo.bankName.length <= 0) {
				return callback('开户行不能为空');
			}
			if (projectInfo.bankUserName.length <= 0) {
				return callback('开户名不能为空');
			}
			if (projectInfo.bankAccount.length <= 0) {
				return callback('开户账号不能为空');
			}
			if (projectInfo.supportMoney.length <= 0) {
				return callback('申请扶持金额不能为空');
			}
		}

		mui.ajax(owner.apiURL + 'project', {
			data: projectInfo,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.listUserProjects = function(offset, limit, from, to, successCallback, errorCallback) {
		var userId = owner.getUserId();
		mui.ajax(owner.apiURL + 'user/' + userId + '/projects', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.listProjects = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'projects', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.listOwerProjects = function(successCallback) {
		var userId = owner.getUserId();
		mui.ajax(owner.apiURL + 'user/' + userId + '/projects', {
			data: {
				offset: 0,
				limit: 100
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				return successCallback(data);
			}
		})
	};

	owner.openLoginPage = function() {
		mui.openWindow({
			id: 'login',
			url: '../login.html',
			show: {
				aniShow: 'pop-in'
			}
		});
	};

	var errorCodeMsgMap = {
		code_10000: '参数错误',
		code_10100: '文件为空',
		code_10101: '文件类型不正确',
		code_10102: '文件过大',
		code_10103: '文件缺少最小尺寸',
		code_10104: '图片过宽',
		code_10105: '图片过高',
		code_10106: '图片缺少最小宽度',
		code_10107: '图片缺少最小高度',
		code_10108: '验证图片出错',
		code_10109: '上传文件出错',
		code_10110: '图片地址不正确',
		code_10200: '地区不存在',
		code_10210: '创业状态不存在',
		code_10220: '个人角色不存在',
		code_10230: '行业领域不存在',
		code_10240: '项目阶段不存在',
		code_10250: '团队规模不存在',
		code_20001: '账号或密码错误',
		code_20000: '账号不存在',
		code_20001: '账号或密码错误',
		code_20002: '账号不可用',
		code_20003: '此电子邮件已被注册',
		code_20004: '此手机号码已被注册',
		code_20005: '获取Open ID失败',
		code_20006: '获取第三方用户信息失败',
		code_20100: '校验码不正确',
		code_20101: '短信发送条数超过日上限',
		code_20102: '短信发送失败',
		code_20200: '旧密码不正确',
		code_20300: '用户不存在',

		code_30000: '该需求不存在',
		code_30001: '无权操作该需求',
		code_30100: ' 该项目不存在',
		code_30101: '无权操作该项目',
		code_30200: '该文章不存在',
		code_30300: '课程不存在',
		code_30400: '问题不存在',
		code_30401: '问题类别不存在',
		code_30402: '导师不存在',
		code_30403: '无权回答此问题',
		code_30500: '创业园不存在',
		code_30600: '投资机构不存在'
	};

	/**
	 * ajax request failed handler
	 **/
	owner.ajaxFailedHandler = function(code) {
		var msg = errorCodeMsgMap['code_' + code];
		if (undefined === msg) {
			msg = '请求出错';
		}
		return msg;
	};

	/**
	 * ajax request error handler
	 **/
	owner.ajaxErrorHandler = function(errorType) {
		var errorMessage = '';
		if ('abort' == errorType) {
			errorMessage = '无网络连接!';
		} else if ('timeout' == errorType) {
			errorMessage = '网络较差，连接请求超时!';
		} else {
			errorMessage = '网络异常!';
		}

		return errorMessage;
	};

	/**
	 * 获取news列表
	 **/
	owner.listnews = function(newsType, keyword, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'news', {
			data: {
				type: newsType,
				keyword: keyword,
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * 获取news详情
	 **/
	owner.getNewsByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'news/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * news对象包装
	 **/
	owner.processNews = function(news) {
		var newsInfo = {};
		newsInfo.id = news.newsId;
		newsInfo.title = news.title;
		newsInfo.synopsis = news.synopsis;
		if (!news.coverImg) {
			newsInfo.image = 'images/blank.jpg';
		} else {
			newsInfo.image = news.coverImg;
		}
		return newsInfo;
	};

	owner.likeNews = function(id, callback) {
		mui.ajax(owner.apiURL + 'news/' + id + '/like', {
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	/**
	 * 获取contents列表
	 **/
	owner.listContents = function(keyword, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'contents', {
			data: {
				keyword: keyword,
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * 获取content详情
	 **/
	owner.getContentByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'content/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * content对象包装
	 **/
	owner.processContent = function(content) {
		var contentInfo = {};
		contentInfo.id = content.contentId;
		contentInfo.title = content.title;
		contentInfo.synopsis = content.synopsis;
		if (!content.coverImg) {
			contentInfo.image = 'images/blank.jpg';
		} else {
			contentInfo.image = content.coverImg;
		}
		return contentInfo;
	};

	owner.likeContent = function(id, callback) {
		mui.ajax(owner.apiURL + 'content/' + id + '/like', {
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	/**
	 * 获取Investorg列表
	 **/
	owner.listInvestorgs = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'investorgs', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * 获取Investorg详情
	 **/
	owner.getInvestorgByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'investorg/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 *Investorg对象包装
	 **/
	owner.processInvestorg = function(invest) {
		var investInfo = {};
		investInfo.id = invest.id;
		investInfo.name = invest.name;
		investInfo.specials = invest.specials;
		investInfo.hardware = invest.hardware;
		if (!invest.logo) {
			investInfo.image = 'images/blank.jpg';
		} else {
			investInfo.image = invest.logo;
		}

		return investInfo;
	};

	/**
	 * 获取pioneerpark列表
	 **/
	owner.listPioneerparks = function(province, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'pioneerparks', {
			data: {
				province: province
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				console.log(JSON.stringify(data));
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * 获取pioneerpark详情
	 **/
	owner.getPioneerparkByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'pioneerpark/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 *pioneerpark对象包装
	 **/
	owner.processPioneerparks = function(pioneerpark) {
		var obj = {};
		obj.id = pioneerpark.id;
		obj.name = pioneerpark.name;
		obj.longitude = pioneerpark.longitude;
		obj.latitude = pioneerpark.latitude;

		return obj;
	};

	/**
	 * 获取course列表
	 **/
	owner.listcourses = function(isFree, keyword, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'courses', {
			data: {
				isFree: isFree,
				keyword: keyword,
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * 获取course详情
	 **/
	owner.getCourseByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'course/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * course对象包装
	 **/
	owner.processCourse = function(course) {
		var courseInfo = {};
		courseInfo.id = course.courseId;
		courseInfo.name = course.name;
		courseInfo.synopsis = course.synopsis;
		courseInfo.speaker = course.speaker;
		courseInfo.time = course.time;
		courseInfo.clicks = course.clicks;
		courseInfo.url = course.url;
		if (!course.coverImg) {
			courseInfo.coverImg = '../images/blank.jpg';
		} else {
			courseInfo.coverImg = course.coverImg;
		}

		return courseInfo;
	};

	owner.uploadCourse = function(courseInfo, callback) {
		callback = callback || $.noop;
		courseInfo = courseInfo || {};
		courseInfo.name = courseInfo.name || '';
		courseInfo.speaker = courseInfo.speaker || '';
		courseInfo.time = courseInfo.time || 0;
		courseInfo.synopsis = courseInfo.synopsis || '';
		courseInfo.url = courseInfo.url || '';
		if (courseInfo.name.length <= 0) {
			return callback('课程名称不能为空');
		} else if (courseInfo.name.length > 30) {
			return callback('课程名称不能大于30个字符');
		}
		if (courseInfo.url.length <= 0) {
			return callback('视频不能为空');
		}
		if (courseInfo.speaker.length <= 0) {
			return callback('主讲人不能为空');
		}
		if (courseInfo.time == 0) {
			return callback('时长不能为空');
		}
		if (courseInfo.synopsis.length <= 0) {
			return callback('简介不能为空');
		} else if (courseInfo.synopsis.length > 100) {
			return callback('简介不能大于100个字符');
		}

		mui.ajax(owner.apiURL + 'course', {
			data: courseInfo,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	/**
	 * 获取tutor列表
	 **/
	owner.listTutors = function(keyword, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'tutors', {
			data: {
				keyword: keyword,
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
    owner.listSelectTutors = function(successCallback) {
		mui.ajax(owner.apiURL + 'tutors', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			}
		})
	};
	/**
	 * 获取tutor详情
	 **/
	owner.getTutorByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'tutor/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};
	/**
	 * tutor对象包装
	 **/
	owner.processTutor = function(tutor) {
		var tutorInfo = {};
		tutorInfo.id = tutor.tutorId;
		tutorInfo.name = tutor.name;
		tutorInfo.professionName = tutor.professionName;
		if (!tutor.avatar) {
			tutorInfo.avatar = 'images/blank.jpg';
		} else {
			tutorInfo.avatar = tutor.avatar;
		}
		if (tutor.domain != null) {
			tutorInfo.domainId = tutor.domain.id;
			tutorInfo.domainName = tutor.domain.name;
		} else {
			tutorInfo.domainId = 0;
			tutorInfo.domainName = '暂无';
		}

		return tutorInfo;
	};

	/**
	 * 获取question列表
	 **/
	owner.listQuestions = function(keyword, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'questions', {
			data: {
				keyword: keyword,
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listQuestionCategories = function(successCallback) {
		mui.ajax(owner.apiURL + 'question/categories', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			}
		})
	};
	/**
	 * question对象包装
	 **/
	owner.processQuestion = function(question) {
		var questionInfo = {};
		questionInfo.id = question.questionId;
		questionInfo.title = question.title;
		questionInfo.categoryId = question.categoryId;
		questionInfo.categoryName = question.categoryName;
		questionInfo.content = question.content;
		questionInfo.questioner = question.questioner;
		questionInfo.created = question.created;

		return questionInfo;
	};

	owner.createQuestion = function(questionInfo, callback) {
		callback = callback || $.noop;
		questionInfo = questionInfo || {};
		questionInfo.title = questionInfo.title || '';
		questionInfo.content = questionInfo.content || '';
		questionInfo.permission = questionInfo.permission || 0;
		questionInfo.tutorId = questionInfo.tutorId || 0;
		questionInfo.categoryId = questionInfo.categoryId || 0;
		if (questionInfo.title.length <= 0) {
			return callback('问题不能为空');
		} else if (questionInfo.title.length > 50) {
			return callback('问题不能大于50个字符');
		}
		if (questionInfo.categoryId == 0) {
			return callback('类别不能为空');
		}
		if (questionInfo.tutorId == 0) {
			return callback('导师不能为空');
		}
		if (questionInfo.content.length <= 0) {
			return callback('内容不能为空');
		} else if (questionInfo.content.length > 200) {
			return callback('内容不能大于200个字符');
		}

		mui.ajax(owner.apiURL + 'question', {
			data: questionInfo,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				console.log(JSON.stringify(data));
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.answerQuestion = function(questionId, content, callback) {
		if (content.length <= 0) {
			return callback('内容不能为空');
		} else if (content.length > 200) {
			return callback('内容不能大于200个字符');
		}

		mui.ajax(owner.apiURL + 'question/'+ questionId +'/answer', {
			data: {
				content: content
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
					return callback(owner.ajaxErrorHandler(type));
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	/**
	 * 获取course详情
	 **/
	owner.getQuestionByGuid = function(guid, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'question/' + guid, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};


	owner.searchFriends = function(keyword, successCallback, errorCallback) {
		if (keyword.length <= 0) {
			return errorCallback('关键字不能为空');
		}
		mui.ajax(owner.apiURL + 'user/friends/search?keyword=' + keyword, {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.addFriend = function(friendId, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/friend/' + friendId, {
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listFriends = function(successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/friends', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.getlatestContest = function(successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'contest/latest', {
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listContestEntries = function(contestId, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'contestEntries', {
			data: {
				contestId: contestId
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				if (data.status == 'SUCCEED') {
					return successCallback(data);
				} else {
					return errorCallback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				return errorCallback(owner.ajaxErrorHandler(type));
			}
		})
	};

	owner.getContestEntry = function(id, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'contestEntry/' + id, {
			dataType: 'json',
			type: 'get',
			timeout: 10000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.registerContest = function(contestId, req, callback) {
		callback = callback || $.noop;
		req = req || {};
		req.projectId = req.projectId || 0;
		req.hasBusinessRegistered = req.hasBusinessRegistered || 'false';
		req.businessLicense = req.businessLicense || '';
		req.businessLicenseImg = req.businessLicenseImg || '';

		req.locationCampus = req.locationCampus || '';
		req.instance = req.instance || '';
		req.legalFormation = req.legalFormation || '';
		req.employqty = req.employqty || 0;
		req.regtime = req.regtime || '';
		req.legalPerson = req.legalPerson || '';
		req.userCategory = req.userCategory || '';
		req.idNumber = req.idNumber || '';
		req.bankName = req.bankName || '';
		req.bankUserName = req.bankUserName || '';
		req.bankAccount = req.bankAccount || '';
		req.supportMoney = req.supportMoney || '';

		if (req.projectId == 0) {
			return callback('请选择参赛项目');
		}
		if (req.locationCampus.length <= 0) {
			return callback('所在市县或园区不能为空');
		}
		if (req.instance.length <= 0) {
			return callback('创业实体名称不能为空');
		}
		if (req.legalFormation.length <= 0) {
			return callback('企业法律形态不能为空');
		}
		if (req.employqty == 0) {
			return callback('吸纳就业人数不能为空');
		}
		if (req.regtime.length <= 0) {
			return callback('注册时间不能为空');
		}
		if (req.legalPerson.length <= 0) {
			return callback('法定代表人不能为空');
		}
		if (req.userCategory.length <= 0) {
			return callback('人员类别不能为空');
		}
		if (req.idNumber.length <= 0) {
			return callback('身份证不能为空');
		}
		if (req.bankName.length <= 0) {
			return callback('开户行不能为空');
		}
		if (req.bankUserName.length <= 0) {
			return callback('开户名不能为空');
		}
		if (req.bankAccount.length <= 0) {
			return callback('开户账号不能为空');
		}
		if (req.supportMoney.length <= 0) {
			return callback('申请扶持金额不能为空');
		}

		mui.ajax(owner.apiURL + 'contest/' + contestId + '/register', {
			data: req,
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.vote = function(entryId, comment, callback) {
		if (comment.length <= 0) {
			return callback('评语不能为空');
		}

		mui.ajax(owner.apiURL + 'contestEntry/' + entryId + '/vote', {
			data: {
				comment: comment
			},
			dataType: 'json',
			type: 'post',
			timeout: 5000,
			success: function(data, textStatus) {
				if (data.status == 'SUCCEED') {
					return callback();
				} else {
					return callback(owner.ajaxFailedHandler(data.body.error.code));
				}
			},
			error: function(xhr, type, errorThrown) {
				if (errorThrown == 'Forbidden') {
					owner.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.listContestVotes = function(entryId, offset, limit, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'contestEntry/' + entryId + '/votes', {
			data: {
				offset: offset,
				limit: limit
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listFavourites = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/favourites', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listOwnQuestions = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/questions', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

	owner.listCurrentTutorQuestions = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'user/tutor/questions', {
			data: {
				offset: offset,
				limit: limit,
				from: from,
				to: to
			},
			dataType: 'json',
			type: 'get',
			timeout: 5000,
			success: function(data) {
				successCallback(data);
			},
			error: function(xhr, type, errorThrown) {
				errorCallback(type);
			}
		})
	};

}(mui, window.app = {}));