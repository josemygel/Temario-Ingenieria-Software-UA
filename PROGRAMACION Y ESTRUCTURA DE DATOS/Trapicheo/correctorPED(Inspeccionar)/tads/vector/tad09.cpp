#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a, b(3), c(5);

  cout << a.Ocupadas() << endl;
  cout << b.Ocupadas() << endl;
  cout << c.Ocupadas() << endl;

  c[1] = 1;
  c.~TVectorCom();
  b[1] = 1;
  b[0] = 1;

  cout << a.Ocupadas() << endl;
  cout << b.Ocupadas() << endl;
  cout << c.Ocupadas() << endl;

  return 0;

}


