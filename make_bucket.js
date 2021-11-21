var Minio = require('minio')

const {  endPoint, port, useSSL, accessKey, secretKey } = require('config');

var minioClient = new Minio.Client(
  {
    endPoint: endPoint,
    port: port,
    useSSL: useSSL,
    accessKey: accessKey,
    secretKey: secretKey
  }
);

let file = '/Users/ramprakash/Downloads/myCanvas.jpg'
let bucket_name = 'randoimages'

minioClient.makeBucket(bucket_name, 'us-east-1', function(err) {
  if (err) return console.log(err);

  console.log('bucket created successfully');
  var metaData = {
        'Content-Type': 'image/png',
        'X-Amz-Meta-random-number': 9390,
        'image-name': 'random_name'
    }

  minioClient.PutObjet('rando_images', 'random_canvas.jpg', file, metaData, function(err, etag){
    if (err) return console.log(err);

    console.log(etag);
    console.log('file_uploaded_successfully');
  })
})
