import cors from 'cors';
import express from "express";
import Routes from "./Routes/Routes.js";
import bodyParser from 'body-parser';
import fileUpload from 'express-fileupload';
import GetMongoConnection from "./Database/Connection.js";

const Application = express();

GetMongoConnection();
Application.use(cors());
Application.use(bodyParser.json({ limit: "100mb", extended: true }));
Application.use(bodyParser.urlencoded({ limit: "100mb", extended: true }));
Application.use(fileUpload({ useTempFiles: true, tempFileDir: "./Resources" }));
Application.use("/backend/api/rest/sivardenuncias/server/node/route/fetch/axios", Routes);

export default Application;