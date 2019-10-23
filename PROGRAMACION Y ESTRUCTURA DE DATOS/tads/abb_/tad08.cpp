#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a;
  TCalendario c1(1, 1,2000,(char *)"fecha"),c2(2,2,2000,(char *)"fecha"),c3(3,3,2000,(char *)"fecha");

  a.Insertar(c1);
  a.Insertar(c2);
  a.Insertar(c3);
  
  TABBCalendario b(a);
  
  cout<<"Inorden="<<b.Inorden()<<endl;

  TABBCalendario c,d(c);
  
  cout<<"Inorden="<<d.Inorden()<<endl;
  
  return 0;
}
