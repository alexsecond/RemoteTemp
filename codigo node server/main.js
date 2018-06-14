var express = require('express');
var app = express();
var server = require('http').Server(app);
var io = require('socket.io')(server);

var port = process.env.PORT || 8080;

app.use(express.static('public'));

server.listen(port, function() { 
	console.log('Servidor corriendo en el puerto: ' + port);
});

io.on('connection', function(socket) {

	console.log("Alguien se ha conectado");

	socket.on('disconnect', function() {
		console.log("Alguien se ha desconectado");
	});

	socket.on("temperature-value", function(data) {
		console.log(data);
		console.log(typeof data);
		socket.emit("get-temperature", data);
	});

});
