DROP TABLE IF EXISTS despesa;

CREATE TABLE despesa(id integer primary key autoincrement, descricao text not null, data text not null, categoria text not null, local text, valor double not null);

INSERT INTO despesa(descricao, data, categoria, local, valor) VALUES('almoco trabalho', '02/08/2014', 'alimentacao', 'cantina', 22.11);
