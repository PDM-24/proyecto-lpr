import mongoose from "mongoose"
import mongoosePaginate from "mongoose-paginate-v2"

const Schema = mongoose.Schema;
const ObjectId = Schema.Types.ObjectId;

const usuario = new Schema({
  image: { type: Object, required: true, default: { public_id: "", url: "" } },
  username: { type: String, required: true, unique: true },
  name: { type: String, required: true },
  surname: { type: String, required: true },
  email: { type: String, required: true, unique: true },
  birth: { type: Date, required: true },
  password: { type: String, required: true },
  rol: { type: String, required: true },
  state: { type: Boolean, required: true, default: true }
}, { versionKey: false }).plugin(mongoosePaginate);

const publicacion = new Schema({
  categoria: { type: ObjectId, required: true, ref: 'categorias' },
  usuario: { type: ObjectId, required: true, ref: 'usuarios' },
  departamento: { type: String, required: true },
  image: { type: Object, required: true, default: { public_id: "", url: "" } },
  details: { type: String, required: true },
  date: { type: Date, required: true },
  state: { type: String, required: true, default: "En revision" }
}, { versionKey: false }).plugin(mongoosePaginate);

const apoyo = new Schema({
  usuario: { type: ObjectId, required: true, ref: 'usuarios' },
  publicacion: { type: ObjectId, required: true, ref: 'publicaciones' },
  codigo: { type: String, required: true }
}, { versionKey: false }).plugin(mongoosePaginate);

const categoria = new Schema({
  name: { type: String, required: true }
}, { versionKey: false })

export const Usuario = mongoose.model('usuarios', usuario);
export const Publicacion = mongoose.model('publicaciones', publicacion);
export const Apoyo = mongoose.model('apoyos', apoyo);
export const Categoria = mongoose.model('categorias', categoria);