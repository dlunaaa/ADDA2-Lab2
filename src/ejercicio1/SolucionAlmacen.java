package ejercicio1;
//1. Una empresa dispone de varios almacenes, cada uno con una capacidad máxima en
//metros cúbicos. Se desea planificar la distribución de distintos productos, cada uno con
//un volumen específico. Cada producto puede ser enviado a un único almacén o no
//enviarse. Además, algunos productos son incompatibles para almacenarse juntos en el
//mismo almacén, ya sea por motivos de seguridad o logística. El objetivo es maximizar la
//cantidad de productos almacenados, cumpliendo con 

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import ejercicio1.DatosAlmacenes.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class SolucionAlmacen {
    
    private Integer numproductos;
    
    private SolucionAlmacen(String datosPath) throws IOException {
        DatosAlmacenes.iniDatos(datosPath);
        numproductos = DatosAlmacenes.getNumProductos();
        
        // Генерация модели
//        AuxGrammar.generate(DatosAlmacenes.class, "modelos/ejercicio1.lsi", "gurobi_models/Ejercicio1-3.lp");
//        AuxGrammar.generate(DatosAlmacenes.class, "modelos/ejercicio1.lsi", "gurobi_models/Ejercicio1-2.lp");
        AuxGrammar.generate(DatosAlmacenes.class, "modelos/ejercicio1.lsi", "gurobi_models/Ejercicio1-1.lp");
        
        // Решение модели с Gurobi
//        GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-3.lp");
//        GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-2.lp");
        GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-1.lp");
        
        // Установка локали для корректного вывода чисел
        Locale.setDefault(Locale.of("en", "US"));
        System.out.println(solution.toString((s, d) -> d > 0.0));
    }
    
    public static void main(String[] args) {
        try {
            new SolucionAlmacen("resources/ejercicio1/DatosEntrada1.txt");
//            new SolucionAlmacen("resources/ejercicio1/DatosEntrada2.txt");
//            new SolucionAlmacen("resources/ejercicio1/DatosEntrada3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
