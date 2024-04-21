package com.ceiba.reservas.factura;

import com.ceiba.reservas.core.BasePrueba;
import com.ceiba.reservas.cliente.ClienteTestDataBuilder;
import com.ceiba.reservas.cliente.entidad.TipoCliente;
import com.ceiba.reservas.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reservas.factura.modelo.entidad.EstadoFactura;
import com.ceiba.reservas.factura.modelo.entidad.Factura;
import com.ceiba.reservas.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.reservas.factura.servicio.ServicioAnular;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class ServicioAnularFacturaTest {

    @Test
    void deberiaAnularExitosamente() {
        var repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.guardar(Mockito.any())).thenReturn(1l);
        var factura = new FacturaTestDataBuilder().conFacturaPorDefecto()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto()
                        .conTipoCliente(TipoCliente.ESPECIAL).reconstruir())
                .conEstado(EstadoFactura.ACTIVA).reconstruir();
        var servicioAnular = new ServicioAnular(repositorioFactura);

        servicioAnular.ejecutar(factura);

        ArgumentCaptor<Factura> captorFactura = ArgumentCaptor.forClass(Factura.class);
        Mockito.verify(repositorioFactura, Mockito.times(1)).actualizarEstado(captorFactura.capture());

        Assertions.assertTrue(captorFactura.getValue().esAnulada());
    }

    @Test
    void anularFacturaNullDeberiaLanzarError() {
        var repositorioFactura = Mockito.mock(RepositorioFactura.class);
        var servicioAnular = new ServicioAnular(repositorioFactura);

        BasePrueba.assertThrows(() -> servicioAnular.
                ejecutar(null), ExcepcionValorObligatorio.class, "No existe una factura para anular");


    }
}
