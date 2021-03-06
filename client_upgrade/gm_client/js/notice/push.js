$(()=> {
	initCalendar('beginTime1', 'beginTime2', 'endTime2');
	pauseIndex = 5;
})
function submit() {
	let msg = new AddNoticeMessage(507);

	postServiceWithCheck(msg, () => success("添加推送成功"))
}
function searchRecord(type, pageNum) {
	console.log("查询第 " + pageNum + " 页");
	let msg = new SearchNoticeMessage(508, type, pageNum);

	getService(msg, (data) => {
		drawView(data, type, msg, drawShowTable, drawExcelTable);
	});
}
function changeShowTable(item, tr) {
	recordMap.set(item.id, item);
	let beginTime = timestampToTime(item.beginTime);
	let isPause = (item.pause == 1);
	let pause = isPause ? "是" : "否";
	let operate = '';
	if(isPause) {
		operate = o_start + o_edit + o_del;
	} else {
		operate = o_pause + o_edit + o_del;
	}
	if(tr) {
		showData.appendToTr(tr, item.id, item.servers, item.channels, beginTime, item.content, pause, operate);
	} else {
		showData.appendWithKey(item.id, item.id, item.servers, item.channels, beginTime, item.content, pause, operate);
	}
}

function drawShowTable(item) {
	recordMap.set(item.id, item);
	changeShowTable(item)
}

function drawExcelTable(item) {
	let beginTime = timestampToTime(item.beginTime);
	let pause = (item.pause == 1) ? "是" : "否";
	excelData.append(item.id, item.servers, item.channels, beginTime, item.content, pause);
}
/*begin 提供四个消息（处理过程使用通用魔板）*/
function genPauseMessage(id) {
	return new OpearteNoticeMessage(1, 3, id);
}

function genStartMessage(id) {
	return new OpearteNoticeMessage(2, 3, id);
}

function genDelMessage(id) {
	return new OpearteNoticeMessage(3, 3, id);
}

function genUpdateMessage() {
	// 共用下添加消息，额外加一个id
	let msg = new AddNoticeMessage(509);
	msg.id = editId;
	return msg;
}
/*end 提供四个消息（处理过程使用通用魔板）*/

/*begin 编辑前后调用的方法*/
function editRecord(item) {
	console.log(JSON.stringify(item));
	setYMD_HMS(item.beginTime, "beginTime1", "begin");
	setSelectedBox("server", item.servers);
	setSelectedBox("channel", item.channels);
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


/*########class###############*/
