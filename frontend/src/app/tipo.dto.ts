// tipo.dto.ts
export interface TipoDTO {
    nombre: string;
    formatoIds: number[];
  }
  
  export interface TipoFormatoDTO {
    id: number;
    nombre: string;
    formatos: string[]; // nombres de formatos
  }
  