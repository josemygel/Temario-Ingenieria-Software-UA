
/* gcc -g dbc.c -o dbc -lm */

#include <stdio.h>
#include <math.h>

int myfunc (int val) {
  int n = (int) (sin(val)+sqrt(val));
  int d = val;
  
  return n / d;

}

int main(int argc, char *argv[])
{
  printf ("Llamamos a myfunc (2): %d\n", myfunc (2));
  printf ("Llamamos a myfunc (0): %d\n", myfunc (0));
  
  return 0;
}
