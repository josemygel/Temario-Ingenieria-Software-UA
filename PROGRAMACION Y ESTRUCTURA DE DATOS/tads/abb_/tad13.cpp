#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  //Comprobar borrar hoja
  
  TABBCalendario b;
  TCalendario c7(7, 7,2000,(char *)"fecha"),c4(4,4,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha");
  TCalendario c2(2,2,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c8(8,8,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha");  
  
  b.Insertar(c2);
  b.Insertar(c4);
  b.Insertar(c5);
  b.Insertar(c7);
  b.Insertar(c8);
  b.Insertar(c9);
  b.Insertar(c10);
  
  b.Borrar(c10);
  
  cout<<"Preorden="<<b.Preorden()<<endl;

  return 0;
}
