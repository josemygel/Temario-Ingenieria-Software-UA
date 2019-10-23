#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a;
  TComplejo b(a);

  b.~TComplejo();


  cout << a << " " << b << endl;

  return 0;
}

