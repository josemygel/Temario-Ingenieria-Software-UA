/* Prueba:
     Borrar
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	TComplejo a(17), b(8), c(26), d(4), e(11), f(31);
	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (c);
	arbol.Insertar (d);
	arbol.Insertar (e);
	arbol.Insertar (f);
	
	cout << arbol << endl;

	if(arbol.Borrar(a))
		cout << "Borrar " << a << endl;
	else
		cout << "Error Borrar " << a << endl;

	cout << arbol << endl;
		
	return 0;
}
