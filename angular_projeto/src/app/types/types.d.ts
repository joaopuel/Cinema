export type User = {
    id: number;
    nome: string,
    sobrenome: string,
    telefone: string,
    cpf: string,
    saldoCarteira: number,
    login: string,
    senha: string,
    authdata?: string
}

export type Cadastro = {
    id?: number,
    nome: string,
    sobrenome: string,
    telefone: string,
    cpf: string,
    login: string,
    senha: string
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

export type Cadeira = {
    id: number,
    codigo: string,
    tipoCadeira: string,
    fileira: number,
    ordemFileira: number
}

export type CadeiraInfo = {
    id: number,
    codigo: string,
    tipoCadeira: string,
    fileira: number,
    ordemFileira: number,
    ocupado: boolean
}

export type Sala = {
    id: number,
    nome: string,
    numFileiras: number,
    tamFileiras: number,
    cadeiras: Cadeira[]
}

export type SalaInfo = {
    id: number,
    nome: string,
    numFileiras: number,
    tamFileiras: number,
    cadeiras: CadeiraInfo[]
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

export type SessaoInfo = {
    id: number,
    nomeFilme: string,
    cartazFilme: string;
    dataSessao: Date,
    valorInteira: number,
    taxaVip: number,
    tipoSessao: string,
    sala: SalaInfo,
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