import { Categoria } from "../Models/Model.js";

export const UploadCategory = async (req, res) => {
  const { name } = req.body;
  try {
    if (name) {
      const check = await Categoria.findOne({ name });
      if (check) {
        res.status(400).json({ state: "error", details: "Categorie already exists." })
      } else {
        const upload = new Categoria({ name })
        upload.save().then((data) => {
          res.status(200).json({ state: "success", details: `Categorie ${data.name} has been registered.` })
        }).catch((error) => {
          res.status(error.http_code).json({ state: "failure", details: error.message })
        });
      }
    } else {
      res.status(400).json({ state: "error", details: "There is an error in the input parameters." })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}

export const GetCategories = async (req, res) => {
  try {
    const categories = await Categoria.find({});
    res.status(200).json({ state: "success", categories })
  } catch (error) {
    res.status(error.http_code).json({ state: "failure", details: error.message })
  }
}