/* Prueba:
     arbol vacio
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int
main(void)
{
	TABBCom arbol;
	
	cout << arbol.Inorden() << endl;
	cout << arbol.Preorden() << endl;
	cout << arbol.Postorden() << endl;
	cout << arbol.Niveles() << endl;
	cout << arbol << endl;

	cout << arbol.Raiz() << endl;
	
	return 0;
}
