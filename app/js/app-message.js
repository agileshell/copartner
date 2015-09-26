(function($, im) {
	var ws; // websocket object
	im.wsURI = 'ws://123.57.55.59:9099';
	im.init = function(mid, messageCallback) {
		if (!window.WebSocket) {
			window.WebSocket = window.MozWebSocket;
		}
		if (window.WebSocket) {
			ws = new WebSocket(im.wsURI);
		}
		ws.onopen = function(event) { // 连接到服务器并上线
			deliver(im.buildPresence(mid), function(data) {
				if (!data.success) {
					plus.nativeUI.toast(data.msg);
				}
			});
			setTimeout(function() { // 3分钟定时检查是否在线
				deliver(im.buildIQ(mid), function(data) {});
			}, 180000);
		};
		ws.onmessage = function(event) { // 接收到消息
			var messgae = event.data; // JSON格式的消息内容
			console.log("接收到消息 : " + messgae);
			switch (messgae.type) {
				case 1:
					{ // IQ
						if (messgae.status == 2) { // 离线
							deliver(im.buildPresence(mid), function(data) {});
						}
						break;
					}
				case 2:
					{ // Message
						var fromMid = messgae.mid,
							party_id = messgae.party_id,
							nick = messgae.nick,
							avatar = messgae.avatar,
							toMid = messgae.tid,
							msgBody = messgae.body,
							gmt_created = new Date(messgae.gmt_created),
							status = messgae.status;

						messageCallback(messgae);
						break;
					}
				case 3:
					{ // Presence
						if (messgae.status == 1) { // 成功出席
							//
						} else if (messgae.status == 2) { // 出席失败
							deliver(im.buildPresence(mid), function(data) {});
						}
						break;
					}
				case 4:
					{ // Register
						break;
					}
				case 5:
					{ // Acknowledge
						if (messgae.status == 1) {
							plus.nativeUI.toast("发送成功");
						}
						break;
					}
				case 6:
					{ // Revise
						break;
					}
			}
		};
	};

	im.deliver = function(message, callback) {
		if (!window.WebSocket) {
			return;
		}
		callback = callback || $.noop;
		if (ws.readyState == WebSocket.OPEN) {
			ws.send(message);
			callback({
				success: true,
				msg: "消息发送成功"
			});
		} else {
			callback({
				success: false,
				msg: "网络无法连接"
			});
		}
	};

	im.buildIQ = function(mid) {
		return "{\"type\":1,\"mid\":" + mid + "}";
	};

	im.buildMessage = function(mid, tid, body) {
		return "{\"type\":2,\"mid\":" + mid + ",\"tid\":" + tid + ",\"body\":\"" + body + "\",\"gmt_created\":" + new Date().getTime() + "}";
	};

	im.buildPresence = function(mid) {
		return "{\"type\":3,\"mid\":" + mid + ",\"passwd\":\"123456\"}";
	};




	/**
	 * format="yyyy-MM-dd hh:mm:ss";
	 */
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









}(mui, window.im = {}));