import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import * as bootstrap from 'bootstrap';
import { HttpClient } from '@angular/common/http';
import { Item } from '../item';
import { ItemRestService } from '../item-rest.service';
import { TipoRestService } from '../tipo-rest.service';
import { ItemDTO } from '../ItemDTO';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { environment } from '../../environments/environment.prod';

@Component({
  selector: 'app-formularioitem',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './formularioitem.component.html',
  styleUrls: ['./formularioitem.component.css']
})
export class FormularioitemComponent implements OnInit {
  @ViewChild('formRef') formRef!: NgForm;
  @ViewChild('fileInput') fileInput!: any;

  imagenPreview: string | null = null;
  imagenFile: File | null = null;
  urlImagenBlob: string | null = null;
  cargando = false;

  item: Item = {
    id: 0,
    titulo: '',
    ubicacion: '',
    fechaAdquisicion: new Date().toISOString().split('T')[0], 
    estado: true,
    tipoId: 0,
    formato: '',
    prestamo: null as any,
    urlImagen: '', // <-- Agregado
  };

  resetearFormulario(): void {
    this.item = {
      id: 0,
      titulo: '',
      ubicacion: '',
      fechaAdquisicion: new Date().toISOString().split('T')[0],
      estado: true,
      tipoId: 0,
      formato: '',
      prestamo: null as any,
      urlImagen: '' // 👈 necesario para evitar que sea undefined
    };
      this.formatosDisponibles = [];
      this.imagenPreview = null;
      this.imagenFile = null;

      if (this.fileInput) {
        this.fileInput.nativeElement.value = '';
      }
      
      const modalElement = document.getElementById('itemCreadoModal');
      const modal = bootstrap.Modal.getInstance(modalElement!);
      modal?.hide();
  }
  

  tipos: any[] = [];
  formatosDisponibles: string[] = [];
  itemCreado: any = null;

  constructor(
    private itemRestService: ItemRestService,
    private tipoRestService: TipoRestService,
    private router: Router,
    private http: HttpClient,
  ) {}

  ngOnInit(): void {
    this.tipoRestService.listarTipos().subscribe({
      next: (data) => {
        this.tipos = data;
      },
      error: (err) => {
        console.error('Error al cargar tipos:', err);
      }
    });
  }

  cargarFormatos(): void {
    const tipoSeleccionado = this.tipos.find(t => t.id === this.item.tipoId);
    this.formatosDisponibles = tipoSeleccionado ? tipoSeleccionado.formatos : [];
    this.item.formato = '';
  }

  quitarImagen(): void {
    this.imagenPreview = null;
    this.imagenFile = null;
    this.item.urlImagen = '';

    if (this.fileInput) {
      this.fileInput.nativeElement.value = ''; // Reinicia el input
    }
  }
  

  crearItem(): void {
    if (this.cargando) return; // Evita doble clic
    this.cargando = true;

    const nuevoItem: ItemDTO = {
      titulo: this.item.titulo,
      ubicacion: this.item.ubicacion,
      tipoId: this.item.tipoId,
      formato: this.item.formato,
      urlImagen: this.item.urlImagen, // <-- incluido
    };

    this.itemRestService.crearItem(nuevoItem).subscribe({
      next: (data) => {
        this.itemCreado = data;
        const modal = new bootstrap.Modal(document.getElementById('itemCreadoModal')!);
        this.cargando = false; // <== FALTA ESTO
        modal.show();
      },
      error: (err) => {
        console.error('Error al crear el artÃ­culo:', err);
      }
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;
  
    this.imagenFile = input.files[0];
    this.imagenPreview = URL.createObjectURL(this.imagenFile);
  
    const formData = new FormData();
    formData.append('file', this.imagenFile);
  
    this.http.post<any>(`${environment.apiUrl}/item/procesar-imagen`, formData).subscribe({
      next: (data) => {
        this.item.titulo = data.titulo || '';
        this.item.urlImagen = data.urlImagen || '';
      },
      error: (err) => {
        console.error('❌ Error al procesar imagen:', err);
        alert('Error al leer el título desde la imagen.');
      }
    });
  }
  
}