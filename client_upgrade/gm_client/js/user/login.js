$(function() {
	$('#entry').click(function() {
		login();
	});
//	$(".logo").fadeIn(2000);
	$(".logo").slideDown(1500);
});

function login() {
	let data = {};
	data.name = $("#name").val();
	data.pwd = $("#pwd").val();
	if(data.name == '') {
		tip("用户名不能为空");
	} else if(data.pwd == '') {
		tip('请输入管理员密码');
	} else {
		let url = server.url + '/user/login?heh=hahaha';

		post(url, data, function() {
			cache.set("admin", data.name);
			location.href = indexPage;
		});
	}
}