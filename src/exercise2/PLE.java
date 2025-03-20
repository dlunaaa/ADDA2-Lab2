package exercise2;

import java.io.IOException;
import java.util.Locale;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;

import us.lsi.solve.AuxGrammar;

public class PLE {

	public static void model() throws IOException {
		DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada1.txt");
//		DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada2.txt");
//		DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada3.txt");

		DatosCursos.getNumCursos();
		DatosCursos.getNumAreas();
		DatosCursos.getPresupuestoTotal();
		
		AuxGrammar.generate(DatosCursos.class,"modelos/exercise2.lsi","gurobi_models/Exercise2-1.lp");
//		AuxGrammar.generate(PLE.class,"modelos/exercise2.lsi","gurobi_models/Exercise2-2.lp");
//		AuxGrammar.generate(PLE.class,"modelos/exercise2.lsi","gurobi_models/Exercise2-3.lp");
		
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise2-1.lp");
//		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise2-2.lp");
//		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Exercise2-3.lp");
		
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		 model();
	}



}
