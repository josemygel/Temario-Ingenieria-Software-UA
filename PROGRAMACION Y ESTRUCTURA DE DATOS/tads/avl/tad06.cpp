#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario ca4(4,4,2000,(char *)"fecha"),ca6(6,6,2000,(char *)"fecha"),ca8(8,8,2000,(char *)"fecha"),ca9(9,9,2000,(char *)"fecha"),ca10(10,10,2000,(char *)"fecha"),ca11(11,11,2000,(char *)"fecha"),ca12(12,12,2000,(char *)"fecha"),ca14(14,12,2000,(char *)"fecha"),ca16(16,12,2000,(char *)"fecha");
  
  
  a.Insertar(ca8);
  a.Insertar(ca12);
  a.Insertar(ca4);
  a.Insertar(ca9);
  a.Insertar(ca14);
  a.Insertar(ca16);
  a.Insertar(ca11);
  a.Insertar(ca6);
  a.Insertar(ca10);
  cout<<"inorden="<<a.Inorden()<<endl;
  cout<<"postorden="<<a.Postorden()<<endl;
  cout<<"preorden="<<a.Preorden()<<endl;
  cout<<"altura="<<a.Altura()<<endl;
  cout<<"nodos hoja="<<a.NodosHoja()<<endl;
  cout<<"raiz="<<a.Raiz()<<endl;
}
