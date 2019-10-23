#include <iostream>

using namespace std;

#include "tvectorcom.h"


int
main(void)
{
  TVectorCom a(3);
  TComplejo res; 

  a[1] = 1; a[2] = 2; a[3] = 3;

  res = a[1]; 
  cout << "res: " << res << endl;

  res = a[2]; 
  cout << "res: " << res << endl;

  res = a[3]; 
  cout << "res: " << res << endl;

  return 0;

}


