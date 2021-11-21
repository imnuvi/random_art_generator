const express = require('express');
const { expressPort } = require('./config');
const app = express();

const { runner } = require('./main')

app.get('/', (req,res) => res.send("hello world bro"));

app.get('/image', async (req,res) => {
  var image_url = await runner();
  console.log(image_url);
  data = 
  {
    "type": "random",
    "url": image_url
  }
  res.json(data)
})

app.listen(expressPort, () => console.log(`listening on port ${expressPort} bruh`))
