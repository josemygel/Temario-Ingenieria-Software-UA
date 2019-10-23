#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 10;

int main()
{
	int dias[kDIAS] = {3, 2, 6, 1, 4, 7, 5, 2, 1, 4};
	TCalendario c(1, 1, 2000, NULL);
	TABBCalendario ab;
	
	if(ab.EsVacio())
		cout << "VACIO" << endl;
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		if(ab.Insertar(c))
			cout << "*SI*";
		else
			cout << "*NO*";
		
	}
	cout << endl;
	if(!ab.EsVacio())
		cout << "NO VACIO" << endl;
	
	cout << ab.Inorden() << endl;
	cout << ab.Preorden() << endl;
	cout << ab.Postorden() << endl,
	cout << "Nodos Hoja: " << ab.NodosHoja() << endl;
	cout << "Altura: " << ab.Altura() << endl;
	cout << "Nodos: " << ab.Nodos() << endl;
	
	
	return 0;
}
