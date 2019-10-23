#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a;
  TCalendario c7(7, 7,2000,(char *)"fecha"),c4(4,4,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha");
  TCalendario c2(2,2,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c8(8,8,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha");
  
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
  
  TABBCalendario b;
  
  b.Insertar(c2);
  b.Insertar(c4);
  b.Insertar(c5);
  b.Insertar(c7);
  b.Insertar(c8);
  b.Insertar(c9);
  b.Insertar(c10);
  
  b.Borrar(c2);
  
  cout<<"Preorden="<<b.Preorden()<<endl;

  return 0;
}
