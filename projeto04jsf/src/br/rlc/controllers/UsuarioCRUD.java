package br.rlc.controllers;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import br.rlc.models.Usuario;

public class UsuarioCRUD {
	
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

    public void create(Usuario usuario) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(usuario);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Usuario> listAll() {
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Usuario> listaUsuarios = session.createQuery("from Usuario", Usuario.class).getResultList();
        session.getTransaction().commit();

        return listaUsuarios;
    }
    
}
