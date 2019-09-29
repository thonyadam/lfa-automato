package view;

import javax.swing.JOptionPane;

import control.Controle;

public class Menu {
	
	
	
	public static void main (String[] args){
		Controle controle = new Controle();
        int opc = 0;
        
    while (opc != 5){  
        opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha a op��o" 
        + "\n1 - Cadastrar Estado"
        + "\n2 - Listar Estados"
        + "\n3 - Testar Palavra"
        + "\n4 - Cadastrar Alfabeto"
        + "\n5 - Verificar Equivalencia"
        + "\n6 - Sair"));
        
        switch (opc){
            case 1:
                controle.cadEstado();
                break;
            case 2:
                controle.listarEstados();
                break;
            case 3:
                String palavra = JOptionPane.showInputDialog("Digite a palavra a ser testada");
                boolean reposta = controle.testarPalavra(palavra);
                if (reposta == true) {
                	JOptionPane.showMessageDialog(null,"� palavra do alfabeto");
                }else {
                	JOptionPane.showMessageDialog(null,"N�o � palavra do alfabeto");
                }
                break;
            case 4:
                controle.cadastrarAlfabeto();
                break;
            case 5:
                controle.verificarEquivalencia();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Saindo do Sistema");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Op��o invalida");
                
            
        }
    }
    }
}
