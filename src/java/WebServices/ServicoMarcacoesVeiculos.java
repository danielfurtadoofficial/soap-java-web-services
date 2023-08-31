/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import classes.VeiculoMarcacao;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "GetMarcacoesVeiculos")
public class ServicoMarcacoesVeiculos {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "GetMarcacoesVeiculosOficina")
    public String getMarcacoesVeiculosOficina(@WebParam(name = "id_oficina") String id_oficina){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            ArrayList<VeiculoMarcacao> VM = con.getMarcacoesVeiculosOficina(id_oficina);
            int i = 0, controlo = 0;
            
            for( ; i < VM.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<VeiculoMarcacaoList><VeiculosMarcacoes>"
                            + "<VeiculoMarcacao>"
                                + "<id>"+VM.get(i).getId()+"</id>"
                                + "<id_cliente>"+VM.get(i).getId_cliente()+"</id_cliente>"
                                + "<id_servico>"+VM.get(i).getId_servico()+"</id_servico>"
                                + "<id_oficina>"+VM.get(i).getId_oficina()+"</id_oficina>"
                                + "<data_entrada>"+VM.get(i).getData_entrada()+"</data_entrada>"
                                + "<data_saida>"+VM.get(i).getData_saida()+"</data_saida>"
                                + "<estado>"+VM.get(i).getEstado()+"</estado>"
                                + "<matricula>"+VM.get(i).getMatricula()+"</matricula>"
                                + "<descricao>"+VM.get(i).getDescricao()+"</descricao>"
                                + "<info_riscos_entrada>"+VM.get(i).getInfo_riscos_entrada()+"</info_riscos_entrada>"
                            + "</VeiculoMarcacao>";
                    controlo = 1;
                }else{
                    RESULT += "<VeiculoMarcacao>"
                                + "<id>"+VM.get(i).getId()+"</id>"
                                + "<id_cliente>"+VM.get(i).getId_cliente()+"</id_cliente>"
                                + "<id_servico>"+VM.get(i).getId_servico()+"</id_servico>"
                                + "<id_oficina>"+VM.get(i).getId_oficina()+"</id_oficina>"
                                + "<data_entrada>"+VM.get(i).getData_entrada()+"</data_entrada>"
                                + "<data_saida>"+VM.get(i).getData_saida()+"</data_saida>"
                                + "<estado>"+VM.get(i).getEstado()+"</estado>"
                                + "<matricula>"+VM.get(i).getMatricula()+"</matricula>"
                                + "<descricao>"+VM.get(i).getDescricao()+"</descricao>"
                                + "<info_riscos_entrada>"+VM.get(i).getInfo_riscos_entrada()+"</info_riscos_entrada>"
                            + "</VeiculoMarcacao>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</VeiculosMarcacoes></VeiculoMarcacaoList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice SystemUsersServices.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }

    @WebMethod(operationName = "GetSingleMarcacaoVeiculo")
    public String getSingleMarcacaoVeiculo(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
       
        try{
            
            ConnectarBD con = new ConnectarBD();
            ArrayList<VeiculoMarcacao> VM = con.getMarcacaoVeiculo(id);
            
            if(VM != null)
                RESULT = "<VeiculoMarcacaoList><VeiculosMarcacoes>"
                            + "<VeiculoMarcacao>"
                                + "<id>"+VM.get(0).getId()+"</id>"
                                + "<id_cliente>"+VM.get(0).getId_cliente()+"</id_cliente>"
                                + "<id_servico>"+VM.get(0).getId_servico()+"</id_servico>"
                                + "<id_oficina>"+VM.get(0).getId_oficina()+"</id_oficina>"
                                + "<data_entrada>"+VM.get(0).getData_entrada()+"</data_entrada>"
                                + "<data_saida>"+VM.get(0).getData_saida()+"</data_saida>"
                                + "<estado>"+VM.get(0).getEstado()+"</estado>"
                                + "<matricula>"+VM.get(0).getMatricula()+"</matricula>"
                                + "<descricao>"+VM.get(0).getDescricao()+"</descricao>"
                                + "<info_riscos_entrada>"+VM.get(0).getInfo_riscos_entrada()+"</info_riscos_entrada>"
                            + "</VeiculoMarcacao>"
                        + "</VeiculosMarcacoes></VeiculoMarcacaoList>";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getMarcacaoVeiculo.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "GetMarcacaoVeiculoMatricula")
    public String getMarcacaoVeiculoMatricula(@WebParam(name = "id") String id){
        
        String RESULT = "No records found";
       
        try{
            
            ConnectarBD con = new ConnectarBD();
            ArrayList<VeiculoMarcacao> VM = con.getMarcacaoVeiculo(id);
            
            if(VM != null)
                RESULT = VM.get(0).getMatricula();
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getMarcacaoVeiculoMatricula.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "EfetuarEntrada")
    public String efetuarEntrada(@WebParam(name = "id") String id, @WebParam(name = "descricao") String descricao, @WebParam(name = "info_riscos") String info_riscos){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            if(con.efetuarEntrada(id, descricao, info_riscos)){
                RESULT = "True";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice efetuarEntrada.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
    
    @WebMethod(operationName = "EfetuarSaida")
    public String efetuarSaida(@WebParam(name = "id") String id, @WebParam(name = "descricao") String descricao, @WebParam(name = "custo_total") String custo_total){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            RESULT = con.efetuarSaida(id, descricao, custo_total);
            
        }catch(Exception ex){
            System.err.println("Erro no webservice efetuarEntrada.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

