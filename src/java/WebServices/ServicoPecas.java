/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Peca;
import classes.PecaUtilizada;
import classes.Tarefa;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoPecas")
public class ServicoPecas {
    
    @WebMethod(operationName = "GetPecasOficina")
    public String getPecasOficina(@WebParam(name = "id_oficina") String id_oficina){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<Peca> lista_pecas = con.getPecasOficina(id_oficina);
            int i = 0, controlo = 0;
            
            for( ; i < lista_pecas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<PecaList><Pecas>"
                            + "<Peca>"
                                + "<id>"+lista_pecas.get(i).getId()+"</id>"
                                + "<quantidade>"+lista_pecas.get(i).getQuantidade()+"</quantidade>"
                                + "<id_oficina>"+lista_pecas.get(i).getId_oficina()+"</id_oficina>"
                                + "<nome>"+lista_pecas.get(i).getNome()+"</nome>"
                            + "</Peca>";
                    controlo = 1;
                }else{
                    RESULT += "<Peca>"
                                + "<id>"+lista_pecas.get(i).getId()+"</id>"
                                + "<quantidade>"+lista_pecas.get(i).getQuantidade()+"</quantidade>"
                                + "<id_oficina>"+lista_pecas.get(i).getId_oficina()+"</id_oficina>"
                                + "<nome>"+lista_pecas.get(i).getNome()+"</nome>"
                            + "</Peca>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Pecas></PecaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getPecasOficina.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetPecasTarefa")
    public String getPecasTarefa(@WebParam(name = "id_tarefa") String id_tarefa){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<PecaUtilizada> lista_pecasUtilizadas = con.getPecasTarefa(id_tarefa);
            int i = 0, controlo = 0;
            
            for( ; i < lista_pecasUtilizadas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<PecaUtilizadaList><PecasUtilizadas>"
                            + "<PecaUtilizada>"
                                + "<id>"+lista_pecasUtilizadas.get(i).getId()+"</id>"
                                + "<quantidade>"+lista_pecasUtilizadas.get(i).getQuantidade()+"</quantidade>"
                                + "<id_tarefa>"+lista_pecasUtilizadas.get(i).getId_tarefa()+"</id_tarefa>"
                                + "<id_peca>"+lista_pecasUtilizadas.get(i).getId_peca()+"</id_peca>"
                                + "<nome_peca>"+lista_pecasUtilizadas.get(i).getNome_peca()+"</nome_peca>"
                            + "</PecaUtilizada>";
                    controlo = 1;
                }else{
                    RESULT += "<PecaUtilizada>"
                                + "<id>"+lista_pecasUtilizadas.get(i).getId()+"</id>"
                                + "<quantidade>"+lista_pecasUtilizadas.get(i).getQuantidade()+"</quantidade>"
                                + "<id_tarefa>"+lista_pecasUtilizadas.get(i).getId_tarefa()+"</id_tarefa>"
                                + "<id_peca>"+lista_pecasUtilizadas.get(i).getId_peca()+"</id_peca>"
                                + "<nome_peca>"+lista_pecasUtilizadas.get(i).getNome_peca()+"</nome_peca>"
                            + "</PecaUtilizada>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</PecasUtilizadas></PecaUtilizadaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getPecasTarefa.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "RegistrarPeca")
    public String registrarPeca(@WebParam(name = "nome") String nome, @WebParam(name = "quantidade") String quantidade, @WebParam(name = "id_oficina") String id_oficina){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.registrarPeca(nome, quantidade, id_oficina)){
                RESULT = "True";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice registrarPeca.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "AumentarPeca")
    public String aumentarPeca(@WebParam(name = "id") String id, @WebParam(name = "quantidade") String quantidade){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.aumentarPeca(id, quantidade)){
                RESULT = "True";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice aumentarPeca.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    
    @WebMethod(operationName = "CreatePecaUtilizada")
    public String createPecaUtilizada(@WebParam(name = "id_tarefa") String id_tarefa, @WebParam(name = "quantidade") String quantidade, @WebParam(name = "id_peca") String id_peca){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            RESULT = con.createPecaUtilizada(id_tarefa, quantidade, id_peca);
            
        }catch(Exception ex){
            System.err.println("Erro no webservice CreatePecaUtilizada.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

