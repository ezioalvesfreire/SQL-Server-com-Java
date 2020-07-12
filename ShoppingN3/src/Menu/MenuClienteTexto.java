
package Menu;

import dao.ClienteDao;
import modelo.Cliente;

import java.util.List;

public class MenuClienteTexto extends MenuEspecificoTexto {
     private ClienteDao dao;

    public MenuClienteTexto() {
        super();
        dao = new ClienteDao();
    }

    private int obterIdCliente() {
        System.out.print("Escolha o id do cliente: ");
        int id = entrada.nextInt();
        entrada.nextLine();

        return id;
    }

    private Cliente obterDadosCliente(Cliente cliente) {
        Cliente c;

        if (cliente == null) {
            c = new Cliente();
        } else {
            c = cliente;
        }

        System.out.print("Digite o nome do cliente: ");
        String nome = entrada.nextLine();

        System.out.print("Digite a idade do cliente: ");
        int idade = entrada.nextInt();
        entrada.nextLine();

        c.setNome(nome);
        c.setIdade(idade);

        return c;
    }

    @Override
    public void adicionar() {
        System.out.println("Adicionar Cliente");
        System.out.println();

        // obter dados do cliente
        Cliente novoCliente = obterDadosCliente(null);

        // inserir no banco o cliente -> DAO
        dao.insert(novoCliente);
    }

    @Override
    public void editar() {
        System.out.println("Editar Cliente");
        System.out.println();

        // listar os clientes
        imprimirClientes();

        // pedir um id do cliente
        int id = obterIdCliente();

        Cliente clienteAModificar = dao.getById(id);

        // obter os dados do cliente
        Cliente novoCliente = obterDadosCliente(clienteAModificar);

        // atualizar cliente no banco
        novoCliente.setId(clienteAModificar.getId());
        dao.update(novoCliente);
    }

    @Override
    public void excluir() {
        System.out.println("Excluir Cliente");
        System.out.println();

        // listar os clientes
        imprimirClientes();
        // pedir um id do cliente
        int id = obterIdCliente();

        // remover cliente do banco
        dao.delete(id);
    }

    @Override
    public void listarTodos() {
        System.out.println("Lista de clientes");
        System.out.println();

        imprimirClientes();
    }

    private void imprimirClientes() {
        // obter clientes do banco
        List<Cliente> clientes = dao.getAll();

        // imprimir clientes
        System.out.println("id\tNome\tIdade");

        for (Cliente c : clientes) {
            System.out.println(c.getId() + "\t" + c.getNome() + "\t" + c.getIdade());
        }
    }
    
}
