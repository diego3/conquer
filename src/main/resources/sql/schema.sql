CREATE DATABASE conquer CHARSET utf8mb4;
use conquer;

create table contrato (
	 id bigint primary key auto_increment,
     identificador varchar(255)  not null,
     uasg int not null,
     modalidade_licitacao int default null,
     numero_aviso_licitacao int  default null,
     codigo_contrato int default null,
     licitacao_associada varchar(255) default null,
     origem_licitacao varchar(255) default null,
     numero int default null,
     numero_aditivo int,
     numero_processo varchar(255),
     cpf_contratada varchar(255),
     cnpj_contratada int,
     data_assinatura date default null,
     fundamento_legal varchar(150),
     data_inicio_vigencia date default null,
     data_termino_vigencia date default null,
	 valor_inicial decimal(10,2) default 0.0,
     atualizacao datetime default current_timestamp on update current_timestamp
) engine Innodb charset utf8