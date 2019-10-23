#include <iostream>
#include "tabbcalendario.h"
using namespace std;

int
main(void)
{
/***************************************************/
/***************** INSERTAR			   */
/***************************************************/
  TABBCalendario a;

  TCalendario c1(1,1,2011,"Fecha1");
  TCalendario c2(2,1,2011,"Fecha2");
  TCalendario c3(3,1,2011,"Fecha3");
  TCalendario c4(4,1,2011,"Fecha4");
  TCalendario c5(5,1,2011,"Fecha5");
  TCalendario c6(6,1,2011,"Fecha6");
  TCalendario c7(7,1,2011,"Fecha7");

  a.Insertar(c5);
  a.Insertar(c2);
  a.Insertar(c1);
  a.Insertar(c3);
  a.Insertar(c6);

  if(a.Insertar(c7))
        cout << "Insertado" << endl;
  else
	cout << "No insertado" << endl;

  if(a.Insertar(c7))
        cout << "Insertado" << endl;
  else
	cout << "No insertado" << endl;

  return 0;
}
