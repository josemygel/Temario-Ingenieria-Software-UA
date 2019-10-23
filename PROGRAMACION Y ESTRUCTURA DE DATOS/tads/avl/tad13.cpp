#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario c3(3,3,2000,(char *)"fecha"),c5(5,5,2000,(char *)"fecha"),c9(9,9,2000,(char *)"fecha"),c12(12,12,2000,(char *)"fecha"),c10(10,10,2000,(char *)"fecha");
  TVectorCalendario v1,v2,v3,v4;
  
  if (a.EsVacio())
    cout<<"es vacio"<<endl; 
    
  a.Insertar(c10);
  a.Insertar(c12);
  a.Insertar(c9);

  v1=a.Inorden();
  v2=a.Postorden();
  v3=a.Preorden();

  if (!a.EsVacio())
  {
    cout<<"inorden="<<v1<<endl;
    cout<<"postorden="<<v2<<endl;
    cout<<"preorden="<<v3<<endl;
  }
  
  a.~TAVLCalendario();

  if (a.EsVacio())
    cout<<"inorden="<<a.Inorden()<<endl;

  return 0;
}
