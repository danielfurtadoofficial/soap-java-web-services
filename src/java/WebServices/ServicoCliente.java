/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Cliente;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoCliente")
public class ServicoCliente {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "GetCliente")
    public String getCliente(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            Cliente cliente = con.getCliente(id);
            
            if(cliente != null)
                RESULT = "<ClienteList><Clientes>"
                            + "<Cliente>"
                                + "<id>"+cliente.getId()+"</id>"
                                + "<nome>"+cliente.getNome()+"</nome>"
                                + "<email>"+cliente.getEmail()+"</email>"
                                + "<telefone>"+cliente.getTelefone()+"</telefone>"
                            + "</Cliente>"
                        + "</Clientes></ClienteList>";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getCliente.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

