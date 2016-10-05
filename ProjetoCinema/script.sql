create table filme (
    codigo serial primary key,
    titulo varchar(80) not null,
    codigo_genero integer references genero(codigo),
    sinopse varchar(300)
);

create table genero (
    codigo serial primary key,
    nome varchar(80) not null,
    descricao varchar(300)
);
