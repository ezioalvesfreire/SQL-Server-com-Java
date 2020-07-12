package shoppingn3;

import dao.dbAccess;
import dao.TipoDeClienteDao;
import dao.ClienteDao;
import modelo.TipoDeCliente;
import modelo.Cliente;
import Menu.MenuPrincipalTexto;
import java.util.List;

public class ShoppingN3 {

    public static void main(String[] args) {
        // TODO code application logic here
        
        			
		MenuPrincipalTexto menu = new MenuPrincipalTexto();
		
		menu.executa();
	}

	private static void imprimeClientes(List<Cliente> clientes) {
		System.out.println("Lista de clientes:");
        for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}
	
	private static void imprimeTipoDeCliente(List<TipoDeCliente> tipodeclientes) {
		System.out.println("Lista de clientes:");
        for (TipoDeCliente tipodecliente : tipodeclientes) {
			System.out.println(tipodecliente);
		}
    }
    
}
