var http = require('http');
var cal = require('./caculator')
http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.write("Ket qua phep tinh: " + cal.giaiPhuongTrinh(-2,-4,3));
}).listen(8080);