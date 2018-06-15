$(() => {
	$("#search").click(() => searchRecord(1,1));
	$("#export").click(() => searchRecord(2,null));
	initCalendar('beginTime','endTime');
});

function searchLog(pageNum, type) {
	let msg = new GameLogMessage(603,type, pageNum);

	msg.charId = $("#charId").val();
	msg.coinType = $("#coinType").val();
	msg.reason = $("#reason").val();
	
	getService(msg, (data) => {
		drownView(data,type,msg);
	});

}
function drownView(data,type,msg) {
	if(type==1) {
		showData.clear();
		$.each(data.recordList, function(index, item) {
			showData.append(item.id,item.regionId,item.answer,item.lastLoginDate,item.lastLoginIp,item.name,item.props,item.question,item.role,item.state,item.todayOnlineTime,item.userSrc);
		});
		msg.updatePages(data.total);
	} else {
		excelData.clear();
		$.each(data.recordList, function(index, item) {
			showData.append(item.id,item.regionId,item.answer,item.lastLoginDate,item.lastLoginIp,item.name,item.props,item.question,item.role,item.state,item.todayOnlineTime,item.userSrc);
		});
		tableToExcel(excelData.id);
	}
	
}