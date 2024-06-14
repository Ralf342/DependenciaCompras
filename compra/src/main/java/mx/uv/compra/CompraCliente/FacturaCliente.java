package mx.uv.compra.CompraCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xx.mx.uv.consumo.wsdl.GenerarFacturaRequest;
import xx.mx.uv.consumo.wsdl.GenerarFacturaResponse;
import xx.mx.uv.consumo.wsdl.RecuperarFacturarRequest;
import xx.mx.uv.consumo.wsdl.RecuperarFacturarResponse;

public class FacturaCliente extends WebServiceGatewaySupport{

    @Autowired
     private Jaxb2Marshaller marshallerCompra;

     public RecuperarFacturarResponse solicitarFactura(RecuperarFacturarRequest request){
        try{
            return (RecuperarFacturarResponse) getWebServiceTemplate()
            .marshalSendAndReceive(request, new SoapActionCallback("https://facturasbasefinal-production.up.railway.app/ws/facturas"));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        } 
     }

     public GenerarFacturaResponse generFacturarResponse(GenerarFacturaRequest request){
        try{
            return (GenerarFacturaResponse) getWebServiceTemplate()
            .marshalSendAndReceive(request, new SoapActionCallback("https://facturasbasefinal-production.up.railway.app/ws/facturas"));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        } 
     }
}
