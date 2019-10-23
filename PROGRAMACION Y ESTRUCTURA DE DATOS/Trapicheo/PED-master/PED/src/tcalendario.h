/*
 * TCalendario.h
 *
 *  Created on: 3/2/2015
 *      Author: alejandro
 */
#include <iostream>
#include <string>
#include <cstring>
using namespace std;

#ifndef TCALENDARIO_H_
#define TCALENDARIO_H_

class TCalendario {
public:
	//Constructor por defecto: inicializa dia, mes y anyo a 1 de 1 del 1900 y mensaje a NULL
	TCalendario ();
	//Constructor que inicializa calendario con parámetros pasados
	TCalendario(int dia, int mes, int anyo, char * mens);
	//Constructor copia
	TCalendario (TCalendario &);
	//Destructor
	~TCalendario();
	// Sobrecarga del operador asignación
	TCalendario & operator=(const TCalendario &);
	// Sobrecarga del operador SUMA de fecha + un número de dias;
	TCalendario operator+(int);
	// Sobrecarga del operador RESTA de fecha - un número de dias;
	TCalendario operator-(int);
	// Modifica la fecha incrementandola en un dia (con postincremento);
	TCalendario operator++(int);
	// Modifica la fecha incrementandola en un dia (con preincremento);
	TCalendario & operator++();
	// Modifica la fecha decrementándola en un dia (con postdecremento);
	TCalendario operator--(int);
	// Modifica la fecha decrementándola en un día (con predecremento);
	TCalendario & operator--();
	// Modifica la fecha
	bool ModFecha (int, int, int);
	// Modifica el mensaje
	bool ModMensaje(char *);
	// Sobrecarga del operador igualdad;
	bool operator ==(TCalendario &);
	// Sobrecarga del operador desigualdad;
	bool operator !=(TCalendario &);
	// Sobrecarga del operador >; (ver ACLARACIÓN sobre ORDENACIÓN)
	bool operator>(TCalendario &);
	// Sobrecarga del operador <; (ver ACLARACIÓN sobre ORDENACIÓN)
	bool operator<(TCalendario &);
	//TCalendario vacío
	bool EsVacio();
	// Devuelve el día del calendario;
	int Dia(){return dia;};
	// Devuelve el mes del calendario;
	int Mes(){return mes;};
	// Devuelve el año del calendario;
	int Anyo(){return anyo;};
	// Devuelve el mensaje del calendario;
	char *Mensaje(){return mensaje;};
	// Sobrecarga del operador salida
	friend ostream & operator<<(ostream &,const TCalendario &);

private:
	int dia, mes, anyo;
	char* mensaje;
	bool bisiesto();
	bool compruebaFecha(int, int, int);
};

#endif /* TCALENDARIO_H_ */
