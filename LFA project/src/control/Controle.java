package control;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AutEstado;
import model.AutTransicao;

public class Controle {
	
	public static Controle controle;

	private ArrayList<AutEstado> listaEstados;
	private ArrayList<Character> listaAlfabeto;
	//private ArrayList<AutTransicao> listaTransicao;
	
	public Controle() {
		super();
		this.listaEstados = new ArrayList<AutEstado>();
		this.listaAlfabeto = new ArrayList<Character>();
		//this.listaTransicao = new ArrayList<AutTransicao>();
	}
	
	public ArrayList<AutEstado> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(ArrayList<AutEstado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Character> getListaAlfabeto() {
		return listaAlfabeto;
	}

	public void setListaAlfabeto(ArrayList<Character> listaAlfabeto) {
		this.listaAlfabeto = listaAlfabeto;
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
	
	private ArrayList<AutTransicao> cadTransicao() {
		ArrayList<AutTransicao> listaTransicao1 = new ArrayList<AutTransicao>();
		
		int opc = 1;
		opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar alguma transicao?\n"
				+ "1 - sim \n"
				+ "2 - não"));
		while (opc == 1) {
			String destino = JOptionPane.showInputDialog("Digite o nome do estado destino");
			String input = JOptionPane.showInputDialog("Digite input da transicao");
			
			AutTransicao tempTransicao = new AutTransicao(destino, input);
			listaTransicao1.add(tempTransicao);
			
			opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar mais alguma transicao?\n"
					+ "1 - sim \n"
					+ "2 - não"));
		}
		System.out.println(listaEstados);
		return listaTransicao1;
	}
	
	public void listarEstados() {
		System.out.println("Lista de estados:\n"
				+ "---------------");
		for(AutEstado e : listaEstados) {
			System.out.println(e);
		}
		System.out.println("---------------");
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
		//System.out.println("achou inicial " + inicial.getNome());
		AutEstado atual = inicial;
		
		String proxEstado = "null";
		for (char c : letras ) {
			//System.out.println("testando input " + c );
			if(proxEstado != null) {
				String destino = atual.transicao(c);
				//System.out.println("achou destino " + destino);
				if(!destino.equals("")) {
					for(AutEstado e : listaEstados) {
						//System.out.println("for elemento " + e.getNome());
						if (e.getNome().equals(destino)) {
							atual = e;
							//System.out.println("está no " + atual.getNome());
						}
					}
				}else {
					//System.out.println("set null");
					atual = null;
					proxEstado = null;
				}
			}
		}
		//System.out.println("rodou e está em " + atual.getNome());
		
		if(atual != null && atual.iseFinal()) {
			ePalavra = true;
		}
		//System.out.println("é palavra " + ePalavra);
		
		return ePalavra;
	}
	
	public void cadastrarAlfabeto() {
		
		int opc = 1;
		
		opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar alguma letra do alfabeto?\n"
				+ "1 - sim \n"
				+ "2 - não"));
		while (opc == 1) {
			String letra = JOptionPane.showInputDialog("Digite a letra");
			listaAlfabeto.add(letra.toCharArray()[0]);
			
			opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja cadastrar mais alguma letra do alfabeto?\n"
					+ "1 - sim \n"
					+ "2 - não"));
		}
	}
	
	public void verificarEquivalencia() {
		int tamanho  = listaEstados.size();
		//ArrayList<ArrayList<String>> tabela = new ArrayList<ArrayList<String>>(tamanho);
		ArrayList<String> listaEquivalentes = new ArrayList<String>();
		ArrayList<String> lista1 = new ArrayList<String>();
		ArrayList<String> lista2 = new ArrayList<String>();
		
		for(int i = 0; i < tamanho - 1; i++) {
			lista1.add(i, listaEstados.get(i).getNome());
			lista2.add(i, listaEstados.get(i+1).getNome());
		}
		
		for(int i = 0; i < tamanho - 1; i++) {
			for(int f = 0; f < tamanho - 1; f++) {
				String q1nome = lista1.get(i);
				String q2nome = lista2.get(f);
				if(!q1nome.equals(q2nome)) {
					listaEquivalentes.add(this.compararEstados(q1nome, q2nome));
				}
			}
		}
		/*for(int i = 1 ; i < tamanho; i++) {
			tabela.get(i).add(listaEstados.get(i-1).getNome());
		}
		for(int i = 1 ; i <= tamanho; i++) {
			tabela.get(0).add(listaEstados.get(i).getNome());
		}	
		
		for(int i = 1 ; i <= tamanho; i++) {
			for(int f = 1 ; f <= tamanho; f++) {
				String q1nome = tabela.get(i).get(0);
				String q2nome = tabela.get(0).get(f);
				listaEquivalentes.add(this.compararEstados(q1nome, q2nome));
			}
		}*/
		
		System.out.println("Lista de estados equivalentes:\n"
				+ "---------------");
		for(String e : listaEquivalentes) {
			System.out.println(e);
		}
		System.out.println("---------------");
	}
	
	private String compararEstados(String a, String b) {
		AutEstado q1 = new AutEstado();
		AutEstado q2 = new AutEstado();
		String resultado = new String();
		
		for(AutEstado e : listaEstados) {
			if (e.getNome().equals(a)) {
				q1 = e;
			}
			if (e.getNome().equals(b)) {
				q2 = e;
			}
		}
		
		System.out.println("Comparando " + q1.getNome() + " " + q2.getNome());
		
		boolean equivalente = true;
		System.out.println("bool1" + equivalente);
		for (char letra : listaAlfabeto) {
			String estado1 = q1.transicao(letra);
			String estado2 = q2.transicao(letra);
			
			System.out.println("Transicaoes " + estado1 + " " + estado2);
			
			if(!estado1.equals(estado2)) {
				equivalente = false;
			}
		}
		
		System.out.println("bool2" + equivalente);
		
		if(equivalente) {
			resultado = a + " equivalente a " + b;
		}
		
		return resultado;
	}
	
	
}
