function searchRecord(type, pageNum) {
	let msg = new TuiguangRecord(type, pageNum);

	getService(msg, (data) => drawView(data, type, msg, drawShowTable, drawExcelTable));
}

function drawShowTable(item) {
	showData.append(item.userid, item.qudaotype, item.sdktype, item.sdkorderid, item.ordercreatetime, item.orderid, item.ordernumber, item.fahuotime, item.channelId);
}

function drawExcelTable(item) {
	excelData.append(item.userid, item.qudaotype, item.sdktype, item.sdkorderid, item.ordercreatetime, item.orderid, item.ordernumber, item.fahuotime, item.channelId);
}
class TuiguangRecord extends GameRecordMessage {
	constructor( type, pageNum) {
		super(302, type, pageNum);
		this.search = $("#search").val();
		let beginTime = $("#beginTime").val();
		if(beginTime) {
			this.beginTime = getTime(beginTime);
		} else {
			beginTime = getNYR(new Date(),"-");
			this.beginTime = getTime(beginTime);
		}
		
		let endTime = $("#endTime").val();
		if(endTime) {
			this.endTime = getEndTime(endTime);
		} else {
			endTime = getNYR(new Date(),"-");
			this.endTime = getEndTime(endTime);
		}
		if(this.search=="2") {
			var hehe = [41744000,43267002,42783002,43164003,42135000,43340000,40731000,40734004];
			this.channels = JSON.stringify(hehe);
		}
	}
	
	/**
	 * 需要校验参数的子类选择实现
	 */
	checkProperty() {
		return true;
	}

}