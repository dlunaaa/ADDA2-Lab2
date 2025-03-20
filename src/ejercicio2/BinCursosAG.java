package ejercicio2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.ag.BinaryData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;
import us.lsi.common.Set2;

public class BinCursosAG implements BinaryData<SolucionCursosAG> {
	
	public BinCursosAG(String linea) {
		DatosCursos.iniDatos(linea);
	}

	public Integer size() {
		return DatosCursos.getNumCursos();
	}	
	@Override
	public SolucionCursosAG solucion(List<Integer> ls) {
		return SolucionCursosAG.create(ls);
	}

	public Double goal(List<Integer> ls) {
		double res = 0;
		for (int i = 0; i <size(); i++) {
			if (ls.get(i) >0) {
				res += DatosCursos.getRelevancia(i);
			}
		}
		return res;
	}
	
	public Double penalizacion(List<Integer> ls) {
		double error1 =0., error2 =0., error3 =0.,  error4 =0.; // tenemos 4 restricciones -> 4 errores
//		
		Set<Integer> areas = Set2.empty();
		Double totalDuracion = 0.;
		Integer totalCoste = 0;
		Integer numCursosSeleccionados = 0;
		Map<Integer, Integer> sumaCursosSeleccionadosPorArea = new HashMap<Integer, Integer>(); // {num_de_area, num_de_cursos_de_area_seleccionados}
		
		for (int j = 0; j < DatosCursos.getNumAreas(); j++) {
			sumaCursosSeleccionadosPorArea.put(j, 0);
		}
		
		for (int i = 0; i < size(); i++) {
			if (ls.get(i) > 0) {
				areas.add(DatosCursos.getArea(i));
				totalDuracion += DatosCursos.getDuracion(i);
				totalCoste += DatosCursos.getCoste(i);
				numCursosSeleccionados += 1;
				sumaCursosSeleccionadosPorArea.put(DatosCursos.getArea(i), sumaCursosSeleccionadosPorArea.get(DatosCursos.getArea(i)) + 1);
			}
		}
		if (numCursosSeleccionados > 0) {
			Double mediaDuracion = totalDuracion / numCursosSeleccionados;
			if (mediaDuracion < 20.) {
				error1 = 20. - mediaDuracion;
			}
		}else {
			error1 +=2000;
		}
		
		if (totalCoste > DatosCursos.getPresupuestoTotal()) {
			error2 += DatosCursos.getPresupuestoTotal() - totalCoste;
		}
		if (areas.size() != DatosCursos.getNumAreas()) {
			error3 += (DatosCursos.getNumAreas() - areas.size()) * 10;
		}
		for (int j=1; j < sumaCursosSeleccionadosPorArea.size(); j++) {
			if (sumaCursosSeleccionadosPorArea.get(j) > sumaCursosSeleccionadosPorArea.get(0)) {
				error4 += (sumaCursosSeleccionadosPorArea.get(j) - sumaCursosSeleccionadosPorArea.get(0)) * 100;
			}
		}
		
		return error1*error1 + error2*error2 + error3*error3 + error4*error4;
		
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		return goal(ls) - 100 * penalizacion(ls);
	}

	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}



}
