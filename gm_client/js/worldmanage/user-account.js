$(() => {
	$('#search').click(() => searchRecord(1, 1));
	$('#export').click(() => searchRecord(2, null));
	initPage();
	initCalendar('loginBeginTime', 'loginEndTime', 'logoutBeginTime', 'logoutEndTime');
});

function searchRecord(type, pageNum) {
	let msg = new UserInfoRecord(301, type, pageNum);

	getService(msg, (data) => drawView(data, type, msg, drawShowTable, drawExcelTable));
}

function drawShowTable(item) {
	showData.append(item.id, item.regionId, item.answer, item.lastLoginDate, item.lastLoginIp, item.lastLogoutTime, item.name, item.props, item.question, item.role, item.state, item.todayOnlineTime, item.userSrc);
}

function drawExcelTable(item) {
	excelData.append(item.id, item.regionId, item.answer, item.lastLoginDate, item.lastLoginIp, item.lastLogoutTime, item.name, item.props, item.question, item.role, item.state, item.todayOnlineTime, item.userSrc);
}
class UserInfoRecord extends GameRecordMessage {
	constructor(msgId, type, pageNum) {
		super(msgId, type, pageNum);
	}
	/**
	 * 子类必须实现
	 */
	initProperty() {
		this.id = $("#accountId").val();
		this.name = $("#name").val();
		this.lastLoginIp = $("#loginIp").val();
		this.loginBeginTime = getTime($("#loginBeginTime").val());
		this.loginEndTime = getEndTime($("#loginEndTime").val());
		this.logoutBeginTime = getTime($("#logoutBeginTime").val());
		this.logoutEndTime = getEndTime($("#logoutEndTime").val());
		this.onlineTimeBeginTime = $("#onlineTimeBeginTime").val();
		this.onlineTimeEndTime = $("#onlineTimeEndTime").val();
		this.role = $("#role").val();
		this.state = $("#state").val();
		this.regionId = $("#regionId").val();
	}
	/**
	 * 需要校验参数的子类选择实现
	 */
	checkProperty() {
	
		return true;
	}

}