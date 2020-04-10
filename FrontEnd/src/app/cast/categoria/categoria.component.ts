import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { CategoriaService } from './categoria.service';
import { Categoria } from './categoria';
import { CategoriaComponentShare } from './categoria.component.share';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categorias: Categoria[] = [];
  categoria: Categoria;

  constructor(
    private categoriaService: CategoriaService,
    private categoriaShare : CategoriaComponentShare) { }

  ngOnInit(): void {
    this.categoriaService.findAll().subscribe(
      (source : Categoria[])=>  {
        this.categorias = source;
      }
    );
    this.categoriaShare.currentCategoria.subscribe(
      (source: Categoria) => {
        this.categoria = source;
      }
    );
  }
  bind(categoria: Categoria) : void {

    this.categoriaShare.changeCategory(categoria);
  }
  compareObjects(o1: Categoria, o2: Categoria): boolean {
    if (typeof o1 === 'undefined' || typeof o2 === 'undefined') {  
      return false;
    }
    return o1.id === o2.id;
  }
}