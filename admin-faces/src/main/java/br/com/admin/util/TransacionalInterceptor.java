package br.com.admin.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TransacionalInterceptor implements Serializable {//Capta as transações dos métodos que forem notados com Transaction

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	@AroundInvoke
	public Object invoke(InvocationContext invocationContext) throws Exception {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean criador = false;

		try {
			if (!entityTransaction.isActive()) {
				// truque para fazer o rollback no que já passou
				// senao, um futuro commit confirmaria até mesmo operações sem transação
				entityTransaction.begin();
				entityTransaction.rollback();

				// agr sim inicie a transação
				entityTransaction.begin();
				criador = true;
			}
			return invocationContext.proceed();
		} catch (Exception e) {
			if (entityTransaction != null && criador) {
				entityTransaction.rollback();
			}
			throw e;
		} finally {
			if (entityTransaction != null && entityTransaction.isActive() && criador) {
				entityTransaction.commit();
			}
		}
	}

}
