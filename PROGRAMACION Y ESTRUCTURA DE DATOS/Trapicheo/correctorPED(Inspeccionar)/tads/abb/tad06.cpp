/* Prueba:
     Borrar
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	
	TComplejo x(1), y(2), z(3), a(4), b(5), c(6), d, e;

	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (x);
	arbol.Insertar (y);
	arbol.Insertar (z);

	cout << arbol.Inorden() << endl;
	cout << arbol.Postorden() << endl;
	cout << arbol.Preorden() << endl;
	cout << arbol.Niveles() << endl;
	
	if(arbol.Borrar(x))
		cout << "Borrado " << x << endl;
	else
		cout << "Error borrando " << x << endl;

	cout << arbol.Inorden() << endl;
	cout << arbol.Postorden() << endl;
	cout << arbol.Preorden() << endl;
	cout << arbol.Niveles() << endl;

	return 0;
}
