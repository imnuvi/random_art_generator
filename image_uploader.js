var Minio = require('minio')

const {  endPoint, port, useSSL, accessKey, secretKey } = require('./config');
console.log(port)
var minioClient = new Minio.Client(
  {
    endPoint: endPoint,
    port: port,
    useSSL: useSSL,
    accessKey: accessKey,
    secretKey: secretKey
  }
);

let file = '/Users/ramprakash/Downloads/myCanvas_2.jpg'
let bucket_name = 'randoimages'

var metaData = {
      'Content-Type': 'image/png',
      'X-Amz-Meta-random-number': 9390,
      'image-name': 'random_name'
  }

minioClient.fPutObject(bucket_name, 'random_canvas.jpg', file, metaData, function(err, etag){
  if (err) return console.log(err);

  console.log(etag);
  console.log('file_uploaded_successfully');
})
