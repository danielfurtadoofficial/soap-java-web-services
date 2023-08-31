/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import classes.Cliente;
import classes.Fatura;
import classes.Mecanico;
import classes.MecanicoEfetivo;
import classes.MecanicoEstagiario;
import classes.Oficina;
import classes.Peca;
import classes.PecaUtilizada;
import classes.Servico;
import classes.SystemUser;
import classes.Tarefa;
import classes.VeiculoMarcacao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
/**
 *
 * @author Daniel Furtado
 */
public class ConnectarBD {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/oficina?allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=UTC&useSSL=false";
    private static final String USER = "oficina_db_user";
    private static final String PASS = "Oficina@@@";
    
    public static Connection getConnection(){

        try {
            Class.forName(DRIVER);          
            return DriverManager.getConnection(URL, USER, PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro ao connectar a base de dados: ", ex);
        }
    }
    
    public ArrayList<SystemUser> getSystemUsers(){
        
        SystemUser user = null;
        String username = "", password = "", email="", telefone="", nome="", tipo_login="";
        int id, id_oficina;
        ArrayList<SystemUser> lista_user = new ArrayList<>();
            
        try{
            Connection con = getConnection();
            Statement stmt= con.createStatement();
            ResultSet rs=stmt.executeQuery("select id, username, CAST( AES_DECRYPT(password,'chave') AS char(255)) AS password  , email, telefone, nome, tipo_login, id_oficina from system_users;");

            while(rs.next()) {

                
                id = rs.getInt("id");
                username = rs.getString("username");
                password = rs.getString("password");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                nome = rs.getString("nome");
                tipo_login = rs.getString("tipo_login");
                id_oficina = rs.getInt("id_oficina");
                
                user = new SystemUser(id, username, password, email, telefone, nome, tipo_login, id_oficina);
           
                lista_user.add(user); 

            }
        }catch(Exception ex){
            System.err.println("Error in getSystemUsers in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_user;
    }
    
    public SystemUser getLogin(String username_search, String password_search){
        
        SystemUser user = null;
        String username = "", password = "", nome="",  email="", telefone="", tipo_login="";
        int id, id_oficina;
        
        System.out.println(":::: USERNAME E PASSWORD RECEBIDOS ::::");
        System.out.println(":::: "+username_search +" "+ password_search+" ::::");
            
        try{
            Connection con = getConnection();
            String query = "SELECT id, username, CAST( AES_DECRYPT(password,'chave') AS char(255)) AS password, email, telefone, nome, tipo_login, id_oficina  FROM system_users WHERE CAST( AES_DECRYPT(password,'chave') AS char(255)) = ? AND username = ?;";
            
            System.out.println(query);
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, password_search);
            statement.setString(2, username_search);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                username = rs.getString("username");
                password = rs.getString("password");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                nome = rs.getString("nome");
                tipo_login = rs.getString("tipo_login");
                id_oficina = rs.getInt("id_oficina");
                
                user = new SystemUser(id, username, password, email, telefone, nome, tipo_login, id_oficina);
            }
        
        }catch(Exception ex){
            System.err.println("Error in getLogin in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return user;
    }

    public Boolean reservaDayQuantityValidation(String data_entrada, String id_oficina){
    
        try{
            Connection con = getConnection();
            
            String validate_query = "SELECT * from veiculos_marcacoes where data_entrada = ? and id_oficina = ? ;";
            PreparedStatement statement = con.prepareStatement(validate_query);
            statement.setString(1, data_entrada);
            statement.setString(2, id_oficina);
            ResultSet rs = statement.executeQuery();
            
            int i = 0;
            
            while(rs.next() )
                i++;
            
            System.out.println("::::::::::: iiiiiiiiii: "+i);
            
            if(i<10)
                return true;
            
        }catch(Exception ex){
            System.err.println("Error in reservaDayQuantityValidation in ConnectarBD class.");
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public Boolean reservaIgual(String data, String matricula){
    
        try{
            Connection con = getConnection();
            
            String validate_query = "SELECT * from veiculos_marcacoes where data_entrada = ? and matricula = ? ;";
            PreparedStatement statement = con.prepareStatement(validate_query);
            statement.setString(1, data);
            statement.setString(2, matricula);
            ResultSet rs = statement.executeQuery();
            
            if(rs.first() )
                return true;
            
        }catch(Exception ex){
            System.err.println("Error in reservaIgual -> ConnectarBD class.");
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public Boolean reservar(String nome, String email, String telefone, int servico, int localidade, String data, String matricula) {
        
        try{
            Connection con = getConnection();
            
            Random r = new Random();
            int low = 1000;
            int high = 1000000;
            int id_cliente = r.nextInt(high-low) + low;
            
            String query1 = "INSERT INTO clientes (id, nome, email, telefone) VALUES (?, ?, ?, ?);";
            String query2 = "INSERT INTO veiculos_marcacoes (data_entrada, estado, matricula, id_cliente, id_servico, id_oficina) VALUES (?, ?, ?, ?, ?, ?);";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, String.valueOf(id_cliente));
            statement1.setString(2, nome);
            statement1.setString(3, email);
            statement1.setString(4, telefone);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setString(1, data);
            statement2.setString(2, "Reserva");
            statement2.setString(3, matricula);
            statement2.setString(4, String.valueOf(id_cliente));
            statement2.setString(5, String.valueOf(servico));
            statement2.setString(6, String.valueOf(localidade));
            System.out.println(statement2);
            statement2.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in reservar in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public Boolean cancelarReserva(String id) {
        
        try{
            Connection con = getConnection();
            
            String query1 = "update veiculos_marcacoes set estado = 'Cancelado' where id = ? ;";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, String.valueOf(id));
            System.out.println(statement1);
            statement1.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in cancelarReserva in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public Oficina getOficina(String id){
        
        Oficina oficina = null;
        String nome="",  ilha="", cidade="", localidade="";
        int id_oficina;
        try{
            Connection con = getConnection();
            String query = "SELECT * FROM oficinas WHERE id = ?;";
            
            System.out.println(query);
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id_oficina = rs.getInt("id");
                nome = rs.getString("nome");
                ilha = rs.getString("ilha");
                cidade = rs.getString("cidade");
                localidade = rs.getString("localidade");
                
                oficina = new Oficina(id_oficina, nome, ilha, cidade, localidade);
            }
        
        }catch(Exception ex){
            System.err.println("Error in getOficina in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return oficina;
    }
    
    //::::::::::::: TAREFAS FUNCTIONS ::::::::::::::::
    public ArrayList<Tarefa> getTarefas(String id){
        
        Tarefa tarefa = null;
        String descricao = "", estado = "";
        Date data_conclusao, data_entrega;
        int id_tarefa, id_veiculo_marcacao, id_mecanico;
        ArrayList<Tarefa> lista_tarefa = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from tarefas where id_mecanico = ? order by data_entrega desc;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id_tarefa = rs.getInt("id");
                descricao = rs.getString("descricao");
                estado = rs.getString("estado");
                data_conclusao = rs.getDate("data_conclusao");
                data_entrega = rs.getDate("data_entrega");
                id_veiculo_marcacao = rs.getInt("id_veiculo_marcacao");
                id_mecanico = rs.getInt("id_mecanico");
                
                tarefa = new Tarefa(id_tarefa, descricao, estado, data_conclusao, data_entrega, id_veiculo_marcacao, id_mecanico);
           
                lista_tarefa.add(tarefa); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getTarefas in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_tarefa;
    }
    
    public ArrayList<Tarefa> getTarefasVeiculo(String id){
        
        Tarefa tarefa = null;
        String descricao = "", estado = "";
        Date data_conclusao, data_entrega;
        int id_tarefa, id_veiculo_marcacao, id_mecanico;
        ArrayList<Tarefa> lista_tarefa = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from tarefas where id_veiculo_marcacao = ? order by data_entrega desc;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id_tarefa = rs.getInt("id");
                descricao = rs.getString("descricao");
                estado = rs.getString("estado");
                data_conclusao = rs.getDate("data_conclusao");
                data_entrega = rs.getDate("data_entrega");
                id_veiculo_marcacao = rs.getInt("id_veiculo_marcacao");
                id_mecanico = rs.getInt("id_mecanico");
                
                tarefa = new Tarefa(id_tarefa, descricao, estado, data_conclusao, data_entrega, id_veiculo_marcacao, id_mecanico);
           
                lista_tarefa.add(tarefa); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getTarefasVeiculo in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_tarefa;
    }
    
    public String getMecanicoIdBySystemUserId(String system_user_id){
        
        int id = 0;
            
        try{
            Connection con = getConnection();

            String query = "select id from mecanicos where id_user = ? ;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, system_user_id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                id = rs.getInt("id");
            }
            
        }catch(Exception ex){
            System.err.println("Error in getMecanicoIdBySystemUserId in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return String.valueOf(id);
    }
    
    
    public Boolean concluirTarefa(String id){
    
        try{
            Connection con = getConnection();
            
            String query = "update tarefas set estado = ? , data_conclusao = ? where id = ? ;";
            PreparedStatement statement = con.prepareStatement(query);
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            
            statement.setString(1, "Concluido");
            statement.setString(2, dateString);
            statement.setString(3, id);
            statement.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in concluirTarefa in ConnectarBD class.");
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public Boolean criarTarefa(String descricao, String id_veiculo_marcacao, String id_mecanico){
    
        try{
            Connection con = getConnection();
            
            String query = "INSERT INTO tarefas (descricao, estado, data_entrega, id_veiculo_marcacao, id_mecanico) VALUES ( ? , ? , ? , ? , ?);";
            PreparedStatement statement = con.prepareStatement(query);
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            
            statement.setString(1, descricao);
            statement.setString(2, "Não Concluido");
            statement.setString(3, dateString);
            statement.setString(4, id_veiculo_marcacao);
            statement.setString(5, id_mecanico);
            statement.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in criarTarefa in ConnectarBD class.");
            ex.printStackTrace();
        }
        
        return false;
    }
    
    //::::::::::::::::::::: INICIO DE OPERACOES PARA MARCACOES / VEICULOS ::::::::::::::::::::::::: 
    public ArrayList<VeiculoMarcacao> getMarcacoesVeiculosOficina(String id_oficina_search){
        
        VeiculoMarcacao veiculoMarcacao = null;
        
        int id, id_cliente, id_servico, id_oficina;
        String data_entrada, data_saida, estado, matricula, descricao, info_riscos_entrada;
        VeiculoMarcacao VM = null;
        ArrayList<VeiculoMarcacao> lista_veiculoMarcacao = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from veiculos_marcacoes where id_oficina = ? order by data_entrada desc ;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_oficina_search);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_cliente = rs.getInt("id_cliente");
                id_servico = rs.getInt("id_servico");
                id_oficina = rs.getInt("id_oficina");
                data_entrada = rs.getString("data_entrada");
                data_saida = rs.getString("data_saida");
                estado = rs.getString("estado");
                matricula = rs.getString("matricula");
                descricao = rs.getString("descricao");
                info_riscos_entrada = rs.getString("info_riscos_entrada");
                
                VM = new VeiculoMarcacao(id, id_cliente, id_servico, id_oficina, data_entrada, data_saida, estado, matricula, descricao, info_riscos_entrada);
           
                lista_veiculoMarcacao.add(VM); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getMarcacoesVeiculosOficina in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_veiculoMarcacao;
    }
    
    public ArrayList<VeiculoMarcacao> getMarcacaoVeiculo(String id_search){
        
        VeiculoMarcacao veiculoMarcacao = null;
        
        int id, id_cliente, id_servico, id_oficina;
        String data_entrada, data_saida, estado, matricula, descricao, info_riscos_entrada;
        VeiculoMarcacao VM = null;
        ArrayList<VeiculoMarcacao> lista_veiculoMarcacao = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from veiculos_marcacoes where id = ?";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_search);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_cliente = rs.getInt("id_cliente");
                id_servico = rs.getInt("id_servico");
                id_oficina = rs.getInt("id_oficina");
                data_entrada = rs.getString("data_entrada");
                data_saida = rs.getString("data_saida");
                estado = rs.getString("estado");
                matricula = rs.getString("matricula");
                descricao = rs.getString("descricao");
                info_riscos_entrada = rs.getString("info_riscos_entrada");
                
                VM = new VeiculoMarcacao(id, id_cliente, id_servico, id_oficina, data_entrada, data_saida, estado, matricula, descricao, info_riscos_entrada);
           
                lista_veiculoMarcacao.add(VM); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getMarcacoesVeiculosOficina in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_veiculoMarcacao;
    }
    
    public Boolean efetuarEntrada(String id, String descricao, String info_riscos) {
        
        try{
            Connection con = getConnection();
            
            String query1 = "update veiculos_marcacoes set estado = 'Em Conserto', descricao = ?, info_riscos_entrada = ? where id = ? ;";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, String.valueOf(descricao));
            statement1.setString(2, String.valueOf(info_riscos));
            statement1.setString(3, String.valueOf(id));
            System.out.println(statement1);
            statement1.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in efetuarEntrada in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public String efetuarSaida(String id, String descricao, String custo_total) {
        
        try{
            Connection con = getConnection();
            
            String query_validate_tarefas = "select * from tarefas where estado = 'Não Concluido' and id_veiculo_marcacao = ? ;";
            
            PreparedStatement statement = con.prepareStatement(query_validate_tarefas);
            statement.setString(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            
            if(rs.first() )
                return "Ainda existem tarefas a serem terminadas!";
            
            //Info para tabela veiculos_marcacoes:
            String query1 = "update veiculos_marcacoes set estado = 'Finalizado', data_saida = ? where id = ? ;";
            PreparedStatement statement1 = con.prepareStatement(query1);
            
            String data_atual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            statement1.setString(1, data_atual);
            statement1.setString(2, id);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            //Info para tabela faturas:
            String query2 = "INSERT INTO faturas (custo_total, descricao, id_veiculo_marcacao) VALUES ( ? , ? , ? );";
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setFloat(1, Float.valueOf(custo_total));
            statement2.setString(2, descricao);
            statement2.setString(3, id);
            System.out.println(statement2);
            statement2.executeUpdate();
            
            return "True";
            
        }catch(Exception ex){
            System.err.println("Error in efetuarEntrada in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return "False";
    }
    
    //::::::::::::::::::::: FIM DE OPERACOES PARA MARCACOES / VEICULOS ::::::::::::::::::::::::: 
    
    
    public Servico getServico(String id){
        
        Servico servico = null;
        String nome="",  descricao="";
        int id_servico;
        
        try{
            Connection con = getConnection();
            String query = "SELECT * FROM servicos WHERE id = ?;";
            
            System.out.println(query);
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id_servico = rs.getInt("id");
                nome = rs.getString("nome");
                descricao = rs.getString("descricao");
                
                servico = new Servico(id_servico, nome, descricao);
            }
        
        }catch(Exception ex){
            System.err.println("Error in getServico in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return servico;
    }
    
    public Cliente getCliente(String id){
        
        Cliente cliente = null;
        String nome="",  email="", telefone="";
        int id_cliente;
        
        try{
            Connection con = getConnection();
            String query = "SELECT * FROM clientes WHERE id = ?;";
            
            System.out.println(query);
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id_cliente = rs.getInt("id");
                nome = rs.getString("nome");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                
                cliente = new Cliente(id_cliente, nome, email, telefone);
            }
        
        }catch(Exception ex){
            System.err.println("Error in getCliente in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return cliente;
    }
    
    //::::::::::::::::::::: INICIO DE OPERACOES PARA Pecas :::::::::::::::::::::::::
    public ArrayList<Peca> getPecasOficina(String id_ofic){
        
        Peca peca = null;
        String nome = "";
        int id, quantidade, id_oficina;
        ArrayList<Peca> lista_peca = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from pecas where id_oficina = ? order by nome asc;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_ofic);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                quantidade = rs.getInt("quantidade");
                id_oficina = rs.getInt("id_oficina");
                nome = rs.getString("nome");
                
                peca = new Peca(id, quantidade, id_oficina, nome);
           
                lista_peca.add(peca); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getPecas in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_peca;
    }
    
    public ArrayList<PecaUtilizada> getPecasTarefa(String id_tarefa){
        
        PecaUtilizada pecaUtilizada = null;
        String nome_peca = "";
        int id, quantidade, id_tar, id_peca;
        ArrayList<PecaUtilizada> lista_pecaUtilizada = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select pecas_utilizados.* , pecas.nome from pecas_utilizados join pecas where pecas_utilizados.id_peca = pecas.id and pecas_utilizados.id_tarefa = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_tarefa);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                quantidade = rs.getInt("quantidade");
                id_tar = rs.getInt("id_tarefa");
                id_peca = rs.getInt("id_peca");
                nome_peca = rs.getString("nome");
                
                pecaUtilizada = new PecaUtilizada(id, quantidade, id_tar, id_peca, nome_peca);
           
                lista_pecaUtilizada.add(pecaUtilizada); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getPecasTarefa in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_pecaUtilizada;
    }
    
    public Boolean registrarPeca(String nome, String quantidade, String id_oficina) {
        
        try{
            Connection con = getConnection();
            
            String query1 = "INSERT INTO pecas (nome, quantidade, id_oficina) VALUES ( ? , ? , ?);";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, nome);
            statement1.setString(2, quantidade);
            statement1.setString(3, id_oficina);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in registrarPeca in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public Boolean aumentarPeca(String id, String quantidade) {
        
        try{
            Connection con = getConnection();
            
            String query1 = "update pecas set quantidade = quantidade + ? where id = ? ;";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setInt(1, Integer.parseInt(quantidade));
            statement1.setString(2, id);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            return true;
            
        }catch(Exception ex){
            System.err.println("Error in aumentarPeca in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public Boolean validarQuantidadePeca(int quantidade, String id_peca){
        
        int quant = 0;
            
        try{
            Connection con = getConnection();

            String query = "select quantidade from pecas where id = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_peca);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {
                
                quant = rs.getInt("quantidade"); 

            }
            
            if(quantidade <= quant ){
                return true;
            }
            
        }catch(Exception ex){
            System.err.println("Error in validarQuantidadePeca in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public String createPecaUtilizada(String id_tarefa, String quantidade, String id_peca){
    
        try{
            
            if(validarQuantidadePeca(Integer.valueOf(quantidade), id_peca)){
                Connection con = getConnection();

                //Criar nova peca utilizada:
                String query1 = "INSERT INTO pecas_utilizados (quantidade, id_tarefa, id_peca) VALUES ( ? , ? , ?);";

                PreparedStatement statement1 = con.prepareStatement(query1);
                statement1.setString(1, quantidade);
                statement1.setString(2, id_tarefa);
                statement1.setString(3, id_peca);
                System.out.println(statement1);
                statement1.executeUpdate();
                
                //Subtraindo quantidade utilizada:
                String query2 = "update pecas set quantidade = quantidade - ? where id = ? ;";
            
                PreparedStatement statement2 = con.prepareStatement(query2);
                statement2.setInt(1, Integer.parseInt(quantidade));
                statement2.setString(2, id_peca);
                System.out.println(statement2);
                statement2.executeUpdate();

                return "True";
            
            }else{
                return "Quantidade Indisponível";
            }
            
        }catch(Exception ex){
            System.err.println("Error in createPecaUtilizada in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return "False";
    }
    
    //::::::::::::::::::::: INICIO DE OPERACOES PARA Mecanicos :::::::::::::::::::::::::
    public ArrayList<Mecanico> getMecanicosOficina(String id_ofic){
        
        Mecanico mecanico = null;
        String nome = "", username="", email="", telefone="";
        int id, bi, efetivo, id_user;
        ArrayList<Mecanico> lista_mecanico= new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select mecanicos.* , system_users.nome, system_users.username, system_users.email, system_users.telefone, system_users.id_oficina from mecanicos join system_users where mecanicos.id_user = system_users.id and system_users.id_oficina = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_ofic);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                bi = rs.getInt("bi");
                efetivo = rs.getInt("efetivo");
                id_user = rs.getInt("id_user");
                nome = rs.getString("nome");
                username = rs.getString("username");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                
                mecanico = new Mecanico(id, bi, efetivo, id_user, nome, username, email, telefone);
           
                lista_mecanico.add(mecanico); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getMecanicosOficina in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_mecanico;
    }
    
    public MecanicoEfetivo getMecanicoEfetivo(String id_mecanico){
        
        MecanicoEfetivo mecanico = null;
        String n_inps = "", nif="";
        int id, id_mec;
        float salario;
        ArrayList<MecanicoEfetivo> lista_mecanico= new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select efetivos.* from efetivos join mecanicos where efetivos.id_mecanico = mecanicos.id and efetivos.id_mecanico = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_mecanico);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_mec = rs.getInt("id_mecanico");
                n_inps = rs.getString("n_inps");
                nif = rs.getString("nif");
                salario = rs.getFloat("salario");
                
                mecanico = new MecanicoEfetivo(id, id_mec, n_inps, nif, salario);

            }
            
        }catch(Exception ex){
            System.err.println("Error in getMecanicoEfetivo in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return mecanico;
    }
    
    public MecanicoEstagiario getMecanicoEstagiario(String id_mecanico){
        
        MecanicoEstagiario mecanico = null;
        String data_inicio = "", data_fim="";
        int id, id_mec;
        ArrayList<MecanicoEstagiario> lista_mecanico= new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select estagiarios.* from estagiarios join mecanicos where estagiarios.id_mecanico = mecanicos.id and estagiarios.id_mecanico = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_mecanico);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_mec = rs.getInt("id_mecanico");
                data_inicio = rs.getString("data_inicio");
                data_fim = rs.getString("data_fim");
                
                mecanico = new MecanicoEstagiario(id, id_mec, data_inicio, data_fim);

            }
            
        }catch(Exception ex){
            System.err.println("Error in getMecanicoEstagiario in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return mecanico;
    }
 
    //pega efetivo também
    public String getMecanicoIdFromSystemUserId(String system_user_id){
    
        String mecanico_id = "", efetivo="";
            
        try{
            Connection con = getConnection();

            String query = "select id, efetivo from mecanicos where id_user = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, system_user_id);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                mecanico_id = rs.getString("id");
                efetivo = rs.getString("efetivo");
                
            }
            
        }catch(Exception ex){
            System.err.println("Error in getMecanicoIdFromSystemUserId in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return mecanico_id+";"+efetivo;
    }
    
    public boolean validarIdSystemUser(int id){
    
        try{
            Connection con = getConnection();

            String query = "select * from system_users where id = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if(rs.first()) {
                return false;
            }
            
        }catch(Exception ex){
            System.err.println("Error in validarIdSystemUser in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return true;
    }
    
    public boolean validarUsername(String username){
    
        try{
            Connection con = getConnection();

            String query = "select * from system_users where username = ?;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            
            if(rs.first()) {
                return false;
            }
            
        }catch(Exception ex){
            System.err.println("Error in validarUsername in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return true;
    }
    
    public String registrarMecanicoEfetivo(String id_oficina, String bi, String username, String password, String nome, String email, String telefone, String n_inps, String nif, String salario){
        
            
        try{
            Connection con = getConnection();
            
            Random r = new Random();
            int low = 1000;
            int high = 1000000;
            int id_system_user = r.nextInt(high-low) + low;
            
            if(!validarUsername(username))
                return "Username já utilizado.";
            
            if(!validarIdSystemUser(id_system_user))
                return "Tente Novamente.";
            
            String query1 = "INSERT INTO system_users (id, username, password, nome, tipo_login, email, telefone, id_oficina) VALUES (?, ?, aes_encrypt(?, 'chave'), ?, ?, ?, ?, ?);";
            String query2 = "INSERT INTO mecanicos (id, bi, efetivo, id_user) VALUES (?, ?, ?, ?);";
            String query3 = "INSERT INTO efetivos (n_inps, nif, salario, id_mecanico) VALUES (?, ?, ?, ?);";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, String.valueOf(id_system_user));
            statement1.setString(2, username);
            statement1.setString(3, password);
            statement1.setString(4, nome);
            statement1.setString(5, "mecanico");
            statement1.setString(6, email);
            statement1.setString(7, telefone);
            statement1.setString(8, id_oficina);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setString(1, String.valueOf(id_system_user));
            statement2.setString(2, bi);
            statement2.setString(3, "1");
            statement2.setString(4, String.valueOf(id_system_user));
            System.out.println(statement2);
            statement2.executeUpdate();
            
            PreparedStatement statement3 = con.prepareStatement(query3);
            statement3.setString(1, n_inps);
            statement3.setString(2, nif);
            statement3.setString(3, salario);
            statement3.setString(4, String.valueOf(id_system_user));
            System.out.println(statement3);
            statement3.executeUpdate();
            
            return "True";
            
        }catch(Exception ex){
            System.err.println("Error in registrarMecanicoEfetivo in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return "False";
    }
    
    public String registrarMecanicoEstagiario(String id_oficina, String bi, String username, String password, String nome, String email, String telefone, String data_inicio, String data_fim){
        
            
        try{
            
            Connection con = getConnection();
            
            Random r = new Random();
            int low = 1000;
            int high = 1000000;
            int id_system_user = r.nextInt(high-low) + low;
            
            if(!validarUsername(username))
                return "Username já utilizado.";
            
            if(!validarIdSystemUser(id_system_user))
                return "Tente Novamente.";
            
            String query1 = "INSERT INTO system_users (id, username, password, nome, tipo_login, email, telefone, id_oficina) VALUES (?, ?, aes_encrypt(?, 'chave'), ?, ?, ?, ?, ?);";
            String query2 = "INSERT INTO mecanicos (id, bi, efetivo, id_user) VALUES (?, ?, ?, ?);";
            String query3 = "INSERT INTO estagiarios (data_inicio, data_fim, id_mecanico) VALUES (?, ?, ?);";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.setString(1, String.valueOf(id_system_user));
            statement1.setString(2, username);
            statement1.setString(3, password);
            statement1.setString(4, nome);
            statement1.setString(5, "mecanico");
            statement1.setString(6, email);
            statement1.setString(7, telefone);
            statement1.setString(8, id_oficina);
            System.out.println(statement1);
            statement1.executeUpdate();
            
            PreparedStatement statement2 = con.prepareStatement(query2);
            statement2.setString(1, String.valueOf(id_system_user));
            statement2.setString(2, bi);
            statement2.setString(3, "0");
            statement2.setString(4, String.valueOf(id_system_user));
            System.out.println(statement2);
            statement2.executeUpdate();
            
            PreparedStatement statement3 = con.prepareStatement(query3);
            statement3.setString(1, data_inicio);
            statement3.setString(2, data_fim);
            statement3.setString(3, String.valueOf(id_system_user));
            System.out.println(statement3);
            statement3.executeUpdate();
            
            return "True";
            
        }catch(Exception ex){
            System.err.println("Error in registrarMecanicoEstagiario in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return "False";
    }
    
    
    //::::::::::: FATURAS ::::::::::::
    public ArrayList<Fatura> getFaturasOficina(String id_oficina){
        
        Fatura fatura = null;
        int id, id_veiculo_marcacao;
        float custo_total;
        String descricao = "";
        
        ArrayList<Fatura> lista_fatura = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select faturas.* from faturas join veiculos_marcacoes where faturas.id_veiculo_marcacao = veiculos_marcacoes.id and veiculos_marcacoes.id_oficina = ? order by veiculos_marcacoes.data_saida;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_oficina);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_veiculo_marcacao = rs.getInt("id_veiculo_marcacao");
                custo_total = rs.getFloat("custo_total");
                descricao = rs.getString("descricao");
                
                
                fatura = new Fatura(id, id_veiculo_marcacao, custo_total, descricao);
           
                lista_fatura.add(fatura); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getFaturasOficina in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_fatura;
    }
    
    public ArrayList<Fatura> getFaturaVeiculo(String id_veiculo){
        
        Fatura fatura = null;
        int id, id_veiculo_marcacao;
        float custo_total;
        String descricao = "";
        
        ArrayList<Fatura> lista_fatura = new ArrayList<>();
            
        try{
            Connection con = getConnection();

            String query = "select * from faturas where id_veiculo_marcacao = ? ;";
            
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id_veiculo);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()) {

                id = rs.getInt("id");
                id_veiculo_marcacao = rs.getInt("id_veiculo_marcacao");
                custo_total = rs.getFloat("custo_total");
                descricao = rs.getString("descricao");
                
                
                fatura = new Fatura(id, id_veiculo_marcacao, custo_total, descricao);
           
                lista_fatura.add(fatura); 

            }
            
        }catch(Exception ex){
            System.err.println("Error in getFaturasVeiculo in ConnectarBD class.");
            System.err.println(ex.getMessage());
        }
        
        return lista_fatura;
    }
}
