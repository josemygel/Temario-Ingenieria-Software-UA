/* Prueba:
    - BORRADO CON 2 HIJOS 
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

  TComplejo c1(20, 1);
  TComplejo c2(10, 1);
  TComplejo c3(24, 1);
  TComplejo c4(8, 1);
  TComplejo c5(12, 1);
  TComplejo c6(26, 1);
  TComplejo c7(28, 1);

  TComplejo c8(88, 1);

  a.Insertar(c1);
  a.Insertar(c2);
  a.Insertar(c3);
  a.Insertar(c4);
  a.Insertar(c5);
  a.Insertar(c6);
  a.Insertar(c7);

  if(!a.Borrar(c1))
  	cout << "Error NO BORRADO" << endl;

  if(!a.Borrar(c5))
  	cout << "Error NO BORRADO" << endl;

  if(!a.Borrar(c8))
  	cout << "Error NO BORRADO" << endl;

  if(!a.Borrar(c2))
  	cout << "Error NO BORRADO" << endl;

  if(!a.Borrar(c4))
  	cout << "Error NO BORRADO" << endl;

  cout << a.Inorden() << endl;


  return 1;
}
