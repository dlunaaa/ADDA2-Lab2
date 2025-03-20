package exercise2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.lsi.ag.BinaryData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;


public class BinCursos implements BinaryData<SolucionCursos> {

	public BinCursos(String linea) {
		DatosCursos.iniDatos(linea);
	}

	@Override
	public Integer size() {
		return DatosCursos.getNumCursos();
	}	
	public Double goal(List<Integer> ls) {
		Double sum = 0.;
		for (int i=0; i < size(); i++) {
			if (ls.get(i) == 1) {
				sum += DatosCursos.getRelevancia(i);
			}
		}
		
		return sum;
	}
	public Double error(List<Integer> ls) {
		Double error1 = 0.;
		Double error2 = 0.;
		Double error3 = 0.;
		Double error4 = 0.;
		Integer price = 0;
		Double duration = 0.;
		Integer cont = 0;
		Map<Integer, Integer> area_number = new HashMap<Integer, Integer>();
		for (int i=0; i<DatosCursos.getNumAreas(); i++) {
			area_number.put(i, 0);
		}
		
		for (int i=0; i < size(); i++) {
			if (ls.get(i) == 1) {
				price += DatosCursos.getCoste(i);
				duration += DatosCursos.getDuracion(i);
				cont+=1;
				area_number.put(DatosCursos.getArea(i), area_number.get(DatosCursos.getArea(i))+1);
				
			}
		}
		
		if (price > DatosCursos.getPresupuestoTotal()) {
			error1 += (price - DatosCursos.getPresupuestoTotal())*1000;
		}
		if (duration/cont < 20) {
			error2 += (20 - duration/cont)*1000;
		}
		for (int i=1; i < DatosCursos.getNumAreas(); i++) {
			if (area_number.get(i) > area_number.get(0)) {
				error3 += (area_number.get(i) - area_number.get(0)) * 100000;
			}
			if (area_number.get(i) == 0 ) {
				error4 += 1000;
			}
		}
		
		
		return error1*error1 + error2*error2 + error3*error3 + error4*error4;
	}
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		return goal(ls) - error(ls);
	}

	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public SolucionCursos solucion(List<Integer> value) {

		return SolucionCursos.create(value);
	}

}
