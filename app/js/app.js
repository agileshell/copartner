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
			return (password.length >= 6 && password.length <= 20 && password.match(/^[0-9a-zA-Z]*$/));
		};

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

		mui.ajax('http://120.24.228.100:8080/copartner/account/signin', {
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

	/**
	 * 获取校验码
	 **/
	owner.getCode = function(mobile, type, callback) {
		if (!checkMobile(mobile)) {
			return callback('手机号码不正确');
		}
		mui.ajax('http://120.24.228.100:8080/copartner/code/send', {
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

		mui.ajax('http://120.24.228.100:8080/copartner/account/signup', {
			data: {
				account: accountInfo.account,
				password: accountInfo.password,
				code: accountInfo.code
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

		mui.ajax('http://120.24.228.100:8080/copartner/account/password/reset', {
			data: {
				account: accountInfo.account,
				password: accountInfo.password,
				code: accountInfo.code
			},
			dataType: 'json',
			type: 'post',
			timeout: 10000,
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

	var errorCodeMsgMap = {
		ode_10000: '参数错误',
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
	owner.listnews = function(newsType, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax('http://120.24.228.100:8080/copartner/news', {
			data: {
				type: newsType,
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
		mui.ajax('http://120.24.228.100:8080/copartner/news/' + guid, {
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
	owner.listContents = function(contentType, offset, limit, from, to, successCallback, errorCallback) {
		mui.ajax('http://120.24.228.100:8080/copartner/contents', {
			data: {
				type: contentType,
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
		mui.ajax('http://120.24.228.100:8080/copartner/content/' + guid, {
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
}(mui, window.app = {}));