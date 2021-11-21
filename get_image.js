
var Minio = require('minio');

const {  endPoint, port, useSSL, accessKey, secretKey } = require('./config');

var minioClient = new Minio.Client(
  {
    endPoint: endPoint,
    port: port,
    useSSL: useSSL,
    accessKey: accessKey,
    secretKey: secretKey
  }
);


let file = '/Users/ramprakash/development/projects/misc_folder/random_canvas.jpg'
let bucket_name = 'randoimages'

//minioClient.fGetObject(bucket_name, 'random_canvas.jpg', file,  function(err){
  //if (err) return console.log(err);
//
  //console.log('file downloaded successfully');
//})


minioClient.presignedUrl('GET', bucket_name, 'random_canvas.jpg', 24*60*60, function(err, presignedUrl) {
  if (err) return console.log(err)
  console.log(presignedUrl)
})
