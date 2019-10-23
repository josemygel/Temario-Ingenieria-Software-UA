#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a, s, r, m;
  double d;

  d=0;
  s = a + d;
  cout << s << endl;

  r = a - d;
  cout << r << endl;

  m = a * d;
  cout << m << endl;
    
  return 0;

}
