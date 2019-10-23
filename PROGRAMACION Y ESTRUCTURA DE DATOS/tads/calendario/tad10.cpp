#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
      
   TCalendario c1(14,12,1966, fc);
   
   if (c1.Dia()==14) cout<<"Funcion Dia correcta"<<endl;
   if (c1.Mes()==12) cout<<"Funcion Mes correcta"<<endl;
   if (c1.Anyo()==1966) cout<<"Funcion Anyo correcta"<<endl;         
}
