#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"
using namespace std;

const int kDIAS = 7;

int main()
{
	int dias[kDIAS] = {3, 2, 6, 1, 4, 7, 5};
	TCalendario c(1, 1, 2000, NULL);
	TABBCalendario ab;
	
	for(int i = 0; i < kDIAS; i++)
	{
		c.ModFecha(dias[i], c.Mes(), c.Anyo());
		ab.Insertar(c);	
	}
	
	TCalendario *x = new TCalendario (3, 2, 2000, NULL);
	if(!ab.Buscar(*x))
		cout << "NO ENCONTRADO" << endl;
	delete x;
	
	x = new TCalendario (7, 1, 2000, NULL);
	if(ab.Buscar(*x))
		cout << "ENCONTRADO EN HOJA" << endl;
	delete x;
	
	x = new TCalendario (2, 1, 2000, NULL);
	if(ab.Buscar(*x))
		cout << "ENCONTRADO SOLO HIJO IZQUIERDO" << endl;
	delete x;
	
	x = new TCalendario (4, 1, 2000, NULL);
	if(ab.Buscar(*x))
		cout << "ENCONTRADO SOLO HIJO ENRECHO" << endl;
	delete x;
	
	x = new TCalendario (3, 1, 2000, NULL);
	if(ab.Buscar(*x))
		cout << "ENCONTRADO CON DOS HIJOS" << endl;
	delete x;
	
	return 0;
}
