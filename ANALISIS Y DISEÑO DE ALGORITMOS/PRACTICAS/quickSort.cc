/*
Análsis y diseño de algoritmos.
Práctica 1: Análisis empírico de la complejidad temporal promedio del algoritmo de ordenación Quicksort central
*/

//--------------------------------------------------------------
// Middle QuickSort
void 
middle_QuickSort(int * v, long left, long right){
long i,j;
int pivot,aux; 
  if (left<right){
	i=left; j=right;
	pivot=v[(i+j)/2];
	do{
		while (v[i]<pivot) i++;
		while (v[j]>pivot) j--;
		if (i<=j) {
		  aux=v[i]; v[i]=v[j]; v[j]=aux;
		  i++; j--;
		}
	}while (i<=j);
	if (left<j)  middle_QuickSort(v,left,j);
	if (i<right) middle_QuickSort(v,i,right);
  }
}

