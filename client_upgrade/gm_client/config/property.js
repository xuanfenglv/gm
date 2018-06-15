var server = {}
server.host = '127.0.0.1' // 服务器域名
server.port = 80 // 服务器端口号
timeout = 5000000 //超时时间
// 首页
indexPage = '/gm_client/index.html'
loginPage = '/gm_client/login.html'
// 工程名
projectName = 'gm_client'
// 视图包
viewPkg = 'page'
cachable = true; // 缓存开关（dev环境关闭，上线打开）








server.url = 'http://'+server.host+(server.port==80?'':':'+server.port);
server.upload = server.url + '/upload';
server.service = server.url + '/service';
server.resource = server.upload + '/resource';
server.img = server.upload + '/img';
server.imgs = server.upload + '/imgs';
server.imgPkg = server.url + '/img/';
server.loginNotice = server.url + '/loginNotice';
var storage=window.localStorage;