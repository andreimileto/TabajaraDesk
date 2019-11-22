/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  AndreiMileto
 * Created: 22/11/2019
 */

CREATE DATABASE tabajaradesk
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- -----------------------------------------------------
-- Table mydb.user
-- -----------------------------------------------------
CREATE TABLE users (
  id serial ,
  users VARCHAR(200) not NULL unique,
  login VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));
  -- UNIQUE INDEX login_UNIQUE (login ASC) )
-- ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.client
-- -----------------------------------------------------
CREATE TABLE client (
  id serial ,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(200) NOT NULL,
  birth_date DATE NOT NULL,
  sync CHAR NOT NULL,
  PRIMARY KEY (id));
-- ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.event
-- -----------------------------------------------------
CREATE TABLE event (
  id serial ,
  name VARCHAR(45) NOT NULL,
  init_hour VARCHAR(45) NOT NULL,
  final_hour VARCHAR(45) NOT NULL,
  sync CHAR NOT NULL,
  PRIMARY KEY (id));
-- ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.activity_event
-- -----------------------------------------------------
CREATE TABLE activity_event (
  id serial ,
  id_event INT NOT NULL,
  id_client INT NOT NULL,
  date DATE NOT NULL,
  status CHAR NOT NULL,
  sync CHAR NOT NULL,
  PRIMARY KEY (id),
--  INDEX fk_activity_event_event_idx (id_event) ,
--  INDEX fk_activity_event_client1_idx (id_client) ,
  CONSTRAINT fk_activity_event_event
    FOREIGN KEY (id_event)
    REFERENCES event (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_activity_event_client1
    FOREIGN KEY (id_client)
    REFERENCES client (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table mydb.checkin
-- -----------------------------------------------------
CREATE TABLE checkin (
  id serial ,
  id_activity_event INT NOT NULL,
  date DATE NULL,
  init_hour VARCHAR(45) NULL,
  sync CHAR NULL,
  PRIMARY KEY (id),
--  INDEX fk_checkin_activity_event1_idx (id_activity_event) ,
  CONSTRAINT fk_checkin_activity_event1
    FOREIGN KEY (id_activity_event)
    REFERENCES activity_event (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- ENGINE = InnoDB;


-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


------------------
INSERT INTO public.users(
	id, users, login, password)
	VALUES (null, 'andrei', 'andrei', '123456');

INSERT INTO public.event(
	id, name, init_hour, final_hour, sync)
	VALUES (null, 'Evento 1','' , '', 'N');
	
	
INSERT INTO public.client(
	id, name, email, birth_date, sync)
	VALUES (null, 'andrei', 'andreimileto@gmail.com', '1996-06-05', 'N');



	
