$(function() {
	'use strict';
	//左侧导航菜单效果
	//因为菜单栏、header、footer都是根据模板页动态渲染的，所以使用事件代理
	$('.side-nav').on("click", ".side-menu li", function() {
		if($(this).hasClass('open')) {
			$(this).removeClass('open');
		} else {
			$(this).addClass('open');
			$(this).parents('.side-menu').find('.InitialPage').removeClass('active');
			$(this).siblings().removeClass('open');
		}
	});
	//
	//Tab选项卡.
	$('.tab-nav li').click(function() {
		var liIndex = $('.tab-nav li').index(this);
		$(this).addClass('active').siblings().removeClass('active');
		$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
	});
	//Button下拉菜单
	$('.btn-drop-group .btn').click(function(e) {
		$(this).siblings('.drop-list').show();
		$(this).parent().siblings().find('.drop-list').hide();
		$(document).one('click', function() {
			$('.btn-drop-group .drop-list').hide();
		});
		e.stopPropagation();
	});
	//点击list将当前值复制于button
	$('.btn-drop-group .drop-list li').click(function() {
		$(this).parent().parent().hide();
	});

	//弹出层基础效果（确认/取消/关闭）
	$('.JyesBtn').click(function() {
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display', 'block')) {
			$('.mask').hide();
		}
	});
	$('.JnoBtn,.JclosePanel').click(function() {
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display', 'block')) {
			$('.mask').hide();
		}
	});
	//基础弹出窗
	$('.JopenPanel').click(function() {
		$('.dialog').show();
		$('.dialog').css('box-shadow', '0 0 30px #d2d2d2');
	});
	//带遮罩
	$('.JopenMaskPanel').click(function() {
		$('.dialog,.mask').show();
		$('.dialog').css('box-shadow', 'none');
	});
	$('.mask').click(function() {
		$(this).hide();
		$('.dialog').hide();
	});
	//自动关闭消息窗口
	$('.Jmsg').click(function() {
		$('dialog').show().delay(5000).hide(0);
	});
	//安全退出
	//	$('#JsSignOut').click(function(){
	//		layer.confirm('确定登出管理中心？', {
	//		  title:'系统提示',
	//		  btn: ['确定','取消']
	//		}, function(){
	//		  location.href = 'login.html';
	//		});
	//	});
});

//捐赠
function reciprocate() {
	layer.open({
		type: 1,
		skin: 'layui-layer-demo',
		closeBtn: 1,
		anim: 2,
		shadeClose: false,
		title: '喝杯咖啡O(∩_∩)O',
		content: '<div class="pl-20 pr-20">' +
			'<table class="table table-bordered table-striped mt-10">' +
			'<tr>' +
			'<td><img src="images/wechat_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>' +
			'<td><img src="images/alipay_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>' +
			'</tr>' +
			'<tr class="cen">' +
			'<td class="text-primary">微信打赏</td>' +
			'<td class="text-primary">支付宝打赏</td>' +
			'</tr>' +
			'</table>' +
			'</div>'
	});
}
// 非原框架的方法
$(document).ready(function() {
	// 改动的方法
	// 左侧菜单收缩
	$('.content-wrap').on("click", ".top-hd .hd-lt", function() {
		$('.side-nav').toggleClass('hide');
		$('.top-hd,.main-cont,.btm-ft').toggleClass('switchMenu');
		$('.top-hd .hd-lt a').toggleClass('icon-exchange');
		localStorage.setItem('setLayOut1', 'hide');
		localStorage.setItem('setLayOut2', 'switchMenu');
		localStorage.setItem('setLayOut3', 'icon-exchange');
		if(!$('.side-nav').hasClass('hide')) {
			localStorage.removeItem('setLayOut1');
			localStorage.removeItem('setLayOut2');
			localStorage.removeItem('setLayOut3');
		}
	});
	//安全退出
	$('.content-wrap').on("click", "#JsSignOut", function() {
		layer.confirm('确定登出管理中心？', {
			title: '系统提示',
			btn: ['确定', '取消']
		}, function() {
			let url = server.url + '/user/logout';
			let data = {};
			data.name = storage.getItem("adminName");
			post(url, data, function() {
				location.href = loginPage;
			});
		});
	});
	// 增加的方法
	$('.top-hd').on("change", "#serverList", function() {
		cache.set("selectedServer", $(this).val());
		success("切换服务器");
	});
	//安全退出
	$('.top-hd').on("click", "#clearCache", function() {
		cache.clear();
		success("缓存已清空");
	});

	// 调配台的抽拉
	$(".drawer-handle").click(function() {
		$(".drawer-box").slideToggle(300);
	});
});
// 错误提示
function tip(msg) {
	layer.msg(msg, {
		icon: 2,
		time: 1000
	});
}
// 成功提示
function success(msg) {
	layer.msg(msg, {
		icon: 1,
		time: 1000
	});
}
function drawerDown() {
	$(".drawer-box").slideDown(300);
}
function timestampToTime(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear();
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
	D = date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate();
	h = date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours();
	m = date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes();
	s = date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds();
	return Y + '-' + M + '-' + D + ' ' + h + ':' + m + ':' + s;
}
function getNYR(date,separate) {
	Y = date.getFullYear();
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
	D = date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate();
	return Y + separate + M + separate + D;
}
function todayNYR(separate) {
	return getNYR(new Date(),separate);
}
/**
 * 支持2014-04-23 18:55:49:123、2014-04-23 18:55:49、2014-04-23 18:55、2014-04-23
 * @param {Object} date
 */
function getTimestamp(date) {
	return new Date(date).getTime();
}
/**
 * @description 把yyyy-MM-dd转化为当天开始的时间戳
 * @param {String} date
 */
function getTime(date) {
	if(date) {
		return getTimestamp(date + ' 00:00:00');
	} else {
		return 0;
	}
	
}
/**
 * @description 把yyyy-MM-dd转化为当天结束的时间戳
 * @param {String} date
 */
function getEndTime(date) {
	if(date) {
		return getTimestamp(date + ' 23:59:59:999');
	} else {
		return 0;
	}
	
}

function openButton(signName, signValue) {
	$("[" + signName + "=" + signValue + "]").addClass("open-inner");
	$("[" + signName + "=" + signValue + "]").parent().addClass("open-outer");
}

function closeButton(signName, signValue) {
	$("[" + signName + "=" + signValue + "]").removeClass("open-inner")
	$("[" + signName + "=" + signValue + "]").parent().removeClass("open-outer");
}

function initCalendar() {
	for(var id of arguments) {
		laydate({
			elem: '#'+id
		});
	}
}
function getNowServerId() {
	return parseInt($("#serverList").val());
}
