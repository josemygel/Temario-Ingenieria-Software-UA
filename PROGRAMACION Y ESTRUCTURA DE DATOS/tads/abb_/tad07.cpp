#include <iostream>
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a,b,c;
  TCalendario c1(1, 1,2000,(char *)"fecha"),c2;
  int i;
  
  for (i=1; i<=300; i++)
  {
      c1.ModFecha(1,1,2000+i);
      a.Insertar(c1);
      b.Insertar(c1);
      c.Insertar(c1);
  }
      
  cout<<"Altura="<<a.Altura()<<endl;
  cout<<"Altura="<<b.Altura()<<endl;
  cout<<"Altura="<<c.Altura()<<endl;
  
  cout<<"Nodos="<<a.Nodos()<<endl;
  cout<<"NodosHoja="<<a.NodosHoja()<<endl; 
  cout<<"Nodos="<<b.Nodos()<<endl;
  cout<<"NodosHoja="<<c.NodosHoja()<<endl; 
  cout<<"Nodos="<<c.Nodos()<<endl;
  cout<<"NodosHoja="<<c.NodosHoja()<<endl; 
  

  return 0;
}
