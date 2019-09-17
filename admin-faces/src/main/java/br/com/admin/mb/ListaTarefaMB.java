package br.com.admin.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admin.model.Tarefa;
import br.com.admin.service.TarefaService;
import br.com.admin.util.FacesUtil;

@Named
@ViewScoped
public class ListaTarefaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TarefaService service;
	private List<Tarefa> tarefas = new ArrayList<>();
	private List<Tarefa> tarefasSelecionadas = new ArrayList<>();

	@PostConstruct
	public void init() {
		tarefas = service.getAll();
	}

	public void deleteSelections() {
		for (Tarefa t : tarefasSelecionadas) {
			service.delete(t); // deleta do banco
			tarefas.remove(t); // deleta da lista em memoria
		}

		FacesUtil.addInfoMessage("Tarefa(s) excluídas com sucesso!");
	}

	public TarefaService getService() {
		return service;
	}

	public void setService(TarefaService service) {
		this.service = service;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public List<Tarefa> getTarefasSelecionadas() {
		return tarefasSelecionadas;
	}

	public void setTarefasSelecionadas(List<Tarefa> tarefasSelecionadas) {
		System.out.println("Setando selecionados: " + tarefasSelecionadas.size());
		this.tarefasSelecionadas = tarefasSelecionadas;
	}
}
