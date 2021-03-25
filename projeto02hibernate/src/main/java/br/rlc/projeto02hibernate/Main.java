package br.rlc.projeto02hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Main {
	
	MoradorCRUD crud = new MoradorCRUD();
    
    public Main() {
    	crud.setup();
    }
	
	public List<Morador> gerarMoradores(int andares, int apartamentosPorAndar) {
		List<Morador> listaMoradores = new ArrayList<Morador>();
		Random random = new Random();
		
    	for (int i = 1; i <= andares; i++) {
    		for (int j = 1; j <= apartamentosPorAndar; j++) {
    			String apartamento = i + "0" + j;
    			String pin;
    			boolean erro;
    			do {
    				pin = String.format("%04d", random.nextInt(10000));
    				erro = false;
        			for (int k = 0; k < listaMoradores.size(); k++) {
    					Morador m = listaMoradores.get(k);
        				if (pin.equals(m.getPin())) {
        					erro = true;
        				}
    				}
    			} while (erro == true);    				
    			listaMoradores.add(new Morador(apartamento, pin));
			}	
		}
    	return listaMoradores;
	}
	
	public void gerar(Main main) {
    	int andares, apartamentosPorAndar;
    	
    	andares = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de andares do edifício: "));
		do {
			apartamentosPorAndar = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de apartamentos por andar (Max. 9): "));
		} while (apartamentosPorAndar > 9);
		
		List<Morador> listaMoradores = new ArrayList<Morador>();
		listaMoradores = main.gerarMoradores(andares, apartamentosPorAndar);
		
		String[] opcoes = {"Sim", "Não"};
		
		if (JOptionPane.showOptionDialog(null, "Você deseja inserir no banco?", "Mensagem de confirmação",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]) == 0) {
			main.inserir(listaMoradores);
		} else {
		    for (int i = 0; i < listaMoradores.size(); i++) {
				Morador m = listaMoradores.get(i);
				System.out.println("Apt.: " + m.getApartamento() + " || " + "PIN: " + m.getPin());
			}
		}		
	}
	
	public void verificar(Main main) {		
    	if (main.consultar(JOptionPane.showInputDialog("Informe o PIN:"))) {
    		JOptionPane.showMessageDialog(null, "O PIN é válido.", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
    	} else {
    		JOptionPane.showMessageDialog(null, "O PIN é inválido.", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
    	}
	}
    
    public void inserir(List<Morador> listaMoradores) {    	
    	for (Morador m : listaMoradores) {
			crud.create(m);
    	}
    }
    
    public boolean consultar(String pin) {
        List<Morador> listaMoradores = crud.listAll();
        
        for (Morador m : listaMoradores) {
        	if (pin.equals(m.getPin())) {
        		return true;
        	}
		}
		return false;
    }
	
    public static void main(String[] args) {
		Main main = new Main();
    	
    	String[] opcoes = {"Gerar", "Verificar", "Sair"};
    	
    	while (true) {
        	int menu = JOptionPane.showOptionDialog(null, "Você deseja gerar moradores ou verificar se o PIN é válido?", "Mensagem de confirmação",
    				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    		
    		switch (menu) {
	    	case 0: main.gerar(main); break;
	    	case 1: main.verificar(main); break;
	    	case 2: System.exit(0); break;
    		}
    	}
    }
    
}
