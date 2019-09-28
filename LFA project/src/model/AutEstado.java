package model;

import java.util.ArrayList;

public class AutEstado {

	private String nome;
	private ArrayList<AutTransicao> transicoes;
	private boolean eInicial;
	private boolean eFinal;
	
	public AutEstado() {
		
	}
	
	public AutEstado(String nome, ArrayList<AutTransicao> transicoes, boolean eInicial, boolean eFinal) {
		super();
		this.nome = nome;
		this.transicoes = transicoes;
		this.eInicial = eInicial;
		this.eFinal = eFinal;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<AutTransicao> getTransicoes() {
		return transicoes;
	}
	public void setTransicoes(ArrayList<AutTransicao> transicoes) {
		this.transicoes = transicoes;
	}

	public boolean iseInicial() {
		return eInicial;
	}

	public void seteInicial(boolean eInicial) {
		this.eInicial = eInicial;
	}

	public boolean iseFinal() {
		return eFinal;
	}

	public void seteFinal(boolean eFinal) {
		this.eFinal = eFinal;
	}
	
	@Override
	public String toString() {
		return "AutEstado [nome=" + nome + ", transicoes=" + transicoes + ", eInicial=" + eInicial + ", eFinal="
				+ eFinal + "]";
	}

	public String transicao(char input) {
		String destino = new String();
		
		for (AutTransicao t : transicoes ) {
			if (input == t.getInput().toCharArray()[0]) {
				destino = t.getNomeDestino();
			}
		}
		
		return destino;
	}
	
}
