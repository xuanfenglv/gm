$(() => {
	//	$("#update").click(update);
	$("#search").click(() => searchRecord(1, 1));
	$("#export").click(() => searchRecord(2, null));
	initPage();
	if(typeof(submit) == 'function') {
		$("#submit").click(submit);
	}
	$("#show_data").on("click", ".pause", function() {
		let id = parseInt($(this).parent().parent().attr("key"));
		let msg = genPauseMessage(id);
		postService(msg, () => {
			success("暂停成功");
			pauseToStart(id, pauseIndex, $(this));
		});
	});
	$("#show_data").on("click", ".start", function() {
		let id = parseInt($(this).parent().parent().attr("key"));
		let msg = genStartMessage(id);
		postService(msg, () => {
			success("开启成功");
			startToPause(id, pauseIndex, $(this));
		});
	});
	$("#show_data").on("click", ".edit", function() {
		let id = parseInt($(this).parent().parent().attr("key"));
		// 当前编辑的id
		editId = id;
		// 拉下来
		drawerDown();
		// 展示更新按钮
		showUpdateComponent();
		// 
		let item = recordMap.get(id);
		editRecord(item);
	});
	$("#show_data").on("click", ".del", function() {
		let id = parseInt($(this).parent().parent().attr("key"));
		let msg = genDelMessage(id);

		postService(msg, () => {
			success("删除成功");
			removeRecord(id);
		});
	});
	$("#update").click(() => {
		let msg = genUpdateMessage();
		postServiceWithCheck(msg, () => updateCallBack(msg));
	});
	$("#cancel").click(() => {
		closeUpdateComponent();
		let type = typeof(recoverToSubmit);
		if(type == 'function') {
			recoverToSubmit();
		}
	});
});
// 当前编辑的记录id
let editId;
let pauseIndex;
let recordMap = new Map();

let showData = {
	tableId: '#show_data',
	clear: function() {
		$(this.tableId + " tr:not(:first)").html("");
	},
	appendToTr: function(tr, ...args) {
		$.each(args, function(idx, item) {
			tr.append($('<td/>').html(item));
		});
	},
	append: function() {
		let tr = $('<tr class="cen trheight"/>');
		$.each(arguments, function(idx, item) {
			tr.append($('<td/>').html(item));
		});
		$(this.tableId).append(tr);
	},
	appendWithKey: function(key, ...args) {
		let tr = $('<tr key="' + key + '" class="cen trheight"/>');
		$.each(args, function(idx, item) {
			tr.append($('<td/>').html(item));
		});
		$(showData.tableId).append(tr);
	},
	tr: (key) => {
		let tr;
		if(key) {
			tr = $('<tr key="' + key + '" class="cen trheight"/>');
		} else {
			tr = $('<tr class="cen trheight"/>');
		}
		return tr;
	},
	td: (value, key) => {
		let td;
		if(key) {
			td = $('<td key="' + key + '"/>');
		} else {
			td = $('<td/>');
		}
		td.html(value);

		return td;
	},
	appendTr: (tr) => {
		$(showData.tableId).append(tr);
	}
}
let excelData = {
	tableId: '#excel_data',
	id: 'excel_data',
	clear: function() {
		$(excelData.tableId + " tr:not(:first)").remove();
	},
	append: function() {
		let tr = $('<tr/>');
		$.each(arguments, function(idx, item) {
			tr.append($('<td/>').html(item));
		});
		$(excelData.tableId).append(tr);
	}
}

function drawView(data, type, msg) {
	if(type == 1) {
		recordMap.clear();
		showData.clear();
		$.each(data.recordList, function(index, item) {
			drawShowTable(item);
		});
		msg.updatePages(data.total);
	} else {
		excelData.clear();
		$.each(data.recordList, function(index, item) {
			drawExcelTable(item);
		});
		tableToExcel(excelData.id);
	}

}

function initPage() {
	$(".pagination").createPage({
		pageCount: 0,
		current: 0,
		backFn: (pageNum) => searchRecord(1, pageNum)
	});
}
/**
 * 打开更新按钮组件
 */
function showUpdateComponent() {
	$("#submit").css("display", "none");

	$("#update").css("display", "");
	$("#cancel").css("display", "");

}
/**
 * 关闭更新按钮组件
 */
function closeUpdateComponent() {
	$("#submit").css("display", "");

	$("#update").css("display", "none");
	$("#cancel").css("display", "none");
	success("切换到添加模式");
}

function pauseToStart(id, index, self) {
	$("tr[key=" + id + "] td:eq(" + index + ")").html("是");
	self.attr("title", "开启");
	self.removeClass("pause");
	self.addClass("start");
	self.html("开启");
}

function startToPause(id, index, self) {
	$("tr[key=" + id + "] td:eq(" + index + ")").html("否");
	self.attr("title", "关闭");
	self.removeClass("start");
	self.addClass("pause");
	self.html("暂停");
}

function removeRecord(id) {
	$("tr[key=" + id + "]").hide(800, function() {
		$(this).remove()
	});
	recordMap.delete(id);
}