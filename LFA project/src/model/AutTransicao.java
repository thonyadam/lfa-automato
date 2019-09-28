package model;

public class AutTransicao {

	private String nomeDestino;
	private String input;
	
	public AutTransicao(String nomeDestino, String input) {
		super();
		this.nomeDestino = nomeDestino;
		this.input = input;
	}
	
	public String getNomeDestino() {
		return nomeDestino;
	}
	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "AutTransicao [nomeDestino=" + nomeDestino + ", input=" + input + "]";
	}
	
}
