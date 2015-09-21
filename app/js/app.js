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

	owner.apiURL = 'http://123.57.55.59:8080/';

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
	owner.logingByAuth = function(authUserInfo, callback) {
		//TODO
		var state = owner.getState();
		state.userId = 1;
		state.imId = 1;
		owner.setState(state);
		return callback();
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
		var state = app.getState();
		if (state.userId) {
			return state.userId;
		} else {
			return 0;
		}
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
					app.setState({});
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
		if (profileInfo.age.length <= 0) {
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
					app.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.createRongzi = function(rzInfo, callback) {
		callback = callback || $.noop;
		rzInfo = rzInfo || {};
		rzInfo.projectName = rzInfo.projectName || '';
		rzInfo.locationId = rzInfo.locationId || 0;
		rzInfo.industryDomainId = rzInfo.industryDomainId || 0;
		rzInfo.teamSizeId = rzInfo.teamSizeId || 0;
		rzInfo.financingPhaseId = rzInfo.financingPhaseId || 0;
		rzInfo.contact = rzInfo.contact || '';
		rzInfo.contactPerson = rzInfo.contactPerson || '';
		rzInfo.advantage = rzInfo.advantage || '';
		rzInfo.content = rzInfo.content || '';
		rzInfo.funding = rzInfo.funding;
		if (rzInfo.projectName.length <= 0) {
			return callback('项目名称不能为空');
		} else if (rzInfo.projectName.length > 50) {
			return callback('项目名称不能大于50个字符');
		}
		if (rzInfo.locationId == 0) {
			return callback('地区不能为空');
		}
		if (rzInfo.industryDomainId == 0) {
			return callback('领域不能为空');
		}
		if (rzInfo.teamSizeId == 0) {
			return callback('团队不能为空');
		}
		if (rzInfo.financingPhaseId == 0) {
			return callback('阶段不能为空');
		}
		if (rzInfo.contact.length > 30) {
			return callback('联系人不能大于30个字符');
		}
		if (rzInfo.contactPerson.length > 30) {
			return callback('联系电话不能大于30个字符');
		}
		if (rzInfo.advantage.length > 200) {
			return callback('优势不能大于200个字符');
		}
		if (rzInfo.content.length <= 0) {
			return callback('融资要求不能为空');
		} else if (rzInfo.content.length > 200) {
			return callback('融资要求不能大于200个字符');
		}

		mui.ajax(owner.apiURL + 'financing', {
			data: {
				projectName: rzInfo.projectName,
				locationId: rzInfo.locationId,
				industryDomainId: rzInfo.industryDomainId,
				teamSizeId: rzInfo.teamSizeId,
				financingPhaseId: rzInfo.financingPhaseId,
				contact: rzInfo.contact,
				contactPerson: rzInfo.contactPerson,
				advantage: rzInfo.advantage,
				content: rzInfo.content,
				hasBusinessRegistered: rzInfo.hasBusinessRegistered,
				funding: rzInfo.funding
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
					app.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};
	owner.listUserRongzis = function(offset, limit, from, to, successCallback, errorCallback) {
		var userId = owner.getUserId();
		mui.ajax(owner.apiURL + 'user/' + userId + '/financings', {
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

	owner.listRongzis = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'financings', {
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

	owner.createRongzhi = function(rzInfo, callback) {
		callback = callback || $.noop;
		rzInfo = rzInfo || {};
		rzInfo.projectName = rzInfo.projectName || '';
		rzInfo.locationId = rzInfo.locationId || 0;
		rzInfo.industryDomainId = rzInfo.industryDomainId || 0;
		rzInfo.teamSizeId = rzInfo.teamSizeId || 0;
		rzInfo.contact = rzInfo.contact || '';
		rzInfo.contactPerson = rzInfo.contactPerson || '';
		rzInfo.advantage = rzInfo.advantage || '';
		rzInfo.content = rzInfo.content || '';
		rzInfo.reward = rzInfo.reward || '';
		if (rzInfo.projectName.length <= 0) {
			return callback('项目名称不能为空');
		} else if (rzInfo.projectName.length > 50) {
			return callback('项目名称不能大于50个字符');
		}
		if (rzInfo.locationId == 0) {
			return callback('地区不能为空');
		}
		if (rzInfo.industryDomainId == 0) {
			return callback('领域不能为空');
		}
		if (rzInfo.teamSizeId == 0) {
			return callback('团队不能为空');
		}
		if (rzInfo.contact.length > 30) {
			return callback('联系人不能大于30个字符');
		}
		if (rzInfo.contactPerson.length > 30) {
			return callback('联系电话不能大于30个字符');
		}
		if (rzInfo.advantage.length > 200) {
			return callback('优势不能大于200个字符');
		}
		if (rzInfo.content.length <= 0) {
			return callback('融智要求不能为空');
		} else if (rzInfo.content.length > 200) {
			return callback('融智要求不能大于200个字符');
		}
		if (rzInfo.reward.length > 50) {
			return callback('回报不能大于50个字符');
		}

		mui.ajax(owner.apiURL + 'demand', {
			data: {
				type: 1,
				projectName: rzInfo.projectName,
				locationId: rzInfo.locationId,
				industryDomainId: rzInfo.industryDomainId,
				teamSizeId: rzInfo.teamSizeId,
				contact: rzInfo.contact,
				contactPerson: rzInfo.contactPerson,
				advantage: rzInfo.advantage,
				content: rzInfo.content,
				hasBusinessRegistered: rzInfo.hasBusinessRegistered,
				reward: rzInfo.reward
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
					app.setState({});
					owner.openLoginPage();
				} else {
					return callback(owner.ajaxErrorHandler(type));
				}
			}
		})
	};

	owner.listUserRongzhis = function(offset, limit, from, to, successCallback, errorCallback) {
		var userId = owner.getUserId();
		mui.ajax(owner.apiURL + 'user/' + userId + '/demands', {
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

	owner.listRongzhis = function(offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax(owner.apiURL + 'demands', {
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

		mui.ajax(owner.apiURL + 'project', {
			data: {
				name: projectInfo.name,
				logo: projectInfo.logo,
				projectPhaseId: projectInfo.projectPhaseId,
				locationId: projectInfo.locationId,
				industryDomainId: projectInfo.industryDomainId,
				teamSizeId: projectInfo.teamSizeId,
				contact: projectInfo.contact,
				contactPerson: projectInfo.contactPerson,
				advantage: projectInfo.advantage,
				content: projectInfo.content,
				hasBusinessRegistered: projectInfo.hasBusinessRegistered
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
					app.setState({});
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
		code_20300: '用户不存在'
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
			errorMessage = '系统错误!';
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

	/**
	 * 获取contents列表
	 **/
	owner.listContents = function(contentType, keyword, offset, limit, from, to, successCallback, errorCallback) {
		console.log(keyword + offset + '-' + offset + '-' + from + '-' + to);
		mui.ajax(owner.apiURL + 'contents', {
			data: {
				type: contentType,
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
		if (!course.coverImg) {
			courseInfo.coverImg = 'images/blank.jpg';
		} else {
			courseInfo.coverImg = course.coverImg;
		}

		return courseInfo;
	};

	owner.uploadCourse = function(courseInfo, callback) {
		callback = callback || $.noop;
		courseInfo = courseInfo || {};
		courseInfo.coverImg = courseInfo.coverImg || '';
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
		if (courseInfo.coverImg.length <= 0) {
			return callback('封皮不能为空');
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
					app.setState({});
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
			tutorInfo.domainName = '';
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
					app.setState({});
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
					app.setState({});
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
		mui.ajax(owner.apiURL + 'user/friends/search?keyword=' + keyword, {
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
}(mui, window.app = {}));