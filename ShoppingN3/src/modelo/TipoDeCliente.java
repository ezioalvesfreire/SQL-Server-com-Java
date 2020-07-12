package modelo;
        
        import java.util.List;

public class TipoDeCliente {
    private int id;
	private String nome;
	private List<Cliente> compradores;
	
	public TipoDeCliente() { }
	
	public TipoDeCliente(int id, String nome, List<Cliente>compradorores) {		
		this.id = id;
		this.nome = nome;
		this.compradores = compradores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getCompradores() {
		return compradores;
	}

	public void setCompradores(List<Cliente> compradores) {
		this.compradores = compradores;
	}

	@Override
	public String toString() {
		return "Tipo de cliente [id=" + id + ", nome=" + nome + "]";
	}
	
}
