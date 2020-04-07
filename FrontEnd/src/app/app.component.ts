import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Categoria } from './entities/Categoria';
import { CategoriaService } from './entities/categoria.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  title = 'FrontEnd';

  constructor(private categoriaServive : CategoriaService) { }

  ngOnInit(): void {

    this.categoriaServive
      .findAll()
      .subscribe(c => {console.log(c)});
  }
}
