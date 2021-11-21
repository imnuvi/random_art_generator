const express = require('express');
const { express_port } = require('config')
const app = express();
const express_port = 3333;

app.get('/', (req,res) => res.send("hello world bro"));

app.get('/image', (req,res) => {
  data = [
  {
    "id": 1,
    "type": "random"
  }]
  res.json(data)
})

app.listen(port, () => console.log(`listening on port ${port} bruh`))
