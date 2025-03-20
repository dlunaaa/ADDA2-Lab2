package ejercicio3;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.common.Map2;


public class SolucionFestivalAG {

    public static SolucionFestivalAG create(List<Integer> ls) {
        return new SolucionFestivalAG(ls);
    }

    private Integer numTiposEntradas;
    private Map<Integer, Integer> solucion;
    private Integer numAreas;

    public SolucionFestivalAG(List<Integer> ls) {
        System.out.println("SOLUCION" + ls);
        solucion = Map2.empty();
    }

    public Integer getNumTiposEntradas() {
        return numTiposEntradas;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Integer getNumAreas() {
        return numAreas;
    }
    
//    public String toString() {
//        StringBuilder result = new StringBuilder("Resumen de asignaciones:\n");
//
//        Map<Integer, Integer> aforoOcupadoPorArea = new HashMap<>();
//        Map<Integer, Map<Integer, Integer>> entradasPorArea = new HashMap<>();
//
//        for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
//            Integer tipoEntrada = entry.getKey() / DatosFestival.getNumAreas();
//            Integer area = entry.getKey() % DatosFestival.getNumAreas();
//            Integer unidades = entry.getValue();
//
//            if (unidades > 0) {
//                aforoOcupadoPorArea.put(area, aforoOcupadoPorArea.getOrDefault(area, 0) + unidades);
//                entradasPorArea.computeIfAbsent(area, k -> new HashMap<>())
//                        .put(tipoEntrada, entradasPorArea.get(area).getOrDefault(tipoEntrada, 0) + unidades);
//            }
//        }
//        for (int i = 0; i < DatosFestival.getNumAreas(); i++) {
//            Integer aforoOcupado = aforoOcupadoPorArea.getOrDefault(i, 0);
//            if (aforoOcupado > 0) {
//                result.append(String.format("Aforo área %s: %d/%d\n",
//                        DatosFestival.getArea(i).nombre(),
//                        aforoOcupado,
//                        DatosFestival.getAforoMaximoArea(i)));
//
//                entradasPorArea.getOrDefault(i, new HashMap<>()).forEach((tipoEntrada, unidades) ->
//                        result.append(String.format("Tipo de entrada %s asignadas: %d unidades\n",
//                                DatosFestival.getTipoEntrada(tipoEntrada).tipo(), unidades)
//                ));
//            }
//        }
//
//        result.append(String.format("\nCoste total: %.2f\nUnidades totales: %d\n", costeTotal, unidadesTotales));
//
//        return result.toString();
//    }
   }
 

