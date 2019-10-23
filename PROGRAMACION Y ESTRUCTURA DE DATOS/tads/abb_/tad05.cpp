#include <iostream> 
#include "tabbcalendario.h"

using namespace std;

int
main(void)
{
  TABBCalendario a;

  cout<<"Inorden="<<a.Inorden()<<endl;
  
  cout<<"Preorden="<<a.Preorden()<<endl;
  
  cout<<"Postorden="<<a.Postorden()<<endl;
  
  return 0;
}
