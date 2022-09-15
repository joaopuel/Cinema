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
	ano_lancamento int not null
);

create table sessao(
	id bigint not null primary key auto_increment,
	data_sessao datetime not null,
	id_sala bigint not null,
	id_filme bigint not null,
	valor_inteira decimal(8,2) not null,
	valor_meia decimal(8,2) not null,
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

alter table filme add column cartaz varchar(500) not null; 

insert into pessoa(nome, sobrenome, telefone, cpf, saldo_carteira, login, senha)
values("Admin", "Admin", "4755964152", "12345678", 1544, "admin", "admin");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Star Wars", '1:29', "Uma aventura espacial malucona", "George Lucas", 1977, "https://http2.mlstatic.com/pster-cartaz-star-wars-o-despertar-da-forca-93x63-cm-D_NQ_NP_535221-MLB20729523909_052016-F.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Senhor dos Aneis", '2:45', "Meu precioso", "Alguém aí", 2000, "https://th.bing.com/th/id/R.7836a26a8af8e62de36d84216aaca7f7?rik=r6E4sJ12Oz%2bBBA&riu=http%3a%2f%2fbr.web.img3.acsta.net%2fmedias%2fnmedia%2f18%2f92%2f91%2f32%2f20224832.jpg&ehk=RUj44Sh2FpGHLd0Hxh9fmLNrCU1kn%2bf0AxWDyl7Ljl4%3d&risl=&pid=ImgRaw&r=0");

<<<<<<< HEAD
insert into cinema(nome, id_administrador, caixa)
values("Cinema Teste", 1, 1500);

insert into sala(nome, id_cinema)
values("Sala1", 1);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-15 19:30:00', 1, 1, 45, 25);

=======
insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Shrek", '1:29', "A gente já chegou?", "Fiona Burro da Silva", 2001,"https://cineclick-static.flixmedia.cloud/1280/69/1080x1620_1592580350.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Shrek Terceiro", '1:33', "E agora, já chegamos?", "Fiona Burro da Silva Pereira", 2007, "https://static.wikia.nocookie.net/vvikipedia/images/6/6b/Shrek_Terceiro.jpg/revision/latest?cb=20200413041407&path-prefix=pt");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Velozes e Furiosos 9", '2:23', "Cadê o Paul", "Justin", 2021,"https://p2.trrsf.com/image/fget/cf/1200/1200/filters:quality(85)/images.terra.com/2021/06/03/1745111479-fastandfurious9johncena.jpeg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Velozes e Furiosos: Desafio em Tóquio", '1:44', "Uau, ele leu o manual", "Justin", 2006, "https://media.fstatic.com/xWSwYWLRUbBbjWvh-OUoDYjCPjE=/210x312/smart/media/movies/covers/2017/12/poster_opt_3_HMJwX5m.jpg");

insert into cinema(nome, id_administrador, caixa)
values("Cinema Teste", 1, 1500);

insert into sala(nome, id_cinema)
values("Sala1", 1);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-15 19:30:00', 1, 6, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-15 20:00:00', 1, 3, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-16 19:30:00', 1, 5, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-16 20:00:00', 1, 5, 45, 25);

select sessoes0_.id_filme as id_filme5_11_0_, sessoes0_.id as id1_11_0_, sessoes0_.id as id1_11_1_, sessoes0_.data_sessao as data_ses2_11_1_, sessoes0_.id_filme as id_filme5_11_1_, sessoes0_.id_sala as id_sala6_11_1_, sessoes0_.valor_inteira as valor_in3_11_1_, sessoes0_.valor_meia as valor_me4_11_1_, salaentity1_.id as id1_10_2_, salaentity1_.id_cinema as id_cinem3_10_2_, salaentity1_.nome as nome2_10_2_, cinemaenti2_.id as id1_2_3_, cinemaenti2_.id_administrador as id_admin4_2_3_, cinemaenti2_.caixa as caixa2_2_3_, cinemaenti2_.nome as nome3_2_3_ from sessao sessoes0_ left outer join sala salaentity1_ on sessoes0_.id_sala=salaentity1_.id left outer join cinema cinemaenti2_ on salaentity1_.id_cinema=cinemaenti2_.id where sessoes0_.id_filme=1;
>>>>>>> main
