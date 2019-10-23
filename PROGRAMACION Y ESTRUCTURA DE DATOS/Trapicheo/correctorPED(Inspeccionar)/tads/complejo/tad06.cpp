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

  cout << a.Re() << " " << a.Im() << endl;
  cout << b.Re() << " " << b.Im() << endl;
  cout << c.Re() << " " << c.Im() << endl;
  cout << d.Re() << " " << d.Im() << endl;
  cout << e.Re() << " " << e.Im() << endl;
  cout << f.Re() << " " << f.Im() << endl;
  cout << g.Re() << " " << g.Im() << endl;
  cout << h.Re() << " " << h.Im() << endl;
  cout << i.Re() << " " << i.Im() << endl;

  return 0;
}
