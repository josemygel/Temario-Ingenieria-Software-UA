#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tabbcalendario.h"

using namespace std;

int main()
{
	TABBCalendario ab;

	cout << ab.Inorden() << endl;
	cout << ab.Preorden() << endl;
	cout << ab.Postorden() << endl,
	cout << "Nodos: " << ab.Nodos() << endl;
	cout << "Altura: " << ab.Altura() << endl;

	return 0;
}
