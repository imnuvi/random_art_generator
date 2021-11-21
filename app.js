const express = require('express');
const { express_port } = require('config')
const app = express();
const express_port = 3333;

import { runner } = require('./main')

app.get('/', (req,res) => res.send("hello world bro"));

app.get('/image', (req,res) => {
  image_url = runner();
  data = [
  {
    "type": "random",
    "url": image_url
  }]
  res.json(data)
})

app.listen(port, () => console.log(`listening on port ${port} bruh`))
