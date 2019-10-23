#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a, s, r, m, t(2,2);
  double d;

  d=0;
  s = d + a;
  cout << s << endl;

  m = d * a;
  cout << m << endl;
    
  r = 1 - t;
  cout << r << endl;

  return 0;

}
