import cloudinary from 'cloudinary';
import { ApiKey, ApiSecret, CloudName } from '../SettingEnv.js';

cloudinary.v2.config({
  api_key: ApiKey,
  api_secret: ApiSecret,
  cloud_name: CloudName,
  secure: true
})

export async function UploadImage(image) {
  return await cloudinary.v2.uploader.upload(image, { folder: 'Denunicas' })
}

export async function DeleteImage(id) {
  return await cloudinary.v2.uploader.destroy(id);
}