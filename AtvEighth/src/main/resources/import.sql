insert into produto (descricao, valor) values ('Arroz', 49.99);
insert into produto (descricao, valor) values ('Feijão', 16.99);
insert into produto (descricao, valor) values ('Farinha', 7.99);
insert into produto (descricao, valor) values ('Carne Acém', 26.99);

insert into pessoa_fisica (id, cpf, nome, telefone) VALUES (1, '123.456.789-10', 'Fulano da Silva','+5563999999999');
insert into pessoa_juridica (id, nome, cnpj, razao_social, telefone) VALUES (2, 'Empresa ABC LTDA', '12.345.678/0001-90', 'MinhaEmpresa','+5563988888888');

insert into pessoa_fisica (id, cpf, nome, telefone) VALUES (3, '987.654.321-00', 'Ciclano Oliveira','+5563977777777');
insert into pessoa_juridica (id, cnpj, nome, razao_social, telefone) VALUES (4, '98.765.432/0001-21', 'Comércio XYZ S/A', 'ComercioComerciante','+556396666666666');

insert into venda (data, pessoa_id) values ('2024-03-08',1);

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 2, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 3, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 4, 1);

insert into venda (data, pessoa_id) values ('2024-03-09',2);

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 2);
insert into item_venda (quantidade, produto_id, venda_id) values (2.5, 4, 2);

