/* Prueba:
     Altura, nodos, nodos hoja, raiz, profundidad
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int
main(void)
{
	TComplejo x(1), y(2), z(3), a(4), b(5), c(6), d, e;

	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (x);
	arbol.Insertar (y);
	arbol.Insertar (z);

	cout << "La altura del arbol a es " << arbol.Altura() << endl;
	cout << "El arbol tiene " << arbol.Nodos() << " nodos" << endl;
	cout << "El arbol tiene " << arbol.NodosHoja() << " nodos hoja" << endl;
	cout << "La raiz del arbol es a" << arbol.Raiz() << endl;

	cout << arbol << endl;

	return 0;
}
