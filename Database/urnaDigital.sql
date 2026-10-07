CREATE DATABASE urnaDigital;
USE urnaDigital;

CREATE TABLE mesarios (
    id_mesario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(64) NOT NULL
);

CREATE TABLE eleitores (
    id_eleitor INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(50) NOT NULL UNIQUE,
    titulo VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    ja_votou BIT NOT NULL DEFAULT 0
);

CREATE TABLE cargo (
    id_cargo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL UNIQUE,
    quant_digito INT NOT NULL,
    tem_vice BIT NOT NULL
);

INSERT INTO cargo (nome, quant_digito, tem_vice) VALUES 
('PREFEITO', 2, 1),
('VEREADOR', 5, 0);

CREATE TABLE candidatos (
    id_candidato INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(5) NOT NULL UNIQUE CHECK (numero REGEXP '^[0-9]+$'),
    nome VARCHAR(100) NOT NULL,
    partido VARCHAR(30) NOT NULL,
    vice VARCHAR(100) NULL,
    id_cargo INT NOT NULL,
    foto VARCHAR(255) NULL,
    CONSTRAINT fk_candidato_cargo FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
);

CREATE TABLE sessao (
    id_sessao INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_mesario INT NOT NULL,
    inicio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fim DATETIME NULL CHECK (fim IS NULL OR fim >= inicio),
    CONSTRAINT fk_sessao_mesario FOREIGN KEY (id_mesario) REFERENCES mesarios (id_mesario)
);

CREATE TABLE voto (
    id_voto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_sessao INT NOT NULL,
    id_cargo INT NOT NULL,
    voto VARCHAR(10) NOT NULL,
    CONSTRAINT fk_voto_sessao FOREIGN KEY (id_sessao) REFERENCES sessao (id_sessao),
    CONSTRAINT fk_voto_cargo FOREIGN KEY (id_cargo) REFERENCES cargo (id_cargo)
);