#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{

  TComplejo a, s, r, m;
  TComplejo b(1), c(11, 12);
  TComplejo e(-1, 1), f(-1, -1);
  TComplejo g(-1,-1), h(0, -1);
  TComplejo i(1, -1);;

  double d;

  d=4.123;

  s = d + b;
  cout << s << endl;

  r = d - c;
  cout << r << endl;

  m = d * f;
  cout << m << endl;
    
  return 0;

}
