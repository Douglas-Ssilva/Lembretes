 package br.com.admin.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admin.model.Tarefa;
import br.com.admin.service.TarefaService;

@Named
@ViewScoped
public class CadastroTarefaMB implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private TarefaService tarefaService;
	private Tarefa tarefa= new Tarefa();
	private Long idTarefa;
	
	public void init() {
		if (idTarefa != null) {
			tarefa= tarefaService.findById(idTarefa);
		}
	}
	
	public String redirect() {
		return "lista-tarefa?faces-redirect=true";
	}
	
	public String salvar() {
		System.out.println(tarefa);
		tarefaService.insert(tarefa);
		return redirect();
	}
	
	public String deletar() {
		tarefaService.delete(tarefa);
		return redirect();
	}
	
	public String invalidateSession() {
		return "/index?faces-redirect=true";
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	public Long getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(Long idTarefa) {
		this.idTarefa = idTarefa;
	}
}
