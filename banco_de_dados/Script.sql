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

create table 
