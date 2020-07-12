package shoppingn3;

public class Produto {
    
        int id;
        String nome;
	double preco;
        int desconto; // adicionei

    public Produto(int id, String nome, double preco, int desconto) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }
     @Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
	}

    
}
