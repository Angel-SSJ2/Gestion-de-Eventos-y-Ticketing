drop database if exists gestion_eventos_db;
create database gestion_eventos_db;
use gestion_eventos_db;

create table Organizadores(
	idOrganizador int auto_increment not null,
    nombreEmpresa varchar(45) not null,
    correo varchar(128) not null,
	constraint pk_Organizador primary key (idOrganizador)
);

create table Eventos (
	idEvento int auto_increment not null,
    idOrganizador int not null,
    nombreEvento varchar(45) not null,
    descripcion varchar(88) not null,
    fechaEvento Date not null,
    ubicacion varchar(125) not null,
    constraint pk_Evento primary key (idEvento)
);

create table Usuarios(
	idUsuario int auto_increment not null,
    nombreUsuario varchar(45) not null,
    correo varchar(128) not null,
    constraint pk_Usuario primary key (idUsuario)
);

create table Tickets(
	idTicket int auto_increment not null,
    idEvento int not null,
    idUsuario int not null,
	codigoQr varchar(45) not null,
    precio decimal(10, 2) not null,
    estado enum('Vendido','Usado', 'Disponible') default 'Disponible',
    constraint pk_Ticket primary key (idTicket)
);