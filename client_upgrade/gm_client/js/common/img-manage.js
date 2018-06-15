$(() => {
loadImg();
	$("#imgs-container").on("mousedown", ".img-select", function() {

		if(inEdit) {
			onKeyDown = true;
			selectImg(this);
		} else {
			let src = $(this).children().attr("src");
			$("#bigImg").attr("src", src);
			$('.mask,.dialog').show();
		}

		//		alert(src)
	});

	$("body").mouseup(() => {
		onKeyDown = false;
	})
	$("#imgs-container").on("mouseover", ".img-select", function() {

		if(onKeyDown) {
			selectImg(this);
		}
	});

	$("#edit").change(function() {
		inEdit = $(this).prop("checked");
		if(inEdit) {
			$("#delete").show();
		} else {
			$("#delete").hide();
		}
	})
	$("#delete").click(() => {
			layer.confirm('确定删除选中的图片？', {
				btn: ['确定', '取消'] //按钮
			}, function() {
				console.log("删除");
				layer.closeAll();
				delImg();
			}, function() {
				console.log("取消");
			});
	});
	//	
});

function selectImg(source) {
	let name = $(source).attr("name");
	var select = $(source).children(".select");
	if(select.hasClass("selected")) {
		select.removeClass("selected");
		selectedImg.remove(name)
	} else {
		select.addClass("selected");
		selectedImg.add(name);
	}
}
function selectImgByName(name) {
	let source = $(".img-select[name='"+name+"']");
	var select = source.children(".select");
	if(!select.hasClass("selected")) {
		select.addClass("selected");
		selectedImg.add(name);
	}
}

function loadImg() {
	$("#imgs-container").html('');
	get(server.imgs, {}, (data) => {
		loadImgByArr(data.imgs);
	});
}
function loadImgByArr(arr) {
	$.each(arr, function(index, img) {
			let imgHtml = '<div class="img-container">' +
				'<div class="img-select" name="' + img + '">' +
				'<img class="img-body" src="' + server.imgPkg + img + '" />' +
				'<div class="select"></div>' +
				'</div>' +
				'</div>';
			$("#imgs-container").append(imgHtml);
		});
}
function delImg() {
	let msg = new DelImgsMessage();
	postServiceWithCheck(msg,()=>{
		success("删除成功");
		loadImg();
	});
}
let inEdit = false;
let onKeyDown = false;
var selectedImg = {
	arr: [],
	add: function(name) {
		this.arr.push(name);
	},
	clear: function() {
		this.arr.splice(0, this.arr.length)
	},
	remove: function(name) {
		var index = this.arr.indexOf(name);
		this.arr.splice(index, 1);
	},
	size: function() {
		return this.arr.length;
	}
}
class DelImgsMessage extends Message {
	constructor() {
		super(4);
		this.imgs = selectedImg.arr;
	}
	checkProperty() {
		if(this.imgs.length==0) {
			tip("未选中图片");
			return false;
		}
		return true;
	}
}
