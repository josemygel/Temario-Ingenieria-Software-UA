#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,b,c;
  TCalendario c3(3,3,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha"),c12(12,12,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha"),c1(1,1,2000,(char *)"fecha");
  TVectorCalendario v1,v2,v3,v4;
  
  a.Insertar(c10);
  a.Insertar(c12);
  a.Insertar(c9);
  a.Insertar(c5);
  a.Insertar(c3);

  b=a;
  c=b;
  c.Insertar(c1);
  
  if (a!=c)
   cout<<"distintos"<<endl;
  else
   cout<<"iguales"<<endl;

  if (b!=(c=a))
    cout<<"distintos"<<endl;
  else
   cout<<"iguales"<<endl;
  return 0;

}
