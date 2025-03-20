package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicio1.DatosAlmacenes.Producto;


public class SolucionAlmacenesAG {
	
	public static SolucionAlmacenesAG of_Range(List<Integer> ls) {
		return new SolucionAlmacenesAG(ls);
	}

	private Map<Producto, Integer> solucion;

	private SolucionAlmacenesAG() {
		solucion = new HashMap<Producto, Integer>();
	}
	private SolucionAlmacenesAG(List<Integer> ls) {
		System.out.println(ls);
		
		solucion = new HashMap<>();
        
        for (int i = 0; i < ls.size(); i++) {
            int almacen = ls.get(i);
            Producto producto = DatosAlmacenes.getProducto(i);
            
            if (almacen == -1) {
                solucion.put(producto, -1);
            } else {
                solucion.put(producto, almacen);
            }
        }
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Distribución de productos:\n");
		for (Map.Entry<Producto, Integer> entry : solucion.entrySet()) {
			sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
		}
		return sb.toString();
	}
}

