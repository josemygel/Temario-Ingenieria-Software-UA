#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 10;

int main()
{
	TCalendario x(1, 1, 2000, NULL);
	TABBCalendario a;
	a.Insertar(x);
	a.~TABBCalendario();
	
	if(a.EsVacio())
		cout << "VACIO" << endl;
	
	TABBCalendario b(a);
	TABBCalendario c;
	c = a;

	if(b.EsVacio())
		cout << "VACIO" << endl;

	if(c.EsVacio())
		cout << "VACIO" << endl;

	cout << "Altura: " << b.Altura() << endl;
	cout << "Nodos hoja: " << b.NodosHoja() << endl;
	cout << "Nodos: " << c.Nodos() << endl;
	if(a == b)
		cout << "VACIOS IGUALES" << endl;
	a.~TABBCalendario();
	c.~TABBCalendario();
	b.~TABBCalendario();
	if(!a.Buscar(x))
		cout << "NO ENCONTRADO" << endl;
	if(!a.Borrar(x))
		cout << "NO BORRADO" << endl; 	
	return 0;
}
