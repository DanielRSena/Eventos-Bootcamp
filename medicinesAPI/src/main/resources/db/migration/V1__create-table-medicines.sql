CREATE TABLE medicines (
    id bigint NOT NULL auto_increment,
    nome varchar(100) NOT NULL,
    via varchar(100) NOT NULL,
    lote varchar(100) NOT NULL,
    validade varchar(100) NOT NULL,
    laboratorio varchar(100) NOT NULL,
    quantidade int(20) NOT NULL,
    PRIMARY KEY(id)
);