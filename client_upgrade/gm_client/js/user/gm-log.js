$(function() {
	loadOperations();
	initCalendar('beginTime','endTime')
});


function loadOperations() {
	let options = cache.get("operations");
	if(!options) {
		let data = new Message(109);
		getService(data, drawList);
	} else {
		fillSel(options);
	}
}

function fillSel(options) {
	$("#operation").html('<option value="-1">全部</option>');
	$("#operation").html($("#operation").html() + options);
}

function drawList(data) {
	let operations = JSON.parse(data.operations);
	let options = '';
	$.each(operations, function(index, operation) {
		options += '<option value="' + operation.msgId + '">' + operation.name + '</option>';
	});
	// 可以在这缓存options
	cache.set("operations", options);
	fillSel(options);
}

function searchRecord(type,pageNum) {
	let msg = new RecordMessage(110,type,pageNum);
	
	msg.operationId = $("#operation").val();
	msg.searchOperator = $("#operator").val();
	if($("#beginTime").val() == '') {
		msg.beginTime = 0;
	} else {
		msg.beginTime = getTime($("#beginTime").val());
	}
	if($("#endTime").val() == '') {
		msg.endTime = 0;
	} else {
		msg.endTime = getTime($("#endTime").val());
	}

	if(msg.beginTime > msg.endTime) {
		tip('开始时间不得小于结束时间');
		return;
	}
	getService(msg, function(data) {
		drownView(data, type, msg);
	})
}

function drownView(data, type, msg) {
	if(type == 1) {
		showData.clear();
		$.each(data.recordList, function(idx, log) {
			let time = timestampToTime(log.time);
			showData.append(log.id, log.msgId, log.name, log.operator, time, log.ret, log.request, log.response);
		});

		msg.updatePages(data.total);
	} else {
		excelData.clear();
		
		$.each(data.recordList, function(index, log) {
			let time = timestampToTime(log.time);
			excelData.append(log.id, log.msgId, log.name, log.operator, time, log.ret, log.request, log.response);
		});
		tableToExcel(excelData.id);
	}

}