function searchRecord(type, pageNum) {
	let msg = new RoleInfoRecord(type, pageNum);

	getService(msg, (data) => drawView(data, type, msg, drawShowTable, drawExcelTable));
}

function drawShowTable(item) {
	showData.append(item.uuid, item.name, item.passportId, item.regionId, item.createDate, item.deleteDate, item.deleted);
}

function drawExcelTable(item) {
	excelData.append(item.uuid, item.name, item.passportId, item.regionId, item.createDate, item.deleteDate, item.deleted);
}
class RoleInfoRecord extends GameRecordMessage {
	constructor( type, pageNum) {
		super(302, type, pageNum);
		this.uuid = $("#roleId").val();
		this.passportId = $("#accountId").val();
		this.name = $("#name").val();
	}
	
	/**
	 * 需要校验参数的子类选择实现
	 */
	checkProperty() {
		return true;
	}

}