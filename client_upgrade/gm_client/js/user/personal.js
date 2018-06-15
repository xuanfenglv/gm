$(function() {
	$("#updatePwd").click(function() {
		let data = new Object();
		data.msgId = 105;
		data.oldPwd = $("#oldPwd").val();
		data.newPwd = $("#newPwd1").val();
		let pwd2 = $("#newPwd2").val();

		if(data.newPwd!=pwd2) {
			tip("2次输入密码不一致");
			return;
		}
		postService(data, function() {
			success("修改密码成功");
		});
	});
});
