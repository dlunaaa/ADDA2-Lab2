package ejemplo1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

//Dado un conjunto de números enteros estrictamente positivos, encontrar el
//multiconjunto (se puede repetir varias veces cada número) formado por números del
//conjunto anterior que sume exactamente n, y que tenga el menor tamaño. El tamaño
//de un multiconjunto es la suma de todas las multiplicidades para cada uno de sus
//elementos. 

// Datos de entreada:
//n - entero, suma a conseguir
//m - entero, tamano de conjuntWo
//ei - entero, elemento en posicion i, i - de 0 a m-1

//Variables:
//xi - entero, numero de veces se repite el numero en posicion i, i de 0 a m-1

//Funcion objetivo:
//min(sumatorio de o a m-1)xi

//Restricciones:
//1) sumatorio de 0 a m-1(xi*ei) = n
//2) 0<= xi <= n/ei 	int xi, i de 0 a m-1

public class Solucion {
	public static Integer suma;
	public static List<Integer> elementos;
	
	public static Integer getSuma() {
		return suma;
	}
	
	public static Integer getNumElementos() {
		return elementos.size();
	}
	
	public static Integer getElemento(Integer i) {
		return elementos.get(i);
	}
	
	public static Integer getMultiplicidad(Integer i) {
		return suma / elementos.get(i);
	}
	
	public static void ejemplo1_model() throws IOException {
		DatosMulticonjunto.iniDatos("resources/p3/ejemplo1_1.txt");
//		DatosMulticonjunto.iniDatos("resources/Ejemplo1DatosEntrada2.txt");

		suma = DatosMulticonjunto.getSuma();
		elementos = DatosMulticonjunto.getListaNumeros();
		
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
//		AuxGrammar.generate(Ejemplo1PLE.class,"lsi_models/Ejemplo1.lsi","gurobi_models/Ejemplo1-2.lp");
		AuxGrammar.generate(DatosMulticonjunto.class,"modelos/ejemplo1.lsi","gurobi_models/Ejemplo1-2.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejemplo1-2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		ejemplo1_model();
	}
}
