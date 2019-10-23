/* Prueba:
    - CONSTR. COPIA , "=" ; posible copia de punteros 
*/

#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{

  TABBCom a;

  TComplejo c1(1), c2(2, 3);

  a.Insertar(c1);

  TABBCom b(a), c;
  c=b;

  a.Insertar(c2);
  if( a.Inorden() == b.Inorden() )
        cout << "MAL! SE HAN COPIADO PUNTEROS" << endl;
  else
	cout << "CORRECTO CONSTRUCTOR DE COPIA" << endl;


  b.Insertar(c2);
  if( b.Inorden() == c.Inorden() )
        cout << "MAL! SE HAN COPIADO PUNTEROS" << endl;
  else
	cout << "CORRECTA ASIGNACION" << endl;

  return 1;
}
