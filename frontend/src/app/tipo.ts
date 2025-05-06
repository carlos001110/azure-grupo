import { Item } from "./item";

export class Tipo {

    constructor(
        public id:number,
        public nombre:string,
        public formato:string,
        public items:Item[],
    ){}

    
}
