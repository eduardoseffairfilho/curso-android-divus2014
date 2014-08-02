DROP TABLE IF EXISTS despesa;

CREATE TABLE despesa(id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL, categoria TEXT NOT NULL, valor double NOT NULL, data TEXT NOT NULL, local TEXT NOT NULL);

INSERT INTO despesa(descricao, categoria, valor, data, local) values ('almoco trabalho', 'alimentacao', 22.11, '02/08/2014', 'cantina');