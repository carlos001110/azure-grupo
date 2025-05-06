import { Component, OnInit } from '@angular/core';
import { TipoRestService } from '../tipo-rest.service';
import { TipoFormatoDTO } from '../tipo.dto';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
declare var bootstrap: any;
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-listatipo',
  standalone: true,
  templateUrl: './listatipo.component.html',
  styleUrls: ['./listatipo.component.css'],
  imports: [CommonModule, FormsModule, RouterModule]
})
export class ListatipoComponent implements OnInit {
  tipos: TipoFormatoDTO[] = [];
  tipoSeleccionado: TipoFormatoDTO = { id: 0, nombre: '', formatos: [] };

  constructor(private tipoService: TipoRestService,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.cargarTipos();
  }

  cargarTipos(): void {
    this.tipoService.listarTipos().subscribe(
      (data) => {
        this.tipos = data;
      },
      (error) => {
        console.error('Error al obtener los tipos:', error);
      }
    );
  }

  abrirModalActualizar(tipo: TipoFormatoDTO): void {
    this.tipoSeleccionado = { ...tipo };
    const modal = new bootstrap.Modal(document.getElementById('modalActualizar')!);
    modal.show();
  }

  confirmarActualizar(): void {
    this.tipoService.actualizarTipo(this.tipoSeleccionado.id, this.tipoSeleccionado.nombre).subscribe(
      () => {
        this.cargarTipos();
        bootstrap.Modal.getInstance(document.getElementById('modalActualizar')!)?.hide();
      },
      (error) => {
        console.error('Error al actualizar tipo:', error);
      }
    );
  }

  abrirModalEliminar(tipo: TipoFormatoDTO): void {
    // Ocultar el contenido del modal temporalmente
    this.tipoSeleccionado = { id: 0, nombre: '', formatos: [] };
    this.cdr.detectChanges(); //Forzamos a Angular a vaciarlo

    // Ahora cargamos el tipo real
    setTimeout(() => {
      this.tipoSeleccionado = { ...tipo }; // Cargamos el tipo a eliminar
      this.cdr.detectChanges(); // Forzamos a Angular a refrescar el DOM

      const modalElement = document.getElementById('modalEliminar');
      if (modalElement) {
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
      }
    }, 0);
  }

  confirmarEliminar(): void {
    this.tipoService.eliminarTipo(this.tipoSeleccionado.id).subscribe({
      next: () => {
        this.cargarTipos();
        bootstrap.Modal.getInstance(document.getElementById('modalEliminar')!)?.hide();
      },
      error: (err) => {
        bootstrap.Modal.getInstance(document.getElementById('modalEliminar')!)?.hide();
  
        if (err.status === 500 || err.status === 409) {
          // Mostramos modal de error
          const modalError = new bootstrap.Modal(document.getElementById('modalErrorEliminar')!);
          modalError.show();
        } else {
          console.error('Error al eliminar tipo:', err);
        }
      }
    });
  }

  paginaActual: number = 0;
tamanoPagina: number = 5;

get tiposPaginados(): TipoFormatoDTO[] {
  const inicio = this.paginaActual * this.tamanoPagina;
  return this.tipos.slice(inicio, inicio + this.tamanoPagina);
}

get totalPaginas(): number {
  return Math.ceil(this.tipos.length / this.tamanoPagina);
}

cambiarPagina(index: number): void {
  this.paginaActual = index;
}

avanzarPagina(): void {
  if (this.paginaActual < this.totalPaginas - 1) {
    this.paginaActual++;
  }
}

retrocederPagina(): void {
  if (this.paginaActual > 0) {
    this.paginaActual--;
  }
}

  
  
}
