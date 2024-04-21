package com.ceiba.reservas.cliente.puerto;

import com.ceiba.reservas.cliente.entidad.Cliente;

public interface RepositorioCliente {

    Cliente obtener(Long id);
}
