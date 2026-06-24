/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class Conexao {
    private static String URL = "jdbc:mysql://localhost:3306/sistema_gestao_escolar";
    private static String USUARIO = "root";
    private static String SENHA = "root";
    
    public static Connection conectar(){
        try{
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado!");
            return conn;
        }catch(SQLException e){
            System.out.println("Erro na conexão!");
            e.printStackTrace();
            return null;
        }
    }  
}
