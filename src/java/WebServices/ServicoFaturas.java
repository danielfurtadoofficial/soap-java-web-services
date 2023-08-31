/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Fatura;
import classes.Tarefa;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoFaturas")
public class ServicoFaturas {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "GetFaturasOficina")
    public String getFaturasOficina(@WebParam(name = "id_oficina") String id_oficina){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<Fatura> lista_faturas = con.getFaturasOficina(id_oficina);
            int i = 0, controlo = 0;
            
            for( ; i < lista_faturas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<FaturaList><Faturas>"
                            + "<Fatura>"
                                + "<id>"+lista_faturas.get(i).getId()+"</id>"
                                + "<id_veiculo_marcacao>"+lista_faturas.get(i).getId_veiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<custo_total>"+lista_faturas.get(i).getCusto_total()+"</custo_total>"
                                + "<descricao>"+lista_faturas.get(i).getDescricao()+"</descricao>"
                            + "</Fatura>";
                    controlo = 1;
                }else{
                    RESULT += "<Fatura>"
                                + "<id>"+lista_faturas.get(i).getId()+"</id>"
                                + "<id_veiculo_marcacao>"+lista_faturas.get(i).getId_veiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<custo_total>"+lista_faturas.get(i).getCusto_total()+"</custo_total>"
                                + "<descricao>"+lista_faturas.get(i).getDescricao()+"</descricao>"
                            + "</Fatura>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Faturas></FaturaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getFaturasOficina.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetFaturaVeiculo")
    public String getFaturaVeiculo(@WebParam(name = "id_veiculo") String id_veiculo){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<Fatura> lista_faturas = con.getFaturaVeiculo(id_veiculo);
            int i = 0, controlo = 0;
            
            for( ; i < lista_faturas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<FaturaList><Faturas>"
                            + "<Fatura>"
                                + "<id>"+lista_faturas.get(i).getId()+"</id>"
                                + "<id_veiculo_marcacao>"+lista_faturas.get(i).getId_veiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<custo_total>"+lista_faturas.get(i).getCusto_total()+"</custo_total>"
                                + "<descricao>"+lista_faturas.get(i).getDescricao()+"</descricao>"
                            + "</Fatura>";
                    controlo = 1;
                }else{
                    RESULT += "<Fatura>"
                                + "<id>"+lista_faturas.get(i).getId()+"</id>"
                                + "<id_veiculo_marcacao>"+lista_faturas.get(i).getId_veiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<custo_total>"+lista_faturas.get(i).getCusto_total()+"</custo_total>"
                                + "<descricao>"+lista_faturas.get(i).getDescricao()+"</descricao>"
                            + "</Fatura>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Faturas></FaturaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getFaturaVeiculo.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
}

