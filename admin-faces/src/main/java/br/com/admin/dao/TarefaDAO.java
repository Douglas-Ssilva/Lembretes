package br.com.admin.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.github.adminfaces.template.exception.BusinessException;

import br.com.admin.model.Tarefa;

public class TarefaDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	public void insert(Tarefa tarefa) {
		this.entityManager.merge(tarefa);
	}
	
	public void delete(Tarefa t) {
		try {
			t= findById(t.getId());
			this.entityManager.remove(t);
			this.entityManager.flush();
		} catch (RuntimeException e) {
			throw new BusinessException("A tarefa não pode ser deletada!");
		}
	}

	public Tarefa findById(Long id) {
		return this.entityManager.find(Tarefa.class, id);
	}
	
	public List<Tarefa> listAll(){
		return this.entityManager.createQuery("SELECT t FROM Tarefa t", Tarefa.class).getResultList();
	}
}
