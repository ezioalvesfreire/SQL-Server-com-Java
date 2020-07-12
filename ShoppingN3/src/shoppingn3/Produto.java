package shoppingn3;

public class Produto {
    
     int id_Produto;
        String nome;
	double preco;
        int desconto; // adicionei

    public Produto(int id_Produto, String nome, double preco, int desconto) {
        this.id_Produto = id_Produto;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
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
		return "Produto [id=" + id_Produto + ", nome=" + nome + ", preco=" + preco + "]";
	}

    
}
