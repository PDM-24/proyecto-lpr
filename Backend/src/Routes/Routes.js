import express from 'express';
import { GetCategories, UploadCategory } from '../Controller/Categoria.js';
import { GetSupportByComplaint, SupportComplaint } from '../Controller/Apoyo.js';
import { DeleteComplaint, GetComplaints, GetComplaintsByUserId, UpdateComplaint, UploadComplaint } from '../Controller/Publicacion.js';
import { ChangeRol, DeleteUser, GetUsers, StateUser, Login, SendEmailCode, SignUp, UpdatePass, UpdatePhoto, UpdateProfile, VerifyToken } from "../Controller/Usuario.js";

const Routes = express.Router();

//Post
Routes.post('/singup', SignUp)
Routes.post('/login', Login);
Routes.post('/uploadcategory', UploadCategory)
Routes.post('/uploadcomplaint', UploadComplaint)
Routes.post('/getemailcode/:Id', SendEmailCode);
Routes.post('/supportcomplaint', SupportComplaint)

//Get
Routes.get('/verifytoken/:TokenKey', VerifyToken);
Routes.get('/getusers', GetUsers);
Routes.get('/getcategories', GetCategories);
Routes.get('/getcomplaints', GetComplaints);
Routes.get('/getcomplaintsbyuserid', GetComplaintsByUserId);
Routes.get('/getsupportbycomplaint/:Id', GetSupportByComplaint);

//Put
Routes.put('/updateprofile/:Id', UpdateProfile);

//Patch
Routes.patch('/stateuser/:Id', StateUser);
Routes.patch('/updatephoto/:Id', UpdatePhoto);
Routes.patch('/changerol/:Id/:Rol', ChangeRol);
Routes.patch('/updatepass/:Id', UpdatePass);
Routes.patch('/updatecomplaint/:Id', UpdateComplaint)

//Delete
Routes.delete('/deleteuser/:Id', DeleteUser);
Routes.delete('/deletecomplaint/:Id', DeleteComplaint)

export default Routes;