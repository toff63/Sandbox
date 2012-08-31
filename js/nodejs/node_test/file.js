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

        var rs = fs.createReadStream(file_path);
        rs.on('data', function(file_content){
            var flushed = response.write(file_content);
            if(!flushed){
                rs.pause();   
            }
        });

        response.on('drain', function(){
            rs.resume();
        });
        rs.on('end', function(){
            response.end();
        });

    }).listen(4000);
});


