package br.com.admin.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.admin.model.Status;
import br.com.admin.model.Tarefa;
import br.com.admin.service.TarefaService;

@Model
public class ChartTarefasMB implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private TarefaService service;
	
	public List<Tarefa> getList(){
		return service.getAll();
	}
	
	public BarChartModel getModel() {
		BarChartModel barChartModel = new BarChartModel();
		barChartModel.setAnimate(true);
		barChartModel.setTitle("Status das Tarefas");
		barChartModel.setLegendPosition("ne");
		
		ChartSeries chartSeries = new ChartSeries();
		chartSeries.setLabel("Em Andamento");
		ChartSeries chartSeries2 = new ChartSeries();
		chartSeries2.setLabel("Finalizado");
		
		int qtdFinalizado= 0;
		int qtdAndamento= 0;
		
		for(Tarefa t: getList()) {
			if (t.getStatus().getDescricao().equalsIgnoreCase("FINALIZADO")) {
				qtdFinalizado+= 1;
			}else {
				qtdAndamento+= 1;
			}
		}
		
		chartSeries.set(" ", qtdAndamento); //Status.ANDAMENTO pois aparecia andamento no gráfico
		chartSeries2.set(Status.FINALIZADO, qtdFinalizado);
		
		barChartModel.addSeries(chartSeries);
		barChartModel.addSeries(chartSeries2);
		
		Axis axis = barChartModel.getAxis(AxisType.X);
		axis.setLabel("Tarefas");
		
		return barChartModel;
		
	}

}
