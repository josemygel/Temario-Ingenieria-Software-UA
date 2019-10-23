#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/***************************************************/
/***************** RESTA, INORDEN 	           */
/***************************************************/
  TABBCalendario a,b, resta;
  TVectorCalendario vec;

  TCalendario c1(1,1,2011,"Fecha1");
  TCalendario c2(2,1,2011,"Fecha2");
  TCalendario c3(3,1,2011,"Fecha3");

  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);

  b.Insertar(c1);

  resta = a-b;

  vec = resta.Inorden();

  cout <<"Inorden Resta: "<< vec << endl;

  return 0; 

}

