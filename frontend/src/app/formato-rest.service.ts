import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.prod';

interface Formato {
  id: number;
  nombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class FormatoRestService {

  private apiUrl = `${environment.apiUrl}/formatos`;

  constructor(private http: HttpClient) {}

  listarFormatos(): Observable<Formato[]> {
    return this.http.get<Formato[]>(`${this.apiUrl}/listar`);
  }
}
