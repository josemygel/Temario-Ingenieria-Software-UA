#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{

  TComplejo a, s, r, m;
  TComplejo b(1), c(1, 1);
  TComplejo e(-1, 1), f(-1, 0);
  TComplejo g(-1,-1), h(0, -1);
  TComplejo i(1, -1);;

  double d;

  d=4;
  s = b + d;
  cout << s << endl;

  r = c - d;
  cout << r << endl;

  m = e * d;
  cout << m << endl;

  return 0;
}
 