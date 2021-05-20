
package estacionamento;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;


public class DAO {
    
    private Connection con;
    
    public DAO(){
        try {
            this.con = Conecta.getConnetion();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }        
    }
    
    public void adicionaCliente(String usuario, String cpf, String placa ){
            String sql = "INSERT INTO cliente (nome, cpf, placa) VALUES (?,?,?)";
           try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, usuario);
                ps.setString(2, cpf);
                ps.setString(3, placa);   
                ps.execute();
                ps.close();
                JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso.");
            } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Erro ao adicionar novo usuario.");
                System.out.println("deu barba na conexao 2");
        }
    }
    

    
    public void entradaVeiculo(String placa) throws SQLException{
GregorianCalendar gc = new GregorianCalendar();
        //verificaMensalista(placa);
        System.out.println(gc.toString());
        String sql = "INSERT INTO vagas (placa, entrada) VALUES (?,?)";
           try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, placa);
                ps.setString(2, gc.getTime().toString()); 
                ps.execute();
                ps.close();
                JOptionPane.showMessageDialog(null, "Veículo adicionado com sucesso.");
            } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao adicionar novo veículo.");
               System.out.println("Erro adicionar veículo");
        }
        //COPIAR O DE CIMA E ADAPTAR
    }
    
    public ArrayList<Usuario> listarUsuarios() throws SQLException{
        String sql = "select * from cliente";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        while(rs.next()){
            Usuario user = new Usuario();
            user.setNome(rs.getString("nome"));
            user.setCpf(rs.getString("cpf"));
            user.setPlaca(rs.getString("placa"));
            users.add(user);
        }
        ps.close();
        rs.close();
        return users;
    }
    
        public void verificaMensalista(String placa) throws SQLException{
        String sql = "SELECT FROM cliente WHERE placa=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, placa);
        ResultSet rs = ps.executeQuery();
            System.out.println(rs.toString());
        if (rs != null){
                JOptionPane.showMessageDialog(null, "JÁ É NOSSO CLIENTE!!!!");         
        }
    }
    
    
    
       public ArrayList<Veiculo> listarVeiculos() throws SQLException{
        String sql = "select * from vagas";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Veiculo> carros = new ArrayList<Veiculo>();
        while(rs.next()){
            Veiculo carro = new Veiculo();
            carro.setPlaca(rs.getString("placa"));
            carro.setHora(rs.getString("entrada"));
            carros.add(carro);
        }
        ps.close();
        rs.close();
        return carros;
    }

       
    public void removerUsuario(String cpf) throws SQLException{
        PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE cpf = ?");
        ps.setString(1, cpf);
        ps.execute();
        ps.close();
    }
    
    public void removerVeiculo(String placa) throws SQLException{
        PreparedStatement ps = con.prepareStatement("DELETE FROM vagas WHERE placa = ?");
        ps.setString(1, placa);
        ps.execute();
        ps.close();     
    }
    
    
}
