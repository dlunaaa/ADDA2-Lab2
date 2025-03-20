package exercise3;
import java.util.List;
import java.util.Map;

import ejercicio3.DatosFestival;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;

public class RangeFestival implements ValuesInRangeData<Integer,SolucionFestival> {


	@Override
	public Integer size() {
		return DatosFestival.getNumAreas() * DatosFestival.getNumTiposEntrada();
	}	
	
	@Override
	public Integer min(Integer i) {
		return 0;
	}
	
	@Override
	public Integer max(Integer i) {
		return DatosFestival.getCuotaMinima(i/DatosFestival.getNumAreas())+1;
	}

	public Double goal(List<Integer> ls) {
		Double res = 0.;
		
		for (int i=0; i < size(); i++) {
			if (ls.get(i)>0) {
				Integer tipoEntrada = i/DatosFestival.getNumAreas();
				Integer area = i%DatosFestival.getNumAreas();
				res += DatosFestival.getCosteAsignacion(tipoEntrada, area) * ls.get(i);
			}
		}
		return res;
	}
	public Double error(List<Integer> ls) {
		Double error1 = 0.;
		Double error2 = 0.;
		Map<Integer, Integer> area_num = Map2.empty();
		Map<Integer, Integer> entrada_num = Map2.empty();
		
		for (int i=0; i < DatosFestival.getNumAreas(); i++) {
			area_num.put(i, 0);
		}
		for (int i=0; i < DatosFestival.getNumTiposEntrada(); i++) {
			entrada_num.put(i, 0);
		}
		
		for (int i=0; i < size(); i++) {
			if (ls.get(i)>0) {
				Integer tipoEntrada = i/DatosFestival.getNumAreas();
				Integer area = i%DatosFestival.getNumAreas();
				area_num.put(area, area_num.get(area)+ls.get(i));
				entrada_num.put(tipoEntrada, entrada_num.get(tipoEntrada)+ls.get(i));
			}
				}

		for (int i=0; i < DatosFestival.getNumAreas(); i++) {
			if (area_num.get(i) > DatosFestival.getAforoMaximoArea(i)) {
				error1 += (area_num.get(i) - DatosFestival.getAforoMaximoArea(i))*100;
			}
		}

		for (int i=0; i < DatosFestival.getNumTiposEntrada(); i++) {
			if (entrada_num.get(i) < DatosFestival.getCuotaMinima(i)) {
				error2 += (- entrada_num.get(i) + DatosFestival.getCuotaMinima(i))*100;
			}
		}
		
		
		return error1*error1 + error2*error2;
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		return -goal(ls) - error(ls);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	public RangeFestival(String linea) {
		DatosFestival.iniDatos(linea);
	}

	@Override
	public SolucionFestival solucion(List<Integer> ls) {
		return SolucionFestival.create(ls);
	}	
	

}


