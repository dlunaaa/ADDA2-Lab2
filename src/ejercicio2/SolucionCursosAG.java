package ejercicio2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Map2;

public class SolucionCursosAG {

    public static SolucionCursosAG create(List<Integer> ls) {
        return new SolucionCursosAG(ls);
    }

    private Integer numCursos;
    private Map<Integer, Integer> solucion;
    private Double puntuacionTotal;
    private Integer costeTotal;

    private SolucionCursosAG(List<Integer> ls) {
        System.out.println("SOLUCION" + ls);
        solucion = Map2.empty();
    }

//    @Override
//    public String toString() {
//        return solucion.entrySet().stream()
//                .map(p -> "Curso " + p.getKey() + ": Seleccionado")
//                .collect(Collectors.joining("\n", "Cursos seleccionados:\n", String.format("\nTotal de cursos seleccionados: %d\nPuntuación total: %.2f\nCoste total: %d", numCursos, puntuacionTotal, costeTotal)));
//    }

    public Integer getNumCursos() {
        return numCursos;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Integer getCosteTotal() {
        return costeTotal;
    }
}