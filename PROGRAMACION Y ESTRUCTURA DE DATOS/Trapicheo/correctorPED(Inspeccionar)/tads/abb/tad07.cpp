/* Prueba:
     Borrado
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	
	TComplejo a(1), b(2), c(3), d(4), e(5), f(6), g(7), h(8), i(9), j(10), k(11);

	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (c);
	arbol.Insertar (d);
	arbol.Insertar (e);
	arbol.Insertar (f);
	arbol.Insertar (g);
	arbol.Insertar (h);
	arbol.Insertar (i);
	arbol.Insertar (j);

	cout << arbol << endl;
	
	if(arbol.Borrar(j))
		cout << "Borrado " << j << endl;
	else
		cout << "Error borrando " << j << endl;

	cout << arbol << endl;

	if(arbol.Borrar(e))
		cout << "Borrado " << e << endl;
	else
		cout << "Error borrando " << e << endl;

	cout << arbol << endl;
	
	if(arbol.Borrar(a))
		cout << "Borrado " << a << endl;
	else
		cout << "Error borrando " << a << endl;

	cout << arbol << endl;
		
	if(arbol.Borrar(k))
		cout << "Borrado " << k << endl;
	else
		cout << "Error borrando " << k << endl;

	cout << arbol << endl;
	
	return 0;
}
