package br.com.admin.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.admin.dao.TarefaDAO;
import br.com.admin.model.Tarefa;
import br.com.admin.util.Transacional;

//Classe na qual fica toda regra de negócio, faz o meio de campo entre o dao e o controller
public class TarefaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TarefaDAO dao;
	
	@Transacional
	public void insert(Tarefa t) {
		if(t.isInclusao()) {
			t.setCriacao(new Date());
		}
		
		if(t.isEdicao()) {
			t.setEdicao(new Date());
		}
		dao.insert(t);
	}
	
	@Transacional
	public void delete(Tarefa t) {
		dao.delete(t);
	}
	
	public Tarefa findById(Long id) {
		return dao.findById(id);
	}
	
	public List<Tarefa> getAll(){
		return dao.listAll();
	}

}
