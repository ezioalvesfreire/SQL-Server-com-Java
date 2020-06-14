package shoppingn2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ShoppingN2 {


    public static void main(String[] args) {
        // TODO code application logic here
        
         // TODO code application logic here
        // Cria variável p/ string de conexão
     //    String connectionUrl = "jdbc:sqlserver://localhost;databaseName=ExemploBanco;user=exemplobanco;password=catolica123"; 
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=AvaliacaoN2;user=exemplobanco;password=ezio123";
        
        // String com a consulta de inserção - ? = variável 
        String insertString = "INSERT INTO Produto (id, nome, preco, desconto) VALUES (?, ?, ?, ?)";
        
        // Tentativa de inserir valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(insertString);
        	) {
            System.out.println("TESTE!");
            
        	Produto p1 = new Produto(1, "Cristiano Araújo", 50, 30);
        	// insira na primeira ? o id do produto
        	stmt.setInt(1, p1.getId());
        	stmt.setString(2, p1.getNome()); // insira na segunda ? o nome do produto
        	stmt.setDouble(3, p1.getPreco()); // insira na terceira ? a idade da produto
        	
        	stmt.executeUpdate(); // executa o insert
        	
        	Produto p2 = new Produto(2, "Bruno e Marrone", 50, 20);
        	// insira na primeira ? o id da produto
        	stmt.setInt(1, p2.getId());
        	stmt.setString(2, p2.getNome()); // insira na segunda ? o nome do produto
        	stmt.setDouble(3, p2.getPreco()); // insira na terceira ? a idade do produto
        	
        	stmt.executeUpdate(); // executa o insert 
                
              
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        // faz um SELECT no banco
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		Statement stmt = con.createStatement(); // não tem variável, se quiser variável, precisa do prepare
        	) {
        	
            String SQL = "SELECT * FROM Produto"; // consulta de SELECT
            ResultSet rs = stmt.executeQuery(SQL); // executa o SELECT

            // itera pelos dados - enquanto houver uma linha para ler ....
            while (rs.next()) {
            	Produto p = new Produto(); // cria um objeto de produto
            	p.setId(rs.getInt("id")); // insere id recuperado do banco do produto
            	p.setNome(rs.getString("nome")); // insere nome recuperado do banco no produto
            	p.setPreco(rs.getDouble("preco")); // insere preço recuperada do banco no produto
                p.setDesconto(rs.getInt("desconto")); // insere desconto recuperada do banco no produto

            	System.out.println(p); // imprime o produto
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        String updateString = "UPDATE Produto SET nome = ?, desconto = ? WHERE id=?";
        
        // Tentativa de atualizar valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(updateString);
        	) {
        	
        	Produto p1 = new Produto(1, "Capital Inicial",58, 20); // o produto alterado
        	stmt.setString(1, p1.getNome()); // insira na primeira ? o nome do produto
        	stmt.setDouble(2, p1.getPreco()); // insira na terceira ? a preco do produto
        	// insira na última ? o id do produto
        	stmt.setInt(3, p1.getId());	
        	
        	stmt.executeUpdate(); // executa o update
        	
        	System.out.println("Produto alterado!");
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        String deleteString = "DELETE Produto WHERE id=?";
        
        // Tentativa de remover valor na tabela
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		PreparedStatement stmt = con.prepareStatement(deleteString);
        	) {
        	
        	int personId = 2;
        	// insira na ? o id do produto
        	stmt.setInt(1, personId);
        	
        	stmt.executeUpdate(); // executa o delete
        	
        	System.out.println("Produto removido!");
        	
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
        
     // faz um SELECT no banco
        try (
        		Connection con = DriverManager.getConnection(connectionUrl); 
        		Statement stmt = con.createStatement(); // não tem variável, se quiser variável, precisa do prepare
        	) {
        	
            String SQL = "SELECT * FROM Produto"; // consulta de SELECT
            ResultSet rs = stmt.executeQuery(SQL); // executa o SELECT

            // itera pelos dados - enquanto houver uma linha para ler ....
            while (rs.next()) {
            	Produto p = new Produto(); // cria um objeto de produto
            	p.setId(rs.getInt("id")); // insere id recuperado do banco no produto
            	p.setNome(rs.getString("nome")); // insere nome recuperado do banco no produto
            	p.setPreco(rs.getDouble("preco")); // insere preço recuperada do banco no produto
                p.setDesconto(rs.getInt("desconto")); // insere desconto recuperada do banco no produto

            	System.out.println(p); // imprime o produto
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
