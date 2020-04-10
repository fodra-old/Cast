import { Component, OnInit, ViewChild, AfterViewInit, Input } from '@angular/core';  

import { Curso } from './curso';
import { CursoService } from './curso.service';
import { Categoria } from '../categoria/categoria';
import { CategoriaComponentShare } from '../categoria/categoria.component.share';
import { CursoComponentShare } from './curso.component.share';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit  {

  categoria: Categoria;
  curso: Curso = <Curso>{};

  constructor(
    private cursoService : CursoService,
    private categoriaShare : CategoriaComponentShare,
    private cursoShare : CursoComponentShare) { }

  ngOnInit(): void {

    this.cursoShare.currentCurso.subscribe(
      (source : Curso) => {
        if (source !== undefined) {
          this.curso = source[0];
          var dt = new DatePipe('en-US');
          this.curso.str_inicio = dt.transform(source[0].inicio, 'yyyy-MM-dd');
          this.curso.str_fim = dt.transform(source[0].fim, 'yyyy-MM-dd');
          this.categoriaShare.changeCategory(this.curso.categoria);
        }
      });
    this.categoriaShare.currentCategoria.subscribe(
      (categoria : Categoria) => {
        this.categoria = categoria
      });
  }
  
  addOrUpdateCurso() {

    this.curso.categoria = this.categoria;
    this.curso.inicio = new Date(this.curso.str_inicio);
    this.curso.fim = new Date(this.curso.str_fim);
    this.cursoService.save(this.curso).subscribe();
    
    this.categoriaShare.reiniciarCategoria();
    this.curso = <Curso>{};
  }
}