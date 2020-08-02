DROP DATABASE IF EXISTS matriculacion;
CREATE DATABASE IF NOT EXISTS matriculacion;
USE matriculacion;

DROP TABLE IF EXISTS alumnos;
CREATE TABLE alumnos (
  nif varchar(10) NOT NULL,
  nombre varchar(64) NOT NULL,
  direccion varchar(64),
  email varchar(64),
  fechaNac datetime,
  PRIMARY KEY (nif)
)
