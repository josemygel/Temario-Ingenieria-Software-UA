/* Prueba:
     INSERTAR
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int
main(void)
{
	TComplejo x(1), y(2), z(3), a(4), b(5), c(6);

	TABBCom arbol;
	
	if(arbol.Insertar (a))
		cout << "Insertado " << a << endl;
	else
		cout << "Error insertando " << a << endl;

	if(arbol.Insertar (b))
		cout << "Insertado " << b << endl;
	else
		cout << "Error insertando " << b << endl;

	if(arbol.Insertar (x))
		cout << "Insertado " << x << endl;
	else
		cout << "Error insertando " << x << endl;

	if(arbol.Insertar (y))
		cout << "Insertado " << y << endl;
	else
		cout << "Error insertando " << y << endl;

	if(arbol.Insertar (z))
		cout << "Insertado " << z << endl;
	else
		cout << "Error insertando " << z << endl;

	cout << arbol;

	return 0;
}
