import mongoose from "mongoose";
import { MongoConnection } from "../SettingEnv.js";

export default function GetMongoConnection() {
  mongoose.set('strictQuery', true);
  mongoose.connect(MongoConnection);
  mongoose.connection.on('open', () => { console.log('Connection database succes.') })
  mongoose.connection.on('error', (err) => { console.error(`Connection database error: ${err}`) });
}