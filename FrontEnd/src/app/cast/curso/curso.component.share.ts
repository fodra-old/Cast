import { BehaviorSubject, Observable } from 'rxjs';
import { Curso } from './curso';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class CursoComponentShare {

    source: Curso;
    private cursoSource = new BehaviorSubject<Curso>(this.source);
    currentCurso = this.cursoSource.asObservable();

    constructor(){}

    changeCurso(curso : Curso) : void {
        this.cursoSource.next(curso);
    }
    getCurrentCurso() : Observable<Curso> {
        return this.currentCurso;
    }
    reiniciarCurso() {
        this.cursoSource.next(undefined);
    }
}