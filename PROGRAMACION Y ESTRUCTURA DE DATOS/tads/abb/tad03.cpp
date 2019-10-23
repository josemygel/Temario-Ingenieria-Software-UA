#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/****************************************************/
/***************** CONSTRUCTOR DE COPIA, "=", "==" */
/***************************************************/
  TABBCalendario a,c;

  TCalendario c1(1,1,2011,"Fecha 1");
  TCalendario c2(2,1,2011,"Fecha 2");
  TCalendario c3(3,1,2011,"Fecha 3");

  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  TABBCalendario b(a);
  c=b;
  
  if ( a==b )
  	cout << "IGUALES" << endl;
  else 
	cout << "DISTINTOS" << endl;

  if ( c==b )
  	cout << "IGUALES" << endl;
  else 
	cout << "DISTINTOS" << endl;

  return 0;
}
