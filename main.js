
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
  return "bruh";
};

//async function put_image(bucket_name,file_name, file_path, metadata){
//  await }

//async function get_image_url(bucket_name, file_name){
//  let the_url = await   return the_url
//}

async function runner(){
  file_name = create_word(7) + '.png';
  file_path = imageLocationLocal + file_name;
  let generated_image_url = '';
  var gen_url = await generate_image(file_path)
  console.log(gen_url);
  succeeded = await minioClient.fPutObject(bucket_name, file_name, file_path, metaData, function(err, etag){
    if (err) return console.log(err);

    console.log(etag);
    console.log('file_uploaded_successfully');
    return "success"
  });

  response_url = await minioClient.presignedUrl('GET', bucket_name, file_name, 24*60*60, function(err, presignedUrl) {
    if (err) return console.log(err);
    return presignedUrl
  });

  console.log(succeeded);
  console.log(response_url);

//  .then(
//    (dat) => {
//      put_image(bucket_name, file_name, file_path, metaData);
//    }).then(
//    () => {
//      get_image_url(bucket_name, file_name);
//    }
//  ).then((generated_image_url) => {
//      return generated_image_url
//    });
}

runner()
//.then((return_string) => {
//});
module.exports.runner = runner;
