export type User = {
    id: number;
    login: string;
    password: string;
    authdata?: string;
}

export type Genero = {
    id: number,
    nome: string
}

export type Avaliacao = {
    id: number,
    nomeUsuario: string,
    sobrenomeUsuario: string,
    nota: number,
    comentario: string,
    dataAvaliacao: Date
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

export type FilmeInfo = {
    id: number,
    nome: string,
    duracao: TimeRanges,
    sinopse: string,
    diretor: string,
    cartaz: string,
    trailer: string,
    mediaNotas: number,
    generos: Genero[],
    sessoes: Sessao[],
    avaliacoes: Avaliacao[]
}