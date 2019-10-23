/************************************************************
** ALTURA, NODOS, NODOSHOJA, =, != 
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main()
{
  TAVLCalendario arb1, arb2;

  TCalendario bd(1,1,2011,(char *)"uno");
  TCalendario bc(2,2,2011,(char *)"dos");
  TCalendario be(3,3,2011,(char *)"tres");
  TCalendario bn(4,4,2011,(char *)"cuatro");
  TCalendario ba(5,5,2011,(char *)"cinco");
  TCalendario bm(6,6,2011,(char *)"seis");

  arb1.Insertar(ba);
  arb1.Insertar(bc);
  arb1.Insertar(bd);
  arb1.Insertar(be);
  arb1.Insertar(bm);
  

  arb2 = arb1;
  if(arb2 != arb1)
    cout << "No iguales" <<endl;
  else
    cout << "Iguales" << endl;

  cout << "Altura: " << arb2.Altura() << endl;
  cout << "Nodos: " << arb2.Nodos() << endl;
  cout << "NodosHoja: " << arb2.NodosHoja() << endl;

  return 0;
}
