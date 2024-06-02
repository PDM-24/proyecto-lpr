import { Usuario } from "../Models/Model.js";
import { EncryptPassword, ComparePassword } from "../Libraries/Bcrypt.js";
import { UploadImage, DeleteImage } from "../Libraries/Cloudinary.js";
import { TokenSecret } from "../SettingEnv.js";
import SendEmail from '../Libraries/Nodemailer.js'
import Jwt from "jsonwebtoken";
import { remove } from 'fs-extra';
import { UserEmail } from "../SettingEnv.js";

export const SignUp = async (req, res) => {
  const { username, name, surname, email, birthdate, rol, pass } = req.body;
  try {
    if (username && name && surname && email && birthdate && rol && pass) {

      const checkUser = await Usuario.findOne({ username })
      const checkEmail = await Usuario.findOne({ email });

      if (checkUser != null || checkEmail != null) {
        res.status(200).json({ state: "unregistered", details: "user or email already exist" })
      } else {
        //Prepare data
        const password = await EncryptPassword(pass)
        const birth = new Date(birthdate);

        //Save user
        new Usuario({ username, name, surname, email, birth, rol, password }).save().then(() => {
          res.status(200).json({ state: "success", details: "your information has been registered" })
        }).catch(error => {
          res.status(500).json({ state: "failure", details: error.message })
        })
      }
    } else {
      //Send message
      res.status(400).json({ state: "error", details: "There is an error in the input parameters" });
    }
  } catch (error) {
    //Send error message
    res.status(error.http_code).json({ state: "failure", details: error.message });
  }
};

export const Login = async (req, res) => {
  const { username, password } = req.body;
  try {
    const usuario = await Usuario.findOne({ username });
    if (usuario && (await ComparePassword(password, usuario.password))) {
      //Save user data with token
      const token = Jwt.sign({ usuario }, TokenSecret, { expiresIn: '5h' });
      res.status(200).json({ state: "success", token });
    } else {
      res.status(200).json({ state: "error", details: "Usuario o contraseña incorrectos." });
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message });
  }
}

export const VerifyToken = (req, res) => {
  const { TokenKey } = req.params;
  try {
    const token = Jwt.verify(TokenKey, TokenSecret);
    res.status(200).json({ state: "success", result: { ...token.usuario } })
  } catch (error) {
    res.status(200).json({ state: "failure", details: error.message });
  }

}

export const GetUsers = async (req, res) => {
  const { Search, Rol, Page } = req.query;
  try {
    Usuario.paginate({ username: { $regex: Search ?? "", $options: 'i' }, rol: Rol }, { page: Page, limit: 10 }).then((results) => {
      res.status(200).json({ state: 'success', results });
    }).catch(error => {
      res.status(error.http_code).json({ state: "failure", details: error.message })
    });
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message });
  }
}

