package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;



public class ClienteDao implements IDao<Cliente> {
    
    public List<Cliente> getAll() {
		Connection conn = dbAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM Cliente"; // consulta de SELECT
	        rs = stmt.executeQuery(SQL); // executa o SELECT
	        
	        while (rs.next()) {
	        	Cliente c = getClienteFromRs(rs);
	        	
	        	clientes.add(c);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllClientes] Erro ao selecionar todas os clientes", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return clientes;		
	}
	
	public Cliente getById(int id) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Cliente cliente = null;
		
		try {
			String SQL = "SELECT * FROM Cliente WHERE id = ?"; // consulta de SELECT
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); // executa o SELECT
	        
	        while (rs.next()) {
	        	cliente = getClienteFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getClienteById] Erro ao selecionar o cliente por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return cliente;		
	}
	
	public void insert(Cliente cliente) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO Cliente (nome, idade) VALUES (?, ?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, cliente.getNome()); // insira na segunda ? o nome do cliente
	    	stmt.setInt(2, cliente.getIdade()); // insira na terceira ? a idade do cliente
			
	        stmt.executeUpdate(); // executa o SELECT
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	cliente.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereCliente] Erro ao inserir o cliente", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE Cliente WHERE id=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteCliente] Erro ao remover o cliente por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(Cliente cliente) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE Cliente SET nome = ?, idade = ? WHERE id=?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, cliente.getNome()); // insira na primeira ? o nome do cliente
	    	stmt.setInt(2, cliente.getIdade()); // insira na segunda ? a idade do cliente
	    	// insira na última ? o id da pessoa
	    	stmt.setInt(3, cliente.getId());
	    	
	        stmt.executeUpdate(); // executa o UPDATE			
		} catch (SQLException e) {
			throw new RuntimeException("[updateCliente] Erro ao atualizar o cliente", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private Cliente getClienteFromRs(ResultSet rs) throws SQLException {
		Cliente c = new Cliente(); // cria um objeto de cliente
		c.setId(rs.getInt("id")); // insere id recuperado do banco no cliente
		c.setNome(rs.getString("nome")); // insere nome recuperado do banco no cliente
		c.setIdade(rs.getInt("idade")); // insere idade recuperada do banco no cliente
		
		return c;
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar recursos.", e);
		}
	}
    
}
