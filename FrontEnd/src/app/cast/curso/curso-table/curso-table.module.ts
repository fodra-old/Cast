import { CommonModule } from '@angular/common';
import { NgModule, OnInit } from '@angular/core';

import { MaterialModule } from 'src/app/material.module';
import { CursoTableComponent } from './curso-table.component';
import { CategoriaModule } from '../../categoria/categoria.module';
import { CursoModule } from '../curso.module';

@NgModule({
  declarations: [CursoTableComponent],
  imports: [
    CursoModule,
    CommonModule,
    MaterialModule,
    CategoriaModule,
  ]
})
export class CursoTableModule {}
