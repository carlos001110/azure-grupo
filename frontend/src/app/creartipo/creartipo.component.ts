import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { TipoRestService } from '../tipo-rest.service';
import { FormatoRestService } from '../formato-rest.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface TipoDTO {
  nombre: string;
  formatoIds: number[];
}

interface Formato {
  id: number;
  nombre: string;
}

@Component({
  selector: 'app-creartipo',
  standalone: true,
  templateUrl: './creartipo.component.html',
  styleUrls: ['./creartipo.component.css'],
  imports: [CommonModule, FormsModule]
})
export class CreartipoComponent implements OnInit {
  tipo: TipoDTO = { nombre: '', formatoIds: [] };
  formatosDisponibles: Formato[] = [];

  // 🔧 Agregamos las variables correctamente dentro de la clase
  @ViewChild('modalExito') modalExitoRef!: ElementRef;
  modalExito: any;
  nombreTipoCreado: string = '';
  formatosTipoCreado: string[] = [];

  constructor(
    private tipoRestService: TipoRestService,
    private formatoRestService: FormatoRestService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formatoRestService.listarFormatos().subscribe(
      (formatos) => {
        this.formatosDisponibles = formatos;
      },
      (error) => {
        console.error('❌ Error al obtener los formatos:', error);
      }
    );
  }

  volverAlInicio(): void {
    this.router.navigate(['/inicio']);
  }

  toggleFormato(id: number): void {
    const index = this.tipo.formatoIds.indexOf(id);
    if (index === -1) {
      this.tipo.formatoIds.push(id);
    } else {
      this.tipo.formatoIds.splice(index, 1);
    }
  }

  crearTipo(): void {
    if (!this.tipo.nombre.trim()) {
      alert('⚠️ El nombre del tipo es obligatorio.');
      return;
    }
  
    if (this.tipo.formatoIds.length === 0) {
      alert('⚠️ Debes seleccionar al menos un formato para crear el tipo.');
      return;
    }
  
    this.tipoRestService.crearTipo(this.tipo).subscribe(
      (res) => {
        this.nombreTipoCreado = this.tipo.nombre;
        this.formatosTipoCreado = this.formatosDisponibles
          .filter(f => this.tipo.formatoIds.includes(f.id))
          .map(f => f.nombre);
  
        this.tipo = { nombre: '', formatoIds: [] };
  
        this.modalExito = new (window as any).bootstrap.Modal(this.modalExitoRef.nativeElement);
        this.modalExito.show();
      },
      (error) => {
        console.error('❌ Error al crear el tipo:', error);
        alert('Error al crear el tipo.');
      }
    );
  }
  

  cerrarModalExito(): void {
    if (this.modalExito) {
      this.modalExito.hide();
    }
  }

  irAListaTipos(): void {
    if (this.modalExito) {
      this.modalExito.hide();
    }
    this.router.navigate(['/listatipos']);
  }
  
}
