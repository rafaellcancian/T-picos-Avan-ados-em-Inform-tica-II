package br.rlc.projeto01;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Main {
	
    MySQLR mysqlr;
    
    public Main() {
        mysqlr = new MySQLR();
    }
	
	public ArrayList<Morador> gerarMoradores(int andares, int apartamentosPorAndar) {
		ArrayList<Morador> listaMoradores = new ArrayList<Morador>();
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
		
		ArrayList<Morador> listaMoradores = new ArrayList<Morador>();
		listaMoradores = main.gerarMoradores(andares, apartamentosPorAndar);
		
		String[] opcoes = {"Sim", "Não"};
		
		if (JOptionPane.showOptionDialog(null, "Você deseja inserir no banco?", "Mensagem de confirmação",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]) == 0) {
	        try {
	        	main.conectar();
	        	
	        	for (int i = 0; i < listaMoradores.size(); i++) {
					Morador morador = listaMoradores.get(i);
					main.inserir(morador);
	        	}
	        	
				main.mysqlr.stmt.close();
				main.mysqlr.conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERRO: " + e, "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
			}    
		} else {
		    for (int i = 0; i < listaMoradores.size(); i++) {
				Morador m = listaMoradores.get(i);
				System.out.println("Apt.: " + m.getApartamento() + " || " + "PIN: " + m.getPin());
			}
		}		
	}
	
	public void verificar(Main main) {		
		try {
        	main.conectar();
        	
        	if (main.consultar(JOptionPane.showInputDialog("Informe o PIN:"))) {
        		JOptionPane.showMessageDialog(null, "O PIN é válido.", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
        	} else {
        		JOptionPane.showMessageDialog(null, "O PIN é inválido.", "Mensagem de aviso", JOptionPane.WARNING_MESSAGE);
        	}
        	
			main.mysqlr.stmt.close();
			main.mysqlr.conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
		}  	
	}
	
    public void conectar() {
        boolean connected = mysqlr.connect("localhost", "3306", "javaee1", "root", "root");
        if (connected) {
            System.out.println("Base de dados conectada.");
        } else {
            System.out.println("Base de dados não conectada.");
        }
    }
    
    public void inserir(Morador m) {
        String query = "INSERT INTO MORADOR (APARTAMENTO, PIN) "
                + "VALUES ('" + m.getApartamento() + "', '" + m.getPin() + "')";
        int status = mysqlr.executeUpdate(query);
        if (status == 1) {
        	System.out.println("Dados inseridos com sucesso!");
        } else {
        	System.out.println("Erro ao inserir dados!");
        }
    }
    
    public boolean consultar(String pin) {
        ResultSet rs = mysqlr.executeQuery("SELECT PIN FROM MORADOR");
        if (rs != null) {
            try {
                while (rs.next()) {
                	if (pin.equals(rs.getString("PIN"))) {
                		return true;
                	}
                }
            } catch (SQLException e) {
            	JOptionPane.showMessageDialog(null, "ERRO: " + e, "Mensagem de erro", JOptionPane.ERROR_MESSAGE);
            }
        }
		return false;
    }
	
    public static void main(String[] args) {
		Main main = new Main();
    	
    	String[] opcoes = {"Gerar", "Verificar"};
    	
		if (JOptionPane.showOptionDialog(null, "Você deseja gerar moradores ou verificar se o PIN é válido?", "Mensagem de confirmação",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]) == 0) {
			main.gerar(main);
		} else {
			main.verificar(main);
		}
    }
    
}
