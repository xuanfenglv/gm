let cache = {
	cachable: cachable,
	set: function(key, value) {
		if(cachable) {
			sessionStorage.setItem(key, value);
		}
	},
	get: function(key) {
		if(cachable) {
			return sessionStorage.getItem(key);
		}
		return null;
	},
	remove: function(key) {
		sessionStorage.removeItem(key);
	},
	clear: function() {
		sessionStorage.clear();
	}
}
/**
 * 消息基类
 */
class Message {
	constructor(msgId) {
		this.msgId = msgId;
		this.initProperty();
	}
	/**
	 * 子类必须实现
	 */
	initProperty() {

	}
	/**
	 * 需要校验参数的子类选择实现
	 */
	checkProperty() {
		return true;
	}
}
class OpearteNoticeMessage extends Message {
	constructor(operate, type, id) {
		super(520);
		this.operate = operate;
		this.type = type;
		this.id = id;
	}
}

/**
 * 精确到时分秒的消息(2种情况，（1）有开始有结束（2）有开始无结束)
 */
class DetailedTimeMessage extends Message {

	constructor(msgId) {
		super(msgId);
		this.initTime();
	}
	initTime() {
		let beginYMD = $("#beginTime1").val();
		let endYMD = $("#endTime1").val();
		let type = typeof(endYMD);
		let hasEndYMD = typeof(endYMD) != "undefined";

		if(beginYMD == '') {
			this.beginTime = 0;
			return;
		}
		if(hasEndYMD) {
			if(endYMD == '') {
				this.endTime = 0;
				return;
			} else {
				let endTime = endYMD + ' ' + getClockTimeByClass('end');
				this.endTime = getTimestamp(endTime);
			}
			
		}
		let beginTime = beginYMD + ' ' + getClockTimeByClass('begin');
		this.beginTime = getTimestamp(beginTime);
	}
	checkProperty() {
		if(this.beginTime == 0) {
			tip("时间未填写");
			return false;
		}
		let hasEndTime = typeof(this.endTime) != "undefined";
		if(hasEndTime) {
			if(this.endTime == 0) {
				tip("时间未填写");
				return false;
			}
			if(this.beginTime > this.endTime) {
				tip("开始时间大于结束时间");
				return false;
			}
		}

		return true;
	}
}
/**
 * 一般针对服务器和渠道的消息都需要详细的时间范围
 */
class AimAtServerAndChannelMessage extends DetailedTimeMessage {

	constructor(msgId) {
		super(msgId);
		this.servers = getSelectedBox('server');
		this.channels = getSelectedBox('channel');
	}

	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		if(this.servers.length == 0) {
			tip("未填写服务器");
			return false;
		} else if(this.channels.length == 0) {
			tip("未填写渠道");
			return false;
		}

		return true;
	}
}
class AddNoticeMessage extends AimAtServerAndChannelMessage {
	constructor(msgId) {
		super(msgId);
		this.content = $('#content').val();
		this.pause = $('#pause').is(":checked") ? 1 : 0;
	}
	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		if(!this.content) {
			tip("内容为空");	
			return false;
		}

		return true;
	}
}
class AddIntervalNoticeMessage extends AddNoticeMessage {
	constructor(msgId) {
		super(msgId);
	}
	
}
/**
 * 记录查询基类
 */
class RecordMessage extends Message {

	constructor(msgId, type, pageNum) {
		super(msgId);
		this.initPageInfo(type, pageNum);
	}
	/**
	 * @description 初始化发送的分页信息(分页查询的消息会用到)
	 * @param {Number} type
	 * @param {Number} pageNum
	 */
	initPageInfo(type, pageNum) {
		this.type = type;
		if(type == 1) {
			this.pageNum = pageNum;
			this.pageSize = parseInt($("#pageSize").val());
		}
	}

	/**
	 * @description 更新分页按钮
	 * @param {Number} total
	 */
	updatePages(total) {
		let pageCount = Math.ceil(total / this.pageSize);
		$(".pagination").createPage({
			pageCount: pageCount,
			current: this.pageNum
		});
	}
}
/**
 * 有效区间记录查询
 */
class IntervalRecordMessage extends RecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
		this.initTime();
	}

	initTime() {
		let beginTime = $("#beginTime2").val();
		let endTime = $("#endTime2").val();

		if(beginTime) {
			this.beginTime = getTime(beginTime);
		} else {
			this.beginTime = 0;
		}

		if(endTime) {
			this.endTime = getEndTime(endTime);
		} else {
			this.endTime = 0;
		}
	}
	checkProperty() {
		if(this.beginTime > this.endTime && this.endTime != 0) {
			tip("开始时间大于结束时间");
			return false;
		}
		return true;
	}
}
class SearchNoticeMessage extends IntervalRecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
		this.content = $("#content2").val();
	}

	
	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		if(this.content==''){
			return false;
		}
		return true;
	}
}
/**
 * GM中的记录查询 deprecated
 */
class GmRecordMessage extends RecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
	}
}
/**
 * 游戏中的记录（增加一个server初始化）
 */
class GameRecordMessage extends RecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
		this.initServer();
	}
	initServer() {
		this.serverId = $("#serverList").val();
	}
}
/**
 * 游戏日志（增加一个date初始化）
 */
class GameLogMessage extends GameRecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
		this.initTime();
	}
	initTime() {

		this.beginTime = '2018_04_27';
		this.endTime = '2018_04_27';
		//		this.beginTime = $("#beginTime").val();
		//		this.endTime = $("#endTime").val();

		if(this.beginTime == '') {
			this.beginTime = todayNYR('_');
		}
		if(this.endTime == '') {
			this.endTime = todayNYR('_');
		}
	}
}