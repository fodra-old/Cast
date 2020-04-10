import { Injectable } from '@angular/core';

import { BehaviorSubject } from 'rxjs';

import { Categoria } from './categoria';


@Injectable({ providedIn: 'root' })
export class CategoriaComponentShare {

    c: Categoria;
    private categoriaSource = new BehaviorSubject<Categoria>(this.c);
    currentCategoria = this.categoriaSource.asObservable();

    constructor() { }

    changeCategory(categoria : Categoria) {
        this.categoriaSource.next(categoria);
    }

    reiniciarCategoria() {
        this.categoriaSource.next(undefined);
    }
}