/* Prueba:
    - Comprobar sustitucion mayor izquierda
    - Comprobar sustitucion unico hijo

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
  
  
  //Comprobar sustitucion mayor izquierda
  a.Insertar(c7);
  a.Insertar(c4);
  a.Insertar(c10);
  a.Insertar(c8);
  a.Insertar(c9);
  a.Insertar(c5);
  a.Insertar(c2);
  
  a.Borrar(c7);
  
  cout<<"Preorden="<<a.Preorden()<<endl;
  
  //Comprobar sustitucion unico hijo
  
  TABBCom b;
  
  b.Insertar(c2);
  b.Insertar(c4);
  b.Insertar(c5);
  b.Insertar(c7);
  b.Insertar(c8);
  b.Insertar(c9);
  b.Insertar(c10);
  
  b.Borrar(c2);
  
  cout<<"Preorden="<<b.Preorden()<<endl;


  return 1;
}


