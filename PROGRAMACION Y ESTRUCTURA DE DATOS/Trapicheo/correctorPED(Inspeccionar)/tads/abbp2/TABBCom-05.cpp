/* Prueba:
operador == de árboles

Orden de inserción: 2, 4, 8 y 10
Orden de inserción: 10, 8, 4 y 2

*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{

  TABBCom a,b;

  TComplejo c2(2, 2);
  TComplejo c4(4, 4);
  TComplejo c8(8, 8);
  TComplejo c10(10, 10);

  a.Insertar(c2);
  a.Insertar(c4);
  a.Insertar(c8);
  a.Insertar(c10);

  b.Insertar(c10);
  b.Insertar(c8);
  b.Insertar(c4);
  b.Insertar(c2);

  if (a==b)

	cout << "IGUALES" << endl;

  else 

	cout << "NO IGUALES" << endl;


  return 1;
}


