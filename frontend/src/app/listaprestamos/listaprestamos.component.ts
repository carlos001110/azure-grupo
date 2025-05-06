import { Component, Inject, LOCALE_ID } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { Prestamo } from '../prestamo';
import { PrestamoRestService } from '../prestamo-rest.service';
import { CommonModule, DatePipe, formatDate } from '@angular/common';
import { Location } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-listaprestamos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listaprestamos.component.html',
  styleUrls: ['./listaprestamos.component.css']
})
export class ListaprestamosComponent {
  listaPrestamosActivos: Prestamo[] = [];
  showModal: boolean = false; // Controla la visibilidad del modal
  prestamoIdToReturn: number | null = null; // ID del préstamo a devolver

  // Variables de filtro
  personaFiltro: string = '';
  fechaDesde: string = ''; // Formato ISO: yyyy-MM-dd
  fechaHasta: string = '';

  constructor(
    private prestamoRestService: PrestamoRestService,
    private datePipe: DatePipe,
    @Inject(LOCALE_ID) private locale: string,
    private cdr: ChangeDetectorRef,
    private location: Location
  ) {}

  goBack(): void {
    this.location.back();
  }

  ngOnInit(): void {
    this.cargarPrestamos();
  }

  cargarPrestamos(): void {
    // Se llaman los filtros; si no se completan, se envían valores vacíos, 
    // y el backend debe tratarlos como nulos.
    this.prestamoRestService
      .listarPrestamosActivos(this.personaFiltro, this.fechaDesde, this.fechaHasta)
      .subscribe((datos: Prestamo[]) => {
        this.listaPrestamosActivos = datos;
      });
  }

  // Método para disparar la búsqueda con filtros
  buscarPrestamos(): void {
    this.cargarPrestamos();
  }

  trackById(index: number, item: any): number {
    return item.id;
  }

  // Abre el modal de devolución
  openModal(id: number): void {
    this.prestamoIdToReturn = id;
    this.showModal = true;
  }

  // Cierra el modal
  closeModal(): void {
    this.showModal = false;
    this.prestamoIdToReturn = null;
  }

  // Confirmar la devolución y actualizar la lista
  confirmarDevolucion(): void {
    if (this.prestamoIdToReturn !== null) {
      this.prestamoRestService.devolverItem(this.prestamoIdToReturn).subscribe(
        (datos) => {
          console.log('Préstamo devuelto con éxito', datos);
          this.listaPrestamosActivos = this.listaPrestamosActivos.filter(
            (prestamo) => prestamo.id !== this.prestamoIdToReturn
          );
          this.cdr.detectChanges();
          this.closeModal();
        },
        (error) => {
          console.error('Error al devolver el préstamo:', error);
        }
      );
    }
  }
}
