#include <iostream>
#include "tcalendario.h"

#ifndef TVECTORCALENDARIO_H_
#define TVECTORCALENDARIO_H_


class TVectorCalendario {
private:
//PARTE PRIVADA
TCalendario* c;
int tamanyo;
TCalendario error;


public:
//FORMA CANÓNICA
// Constructor por defecto
TVectorCalendario();
// Constructor a partir de un tamaño
TVectorCalendario(int tam);
// Constructor de copia
TVectorCalendario(const TVectorCalendario &vec);
// Destructor
~TVectorCalendario();
// Sobrecarga del operador asignación
TVectorCalendario& operator=(const TVectorCalendario &vec);

//MÉTODOS
void clear();
// Sobrecarga del operador igualdad
bool operator==(const TVectorCalendario &vec)const;
// Sobrecarga del operador desigualdad
bool operator!=(const TVectorCalendario &vec)const;
// Sobrecarga del operador corchete (parte IZQUIERDA)
TCalendario& operator[](int num);
// Sobrecarga del operador corchete (parte DERECHA)
TCalendario operator[](int num) const;
// Tamaño del vector (posiciones TOTALES)
int Tamano() const;
// Cantidad de posiciones OCUPADAS (no vacías) en el vector
int Ocupadas() const;
// Devuelve true si existe el calendario en el vector
bool ExisteCal(const TCalendario &cal) const;
// Mostrar por pantalla mensajes de TCalendario en el vector, de fecha IGUAL O POSTERIOR a la pasada
void MostrarMensajes(int n1,int n2,int n3)const;
// REDIMENSIONAR el vectorde TCalendario
bool Redimensionar(int tam);

//FUNCIONES AMIGAS
// Sobrecarga del operador salida
friend std::ostream & operator<<(std::ostream &os, const TVectorCalendario &cal);

};

#endif /* TVECTORCALENDARIO_H_ */
