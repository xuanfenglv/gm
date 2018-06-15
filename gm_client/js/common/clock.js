$(() => {
	drowClock();
	$(".hour").on("mousewheel DOMMouseScroll", function(e) {
		wheelScroll(e, 23, $(this));
	});
	$(".min").on("mousewheel DOMMouseScroll", function(e) {
		wheelScroll(e, 59, $(this));
	});
	$(".second").on("mousewheel DOMMouseScroll", function(e) {
		wheelScroll(e, 59, $(this));
	});
});

function drowClock() {
	let clock = '<input class="hour" value="00" readonly="readonly" /><div class="timeTag">:</div>' +
		'<input class="min" value="00" readonly="readonly" /><div class="timeTag">:</div>' +
		'<input class="second" value="00" readonly="readonly" />';
	$(".clock").html(clock);
}

function wheelScroll(e, max, source) {
	var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
		(e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox

	var val = parseInt(source.val());
	if(delta > 0) {
		source.val(scrollUp(max, val));
	} else if(delta < 0) {
		source.val(scrollDown(max, val));
	}
}

function scrollUp(max, val) {
	if(val >= max) {
		val = 00;
	} else {
		val++;
	}
	if(val < 10) {
		val = '0' + val;
	}
	return val;
}

function scrollDown(max, val) {
	if(val <= 0) {
		val = max;
	} else {
		val--;
	}
	if(val < 10) {
		val = '0' + val;
	}
	return val;
}
/**
 * 页面中只有一个clock时
 */
function getClockTime() {
	return $(".hour").val() + ":" + $(".min").val() + ":" + $(".second").val();
}
/**
 * 页面中有多个clock时,需要给。clock增加一个类 
 */
function getClockTimeByClass(clazz) {
	return $("." + clazz + " .hour").val() + ":" + $("." + clazz + " .min").val() + ":" + $("." + clazz + " .second").val();
}

function setYMD_HMS(time, ymdId, hmsClass) {
	let beginTimeStr = timestampToTime(time);
	let arr = beginTimeStr.split(' ');
	let YMD = arr[0];
	let HMS = arr[1];
	$("#" + ymdId).val(YMD);
	setClockTimeByStr(hmsClass, HMS);
}
/**
 * 
 * @param {Object} clazz
 * @param {Object} h_m_s 23:30:56
 */
function setClockTimeByStr(clazz, h_m_s) {
	let arr = h_m_s.split(':');
	setClockTimeByHMS(clazz, arr[0], arr[1], arr[2]);
}

function setClockTimeByHMS(clazz, hour, min, second) {
	if(hour >= 24 || min >= 60 || second >= 60 || hour < 0 || min < 0 || second < 0) {
		throw "时间范围错误";
	}
	var showHour = getTimeNum(hour);
	var showMin = getTimeNum(min);
	var showSecond = getTimeNum(second);
	$("." + clazz + " .hour").val(showHour)
	$("." + clazz + " .min").val(showMin)
	$("." + clazz + " .second").val(showSecond);
}
/**
 * 不足2位补零
 */
function getTimeNum(num) {
	var tem = parseInt(num);
	if(0 <= tem && tem < 10) {
		return '0' + tem;
	}
	return tem;
}
function getDetailTime(ymdId, hmsClass) {
	let YMD = $("#"+ymdId).val();
	if(YMD == '') {
		return 0;
	}
	let detail = YMD + ' ' + getClockTimeByClass(hmsClass);
	return getTimestamp(detail);
}