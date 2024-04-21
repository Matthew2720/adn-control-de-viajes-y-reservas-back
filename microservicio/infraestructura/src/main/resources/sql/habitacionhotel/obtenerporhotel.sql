select *
from habitacion
where hotel_idu = :id_hotel
and disponibilidad = true;