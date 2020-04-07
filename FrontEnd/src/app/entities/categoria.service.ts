import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Categoria } from './categoria';

var API : 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class CategoriaService {

    constructor(private http : HttpClient) {}

    findAll() {
        
        return this.http.get<Categoria>(API + '/categorias');
    }
}