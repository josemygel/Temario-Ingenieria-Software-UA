/* Prueba:
     Insertar
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	TComplejo a(17), b(8), c(26), d(4), e(11), f(31), g(27);
	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (c);
	arbol.Insertar (d);
	arbol.Insertar (e);
	arbol.Insertar (f);
	arbol.Insertar (g);
	
	cout << arbol << endl;

	TComplejo h(9);
	if(arbol.Insertar(h))
		cout << "Insertar " << h << endl;
	else
		cout << "Error Insertar " << h << endl;

	cout << arbol << endl;
		
	return 0;
}
