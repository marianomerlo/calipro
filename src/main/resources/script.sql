CREATE TABLE Estado (
  idEstado INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  idGrupo INTEGER UNSIGNED NOT NULL,
  nombre VARCHAR(45) NULL,
  PRIMARY KEY(idEstado, idGrupo)
);

CREATE TABLE Perfil (
  idPerfil INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(20) NULL,
  PRIMARY KEY(idPerfil)
);

CREATE TABLE Usuario (
  idUsuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  alias VARCHAR(6),
  nombre VARCHAR(45) NULL,
  apellido VARCHAR(45) NULL,
  estado INTEGER UNSIGNED NULL,
  legajo VARCHAR(20) NULL,
  contrasenia VARCHAR(20) NULL,
  PRIMARY KEY(idUsuario),
  CONSTRAINT CONST_ALIAS UNIQUE(alias)
);

CREATE TABLE `banda` (
  `idBanda` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idBanda`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
);


CREATE TABLE `festival` (
  `idFestival` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `fecha_inicio` varchar(45) NOT NULL,
  `cantidad_dias` int(11) NOT NULL,
  `horasPorDia` int(11) DEFAULT NULL,
  `estado` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idFestival`)
);


CREATE TABLE `banda_dia_festival` (
  `posicion` int(11) NOT NULL AUTO_INCREMENT,
  `idBanda` int(11) NOT NULL,
  `IdDia` int(11) NOT NULL,
  `idFestival` int(11) NOT NULL,
  `tiempoAsignado` varchar(45) NOT NULL,
  `costoExtra` varchar(20) NOT NULL,
  PRIMARY KEY (`posicion`,`idBanda`,`IdDia`,`idFestival`),
  KEY `idBandaFK` (`idBanda`),
  CONSTRAINT `idBandaFK` FOREIGN KEY (`idBanda`) REFERENCES `banda` (`idBanda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `dia_festival` (
  `idDia` int(11) NOT NULL,
  `idFestival` int(11) NOT NULL,
  `hora_inicio` varchar(45) NOT NULL,
  PRIMARY KEY (`idDia`,`idFestival`),
  KEY `idFestival` (`idFestival`),
  CONSTRAINT `idFestival` FOREIGN KEY (`idFestival`) REFERENCES `festival` (`idFestival`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE Perfil_por_Usuario (
  idPerfil INTEGER UNSIGNED NOT NULL,
  idUsuario INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(idPerfil, idUsuario),
  INDEX Perfil_has_Usuario_FKIndex1(idPerfil),
  INDEX Perfil_has_Usuario_FKIndex2(idUsuario),
  FOREIGN KEY(idPerfil)
    REFERENCES Perfil(idPerfil)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idUsuario)
    REFERENCES Usuario(idUsuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

 -- Insert table ESTADO.

-- Grupo 1 - Estado Usuarios.
insert into estado (idgrupo,nombre) values(1,'Habilitado');  /*id:1*/
insert into estado (idgrupo,nombre) values(1,'Deshabilitado'); /*id:2*/

-- Grupo 2 - Estado Festival.
insert into estado (idgrupo,nombre) values(2,'En Programacion');  /*id:3*/
insert into estado (idgrupo,nombre) values(2,'Programado'); /*id:4*/
insert into estado (idgrupo,nombre) values(2,'En Curso'); /*id:5*/

-- Insert tabla USUARIO
 insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado)
 values ('Admin','Admin','10000000','ADMIN','ADMIN',1);/*Usuario deshabilitado*/

-- insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado) 
-- values ('Mariano','Merlo','10000001','MMERLO','MMERLO',1); /*Usuario habilitado*/

-- insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado)
-- values ('Gabriel','Lopez Condori','10000002','GLOPEZ','GLOPEZ',1);/*Usuario habilitado*/

-- insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado)
-- values ('Alan','Viera','10000003','AVIERA','AVIERA',1);/*Usuario habilitado*/

-- insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado)
-- values ('Mario','Sarmiento','10000004','MSARMI','MSARMI',1);/*Usuario habilitado*/

-- insert into usuario (nombre,apellido,legajo,alias,contrasenia,estado)
-- values ('Juan','Perez','10000005','JPEREZ','JPEREZ',2);/*Usuario deshabilitado*/

-- Insert tabla PERFIL

insert into perfil (nombre) values ('Administrador');  /*Id:1*/ 
insert into perfil (nombre) values ('Vendedor');     /*Id:2*/

-- Insert table PERFIL POR USUARIO

insert into perfil_por_usuario (idusuario,idperfil) values (1,1);
insert into perfil_por_usuario (idusuario,idperfil) values (1,2);

-- Insert tabla BANDA

insert into banda(nombre) values ('La Renga'); /*Id:1*/
insert into banda(nombre) values ('La Vela Puerca'); /*Id:2*/
insert into banda(nombre) values ('Los Piojos'); /*Id:3*/
insert into banda(nombre) values ('Catupecu Machu'); /*Id:4*/
insert into banda(nombre) values ('Rata Blanca'); /*Id:5*/
insert into banda(nombre) values ('Los Ratones Paranoicos'); /*Id:6*/
insert into banda(nombre) values ('Kapanga'); /*Id:7*/
insert into banda(nombre) values ('Esquivando el Exito'); /*Id:8*/
insert into banda(nombre) values ('Attaque 77'); /*Id:9*/
insert into banda(nombre) values ('Soda Stereo'); /*Id:10*/
insert into banda(nombre) values ('Los Redondos'); /*Id:11*/
insert into banda(nombre) values ('Babasonicos'); /*Id:12*/
insert into banda(nombre) values ('Divididos'); /*Id:13*/
insert into banda(nombre) values ('Los Abuelos de la Nada'); /*Id:14*/
insert into banda(nombre) values ('La Bersuit'); /*Id:15*/
insert into banda(nombre) values ('El Otro Yo'); /*Id:16*/
insert into banda(nombre) values ('Andres Calamaro'); /*Id:17*/
insert into banda(nombre) values ('Fito Paez'); /*Id:18*/
insert into banda(nombre) values ('Los Autenticos Decadentes'); /*Id:19*/
insert into banda(nombre) values ('Los Fabulosos Cadillacs'); /*Id:20*/
insert into banda(nombre) values ('Intoxicados'); /*Id:21*/
insert into banda(nombre) values ('Charly Garcia'); /*Id:22*/
insert into banda(nombre) values ('Callejeros'); /*Id:23*/
insert into banda(nombre) values ('Las Pelotas'); /*Id:24*/
insert into banda(nombre) values ('No te va a Gustar'); /*Id:25*/
