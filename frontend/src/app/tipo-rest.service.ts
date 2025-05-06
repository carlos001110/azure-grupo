import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoDTO, TipoFormatoDTO } from './tipo.dto';
import { environment } from '../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class TipoRestService {

  //private apiUrl = 'http://localhost:4200/api/tipo';
  private apiUrl = `${environment.apiUrl}/tipo`;

  constructor(private http: HttpClient) {}

  crearTipo(tipo: TipoDTO): Observable<TipoDTO> {
    return this.http.post<TipoDTO>(this.apiUrl, tipo);
  }

  listarTipos(): Observable<TipoFormatoDTO[]> {
    return this.http.get<TipoFormatoDTO[]>(this.apiUrl); // ← sin /listar
  }

  actualizarTipo(id: number, nuevoNombre: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, nuevoNombre, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
  
  eliminarTipo(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  obtenerTipos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
  
}
