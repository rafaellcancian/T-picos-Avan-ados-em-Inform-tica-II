package br.rlc.projeto02hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MoradorCRUD {
    protected SessionFactory sessionFactory;
    
    protected void setup() {
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
 
    protected void exit() {
        sessionFactory.close();
    }

    protected void create(Morador morador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(morador);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Morador> listAll() {
    	Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Morador> listaMoradores = session.createQuery("from Morador", Morador.class).getResultList();
        session.getTransaction().commit();

        return listaMoradores;
    }
    
}