export const ChangeRol = async (req, res) => {
  const { Id, Rol } = req.params;
  try {
    Usuario.findByIdAndUpdate(Id, { rol: Rol }).then((result) => {
      res.status(200).json({ state: "success", details: `El usuario:${result.username}, paso de ser ${result.rol} a ser ${Rol}` })
    }).catch(error => {
      res.status(error.http_code).json({ state: "failure", details: error.message })
    });
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const UpdatePhoto = async (req, res) => {
  //Data
  const { Id } = req.params;
  try {
    const { photo } = req.body;
    //Check Data
    if (photo) {
      const check = await Usuario.findById(Id);
      if (check != null) {
        const img = await UploadImage(photo);
        Usuario.findByIdAndUpdate(Id, { image: { public_id: img.public_id, url: img.url } }).then(async (result) => {
          // remove(photo.tempFilePath);
          if (result.image.public_id !== "") DeleteImage(result.image.public_id);
          const usuario = await Usuario.findById(Id);
          const token = Jwt.sign({ usuario }, TokenSecret, { expiresIn: '1h' })
          res.status(200).json({ state: 'success', token });
        }).catch(error => {
          //remove(photo.tempFilePath)
          res.status(error.http_code).json({ state: "failure", details: error.message })
        })
      } else {
        //remove(photo.tempFilePath)
        res.status(400).json({ state: "error", details: "Your photo could´t be update" })
      }
    } else {
      res.status(400).json({ status: 'error', details: 'There is an error in the input parameters' })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const UpdateProfile = async (req, res) => {
  const { Id } = req.params;
  const { username, name, surname, email } = req.body;
  try {
    if (username && name && surname && email) {
      const check = await Usuario.findById(Id);
      if (check !== null) {
        Usuario.findByIdAndUpdate(Id, { username, name, surname, email }).then(async () => {
          const usuario = await Usuario.findById(Id);
          const token = Jwt.sign({ usuario }, TokenSecret, { expiresIn: '1h' })
          res.status(200).json({ state: "success", token });
        }).catch(error => {
          res.status(200).json({ state: "error", details: 'Username or email already used by other user or Id user not found' })
        })
      } else {
        res.status(400).json({ state: "error", details: "Your profile could´t be update" })
      }
    } else {
      res.status(400).json({ status: 'error', details: 'There is an error in the input parameters' })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const UpdatePass = async (req, res) => {
  const { Id } = req.params;
  const { password } = req.body;
  try {
    if (Id && password) {
      const check = await Usuario.findById(Id);
      if (check !== null) {
        Usuario.findByIdAndUpdate(Id, { password: await EncryptPassword(password) }).then(() => {
          res.status(200).json({ state: "success", details: 'Your password has been updated' });
        }).catch(error => {
          res.status(error.http_code).json({ state: "failure", details: error.message })
        })
      } else {
        res.status(400).json({ state: "error", details: "Your pass could't be update" })
      }
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const StateUser = async (req, res) => {
  const { Id } = req.params;
  try {
    const check = await Usuario.findById(Id);
    if (check !== null) {
      Usuario.findByIdAndUpdate(Id, { state: !check.state }).then((result) => {
        res.status(200).json({ state: "success", details: `El cambio de estado del usuario se realizo correctamente` })
      }).catch(error => {
        res.status(error.http_code).json({ state: "failure", details: error.message })
      });
    } else {
      res.status(400).json({ state: "error", details: "User could't be lock or no exist." })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const DeleteUser = async (req, res) => {
  const { Id } = req.params;
  try {
    Usuario.findByIdAndDelete(Id).then((data) => {
      if (data.image.public_id !== "") DeleteImage(data.image.public_id);
      res.status(200).json({ state: "success", details: `User ${data.username} as been deleted.` })
    }).catch(error => {
      res.status(error.http_code).json({ state: "failure", details: error.message })
    });
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const SendEmailCode = async (req, res) => {
  const { code } = req.body;
  const { Id } = req.params;
  try {
    if (code) {
      const getuser = await Usuario.findById(Id);
      if (getuser !== null) {
        SendEmail({
          from: UserEmail,
          to: getuser.email,
          subject: "Codigo de apoyo de la denuncia",
          html: `
           <h2>Estimado/a ${getuser.username}</h2>
           <p>EL codigo de apoyo de la denuncia es: <label style='color:blue; font-weight: 900;'>${code}</label></p>
           <br />
           <P>No responde a este correo por favor.</p>
      `
        }).then(responce => {
          res.status(200).json({ state: 'success', message: 'Se te ha enviado un correo electronico con el codigo de apoyo para la denuncia' });
          console.log(`Send email to ${getuser.username}, idmessage:${responce.messageId}`);
        }).catch((error => {
          res.status(error.http_code0).json({ state: 'error', message: 'Lo sentimos, ocurrio un error al enviarte el correo electronico con el codigo de apoyo, verifique que su correo sea correcto por favor' });
          console.log(`There is a problem with nodemailer ${error}`);
        }));
      } else {
        res.status(400).json({ state: 'error', message: 'The user does not exist in the database' });
      }
    } else {
      res.status(400).json({ state: "error", details: "There is an error in the input parameters" });
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", message: error.message })
  }
}