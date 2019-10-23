/************************************************************
** INORDEN, PREORDEN Y POSTORDEN
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario d1(1,1,2011,(char *)"uno");
  TCalendario d2(2,2,2011,(char *)"dos");
  TCalendario d3(3,3,2011,(char *)"tres");
  TCalendario d4(4,4,2011,(char *)"cuatro");

  TVectorCalendario v;

  a.Insertar(d4);
  a.Insertar(d3);
  a.Insertar(d2);
  a.Insertar(d1);

  v = a.Inorden();
  cout << "Inorden: " << v << endl;

  v = a.Preorden();
  cout << "Preorden: " << v << endl;

  v = a.Postorden();
  cout << "Postorden: " << v << endl;
  return 0;
}
