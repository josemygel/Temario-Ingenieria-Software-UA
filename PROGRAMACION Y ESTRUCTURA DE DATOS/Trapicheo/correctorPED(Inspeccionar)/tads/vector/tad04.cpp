#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  int i;
  TVectorCom a(100);
  TVectorCom b;

  for(i = 1; i<= 100; i++)
  {
    a[i] = 1;
  }

  if(a == b)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  if(a != b)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  return 0;

}


