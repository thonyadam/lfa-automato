package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AutEstado;
import model.AutTransicao;

public class Controle {
	
	public static Controle controle;

	private ArrayList<AutEstado> listaEstados;
	//private ArrayList<AutTransicao> listaTransicao;
	
	public Controle() {
		super();
		this.listaEstados = new ArrayList<AutEstado>();
		//this.listaTransicao = new ArrayList<AutTransicao>();
	}
	
	public ArrayList<AutEstado> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(ArrayList<AutEstado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	public void cadEstado() {
		
		String nome = JOptionPane.showInputDialog("Digite o nome do estado");
		
		ArrayList<AutTransicao> listaTransicao = this.cadTransicao();
		
		int tempBool = Integer.parseInt(JOptionPane.showInputDialog("Esse estado é final ou inicial ou intermediario?\n"
				+ " 0 - inicial \n"
				+ " 1 - intemediario \n"
				+ " 2 - final"));
		boolean eInicial = false;
		boolean eFinal = false;
		if (tempBool == 0) {
			eInicial = true;
		}
		if (tempBool == 2) {
			eFinal = true;
		}
		
		AutEstado estado = new AutEstado(nome, listaTransicao, eInicial, eFinal);
		
		this.listaEstados.add(estado);
		
	}
	
	public ArrayList<AutTransicao> cadTransicao() {
		ArrayList<AutTransicao> listaTransicao1 = new ArrayList<AutTransicao>();
		
		int opc = 1;
		
		while (opc == 1) {
			String destino = JOptionPane.showInputDialog("Digite o nome do estado destino");
			String input = JOptionPane.showInputDialog("Digite input da transicao");
			
			AutTransicao tempTransicao = new AutTransicao(destino, input);
			listaTransicao1.add(tempTransicao);
			
			opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar mais alguma transicao?\n"
					+ "1 - sim \n"
					+ "2 - não"));
		}
		
		return listaTransicao1;
	}
	
	public void listarEstados() {
		
		for(AutEstado e : listaEstados) {
			System.out.println(e);
		}
		
	}
	
	public boolean testarPalavra(String input) {
		boolean ePalavra = false;
		char[] letras = input.toCharArray();
		
		AutEstado inicial = new AutEstado();
		for(AutEstado e : listaEstados) {
			if (e.iseInicial() == true) {
				inicial = e;
			}
		}
		AutEstado atual = inicial;
		
		for (char c : letras ) {
			
			String proxEstado = "a";
			while (proxEstado != null) {
				String destino = atual.transicao(c);
				for(AutEstado e : listaEstados) {
					if (e.getNome() == destino) {
						atual = e;
					}
				}
			}
		}
		
		if(atual.iseFinal()) {
			ePalavra = true;
		}
		
		return ePalavra;
	}
	
	
}
