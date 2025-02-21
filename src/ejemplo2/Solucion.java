package ejemplo2;

//Se tiene un conjunto U de m elementos de tipo entero ej, j ∈ [0,m), (llamado el
//universo) y un conjunto S de n conjuntos si, i ∈ [0,n), cuya unión es igual al universo.
//Cada conjunto si tiene un peso wi ≥ 0 asociado. El problema de cobertura de conjuntos
//consiste en identificar los conjuntos de S cuya unión es igual al universo U, de forma
//que se minimice la suma de los pesos de los conjuntos elegidos

//Datos de entrada:
// m - tamano del universo, integer
// n - integer, numero de conjuntos Si
//Wi - integer, peso de conjunto i, i de 0 a n-1
//Cij - binario, si conjunto i tiene elemento ej, i de 0 a n-1, j de 0 a m-1

//Variables
//Xi - binario, si conjunto tiene q estar incluido en solucion o no

//Funcion objetiva
//min (sumatorio de 0 a n-1) Wi * Xi

//Restricciones
// sumatorio de 0 a n-1 (Xi *Cij) >=1, j de 0 a m-1

//bin Xi, i de 0 a n-1
public class Solucion {

}
