#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/***************************************************/
/***************** SUMA,INORDEN 	           */
/***************************************************/
  TABBCalendario a,b, suma;
  TVectorCalendario vec;

  TCalendario c1(1,1,2011,"Fecha1");
  TCalendario c2(2,1,2011,"Fecha2");
  TCalendario c3(3,1,2011,"Fecha3");
  TCalendario c4(4,1,2011,"Fecha4");
  TCalendario c5(5,1,2011,"Fecha5");

  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);

  b.Insertar(c4);
  b.Insertar(c5);

  suma = a+b;

  vec = suma.Inorden();

  cout <<"Inorden Suma: "<< vec << endl;

  return 0; 

}

