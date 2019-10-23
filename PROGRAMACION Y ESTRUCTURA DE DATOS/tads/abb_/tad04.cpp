#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a;
  TCalendario c1(1, 1,2000,(char *)"fecha"), c2(2, 2,2000,(char *)"fecha"), c3(3, 3,2000,(char *)"fecha");
  TCalendario c4(4,4,2000,(char *)"fecha");

  a.Insertar(c1);
  a.Insertar(c2);
  a.Insertar(c3);

  if (a.Buscar(c3))
    cout<<"Esta" <<endl;
  else
    cout<<"No esta"<<endl;
    
  if (a.Buscar(c4))
    cout<<"Esta"<<endl;
  else
    cout<<"No esta"<<endl;

  return 0;
}
