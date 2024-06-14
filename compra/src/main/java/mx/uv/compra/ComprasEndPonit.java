package mx.uv.compra;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mx.uv.compra.CompraCliente.FacturaCliente;
import mx.uv.t4is.compra.SolicitarFacturaRequest;
import mx.uv.t4is.compra.SolicitarFacturaResponse;
import mx.uv.t4is.compra.SolicitarUUIDRequest;
import mx.uv.t4is.compra.SolicitarUUIDResponse;
import xx.mx.uv.consumo.wsdl.GenerarFacturaRequest;
import xx.mx.uv.consumo.wsdl.GenerarFacturaResponse;

@Endpoint
public class ComprasEndPonit {
    
    @Autowired
    FacturaCliente facturaCliente;

    ArrayList<String> ultimoUuid = new ArrayList<String>();


    @PayloadRoot(localPart = "SolicitarFacturaRequest", namespace = "t4is.uv.mx/compra")
    @ResponsePayload
    public SolicitarFacturaResponse solicitarFactura(@RequestPayload SolicitarFacturaRequest peticion){
        SolicitarFacturaResponse respuesta = new SolicitarFacturaResponse();

        GenerarFacturaRequest facturaGenerada = new GenerarFacturaRequest();
        facturaGenerada.setFecha(peticion.getFecha());
        facturaGenerada.setFormaDePago(peticion.getFormaPago());
        facturaGenerada.setSubtotal(peticion.getSubtotal());
        facturaGenerada.setNombre(peticion.getNombre());
        facturaGenerada.setCantidad(peticion.getCantidad());
        facturaGenerada.setPrecioUnitario(peticion.getPrecio());
        facturaGenerada.setRfc(peticion.getRfc());
        facturaGenerada.setRegimenFiscalReceptor(peticion.getRegimen());
        facturaGenerada.setDomicilioFiscalReceptor(peticion.getDomicilio());
        facturaGenerada.setDescripcion(peticion.getDescripcion());
        GenerarFacturaResponse generarFactura = facturaCliente.generFacturarResponse(facturaGenerada);
        System.out.println(generarFactura);
        respuesta.setUuid(generarFactura.getUDDI());
        ultimoUuid.add(generarFactura.getUDDI());
        return respuesta;
    }

     @PayloadRoot(localPart = "SolicitarUUIDRequest", namespace = "t4is.uv.mx/compra")
    @ResponsePayload
    public SolicitarUUIDResponse generarFolio(@RequestPayload SolicitarUUIDRequest peticion){
        SolicitarUUIDResponse respuesta = new SolicitarUUIDResponse();
        SolicitarUUIDRequest mensaje = new SolicitarUUIDRequest();

        mensaje.setSolicitud(peticion.getSolicitud());
        respuesta.setUuid(null);
        return respuesta;
    }
}
