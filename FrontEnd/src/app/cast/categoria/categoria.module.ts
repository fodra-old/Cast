import { NgModule } from '@angular/core';
import { CategoriaComponent } from './categoria.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from 'src/app/material.module';
import { FormsModule } from '@angular/forms';

 @NgModule({
     declarations: [CategoriaComponent],
     imports: [
        CommonModule,
        HttpClientModule,
        MaterialModule,
        FormsModule
    ],exports: [
        CategoriaComponent,
    ]
 })
 export class CategoriaModule{

 }