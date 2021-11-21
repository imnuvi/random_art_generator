const dotenv = require('dotenv');

dotenv.config();

module.exports = {
  expressPort: parseInt(process.env.EXPRESS_PORT),
  endPoint: process.env.MINIO_ENDPOINT,
  port: parseInt(process.env.MINIO_PORT),
  useSSL: (process.env.USE_SSL.toLowerCase() === 'true'),
  accessKey: process.env.ACCESS_KEY,
  secretKey: process.env.SECRET_KEY,
  bucketName: process.env.BUCKET_NAME,
  imageLocationLocal: process.env.IMAGE_LOCATION_LOCAL,
  hostPrefix: process.env.HOST_PREFIX
}


