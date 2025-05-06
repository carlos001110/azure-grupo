import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { PrestamoRestService } from '../prestamo-rest.service';
import { Prestamo } from '../prestamo';

@Component({
  standalone: true,
  selector: 'app-listahistorial',
  templateUrl: './listahistorial.component.html',
  imports: [CommonModule, RouterModule],
})

export class ListahistorialComponent implements OnInit {

  historial: Prestamo[] = [];

  constructor(
    private prestamoService: PrestamoRestService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) { }

  paginaActual: number = 0;
  tamanoPagina: number = 5;

  ngOnInit(): void {
    this.prestamoService.obtenerHistorial().subscribe({
      next: (data) => this.historial = data,
      error: (err) => console.error('Error al cargar historial:', err)
    });
  }

  volver(): void {
    this.router.navigate(['/inicio']);
  }

  
  get historialPaginado(): Prestamo[] {
    const inicio = this.paginaActual * this.tamanoPagina;
    return this.historial.slice(inicio, inicio + this.tamanoPagina);
  }
  
  get totalPaginas(): number {
    return Math.ceil(this.historial.length / this.tamanoPagina);
  }
  
  cambiarPagina(index: number): void {
    this.paginaActual = index;
    this.cdr.detectChanges();
  }
  
  avanzarPagina(): void {
    if (this.paginaActual < this.totalPaginas - 1) {
      this.paginaActual++;
      this.cdr.detectChanges();
    }
  }
  
  retrocederPagina(): void {
    if (this.paginaActual > 0) {
      this.paginaActual--;
      this.cdr.detectChanges();
    }
  }
  
  }
  
  


