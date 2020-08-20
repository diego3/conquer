CREATE DATABASE conquer CHARSET utf8mb4;
use conquer;

create table contrato (
     identificador bigint not null primary key comment "Identificador do Contrato",
     uasg int not null comment "Campo de seis digitos que indica o código da UASG contratante",
     modalidade_licitacao int default null comment "Número e o ano da licitação que originou a contratação",
     numero_aviso_licitacao int  default null comment "Número do aviso da licitação que originou a contratação",
     codigo_contrato int default null comment "Tipo de Contrato",
     licitacao_associada varchar(255) default null comment "Referência à licitação que originou a contratação",
     origem_licitacao varchar(255) default null comment "Origem da licitação que gerou o contrato: Preço praticado(SISPP) ou Registro de preço(SISRP)",
     numero int default null comment "Campo seguido pelo número do contrato, seguido do respectivo ano",
     numero_aditivo int comment "Quantidade de termos aditivos de um contrato",
     numero_processo varchar(255) comment "Número do processo de contratação",
     cpf_contratada varchar(255) comment "CPF da contratada",
     cnpj_contratada int comment "CNPJ da empresa contratada",
     data_assinatura date default null comment "Data de assinatura do contrato",
     fundamento_legal varchar(150) comment "Fundamento legal do processo de contratação, geralmente o número de uma lei",
     data_inicio_vigencia date default null comment "Data de início de vigência dos contratos",
     data_termino_vigencia date default null comment "Data de término de vigência dos contratos",
	 valor_inicial decimal(10,2) default 0.0 comment "Valor inicial do contrato",
     atualizacao datetime default current_timestamp on update current_timestamp comment "Data de atualização deste registro no banco de dados"
) engine Innodb charset utf8 comment "Tabela responsável por guardar dados de contratos de compras do governo do Brasil";