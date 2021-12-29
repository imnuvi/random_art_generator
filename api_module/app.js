const express = require('express');
const { expressPort } = require('./config');
const app = express();

const { generate_image } = require('./main')

app.get('/', (req,res) => res.send("hello world bro"));

app.get('/image', async (req,res) => {
  var image_buffer = await generate_image();
  res.setHeader('content-type', 'image/png');
  res.write(image_buffer, 'binary');
  res.end(null, 'binary');
})

app.listen(expressPort, () => console.log(`listening on port ${expressPort} bruh`))
