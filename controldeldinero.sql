create database controldedinero;
use controldedinero;
create table delegaciones(
id int not null auto_increment,
Cedula int (10) not null,
Nombre varchar (35) not null,
Telefono int (11) not null,
Correo Varchar (50) not null,
Usuario varchar (20) not null,
Contraseña varchar (20) not null,
primary key (id)
);

create table empleados(
id int not null auto_increment,
Cedula int (10) not null,
Nombre varchar (35) not null,
Telefono int (11) not null,
Correo Varchar (50) not null,
Usuario varchar (20) not null,
Contraseña varchar (20) not null,
primary key(id)
);

create table transacciones(
id int not null auto_increment,
primary key (id),
Fecha varchar (10) not null,
Consignacion decimal (15) not null,
porformalizar decimal (15) not null,
Formalizado varchar (100) not null,
Estado Varchar (7) not null,
FK_empleados_id int (11) not null,
FOREIGN KEY (FK_empleados_id) REFERENCES 
empleados (id),
FK_delegaciones_id int (11) not null,
FOREIGN KEY (FK_empleados_id) REFERENCES 
empleados (id)
);


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);