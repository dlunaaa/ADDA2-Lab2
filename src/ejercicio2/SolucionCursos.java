package ejercicio2;
//2. Se desea diseñar un programa de formación que incluya varios cursos. Cada curso
//pertenece a un área de conocimiento (tecnología, ciencias, humanidades, …), tiene un
//coste de inscripción, una puntuación de relevancia (entero entre 1 y 5), y una duración en
//horas. Determine la selección de cursos de forma que:
//• El programa debe incluir al menos un curso de cada área de conocimiento.
//• El número de cursos de tecnología seleccionados debe ser superior o igual a los
//seleccionados de cualquiera otra área.
//• Se debe garantizar que la duración media de los cursos seleccionados sea de al
//menos 20 horas.
//• El coste total de los cursos seleccionados no debe superar un presupuesto total
//asignado para el programa.
//• Se debe maximizar la suma total de las puntuaciones de los cursos seleccionados

import java.io.IOException;

// datos de entrada:
// n - entero, numero de cursos
//m - entero, numbero de areas


import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import ejercicio1.SolucionAlmacen;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class SolucionCursos {
    private Integer numCursos;
    private Integer numAreas;
    private Integer costeTotal;

    private SolucionCursos(String path) throws IOException {
        DatosCursos.iniDatos(path);
        numCursos = DatosCursos.getNumCursos();
        numAreas = DatosCursos.getNumAreas();
        costeTotal = DatosCursos.getPresupuestoTotal();
        
        AuxGrammar.generate(DatosCursos.class, "modelos/ejercicio2.lsi", "gurobi_models/Ejercicio2-1.lp");
        GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-1.lp");
        
        Locale.setDefault(Locale.of("en", "US"));
        System.out.println(solution.toString((s, d) -> d > 0.0));
    	}
    
    public static void main(String[] args) {
        try {
            new SolucionCursos("resources/ejercicio2/DatosEntrada1.txt");
//            new SolucionAlmacen("resources/ejercicio1/DatosEntrada2.txt");
//            new SolucionCursos("resources/ejercicio2/DatosEntrada3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


