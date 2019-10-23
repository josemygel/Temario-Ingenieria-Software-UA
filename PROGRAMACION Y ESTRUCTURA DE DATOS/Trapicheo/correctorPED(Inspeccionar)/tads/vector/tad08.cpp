#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a, b(1), c(5);
  TVectorCom a1(a), b1(b), c1(c);

  cout << a.Tamano() << endl;
  cout << b.Tamano() << endl;
  cout << c.Tamano() << endl;
  cout << a1.Tamano() << endl;
  cout << b1.Tamano() << endl;
  cout << c1.Tamano() << endl;

  a.~TVectorCom();
  b.~TVectorCom();
  c.~TVectorCom();
  a1.~TVectorCom();
  b1.~TVectorCom();
  c1.~TVectorCom();

  cout << a.Tamano() << endl;
  cout << b.Tamano() << endl;
  cout << c.Tamano() << endl;
  cout << a1.Tamano() << endl;
  cout << b1.Tamano() << endl;
  cout << c1.Tamano() << endl;

  return 0;

}


