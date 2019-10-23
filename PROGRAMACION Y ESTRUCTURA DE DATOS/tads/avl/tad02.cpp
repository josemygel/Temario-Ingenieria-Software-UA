#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,b,c;
  
  TCalendario ca1(1,1,2000,(char *)"fecha"),ca2(2,2,2000,(char *)"fecha"),ca3(3,3,2000,(char *)"fecha");
  
  if (a==b)
    cout<<"Iguales"<<endl;
  else
    cout<<"Distintos"<<endl;
  
  cout<<"a="<<a.Inorden()<<" b="<<b.Preorden()<<" c="<<c.Postorden()<<endl;
  return 0;
}
