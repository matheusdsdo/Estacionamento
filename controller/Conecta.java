/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 2017004573
 */
public class Conecta {
        public static Connection getConnetion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/estacionamento", "root", "");
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
            return null;
        }
    }
}
