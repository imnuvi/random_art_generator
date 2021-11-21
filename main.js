
var Minio = require('minio')
const puppeteer = require('puppeteer');
var path = require('path');

const url = 'file://' + path.join(__dirname, 'sketch', 'index.html');

const {  endPoint, port, useSSL, accessKey, secretKey, bucketName, imageLocationLocal } = require('./config');
console.log(bucketName, imageLocationLocal);
var minioClient = new Minio.Client(
  {
    endPoint: endPoint,
    port: port,
    useSSL: useSSL,
    accessKey: accessKey,
    secretKey: secretKey
  }
);

var bucket_name = bucketName

var metaData = {
      'Content-Type': 'image/png',
      'X-Amz-Meta-random-number': 9390,
      'image-name': 'random_name'
  }

//this function creates a random word for the image
function create_word(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() *
 charactersLength));
   }
   return result;
}

async function generate_image(image_path){
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto(url, {waitUntil: 'networkidle2'});
  await page.screenshot({path: image_path});
  console.log(image_path);
  console.log(await browser.version());
  await browser.close();
};

function put_image(bucket_name,file_name, file_path, metadata){
  minioClient.fPutObject(bucket_name, file_name, file_path, metaData, function(err, etag){
    if (err) return console.log(err);

    console.log(etag);
    console.log('file_uploaded_successfully');
  })
}

function get_image_url(bucket_name, file_name){
  minioClient.presignedUrl('GET', bucket_name, file_name, 24*60*60, function(err, presignedUrl) {
    if (err) return console.log(err);
    console.log(presignedUrl);
  })
}

export async function runner(){
  file_name = create_word(7) + '.png';
  file_path = imageLocationLocal + file_name;
  await generate_image(file_path)
  .then(
    () => {
      put_image(bucket_name, file_name, file_path, metaData);
    }).then(
    () => {
      return get_image_url(bucket_name, file_name);
    }
  )
}

console.log(imageLocationLocal);
return_string = runner();
