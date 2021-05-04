package br.rlc.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import br.rlc.controllers.SessionContext;
import br.rlc.controllers.UsuarioCRUD;
import br.rlc.models.Usuario;

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
	private String nome;
	private String senha;
	
	public String entrar() {
		UsuarioCRUD crud = new UsuarioCRUD();
		crud.setup();
		
		List<Usuario> listaUsuarios = crud.listAll();
		
		for (Usuario u : listaUsuarios) {
			if (nome.equals(u.getNome()) && senha.equals(u.getSenha())) {
				if (u.getTipo().equals("Cliente")) {
					SessionContext.getInstance().setAttribute("usuarioLogado", u);
					return "cliente/areaCliente.xhtml?faces-redirect=true";
				} else if (u.getTipo().equals("Entregador")) {
					SessionContext.getInstance().setAttribute("usuarioLogado", u);
					return "entregador/areaEntregador.xhtml?faces-redirect=true";
				}
			}
		}
		return "erro.xhtml?faces-redirect=true";
	}
	
    public String sair() {
        SessionContext.getInstance().encerrarSessao();
        return "/index.xhtml?faces-redirect=true";
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
	
}
