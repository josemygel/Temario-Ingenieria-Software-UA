#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
   char men[] = "Otra fecha correcta";
      
   TCalendario c1(14,12,1966, fc);
   cout << c1 << endl;
   
   c1.ModMensaje(men);
   cout << c1 << endl;
   
   c1.ModMensaje(NULL);
   cout << c1 << endl;
}
