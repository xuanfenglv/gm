$(() => {
	//添加拖拽事件
	var dz = document.getElementById('drag-frame');
	dz.ondragover = function(ev) {
		//阻止浏览器默认打开文件的操作
		ev.preventDefault();
		//拖入文件后边框颜色变红
		this.style.borderColor = 'red';
	}

	dz.ondragleave = function() {
		//恢复边框颜色
		this.style.borderColor = 'gray';
	}
	dz.ondrop = function(ev) {
		ev.preventDefault();
		//恢复边框颜色
		this.style.borderColor = 'gray';
		//阻止浏览器默认打开文件的操作

		var drag_files = ev.dataTransfer.files;
		var frag = document.createDocumentFragment(); //为了减少js修改dom树的频度，先创建一个fragment，然后在fragment里操作
		var tr, time, size;
		for(var file of drag_files) {
			tr = document.createElement('tr');
			//获取文件大小
			size = Math.round(file.size * 100 / 1024) / 100 + 'KB';
			//获取格式化的修改时间
			time = file.lastModifiedDate.toLocaleDateString() + ' ' + file.lastModifiedDate.toTimeString().split(' ')[0];
			tr.innerHTML = '<td>' + file.name + '</td><td>' + time + '</td><td>' + size + '</td><td>删除</td>';
			console.log(size + ' ' + time);
			frag.appendChild(tr); //?
			dragFiles.set(file.name,file);
		}
		this.childNodes[1].childNodes[1].appendChild(frag);
		//为什么是‘1'？文档里几乎每一样东西都是一个节点，甚至连空格和换行符都会被解释成节点。而且都包含在childNodes属性所返回的数组中.不同于jade模板
	}
	// 用事件委托的方法为‘删除'添加点击事件，使用jquery中的on方法
	$(".tbody").on('click', 'tr td:last-child', function() {
		//删除拖拽框已有的文件
		var key = $(this).prev().prev().prev().text();
		console.log(key);
		dragFiles.remove()
		$(this).parent().remove();
	});
	
	$("#upload").click(upload);
	$("#clear").click(clearAll);
});
//利用html5 FormData() API,创建一个接收文件的对象，因为可以多次拖拽，这里采用单例模式创建对象Dragfiles
var newForm = new FormData();
let dragFiles = new Map();

function blink() {
	document.getElementById('content').style.borderColor = 'gray';
}

//ajax上传文件
//function upload() {
//	if(document.getElementsByTagName('tbody')[0].hasChildNodes() == false) {
//		document.getElementById('content').style.borderColor = 'red';
//		setTimeout(blink, 200);
//		return false;
//	}
//	$.ajax({
//		url: 'upload',
//		type: 'POST',
//		data: newForm,
//		async: true,
//		cache: false,
//		contentType: false,
//		processData: false,
//		success: function(data) {
//			alert('succeed!') //可以替换为自己的方法
//			closeModal();
//			data.deleteAll(); //清空formData
//			$('.tbody').empty(); //清空列表
//		},
//		error: function(returndata) {
//			alert('failed!') //可以替换为自己的方法
//		}
//	});
//}

//清空所有内容
function clearAll() {
	$("#select-file").val('');
	$("#drag-frame tbody").html('');

	dragFiles.clear();
}

function getAllFiles() {
	var selectFiles = document.getElementById("select-file").files;
	
	for(var file of selectFiles) {　　
		if(dragFiles.set(file.name,file));
	}
	var formDate = new FormData();
	dragFiles.forEach(function(item) {
		formDate.append("files",item);
	})
	
	console.log(dragFiles.size);
	return formDate;
}
//function upload() {
//	var formData = getAllFiles();
//	var keyIterator = formData.keys();
//	var n = keyIterator.next().value;
//	if(!n) {
//		return ;
//	}
//	post(server.resource,formData,()=>{
//		success("文件上传成功")
//		clearAll();
//	},true);
//}
function isEmpty(formData) {
	var keyIterator = formData.keys();
	var n = keyIterator.next().value;
	if(!n) {
		return true;
	}
	return false;
}
