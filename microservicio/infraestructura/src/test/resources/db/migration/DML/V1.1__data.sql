INSERT INTO  cliente(id, nombre, tipo_cliente) VALUES(1,'Cliente 1','PREFERENCIAL');
INSERT INTO  cliente(id, nombre, tipo_cliente) VALUES(2,'Cliente 2','COMUN');
INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, asientos_disponibles, precio_base)
VALUES ('MEDELLIN', 'BOGOTA', '2023-08-15 09:00:00', '2023-08-20 12:00:00', 100, 150.00);
INSERT INTO vuelo (origen, destino, fecha_salida, fecha_llegada, asientos_disponibles, precio_base)
VALUES ('CALI', 'CARTAGENA', '2023-08-05 15:30:00', '2023-08-15 18:45:00', 50, 200.00);
INSERT INTO reserva_vuelo (id_cliente, id_vuelo, fecha_hora_reserva, pasajeros, precio_final, estado_reserva)
VALUES (1, 1, '2023-08-09 14:00:00', 2, 250.00, 'ACTIVA');
INSERT INTO reserva_vuelo (id_cliente, id_vuelo, fecha_hora_reserva, pasajeros, precio_final, estado_reserva)
VALUES (2, 2, '2023-08-14 10:30:00', 3, 500.00, 'ACTIVA');


insert into producto(id, nombre, aplica_iva, valor) values(1,'Producto 1',1, 25000);
insert into producto(id, nombre, aplica_iva, valor) values(2,'Producto 2',0, 10000);
insert into factura(id_cliente, valor_total, estado ) values(1, 25000, 'ACTIVA');
insert into factura(id_cliente, valor_total, estado ) values(2, 3000, 'ANULADA');
insert into producto_facturar( id_factura, id_producto, cantidad ) values(1, 1, 2);

INSERT INTO hotel (nombre, ubicacion, costo_por_noche)
VALUES ('Hotel La Maria', 'MEDELLIN', 100.00),
       ('Hotel Hilton', 'BOGOTA', 150.00),
       ('Hotel La Carla', 'MEDELLIN', 200.00);

INSERT INTO habitacion (numero_habitacion, disponibilidad, hotel_idu)
VALUES ('101', true, 1),
       ('102', true, 1),
       ('103', false, 1),
       ('201', true, 2),
       ('202', false, 2),
       ('203', true, 2),
       ('301', true, 3),
       ('302', true, 3),
       ('303', false, 3);

INSERT INTO reserva_hotel (fecha_entrada, fecha_salida, hotel_id, habitacion_id, cliente_id, costo_total, estado)
VALUES ('2023-11-01T18:01:51', '2023-11-15T18:01:51', 1, 1, 1, 400.00, 'RESERVADO'),
       ('2023-08-25T18:01:51', '2023-08-27T18:01:51', 2, 5, 2, 750.00, 'RESERVADO'),
       ('2023-08-29T18:01:51', '2023-08-30T18:01:51', 3, 7, 2, 1000.00, 'RESERVADO');
