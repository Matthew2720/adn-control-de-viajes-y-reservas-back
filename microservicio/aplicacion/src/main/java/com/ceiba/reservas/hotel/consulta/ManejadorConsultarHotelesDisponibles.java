package com.ceiba.reservas.hotel.consulta;

import com.ceiba.reservas.hotel.modelo.entidad.Hotel;
import com.ceiba.reservas.hotel.puerto.dao.HotelDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarHotelesDisponibles {

    private final HotelDAO hotelDAO;

    public ManejadorConsultarHotelesDisponibles(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<Hotel> ejecutar(){
        return hotelDAO.obtenerHotelesDisponibles();
    }
}
