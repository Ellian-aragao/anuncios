insert into tb_cliente(nm_nome)
values ('Rogério'),
       ('Marcio'),
       ('Agnaldo'),
       ('Maria'),
       ('Fernanda'),
       ('Marcos'),
       ('Marcela'),
       ('Tayna');

insert into tb_anuncio(nm_nome, num_investimento_diario, dt_inicio, dt_termino, cliente_pk_id)
values ('investimento de 1 dia', 50.00, '2020-01-01', '2020-01-02', 1),
       ('Loja de roupas', 50.00, '2020-08-27', '2020-11-01', 2),
       ('Tênis', 25.33, '2010-12-25', '2011-04-13', 4),
       ('relógios', 44.2, '2018-01-20', '2020-08-08', 3),
       ('comida', 8.09, '2015-02-17', '2016-07-14', 8),
       ('sofás', 23, '2018-01-11', '2020-05-04', 5),
       ('violões', 24, '2017-12-20', '2020-07-19', 1),
       ('notebooks', 53, '2018-03-13', '2019-10-30', 7),
       ('livros', 97, '2018-06-20', '2020-06-04', 3),
       ('facas', 10.4, '2018-05-28', '2020-11-14', 8),
       ('camas', 33.8, '2019-03-27', '2021-05-27', 5),
       ('carros', 78.59, '2018-12-28', '2019-04-25', 2),
       ('óculos', 1.56, '2016-07-16', '2020-06-14', 4);
