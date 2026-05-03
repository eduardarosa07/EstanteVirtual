/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection connection = null;

    public static Connection getConnection() {
        
        if (connection != null)
            return connection;
        else {
            try {
                
                String user = "postgres";
                String password = "rose";
                
                Class.forName("org.postgresql.Driver");
                
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/estante_virtual",user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            return connection;
        }

    }
}