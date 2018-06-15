var params = GetUrlParams();
var noticeMap = new Map();

$(() => {
    let ul = $(".notice-select ul");
let h1 = $(".title h1");
let imgs = $(".imgs");
let content = $(".content");

get("loginNotice", {
    ids: params.ids
}, (data) => {
    success("加载完毕");
drawContent(data.recordList[0]);

$.each(data.recordList, function(id, notice) {
    ul.append($("<li id=" + notice.id + " >" + notice.title + "</li>"))
    noticeMap.set(notice.id, notice);
});
});

function drawContent(notice) {
    h1.html('');
    h1.html(notice.title);
    imgs.html('');
    $.each(JSON.parse(notice.imgs), function(id, img) {
        var img = '<div class="img-wrapper"><img class="img" src="' + server.imgPkg + img + '"></div>';
        imgs.append(img);
    });
    content.html('')
    content.html(notice.content);
}

$(".notice-select").on("click", "li", function() {
    let notice = noticeMap.get(parseInt(this.id));
    drawContent(notice);
});
});

function success(msg) {
    layer.msg(msg, {
        icon: 1,
        time: 1000
    });
}