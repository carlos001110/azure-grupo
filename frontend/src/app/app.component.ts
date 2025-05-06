import { Component } from '@angular/core';

import { RouterLink, RouterOutlet } from '@angular/router';
import { ListaitemsComponent } from './listaitems/listaitems.component';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'coleccion-app';
}
