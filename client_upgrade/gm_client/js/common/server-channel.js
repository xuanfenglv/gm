$(()=> {
	loadServerBox();
	loadAndroidBox();
	$("#serverAll").change(function() {
		var status = this.checked;
		$("input[name='server']").prop("checked", status);
	});
	$("#androidAll").change(function() {
		var status = this.checked;
		$("#androidContent input[name='channel']").prop("checked", status);
	});
});
function loadServerBox() {
	let serverBox = cache.get("serverBox");
	if(serverBox) {
		$("#serverContent").html(serverBox);
	} else {
		$.getJSON("/gm_client/config/server.json", function(data) {
			drowServerBox(data);
		});
	}
}

function drowServerBox(data) {
	let boxes = '';
	$.each(data, function(index, server) {
		boxes += '<label class="check-box"><input name="server" type="checkbox" value="' + server.id + '"><span>' + server.name + '</span></label>';
	});
	cache.set("serverBox", boxes);
	$("#serverContent").html(boxes);
}

function loadAndroidBox() {
	let androidBox = cache.get("androidBox");
	if(androidBox) {
		$("#androidContent").html(androidBox);
	} else {
		$.getJSON("/gm_client/config/AndroidChannel.json", function(data) {
			drowAndroidBox(data);
		});
	}
}

function drowAndroidBox(data) {
	let boxes = '';
	$.each(data, function(index, item) {
		boxes += '<label class="check-box"><input name="channel" type="checkbox" value="' + item.id + '"><span>' + item.name + '</span></label>';
	});
	cache.set("androidBox", boxes);
	$("#androidContent").html(boxes);
}
/**
 * @description 返回复选框选中的值为一个数组
 * @param {Object} name
 */
function getSelectedBox(name) {
	let servers = new Array();
	$("[name=" + name + "]:checkbox:checked").each(function() {
		servers.push(parseInt($(this).val()));
	})
	return servers;
}
function setSelectedBox(name,arr) {
	$("[name=" + name + "]:checkbox:checked").prop("checked", false);
	if(typeof(arr)=='string') {
		arr = JSON.parse(arr);
	}
	for(let i of arr) {
		$("[name=" + name + "]:checkbox[value="+i+"]").prop("checked", true);
	}
}