package ejercicio3;

import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

import java.io.IOException;

public class SolucionFestival {
        private Integer numTiposEntradas;
        		Integer numAreas;
    
        private SolucionFestival(String path) throws IOException {
            DatosFestival.iniDatos(path);
            numAreas = DatosFestival.getNumAreas();
            numTiposEntradas = DatosFestival.getNumTiposEntrada();
            
            AuxGrammar.generate(DatosFestival.class, "modelos/ejercicio3.lsi", "gurobi_models/Ejercicio3-1.lp");
            GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-1.lp");
            
            Locale.setDefault(Locale.of("en", "US"));
            System.out.println(solution.toString((s, d) -> d > 0.0));
        	}
        
        public static void main(String[] args) {
            try {
                new SolucionFestival("resources/ejercicio3/DatosEntrada3.txt");

            } catch (IOException e) {
                e.printStackTrace();
            }
        
}}
