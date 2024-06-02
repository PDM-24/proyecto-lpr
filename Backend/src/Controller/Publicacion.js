import { Apoyo, Categoria, Publicacion } from '../Models/Model.js'
import { DeleteImage, UploadImage } from '../Libraries/Cloudinary.js'
import { remove } from 'fs-extra';
import { Types } from 'mongoose';

export const UploadComplaint = async (req, res) => {
  const { user, category, departamento, details, fecha, img } = req.body;
  try {
    if (user && category && departamento && details && fecha) {
      //data
      const date = new Date(fecha);
      const usuario = new Types.ObjectId(user);
      const categoria = new Types.ObjectId(category);

      if (img) {

        //img file
        const cloudimg = await UploadImage(img);
        const image = { public_id: cloudimg.public_id, url: cloudimg.url };

        //Upload complaint
        const upload = new Publicacion({ categoria, usuario, departamento, image, details, date })
        upload.save().then(() => {
          //remove(img.tempFilePath);
          res.status(200).json({ state: "success", details: "your complaint has been registered" })
        }).catch(error => {
          //remove(img.tempFilePath);
          res.status(error.http_code).json({ state: "failure", details: error.message })
        })
      } else {
        //Upload complaint
        const upload = new Publicacion({ categoria, usuario, departamento, details, date })
        upload.save().then(() => {
          //remove(img.tempFilePath);
          res.status(200).json({ state: "success", details: "your complaint has been registered" })
        }).catch(error => {
          //remove(img.tempFilePath);
          res.status(500).json({ state: "failure", details: error.message })
        })
      }

    } else {
      res.status(400).json({ state: "error", details: "There is an error in the input parameters." })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const GetComplaints = async (req, res) => {
  const { Search, Departamento, Categorie } = req.query;
  try {
    const getcategorie = await Categoria.findOne({ name: Categorie })
    const configSearch = { details: { $regex: Search ?? "", $options: 'i' } };
    const configDepartamento = Departamento ? { ...configSearch, departamento: Departamento } : configSearch;
    const config = getcategorie !== null ? { ...configDepartamento, categoria: getcategorie._id } : configDepartamento;

    const results = await Publicacion.find(config).sort({ date: -1 }).populate(
      [
        {
          path: 'usuario',
          select: '_id username image.url',
          model: 'usuarios'
        }, {
          path: 'categoria',
          select: '-_id name',
          model: 'categorias'
        }
      ]
    )

    res.status(200).json({ state: "success", results })
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const GetComplaintsByUserId = async (req, res) => {
  const { Id } = req.query;
  try {
    if (Id) {
      const results = await Publicacion.find({ usuario: Id }).sort({ date: -1 }).populate(
        [
          {
            path: 'usuario',
            select: '-_id username image.url',
            model: 'usuarios'
          }, {
            path: 'categoria',
            select: '-_id name',
            model: 'categorias'
          }
        ]
      )
      res.status(200).json({ state: "success", results })
    } else {
      res.status(200).json({ state: "success", results: [] })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const UpdateComplaint = async (req, res) => {
  const { state } = req.body;
  const { Id } = req.params;
  if (state && Id) {
    try {
      Publicacion.findByIdAndUpdate(Id, { state }).then((data) => {
        res.status(200).json({ state: "success", details: `El estado de la denuncia paso de ser ${data.state} a estar en: ${state}` });
      }).catch(error => {
        res.status(error.http_code).json({ state: "failure", details: error.message })
      });
    } catch (error) {
      res.status(error.http_code).json({ state: "failure", details: error.message })
    }
  }
}

export const DeleteComplaint = (req, res) => {
  const { Id } = req.params;
  try {
    Publicacion.findByIdAndDelete(Id).then((data) => {
      if (data.image.public_id !== "") DeleteImage(data.image.public_id);
      Apoyo.deleteMany({ publicacion: Id }).then(() => console.log("the support of the complaint eliminated"))
      res.status(200).json({ state: "success", details: `La denuncia se elimino correctamete` });
    }).catch((error) => {
      res.status(error.http_code).json({ state: "failure", details: error.message })
    })
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}
