#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   char fi[] = "Fecha incorrecta";
   char fc[] = "Fecha correcta";
      
   TCalendario c1(14,12,1966, fc);
   TCalendario c2(19,11,1994, fc);
   TCalendario c3(01,01,1900, fc);
   TCalendario c4(14,12,1966, fc);

   if (c1==c4)
   cout<<"operador == funciona"<<endl;
   else
   cout<<"operador == no funciona"<<endl;
   
   if (c2==c3)
   cout<<"operador == no funciona"<<endl;
   else
   cout<<"operador == funciona"<<endl;

   
   if (c1>c2)
   cout<<"operador > no funciona"<<endl;
   else 
   cout<<"operador > funciona"<<endl;

   if (c3<c4)
   cout<<"operador < funciona"<<endl;
   else
   cout<<"operador < no funciona"<<endl;

   if (c1<c4)
   cout<<"operador < no funciona"<<endl;
   else
   cout<<"operador < funciona"<<endl;

   if (c1>c4)
   cout<<"operador > no funciona"<<endl;
   else
   cout<<"operador > funciona"<< endl;
}
