package shoppingn2;
public class Cd extends Produto {
        String artista;
	int faixas;

    public Cd(String artista, int faixas, int id, String nome, double preco, int desconto) {
        super(id, nome, preco, desconto);
        this.artista = artista;
        this.faixas = faixas;
    }

  

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getFaixas() {
        return faixas;
    }

    public void setFaixas(int faixas) {
        this.faixas = faixas;
    }
    
	
    
}
