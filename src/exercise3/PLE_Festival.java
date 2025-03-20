package exercise3;

import java.io.IOException;
import java.util.Locale;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;

import us.lsi.solve.AuxGrammar;

public class PLE_Festival {

	public static void model() throws IOException {
//		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada1.txt");
//		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada2.txt");
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada3.txt");

		DatosFestival.getNumTiposEntrada();
		DatosFestival.getNumAreas();
		
//		AuxGrammar.generate(DatosFestival.class,"modelos/exercise3.lsi","gurobi_models/Exercise3-1.lp");
//		AuxGrammar.generate(DatosFestival.class,"modelos/exercise3.lsi","gurobi_models/Exercise3-2.lp");
		AuxGrammar.generate(DatosFestival.class,"modelos/exercise3.lsi","gurobi_models/Exercise3-3.lp");
		
//		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise3-1.lp");
//		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise3-2.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise3-3.lp");
		
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		 model();
	}



}
