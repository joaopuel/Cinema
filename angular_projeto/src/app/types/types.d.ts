export type User = {
    id: number;
    login: string;
    password: string;
    authdata?: string;
}

export type Sessao = {
    id: number,
    nomeFilme: string,
    dataSessao: Date,
    valorInteira: number,
    taxaVip: number,
    idSala: number;
    nomeSala: string,
    idCinema: number,
    nomeCinema: string
}

export type Filme = {
    id: number,
    nome: string,
    cartaz: string,
    sessoes: Sessao[]
}