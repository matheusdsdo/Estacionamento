
package estacionamento;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Conexao {
    private final String user = "root";
    private final String pass = "";
    private final String banco = "jdbc:mysql://localhost:3306/estacionamento";
    Connection con;
 
    public void conecta(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(banco, user, pass);
            if (DriverManager.getConnection(banco, user, pass)!= null){
                System.out.println("conectado!!!!!!!!!");
            }
        } catch (Exception e) {
            
        }
    }
    
    public void adicionaCliente(String user, String cpf, String placa) throws SQLException{
        conecta();

    }
    
}
    

