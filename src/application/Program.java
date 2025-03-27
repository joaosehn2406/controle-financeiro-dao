package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Categoria;
import model.Movimentacao;
import model.TipoMovimentacao;
import model.Usuario;
import services.CategoriaService;
import services.MovimentacaoService;
import services.UsuarioService;
import dao.interfaces.DaoFactory;
import exceptions.CategoriaException;
import exceptions.MovimentacaoException;
import exceptions.UsuarioException;

public class Program {

    private static Scanner in = new Scanner(System.in);
    private static CategoriaService categoriaService;
    private static MovimentacaoService movimentacaoService;
    private static UsuarioService usuarioService;

    public static void main(String[] args) {

        DaoFactory daoFactory = new DaoFactory();

        categoriaService = new CategoriaService(daoFactory.createCategoriaDao());
        movimentacaoService = new MovimentacaoService(daoFactory.createMovimentacaoDao());
        usuarioService = new UsuarioService(daoFactory.createUsuarioDao());

        int opcao = -1;

        while (opcao != 0) {
            mostrarMenu();
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCategoria();
                    break;
                case 2:
                    atualizarCategoria();
                    break;
                case 3:
                    buscarCategoriaPorId();
                    break;
                case 4:
                    removerCategoria();
                    break;
                case 5:
                    adicionarMovimentacao();
                    break;
                case 6:
                    atualizarMovimentacao();
                    break;
                case 7:
                    buscarMovimentacao();
                    break;
                case 8:
                    buscarMovimentacaoPorUsuario();
                    break;
                case 9:
                    buscarMovimentacaoPorCategoria();
                    break;
                case 10:
                    buscarMovimentacaoAll();
                    break;
                case 11:
                    removerMovimentacao();
                    break;
                case 12:
                    adicionarUsuario();
                    break;
                case 13:
                    atualizarUsuario();;
                    break;
                case 14:
                    buscarUsuarioPorId();
                    break;
                case 15:
                    buscarUsuarioPorEmail();
                    break;
                case 16: 
                    buscarUsuarioAll();
                    break;
                case 17:
                    removerUsuario();
                    break;
                case 0:
                    try {
                        Thread.sleep(300);
                        System.out.print("Saindo");
                        Thread.sleep(300);
                        System.out.print(".");
                        Thread.sleep(300);
                        System.out.print(".");
                        Thread.sleep(300);
                        System.out.print(".");
                        Thread.sleep(300);
                        System.out.print(".");
                    }
                    catch(InterruptedException e) {
                        e.getMessage();
                    }
                    
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void mostrarMenu() {

        try {
            System.out.println("\n--- Menu de Operações ---");
            System.out.println("1. Adicionar Categoria");
            Thread.sleep(300);
            System.out.println("2. Atualizar Categoria");
            Thread.sleep(300);
            System.out.println("3. Buscar Categoria");
            Thread.sleep(300);
            System.out.println("4. Remover Categoria");
            Thread.sleep(300);
            System.out.println("5. Adicionar Movimentação");
            Thread.sleep(300);
            System.out.println("6. Atualizar Movimentação");
            Thread.sleep(300);
            System.out.println("7. Buscar Movimentação");
            Thread.sleep(300);
            System.out.println("8. Buscar Movimentação por usuário");
            Thread.sleep(300);
            System.out.println("9. Buscar Movimentação por categoria");
            Thread.sleep(300);
            System.out.println("10. Buscar todas as Movimentação");
            Thread.sleep(300);
            System.out.println("11. Remover Movimentação");
            Thread.sleep(300);
            System.out.println("12. Adicionar Usuário");
            Thread.sleep(300);
            System.out.println("13. Atualizar Usuário");
            Thread.sleep(300);
            System.out.println("14. Buscar Usuário por Id");
            Thread.sleep(300);
            System.out.println("15. Buscar Usuário por Email");
            Thread.sleep(300);
            System.out.println("16. Buscar todos os Usuário");
            Thread.sleep(300);
            System.out.println("17. Remover Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
       
    }

    private static void adicionarCategoria() {
        try {
            System.out.print("Informe o nome da categoria: ");
            String nome = in.nextLine();
            System.out.print("Informe o ID do usuário associado: ");
            int idUsuario = in.nextInt();
            in.nextLine();

            Usuario usuario = usuarioService.findByUsuarioId(idUsuario);

            Categoria categoria = new Categoria(usuario, nome, 0);
            categoriaService.adicionarCategoria(categoria);
            System.out.println("Categoria adicionada com sucesso!");
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarCategoria() {
        try {
            System.out.print("Informe o ID da categoria a ser atualizada: ");
            int idCategoria = in.nextInt();
            in.nextLine();

            System.out.print("Informe o novo nome da categoria: ");
            String nome = in.nextLine();

            Usuario usuario = new Usuario(1, "Usuário Exemplo");

            Categoria categoria = new Categoria(usuario, nome, idCategoria);
            categoriaService.atualizarCategoria(categoria);
            System.out.println("Categoria atualizada com sucesso!");
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarCategoriaPorId() {
        System.out.print("Informe o ID da categoria a ser buscada: ");
        int idCategoria = in.nextInt();
        in.nextLine();

        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);
            System.out.println("Categoria encontrada: " + categoria);
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    

    private static void removerCategoria() {
        System.out.print("Informe o ID da categoria a ser removida: ");
        int idCategoria = in.nextInt();
        in.nextLine();

        try {
            categoriaService.removerCategoria(idCategoria);
            System.out.println("Categoria removida com sucesso!");
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void adicionarMovimentacao() {
        try {
            System.out.print("Informe o valor da movimentação: ");
            double valor = in.nextDouble();
            in.nextLine();

            System.out.print("Informe a descrição da movimentação: ");
            String descricao = in.nextLine();

            System.out.print("Informe o ID da categoria: ");
            int idCategoria = in.nextInt();

            System.out.print("Informe o ID do usuário: ");
            int idUsuario = in.nextInt();
            in.nextLine();

            System.out.print("Informe o tipo de movimentação (D = despesa, R = receita): ");
            String respostaTipo = in.nextLine();

            Usuario usuario = usuarioService.findByUsuarioId(idUsuario);
            Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);

            LocalDate dataAtual = LocalDate.now();
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setValor(valor);
            movimentacao.setUsuario(usuario);
            movimentacao.setCategoria(categoria);
            movimentacao.setDescricao(descricao);
            movimentacao.setData(dataAtual);

            if (respostaTipo.equalsIgnoreCase("D")) {
                movimentacao.setTipoMovimentacao(TipoMovimentacao.DESPESA);
            }
            movimentacao.setTipoMovimentacao(TipoMovimentacao.RECEITA);

            movimentacaoService.adicionarMovimentacao(movimentacao);

            System.out.println("Movimentação adicionada com sucesso!");
        } 
        catch (MovimentacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarMovimentacao() {
        try {
            System.out.print("Informe o ID da movimentação a ser atualizada: ");
            int idMovimentacao = in.nextInt();
            in.nextLine();

            System.out.print("Informe o novo valor da movimentação: ");
            double valor = in.nextDouble();
            in.nextLine();

            System.out.print("Informe a descrição da movimentação: ");
            String descricaoMov = in.nextLine();


            System.out.print("Informe o novo tipo de movimentação (RECEITA/DESPESA): ");
            String tipoMovStr = in.nextLine();

            System.out.print("Informe o ID da categoria: ");
            int idCategoria = in.nextInt();

            System.out.print("Informe o ID do usuário: ");
            int idUsuario = in.nextInt();
            in.nextLine();

            Usuario usuario = usuarioService.findByUsuarioId(idUsuario);
            Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);

            LocalDate data = LocalDate.now();


            Movimentacao movimentacao = new Movimentacao();

            movimentacao.setId_transacao(idMovimentacao);
            movimentacao.setDescricao(descricaoMov);
            movimentacao.setData(data);
            movimentacao.setValor(valor);

            if (tipoMovStr.equalsIgnoreCase("RECEITA")) {
                movimentacao.setTipoMovimentacao(TipoMovimentacao.RECEITA);
            }
            movimentacao.setTipoMovimentacao(TipoMovimentacao.DESPESA);

            movimentacao.setCategoria(categoria);
            movimentacao.setUsuario(usuario);

            movimentacaoService.atualizarMovimentacao(movimentacao);
            System.out.println("Movimentação atualizada com sucesso!");
        } 
        catch (MovimentacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
        catch (CategoriaException e) {
            System.out.println("Erro: " + e.getMessage());
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarMovimentacao() {
        System.out.print("Informe o ID da movimentação a ser buscada: ");
        int idMovimentacao = in.nextInt();
        in.nextLine();

        try {
            Movimentacao movimentacao = movimentacaoService.buscarMovimentacaoPorId(idMovimentacao);
            System.out.println("Movimentação encontrada: " + movimentacao);
        } 
        catch (MovimentacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarMovimentacaoPorUsuario(){
        System.out.print("Informe o ID do usuário para buscar a movimentação: ");
        int idUsuario = in.nextInt();
        in.nextLine();

        try {

            Usuario usuario = usuarioService.findByUsuarioId(idUsuario);

            List<Movimentacao> list = movimentacaoService.buscarMovimentacaoPorUsuario(usuario);

            if (list.isEmpty()) {
                System.out.println("Nenhuma movimentação para o usuário " + usuario.getNome());
            }
            System.out.println("Movimentações do usuário " + usuario.getNome() + ": ");
            list.forEach(x -> System.out.println(x));

        }
        catch(UsuarioException e) {
            throw new UsuarioException(e.getMessage());
        }
        catch(MovimentacaoException e) {
            throw new MovimentacaoException(e.getMessage());
        }
    }

    private static void buscarMovimentacaoPorCategoria(){
        System.out.print("Informe o ID da categoria para buscar a movimentação: ");
        int idCat = in.nextInt();
        in.nextLine();

        try {

            Categoria cat = categoriaService.buscarCategoriaPorId(idCat);

            List<Movimentacao> list = movimentacaoService.buscarMovimentacaoPorCategoria(cat);

            if (list.isEmpty()) {
                System.out.println("Nenhuma movimentação para o usuário " + cat.getNome());
            }
            System.out.println("Movimentações do usuário " + cat.getNome() + ": ");
            list.forEach(x -> System.out.println(x));
        }
        catch(CategoriaException e) {
            throw new CategoriaException(e.getMessage());
        }
        catch(MovimentacaoException e) {
            throw new MovimentacaoException(e.getMessage());
        }
    }


    private static void buscarMovimentacaoAll(){

        try {
            
            List<Movimentacao> list = movimentacaoService.buscarMovimentacaoAll();

            if (!list.isEmpty()) {
                System.out.println("Movimentações: ");
                list.forEach(x -> System.out.println(x));
            }
            System.out.println("Nenhuma movimentação registrada");

        }
        catch(MovimentacaoException e) {
            throw new MovimentacaoException(e.getMessage());
        }


    }



    private static void removerMovimentacao() {
        System.out.print("Informe o ID da movimentação a ser removida: ");
        int idMovimentacao = in.nextInt();
        in.nextLine();

        try {
            movimentacaoService.removerMovimentacao(idMovimentacao);
            System.out.println("Movimentação removida com sucesso!");
        } 
        catch (MovimentacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    private static void adicionarUsuario() {
        try {
            System.out.print("Informe o nome do usuário: ");
            String nome = in.nextLine();

            System.out.print("Informe o email do usuário: ");
            String email = in.nextLine();

            System.out.print("Informe a senha do usuário: ");
            String senha = in.nextLine();

            Usuario usuario = new Usuario(nome, email, senha);
            usuarioService.adicionarUsuario(usuario);
            System.out.println("Usuário adicionado com sucesso!");
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void atualizarUsuario() {
        try {
            System.out.print("Informe o ID do usuário a ser atualizado: ");
            int idUsuario = in.nextInt();
            in.nextLine();

            System.out.print("Informe o novo nome do usuário: ");
            String nome = in.nextLine();

            System.out.print("Informe o novo email do usuário: ");
            String email = in.nextLine();

            System.out.print("Informe a nova senha do usuário: ");
            String senha = in.nextLine();

            Usuario usuario = new Usuario(idUsuario, nome, email, senha);
            usuarioService.atualizarUsuario(usuario);
            System.out.println("Usuário atualizado com sucesso!");
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarUsuarioPorId() {
        System.out.print("Informe o ID do usuário a ser buscado: ");
        int idUsuario = in.nextInt();
        in.nextLine();

        try {
            Usuario usuario = usuarioService.findByUsuarioId(idUsuario);
            System.out.println("Usuário encontrado: " + usuario);
        } 
        catch (UsuarioException e) {
            throw new UsuarioException(e.getMessage());
        }
    }


    private static void buscarUsuarioPorEmail(){
        System.out.print("Informe o Email do usuário a ser buscado: ");
        String emailUsu = in.nextLine();
        

        try {
            List<Usuario> list = usuarioService.findByUsuarioEmail(emailUsu);
            
            if (!list.isEmpty()) {
                list.forEach(x -> System.out.println(x));
            }
            System.out.println("Nenhum usuário com o e-mail " + emailUsu + " encontrado");
            
        }
        catch(UsuarioException e) {
            throw new UsuarioException(e.getMessage());
        }

    }

    private static void buscarUsuarioAll(){

        try {
            List<Usuario> list = usuarioService.findAll();

            if (!list.isEmpty()) {
                list.forEach(x -> System.out.println(x));
            }
            System.out.println("Nenhum usuário cadastrado!");
        }
        catch(UsuarioException e) {
            throw new UsuarioException(e.getMessage());
        }

    }

    private static void removerUsuario() {
        System.out.print("Informe o ID do usuário a ser removido: ");
        int idUsuario = in.nextInt();
        in.nextLine();

        try {
            usuarioService.removerUsuario(idUsuario);
            System.out.println("Usuário removido com sucesso!");
        } 
        catch (UsuarioException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
