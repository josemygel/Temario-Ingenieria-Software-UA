#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,b;
  TCalendario c4(4,4,2000,(char *)"fecha"),c8(8,8,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha"),c11(11,11,2000,(char *)"fecha"),c12(12,12,2000,(char *)"fecha"),c14(14,12,2000,(char *)"fecha");
  TVectorCalendario v1,v2,v3,v4;
  
  a.Insertar(c8);
  a.Insertar(c12);
  a.Insertar(c4);
  a.Insertar(c9);
  a.Insertar(c14);
  a.Insertar(c10);

  b.Insertar(c8);
  b.Insertar(c12);
  b.Insertar(c4);
  b.Insertar(c9);
  b.Insertar(c14);
  b.Insertar(c11);
 
  if (a==b)
    cout<<"iguales"<<endl;
  else
    cout<<"diferentes"<<endl;

  if (a!=b)
    cout<<"diferentes"<<endl;
  else
    cout<<"iguales"<<endl;
  return 0;
}
