package exercise3;

import java.util.List;
import java.util.Map;

import ejercicio3.DatosFestival;
import us.lsi.common.Map2;

import java.util.HashMap;

public class SolucionFestival {

    public static SolucionFestival create(List<Integer> ls) {
        return new SolucionFestival(ls);
    }

    private Integer numAsignaciones;
    private Map<Integer, Integer> solucion;
    private Double costeTotal;
    private Integer unidadesTotales;

    private SolucionFestival(List<Integer> ls) {
    	System.out.println("Solution: "+ ls);
    	solucion = Map2.empty();
    	costeTotal =0.;
    	unidadesTotales = 0;
    	numAsignaciones = 0;
    	for (int i = 0; i<ls.size();i++) {
    		solucion.put(i,  ls.get(i));
    		unidadesTotales+=ls.get(i);
    		Integer tipoEntrada = i/DatosFestival.getNumAreas();
			Integer area = i%DatosFestival.getNumAreas();
			costeTotal +=DatosFestival.getCosteAsignacion(tipoEntrada, area)*ls.get(i);
    		
    	}
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen de asignaciones:\n");

        Map<Integer, Integer> aforoOcupadoPorArea = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> entradasPorArea = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
            Integer tipoEntrada = entry.getKey() / DatosFestival.getNumAreas();
            Integer area = entry.getKey() % DatosFestival.getNumAreas();
            Integer unidades = entry.getValue();

            if (unidades > 0) {
                aforoOcupadoPorArea.put(area, aforoOcupadoPorArea.getOrDefault(area, 0) + unidades);
                entradasPorArea.computeIfAbsent(area, k -> new HashMap<>())
                        .put(tipoEntrada, entradasPorArea.get(area).getOrDefault(tipoEntrada, 0) + unidades);
            }
        }

        for (int i = 0; i < DatosFestival.getNumAreas(); i++) {
            Integer aforoOcupado = aforoOcupadoPorArea.getOrDefault(i, 0);
            if (aforoOcupado > 0) {
                result.append(String.format("Aforo área %s: %d/%d\n",
                        DatosFestival.getArea(i).nombre(),
                        aforoOcupado,
                        DatosFestival.getAforoMaximoArea(i)));

                entradasPorArea.getOrDefault(i, new HashMap<>()).forEach((tipoEntrada, unidades) ->
                        result.append(String.format("Tipo de entrada %s asignadas: %d unidades\n",
                                DatosFestival.getTipoEntrada(tipoEntrada).tipo(), unidades)
                ));
            }
        }

        result.append(String.format("\nCoste total: %.2f\nUnidades totales: %d\n", costeTotal, unidadesTotales));

        return result.toString();
    }

    public Integer getNumAsignaciones() {
        return numAsignaciones;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getCosteTotal() {
        return costeTotal;
    }

    public Integer getUnidadesTotales() {
        return unidadesTotales;
    }
}