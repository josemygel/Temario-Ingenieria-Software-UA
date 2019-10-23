#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{

   TCalendario cal1(01,01,1900, (char*) "Fecha correcta");
 
   cout<<cal1+1<<endl;
   cout<<cal1-1<<endl;
  
}
