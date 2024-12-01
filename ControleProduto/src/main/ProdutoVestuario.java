package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Classe produtoVestuario que herda as caracteristicas comuns e o metodo de calcular lucro da classe Produto
public class ProdutoVestuario extends Produto {
	// atributos específicos
	protected String tamanho;
	protected String cor;
	protected String material;
	
	// metodo construtor para adicionar as caracteristicas especificas  junto com as caracteristicas comuns
	public ProdutoVestuario(String nome, float precoCusto, float precoVenda, String tamanho, String cor, String material) {
		super(nome, precoCusto, precoVenda);
		setTamanho(tamanho);
		setCor(cor);
		setMaterial(material);
	}
	// método get para acessar o atributo
	public String getTamanho() {
		return tamanho;
	}
	// método set para modificar o valor do atributo
	public void setTamanho(String tamanho) {
		if( tamanho == null || tamanho.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
			throw new IllegalArgumentException("O tamanho não pode estar vazio");
		}
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		if( cor == null || cor.trim().isEmpty()) {
			throw new IllegalArgumentException("Cor não pode estar vazio");
		}
		this.cor = cor;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		if( material == null || material.trim().isEmpty()) {
			throw new IllegalArgumentException("O material não pode estar vazio");
		}
		this.material = material;
	}
	// método para formatar a string e adicionar as caracteristicas específicas junto com as caracteristicas comuns
	public String descricao() {
		return super.descricao() + "\nTamanho: " + tamanho + "\nCor: " + cor + "\nMaterial: " + material;
	}

	// Método string com o comando SQL para salvar o produto
	public void salvarProduto(Connection conexao) {
		// método que insere as informações
	    String sql = "INSERT INTO produtos_vestuario (nome, precoCusto, precoVenda, tamanho, cor, material) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getNome()); // Define o nome na consulta SQL
	        stmt.setFloat(2, getPrecoCusto()); // Define o preço de custo na consulta SQL
	        stmt.setFloat(3, getPrecoVenda()); // Define o preço de venda na consulta SQL
	        stmt.setString(4, getTamanho()); // Define o tamanho na consulta SQL
	        stmt.setString(5, getCor()); // Define a cor na consulta SQL
	        stmt.setString(6, getMaterial()); // Define o material na consulta SQL
	        
	        int rowsUpdated = stmt.executeUpdate(); // atualiza o banco de dados
	        if (rowsUpdated > 0) {
	            System.out.println("Produto salvo com sucesso."); 
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao salvar produto: " + e.getMessage());
	    }
	}

	public void atualizarProduto(Connection conexao, int id) {
		// método que atualiza as informações
	    String sql = "UPDATE produtos_vestuario SET nome = ?, precoCusto = ?, precoVenda = ?, tamanho = ?, cor = ?, material = ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getNome()); 
	        stmt.setFloat(2, getPrecoCusto());
	        stmt.setFloat(3, getPrecoVenda());
	        stmt.setString(4, getTamanho());
	        stmt.setString(5, getCor());
	        stmt.setString(6, getMaterial());
	        stmt.setInt(7, id);
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto atualizado com sucesso.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar produto: " + e.getMessage());
	    }
	}

	public static void deletarProduto(Connection conexao, int id) {
		// método que deleta as informações
	    String sql = "DELETE FROM produtos_vestuario WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        
	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto deletado com sucesso.");
	        } 
	    } catch (SQLException e) {
	        System.err.println("Erro ao deletar produto: " + e.getMessage());
	    }
	}
}
