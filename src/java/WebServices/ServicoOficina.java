/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Oficina;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoOficina")
public class ServicoOficina {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "GetOficina")
    public String getOficina(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            Oficina oficina = con.getOficina(id);
            
            if(oficina != null)
                RESULT = "<OficinaList><Oficinas>"
                            + "<Oficina>"
                                + "<id>"+oficina.getId()+"</id>"
                                + "<nome>"+oficina.getNome()+"</nome>"
                                + "<ilha>"+oficina.getIlha()+"</ilha>"
                                + "<cidade>"+oficina.getCidade()+"</cidade>"
                                + "<localidade>"+oficina.getLocalidade()+"</localidade>"
                            + "</Oficina>"
                        + "</Oficinas></OficinaList>";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getOficina.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

