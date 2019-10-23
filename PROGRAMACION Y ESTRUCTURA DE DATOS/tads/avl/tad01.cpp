#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario ca1(1,1,2000,(char *)"fecha");
  TCalendario ca2(2,2,2000,(char *)"fecha");
  TCalendario ca3(3,3,2000,(char *)"fecha");

  a.Insertar(ca1);
  cout<<"Nodos="<<a.Nodos()<<endl;
  cout<<"NodosHoja="<<a.NodosHoja()<<endl;
  cout << "Altura=" << a.Altura() << endl;
  
  a.Insertar(ca2);
  cout<<"Nodos="<<a.Nodos()<<endl;
  cout<<"NodosHoja="<<a.NodosHoja()<<endl;
  cout << "Altura=" << a.Altura() << endl;

  a.Insertar(ca3);
  cout<<"Nodos="<<a.Nodos()<<endl;
  cout<<"NodosHoja="<<a.NodosHoja()<<endl;
  cout << "Altura=" << a.Altura() << endl;
  return 0;
}
