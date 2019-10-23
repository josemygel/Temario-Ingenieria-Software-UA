/*
 * tvectorcalendario.h
 *
 *  Created on: 25/2/2015
 *      Author:Alejandro Reyes Albillar
 */
#include "tcalendario.h"
using namespace std;

#ifndef TVECTORCALENDARIO_H_
#define TVECTORCALENDARIO_H_

class TVectorCalendario {
public:
	// Constructor por defecto
	TVectorCalendario();
	// Constructor a partir de un tamaño
	TVectorCalendario(int);
	// Constructor de copia
	TVectorCalendario(const TVectorCalendario &);
	// Destructor
	~TVectorCalendario();
	// Sobrecarga del operador asignación
	TVectorCalendario & operator=(const TVectorCalendario &);
	// Sobrecarga del operador igualdad
	bool operator==(const TVectorCalendario &);
	// Sobrecarga del operador desigualdad
	bool operator!=(const TVectorCalendario &);
	// Sobrecarga del operador corchete (parte IZQUIERDA)
	TCalendario & operator[](int);
	// Sobrecarga del operador corchete (parte DERECHA)
	TCalendario operator[](int) const;
	// Tamaño del vector (posiciones TOTALES)
	int Tamano();
	// Cantidad de posiciones OCUPADAS (no vacías) en el vector
	int Ocupadas();
	// Devuelve true si existe el calendario en el vector
	bool ExisteCal(TCalendario &);
	// Mostrar por pantalla mensajes de TCalendario en el vector, de fecha IGUAL O POSTERIOR a la pasada
	void MostrarMensajes(int,int,int);
	// REDIMENSIONAR el  vectorde TCalendario
	bool  Redimensionar(int);
	// Sobrecarga del operador salida
	friend ostream & operator<<(ostream &,const TVectorCalendario &);
private:
	TCalendario *c;
	int tamano;
	TCalendario error;
	bool bisiesto(int);
	bool compruebaFecha(int, int, int);

};

#endif /* TVECTORCALENDARIO_H_ */
