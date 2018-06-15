$(function() {
	// 动态引入3个模板
	$(".side-nav").load('/gm_client/page/public/side-menu.html', ()=> {
		// 加载方法
		let lis = cache.get("menu");
		if(lis) {
			$(".side-menu ul").html(lis);
			hightlight();
		} else {
			$.getJSON("/gm_client/config/menu.json", (data)=> {
				//console.log(JSON.stringify(data));
				drowMenu(data);
				hightlight();
			});
		}
	});
	$(".top-hd").load('/gm_client/page/public/top-hd.html', ()=> {
		let admin = cache.get("admin");
		if(admin)
			$("#admin").html(admin);
		let server = cache.get("server");
		if(server) {
			$("#serverList").html(server);
			markServer();
		} else {
			$.getJSON("/gm_client/config/server.json", (data)=> {
				//console.log(JSON.stringify(data));
				drowServerList(data);
				markServer();
			});
		}

	});
	$(".btm-ft").load('/gm_client/page/public/btm-ft.html');

});

function drowMenu(data) {
	lis = '';
	$.each(data, function(index, module) {
		var li = '<li><dl><dt><i class="icon-columns"></i>' + module.name + '<i class="icon-angle-right"></i></dt>';
		$.each(module.functions, function(index, func) {
			li += '<dd><a href="/' + projectName + '/' + viewPkg + '/' + module.prefix + '/' + func.page + '">' + func.name + '</a></dd>';
		});
		li += '</dl></li>';
		lis += li;
	});
	$(".side-menu ul").html(lis);
	cache.set("menu", lis);
}

function hightlight() {
	let nowPath = window.location.pathname;
	//	console.log("indexPage:" + indexPage + ",nowPath:" + nowPath);
	if(indexPage == nowPath) {
		$('.InitialPage').addClass('active');
		return;
	}
	let keepOn = true;
	$('.side-menu li').each(function() {
		let li = $(this);
		li.find('a').each(function() {
			var item = $(this).attr('href');
			//			console.log("item:" + item)
			//判断链接相当（当前页面导航样式）
			if(item == nowPath) {
				li.addClass('open');
				keepOn = false;
			}
			return keepOn;
		});
		return keepOn;
	});
	getMemory();
}

function getMemory() {
	$('.side-nav').addClass(localStorage.getItem('setLayOut1'));
	$('.top-hd,.main-cont,.btm-ft').addClass(localStorage.getItem('setLayOut2'));
	$('.top-hd .hd-lt a').addClass(localStorage.getItem('setLayOut3'));
}

function drowServerList(data) {
	options = '';
	$.each(data, function(index, server) {
		options += '<option value="' + server.id + '">' + server.name + '</option>';
	});
	$("#serverList").html(options);
	cache.set("server", options);
}

function markServer() {
	let id = cache.get("selectedServer");
	$("#serverList").find("[value="+id+"]").attr("selected","selected");
}