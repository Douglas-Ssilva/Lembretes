package br.com.admin.util;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		new EntityManagerProducer().createEntityManager();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		EntityManager entityManager = new EntityManagerProducer().createEntityManager();
		entityManager.close();
	}

}
