package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.time.LocalDate;
import conexao.Conexao;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection conexao = Conexao.conectar(); // Conexão com o banco de dados

        if (conexao != null) {
            int opcao = -1; // Inicializa com um valor diferente de 0
            while (opcao != 0) {  // O loop vai continuar enquanto a opção não for 0
                System.out.println("Escolha uma operação:");
                System.out.println("1. Inserir Produto");
                System.out.println("2. Atualizar Produto");
                System.out.println("3. Deletar Produto");
                System.out.println("0. Sair do Sistema");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        inserirProduto(scanner, conexao); // opção de inserir
                        break;
                    case 2:
                        atualizarProduto(scanner, conexao); // opção de atualizar
                        break;
                    case 3:
                        deletarProduto(scanner, conexao); // opção de deletar
                        break;
                    case 0:
                        System.out.println("Saindo do sistema..."); // opção de sair do sistema
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
    // método que insere as informações 
    public static void inserirProduto(Scanner scanner, Connection conexao) throws SQLException {
        System.out.println("Escolha o tipo de produto a ser inserido:");
        System.out.println("1. Produto Alimentício");
        System.out.println("2. Produto Vestuário");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Preço de Custo: ");
        float precoCusto = scanner.nextFloat();
        System.out.print("Preço de Venda: ");
        float precoVenda = scanner.nextFloat();
        scanner.nextLine();

        if (tipoProduto == 1) {
            // Produto Alimentício
            System.out.print("Calorias: ");
            int caloria = scanner.nextInt();
            System.out.print("Proteínas: ");
            int proteina = scanner.nextInt();
            System.out.print("Gorduras: ");
            int gordura = scanner.nextInt();
            System.out.print("Carboidratos: ");
            int carboidrato = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Data de validade (AAAA-MM-DD): ");
            String dataVal = scanner.nextLine();
            LocalDate dataValidade = LocalDate.parse(dataVal); // Converte para LocalDate
            // novo objeto
            ProdutoAlimenticio produto = new ProdutoAlimenticio(nome, precoCusto, precoVenda, caloria, proteina, gordura, carboidrato, dataValidade);
            produto.salvarProduto(conexao);

        } else if (tipoProduto == 2) {
            // Produto Vestuário
            System.out.print("Tamanho: ");
            String tamanho = scanner.nextLine();
            System.out.print("Cor: ");
            String cor = scanner.nextLine();
            System.out.print("Material: ");
            String material = scanner.nextLine();
            // novo objeto
            ProdutoVestuario produto = new ProdutoVestuario(nome, precoCusto, precoVenda, tamanho, cor, material);
            produto.salvarProduto(conexao);
        } else {
            System.out.println("Opção inválida.");
        }
    }
    // método que atualiza as informações
    public static void atualizarProduto(Scanner scanner, Connection conexao) throws SQLException {
    	
    	System.out.println("Escolha o tipo de produto a ser atualizado:");
        System.out.println("1. Produto Alimentício");
        System.out.println("2. Produto Vestuário");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Digite o ID do produto a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        

        if (tipoProduto == 1) {
            // Atualizar Produto Alimentício
            System.out.print("Novo Nome do produto: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Preço de Custo: ");
            float precoCusto = scanner.nextFloat();
            System.out.print("Novo Preço de Venda: ");
            float precoVenda = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Novas Calorias: ");
            int caloria = scanner.nextInt();
            System.out.print("Novas Proteínas: ");
            int proteina = scanner.nextInt();
            System.out.print("Novas Gorduras: ");
            int gordura = scanner.nextInt();
            System.out.print("Novos Carboidratos: ");
            int carboidrato = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nova Data de validade (AAAA-MM-DD): ");
            String dataVal = scanner.nextLine();
            LocalDate dataValidade = LocalDate.parse(dataVal);
            // novo objeto
            ProdutoAlimenticio produto = new ProdutoAlimenticio(nome, precoCusto, precoVenda, caloria, proteina, gordura, carboidrato, dataValidade);
            produto.atualizarProduto(conexao, id);

        } else if (tipoProduto == 2) {
            // Atualizar Produto Vestuário
            System.out.print("Novo Nome do produto: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Preço de Custo: ");
            float precoCusto = scanner.nextFloat();
            System.out.print("Novo Preço de Venda: ");
            float precoVenda = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Novo Tamanho: ");
            String tamanho = scanner.nextLine();
            System.out.print("Nova Cor: ");
            String cor = scanner.nextLine();
            System.out.print("Novo Material: ");
            String material = scanner.nextLine();
            // novo objeto
            ProdutoVestuario produto = new ProdutoVestuario(nome, precoCusto, precoVenda, tamanho, cor, material);
            produto.atualizarProduto(conexao, id);

        } else {
            System.out.println("Opção inválida.");
        }
    }
    // método que deleta as informações
    public static void deletarProduto(Scanner scanner, Connection conexao) throws SQLException {
        System.out.println("Escolha o tipo de produto a ser deletado:");
        System.out.println("1. Produto Alimentício");
        System.out.println("2. Produto Vestuário");
        int tipoProduto = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o ID do produto a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipoProduto == 1) {
            // Deletar Produto Alimentício
            ProdutoAlimenticio.deletarProduto(conexao, id);
            System.out.println("Produto deletado com sucesso");
        } else if (tipoProduto == 2) {
            // Deletar Produto Vestuário
            ProdutoVestuario.deletarProduto(conexao, id);
            System.out.println("Produto deletado com sucesso");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}