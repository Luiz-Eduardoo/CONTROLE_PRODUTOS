package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate; // importa localDate para poder fazer o sistema de data de validade

// Classe produtoAlimenticio que herda as caracteristicas comuns e o metodo de calcular lucro da classe Produto
public class ProdutoAlimenticio extends Produto {
	// atributos específicos
	protected int caloria;
	protected int proteina;
	protected int gordura;
	protected int carboidrato;
	protected LocalDate dataVal;
	
	// metodo construtor para adicionar as caracteristicas especificas  junto com as caracteristicas comuns
	public ProdutoAlimenticio(String nome, float precoCusto, float precoVenda, int caloria, int proteina, int gordura, int carboidrato, LocalDate dataVal) {
		super(nome, precoCusto, precoVenda);
		setCaloria(caloria);
		setProteina(proteina);
		setGordura(gordura);
		setCarboidrato(carboidrato);
		setDataVal(dataVal);
	}
	// método get para acessar o atributo
	public int getCaloria() {
		return caloria;
	}

	// método set para modificar o valor do atributo
	public void setCaloria(int caloria) {
		if(caloria < 0) { // se a caloria for abaixo de 0 ele vai dar exceção
			throw new IllegalArgumentException("Caloria não pode ser negativa");
		}
		this.caloria = caloria;
	}


	public int getProteina() {
		return proteina;
	}


	public void setProteina(int proteina) {
		if(proteina < 0) { // se a proteina for abaixo de 0 ele vai dar exceção
			throw new IllegalArgumentException("Proteina não pode ser negativa");
		}
		this.proteina = proteina;
	}


	public int getGordura() {
		return gordura;
	}


	public void setGordura(int gordura) {
		if(gordura < 0) { // se a gordura for abaixo de 0 ele vai dar exceção
			throw new IllegalArgumentException("Gordura não pode ser negativa");
		}
		this.gordura = gordura;
	}


	public int getCarboidrato() {
		return carboidrato;
	}


	public void setCarboidrato(int carboidrato) {
		if(carboidrato < 0) { // se a carboidrato for abaixo de 0 ele vai dar exceção
			throw new IllegalArgumentException("Carboidrato não pode ser negativo");
		}
		this.carboidrato = carboidrato;
	}


	public LocalDate getDataVal() {
		return dataVal;
	}


	public void setDataVal(LocalDate dataVal) {
		if(dataVal == null) { // se a data estiver nula vai dar exceção
			throw new IllegalArgumentException("A data de validade deve ser informada");
		}
		this.dataVal = dataVal;
	}
	// método para formatar a string e adicionar as caracteristicas específicas junto com as caracteristicas comuns
	public String descricao()  {
		return super.descricao() + "\nInformações Nutricionais: " + "\nCalorias: " + caloria + "\nProteinas: " + proteina + "\nGorduras: " + gordura + "\nCarboidratos: " + carboidrato + "\nData de validade: " + dataVal;
	}
	
	public void salvarProduto(Connection conexao) {
		// método que insere as informações
	    String sql = "INSERT INTO produtos_alimenticio (nome, precoCusto, precoVenda, caloria, proteina, gordura, carboidrato, dataVal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getNome()); // Define o nome na consulta SQL
	        stmt.setFloat(2, getPrecoCusto()); // Define o preço de custo na consulta SQL
	        stmt.setFloat(3, getPrecoVenda()); // Define o preço de venda na consulta SQL
	        stmt.setInt(4, getCaloria()); // Define a quantidade de calorias na consulta SQL
	        stmt.setInt(5, getProteina()); // Define a quantidade de proteinas na consulta SQL
	        stmt.setInt(6, getGordura()); // Define o quantidade de gordura na consulta SQL
	        stmt.setInt(7, getCarboidrato()); // Define o quantidade de carboidratos na consulta SQL
	        stmt.setObject(8, getDataVal());  // Define a data de validade na consulta SQL

	        int rowsUpdated = stmt.executeUpdate(); // atualiza o banco de dados
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício salvo com sucesso.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao salvar produto alimentício: " + e.getMessage());
	    }
	}


// Método string com o comando SQL para atualizar o produto
	public void atualizarProduto(Connection conexao, int id) {
		// método que atualiza as informações
	    String sql = "UPDATE produtos_alimenticio SET nome = ?, precoCusto = ?, precoVenda = ?, caloria = ?, proteina = ?, gordura = ?, carboidrato = ?, dataVal = ? WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setString(1, getNome());
	        stmt.setFloat(2, getPrecoCusto());
	        stmt.setFloat(3, getPrecoVenda());
	        stmt.setInt(4, getCaloria());
	        stmt.setInt(5, getProteina());
	        stmt.setInt(6, getGordura());
	        stmt.setInt(7, getCarboidrato());
	        stmt.setObject(8, getDataVal());
	        stmt.setInt(9, id);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício atualizado com sucesso.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar produto alimentício: " + e.getMessage());
	    }
	}
	// método que deleta as informações
	public static void deletarProduto(Connection conexao, int id) {
	    String sql = "DELETE FROM produtos_alimenticio WHERE id = ?";
	    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	        stmt.setInt(1, id);

	        int rowsUpdated = stmt.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Produto alimentício deletado com sucesso.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao deletar produto alimentício: " + e.getMessage());
	    }
	}
}
