import { Prestamo } from "./prestamo";
import { Tipo } from "./tipo";

export class Item {
    constructor(
      public id: number,
      public titulo: string,
      public ubicacion: string,
      public fechaAdquisicion: string,
      public estado: boolean,
      public tipoId: number,
      public formato: string,
      public prestamo: Prestamo,
      public tipo?: string,
      public  urlImagen?: string,
    ) {}
  }
  