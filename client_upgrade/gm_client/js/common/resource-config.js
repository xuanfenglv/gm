var rewardMap = new Map();
$(function() {
		loadResource()
	//	loadResource(1);
	//	loadResource(2);
	//	loadResource(3);

	$(".resource-option .add").click(function() {
		let addBtn = $(this);
		addResource(addBtn);
	});
	$(".resource-info").on('click', '.del', function() {
		let delBtn = $(this);
		let li = delBtn.parent();
		let key = li.attr('key');
		rewardMap.delete(key);
		li.remove();
	});
});

function addResource(addBtn) {
	let type = parseInt(addBtn.parent().attr('type'));
	console.log(type);

	let resourceId = parseInt(addBtn.siblings(".resource").val());
	//	let resourceName = addBtn.siblings(".resource").find("option:selected").text();
	let num = parseInt(addBtn.siblings(".num").val());

	let reward = new Reward(type, resourceId, num);
	addReward(reward);
}

function addReward(reward) {
	let key = reward.getKey();
	let typeName = getTypeName(reward.type);
	//	

	if(rewardMap.has(key)) {
		let before = rewardMap.get(key);
		before.setNum(before.getNum() + reward.getNum());
		// 视图更新
		updateInfo(before);
	} else {
		rewardMap.set(key, reward);
		// 添加视图
		let resourceName = $("li[type=" + reward.type + "] .resource option[value=" + reward.id + "]").text();
		addInfo(reward, typeName, resourceName);
	}
}

function addRewards(rewards) {
	let type = typeof(rewards);
	if(type == 'string') {
		rewards = JSON.parse(rewards);
	}
	for(let reward of rewards) {
		addReward(new Reward(reward.type, reward.id, reward.num));
	}

}
/**
 * 清空视图
 */
function clearRewards() {
	rewardMap.clear();
	$(".resource-info").html('');
}

function addInfo(reward, typeName, resourceName) {
	let info =
		'<li key="' + reward.getKey() + '">' +
		'<div class="type tag ">' + typeName + '</div>' +
		'<div class="name tag ">' + resourceName + '</div>' +
		'<div class="tip tag ">X</div>' +
		'<div class="num tag ">' + reward.getNum() + '</div>' +
		'<div class="del tag " title="移除"></div>' +
		'</li>';
	$(".resource-info").append(info);
}

function updateInfo(reward) {
	$(".resource-info [key='" + reward.getKey() + "']").children(".num").html(reward.getNum());
}

function getRsourceSize() {
	return rewardMap.size;
}

function getResourceInfo() {
	let arr = new Array();
	rewardMap.forEach(function(item) {
		arr.push(item);
	});
	return arr;
	console.log(JSON.stringify(arr));
}
class Reward {
	constructor(type, id, num) {
		this.type = type;
		this.id = id
		this.num = num;
	}
	setType(type) {
		this.type = type;
	}
	setId(id) {
		this.id = id;
	}
	setNum(num) {
		return this.num = num;
	}
	getType() {
		return this.type;
	}
	getId() {
		return this.id;
	}
	getNum() {
		return this.num;
	}
	getKey() {
		return this.type + '#' + this.id;
	}
}
//function getKey(reward) {
//	return reward.type + '#' + reward.id;
//}

function getTypeName(type) {
	switch(type) {
		case 1:
			return '道具';
		case 2:
			return '装备';
		case 3:
			return '卡片';
		case 4:
			return '钻石';
		case 5:
			return '金币';
		case 6:
			return '银币';
		default:
			return "未知";
	}
}

function getTypeVal(type) {
	switch(type) {
		case 1:
			return 'item';
		case 2:
			return 'equip';
		case 3:
			return 'card';
		case 4:
			return 'demond';
		case 5:
			return 'gold';
		case 6:
			return 'sliver';
		default:
			return "未知";
	}
}
//@deprecated
// 前端获取
//function loadResource(type) {
//	let typeVal = getTypeVal(type);
//	let list = cache.get(type + "List");
//	if(list) {
//		$("li[type='" + type + "'] .resource").html(list);
//	} else {
//		$.getJSON("/gm_client/config/" + typeVal + ".json", function(data) {
//			drowResourceList(type, data);
//		});
//	}
//}

/**
 * 服务端获取
 */
function loadResource() {
	let list = cache.get("1List");
	if(list) {
		$("li[type='1'] .resource").html(list);
		$("li[type='2'] .resource").html(cache.get("2List"));
		$("li[type='3'] .resource").html(cache.get("3List"));
	} else {
		let msg = new Message(499);
		getService(msg, (data) => {
			drowResourceList(1, data.item);
			drowResourceList(2, data.equip);
			drowResourceList(3, data.card);
		});
	}

}

function drowResourceList(type, data) {
	let list = '';
	$.each(data, function(index, item) {
		list += '<option value="' + item.id + '">[' + item.id + ']' + item.name + '</option>';
	});
	cache.set(type + "List", list);
	$("li[type='" + type + "'] .resource").html(list);
}