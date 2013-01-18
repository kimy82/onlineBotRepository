set global  max_allowed_packet = 73741824;

CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(200),
  `TELEFON` VARCHAR(10),
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

INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (100, 'kim', '7c4a8d09ca3762af61e59520943dc26494f8941b', TRUE);
 
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
  DESCRIPCIO VARCHAR(200),
  TIPUS VARCHAR(20),
  PREU DOUBLE(10,2),
  PRIORITAT INT(10) UNSIGNED NULL,
  CODI  VARCHAR(20),
  ACTIU tinyint(1) NOT NULL DEFAULT 1 ,
  TEMPS INT(10) UNSIGNED NULL,
  IMAGE_ID INT(10) UNSIGNED NULL,
  PRIMARY KEY (`PLAT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  restaurants (
  RESTAURANT_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  NOM VARCHAR(200),
  DESCRIPCIO VARCHAR(200),
  CODIMAQUINA VARCHAR(20),
  HORES	VARCHAR(400),
  IMAGE_ID INT(10) UNSIGNED,
  PRIMARY KEY (`RESTAURANT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE  RESTAURANTS_PLATS (	
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
	NOM VARCHAR(200),
	TIPUS_DESCOMPTE VARCHAR(200),
	DESCOMPTE DOUBLE(10,2),
	NUM_COMANDES INT(10),
	TEMPS INT(10),
	IMPORT_A_PARTIR_DE DOUBLE(10,2),
	DIA DATE,
	NUM_BEGUDES INT(5),
    TIPUS_BEGUDA VARCHAR(20),
	DISCRIMINATOR VARCHAR(20) NOT NULL,
	PRIMARY KEY (PROMOCIO_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE begudes (
	BEGUDA_ID INT(10) UNSIGNED,
	NOM VARCHAR(200),
	TIPUS VARCHAR(200),
	DESCRIPCIO VARCHAR(1000),	
	PREU DOUBLE(10,2),
	IMAGE_ID INT(10) UNSIGNED NULL,
	PRIMARY KEY (BEGUDA_ID)  USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE comandes (
	COMANDA_ID INT(10) UNSIGNED,
	HORA VARCHAR(5),
	DIA DATE,
	FENTRADA DATE,
	OBSERVACIONS VARCHAR(1000),	
	PREU DOUBLE(10,2),
	TIPUS_DESCOMPTE varchar(10),
	IMPORT_DESCOMPTE DOUBLE(10,2),
	ADDRESS VARCHAR(200),
	ADOMICILI tinyint(1) DEFAULT 0,
	USER_ID INT(10) UNSIGNED,
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
  PRIMARY KEY (`BEGUDACOMANDA_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE foro(
  FORO_ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  COMMENT  varchar(400),
  PLAT_ID INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`FORO_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
   
CREATE TABLE  PLATS_FORO (	
  FORO_ID INT(10) UNSIGNED, 
  PLAT_ID INT(10) UNSIGNED
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  TMP_VT (	  
  PLAT_ID INT(10) UNSIGNED,
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
