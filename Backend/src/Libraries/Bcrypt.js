import bcrypt from 'bcryptjs'

export async function EncryptPassword(password) {
  return await bcrypt.hash(password, 10);
}

export async function ComparePassword(password, encryptpass) {
  return await bcrypt.compare(password, encryptpass);
}