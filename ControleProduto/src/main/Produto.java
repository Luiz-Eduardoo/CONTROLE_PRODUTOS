package main;
//classe abstrata com os metodos abstratos
public abstract class Produto {
	// atributos comuns
	protected String nome;
	protected float precoCusto;
	protected float precoVenda;
	
	// metodo construtor para montar as caracteristicas
	public Produto(String nome, float precoCusto, float precoVenda) {
		setNome(nome);
		setPrecoCusto(precoCusto);
		setPrecoVenda(precoVenda);
	}
	// método get para acessar o atributo
	public String getNome() {
		return nome;
	}

	// método set para modificar o valor do atributo
	public void setNome(String nome) {
		if( nome == null || nome.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
			throw new IllegalArgumentException("O nome não pode estar vazio");
		}
		this.nome = nome;
	}


	public float getPrecoCusto() {
		return precoCusto;
	}


	public void setPrecoCusto(float precoCusto) {
		if(precoCusto <=0) { // se o preço de custo for abaixo ou igual a 0 ele vai dar exceção
			throw new IllegalArgumentException("O Preço de custo deve ser maior que 0");
		}
		this.precoCusto = precoCusto;
	}


	public float getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(float precoVenda) {
		if(precoVenda <=0) { // se o preço de venda for abaixo ou igual a 0 ele vai dar exceção
			throw new IllegalArgumentException("O Preço de venda deve ser maior que 0");
		}
		this.precoVenda = precoVenda;
	}
	// método para formatar a string com as caracteristicas
	public String descricao() {
		return "Nome do Produto: " + nome + "\nPreço de custo: " + precoCusto + "\nPreço de Venda: " + precoVenda;
	}
	// metodo de calcular lucro 
	void CalcularLucro() {
	    float lucro = precoVenda - precoCusto;
	    System.out.println("Lucro da venda: " + lucro);
	}
}
