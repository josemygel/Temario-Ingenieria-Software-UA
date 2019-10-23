#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/************************************************/
/***************** INORDEN, "=="                */
/************************************************/

  TABBCalendario a,b;
  TVectorCalendario vec, vec2;

  TCalendario c1(1,1,2011,"Fecha1");
  TCalendario c2(2,1,2011,"Fecha2");
  TCalendario c3(3,1,2011,"Fecha3");
  TCalendario c4(4,1,2011,"Fecha4");
  TCalendario c5(5,1,2011,"Fecha5");
  TCalendario c6(6,1,2011,"Fecha6");

  a.Insertar(c5);
  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  a.Insertar(c6);


  vec = a.Inorden();
  b = a;
 
  vec2 = b.Inorden();
  if ( vec == vec2 ) 
	{cout << "IGUALES"<< endl;}
  else 
	{cout << "NO IGUALES"<< endl;}

  vec2 = b.Preorden();
  if ( vec == vec2 ) 
	{cout << "IGUALES"<< endl;}
  else 
	{cout << "NO IGUALES"<< endl;}

  return 0; 
}
