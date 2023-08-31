/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebServices;

import DBConnection.ConnectarBD;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import classes.SystemUser;
import java.util.ArrayList;
/**
 *
 * @author Daniel Furtado
 */
@WebService(serviceName = "GetSystemUsers")
public class SystemUsersServices {

    /**
     * This is a sample web service operation
     */
    
    @WebMethod(operationName = "GetAllSystemUsers")
    public String getSystemUsers(){
        
        String RESULT = "No records found";
        
        try{
            
            ConnectarBD con = new ConnectarBD();
            
            ArrayList<SystemUser> lista_user = con.getSystemUsers();
            int i = 0, controlo = 0;
            
            for( ; i < lista_user.size(); i++){
            
                if(controlo == 0){
                    RESULT = "<SystemUserList><SystemUsers><SystemUser><id>"+lista_user.get(i).getId()+"</id>,<username>"+lista_user.get(i).getUsername()+"</username>,<password>"+lista_user.get(i).getPassword()+"</password>, <email>"+lista_user.get(i).getEmail()+"</email>, <telefone>"+lista_user.get(i).getTelefone()+"</telefone>, <nome>"+lista_user.get(i).getNome()+"</nome>, <tipo_login>"+lista_user.get(i).getTipo_login()+"</tipo_login>, <id_oficina>"+lista_user.get(i).getId_oficina()+"</id_oficina></SystemUser>";
                    controlo = 1;
                }else{
                    RESULT += "<SystemUser><id>"+lista_user.get(i).getId()+"</id>,<username>"+lista_user.get(i).getUsername()+"</username>,<password>"+lista_user.get(i).getPassword()+"</password>, <email>"+lista_user.get(i).getEmail()+"</email>, <telefone>"+lista_user.get(i).getTelefone()+"</telefone>, <nome>"+lista_user.get(i).getNome()+"</nome>, <tipo_login>"+lista_user.get(i).getTipo_login()+"</tipo_login>, <id_oficina>"+lista_user.get(i).getId_oficina()+"</id_oficina></SystemUser>";
                }
            }
            
            if(controlo != 0){
                RESULT += "</SystemUsers></SystemUserList>";
            }
            
        }catch(Exception ex){
            System.err.println("Erro no webservice SystemUsersServices.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }

    @WebMethod(operationName = "Login")
    public String getLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password){
        
        String RESULT = "No records found";
       
        try{
            
            ConnectarBD con = new ConnectarBD();
            SystemUser user = con.getLogin(username, password);
            
            if(user != null)
                RESULT = "<SystemUserList><SystemUsers>"
                            + "<SystemUser>"
                                + "<id>"+user.getId()+"</id>"
                                + "<username>"+user.getUsername()+"</username>"
                                + "<password>"+user.getPassword()+"</password>"
                                + "<email>"+user.getEmail()+"</email>"
                                + "<telefone>"+user.getTelefone()+"</telefone>"
                                + "<nome>"+user.getNome()+"</nome>"
                                + "<tipo_login>"+user.getTipo_login()+"</tipo_login>"
                                + "<id_oficina>"+user.getId_oficina()+"</id_oficina>"
                            + "</SystemUser>"
                        + "</SystemUsers></SystemUserList>";
            
        }catch(Exception ex){
            System.err.println("Erro no webservice getLogin.");
            System.err.println(ex.getMessage());
        }
        
        return RESULT;
    }
}

