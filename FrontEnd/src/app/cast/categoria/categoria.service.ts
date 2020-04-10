import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Categoria } from "./categoria";

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class CategoriaService {

    constructor(private http: HttpClient) {

    }
    findAll() {
        return this.http.get<Categoria[]>(API + '/categoria/all');
    }
    
    findById(id: number) {
        return this.http.get<Categoria>(API + '/categoria/id=' + id);
    }
}
