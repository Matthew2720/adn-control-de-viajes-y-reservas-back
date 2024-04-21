package com.ceiba.reservas.vuelo.puerto.repositorio;

import com.ceiba.reservas.vuelo.modelo.entidad.Vuelo;

public interface RepositorioVuelo {
    void actualizar(Vuelo vuelo);
    Vuelo obtener(Long id);
}
