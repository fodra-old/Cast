import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CursoComponent } from './cast/curso/curso.component';
import { CategoriaComponent } from './cast/categoria/categoria.component';
import { NotFoundComponent } from './errors/not-found/not-found.component';
import { CursoTableComponent } from './cast/curso/curso-table/curso-table.component';

const routes: Routes = [
    { 
        path: 'curso', 
        component: CursoComponent,
    },
    { 
        path: 'curso/pesquisar', 
        component: CursoTableComponent 
    },
    { 
        path: 'categoria', 
        component: CategoriaComponent 
    },
    {
        path: '**',
        component: NotFoundComponent
    }
];

@NgModule({
    imports: [ 
        RouterModule.forRoot(routes) 
    ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }

