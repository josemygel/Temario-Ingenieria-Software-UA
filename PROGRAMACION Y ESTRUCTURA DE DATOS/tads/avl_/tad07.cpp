/************************************************************
** BUSCAR
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main()
{
  TAVLCalendario arb1;

  TCalendario c1(1,1,2011,(char *)"uno");
  TCalendario c2(2,2,2011,(char *)"dos");
  TCalendario c3(3,3,2011,(char *)"tres");
  TCalendario c4(4,4,2011,(char *)"cuatro");
  TCalendario c5(5,5,2011,(char *)"cinco");
  TCalendario c6(6,6,2011,(char *)"seis");
  TCalendario otro(10,10,2011,(char *)"diez");

  arb1.Insertar(c5);
  arb1.Insertar(c2);
  arb1.Insertar(c3);
  arb1.Insertar(c1);
  arb1.Insertar(c6);

  if(arb1.Buscar(otro))
    cout << "Encontrado" << endl;
  else
    cout << "No encontrado" << endl;

  if(arb1.Buscar(c1))
    cout << "Encontrado" << endl;
  else
    cout << "No encontrado" << endl;

  return 0;
}
