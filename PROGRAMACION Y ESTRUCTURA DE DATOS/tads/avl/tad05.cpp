#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario ca4(4,4,2000,(char *)"fecha"),ca8(8,8,2000,(char *)"fecha"),ca9(9,9,2000,(char *)"fecha"),ca10(10,10,2000,(char *)"fecha"),ca12(12,12,2000,(char *)"fecha"),ca14(14,12,2000,(char *)"fecha");
  TVectorCalendario v1,v2,v3;
  
  a.Insertar(ca8);
  a.Insertar(ca12);
  a.Insertar(ca4);
  a.Insertar(ca9);
  a.Insertar(ca14);
  a.Insertar(ca10);

  v1=a.Inorden();
  v2=a.Postorden();
  v3=a.Preorden();

  cout<<"inorden="<<v1<<endl;
  cout<<"postorden="<<v2<<endl;
  cout<<"preorden="<<v3<<endl;
  cout<<"altura="<<a.Altura()<<endl;
  cout<<"nodos hoja="<<a.NodosHoja()<<endl;
  return 0;
}
