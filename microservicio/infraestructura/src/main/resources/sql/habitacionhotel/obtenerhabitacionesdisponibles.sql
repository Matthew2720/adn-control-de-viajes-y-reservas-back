SELECT h.*
FROM hotel h
JOIN habitacion hb
ON h.id = hb.hotel_idu
WHERE hb.disponibilidad = true;