USE AvaliacaoN2

CREATE TABLE Produto
(
   id_Produto smallint primary key  NOT NULL,
   nome VARCHAR(50),
   preco smallmoney,
    desconto int
);

create table Cd
(

id_Cd smallint primary key  NOT NULL,
id_Produto int NOT NULL,
artista VARCHAR(50),
faixas smallint,
 FOREIGN KEY (id_Cd) REFERENCES Produto(id_Produto)

);

create table Livro
(
id_Livro smallint primary key  NOT NULL,
id_Produto int NOT NULL,
autor VARCHAR(50),
pagina smallint
 FOREIGN KEY (id_Livro) REFERENCES Produto(id_Produto)
);
