#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 3;

int main()
{
	int dias[kDIAS] = {2, 3, 1};
	TCalendario c(1, 1, 2000, NULL);
	TABBCalendario ab;
	
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		ab.Insertar(c);
	}
	
	cout << ab.Inorden() << endl;
	cout << ab.Preorden() << endl;
	cout << ab.Postorden() << endl,
	cout << "Nodos: " << ab.Nodos() << endl;
	cout << "Altura: " << ab.Altura() << endl;
	
	return 0;
}
