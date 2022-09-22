create database BancoCinema;

use bancocinema;

create table pessoa(
	id bigint not null primary key auto_increment,
	nome varchar(50) not null,
	sobrenome varchar(50) not null,
	telefone varchar(50) not null,
	cpf char(11) not null,
	saldo_carteira decimal(8,2),
	login varchar(100) unique key not null,
	senha varchar(50) not null
);

create table cinema(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null,
	id_administrador bigint not null,
	caixa decimal(8,2) not null,
	foreign key (id_administrador) references pessoa(id)
);

create table sala(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null,
	id_cinema bigint not null,
	foreign key (id_cinema) references cinema(id)
);

create table cadeira(
	id bigint not null primary key auto_increment,
	codigo varchar(250) not null,
	id_sala bigint not null,
	tipo_cadeira varchar(45) not null,
	fileira int not null,
	ordem_fileira int not null,
	foreign key (id_sala) references sala(id)
);

create table filme(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null,
	duracao time not null,
	sinopse varchar(500) not null,
	diretor varchar(250) not null,
	cartaz varchar(500) not null
);

create table sessao(
	id bigint not null primary key auto_increment,
	data_sessao datetime not null,
	id_sala bigint not null,
	id_filme bigint not null,
	valor_inteira decimal(8,2) not null,
	valor_meia decimal(8,2) not null,
	tipo_sessao varchar(45) not null,
	foreign key (id_sala) references sala(id),
	foreign key (id_filme) references filme(id)
);

create table ingresso(
	id_sessao bigint not null,
	id_pessoa bigint not null,
	id_cadeira bigint not null,
	data_compra datetime not null,
	primary key(id_sessao, id_pessoa, id_cadeira),
	foreign key (id_sessao) references sessao(id),
	foreign key (id_pessoa) references pessoa(id),
	foreign key (id_cadeira) references cadeira(id)
);

create table avaliacao(
	id bigint not null primary key auto_increment,
	rating decimal(8,2) not null,
	comentario varchar(500),
	data_avaliacao datetime not null,
	id_usuario bigint not null,
	id_filme bigint not null,
	foreign key(id_usuario) references pessoa(id),
	foreign key(id_filme) references filme(id)
);

create table genero(
	id bigint not null primary key auto_increment,
	nome varchar(250) not null
);

create table filme_genero(
	id_filme bigint not null,
	id_genero bigint not null,
	primary key(id_filme, id_genero),
	foreign key (id_filme) references filme(id),
	foreign key (id_genero) references genero(id)
);

ALTER TABLE cadeira RENAME COLUMN código TO codigo;
alter table filme add column cartaz varchar(500) not null;
alter table filme drop column ano_lancamento;
alter table sessao add column tipo_sessao varchar(45) not null;
