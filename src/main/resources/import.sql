INSERT INTO TB_SETOR (id,nome) VALUES (default,'limpeza');
INSERT INTO TB_SETOR (id,nome) VALUES (default,'adminstrativo');
INSERT INTO TB_SETOR (id,nome) VALUES (default,'vendas');


INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Aux. limpeza',1);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Coordenador De Limpeza',1);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Aux. adminstrativo',2);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Diretor de Finanças',2);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Aux. de vendas',3);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Gerente de vendas',3);
INSERT INTO TB_CARGO (id,nome,setor_id) VALUES (default,'Diretor de vendas',3);

INSERT INTO TB_TRABALHADOR (id,cpf,nome,cargo_id) VALUES (default,'058.299.720-85','Zé',6);

