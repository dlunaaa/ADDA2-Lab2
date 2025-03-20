package ejercicio1;

import java.util.List;
import java.util.Map;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Map2;


public class RangeAlmacenesAG implements ValuesInRangeData<Integer,SolucionAlmacenesAG> {

	public RangeAlmacenesAG(String linea) {
		DatosAlmacenes.iniDatos(linea);
	}

	@Override
	public Integer size() {
		return DatosAlmacenes.getNumProductos();
	}	
	
	@Override
	public Integer min(Integer i) {
		return -1;
	}
	
	@Override
	public Integer max(Integer i) {
		return DatosAlmacenes.getNumAlmacenes() -1;
	}

	@Override
	public SolucionAlmacenesAG solucion(List<Integer> ls) {
		return SolucionAlmacenesAG.of_Range(ls);
	}	
	public Double goal(List<Integer> ls) {
		double productosAlmacenados = 0;
		for (int i=0; i < size(); i++) {
			if (ls.get(i) != -1) {
				productosAlmacenados ++;
			}
		}
		return productosAlmacenados;
		
	}
	public Double error(List<Integer> ls) {
		double errorCapacidad = 0;
		double errorIncompatibilidad = 0;
		Map<Integer, Double> almacen_kg = Map2.empty();
		
		for (int i=0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
			almacen_kg.put(i,  0.);
		}
			
		for (int i=0; i <size(); i++) {
			almacen_kg.put(ls.get(i), almacen_kg.get(ls.get(i))+DatosAlmacenes.getMetrosCubicosProducto(i));
			for (int j=0; j <size(); j++) {
				if (ls.get(i) == ls.get(j) && DatosAlmacenes.sonIncompatibles(i, j) == true) {
					errorIncompatibilidad += 1000;
				}
			}
		}
		for (int i=0; i < DatosAlmacenes.getNumAlmacenes(); i++) {
			if (almacen_kg.get(i) > DatosAlmacenes.getMetrosCubicosAlmacen(i)) {
				errorCapacidad += (almacen_kg.get(i) - DatosAlmacenes.getMetrosCubicosAlmacen(i))*100;
			}
		}
		
		return errorIncompatibilidad*errorIncompatibilidad + errorCapacidad*errorCapacidad;

		
	}
	
	@Override
	public Double fitnessFunction(List<Integer> ls) {
		return goal(ls) -error(ls);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

}
