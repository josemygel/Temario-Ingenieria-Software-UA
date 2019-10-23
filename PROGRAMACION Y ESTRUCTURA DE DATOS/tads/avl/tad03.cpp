#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,b(a),c(a);
  TCalendario ca1(1,1,2000,(char *)"fecha"),ca2(2,2,2000,(char *)"fecha"),ca3(3,3,2000,(char *)"fecha");
  TVectorCalendario v1,v2,v3;
  
  if (a==b)
    cout<<"Iguales"<<endl;
  else
    cout<<"Distintos"<<endl;
  v1=a.Inorden();
  v2=b.Preorden();
  v3=c.Postorden();
  cout<<"a="<<v1<<" b="<<v2<<" c="<<v3<<endl;
  return 0;
}
