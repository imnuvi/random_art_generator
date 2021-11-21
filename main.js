
const puppeteer = require('puppeteer');
var path = require('path');

const url = 'file://' + path.join(__dirname, 'sketch', 'index.html');
console.log(url);


async function generate_image(){
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto(url, {waitUntil: 'networkidle2'});
  await page.screenshot({path: '/Users/ramprakash/development/projects/misc_folder/puppeteer_screenshot.png'});
  console.log(await browser.version());
  await browser.close();
};


generate_image();
