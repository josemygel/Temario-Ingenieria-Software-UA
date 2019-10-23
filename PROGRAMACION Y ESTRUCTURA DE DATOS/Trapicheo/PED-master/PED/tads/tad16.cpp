#include <iostream>
#include "tabbcalendario.h"
#include "tvectorcalendario.h"
using namespace std;

int main(){
	TABBCalendario a, b;
	
	// iguales dos vacios.
	cout << (a == b) << endl;
	a.Insertar(TCalendario(2, 2, 2002, NULL));
	// iguales uno vacio y el otro con un elemento
	cout << (a == b) << endl;
	a.Insertar(TCalendario(1, 1, 2002, NULL));	
	b.Insertar(TCalendario(1, 1, 2002, NULL));
	b.Insertar(TCalendario(2, 2, 2002, NULL));
	// iguales mismo contenido distinta estructura.
	cout << (a == b) << endl;
	return 0;
}
