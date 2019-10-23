/************************************************************
** CONSTRUCTORES, = CON ARBOLES VACIOS
*************************************************************/
#include <iostream>
#include "tavlcalendario.h"

using namespace std;

int
main(void)
{
  TAVLCalendario a,c;
  TAVLCalendario b(a);

  c=b;
  
  cout << "No hace nada" << endl;
  return 0;
}
