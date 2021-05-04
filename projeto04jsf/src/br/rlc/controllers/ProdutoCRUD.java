package br.rlc.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.rlc.models.Produto;

public class ProdutoCRUD {
	
	protected SessionFactory sessionFactory;
    
    public void setup() {
        // code to load Hibernate Session factory
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    	      //  .configure() // configures settings from hibernate.cfg.xml
    	      //  .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    		//sessionFactory = configuration.buildSessionFactory();
    	} catch (Exception ex) {
    	   StandardServiceRegistryBuilder.destroy(registry);
    	}
    }
 
    public void exit() {
        sessionFactory.close();
    }

    public void create(Produto Produto) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(Produto);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public void delete(int ProdutoId) {
    	Produto Produto = new Produto();
        Produto.setId(ProdutoId);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(Produto);
     
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Produto> listAll() {
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Produto> listaProdutos = session.createQuery("from Produto", Produto.class).getResultList();
        session.getTransaction().commit();

        return listaProdutos;
    }
    
    public List<Produto> queryWhere(String cliente) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();

    	List<Produto> listaProdutosCliente = session.createQuery("from Produto where cliente=" + "'" + cliente + "'", Produto.class).getResultList();
        
        return listaProdutosCliente ;
    }
    
}
