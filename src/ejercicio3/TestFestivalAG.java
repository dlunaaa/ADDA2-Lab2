package ejercicio3;

import java.util.List;
import java.util.Locale;

import ejercicio3.RangeFestivalAG;
import ejercicio3.SolucionFestivalAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestFestivalAG {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		RangeFestivalAG p = new RangeFestivalAG("resources/ejercicio3/DatosEntrada1.txt");
		
		
		AlgoritmoAG<List<Integer>,SolucionFestivalAG> ap = AlgoritmoAG.of(p);
		ap.ejecuta();

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}

}
