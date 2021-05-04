package br.rlc.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.rlc.controllers.UsuarioCRUD;
import br.rlc.models.Usuario;

@ManagedBean(name="cadastroBean")
@RequestScoped
public class CadastroBean {
	
	private String nome;
	private String senha;
	private String confirmarSenha;
	private String tipo;
	
	public String cadastrar() {
		UsuarioCRUD crud = new UsuarioCRUD();
		crud.setup();
		
		List<Usuario> listaUsuarios = crud.listAll();
		
		for (Usuario u : listaUsuarios) {
			if (nome.equals(u.getNome())) {
				return "erro.xhtml?faces-redirect=true";
			}
		}
		
		if (senha.equals(confirmarSenha)) {
			crud.create(new Usuario(nome, senha, tipo));
			return "index.xhtml?faces-redirect=true";
		}
		return "erro.xhtml?faces-redirect=true";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
