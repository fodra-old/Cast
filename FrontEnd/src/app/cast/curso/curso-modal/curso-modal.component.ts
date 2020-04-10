import { Component, OnInit, Inject, AfterViewInit, AfterContentInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Curso } from '../curso';
import { CursoComponentShare } from '../curso.component.share';
import { CategoriaComponentShare } from '../../categoria/categoria.component.share';
import { CursoTableComponent } from '../curso-table/curso-table.component';

@Component({
  selector: 'app-curso-modal',
  templateUrl: './curso-modal.component.html',
  styleUrls: ['./curso-modal.component.css']
})
export class CursoModalComponent implements OnInit {

  private classname = CursoModalComponent.name;
  private target : string[];

  constructor(
    private cursoShare : CursoComponentShare,
    public dialog : MatDialogRef<CursoModalComponent>,
    @Inject(MAT_DIALOG_DATA) private curso: Curso) { 

  }

  onNoClick() : void {

    this.cursoShare.changeCurso(undefined);
    this.dialog.close();
  }
  ngOnInit(): void {
    
    this.cursoShare.changeCurso(this.curso);
  }
}