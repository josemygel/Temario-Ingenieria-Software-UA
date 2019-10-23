#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a(1000);

  for(int i = 1; i <= 1000; i++)
    a[i] = i;

  for(int i = 1; i <= 1000; i++)
    cout << a[i] << endl;

  return 0;
}
