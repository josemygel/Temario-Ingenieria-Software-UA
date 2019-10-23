/* Prueba:
	Inorden, postorden, preorden y niveles
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int
main(void)
{
	TComplejo a(20), b(10), c(30), d(5), e(15), f(22), g(40), h(3), i(25), j(35), k(50), l(24), m(26), n(38), o(37), p(39);
	TComplejo z;
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
	arbol.Insertar (l);
	arbol.Insertar (m);
	arbol.Insertar (n);
	arbol.Insertar (o);
	arbol.Insertar (p);
	
	cout << arbol << endl;
		
	cout << arbol.Inorden() << endl;
	cout << arbol.Postorden() << endl;
	cout << arbol.Preorden() << endl;
	cout << arbol.Niveles() << endl;
	
	return 0;
}
