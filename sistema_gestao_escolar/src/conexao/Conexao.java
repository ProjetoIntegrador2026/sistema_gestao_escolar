/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static String URL = "jdbc:postgresql://dpg-d91g8q28qa3s73aiu700-a:5432/meubanco_java?sslmode=require";
    private static String USUARIO = "meubanco_java_user";
    private static String SENHA = "2dPetn34fYKTmIWD8HCBCfXkHgCi78t5";

    public static Connection conectar(){
        try{
            // Carrega o driver que você acabou de baixar
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conectado com sucesso ao Render!");
            return conn;
        }catch(ClassNotFoundException e){
            System.out.println("Erro: Driver do PostgreSQL nao encontrado na pasta lib!");
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            System.out.println("Erro na conexao com o banco do Render!");
            e.printStackTrace();
            return null;
        }
    }
}
