$(function() {
	laydate({
		elem: '#fengDate',
	});
	$("#userSearch").click(function() {
		let data = new Object();
		data.msgId = 107;
		data.name = $("#searchName").val();
		data.minPower = $("#minPower").val();
		data.maxPower = $("#maxPower").val();

		getService(data, getUserListCallBack);
	});
	$("#confirmFenghao").click(function() {
		//		closeButton("feng","asd");
		let data = new Object();
		data.msgId = 104;
		data.name = $("#fenghaoName").html();

		var fengDate = $("#fengDate").val();
		if(!fengDate) {
			tip("未填写年月日");
			return;
		}
		fengDate += ' '+getClockTime();
		data.deadline = getTimestamp(fengDate);
		if(!checkIsFeng(data.deadline)) {
			tip("封号时间小于当前时间");
			return;
		}

		postService(data, function() {
			success("封号成功");
			// 打开按钮
			openButton("feng", data.name);
			// 更新封号时间
			$("span[sign=" + data.name + "]").html(fengDate);
		});
	});
	// 动态生成的元素要用事件代理
	$("#show_data").on("click", "[feng]", function() {
		// 操作对象
		var operateObj = $(this).attr("feng");
		if($(this).hasClass("open-inner")) {
			// 确认解封
			layer.confirm('确认解封？', {
				btn: ['确认'] //按钮
			}, function() {
				// 解封请求
				var data = {};
				data.msgId = 108;
				data.name = operateObj;
				postService(data, function() {
					success("解封成功");
					// 打开按钮
					closeButton("feng", data.name);
					// 更新封号时间
					$("span[sign=" + data.name + "]").html('x');
				});
			});
		} else {
			// 确认封号
			$(".fenghao").fadeIn(300);
			$("#fenghaoName").html(operateObj);
		}
	});
	$("#show_data").on("change", "[power]", function() {
		var operateObj = $(this).attr("power");
		var data = {};
		data.msgId = 103;
		data.name = operateObj;
		data.power = $(this).find("option:selected").text();
		postService(data, function() {
			success("修改权限成功");
		})
	});
});

function getUserListCallBack(data) {
	showData.clear();
	if(data) {
		$.each(data.userList, function(idx, user) {
			let reset = '<button class="btn btn-danger radius-rounded" onclick="resetPwd(\'' + user.name + '\')">重置密码</button>';
			let isFeng = checkIsFeng(user.deadline);
			let power, button, fengDate;
			if(isFeng) {
				button = getOnOff(isFeng, '点击解封', 'feng', user.name);
				fengDate = timestampToTime(user.deadline);
				fengDate = '<span sign="' + user.name + '">' + fengDate + '</span>';
			} else {
				button = getOnOff(isFeng, '点击封号', 'feng', user.name);
				fengDate = '<span sign="' + user.name + '">x</span>';
			}
			power = getPowerSel(user.name, user.power);
			showData.append(user.name, power, reset, button, fengDate);
		});
	}
}

function checkIsFeng(deadline) {
	if(new Date().getTime() < deadline) {
		return true;
	}
	return false;
}

function getOnOff(open, title, signName, signValue) {
	let button = '<div title="' + title + '" class="feng switch-outer ' + (open ? 'open-outer' : '') + '"><div ' + signName + '="' + signValue + '" class="switch-inner ' + (open ? 'open-inner' : '') + '"></div></div>';
	return button;
}

function getPowerSel(name, power) {
	let select = '<select power="'+name+'" style="width:auto;">';
	for(let i = 1; i < 10; i++) {
		if(power == i) {
			select += '<option selected="selected">' + i + '</option>';
		} else {
			select += '<option>' + i + '</option>';
		}
	}
	select += '</select>';
	return select;
}

// 102
function resetPwd(name) {
	let data = new Object();
	data.msgId = 102;
	data.name = name;

	getService(data, function() {
		success("密码重置成功");
	});
}
//