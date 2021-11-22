
var Minio = require('minio')
const puppeteer = require('puppeteer');
var path = require('path');

const url = 'file://' + path.join(__dirname, 'sketch', 'index.html');

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
  let imageBuf = await page.screenshot({type: 'png'});
  await browser.close();
  return Promise.resolve(imageBuf);
};

async function runner(){
  var imageBuffer = await generate_image()
  return imageBuffer

};
module.exports.runner = runner;
