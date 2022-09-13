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
values("Star Wars: O Despertar da Força", '1:29', "A queda de Darth Vader e do Império levou ao surgimento de uma nova força sombria: a Primeira Ordem. Eles procuram o jedi Luke Skywalker, desaparecido. A resistência tenta desesperadamente encontrá-lo antes para salvar a galáxia.
", "George Lucas", 1977, "https://static.wikia.nocookie.net/ptstarwars/images/d/d5/P%C3%B4sterVII.jpg/revision/latest?cb=20151019202201");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("O Senhor dos Anéis: A Sociedade do Anel", '2:45', "Em uma terra fantástica e única, um hobbit recebe de presente de seu tio um anel mágico e maligno que precisa ser destruído antes que caia nas mãos do mal. Para isso, o hobbit Frodo tem um caminho árduo pela frente, onde encontra perigo, medo e seres bizarros. Ao seu lado para o cumprimento desta jornada, ele aos poucos pode contar com outros hobbits, um elfo, um anão, dois humanos e um mago, totalizando nove seres que formam a Sociedade do Anel.
", "Peter Jackson", 2000, "https://th.bing.com/th/id/R.7836a26a8af8e62de36d84216aaca7f7?rik=r6E4sJ12Oz%2bBBA&riu=http%3a%2f%2fbr.web.img3.acsta.net%2fmedias%2fnmedia%2f18%2f92%2f91%2f32%2f20224832.jpg&ehk=RUj44Sh2FpGHLd0Hxh9fmLNrCU1kn%2bf0AxWDyl7Ljl4%3d&risl=&pid=ImgRaw&r=0");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Shrek", '1:29', "Com a intenção de salvar sua casa, um ogro e seu burro fazem um acordo com um Lorde maldoso para resgatar uma linda princesa.
", "Andre Adamson, Vicky Jenson", 2001,"https://cineclick-static.flixmedia.cloud/1280/69/1080x1620_1592580350.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Shrek Terceiro", '1:33', "Doente em estado terminal, o rei Harold chama Fiona e Shrek para uma conversa sobre a sucessão de seu reinado e o futuro do povo em Tão Tão Distante. Como o genro se recusa a assumir o trono e prefere continuar sua pacata vida no pântano, a única saída é encontrar o primo Artur. Na companhia do Burro e do Gato de Botas, Shrek se encarrega da missão e sai em busca do parente que pode ser seu substituto no trono.", "Chris Miller", 2007, "https://static.wikia.nocookie.net/vvikipedia/images/6/6b/Shrek_Terceiro.jpg/revision/latest?cb=20200413041407&path-prefix=pt");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Velozes e Furiosos 9", '2:23', "Dominic Toretto e Letty vivem uma vida pacata ao lado do filho. Mas eles logo são ameaçados pelo passado de Dom: seu irmão desaparecido Jakob, que retorna e está trabalhando ao lado de Cipher. Cabe a Dom reunir a equipe novamente para enfrentá-los.
", "Justin Lin", 2021,"https://p2.trrsf.com/image/fget/cf/1200/1200/filters:quality(85)/images.terra.com/2021/06/03/1745111479-fastandfurious9johncena.jpeg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Velozes e Furiosos: Desafio em Tóquio", '1:44', "Sean Boswell é um piloto de rua que desafia seu rival e bate o carro no fim da corrida. Então, Sean decide se mudar para o Japão em companhia de seu pai para evitar a prisão nos Estados Unidos, já que os rachas não são nada populares com as autoridades. Em Tóquio, ele começa a aprender um excitante e perigoso estilo novo de competir nas ruas. Só que os riscos ficam ainda mais altos quando Sean decide competir com o campeão local e acaba se apaixonando pela namorada dele.
", "Justin Lin", 2006, "https://media.fstatic.com/xWSwYWLRUbBbjWvh-OUoDYjCPjE=/210x312/smart/media/movies/covers/2017/12/poster_opt_3_HMJwX5m.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Parasita", '2:12', "Toda a família de Ki-taek está desempregada, vivendo em um porão sujo e apertado. Porém uma obra do acaso faz com que ele comece a dar aulas de inglês a uma garota de família rica. Fascinados com a vida luxuosa destas pessoas, pai, mãe e filhos bolam um plano para se infiltrarem também na família burguesa, um a um. No entanto, os segredos e mentiras necessários à ascensão social custam caro a todos.
", "Bong Joon-ho",2019 , "https://veja.abril.com.br/wp-content/uploads/2020/02/poster-filme-parasite.jpg?quality=70&strip=info");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Coringa", '2:02', "Isolado, intimidado e desconsiderado pela sociedade, o fracassado comediante Arthur Fleck inicia seu caminho como uma mente criminosa após assassinar três homens em pleno metrô. Sua ação inicia um movimento popular contra a elite de Gotham City, da qual Thomas Wayne é seu maior representante.
", "Todd Phillips",2019 , "https://img.elo7.com.br/product/zoom/2A1A4B7/big-poster-filme-joker-coringa-joaquin-phoenix-tam-90x60-cm-nerd.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("The Batman", '2:56', "Após dois anos espreitando as ruas como Batman, Bruce Wayne se encontra nas profundezas mais sombrias de Gotham City. Com poucos aliados confiáveis, o vigilante solitário se estabelece como a personificação da vingança para a população.
", "Matt Reeves",2022 , "https://vertentesdocinema.com/wp-content/uploads/2022/03/big-poster-filme-batman-2022-90x60-cm-lo002-poster-batman.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Clube da Luta", '2:19', "Um homem deprimido que sofre de insônia conhece um estranho vendedor chamado Tyler Durden e se vê morando em uma casa suja depois que seu perfeito apartamento é destruído. A dupla forma um clube com regras rígidas onde homens lutam. A parceria perfeita é comprometida quando uma mulher, Marla, atrai a atenção de Tyler.", 
"David Fincher",1999 , "https://br.web.img3.acsta.net/medias/nmedia/18/90/95/96/20122166.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Entre Facas e Segredos", '2:10', "Depois de fazer 85 anos, Harlan Thrombey, um famoso escritor de histórias policiais, é encontrado morto. Contratado para investigar o caso, o detetive Benoit Blanc descobre que, entre os funcionários misteriosos e a família conflituosa de Harlan, todos podem ser considerados suspeitos do crime.", 
"Rian Johnson",2019 , "https://m.media-amazon.com/images/I/91Z59f11AnL._AC_SL1500_.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Pulp Fiction: Tempo de Violência", '2:34', "Assassino que trabalha para a máfia se apaixona pela esposa de seu chefe quando é convidado a acompanhá-la, um boxeador descumpre sua promessa de perder uma luta e um casal tenta um assalto que rapidamente sai do controle.", 
"Quentin Tarantino",1994 , "https://m.media-amazon.com/images/I/81UTs3sC5hL._AC_SL1500_.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Midsommar - O Mal Não Espera a Noite", '2:20', "Após vivenciar uma tragédia pessoal, Dani vai com o namorado Christian e um grupo de amigos até a Suécia para participar de um festival local de verão. Mas, ao invés das férias tranquilas com a qual todos sonhavam, o grupo se depara com rituais bizarros de uma adoração pagã.
", "Ari Aster",2019 , "https://i0.wp.com/pbs.twimg.com/media/D0dJ2_jWoAAtnlE.jpg?w=760&ssl=1");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Homem-Aranha no Aranhaverso", '1:56', "Após ser atingido por uma teia radioativa, Miles Morales, um jovem negro do Brooklyn, se torna o Homem-Aranha, inspirado no legado do já falecido Peter Parker. Entretanto, ao visitar o túmulo de seu ídolo em uma noite chuvosa, ele é surpreendido com a presença do próprio Peter, vestindo o traje do herói por baixo de um sobretudo. A surpresa fica ainda maior quando Miles descobre que ele veio de uma dimensão paralela, assim como outras versões do Homem-Aranha.
", "Peter Ramsey, Bob Persichetti, Rodney Rothman",2018 , "https://br.web.img3.acsta.net/pictures/18/12/05/16/28/3718855.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("A Origem", '2:28', "Dom Cobb é um ladrão com a rara habilidade de roubar segredos do inconsciente, obtidos durante o estado de sono. Impedido de retornar para sua família, ele recebe a oportunidade de se redimir ao realizar uma tarefa aparentemente impossível: plantar uma ideia na mente do herdeiro de um império. Para realizar o crime perfeito, ele conta com a ajuda do parceiro Arthur, o discreto Eames e a arquiteta de sonhos Ariadne. Juntos, eles correm para que o inimigo não antecipe seus passos.
", "Christopher Nolan",2010 , "https://i.pinimg.com/736x/26/3d/72/263d72112c775b4fd4d55e919ea06cd6--cool-movie-posters-awesome-posters.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Corra!", '1:44', "Chris é um jovem fotógrafo negro que está prestes a conhecer os pais de Rose, sua namorada caucasiana. Com o tempo, ele percebe que a família dela esconde algo muito perturbador.
", "Jordan Peele",2017 , "https://br.web.img3.acsta.net/pictures/17/04/19/21/08/577190.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Interestelar", '2:49', "As reservas naturais da Terra estão chegando ao fim e um grupo de astronautas recebe a missão de verificar possíveis planetas para receberem a população mundial, possibilitando a continuação da espécie. Cooper é chamado para liderar o grupo e aceita a missão sabendo que pode nunca mais ver os filhos. Ao lado de Brand, Jenkins e Doyle, ele seguirá em busca de um novo lar.
", "Christopher Nolan",2014 , "https://www.cafecomfilme.com.br/media/k2/items/cache/3d4417dea4b5985d283b0a7dfa9861de_XL.jpg?t=20170122_203040");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Duna (2021)", '2:35', "Paul Atreides é um jovem brilhante, dono de um destino além de sua compreensão. Ele deve viajar para o planeta mais perigoso do universo para garantir o futuro de seu povo.
", "Denis Villeneuve",2021 , "https://i.pinimg.com/originals/7e/4c/3a/7e4c3a7b6aa1fe5ae34d3247f054f517.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Whiplash: Em Busca da Perfeição", '1:47', "Andrew sonha em ser o melhor baterista de sua geração. Ele chama a atenção do impiedoso mestre do jazz Terence Fletcher, que ultrapassa os limites e transforma seu sonho em uma obsessão, colocando em risco a saúde física e mental do jovem músico.
", "Damien Chazelle",2014 , "http://www.magazine-hd.com/apps/wp/wp-content/uploads/2015/01/Whiplash-Cartaz.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("O Lobo de Wall Street", '3:00', "Jordan Belfort é um ambicioso corretor da bolsa de valores que cria um verdadeiro império, enriquecendo de forma rápida, porém ilegal. Ele e seus amigos mergulham em um mundo de excessos, mas seus métodos ilícitos despertam a atenção da polícia.
", "Martin Scorsese",2013 , "https://www.themoviedb.org/t/p/original/xfbnEVFKS26YHLuuckP0i58GPlo.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Psicopata Americano", '1:42', "Em Nova York, em 1987, o belo jovem profissional Patrick Bateman tem uma segunda vida como um horrível assassino em série durante a noite. O elenco é formado pelo detetive, a noiva, a amante, o colega de trabalho e a secretária. Esta é uma comédia de humor seco que examina os elementos que transformam um homem em um monstro.
", "Mary Harron",2000 , "https://internacionaldaamazoniacom.files.wordpress.com/2022/04/american-pyscho.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Bastardos Inglórios", '2:33', "Durante a Segunda Guerra Mundial, na França, um grupo de judeus americanos conhecidos como Bastardos espalha o terror entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança quando um evento em seu cinema reunirá os líderes do partido.
", "Quentin Tarantino",2009 , "https://br.web.img2.acsta.net/medias/nmedia/18/90/43/36/20096333.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Em Ritmo de Fuga", '1:55', "O talentoso motorista de fuga Baby confia nas batidas de sua própria trilha sonora para ser o melhor que existe. A música silencia um zumbido que o perturba desde um acidente na infância. Após conhecer a mulher dos seus sonhos, ele reconhece uma oportunidade de se livrar do estilo de vida questionável e recomeçar do zero. Obrigado a trabalhar para um chefão do crime, Baby lida com a música ao mesmo tempo em que um golpe fadado ao fracasso ameaça sua vida, seu amor e sua liberdade.
", "Edgar Wright",2017 , "https://i.pinimg.com/originals/60/2a/99/602a993352ad3887ba18d64cbb971b53.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Guardiões da Galáxia", '2:02', "O aventureiro do espaço Peter Quill torna-se presa de caçadores de recompensas depois que rouba a esfera de um vilão traiçoeiro, Ronan. Para escapar do perigo, ele faz uma aliança com um grupo de quatro extraterrestres. Quando Quill descobre que a esfera roubada possui um poder capaz de mudar os rumos do universo, ele e seu grupo deverão proteger o objeto para salvar o futuro da galáxia.
", "James Gunn",2014 , "https://i.pinimg.com/736x/e2/9f/40/e29f4041ead552c836c3dbbf3dc17e74.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Se7en - Os Sete Crimes Capitais", '2:07', "A ponto de se aposentar, o detetive William Somerset pega um último caso, com a ajuda do recém-transferido David Mills. Juntos, descobrem uma série de assassinatos e logo percebem que estão lidando com um assassino que tem como alvo pessoas que ele acredita representar os sete pecados capitais.
", "David Fincher",1995 , "https://br.web.img3.acsta.net/pictures/210/124/21012465_2013061319170245.jpg");

insert into filme(nome, duracao, sinopse, diretor, ano_lancamento, cartaz)
values("Forrest Gump - O Contador de Histórias", '2:22', "Mesmo com o raciocínio lento, Forrest Gump nunca se sentiu desfavorecido. Graças ao apoio da mãe, ele teve uma vida normal. Seja no campo de futebol como um astro do esporte, lutando no Vietnã ou como capitão de um barco de pesca de camarão, Forrest inspira a todos com seu otimismo. Mas a pessoa que Forrest mais ama pode ser a mais difícil de salvar: seu amor de infância, a doce e perturbada Jenny.
", "Robert Zemeckis",1944 , "https://cinegarimpo.com.br/wp/content/uploads/2009/11/cinegarimpo_forrest-gump-movie_poster.jpg");

insert into cinema(nome, id_administrador, caixa)
values("Cinema Teste", 1, 1500);

insert into sala(nome, id_cinema)
values("Sala1", 1);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-15 19:30:00', 1, 3, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-15 20:00:00', 1, 3, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-16 19:30:00', 1, 3, 45, 25);

insert into sessao(data_sessao, id_sala, id_filme, valor_inteira, valor_meia)
values('2022-09-16 20:00:00', 1, 3, 45, 25);
