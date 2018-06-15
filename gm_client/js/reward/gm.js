$(() => {
	initCalendar("deadline","beginTime2","endTime2");
	
});

function submit() {
	let msg = new Message(411);
	msg.serverId = getNowServerId();
	msg.rewardId = $(".drawer-box [name=rewardId]").val();
	msg.name = $(".drawer-box [name=name]").val();
	msg.account = $(".drawer-box [name=account]").val();
	msg.reason = $(".drawer-box [name=reason]").val();
	msg.desc = $(".drawer-box [name=desc]").val();
	msg.deadline = getDetailTime("deadline","deadline");
	msg.content = getResourceInfo();
	let rs = checkReward(msg);
	if(!rs) return;
	postService(msg,()=>{success("发送奖励成功")})

}
function checkReward(msg) {
	if(msg.msgId!=411) {
		tip("消息号错误");
	} else if(!msg.rewardId||!msg.name||!msg.account||!msg.desc) {
		tip("缺少必要的参数");
	} else if(msg.deadline==0) {
		tip("截止时间不能为空");
	} else if(msg.deadline<=(new Date().getTime())) {
		tip("截止时间不能是过去时");
	} else if(msg.content.length==0) {
		tip("奖励不能为空");
	} else {
		return true;
	}
	return false;
}
function searchRecord(type,pageNum) {
	console.log("查询第 " + pageNum + " 页");
	let msg = new RecordMessage(412,type,pageNum);
	msg.rewardId = $("#rewardId").val();
	if(!msg.rewardId) {
		
		msg.name = $("#name").val();
		msg.reason = $("#condition [name=reason]").val();
		let begin = $("#beginTime2").val();
		let end = $("#endTime2").val();
		if(begin) {
			msg.beginTime = getTimestamp(begin);
		} else {
			msg.beginTime = 0;
		}
		if(end) {
			msg.endTime = getTimestamp(end);
		} else {
			msg.endTime = 0;
		}

		let rs = checkSearchParams(msg);
		if(!rs) {
			return;
		}
	}
	getService(msg, (data) => {
		drownView(data,type,msg);
	});
}
function checkSearchParams(msg) {
	if(!(msg.type==1||msg.type==2)) {
		tip("错误的查询类型");
		return false;
	}
	if(msg.beginTime > msg.endTime) {
		tip("有效期填错了");
		return false;
	}
	return true;
}
function drownView(data,type,msg) {
	if(type==1) {
		showData.clear();
		$.each(data.recordList, function(index, item) {
			let deadline = timestampToTime(item.deadline);
			
			showData.append(item.id,item.rewardId,item.name,item.account,item.reason,item.desc,deadline,item.content,item.status,item.operator);
		});
		msg.updatePages(data.total);
	} else {
		excelData.clear();
		$.each(data.recordList, function(index, item) {
			let deadline = timestampToTime(item.deadline);
			excelData.append(item.id,item.rewardId,item.name,item.account,item.reason,item.desc,deadline,item.content,item.status,item.operator);
		});
		tableToExcel(excelData.id);
	}
	
}