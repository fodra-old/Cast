import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  title = 'FrontEnd';

  categorias : Object[] = []

  constructor(http : HttpClient) {
    http
      .get<Object[]>('http://localhost:8080/categorias')
      .subscribe(c => this.categorias = c);
  }
}
