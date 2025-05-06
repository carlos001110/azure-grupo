
export class Prestamo {

    constructor(
        public id: number, 
        public itemId:number,
        public persona:string,
        public fechaPrestamo:Date ,
        public fechaPrevistaDevolucion:Date ,
        public fechaDevolucion:Date,
        public activo:boolean,
    ){}
}
