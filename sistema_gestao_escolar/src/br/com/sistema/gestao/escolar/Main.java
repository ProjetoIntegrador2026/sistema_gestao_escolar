package br.com.sistema.gestao.escolar;

import com.formdev.flatlaf.FlatDarkLaf;
import br.com.sistema.gestao.escolar.view.TelaLogin;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            
            FlatDarkLaf.setup();
            
            
            UIManager.put("Button.arc", 12);          
            UIManager.put("Component.arc", 12);       
            UIManager.put("TextComponent.showClearButton", true); 

        } catch (Exception ex) {
            System.err.println("Erro ao inicializar o FlatLaf. Usando padrão nativo.");
        }

        // Abre a sua tela de login de forma segura
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaLogin login = new TelaLogin();
                login.setLocationRelativeTo(null); 
                login.setVisible(true);            
            }
        });
    }
}