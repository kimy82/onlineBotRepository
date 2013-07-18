/*DROPS*/
drop table claus;
drop table promos_associated;
drop table users;
drop table user_roles;
drop table images;
drop table plats;
drop table restaurants;
drop table restaurants_plats;
drop table newsletters;
drop table votacio_beguda; 
drop table votacio_plat; 
drop table votacio_restaurant; 
drop table TMP_VT_B; 
drop table TMP_VT; 
drop table beguda_foro; 
drop table foro;
drop table plats_foro; 
drop table foroB;
drop table begudaComanda; 
drop table comanda_begudes; 
drop table platComanda; 
drop table comanda_plats; 
drop table begudes;
drop table promocions;  
drop table mt_config_moters;  
drop table mt_config_restaurant; 
drop table restaurants_obertura;
drop table comandes;


/* alters init*/
 set global  max_allowed_packet = 1073741824;
/*per veure info en els grafics de les promos normals. Primer s'han de crear*/
update promocions set list_date_used="2013-03-01 2013-03-01 2013-03-04 20
13-03-04 2013-03-20 2013-03-20 2130-03-20 2013-03-29" where 1=1;
update promocions set NUM_USED=24 where 1=1;
update promocions set NUM_USES=98 where 1=1;

/*per veure info en els grafics de les promos assignables. Primer s'han de crear*/
update promos_associated set list_date_used="2013-03-01 2013-03-01 2013-03-04 20
13-03-04 2013-03-20 2013-03-20 2130-03-20 2013-03-29" where 1=1;
update promos_associated set NUM_USED=24 where 1=1;

alter table plats add column CELIACS tinyint(1) NOT NULL DEFAULT 1;
alter table plats add column OUS tinyint(1) NOT NULL DEFAULT 1;
alter table plats add column LACTICS tinyint(1) NOT NULL DEFAULT 1;
alter table plats add column VEGETARIANS tinyint(1) NOT NULL DEFAULT 1;
alter table plats add column FRUITS_SECS tinyint(1) NOT NULL DEFAULT 1;


alter table foro add column DIA DATE;
alter table foro add column NOM_USU varchar(100);

alter table foroB add column DIA DATE;
alter table foroB add column NOM_USU varchar(100);

alter table  promocions add column CODE VARCHAR(10);
alter table  promocions add column VISIBILITY tinyint(1) NOT NULL DEFAULT 1;

 alter table comandes add column HORA_ENTREGA varchar(45);
  alter table users modify column PROMO_DISP varchar(200);
