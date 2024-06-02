import nodemailer from 'nodemailer';
import smtp from 'nodemailer-smtp-transport'
import { UserEmail, UserPass } from '../SettingEnv.js'

const transport = nodemailer.createTransport(
  smtp({
    host: "smtp.gmail.com",
    port: 465,
    secure: true,
    auth: {
      user: UserEmail,
      pass: UserPass
    }
  })
)

export default async function SendEmail(message) { return await transport.sendMail(message) }; 