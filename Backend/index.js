import express from "express";
import Application from "./src/Application.js";
import { Port } from "./src/SettingEnv.js";

const Server = express();

Server.use(Application);
Server.listen(Port, () => { console.log(`The server is running on port ${Port}`) })