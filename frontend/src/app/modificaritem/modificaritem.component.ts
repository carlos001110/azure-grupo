import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { Item } from '../item';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemRestService } from '../item-rest.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-modificaritem',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './modificaritem.component.html',
  styleUrls: ['./modificaritem.component.css']
})
export class ModificaritemComponent implements OnInit {
  @Output() itemActualizado = new EventEmitter<Item>();
  
  @Input() item: Item = {
    id: 0,
    titulo: '',
    ubicacion: '',
    fechaAdquisicion: new Date().toISOString().split('T')[0],
    estado: true,
    formato: '',
    prestamo: null!,
    tipoId: 0
  };

  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private itemRestService: ItemRestService
  ) {}

  ngOnInit(): void {
    if (!this.item || !this.item.id) {
      console.warn("⚠️ El ítem recibido no tiene un ID válido:", this.item);
    }
  }

  actualizarItem(): void {
    this.itemRestService.actualizarItemDTO(this.item.id, this.item).subscribe({
      next: (data) => {
        console.log('✅ Item actualizado:', data);
        this.itemActualizado.emit(data);
        this.cerrarModal();
      },
      error: (error) => {
        console.error('❌ Error al actualizar el ítem:', error);
        this.errorMessage = 'Error al actualizar el ítem';
      }
    });
  }

  cerrarModal(): void {
    const modalElement = document.getElementById('modificarItemModal');
    if (modalElement) {
      const modalInstance = bootstrap.Modal.getInstance(modalElement);
      modalInstance?.hide();
    }
  }
}
