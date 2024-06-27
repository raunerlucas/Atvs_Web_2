insert into produto (descricao, valor) values ('Arroz', 49.99);
insert into produto (descricao, valor) values ('Feijão', 16.99);
insert into produto (descricao, valor) values ('Farinha', 7.99);
insert into produto (descricao, valor) values ('Carne Acém', 26.99);

insert into venda (data) values ('2024-03-08');

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 2, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 3, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 4, 1);

insert into venda (data) values ('2024-03-09');

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 2);
insert into item_venda (quantidade, produto_id, venda_id) values (2.5, 4, 2);
