UPDATE reserva_hotel
SET fecha_entrada = :fecha_entrada,
    fecha_salida = :fecha_salida,
    hotel_id = :hotel_id,
    habitacion_id = :habitacion_id,
    cliente_id = :cliente_id,
    costo_total = :costo_total,
    estado = :estado
WHERE id = :id;
