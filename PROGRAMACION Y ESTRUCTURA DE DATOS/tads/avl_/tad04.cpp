/************************************************************
** ALTURA, NODOS, NODOSHOJA CON 1 ELEMENTO
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a;
  TCalendario cal(1,1,2011,(char *)"uno");

  a.Insertar(cal);

  cout << "Altura: " << a.Altura() << endl;
  cout << "Nodos: " << a.Nodos() << endl;
  cout << "NodosHoja: " << a.NodosHoja() << endl;
 return 0;
}
