package ejercicio3;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ejemplo1.SolucionMulticonjunto;
import ejercicio2.DatosCursos;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;
import us.lsi.common.Set2;

public class RangeFestivalAG implements ValuesInRangeData<Integer,SolucionFestivalAG>{
	
	public RangeFestivalAG(String linea) {
		DatosFestival.iniDatos(linea);
	}

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
		Double res= 0. ;
		for (int i = 0; i<size(); i++) {
			Integer tipoEntrada = i /DatosFestival.getNumAreas();
			Integer area =  i % DatosFestival.getNumAreas();
			res += DatosFestival.getCosteAsignacion(tipoEntrada, area) * ls.get(i);
		}
		return res;
	}
	
	public Double penalizacion(List<Integer> ls) {
		Double error1 = 0.0;
		Double error2 = 0.0;
		
		Map<Integer, Integer> udsPorEntrada = Map2.empty();
		Map<Integer, Integer> udsPorArea = Map2.empty();
		
		for (int i =0; i < DatosFestival.getNumTiposEntrada(); i++) {
			udsPorEntrada.put(i, 0);
		}
		
		for (int i =0; i < DatosFestival.getNumAreas(); i++) {
			udsPorArea.put(i, 0);
		}
		for (int i =0; i <size(); i++) {
			Integer tipoEntrada = i /DatosFestival.getNumAreas();
			Integer area =  i % DatosFestival.getNumAreas();
			udsPorEntrada.put(tipoEntrada,  udsPorEntrada.get(tipoEntrada) + ls.get(i));
			udsPorArea.put(area,  udsPorArea.get(area) + ls.get(i));
		}
		for (int i =0; i < DatosFestival.getNumTiposEntrada(); i++) {
			if (udsPorEntrada.get(i) < DatosFestival.getCuotaMinima(i)) {
				error1 +=( DatosFestival.getCuotaMinima(i) -udsPorEntrada.get(i)) *100;
			}
		}
		
		for (int i =0; i < DatosFestival.getNumAreas(); i++) {
			if (udsPorArea.get(i) > DatosFestival.getAforoMaximoArea(i)) {
				error2 +=(udsPorArea.get(i) - DatosFestival.getAforoMaximoArea(i))*100;
			}
		}
		
		return error1*error1 + error2*error2;
	}
	
	public Double fitnessFunction(List<Integer> ls) {
		return -goal(ls) - 100 * penalizacion(ls);
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public SolucionFestivalAG solucion(List<Integer> value) {
		return SolucionFestivalAG.create(value);
	}



}
