#include <iostream>

using namespace std;

#include "tvectorcom.h"

void
funcion(TVectorCom b)
{
  cout << "b: " << b << endl;
}

int
main(void)
{

  TVectorCom a(2);

  a[1]= 1;
  cout << "a: " << a << endl;
  funcion(a);

  return 0;

}


