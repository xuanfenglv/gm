$(function() {
	initCalendar('beginTime1', 'endTime1', 'beginTime2', 'endTime2');
	pauseIndex = 11;

	$("#show_data").on('click', '.look', function() {
		let tr = $(this).parent().parent();
		let codeId = tr.attr("key");
		let msg = new Message(405);
		msg.codeId = codeId;
		postService(msg, (data) => {
			let codes = '';
			data.codeList.forEach((item) => {
				codes += item + '<br/>';
			});
			layer.alert(codes, {
				"title": "兑换码"
			});
		});

	});

});
class AddRedeemMessage extends AimAtServerAndChannelMessage {
	constructor() {
		super(401);
	}
	initProperty() {
		super.initProperty();
		this.codeId = $("#codeId").val();
		this.num = $("#num").val();
		this.name = $("#name").val();
		this.desc = $("#desc").val();
		this.times = $("#times").val();
		this.content = getResourceInfo();
		this.pause = $("#pause").is(":checked") ? 1 : 0;
	}
	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		if(!checkRedeemCodeParms(this)) {
			return false;
		}

		return true;
	}
}
class UpdateRedeemMessage extends AimAtServerAndChannelMessage {
	constructor() {
		super(404);
	}
	initProperty() {
		super.initProperty();
		this.codeId = $("#codeId").val();
		//		this.num = $("#num").val();
		this.name = $("#name").val();
		this.desc = $("#desc").val();
		this.times = $("#times").val();
		this.content = getResourceInfo();
		this.pause = $("#pause").is(":checked") ? 1 : 0;
	}
	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		if(!this.codeId || !this.name || !this.desc) {
			tip('缺少必要的参数');
			return false;
		}
		if(this.name.length > 15) {
			tip("礼包名长度不超过15");
			return false;
		}
		if(this.desc.length > 20) {
			tip("描述长度不超过15");
			return false;
		}
		if(this.content.length == 0) {
			tip("奖励不能为空");
			return false;
		}

		return true;
	}
}

function submit() {
	let msg = new AddRedeemMessage();
	postServiceWithCheck(msg, function(data) {
		console.log(data.redeemCodeList);
		success("添加兑换码成功");
	})

}

function checkRedeemCodeParms(msg) {
	if(!msg.codeId || !msg.num || !msg.name || !msg.desc || !msg.times) {
		tip('缺少必要的参数');
		return false;
	}

	if(msg.num > 10000) {
		tip("生成兑换码数量不超过1万");
		return false;
	}
	if(msg.name.length > 15) {
		tip("礼包名长度不超过15");
		return false;
	}
	if(desc.length > 20) {
		tip("描述长度不超过15");
		return false;
	}

	if(msg.times > 10000) {
		tip("兑换码领取次数不超过1万");
		return false;
	}
	if(msg.content.length == 0) {
		tip("奖励不能为空");
		return false;
	}
	return true;
}
class RedeemRecordMessage extends IntervalRecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
	}
	initProperty() {
		this.codeId = $("#condition #codeId").val();
		if(!this.codeId) {
			this.name = $("#condition #name").val();
		}
	}
	checkProperty() {
		if(!super.checkProperty()) {
			return false;
		}
		return true;
	}
}

function searchRecord(type, pageNum) {
	console.log("查询第 " + pageNum + " 页");
	let msg = new RedeemRecordMessage(402, type, pageNum);

	let rs = msg.checkProperty(msg);
	if(!rs) return;
	getService(msg, (data) => {
		drawView(data, type, msg);
	});
}

function changeShowTable(item, tr) {
	recordMap.set(item.codeId, item);
	let beginTime = timestampToTime(item.beginTime);
	let endTime = timestampToTime(item.endTime);
	let isPause = (item.pause == 1);
	let pause = isPause ? "是" : "否";
	let operate = '';
	if(isPause) {
		operate = o_start + o_edit + o_del + o_look;
	} else {
		operate = o_pause + o_edit + o_del + o_look;
	}
	if(tr) {
		showData.appendToTr(tr, item.codeId, item.num, item.name, item.desc, beginTime, endTime, item.servers, item.channels, item.times, item.content, pause, operate);
	} else {
		showData.appendWithKey(item.codeId, item.id, item.codeId, item.num, item.name, item.desc, beginTime, endTime, item.servers, item.channels, item.times, item.content, pause, operate);
	}
}

function drawShowTable(item) {
	recordMap.set(item.codeId, item);
	changeShowTable(item)
}

function drawExcelTable(item) {
	let beginTime = timestampToTime(item.beginTime);
	let endTime = timestampToTime(item.endTime);
	let pause = (item.pause == 1) ? "是" : "否";
	excelData.append(item.id, item.codeId, item.num, item.name, item.desc, beginTime, endTime, item.servers, item.channels, item.times, item.content, pause);
}

function editRecord(item) {
	updateDisable();
	$("#codeId").val(item.codeId);
	$("#num").val(item.num);
	$("#name").val(item.name);
	$("#desc").val(item.desc);
	$("#times").val(item.times);
	clearRewards();
	addRewards(item.content);

	this.content = getResourceInfo();
	setYMD_HMS(item.beginTime, "beginTime1", "begin");
	setYMD_HMS(item.endTime, "endTime1", "end");
	setSelectedBox("server", item.servers);
	setSelectedBox("channel", item.channels);
	let check = item.pause == 1 ? true : false;
	$('#pause').prop("checked", check);

	success("原数据已被自动添加到表单");
}

function updateDisable() {
	$("#codeId").attr("disabled", "disabled");
	$("#num").attr("disabled", "disabled");
}

function recoverToSubmit() {
	$("#codeId").removeAttr("disabled");
	$("#num").removeAttr("disabled");
}

function genPauseMessage(id) {
	return new OpearteRedeemMessage(1, id);
}

function genStartMessage(id) {
	return new OpearteRedeemMessage(2, id);
}

function genDelMessage(id) {
	return new OpearteRedeemMessage(3, id);
}

function genUpdateMessage() {
	return new UpdateRedeemMessage();
}
function updateCallBack(msg) {
	success("修改兑换码信息成功");
	let tr = $("tr[key=" + editId + "]");
	$("tr[key=" + editId + "] td:gt(0)").remove()

	msg.num = $("#num").val();
	msg.servers = JSON.stringify(msg.servers);
	msg.channels = JSON.stringify(msg.channels);
	msg.content = JSON.stringify(msg.content);
	changeShowTable(msg, tr);
	
}

class OpearteRedeemMessage extends Message {
	constructor(operate, id) {
		super(403);
		this.operate = operate;
		this.codeId = id;
	}
}