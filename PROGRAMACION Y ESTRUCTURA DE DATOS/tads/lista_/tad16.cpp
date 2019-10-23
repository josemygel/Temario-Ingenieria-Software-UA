#include <iostream>

using namespace std;

#include "tcalendario.h"
#include "tlistacalendario.h"

int
main(void)
{
  TCalendario a(10,10,2006, (char*) "mensaje 1");
  TCalendario b(11,10,2006, (char*) "mensaje 2");
  TCalendario c(12,10,2006, (char*) "mensaje 3");
  TCalendario d(13,10,2006, (char*) "mensaje 4");
  TCalendario e(14,10,2006, (char*) "mensaje 5");
  TCalendario f(15,10,2006, (char*) "mensaje 6");
  TCalendario g(16,10,2006, (char*) "mensaje 7");
  TCalendario h(17,10,2006, (char*) "mensaje 8");
  TCalendario i(18,10,2006, (char*) "mensaje 9");
  TCalendario j(19,10,2006, (char*) "mensaje 10");
  TCalendario k(20,10,2006, (char*) "mensaje 11");
  TCalendario l(21,10,2006, (char*) "mensaje 12");


  TListaCalendario lista;
  TListaCalendario lista2;
  TListaCalendario lista3;

  lista.Insertar(a);
  lista.Insertar(b);
  lista.Insertar(c);
  lista.Insertar(d);
  lista.Insertar(e);

  lista2.Insertar(f);
  lista2.Insertar(g);
  lista2.Insertar(h);
  lista2.Insertar(i);
  lista2.Insertar(j);

  lista3=lista.SumarSubl(4,2,lista2,0,1);
  
  cout<<lista3<<endl;

}
