import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Curso } from './curso';

const API = 'http://localhost:8080';

@Injectable({ providedIn: 'root' })
export class CursoService {

    constructor(private http: HttpClient) {

    }

    findByCategoria(id: number) { 
        return this.http.get<Curso[]>(API + '/curso?categoria=' + id);
    }

    deletarCurso(id: any) {
        return this.http.delete(API + '/curso?id=' + id);
    }

    save(curso:Curso) {
        if (curso.id == null) {
            return this.http.post(API + '/curso', curso);
        } else {
            return this.http.put(API + '/curso', curso);
        }
    }
    findById(id: string) {
        return this.http.get<Curso[]>(API + '/curso?id=' + id);
    }
}
