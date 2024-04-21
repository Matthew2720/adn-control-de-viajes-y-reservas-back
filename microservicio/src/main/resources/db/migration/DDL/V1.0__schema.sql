
create table cliente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 tipo_cliente varchar(20) not null,
 primary key (id)
);

create table vuelo (
id int(11) not null auto_increment,
origen varchar(20) not null,
destino varchar(20) not null,
fecha_salida DATETIME not null,
fecha_llegada DATETIME not null,
asientos_disponibles int not null,
precio_base decimal(10, 2) not null,
primary key(id)
);

create table reserva_vuelo (
 id int(11) not null auto_increment,
 id_cliente int(11) not null,
 id_vuelo int(11) not null,
 fecha_hora_reserva DATETIME NOT NULL,
 pasajeros INT NOT NULL,
 precio_final DECIMAL(10, 2) NOT NULL,
 estado_reserva VARCHAR(256) NOT NULL,
 primary key (id)
);

ALTER TABLE reserva_vuelo
ADD CONSTRAINT cliente_vuelo_fk
  FOREIGN KEY (id_cliente)
  REFERENCES cliente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE reserva_vuelo
ADD CONSTRAINT vuelo_fk
FOREIGN KEY (id_vuelo)
REFERENCES vuelo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;



create table producto (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 aplica_iva TINYINT not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

create table factura (
 id int(11) not null auto_increment,
 id_cliente int(11) not null,
 valor_total DECIMAL(10,2) not null,
 estado varchar(20) not null,
 primary key (id)
);

create table producto_facturar (
 id int(11) not null auto_increment,
 id_factura int(11) not null,
 id_producto int(11) not null,
 cantidad int(11) not null,
 primary key (id)
);

ALTER TABLE factura
ADD CONSTRAINT cliente_fk
  FOREIGN KEY (id_cliente)
  REFERENCES cliente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_facturar
ADD CONSTRAINT producto_fk
  FOREIGN KEY (id_producto)
  REFERENCES producto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_facturar
ADD CONSTRAINT factura_fk
  FOREIGN KEY (id_factura)
  REFERENCES factura (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE hotel (
  id int NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(40),
  ubicacion VARCHAR(40),
  costo_por_noche DOUBLE,
  PRIMARY KEY (id));

CREATE TABLE habitacion (
  id int NOT NULL AUTO_INCREMENT,
  numero_habitacion VARCHAR(10),
  disponibilidad BOOLEAN,
  hotel_idu int,
  PRIMARY KEY (id),
  FOREIGN KEY (hotel_idu) REFERENCES Hotel(id));

CREATE TABLE reserva_hotel (
  id int NOT NULL AUTO_INCREMENT,
  fecha_entrada DATETIME,
  fecha_salida DATETIME,
  hotel_id int,
  habitacion_id int,
  cliente_id int,
  costo_total DOUBLE,
  estado VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (hotel_id) REFERENCES Hotel(id),
  FOREIGN KEY (habitacion_id) REFERENCES Habitacion(id),
  FOREIGN KEY (cliente_id) REFERENCES Cliente(id));