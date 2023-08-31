/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import classes.Mecanico;
import classes.MecanicoEfetivo;
import classes.MecanicoEstagiario;
import classes.Peca;
import classes.PecaUtilizada;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "ServicoMecanico")
public class ServicoMecanico {
    
    @WebMethod(operationName = "GetMecanicosOficina")
    public String getMecanicosOficina(@WebParam(name = "id_oficina") String id_oficina){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<Mecanico> lista_mecanico = con.getMecanicosOficina(id_oficina);
            int i = 0, controlo = 0;
            
            for( ; i < lista_mecanico.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<MecanicoList><Mecanicos>"
                            + "<Mecanico>"
                                + "<id>"+lista_mecanico.get(i).getId()+"</id>"
                                + "<bi>"+lista_mecanico.get(i).getBi()+"</bi>"
                                + "<efetivo>"+lista_mecanico.get(i).getEfetivo()+"</efetivo>"
                                + "<id_user>"+lista_mecanico.get(i).getId_user()+"</id_user>"
                                + "<nome>"+lista_mecanico.get(i).getNome()+"</nome>"
                                + "<username>"+lista_mecanico.get(i).getUsername()+"</username>"
                                + "<email>"+lista_mecanico.get(i).getEmail()+"</email>"
                                + "<telefone>"+lista_mecanico.get(i).getTelefone()+"</telefone>"
                            + "</Mecanico>";
                    controlo = 1;
                }else{
                    RESULT += "<Mecanico>"
                                + "<id>"+lista_mecanico.get(i).getId()+"</id>"
                                + "<bi>"+lista_mecanico.get(i).getBi()+"</bi>"
                                + "<efetivo>"+lista_mecanico.get(i).getEfetivo()+"</efetivo>"
                                + "<id_user>"+lista_mecanico.get(i).getId_user()+"</id_user>"
                                + "<nome>"+lista_mecanico.get(i).getNome()+"</nome>"
                                + "<username>"+lista_mecanico.get(i).getUsername()+"</username>"
                                + "<email>"+lista_mecanico.get(i).getEmail()+"</email>"
                                + "<telefone>"+lista_mecanico.get(i).getTelefone()+"</telefone>"
                            + "</Mecanico>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</Mecanicos></MecanicoList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getMecanicoOficina.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetMecanicoEfetivo")
    public String getMecanicoEfetivo(@WebParam(name = "id_mecanico") String id_mecanico){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            MecanicoEfetivo lista_mecanico = con.getMecanicoEfetivo(id_mecanico);
            
            RESULT = "<MecanicoEfetivoList><MecanicosEfetivos>"
                    + "<MecanicoEfetivo>"
                        + "<id>"+lista_mecanico.getId()+"</id>"
                        + "<id_mecanico>"+lista_mecanico.getId_mecanico()+"</id_mecanico>"
                        + "<n_inps>"+lista_mecanico.getN_inps()+"</n_inps>"
                        + "<nif>"+lista_mecanico.getNif()+"</nif>"
                        + "<salario>"+lista_mecanico.getSalario()+"</salario>"
                    + "</MecanicoEfetivo></MecanicosEfetivos></MecanicoEfetivoList>";
            
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getMecanicoEfetivo.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetMecanicoEstagiario")
    public String getMecanicoEstagiario(@WebParam(name = "id_mecanico") String id_mecanico){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            MecanicoEstagiario lista_mecanico = con.getMecanicoEstagiario(id_mecanico);
            
            RESULT = "<MecanicoEstagiarioList><MecanicosEstagiarios>"
                    + "<MecanicoEstagiario>"
                        + "<id>"+lista_mecanico.getId()+"</id>"
                        + "<id_mecanico>"+lista_mecanico.getId_mecanico()+"</id_mecanico>"
                        + "<data_inicio>"+lista_mecanico.getData_inicio()+"</data_inicio>"
                        + "<data_fim>"+lista_mecanico.getData_fim()+"</data_fim>"
                    + "</MecanicoEstagiario></MecanicosEstagiarios></MecanicoEstagiarioList>";
                    
              
        }catch(Exception ex){
            System.err.println("Erro no webservice getMecanicoEstagiario.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "getMecanicoIdFromSystemUserId")
    public String getMecanicoIdFromSystemUserId(@WebParam(name = "system_user_id") String system_user_id){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            RESULT = con.getMecanicoIdFromSystemUserId(system_user_id);
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getMecanicoIdFromSystemUserId.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    
    @WebMethod(operationName = "RegistrarMecanicoEfetivo")
    public String registrarMecanicoEfetivo(@WebParam(name = "id_oficina") String id_oficina, @WebParam(name = "bi") String bi, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "nome") String nome, @WebParam(name = "email") String email, @WebParam(name = "telefone") String telefone, @WebParam(name = "n_inps") String n_inps, @WebParam(name = "niif") String nif, @WebParam(name = "salario") String salario){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            RESULT = con.registrarMecanicoEfetivo(id_oficina, bi, username, password, nome, email, telefone, n_inps, nif, salario);
            
        }catch(Exception ex){
            System.err.println("Erro no webservice registrarMecanicoEfetivo.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "RegistrarMecanicoEstagiario")
    public String registrarMecanicoEstagiario(@WebParam(name = "id_oficina") String id_oficina, @WebParam(name = "bi") String bi, @WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "nome") String nome, @WebParam(name = "email") String email, @WebParam(name = "telefone") String telefone, @WebParam(name = "data_inicio") String data_inicio, @WebParam(name = "data_fim") String data_fim){
        
        String RESULT = "No records found";
        
        try{
            ConnectarBD con = new ConnectarBD();
            RESULT = con.registrarMecanicoEstagiario(id_oficina, bi, username, password, nome, email, telefone, data_inicio, data_fim);
            
        }catch(Exception ex){
            System.err.println("Erro no webservice registrarMecanicoEstagiario.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
   
    
}

