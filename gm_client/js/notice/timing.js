$(() => {
	initCalendar('beginTime1', 'endTime1', 'beginTime2', 'endTime2');
	pauseIndex = 7;
});

class AddTimingNoticeMessage extends AddNoticeMessage {
	constructor(msgId) {
		super(msgId);
	}
	initProperty() {
		this.repeatInterval = $("#repeatInterval").val();

	}
	checkProperty() {
		let rs = super.checkProperty();
		if(!rs) {
			return false
		}
		if(this.repeatInterval == '') {
			tip("重复间隔不能为空");
			return false;
		}
		if(this.repeatInterval < 1) {
			tip("重复间隔为正整数");
			return false;
		}
		
		if(this.content == '') {
			tip("公告内容不能为空");
			return false;
		}
		return true;
	}
}

function submit() {
	let msg = new AddTimingNoticeMessage(501);
	let check = msg.checkProperty();
	if(!check) return;

	postService(msg, () => success("添加定时公告成功"))
}

function update() {
	let msg = new AddTimingNoticeMessage(503);
	msg.id = editId;

	postService(msg, () => {
		success("修改公告成功");
		// show_data 改变
		let tr = $("tr[key=" + editId + "]");
		tr.html('');
		msg.servers = JSON.stringify(msg.servers);
		msg.channels = JSON.stringify(msg.channels);
		changeShowTable(msg, tr);
	});
}

function searchRecord(type, pageNum) {
	console.log("查询第 " + pageNum + " 页");
	let msg = new SearchNoticeMessage(502, type, pageNum);

	getService(msg, (data) => {
		drawView(data, type, msg, drawShowTable, drawExcelTable);
	});
}

function changeShowTable(item, tr) {
	recordMap.set(item.id, item);
	let beginTime = timestampToTime(item.beginTime);
	let endTime = timestampToTime(item.endTime);
	let isPause = (item.pause == 1);
	let pause = isPause ? "是" : "否";
	let operate = '';
	if(isPause) {
		operate = o_start + o_edit + o_del;
	} else {
		operate = o_pause + o_edit + o_del;
	}
	if(tr) {
		showData.appendToTr(tr, item.id, item.servers, beginTime, endTime, item.repeatInterval, item.channels, item.content, pause, operate);
	} else {
		showData.appendWithKey(item.id, item.id, item.servers, beginTime, endTime, item.repeatInterval, item.channels, item.content, pause, operate);
	}
}

function drawShowTable(item) {
	recordMap.set(item.id, item);
	changeShowTable(item)
}

function drawExcelTable(item) {
	let beginTime = timestampToTime(item.beginTime);
	let endTime = timestampToTime(item.endTime);
	let pause = (item.pause == 1) ? "是" : "否";
	excelData.append(item.id, item.servers, beginTime, endTime, item.repeatInterval, item.channels, pause, item.content);
}
/*begin 提供四个消息（处理过程使用通用魔板）*/
function genPauseMessage(id) {
	return new OpearteNoticeMessage(1, 1, id);
}

function genStartMessage(id) {
	return new OpearteNoticeMessage(2, 1, id);
}

function genDelMessage(id) {
	return new OpearteNoticeMessage(3, 1, id);
}

function genUpdateMessage() {
	let msg = new AddTimingNoticeMessage(503);
	msg.id = editId;
	return msg;
}
/*end 提供四个消息（处理过程使用通用魔板）*/
/*begin 编辑前后调用的方法*/
function editRecord(item) {

	console.log(JSON.stringify(item));
	setYMD_HMS(item.beginTime, "beginTime1", "begin");
	setYMD_HMS(item.endTime, "endTime1", "end");
	$("#repeatInterval").val(item.repeatInterval);
	setSelectedBox("server", item.servers);
	setSelectedBox("channel", item.channels);
	//	setSelectedBox("server", JSON.parse(item.servers));
	//	setSelectedBox("channel", JSON.parse(item.channels));
	$('#content').val(item.content);
	let check = item.pause == 1 ? true : false;
	$('#pause').prop("checked", check);
	success("原数据已被自动添加到表单");
}

function updateCallBack(msg) {
	success("修改公告成功");
	// show_data 改变
	let tr = $("tr[key=" + editId + "]");
	tr.html('');
	msg.servers = JSON.stringify(msg.servers);
	msg.channels = JSON.stringify(msg.channels);
	changeShowTable(msg, tr);
}
/*begin 编辑前后调用的方法*/
