import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ListaitemsComponent } from './listaitems/listaitems.component';
import { BrowserModule } from '@angular/platform-browser';
import { InicioComponent } from './inicio/inicio.component';
import { FormularioitemComponent } from './formularioitem/formularioitem.component';
import { ListaprestamosComponent } from './listaprestamos/listaprestamos.component';
import { CrearprestamoComponent } from './crearprestamo/crearprestamo.component';
import { ModificaritemComponent } from './modificaritem/modificaritem.component';
import { EliminarItemComponent } from './eliminarItem/eliminarItem.component';
import { CreartipoComponent } from './creartipo/creartipo.component';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ListatipoComponent } from './listatipo/listatipo.component';
import { ListahistorialComponent } from './historialprestamo/listahistorial.component'; 


export const routes: Routes = [
  { path: '', redirectTo: '/inicio', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'listaitems', component: ListaitemsComponent },
  { path: 'formularioitem', component:FormularioitemComponent },
  { path: 'listaprestamos', component:ListaprestamosComponent },
  { path: 'crearprestamo', component:CrearprestamoComponent},
  {path: 'modificaritem',component:ModificaritemComponent},
  {path: 'eliminaritem',component:EliminarItemComponent},
  { path: 'creartipo', component: CreartipoComponent },
  { path: 'listatipos', component: ListatipoComponent },
  { path: 'listahistorial', component: ListahistorialComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes), 
    FormsModule,
    CommonModule,
    HttpClientModule,
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}