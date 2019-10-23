#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a(10, 20), s, r, m;
  double d = 6;

  s = d + a;
  cout << s << endl;

  r = d - a;
  cout << r << endl;

  m = d * a;
  cout << m << endl;
    
  return 0;

}
