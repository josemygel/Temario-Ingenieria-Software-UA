/* Prueba:
     Borrar
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	TComplejo a(30), b(20), c(40), d(10), e(25), f(35), g(45), h(5), i(32), j(42), k(50);
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
	arbol.Insertar (k);
	
	cout << arbol << endl;

	if(arbol.Borrar(i))
		cout << "Borrar " << i << endl;
	else
		cout << "Error Borrar " << i << endl;

	cout << arbol << endl;

	if(arbol.Borrar(c))
		cout << "Borrar " << c << endl;
	else
		cout << "Error Borrar " << c << endl;

	cout << arbol << endl;

	if(arbol.Borrar(a))
		cout << "Borrar " << a << endl;
	else
		cout << "Error Borrar " << a << endl;

	cout << arbol << endl;

	if(arbol.Borrar(d))
		cout << "Borrar " << d << endl;
	else
		cout << "Error Borrar " << d << endl;

	cout << arbol << endl;
	
	if(arbol.Borrar(h))
		cout << "Borrar " << h << endl;
	else
		cout << "Error Borrar " << h << endl;

	cout << arbol << endl;
	
	return 0;
}
