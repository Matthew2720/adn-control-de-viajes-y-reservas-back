package com.ceiba.reservas.factura.puerto.dao;

import com.ceiba.reservas.factura.modelo.dto.ResumenFacturaDTO;

import java.util.List;

public interface DaoFactura {

    List<ResumenFacturaDTO> obtenerResumenDeFacturasAnuladas();
}
