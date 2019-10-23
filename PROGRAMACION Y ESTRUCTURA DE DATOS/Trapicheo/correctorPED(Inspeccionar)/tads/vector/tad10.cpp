#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a, b(3), c(5);

  if(a.ExisteCom(3))
	cout << "Existe" << endl;
  else
	cout << "No Existe" << endl;

  b[1]=3;
  if(b.ExisteCom(3))
	cout << "Existe" << endl;
  else
	cout << "No Existe" << endl;

  b.~TVectorCom();
  if(b.ExisteCom(3))
	cout << "Existe" << endl;
  else
	cout << "No Existe" << endl;

  c[1] = 1;
  if(c.ExisteCom(3))
	cout << "Existe" << endl;
  else
	cout << "No Existe" << endl;

  return 0;

}


