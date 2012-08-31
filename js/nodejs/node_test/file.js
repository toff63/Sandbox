var http = require('http');
var fs = require('fs');

var file_path =  "/home/toff/Pictures/data_pictures/Villefranche1040277.JPG";
fs.stat(file_path, function(err, stat){
    if(err){
        throw err;
    }
    http.createServer(function(request, response){
        response.writeHead(200, {
            'Content-Type': 'image/jpeg',
            'Content-Length' : stat.size
        });

        fs.readFile(file_path, function(err, file_content){
            response.write(file_content);
            response.end();
        });
    }).listen(4000);
});


