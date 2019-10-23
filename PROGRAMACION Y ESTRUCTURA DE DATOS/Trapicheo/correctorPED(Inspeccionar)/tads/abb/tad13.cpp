/* Prueba:
     comparacion
*/
#include <iostream>

using namespace std;

#include "tabbcom.h"

int main(void) {
	TComplejo a(17), b(8), c(26), d(4), e(11), f(31), g(27), h(1);
	TABBCom arbol;
	
	arbol.Insertar (a);
	arbol.Insertar (b);
	arbol.Insertar (c);
	arbol.Insertar (d);
	arbol.Insertar (e);
	arbol.Insertar (f);
	arbol.Insertar (g);
	
	cout << arbol << endl;

	TABBCom asignado, copiado(arbol);
	asignado = arbol;

	cout << asignado << endl;
	cout << copiado << endl;

	if(asignado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;

	if(copiado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;

	if(asignado.Insertar(h))
		cout << "Borrar " << h << endl;
	else
		cout << "Error Borrar " << h << endl;
		
	if(copiado.Borrar(a))
		cout << "Borrar " << a << endl;
	else
		cout << "Error Borrar " << a << endl;

	cout << arbol << endl;
	cout << asignado << endl;
	cout << copiado << endl;

	if(asignado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;

	if(copiado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;
		
	arbol = asignado;

	if(asignado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;

	if(copiado == arbol)
		cout << "Iguales"<< endl;
	else
		cout << "Distintos" << endl;
		
	cout << arbol << endl;
	cout << asignado << endl;
	cout << copiado << endl;

	return 0;
}
