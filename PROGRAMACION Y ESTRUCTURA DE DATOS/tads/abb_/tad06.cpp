#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a;
  TCalendario c1(1, 1,2000,(char *)"fecha"), c2(2, 2,2000,(char *)"fecha"), c3(3, 3,2000,(char *)"fecha");
  TCalendario c4(4,4,2000,(char *)"fecha");

  cout<<"Altura="<<a.Altura()<<endl;

  a.Insertar(c1);
  
  cout<<"Altura="<<a.Altura()<<endl;
  
  a.Insertar(c2);
  
  cout<<"Altura="<<a.Altura()<<endl;
  
  a.Insertar(c3);
  
  cout<<"Altura="<<a.Altura()<<endl;
  
  a.Insertar(c4);
  
  cout<<"Altura="<<a.Altura()<<endl;
  
  return 0;
}
