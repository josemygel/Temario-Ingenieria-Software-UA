#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  //Comprobar borrar hoja
  
  TABBCalendario a,b;
    TCalendario c7(7, 7,2000,(char *)"fecha"),c4(4,4,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha");
  TCalendario c2(2,2,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c8(8,8,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha");
  
  a.Insertar(c2);
  a.Insertar(c4);
  a.Insertar(c5);
  a.Insertar(c7);
  a.Insertar(c8);
  a.Insertar(c9);
  a.Insertar(c10);
  
  b=a;

  cout<<"Preorden="<<b.Preorden()<<endl;
  
  b.~TABBCalendario();
  
  cout<<"Preorden="<<a.Preorden()<<endl;
  
  return 0;
}
