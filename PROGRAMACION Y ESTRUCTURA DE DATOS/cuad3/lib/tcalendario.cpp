#include "tcalendario.h"
#include <stdio.h>
#include <string.h>

int TCalendario::diasMesAnyo(int m, int a) const {
    

    if (m == 2) //El mes es febrero
        return bisiesto(a) ? 29 : 28; //Si es bisiesto 29, si no 28
        
    if (m <= 7) // Testeando meses pares antes de Agosto (tienen 30 dias)
        return (m % 2 == 0) ? 30 : 31;

    // Testeando meses pares despues de Agosto (tienen 31 dias)
    return (m % 2 == 0) ? 31 : 30;  

    
    /*ANTES
    if (bisiesto(a) && m == 2) {
        return 29;
        
    }else if (!bisiesto(a) && m == 2) {
        return 28;
        
    }else if (m <= 7) {
        // Testeando meses pares antes de Agosto (tienen 30 dias)
        if (m % 2 == 0) {
            return 30;
        }else{
            return 31;
        }
    }else{
        // Testeando meses pares despues de Agosto (tienen 31 dias)
        if (m % 2 == 0) {
            return 31;
        }else{
            return 30;
        }
    }  */
}


bool 
TCalendario::bisiesto(int a) const{
 if (a % 400 == 0) {
     return true;
 }else if (a % 100 == 0) {
     return false;
 }else if (a % 4 == 0) {
     return true;
 }
 return false;
}

bool 
TCalendario::comprobar(int d, int m, int a) const{
    if (a < 1900 || m > 12 ||  m < 1 ||  d < 1 || d > diasMesAnyo(m, a))
        return false;
           
    return true;
}
//FORMA CANÓNICA
//Constructor por defecto: inicializa dia, mes y anyo a 1/1/1900 y mensaje a NULL
TCalendario::TCalendario(){
    dia=1;
    mes=1;
    anyo=1900;
    mensaje=NULL;
}
//Constructor que inicializa calendario con parámetros pasados
TCalendario::TCalendario(int d, int m, int a, char * msg){
    if (comprobar(d, m, a)){
        dia = d;
        mes = m;
        anyo = a;    
        if(msg != NULL) { //Comprobemos si c.mensaje no es nulo
            mensaje = new char[strlen(msg)+1]; //Asigno la misma capacidad de memoria a mens que a mensaje
            strcpy(mensaje,msg); // Copio el mensaje
        }else mensaje = NULL;
    }else{
        dia = 1;
        mes = 1;
        anyo = 1900;
        mensaje = NULL;
    }
}
//Constructor copia
TCalendario::TCalendario(const TCalendario &cal) {
    this->operator=(cal);
}

//Destructor
//TCalendario::~TCalendario()

// Sobrecarga del operador asignación
TCalendario& 
TCalendario::operator=(const TCalendario &cal) {
    dia = cal.Dia();
    mes = cal.Mes();
    anyo = cal.Anyo();

    if(cal.Mensaje() != NULL) { //Comprobemos si c.mensaje no es nulo
        mensaje = new char[strlen(cal.Mensaje())+1]; //Asigno la misma capacidad de memoria a mens que a mensaje
        strcpy(Mensaje(),cal.Mensaje()); // Copio el mensaje
    }else{
        mensaje = NULL;
    }

    return *this;
}

