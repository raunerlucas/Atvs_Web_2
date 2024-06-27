create table produto
(
    id int AUTO_INCREMENT PRIMARY KEY,
    descricao varchar(200) not null,
    valor double not null
);
insert into produto (descricao,valor) values ('Arroz', 49.99);
insert into produto (descricao,valor) values ('Carne Acen', 26.99);