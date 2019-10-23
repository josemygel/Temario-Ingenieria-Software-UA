/* Prueba:
    - Comprobar borrar hoja
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

  TComplejo c7(7, 0);
  TComplejo c4(4, 0);
  TComplejo c10(10, 0);
  TComplejo c2(2, 0);
  TComplejo c5(5, 0);
  TComplejo c8(8, 0);
  TComplejo c9(9, 0);
  
  a.Insertar(c2);
  a.Insertar(c4);
  a.Insertar(c5);
  a.Insertar(c7);
  a.Insertar(c8);
  a.Insertar(c9);
  a.Insertar(c10);
  
  a.Borrar(c10);
  
  cout<<"Preorden="<<a.Preorden()<<endl;

  return 1;
}


