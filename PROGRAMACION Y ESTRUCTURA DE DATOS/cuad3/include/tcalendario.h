#include <iostream>


#ifndef TCALENDARIO_H_
#define TCALENDARIO_H_


class TCalendario {
private:
//PARTE PRIVADA
int dia;
int mes;
int anyo; 
char* mensaje;

//METODOS AUXILIARES//Comprobacion de que el año es bisiesto
bool bisiesto(int a) const;
bool bisiesto() const{return bisiesto(anyo);}
//Comprobacion de la cantidad de dias que tiene un determinado mes en un determinado anyo
int diasMesAnyo(int m, int a)const;
int diasMesAnyo()const{return diasMesAnyo(mes, anyo);}
//Comprobacion de que la fecha es correcta
bool comprobar(int d, int m, int a)const;
bool comprobar() const{return comprobar(dia, mes, anyo);}

public:
//FORMA CANÓNICA
//Constructor por defecto: inicializa dia, mes y anyo a 1/1/1900 y mensaje a NULL
TCalendario();
//Constructor que inicializa calendario con parámetros pasados
TCalendario(int d, int m, int a, char * msg);
//Constructor copia
TCalendario(const TCalendario &cal);
//Destructor
~TCalendario(){dia=1;mes=1;anyo=1900;mensaje=NULL;}
// Sobrecarga del operador asignación
TCalendario& operator=(const TCalendario &cal);

//MÉTODOS
// Sobrecarga del operador SUMA de fecha + un número de dias;
TCalendario operator+(int count)const;
// Sobrecarga del operador RESTA de fecha - un número de dias;
TCalendario operator-(int count)const;
// Modifica la fecha incrementandola en un dia (con postincremento);
TCalendario operator++(int count);
// Modifica la fecha incrementandola en un dia (con preincremento);
TCalendario& operator++();
// Modifica la fecha decrementándola en un dia (con postdecremento);
TCalendario operator--(int count);
// Modifica la fecha decrementándola en un día (con predecremento);
TCalendario& operator--();
// Modifica la fecha
bool ModFecha (int d, int m, int a);
// Modifica el mensaje
bool ModMensaje(char * msg);
// Sobrecarga del operador igualdad;
bool operator ==(const TCalendario &cal)const;
// Sobrecarga del operador desigualdad;
bool operator !=(const TCalendario &cal)const;
// Sobrecarga del operador >; (ver ACLARACIÓN sobre ORDENACIÓN)
bool operator >(const TCalendario &cal)const;
// Sobrecarga del operador <; (ver ACLARACIÓN sobre ORDENACIÓN)
bool operator <(const TCalendario &cal)const;
//TCalendario vacío
bool EsVacio()const{return (dia==1 && mes==1 && anyo==1900 && mensaje==NULL);}
// Devuelve el día del calendario;
int Dia()const{return dia;}
// Devuelve el mes del calendario;
int Mes()const{return mes;}
// Devuelve el año del calendario;
int Anyo()const{return anyo;}
// Devuelve el mensaje del calendario;
char* Mensaje()const{return mensaje;}

//FUNCIONES AMIGAS
// Sobrecarga del operador salida
friend std::ostream & operator<<(std::ostream &, const TCalendario &);

};

#endif /* TCALENDARIO_H_ */
