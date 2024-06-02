import dotenv from 'dotenv';
dotenv.config();

export const MongoConnection = process.env.MONGODB;
export const Port = process.env.PORT;
export const ApiKey = process.env.APIKEY;
export const ApiSecret = process.env.APISECRET;
export const CloudName = process.env.CLOUDNAME;
export const UserEmail = process.env.USEREMAIL;
export const UserPass = process.env.USERPASS;
export const TokenSecret = process.env.TOKENSECRET;