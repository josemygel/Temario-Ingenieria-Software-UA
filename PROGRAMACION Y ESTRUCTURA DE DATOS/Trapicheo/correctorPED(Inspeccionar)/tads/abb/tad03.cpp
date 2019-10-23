/* Prueba:
     Insertados repetidos, buscar
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int
main(void)
{
	TComplejo x(40), y(20), z(35), a(24), b(25), c(26), d, e;

	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (c);
	arbol.Insertar (x);
	arbol.Insertar (y);

	if(arbol.Buscar (z))
		cout << "Encontrado " << z << endl;
	else
		cout << "No encontrado " << z << endl;

	if(arbol.Buscar (c))
		cout << "Encontrado " << c << endl;
	else
		cout << "No encontrado " << c << endl;

	if(arbol.Insertar (a))
		cout << "Insertado " << a << endl;
	else
		cout << "Error insertando " << a << endl;
		
	if(arbol.Insertar (d))
		cout << "Insertado " << d << endl;
	else
		cout << "Error insertando " << d << endl;

	if(arbol.Insertar (e))
		cout << "Insertado " << e << endl;
	else
		cout << "Error insertando " << e << endl;

	if(arbol.Buscar (d))
		cout << "Encontrado " << d << endl;
	else
		cout << "No encontrado " << d << endl;

	if(arbol.Buscar (e))
		cout << "Encontrado " << e << endl;
	else
		cout << "No encontrado " << e << endl;

	return 0;
}
