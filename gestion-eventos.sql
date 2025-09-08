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

insert into Organizadores (nombreOrganizador, correo, contrasena) values
('María Gómez', 'maria.gomez@eventos.com', 'pass1234'),
('Luis Fernández', 'luis.fernandez@eventos.com', 'segura567'),
('Ana Torres', 'ana.torres@eventos.com', 'clave987'),
('Carlos Méndez', 'carlos.mendez@eventos.com', 'password321'),
('Sofía Ramírez', 'sofia.ramirez@eventos.com', 'admin456');

insert into Eventos (nombreEvento, descripcion, fechaEvento, ubicacion) values
('Tech Summit 2025', 'Conferencia de tecnología e innovación', '2025-10-15', 'Centro de Convenciones, CDMX'),
('Feria del Libro', 'Exposición y venta de libros nacionales e internacionales', '2025-11-01', 'Expo Guadalajara'),
('Concierto RockFest', 'Festival de bandas de rock nacionales', '2025-09-25', 'Parque Fundidora, Monterrey'),
('Startup Pitch', 'Competencia de pitches de startups emergentes', '2025-10-05', 'WeWork Reforma, CDMX'),
('Festival Gastronómico', 'Muestra culinaria de diversas regiones', '2025-12-12', 'Plaza Central, Puebla');

insert into Usuarios (nombreUsuario, correo, contrasena) values
('Juan Pérez', 'juan.perez@gmail.com', 'ju4npass'),
('Laura Ríos', 'laura.rios@hotmail.com', 'l4ur4clave'),
('Andrés Soto', 'andres.soto@yahoo.com', 's0t0andres'),
('Gabriela Luna', 'gabriela.luna@outlook.com', 'gabpass321'),
('Ricardo Díaz', 'ricardo.diaz@gmail.com', 'rd123456');

insert into Tickets (idEvento, idUsuario, precio, estado) values
(1, 1, 500.00, 'Vendido'),
(2, 2, 150.00, 'Usado'),
(3, 3, 750.00, 'Disponible'),
(4, 4, 100.00, 'Vendido'),
(5, 5, 200.00, 'Disponible');



select * from Usuarios;
select * from Organizadores;
select * from Tickets;
select * from Eventos;


