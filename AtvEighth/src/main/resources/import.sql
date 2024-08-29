insert into produto (descricao, valor) values ('Arroz', 49.99);
insert into produto (descricao, valor) values ('Feijão', 16.99);
insert into produto (descricao, valor) values ('Farinha', 7.99);
insert into produto (descricao, valor) values ('Carne Acém', 26.99);
insert into produto (descricao, valor) values ('Óleo de Soja', 12.99);
insert into produto (descricao, valor) values ('Açúcar', 8.49);
insert into produto (descricao, valor) values ('Sal', 3.29);
insert into produto (descricao, valor) values ('Café', 15.99);
insert into produto (descricao, valor) values ('Macarrão', 5.79);
insert into produto (descricao, valor) values ('Molho de Tomate', 4.59);
insert into produto (descricao, valor) values ('Biscoito', 6.99);
insert into produto (descricao, valor) values ('Leite', 7.29);
insert into produto (descricao, valor) values ('Iogurte', 2.99);
insert into produto (descricao, valor) values ('Pão de Forma', 8.99);

insert into role(nome) values ('ROLE_USER');
insert into role(nome) values ('ROLE_ADMIN');

insert into usuario (login, password) values('userf','$2a$10$ge.D0BP8Ae3AkS4dxMWMX.nMJkGtnBd2843Kz7bpBoyEtwTadmbGS');
insert into pessoa_fisica (id, USUARIO_ID, cpf, nome, telefone) VALUES (1,1, '123.456.789-10', 'Fulano da Silva','90000-0000');
insert into usuario_roles (roles_id,usuarios_id) values (1,1);

insert into usuario (login, password) values('userabc','$2a$10$ge.D0BP8Ae3AkS4dxMWMX.nMJkGtnBd2843Kz7bpBoyEtwTadmbGS');
insert into pessoa_juridica (id, USUARIO_ID, nome, cnpj, razao_social, telefone) VALUES (2,2, 'Empresa ABC LTDA', '12.345.678/0001-90', 'MinhaEmpresa','+5563988888888');
insert into usuario_roles (roles_id,usuarios_id) values (1,2);

insert into usuario (login, password) values('cliclano','$2a$10$ge.D0BP8Ae3AkS4dxMWMX.nMJkGtnBd2843Kz7bpBoyEtwTadmbGS');
insert into pessoa_fisica (id, USUARIO_ID, cpf, nome, telefone) VALUES (3,3, '987.654.321-00', 'Ciclano Oliveira','+5563977777777');
insert into usuario_roles (roles_id,usuarios_id) values (1,3);

insert into usuario (login, password) values('userxyz','$2a$10$ge.D0BP8Ae3AkS4dxMWMX.nMJkGtnBd2843Kz7bpBoyEtwTadmbGS');
insert into pessoa_juridica (id, USUARIO_ID, cnpj, nome, razao_social, telefone) VALUES (4,4, '98.765.432/0001-21', 'Comércio XYZ S/A', 'ComercioComerciante','+556396666666666');
insert into usuario_roles (roles_id,usuarios_id) values (1,4);

insert into usuario (login, password) values('admin','$2a$10$cufo7kEnAD8XivEv1A7grOxUq/XCd5vdPhxk4bbsm.apG/vUxfEO2');
insert into usuario_roles (roles_id,usuarios_id) values (2,5); // Admin == Admin


insert into venda (data, pessoa_id) values ('2024-03-08',1);

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 2, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 3, 1);
insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 4, 1);

insert into venda (data, pessoa_id) values ('2024-03-09',2);

insert into item_venda (quantidade, produto_id, venda_id) values (1.0, 1, 2);
insert into item_venda (quantidade, produto_id, venda_id) values (2.5, 4, 2);
