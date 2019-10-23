#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a, b(3), c(5);
  c[1] = 1;
  c[2] = 2;
  c[3] = 3;
  c[4] = 4;
  c[5] = 1;
  c[6] = 6;
  c.MostrarComplejos(3);
  a.MostrarComplejos(3);
  b.MostrarComplejos(3);

  return 0;

}


