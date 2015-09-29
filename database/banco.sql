create database nicenes;
use nicenes;
create table perfil (
	id_perfil int not null auto_increment,
    nome varchar(255),
    primary key (id_perfil)
);
CREATE TABLE usuario (
	id_usuario int not null auto_increment,
    nome varchar(255),
    email varchar(255),
    senha varchar(255),
    id_perfil int not null,
    primary key (id_usuario),
    foreign key (id_perfil) references perfil (id_perfil)
);

insert into perfil(nome) values("Adm");
insert into perfil(nome) values("Visitante");
insert into usuario(nome,email,senha,id_perfil) values ("UserTeste","admin@admin","admin",1);

create table categoria (
	id_categoria int not null auto_increment,
    categoria varchar(255),
    primary key (id_categoria)
);


create table editora(
	id_editora int not null auto_increment,
    nome varchar(255),
	endereco varchar(255),
	cidade varchar(255),
	uf varchar(255),
	pais varchar(255),
	cep varchar(255),
	fone varchar(255),
	cnpj varchar(255),
	razao varchar(255),
	web varchar(255),
    primary key (id_editora)
);

create table autor (
	id_autor int not null auto_increment,
    nome varchar(255),
    sobrenome varchar(255),
    principal_livro varchar(255),
    endereco varchar(255),
    telefone varchar(255),
    email varchar(255),
    rg varchar(255),
    dt_nascimento date,
    obs varchar(255),
    qtd_livros int,
    primary key (id_autor)
);

create table livro (
    id_livro int not null auto_increment,
    titulo varchar(255),
    isbn varchar(255),
    colecao varchar(255),
    edicao varchar(255),
    idioma varchar(255),
    preco double(10,2),
    ano int,
    id_autor int not null,
    id_editora int not null,
    id_categoria int not null,
    primary key (id_livro),
    constraint fk_autor FOREIGN KEY (id_autor)
    REFERENCES autor (id_autor),
    constraint fk_editora FOREIGN KEY (id_editora)
    REFERENCES editora (id_editora),
    constraint fk_categoria FOREIGN KEY (id_categoria)
    REFERENCES categoria (id_categoria)
);



create table venda(
	id_venda int not null auto_increment,
    frete varchar(255),
    valor_frete double,
    id_usuario int,
    primary key (id_venda),
    foreign key (id_usuario) references usuario (id_usuario)
);

create table item_venda(
	id_item_venda int not null auto_increment,
    id_item int,
	id_venda int,
    preco double,
    primary key(id_item_venda),
    foreign key(id_item) references livro(id_livro),
    foreign key(id_venda) references venda(id_venda)
);

create table lista_desejo(
	id_livro int not null,
    id_usuario int not null,
    foreign key(id_livro) references livro(id_livro),
    foreign key(id_usuario) references usuario(id_usuario)
);

insert into autor values(null,'Dan', 'Brown', 'O Código Da Vinci', 'Exeter. Nova Hampshire', '857645632', 'dan_brown@hotmail.com', '3777652608', CURDATE(), 'Sem obs', 55);
