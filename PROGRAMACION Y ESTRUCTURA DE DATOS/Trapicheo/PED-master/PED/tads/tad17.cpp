#include "tabbcalendario.h"
#include "tvectorcalendario.h"
#include <iostream>
using namespace std;

int main(){
	TABBCalendario ab;
	
	// niveles de un arbol vacio.
	cout << ab.Niveles() << endl;
	
	ab.Insertar(TCalendario(1, 1, 2020, NULL));
	// niveles de un solo nodo
	cout << ab.Niveles() << endl;
		

	// niveles de mas de un nodo.
	ab.Insertar(TCalendario(1, 1, 2000, NULL));
	ab.Insertar(TCalendario(1, 1, 2010, NULL));
	ab.Insertar(TCalendario(1, 1, 2012, NULL));
	ab.Insertar(TCalendario(1, 1, 2030, NULL));
	ab.Insertar(TCalendario(1, 1, 2040, NULL));
	ab.Insertar(TCalendario(1, 1, 2035, NULL));

/*
				2020
		2000           2030
		    2010          2040
			  2012       2035
*/

	cout << "Niveles: " << ab.Niveles() << endl;
	cout << "Innorden: " << ab.Inorden() << endl;

	return 0;
}
