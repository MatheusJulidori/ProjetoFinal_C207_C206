DROP DATABASE IF EXISTS projeto;
CREATE DATABASE projeto;
USE projeto;

CREATE TABLE IF NOT EXISTS curso(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS aluno(
	matricula INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45),
    ano_nasc int,
    sexo ENUM('F','M'),
    fk_idcurso INT,
    CONSTRAINT fk
		FOREIGN KEY(fk_idcurso) REFERENCES curso(id) ON UPDATE CASCADE
);

#truncate table usuario;