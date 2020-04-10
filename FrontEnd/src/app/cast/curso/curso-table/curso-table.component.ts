import { Component, OnInit, ViewChild, Directive, OnDestroy } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

import { CategoriaComponentShare } from '../../categoria/categoria.component.share';
import { Categoria } from '../../categoria/categoria';
import { CursoService } from '../curso.service';
import { Curso } from '../curso';
import { MatDialog } from '@angular/material/dialog';
import { CursoModalComponent } from '../curso-modal/curso-modal.component';
import { CursoComponentShare } from '../curso.component.share';

@Component({
  selector: 'app-curso-table',
  styleUrls: ['./curso-table.component.css'],
  templateUrl: './curso-table.component.html'
})
export class CursoTableComponent implements OnInit {

  @ViewChild(MatPaginator, {static: true}) paginator : MatPaginator;

  private classname = CursoTableComponent.name;
  colunas = [
    'id','categoria.descricao', 'assunto', 'inicio', 'fim','quantidadeAlunos', 'editar', 'eliminar'
  ];
  cursos: MatTableDataSource<Curso>;

  constructor(
    private cursoService : CursoService, 
    private categoriaShare: CategoriaComponentShare,
    private dialog : MatDialog) { }
  
  ngOnInit(): void {

    this.categoriaShare.currentCategoria.subscribe((source: Categoria) => {
      if (typeof source !== 'undefined') {
        this.cursoService.findByCategoria(source.id).subscribe(
          (result : Curso[]) =>  {
            this.cursos = new MatTableDataSource(result);
            this.cursos.paginator = this.paginator;
          }
        );
      }
    });
  }

  applyFilter(filterValue: string) {

    this.cursos.filter = filterValue.trim().toLowerCase();
  }

  deletarCurso(curso: any) {

    console.log(curso);
    this.cursoService.deletarCurso(curso[0].id).subscribe();
    const index = this.cursos.data.indexOf(curso[0]);
    this.cursos.data.splice(index, 1);
    this.cursos._updateChangeSubscription();
  }
  
  editarCurso(curso: any) {
    
    //Multiplas invocações
    const d = this.dialog.open(CursoModalComponent, {
      width: '80%',
      height: '80%',
      data: curso,
    });
  }
}
