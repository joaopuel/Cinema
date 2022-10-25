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

export type PessoaPayloadDTO = {
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

export type GenerosFilmeDTO = {
    idFilme: number,
    idGeneros: number[]
}

export type Avaliacao = {
    id: number,
    nomeUsuario: string,
    sobrenomeUsuario: string,
    nota: number,
    comentario: string | null,
    dataAvaliacao: Date
}

export type AvaliacaoPayload = {
    idFilme: number,
    nota: number,
    comentario: string | null
}


export type Cadeira = {
    id: number | null,
    codigo: string,
    tipoCadeira: string,
    fileira: number,
    ordemFileira: number
}

export type CadeiraPayload = {
    idSala: number,
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
    tipoSessao: string,
    idSala: number;
    nomeSala: string,
    idCinema: number,
    nomeCinema: string,
    logradouroCinema: string,
    numeroCinema: number
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

export type SessaoPayload = {
    dataSessao: Date,
    idFilme: number;
    idSala: number,
    valorInteira: number,
    taxaVip: number,
    tipoSessao: string
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

export type FilmePayload = {
    nome: string,
    duracao: TimeRanges,
    sinopse: string,
    diretor: string,
    cartaz: string,
    trailer: string
}

export type LoginDTO = {
    username: string;
    password: string;
}

export type Ingresso = {
    id: number,
    dataCompra: Date,
    idPessoa: number,
    nomeFilme: string,
    cartazFilme: string,
    nomeCinema: string,
    valorIngresso: number,
    meiaEntrada: boolean,
    sessao: Sessao,
    cadeira: Cadeira
}

export type IngressoPayload = {
    idSessao: number,
    idCadeira: number,
    meiaEntrada: boolean
}

export type Cinema = {
    id: number,
    nome: string,
    logradouro: string,
    numero: number
}

export type CinemaInfo = {
    id: number,
    nome: string,
    idAdministrador: number,
    caixa: number,
    logradouro: string,
    numero: number,
    salas: Sala[],
    registroCaixa: Registro[]
}

export type Registro = {
    id: number,
    operacao: string,
    valor: number,
    descricao: string,
    nomeCinema: string,
    dataOperacao: Date
}