/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoReservar")
public class ServicoReservar {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "Reservar")
    public String reservar(@WebParam(name = "nome") String nome, @WebParam(name = "email") String email, @WebParam(name = "telefone") String telefone, @WebParam(name = "servico") int servico, @WebParam(name = "localidade") int localidade, @WebParam(name = "data") String data, @WebParam(name = "matricula") String matricula){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            if(!con.reservaDayQuantityValidation(data, String.valueOf(localidade))){
                RESULT = "Reservas fechadas para essa data nessa oficina.";
            }else{
            
                if(!con.reservaIgual(data, matricula)){
                    if(con.reservar(nome, email, telefone, servico, localidade, data, matricula))
                    RESULT = "True";
                }else{
                    RESULT = "Já existe uma reserva para esse carro nesse dia.";
                }
            }
            
            
        }catch(Exception ex){
            System.err.println("Erro no webservice reservar.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "CancelarReserva")
    public String cancelarReserva(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.cancelarReserva(id)){
                RESULT = "True";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice cancelarReserva.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

