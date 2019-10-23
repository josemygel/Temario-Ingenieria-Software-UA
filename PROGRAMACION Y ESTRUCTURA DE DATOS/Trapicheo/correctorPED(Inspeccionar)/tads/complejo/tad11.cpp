#include <iostream>

using namespace std;

#include "tcomplejo.h"

int
main(void)
{
  TComplejo a;
  TComplejo b(1,1);
  TComplejo c(b);
  
  
  if(a == b)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  b.~TComplejo();

 if(a == b)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;

  c.~TComplejo();

  if(b == c)
    cout << "SI" << endl;
  else
    cout << "NO" << endl;


  return 0;
}











