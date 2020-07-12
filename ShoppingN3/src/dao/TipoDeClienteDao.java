
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.TipoDeCliente;


public class TipoDeClienteDao implements IDao<TipoDeCliente> {
    
    
    public TipoDeClienteDao() {
		try {
			createTable();
		} catch (SQLException e) {
			//throw new RuntimeException("Erro ao criar tabela tipo de cliente");
			e.printStackTrace();
		}
	}
	
	// Cria a tabela se não existir
	private void createTable() throws SQLException {
		final String sqlCreate = "IF NOT EXISTS (" 
				+ "SELECT * FROM sys.tables t JOIN sys.schemas s ON " 
				+ "(t.schema_id = s.schema_id) WHERE s.name = 'dbo'" 
				+ "AND t.name = 'Departamento')"
				+ "CREATE TABLE Departamento"
				+ " (id	int	IDENTITY,"
				+ "  nome	VARCHAR(255),"
				+ "  PRIMARY KEY (id))";
		
		Connection conn = dbAccess.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.execute(sqlCreate);
	}

	public List<TipoDeCliente> getAll() {
		Connection conn = dbAccess.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<TipoDeCliente> tipodeclientes = new ArrayList<TipoDeCliente>();
		
		try {
			stmt = conn.createStatement();
			
			String SQL = "SELECT * FROM TipoDeClientes"; // consulta de SELECT
	        rs = stmt.executeQuery(SQL); // executa o SELECT
	        
	        while (rs.next()) {
	        	TipoDeCliente tpC = getTipoDeClienteFromRs(rs);
	        	
	        	tipodeclientes.add(tpC);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getAllTipoDeClientes] Erro ao selecionar todos os tipodeclientes", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return tipodeclientes;		
	}
	
	public TipoDeCliente getById(int id) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		TipoDeCliente tipodecliente = null;
		
		try {
			String SQL = "SELECT * FROM TipoDeClientes WHERE id = ?"; // consulta de SELECT
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        rs = stmt.executeQuery(); // executa o SELECT
	        
	        while (rs.next()) {
	        	tipodecliente = getTipoDeClienteFromRs(rs);
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[getTipoDeClienteById] Erro ao selecionar o tipodeclientepor por id", e);
		} finally {
			close(conn, stmt, rs);
		}
		
		return tipodecliente;		
	}
	
	public void insert(TipoDeCliente tipodecliente) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "INSERT INTO TipoDeCliente (nome) VALUES (?)";
			stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	    	stmt.setString(1, tipodecliente.getNome()); // insira na segunda ? o nome da pessoa
	    	
			
	        stmt.executeUpdate(); // executa o SELECT
	        
	        rs = stmt.getGeneratedKeys();
	        
	        if (rs.next()) {
	        	tipodecliente.setId(rs.getInt(1));
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException("[insereTipoDeCliente] Erro ao inserir o tipodecliente", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	public void delete(int id) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
			
		try {
			String SQL = "DELETE TipoDeCliente WHERE id=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, id);
			
	        stmt.executeUpdate(); 			
		} catch (SQLException e) {
			throw new RuntimeException("[deleteTipoDeCliente] Erro ao remover o tipodecliente por id", e);
		} finally {
			close(conn, stmt, null);
		}
	}
	
	public void update(TipoDeCliente tipodecliente) {
		Connection conn = dbAccess.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		try {
			String SQL = "UPDATE TipoDeCliente SET nome = ? WHERE id=?";
			stmt = conn.prepareStatement(SQL);
	    	stmt.setString(1, tipodecliente.getNome()); // insira na primeira ? o nome do cliente
	    	// insira na última ? o id do cliente
	    	stmt.setInt(2, tipodecliente.getId());
	    	
	        stmt.executeUpdate(); // executa o UPDATE			
		} catch (SQLException e) {
			throw new RuntimeException("[updateTipoDeCliente] Erro ao atualizar o tipodecliente", e);
		} finally {
			close(conn, stmt, rs);
		}
				
	}
	
	private TipoDeCliente getTipoDeClienteFromRs(ResultSet rs) throws SQLException {
		TipoDeCliente tpC = new TipoDeCliente(); // cria um objeto de tipo de cliente
		tpC.setId(rs.getInt("id")); // insere id recuperado do banco no tipo de cliente
		tpC.setNome(rs.getString("nome")); // insere nome recuperado do banco no tipo de cliente
		
		return tpC;
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
