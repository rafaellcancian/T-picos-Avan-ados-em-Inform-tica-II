package br.rlc.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.rlc.controllers.ProdutoCRUD;
import br.rlc.controllers.SessionContext;
import br.rlc.models.Produto;
import br.rlc.models.Usuario;

@ManagedBean(name="entregadorBean")
@SessionScoped
public class EntregadorBean {
	
	private String entregador;
	
	@PostConstruct
	public void init() {
		entregador = getUser().getNome();
	}
	
	public List<Produto> listarProdutos() {
		ProdutoCRUD crud = new ProdutoCRUD();
		crud.setup();
		
		List<Produto> listaProdutos = crud.listAll();
		
		return listaProdutos;
	}
	
    public Usuario getUser() {
        return (Usuario) SessionContext.getInstance().getUsuarioLogado();
     }

	public String getEntregador() {
		return entregador;
	}

	public void setEntregador(String entregador) {
		this.entregador = entregador;
	}
	
}
