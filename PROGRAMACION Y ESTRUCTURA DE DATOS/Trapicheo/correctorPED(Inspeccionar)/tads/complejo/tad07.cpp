#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a(0, 0);
  TComplejo b(1, 0), c(1, 1), d(0, 1);
  TComplejo e(-1, 1), f(-1, 0);
  TComplejo g(-1,-1), h(0, -1);
  TComplejo i(1, -1);;

  cout << a.Mod() << " " << a.Arg() << endl;
  cout << b.Mod() << " " << b.Arg() << endl;
  cout << c.Mod() << " " << c.Arg() << endl;
  cout << d.Mod() << " " << d.Arg() << endl;
  cout << e.Mod() << " " << e.Arg() << endl;
  cout << f.Mod() << " " << f.Arg() << endl;
  cout << g.Mod() << " " << g.Arg() << endl;
  cout << h.Mod() << " " << h.Arg() << endl;
  cout << i.Mod() << " " << i.Arg() << endl;

  return 0;
}
