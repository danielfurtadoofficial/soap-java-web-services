/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Tarefa;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoTarefas")
public class ServicoTarefas {

    /**
     * This is a sample web service operation
     */
    
    
    @WebMethod(operationName = "GetTarefas")
    public String getTarefas(@WebParam(name = "id_mecanico") String system_user_id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            String id = con.getMecanicoIdBySystemUserId(system_user_id);
            
            ArrayList<Tarefa> lista_tarefas = con.getTarefas(id);
            int i = 0, controlo = 0;
            
            for( ; i < lista_tarefas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<TarefaList><Tarefas>"
                            + "<Tarefa>"
                                + "<id>"+lista_tarefas.get(i).getId()+"</id>"
                                + "<descricao>"+lista_tarefas.get(i).getDescricao()+"</descricao>"
                                + "<estado>"+lista_tarefas.get(i).getEstado()+"</estado>"
                                + "<data_conclusao>"+lista_tarefas.get(i).getData_conclusao()+"</data_conclusao>"
                                + "<data_entrega>"+lista_tarefas.get(i).getData_entrega()+"</data_entrega>"
                                + "<id_veiculo_marcacao>"+lista_tarefas.get(i).getVeiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<id_mecanico>"+lista_tarefas.get(i).getId_mecanico()+"</id_mecanico>"
                            + "</Tarefa>";
                    controlo = 1;
                }else{
                    RESULT += "<Tarefa>"
                                + "<id>"+lista_tarefas.get(i).getId()+"</id>"
                                + "<descricao>"+lista_tarefas.get(i).getDescricao()+"</descricao>"
                                + "<estado>"+lista_tarefas.get(i).getEstado()+"</estado>"
                                + "<data_conclusao>"+lista_tarefas.get(i).getData_conclusao()+"</data_conclusao>"
                                + "<data_entrega>"+lista_tarefas.get(i).getData_entrega()+"</data_entrega>"
                                + "<id_veiculo_marcacao>"+lista_tarefas.get(i).getVeiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<id_mecanico>"+lista_tarefas.get(i).getId_mecanico()+"</id_mecanico>"
                            + "</Tarefa>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Tarefas></TarefaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getTarefas.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetTarefasVeiculo")
    public String getTarefasVeiculo(@WebParam(name = "id_veiculo") String id_veiculo){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<Tarefa> lista_tarefas = con.getTarefasVeiculo(id_veiculo);
            int i = 0, controlo = 0;
            
            for( ; i < lista_tarefas.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<TarefaList><Tarefas>"
                            + "<Tarefa>"
                                + "<id>"+lista_tarefas.get(i).getId()+"</id>"
                                + "<descricao>"+lista_tarefas.get(i).getDescricao()+"</descricao>"
                                + "<estado>"+lista_tarefas.get(i).getEstado()+"</estado>"
                                + "<data_conclusao>"+lista_tarefas.get(i).getData_conclusao()+"</data_conclusao>"
                                + "<data_entrega>"+lista_tarefas.get(i).getData_entrega()+"</data_entrega>"
                                + "<id_veiculo_marcacao>"+lista_tarefas.get(i).getVeiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<id_mecanico>"+lista_tarefas.get(i).getId_mecanico()+"</id_mecanico>"
                            + "</Tarefa>";
                    controlo = 1;
                }else{
                    RESULT += "<Tarefa>"
                                + "<id>"+lista_tarefas.get(i).getId()+"</id>"
                                + "<descricao>"+lista_tarefas.get(i).getDescricao()+"</descricao>"
                                + "<estado>"+lista_tarefas.get(i).getEstado()+"</estado>"
                                + "<data_conclusao>"+lista_tarefas.get(i).getData_conclusao()+"</data_conclusao>"
                                + "<data_entrega>"+lista_tarefas.get(i).getData_entrega()+"</data_entrega>"
                                + "<id_veiculo_marcacao>"+lista_tarefas.get(i).getVeiculo_marcacao()+"</id_veiculo_marcacao>"
                                + "<id_mecanico>"+lista_tarefas.get(i).getId_mecanico()+"</id_mecanico>"
                            + "</Tarefa>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Tarefas></TarefaList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getTarefas.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "ConcluirTarefa")
    public String concluirTarefa(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.concluirTarefa(id))
                RESULT = "true";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice concluirTarefa.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "CriarTarefa")
    public String criarTarefa(@WebParam(name = "descricao") String descricao, @WebParam(name = "id_veiculo_marcacao") String id_veiculo_marcacao, @WebParam(name = "id_mecanico") String id_mecanico){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.criarTarefa(descricao, id_veiculo_marcacao, id_mecanico))
                RESULT = "true";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice criarTarefa.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

