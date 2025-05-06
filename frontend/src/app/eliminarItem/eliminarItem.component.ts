import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Item } from '../item';

@Component({
  selector: 'app-eliminar-item',
  templateUrl: './eliminar-item.component.html',
})
export class EliminarItemComponent {
  @Input() itemAEliminar: Item | null = null;
  @Output() onConfirmarEliminar = new EventEmitter<void>();

  confirmarEliminar(): void {
    this.onConfirmarEliminar.emit();
  }
}
