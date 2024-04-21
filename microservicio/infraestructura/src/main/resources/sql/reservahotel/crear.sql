INSERT INTO reserva_hotel
(fecha_entrada, fecha_salida, hotel_id, habitacion_id, cliente_id, costo_total, estado)
VALUES
(:fecha_entrada, :fecha_salida, :hotel_id, :habitacion_id, :cliente_id, :costo_total, :estado)