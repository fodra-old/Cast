import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { CursoComponent } from './curso.component';
import { MaterialModule } from 'src/app/material.module';
import { CategoriaModule } from '../categoria/categoria.module';

@NgModule({
    declarations: [CursoComponent],
    imports: [
        FormsModule,
        CommonModule,
        MaterialModule,
        CategoriaModule,
        HttpClientModule,
    ],
    exports: [
        CursoComponent,
        CategoriaModule
    ]
})
export class CursoModule {

}