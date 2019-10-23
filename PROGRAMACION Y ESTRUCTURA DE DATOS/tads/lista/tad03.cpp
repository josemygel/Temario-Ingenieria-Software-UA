#include <iostream>

using namespace std;

#include "tvectorcalendario.h"

int
main(void)
{
   TVectorCalendario a, b(-3), c(4);
   cout << a.Tamano() << endl;
   cout << b.Tamano() << endl;
   cout << c.Tamano() << endl;
   cout << c.Ocupadas() << endl;
   return 0;
}
