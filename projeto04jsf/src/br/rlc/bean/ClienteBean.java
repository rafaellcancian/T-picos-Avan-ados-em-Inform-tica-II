package br.rlc.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.rlc.controllers.ProdutoCRUD;
import br.rlc.controllers.SessionContext;
import br.rlc.models.Produto;
import br.rlc.models.Usuario;

@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean {
	
	private String produto;
	private String descricao;
	private String origem;
	private String destino;
	private String cliente;
	
	@PostConstruct
	public void init() {
		cliente = getUser().getNome();
	}
	
	public String solicitarFrete() {
		ProdutoCRUD crud = new ProdutoCRUD();
		crud.setup();
		
		crud.create(new Produto(produto, descricao, origem, destino, cliente));
		limparCampos();
		
		return "areaCliente.xhtml?faces-redirect=true";
	}
	
	public List<Produto> listarProdutosCliente() {
		ProdutoCRUD crud = new ProdutoCRUD();
		crud.setup();
		
		List<Produto> listaProdutos = crud.queryWhere(cliente);
		
		return listaProdutos;
	}
	
	public void limparCampos() {
		this.produto = "";
		this.descricao = "";
		this.origem = "";
		this.destino = "";
	}
	
    public Usuario getUser() {
        return (Usuario) SessionContext.getInstance().getUsuarioLogado();
     }
	
	public String getProduto() {
		return produto;
	}
	
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getOrigem() {
		return origem;
	}
	
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
}
