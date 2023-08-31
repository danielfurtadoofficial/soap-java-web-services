/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Servico;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoServico")
public class ServicoServico {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "GetServico")
    public String getServico(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            Servico servico = con.getServico(id);
            
            
            if(servico != null)
                RESULT = "<ServicoList><Servicos>"
                            + "<Servico>"
                                + "<id>"+servico.getId()+"</id>"
                                + "<nome>"+servico.getNome()+"</nome>"
                                + "<descricao>"+servico.getDescricao()+"</descricao>"
                            + "</Servico>"
                        + "</Servicos></ServicoList>";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getServico.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

