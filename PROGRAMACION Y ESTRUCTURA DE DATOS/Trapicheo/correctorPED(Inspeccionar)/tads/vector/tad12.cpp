#include <iostream>

using namespace std;

#include "tvectorcom.h"

int
main(void)
{
  TVectorCom a, b(3), c(5);
  c[1] = 1;
  c[2] = 2;
  c[3] = 3;
  c[4] = 4;
  c[5] = 5;
  c[6] = 6;
  if(c.Redimensionar(0))
	cout << "Redimensionado: " << c << endl;
  else
	cout << "No Redimensionado: " << c << endl;

  if(c.Redimensionar(5))
	cout << "Redimensionado: " << c << endl;
  else
	cout << "No Redimensionado: " << c << endl;

  if(c.Redimensionar(6))
	cout << "Redimensionado: " << c << endl;
  else
	cout << "No Redimensionado: " << c << endl;

  if(c.Redimensionar(3))
	cout << "Redimensionado: " << c << endl;
  else
	cout << "No Redimensionado: " << c << endl;

  if(a.Redimensionar(3))
	cout << "Redimensionado: " << a << endl;
  else
	cout << "No Redimensionado: " << a << endl;

  return 0;

}


