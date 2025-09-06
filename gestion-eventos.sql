drop database if exists gestion_eventos_db;
create database gestion_eventos_db;
use gestion_eventos_db;

create table Organizadores(
	idOrganizador Integer auto_increment not null,
    nombreOrganizador varchar(45) not null,
    correo varchar(128) not null,
    contrasena varchar(64) not null,
	constraint pk_Organizador primary key (idOrganizador)
);

create table Eventos (
	idEvento Integer auto_increment not null,
    idOrganizador Integer not null,
    nombreEvento varchar(45) not null,
    descripcion varchar(88) not null,
    fechaEvento Date not null,
    ubicacion varchar(125) not null,
    constraint pk_Evento primary key (idEvento)
);

create table Usuarios(
	idUsuario Integer auto_increment not null,
    nombreUsuario varchar(45) not null,
    correo varchar(128) not null,
    contrasena varchar(64) not null,
    constraint pk_Usuario primary key (idUsuario)
);

create table Tickets(
	idTicket Integer auto_increment not null,
    idEvento Integer not null,
    idUsuario int not null,
	codigoQr varchar(45) not null,
    precio decimal(10, 2) not null,
    estado enum('Vendido','Usado', 'Disponible') default 'Disponible',
    constraint pk_Ticket primary key (idTicket)
);