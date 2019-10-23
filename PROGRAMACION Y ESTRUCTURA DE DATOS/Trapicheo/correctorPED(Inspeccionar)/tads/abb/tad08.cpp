/* Prueba:
     Borrado
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	TComplejo a(20), b(10), c(30), d(5), e(17), f(22), g(40), h(3), i(25), j(35), k(50), l(24), m(26), n(38), o(37), p(39), q(15), r(12);
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
	arbol.Insertar (q);
	arbol.Insertar (r);
	
	cout << arbol << endl;
	
	if(arbol.Borrar(c))
		cout << "Borrado " << c << endl;
	else
		cout << "Error borrando " << c << endl;

	cout << arbol << endl;
		
	if(arbol.Borrar(a))
		cout << "Borrado " << a << endl;
	else
		cout << "Error borrando " << a << endl;

	cout << arbol << endl;

	return 0;
}
