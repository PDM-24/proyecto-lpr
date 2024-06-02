import { Types } from "mongoose";
import { Apoyo } from "../Models/Model.js";
import mongoose from "mongoose";

export const SupportComplaint = async (req, res) => {
  const { user, complaint, codigo } = req.body;
  if (user && complaint && codigo) {
    try {
      const usuario = new mongoose.Types.ObjectId(user);
      const publicacion = new mongoose.Types.ObjectId(complaint);
      const checksupport = await Apoyo.findOne({ usuario, publicacion })
      if (!checksupport) {
        const upload = new Apoyo({ usuario, publicacion, codigo });
        upload.save().then(() => {
          res.status(202).json({ state: 'success', details: "El apoyo a la denuncia se ha realizado con éxito" })
        })
      } else {
        res.status(202).json({ state: 'nosupport', details: "Usted ya apoyo esta denuncia." })
      }
    } catch (error) {
      res.status(error.http_code).json({ state: 'failure', details: error.message })
    }
  } else {
    res.status(404).json({ state: 'error', details: "There is an error in the input parameters" })
  }
}
export const GetSupportByComplaint = async (req, res) => {
  const { Id } = req.params;
  try {
    if (Id) {
      const publicacion = new Types.ObjectId(Id);
      const support = await Apoyo.find({ publicacion }).count();
      res.status(200).json(support);
    } else {
      res.status(404).json({ state: 'error', details: "There is an error in the input parameters" })
    }
  } catch (error) {
    res.status(error.http_code).json({ state: 'failure', details: error.message })
  }
}