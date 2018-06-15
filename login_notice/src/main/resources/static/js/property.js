var server = {}
server.host = '127.0.0.1' // 服务器域名
server.port = 80 // 服务器端口号
timeout = 10000 //超时时间

server.url = 'http://'+server.host+(server.port==80?'':':'+server.port);
server.imgPkg = server.url + '/img/';
