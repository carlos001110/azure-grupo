import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { PrestamoRestService } from '../prestamo-rest.service';
import { ItemRestService } from '../item-rest.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Location } from '@angular/common';

declare const bootstrap: any;

interface Prestamo {
  itemId: number;
  persona: string;
  fechaPrevistaDevolucion: string;
}

interface ItemResumen {
  id: number;
  titulo: string;
}

@Component({
  selector: 'app-crearprestamo',
  templateUrl: './crearprestamo.component.html',
  styleUrls: ['./crearprestamo.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class CrearprestamoComponent implements OnInit {

  prestamo: Prestamo = {
    itemId: 0,
    persona: '',
    fechaPrevistaDevolucion: ''
  };

  itemsDisponibles: ItemResumen[] = [];
  nombreItemSeleccionado: string = '';
  modalExito: any;

  @ViewChild('confirmarModalElement') confirmarModalElement!: ElementRef;
  @ViewChild('exitoModalElement') exitoModalElement!: ElementRef;

  constructor(
    private prestamoRestService: PrestamoRestService,
    private itemRestService: ItemRestService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.itemRestService.listarItemsDisponibles().subscribe({
      next: (items) => this.itemsDisponibles = items,
      error: (err) => console.error('Error al cargar ítems disponibles:', err)
    });
  }

  abrirModal(): void {
    const seleccionado = this.itemsDisponibles.find(item => item.id === Number(this.prestamo.itemId));
    this.nombreItemSeleccionado = seleccionado ? seleccionado.titulo : 'No seleccionado';

    const modal = new bootstrap.Modal(this.confirmarModalElement.nativeElement);
    modal.show();
  }

  confirmarRegistro(): void {
    this.prestamoRestService.crearPrestamo(this.prestamo).subscribe({
      next: () => {
        // Cierra el modal de confirmación
        bootstrap.Modal.getInstance(this.confirmarModalElement.nativeElement)?.hide();

        // Abre modal de éxito
        this.modalExito = new bootstrap.Modal(this.exitoModalElement.nativeElement);
        this.modalExito.show();
      },
      error: (err) => console.error('Error al crear el préstamo:', err)
    });
  }

  cerrarModalExito(): void {
    if (this.modalExito) {
      this.modalExito.hide();
    }
  
    // Limpiar el formulario
    this.prestamo = {
      itemId: 0,
      persona: '',
      fechaPrevistaDevolucion: ''
    };
    this.nombreItemSeleccionado = '';
  }
  
  goBack(): void {
    this.location.back();
  }
  
}
