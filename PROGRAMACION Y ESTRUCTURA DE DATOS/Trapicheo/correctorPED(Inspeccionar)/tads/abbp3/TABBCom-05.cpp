/* Prueba:
    - EsVacio() 
*/
#include <iostream>
#include "tabbcom.h"
#include "tcomplejo.h"
#include "tvectorcom.h"
using namespace std;

int
main(void)
{
  TABBCom a ;

  if( a.EsVacio() )
        cout << "VACIO" << endl;
  else
	cout << "INCORRECTO EsVacio()" << endl;

  TComplejo c1(1, 1);

  a.Insertar(c1);
  if( a.EsVacio() )
        cout << "INCORRECTO EsVacio()" << endl;
  else
	cout << "NO VACIO" << endl;

  a.Borrar(c1);
  if( a.EsVacio() )
        cout << "VACIO" << endl;
  else
	cout << "INCORRECTO Borrar()" << endl;


  return 1;
}
