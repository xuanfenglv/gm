$(document).ready(function() {
	$("#table2").on("click",".staus .switch-inner",function(){//动态绑定#table下的所有.staus .switch-inner
		var id = $(this).attr('userid');
		var staus = ($(this).hasClass('open-inner'))?"0":"1";
		updateUserStatus(id,staus);
	});
	$("#table2").on("click",".feng .switch-inner",function(){//动态绑定#table下的所有.staus .switch-inner
		var id = $(this).attr('userid');
		var staus = $(this).hasClass('open-inner');
		if(staus) {
			//解封
			jieFeng(id);
		} else {
			//封号
			id = "<span id='fengid'>"+id+"</span>";
			var name = $(this).parent().parent().parent().children('td').eq(1).html();
			$(".fenghaoPanel .lt-title").html("封号：["+name+"]("+id+")");
			$('.fenghaoPanel,.mask').show();
		}
		
		//封号
//		updateUserStatus(id,staus);
	});
});

function onOff(type, id, on) {
	var onOff;
	if(type=='staus') {
		onOff = $(".staus [userid='"+id+"']");
	} else if(type=='feng') {
		onOff = $(".feng [userid='"+id+"']");
	}
	if(on) {
		onOff.addClass("open-inner");
		onOff.parent().addClass("open-outer");
	} else {
		onOff.removeClass('open-inner');
		onOff.parent().removeClass('open-outer');
	}
}