/* alters finish*/
/*nova taula per les promos assignables*/
CREATE TABLE claus(
	ID  INT(10) UNSIGNED,
	ENTORN VARCHAR(10),
	CODE VARCHAR(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE promos_associated (
	PROMOCIO_ASS_ID INT(10) UNSIGNED,
	CODE VARCHAR(10),
	FENTRADA DATE,
	NOM VARCHAR(200),
	NOM_ES VARCHAR(200),
	TIPUS_DESCOMPTE VARCHAR(200),
	DESCOMPTE DOUBLE(10,2),
	IMPORT_A_PARTIR_DE DOUBLE(10,2),
	NUM_BEGUDES INT(5),
	HORA VARCHAR(20),
    TIPUS_BEGUDA VARCHAR(20),
    NUM_USED INT(4) NULL,
    LIST_DATE_USED VARCHAR(10000) NULL,
	PRIMARY KEY (PROMOCIO_ASS_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `NOM` VARCHAR(45) NOT NULL,
  `INDICACIONS` VARCHAR(200),
  `ADDRESS` VARCHAR(200),
  `TELEFON` VARCHAR(10),
  `PROMO_DISP` VARCHAR(200),
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO users (USER_ID, USERNAME,PASSWORD,NOM,ADDRESS, TELEFON,ENABLED) VALUES (100, 'joaquim.orra@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b','Quim','cami del mig n14','600789687', TRUE);
 
INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 100, 'ROLE_ADMIN');

CREATE TABLE  images (
  IMAGE_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  IMAGE MEDIUMBLOB  NOT NULL,
  DESCRIPCIO  VARCHAR(200),
  PRIMARY KEY (`IMAGE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  plats (
  PLAT_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NOM VARCHAR(200),
  NOM_ES VARCHAR(200),
  DESCRIPCIO VARCHAR(200),
  DESCRIPCIO_ES VARCHAR(200),
  TIPUS VARCHAR(20),
  PREU DOUBLE(10,2),
  PRIORITAT INT(10) UNSIGNED NULL,
  CODI  VARCHAR(20),
  ACTIU tinyint(1) NOT NULL DEFAULT 1 ,
  TEMPS INT(10) UNSIGNED NULL DEFAULT 30,
  CELIACS tinyint(1) NOT NULL DEFAULT 1,
  FRUITS_SECS tinyint(1) NOT NULL DEFAULT 1,
  LACTICS tinyint(1) NOT NULL DEFAULT 1,
  VEGETARIANS tinyint(1) NOT NULL DEFAULT 1,
  OUS tinyint(1) NOT NULL DEFAULT 1,
  IMAGE_ID INT(10) UNSIGNED NULL,
  PRIMARY KEY (`PLAT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  restaurants (
  RESTAURANT_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NOM VARCHAR(200),
  DESCRIPCIO VARCHAR(200),
  DESCRIPCIO_ES VARCHAR(200),
  CODIMAQUINA VARCHAR(20),
  HORES	VARCHAR(4000),
  ADDRESS VARCHAR(400),
  IMAGE_ID INT(10) UNSIGNED,
  PRIMARY KEY (`RESTAURANT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  restaurants_plats (	
	RESTAURANT_ID INT(10) UNSIGNED, 
	PLAT_ID INT(10) UNSIGNED
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;
   
CREATE TABLE  mt_config_moters (
  MT_MOTER_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NUM_MOTERS	INT(10) UNSIGNED NOT NULL,
  NUM_MOTERS_USED	INT(10) UNSIGNED,
  FECHA DATE NOT NULL,
  HORA VARCHAR(5) NOT NULL,
  PRIMARY KEY (`MT_MOTER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE  mt_config_restaurant (
  MT_RESTAURANT_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  RESTAURANT_ID	 INT(10) UNSIGNED NOT NULL,
  FECHA DATE NOT NULL,
  OBERT tinyint(1) NOT NULL DEFAULT 1 ,
  HORES	VARCHAR(400),
  PRIMARY KEY (`MT_RESTAURANT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  restaurants_obertura (	
	RESTAURANT_ID INT(10) UNSIGNED, 
	MT_RESTAURANT_ID INT(10) UNSIGNED
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE promocions (
	PROMOCIO_ID INT(10) UNSIGNED,
	FENTRADA DATE,
	NOM VARCHAR(200),
	NOM_ES VARCHAR(200),
	TIPUS_DESCOMPTE VARCHAR(200),
	DESCOMPTE DOUBLE(10,2),
	NUM_COMANDES INT(10),
	TEMPS INT(10),
	IMPORT_A_PARTIR_DE DOUBLE(10,2),
	DIA DATE,
	NUM_BEGUDES INT(5),
	HORA VARCHAR(20),
	NUM_USES INT(4),
	NUM_USED INT(4),
	ACTIU_DILLUNS tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DIMARTS tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DIMECRES tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DIJOUS tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DIVENDRES tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DISSABTE tinyint(1) NOT NULL DEFAULT 1,
	ACTIU_DIUMENGE tinyint(1) NOT NULL DEFAULT 1,
    TIPUS_BEGUDA VARCHAR(20),
    LIST_DATE_USED VARCHAR(10000) NULL,
    CODE VARCHAR(10),
    VISIBILITY tinyint(1) NOT NULL DEFAULT 1,
	DISCRIMINATOR VARCHAR(20) NOT NULL,
	PRIMARY KEY (PROMOCIO_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE begudes (
	BEGUDA_ID INT(10) UNSIGNED,
	NOM VARCHAR(200),
	NOM_ES VARCHAR(200),
	TIPUS VARCHAR(200),
	DESCRIPCIO VARCHAR(1000),
	DESCRIPCIO_ES VARCHAR(200),
	PREU DOUBLE(10,2),
	IMAGE_ID INT(10) UNSIGNED NULL,
	PRIMARY KEY (BEGUDA_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE comandes (
	COMANDA_ID INT(10) UNSIGNED,
	HORA VARCHAR(5),
	HORA_ENTREGA VARCHAR(45) default null,
	DIA DATE,
	FENTRADA DATE,
	OBSERVACIONS VARCHAR(1000),	
	PREU DOUBLE(10,2),
	TIPUS_DESCOMPTE varchar(10),
	IMPORT_DESCOMPTE DOUBLE(10,2),
	ADDRESS VARCHAR(200),
	ADOMICILI tinyint(1) DEFAULT 0,
	PAGADA tinyint(1) DEFAULT 0,
	TARGETA tinyint(1) DEFAULT 0,
	REVISIO tinyint(1) DEFAULT 0,
	USER_ID INT(10) UNSIGNED,
	PLATS_BORRATS VARCHAR(2000),
	PRIMARY KEY (COMANDA_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  comanda_plats (	
	COMANDA_ID INT(10) UNSIGNED, 
	PLATCOMANDA_ID INT(10) UNSIGNED,
	NOM varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  platComanda (
  PLATCOMANDA_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PLAT_ID INT(10) UNSIGNED,
  NUM INT(10) UNSIGNED,
  PRIMARY KEY (`PLATCOMANDA_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  comanda_begudes (	
	COMANDA_ID INT(10) UNSIGNED, 
	BEGUDACOMANDA_ID INT(10) UNSIGNED,
	NOM varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  begudaComanda (
  BEGUDACOMANDA_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  BEGUDA_ID INT(10) UNSIGNED,
  PROMO tinyint(1) DEFAULT 0,
  NUM INT(10) UNSIGNED,
  NUM_PROMO INT(10) UNSIGNED,
  PRIMARY KEY (`BEGUDACOMANDA_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE foro(
  FORO_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  COMMENT  varchar(400),
  PLAT_ID INT(10) UNSIGNED NOT NULL,
  DIA DATE,
  NOM_USU varchar(100),
  PRIMARY KEY (`FORO_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
CREATE TABLE  plats_foro (	
  FORO_ID INT(10) UNSIGNED, 
  PLAT_ID INT(10) UNSIGNED
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE foroB(
  FOROB_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  COMMENT  varchar(400),
  BEGUDA_ID INT(10) UNSIGNED NOT NULL,
  DIA DATE,
  NOM_USU varchar(100),
  PRIMARY KEY (`FOROB_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
CREATE TABLE  beguda_foro (	
  FOROB_ID INT(10) UNSIGNED, 
  BEGUDA_ID INT(10) UNSIGNED
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  TMP_VT (	  
  PLAT_ID INT(10) UNSIGNED,
  USER_ID INT(10) UNSIGNED,
  VAL_VOT INT(5) UNSIGNED,
  DIA DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  TMP_VT_B (	  
  BEGUDA_ID INT(10) UNSIGNED,
  USER_ID INT(10) UNSIGNED,
  VAL_VOT INT(5) UNSIGNED,
  DIA DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  votacio_plat (	  
  PLAT_ID INT(10) UNSIGNED,
  NVOTS INT(10) UNSIGNED,
  VAL_VOT INT(5) UNSIGNED,
  PRIMARY KEY (`PLAT_ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  votacio_restaurant (	  
  RESTAURANT_ID INT(10) UNSIGNED,
  NVOTS INT(10) UNSIGNED,
  VAL_VOT INT(5) UNSIGNED,
  PRIMARY KEY (`RESTAURANT_ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  votacio_beguda (	  
  BEGUDA_ID INT(10) UNSIGNED,
  NVOTS INT(10) UNSIGNED,
  VAL_VOT INT(5) UNSIGNED,
  PRIMARY KEY (`BEGUDA_ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE newsletters(
	NEWSLETTER_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, 
	EMAIL varchar(100),
	PRIMARY KEY (`NEWSLETTER_ID`) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;