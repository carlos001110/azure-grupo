import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Prestamo } from './prestamo';
import { map, Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { PrestamoData } from './prestamo-data';
import { Item } from './item';
import { environment } from '../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PrestamoRestService {

  //private apiUrl = "http://localhost:4200/api/prestamo";
  private apiUrl = `${environment.apiUrl}/prestamo`;

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  crearPrestamo(prestamo: { itemId: number, persona: string, fechaPrevistaDevolucion: string }): Observable<any> {
    return this.http.post(this.apiUrl, null, {
      params: {
        itemId: prestamo.itemId,
        persona: prestamo.persona,
        fechaPrevistaDevolucion: prestamo.fechaPrevistaDevolucion,
      },
    });
  }
  
  devolverItem(id: number): Observable<Prestamo> {
    const url = `${this.apiUrl}/devolver/${id}`;
    return this.http.put<Prestamo>(url, null, { 
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    });
  }

  /**
   * Lista los préstamos activos, permitiendo filtrar opcionalmente por persona (subcadena)
   * y por un rango de fechas (fechaDesde y fechaHasta) en formato ISO (yyyy-MM-dd).
   */
  listarPrestamosActivos(persona?: string, fechaDesde?: string, fechaHasta?: string): Observable<Prestamo[]> {
    let params = new HttpParams();

    // Sólo agregar el parámetro si la cadena no está vacía
    if (persona && persona.trim() !== '') {
      params = params.set('persona', persona.trim());
    }
    if (fechaDesde && fechaDesde.trim() !== '') {
      params = params.set('fechaDesde', fechaDesde);
    }
    if (fechaHasta && fechaHasta.trim() !== '') {
      params = params.set('fechaHasta', fechaHasta);
    }
    
    // Log para depuración: ver qué parámetros se envían
    console.log("Parametros para listarPrestamosActivos:", {
      persona: persona && persona.trim() !== '' ? persona.trim() : null,
      fechaDesde: (fechaDesde && fechaDesde.trim() !== '') ? fechaDesde : null,
      fechaHasta: (fechaHasta && fechaHasta.trim() !== '') ? fechaHasta : null
    });
    
    return this.http.get<PrestamoData[]>(`${this.apiUrl}/activos`, { params }).pipe(
      map(prestamos => {
        // Log de la respuesta sin mapear para ver qué devuelve el backend
        console.log("Respuesta cruda de prestamos:", prestamos);
        return prestamos.map(prestamo => {
          if (!prestamo.id) {
            console.error('Error: El préstamo no tiene id.', prestamo);
            return null;
          }
  
          let fechaPrestamo = this.convertirFecha(prestamo.fechaPrestamo);
          let fechaDevolucion = this.convertirFecha(prestamo.fechaDevolucion);
          let fechaPrevistaDevolucion = this.convertirFecha(prestamo.fechaPrevistaDevolucion);
  
          return new Prestamo(
            prestamo.id,
            prestamo.itemId,
            prestamo.persona,
            fechaPrestamo ?? new Date(),
            fechaPrevistaDevolucion ?? new Date(),
            fechaDevolucion ?? new Date(),
            prestamo.activo
          );
        }).filter(prestamo => prestamo !== null);
      })
    );
  }
  
  public convertirFecha(fecha: string | null): Date | null {
    if (fecha && fecha.trim() !== '') {
      let trozosFechas: string[] = fecha.split("-");
      if (trozosFechas.length === 3) {
        return new Date(parseInt(trozosFechas[0]), parseInt(trozosFechas[1]) - 1, parseInt(trozosFechas[2]));
      }
    }
    return null;
  }

  private convertirFechaCrear(fecha: string | null): Date | null {
    if (fecha && typeof fecha === 'string' && fecha.trim() !== '') {
      let trozosFechas: string[] = fecha.split("-");
      if (trozosFechas.length === 3) {
        return new Date(parseInt(trozosFechas[0]), parseInt(trozosFechas[1]) - 1, parseInt(trozosFechas[2]));
      }
    }
    return null;
  }

  obtenerHistorial(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/historial`);
  }
}
