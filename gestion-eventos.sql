drop database if exists gestion_eventos_db;
create database gestion_eventos_db;
use gestion_eventos_db;

create table Organizadores(
	idOrganizador integer auto_increment not null,
    nombreOrganizador varchar(45) not null,
    correo varchar(128) not null,
    contrasena varchar(64) not null,
	constraint pk_Organizador primary key (idOrganizador)
);

create table Eventos (
	idEvento integer auto_increment not null,
    nombreEvento varchar(45) not null,
    descripcion varchar(88) not null,
    fechaEvento Date not null,
    ubicacion varchar(125) not null,
    constraint pk_Evento primary key (idEvento)
);

create table Usuarios(
	idUsuario integer auto_increment not null,
    nombreUsuario varchar(45) not null,
    correo varchar(128) not null,
    contrasena varchar(64) not null,
    constraint pk_Usuario primary key (idUsuario)
);

create table Tickets(
	idTicket integer auto_increment not null,
    idEvento integer not null,
    idUsuario integer not null,
    precio decimal(10, 2) not null,
    estado enum('Vendido','Usado', 'Disponible') default 'Disponible',
    constraint pk_Ticket primary key (idTicket)
);

select * from Usuarios;