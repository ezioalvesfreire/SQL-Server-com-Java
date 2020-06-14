package exemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exemplo { ///>>>Principal<<< é o nome classe no gist  do professor

    public static void main(String[] args) {
        // TODO code application logic here
        // Cria variável p/ string de conexão
     //    String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ExemploBanco;user=exemplobanco;password=catolica123"; 
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ExemploBanco;user=exemplobanco;password=ezio123";
        
        // String com a consulta de inserção - ? = variável 
        String insertString = "INSERT INTO Pessoa (id, nome, idade) VALUES (?, ?, ?)";
        
        // Tentativa de inserir valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(insertString);
        	) {
            System.out.println("TESTE!");
            
        	Pessoa p1 = new Pessoa(1, "Maria", 50);
        	// insira na primeira ? o id da pessoa
        	stmt.setInt(1, p1.getId());
        	stmt.setString(2, p1.getNome()); // insira na segunda ? o nome da pessoa
        	stmt.setInt(3, p1.getIdade()); // insira na terceira ? a idade da pessoa
        	
        	stmt.executeUpdate(); // executa o insert
        	
        	Pessoa p2 = new Pessoa(2, "Lucia", 24);
        	// insira na primeira ? o id da pessoa
        	stmt.setInt(1, p2.getId());
        	stmt.setString(2, p2.getNome()); // insira na segunda ? o nome da pessoa
        	stmt.setInt(3, p2.getIdade()); // insira na terceira ? a idade da pessoa
        	
        	stmt.executeUpdate(); // executa o insert 
                
              
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        // faz um SELECT no banco
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		Statement stmt = con.createStatement(); // não tem variável, se quiser variável, precisa do prepare
        	) {
        	
            String SQL = "SELECT * FROM Pessoa"; // consulta de SELECT
            ResultSet rs = stmt.executeQuery(SQL); // executa o SELECT

            // itera pelos dados - enquanto houver uma linha para ler ....
            while (rs.next()) {
            	Pessoa p = new Pessoa(); // cria um objeto de pessoa
            	p.setId(rs.getInt("id")); // insere id recuperado do banco na pessoa
            	p.setNome(rs.getString("nome")); // insere nome recuperado do banco na pessoa
            	p.setIdade(rs.getInt("idade")); // insere idade recuperada do banco na pessoa

            	System.out.println(p); // imprime a pessoa
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        String updateString = "UPDATE Pessoa SET nome = ?, idade = ? WHERE id=?";
        
        // Tentativa de atualizar valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(updateString);
        	) {
        	
        	Pessoa p1 = new Pessoa(1, "Mariano", 20); // a pessoa alterada
        	stmt.setString(1, p1.getNome()); // insira na primeira ? o nome da pessoa
        	stmt.setInt(2, p1.getIdade()); // insira na terceira ? a idade da pessoa
        	// insira na última ? o id da pessoa
        	stmt.setInt(3, p1.getId());	
        	
        	stmt.executeUpdate(); // executa o update
        	
        	System.out.println("Pessoa alterada!");
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        String deleteString = "DELETE Pessoa WHERE id=?";
        
        // Tentativa de remover valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(deleteString);
        	) {
        	
        	int personId = 2;
        	// insira na ? o id da pessoa
        	stmt.setInt(1, personId);
        	
        	stmt.executeUpdate(); // executa o delete
        	
        	System.out.println("Pessoa removida!");
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        
     // faz um SELECT no banco
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		Statement stmt = con.createStatement(); // não tem variável, se quiser variável, precisa do prepare
        	) {
        	
            String SQL = "SELECT * FROM Pessoa"; // consulta de SELECT
            ResultSet rs = stmt.executeQuery(SQL); // executa o SELECT

            // itera pelos dados - enquanto houver uma linha para ler ....
            while (rs.next()) {
            	Pessoa p = new Pessoa(); // cria um objeto de pessoa
            	p.setId(rs.getInt("id")); // insere id recuperado do banco na pessoa
            	p.setNome(rs.getString("nome")); // insere nome recuperado do banco na pessoa
            	p.setIdade(rs.getInt("idade")); // insere idade recuperada do banco na pessoa

            	System.out.println(p); // imprime a pessoa
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
