import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CursoModalComponent } from './curso-modal.component';
import { CursoModule } from '../curso.module';
import { MaterialModule } from 'src/app/material.module';
import { CategoriaModule } from '../../categoria/categoria.module';

@NgModule({
  declarations: [CursoModalComponent],
  imports: [
    CursoModule,
    CommonModule,
    MaterialModule,
    CategoriaModule,
  ],
  exports: [
    CursoModalComponent
  ]
})
export class CursoModalModule { }
