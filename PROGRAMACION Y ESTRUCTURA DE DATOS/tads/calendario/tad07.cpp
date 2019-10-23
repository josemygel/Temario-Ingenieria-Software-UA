#include <iostream>

using namespace std;

#include "tcalendario.h"


int
main()
{
   TCalendario c1;

   if (c1.ModFecha(29,02,2006))  
   cout<<"Procedimiento ModFecha correcto"<<endl;
   else
   cout<<"Procedimiento ModFecha incorrecto"<<endl;

   if (c1.ModFecha(1,1,1900))
   cout<<"Procedimiento ModFecha correcto"<<endl;
   else
   cout<<"Procedimiento ModFecha incorrecto"<<endl;

   if (c1.ModFecha(20,10,1800))
   cout<<"Procedimiento ModFecha correcto"<<endl;
   else
   cout<<"Procedimiento ModFecha incorrecto"<<endl;

   if (c1.ModFecha(40,10,1900))
   cout<<"Procedimiento ModFecha correcto"<<endl;
   else
   cout<<"Procedimiento ModFecha incorrecto"<<endl;


}
