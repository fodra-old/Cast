import { Categoria } from '../categoria/categoria';

export interface Curso {

    id: number;
    assunto: string;
    inicio: Date;
    fim: Date;
    quantidadeAlunos: number;
    categoria: Categoria;
    
    str_inicio: string;
    str_fim: string;
}