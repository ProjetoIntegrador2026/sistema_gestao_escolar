/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.gestao.escolar.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // URL ajustada com o formato JDBC que o Java exige + parâmetro de segurança SSL
    private static final String URL = "jdbc:postgresql://dpg-d91g8q28qa3s73aiu700-a.oregon-postgres.render.com/meubanco_java?sslmode=require";
    private static final String USUARIO = "meubanco_java_user";
    private static final String SENHA = "2dPetn34fYKTmIWD8HCBCfXkHgCi78t5";

    public static Connection getConexao() throws SQLException {
        try {
            // Carrega o driver físico postgresql-42.7.11.jar que está na sua pasta lib
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro: O driver postgresql-42.7.11.jar não foi encontrado na pasta lib!", e);
        }
    }
}