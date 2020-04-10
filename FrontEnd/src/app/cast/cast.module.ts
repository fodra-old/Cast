import { NgModule } from '@angular/core';
import { CursoModule } from './curso/curso.module';
import { CategoriaModule } from './categoria/categoria.module';
import { CursoTableModule } from './curso/curso-table/curso-table.module';
import { CursoModalModule } from './curso/curso-modal/curso-modal.module';

@NgModule({
    imports: [
        CursoModule,
        CategoriaModule,
        CursoTableModule,
        CursoModalModule,
    ]
})
export class CastModule {

}