//MÉTODOS
// Sobrecarga del operador SUMA de fecha + un número de dias;
TCalendario 
TCalendario::operator+(int count) const {
    TCalendario aux(*this);
    
    aux.dia = aux.Dia()+count;
    count = aux.Dia() - aux.diasMesAnyo();
    
    while (count > 0 && !aux.comprobar()){
        // PASAMOS DE MES Y AÑO
        aux.dia = 1;
        aux.mes++;
        
        if (aux.Mes() > 12) {
            aux.mes = 1;
            aux.anyo++;
        }
        
        aux.dia = count;
        count = aux.Dia() - aux.diasMesAnyo();
    }
    
    
    return aux;
}
// Sobrecarga del operador RESTA de fecha - un número de dias;
TCalendario 
TCalendario::operator-(int count) const {
    TCalendario aux(*this);
    // Ejemplo: 2/2/1999 - 3 = 30/1/1999 - 5 = 25/1/1999
    if (count < aux.Dia()) {
      aux.dia = aux.Dia() - count;
      return aux;
    }
    
    while (count > 0 && aux.anyo >= 1900){
        
        count = count - aux.Dia();
        aux.mes--;
        
        if (aux.Mes() < 1) {
            aux.mes = 12;
            aux.anyo--;
        }
        
        aux.dia = aux.diasMesAnyo();
        if (count < aux.Dia()){
            aux.dia=aux.Dia()-count;
            count=0;
        }
    }
    
    aux.dia = aux.dia - count;
  
    if (aux.anyo < 1900) {
        aux.~TCalendario();
    }
    
    return aux;
}
// Modifica la fecha incrementandola en un dia (con postincremento);
TCalendario 
TCalendario::operator++(int count) {
    TCalendario aux(*this);
    *this=operator+(1);
    return aux;
}
// Modifica la fecha incrementandola en un dia (con preincremento);
TCalendario& 
TCalendario::operator++() {
    *this=operator+(1);
    return *this;
}
// Modifica la fecha decrementándola en un dia (con postdecremento);
TCalendario 
TCalendario::operator--(int count) {
    TCalendario aux(*this);
    *this=operator-(1);
    return aux;
}
// Modifica la fecha decrementándola en un día (con predecremento);
TCalendario& 
TCalendario::operator--() {
    *this=operator-(1);
    return *this;
}
// Modifica la fecha
bool TCalendario::ModFecha (int d, int m, int a) {
    if(comprobar(d,m,a)){
        dia=d;
        mes=m;
        anyo=a;
        return true;
    }
    return false;
}
// Modifica el mensaje
bool TCalendario::ModMensaje(char* msg) {
    
    if(msg == NULL){
        mensaje = NULL;
    }else{ //Comprobemos si c.mensaje no es nulo
        mensaje = new char[strlen(msg)+1]; //Asigno la misma capacidad de memoria a mens que a mensaje
        strcpy(mensaje, msg); // Copio el mensaje
    }
    return true;
}
// Sobrecarga del operador igualdad;
bool TCalendario::operator ==(const TCalendario &cal) const{
    
    if(dia==cal.Dia() && mes==cal.Mes() && anyo==cal.Anyo()){   //Si las fechas coinciden comparamos mensajes
        if (mensaje == NULL || cal.mensaje == NULL){        //Si los mensajes son alguno nulos
            return (mensaje==NULL && cal.Mensaje()==NULL);      //Ambos deben ser nulos para ser el mismo mensaje
        }else{                                          //Si no...
            return strcmp(mensaje,cal.Mensaje())==0;            //Si los mensajes son iguales strcmp devuelve 0
        }
    }

    return false;
}
// Sobrecarga del operador desigualdad;
bool TCalendario::operator !=(const TCalendario &cal) const{
    return !this->operator ==(cal);
}
// Sobrecarga del operador >; (ver ACLARACIÓN sobre ORDENACIÓN)
bool TCalendario::operator >(const TCalendario &cal) const{
    if(*this==cal)
        return false;

    return !operator <(cal);
}
// Sobrecarga del operador <; (ver ACLARACIÓN sobre ORDENACIÓN)
bool TCalendario::operator <(const TCalendario &cal) const{
    if(*this==cal)
        return false;

    if (anyo != cal.anyo) //Si los anyos son distintos
        return anyo < cal.anyo;

    else if (mes != cal.mes) //Si los meses son distintos
        return mes < cal.mes;

    else if (dia != cal.dia) //Si los dias son distintos
        return dia < cal.dia;

    else if (mensaje != NULL || cal.mensaje != NULL) //Si uno de los dos es NULL
        return mensaje == NULL; //Si el de cal.mensaje NO es NULL, TRUE, si no FALSE
    
    return (strcmp(mensaje,cal.mensaje) < 0); //< 0 si el mensaje es menor que el de cal.mensaje
    
}
/* IN LINE
//TCalendario vacío
bool TCalendario::EsVacio()const{
    return (dia==1 && mes==1 && anyo==1900 && mensaje==NULL);
}
*/
/*
// Devuelve el día del calendario;
int TCalendario::Dia() const{
    return dia;
}
// Devuelve el mes del calendario;
int TCalendario::Mes() const{
    return mes;
}
// Devuelve el año del calendario;
int TCalendario::Anyo() const{
    return anyo;
}
// Devuelve el mensaje del calendario;
char* TCalendario::Mensaje()const{
    return mensaje;
}
*/

//FUNCIONES AMIGAS
// Sobrecarga del operador salida
std::ostream& operator<<(std::ostream &os, const TCalendario &cal){
    if (cal.Dia()<10)
        os << "0";
    // Introducimos dia
    os <<  cal.Dia() <<  "/";
    
    if (cal.Mes()<10)
        os << "0";
    // Introducimos mes
    os << cal.Mes() << "/";
    
    // Introducimos año
    os << cal.Anyo() << " ";
    
    if (cal.Mensaje() != NULL)
        os << "\"" << cal.Mensaje() <<  "\"";
    else
        os << "\"\"";
    return os;
}