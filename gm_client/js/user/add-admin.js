$(function() {
	$("#createAdmin").click(function() {
		let data = new Object();
		data.msgId = 101;
		data.name = $("#adminName").val();
		data.pwd = $("#pwd1").val();
		let pwd2 = $("#pwd2").val();
		data.power = $("#power").val();
		
		if(data.pwd!=pwd2) {
			tip("两次输入密码不一致");
			return;
		}
		if(checkData(data)) {
			postService(data,addAdminCallBack);
		}
	});
	$("#adminName").blur(function() {
		let data = new Object();
		data.msgId = 106;
		data.name = $("#adminName").val();
		if(data.name) {
			postService(data,handleExist);
		}
	});
	
});
function checkData(data) {
	if(!data.name) {
		tip("名称不为空");
		return false;
	} else if(data.name.length>10) {
		tip("名称长度不超过10");
		return false;
	} else if(!data.pwd) {
		tip("密码不为空");
		return false;
	} else if(data.pwd.length>15) {
		tip("密码长度不超过15");
		return false;
	}
	return true;
}
function addAdminCallBack() {
	layer.msg("添加管理员成功",{icon:1,time:1000});
}
function handleExist(data) {
	if(data.exist) {
		$("#exist").css("display","block");
		$("#createAdmin").attr("disabled","disabled");
	} else {
		$("#exist").css("display","none");
		$("#createAdmin").removeAttr("disabled");
	}
}
