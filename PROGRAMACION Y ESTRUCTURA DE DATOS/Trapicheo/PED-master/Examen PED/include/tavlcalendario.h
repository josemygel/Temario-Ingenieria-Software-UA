/*
 * tavlcalendario.h
 *
 *  Created on: 5/5/2015
 *      Author: Alejandro Reyes Albillar
 */
#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tlistacalendario.h"


#ifndef TAVLCALENDARIO_H_
#define TAVLCALENDARIO_H_

class TNodoAVL;

class TAVLCalendario{
	friend class TNodoAVL;

public:
	// Constructor por defecto
	TAVLCalendario ();
	// Constructor de copia
	TAVLCalendario (const TAVLCalendario &);
	// Destructor
	~TAVLCalendario ();
	// Sobrecarga del operador asignación
	TAVLCalendario & operator=(const TAVLCalendario &);
	// Sobrecarga del operador igualdad
	bool operator==(const TAVLCalendario &);
	// Sobrecarga del operador desigualdad
	bool operator!=(const TAVLCalendario &);
	// Devuelve TRUE si el árbol está vacío, FALSE en caso contrario
	bool EsVacio();
	// Inserta el elemento en el árbol
	bool Insertar(const TCalendario &);
	// Devuelve true si el elemento está en el árbol, false en caso contrario
	bool Buscar(const TCalendario &);
	// Devuelve la altura del árbol (la altura de un árbol vacío es 0)
	int Altura();
	// Devuelve el número de nodos del árbol (un árbol vacío posee 0 nodos)
	int Nodos();
	// Devuelve el número de nodos hoja en el árbol (la raíz puede ser nodo hoja)
	int NodosHoja();
	// Devuelve el recorrido en inorden sobre un vector
	TVectorCalendario Inorden();
	// Devuelve el recorrido en preorden sobre un vector
	TVectorCalendario Preorden();
	// Devuelve el recorrido en postorden sobre un vector
	TVectorCalendario Postorden();
	// Borra un TCalendario del árbol AVL
	bool Borrar(const TCalendario &);
	// Devuelve el elemento TCalendario raíz del árbol AVL
	TCalendario Raiz();
	// Sobrecarga del operador salida
	friend ostream & operator<<(ostream &,const TAVLCalendario &);
private:
	// Puntero al nodo raíz
	TNodoAVL *raiz;
	// Devuelve el recorrido en inorden
	void InordenAux(TVectorCalendario &, int &);
	// Devuelve el recorrido en preorden
	void PreordenAux(TVectorCalendario &, int &);
	// Devuelve el recorrido en postorden
	void PostordenAux(TVectorCalendario &, int &);
	/** Método de copia*/
	void copiar(const TAVLCalendario &);
	// Mueve el arbol de la derecha al de la iquierda;
	void Mover(TAVLCalendario&,TAVLCalendario&);
	// Mueve el arbol al que apunta el puntero al arbol
	void Mover(TAVLCalendario&,TAVLCalendario*&);
	// Mueve el arbol a la posición donde apunta el puntero
	void Mover(TAVLCalendario*&,TAVLCalendario&);
	// Mueve el arbol al que apunta el arbol de la derecha al puntero que apunta el árbol de la izquierda
	void Mover(TAVLCalendario*&,TAVLCalendario*&);
	//Equilibra el arbol por la izquierda
	bool EquilibrarIzquierda();
	//Equilibra el arbol por la derecha
	bool EquilibrarDerecha();
	//Reequilbra los factores de equilibrio
	void reequilibrarFE();
	/** Me devuelve el último hijo de la derecha, y si ese hijo tiene hijos a la izquierda, cambia la raiz por este y devuelve la copia temporal del hijo*/
	TAVLCalendario* MayorArbol();
};

class TNodoAVL{
	friend class TAVLCalendario;
public:
	//Constructor por defecto
	TNodoAVL ();
	//Constructor copia
	TNodoAVL (const TNodoAVL &);
	//Destructor
	~TNodoAVL ();
	// Sobrecarga del operador asignación
	TNodoAVL & operator=(const TNodoAVL &);
private:
	// El elemento (etiqueta) del nodo
	TCalendario item ;
	// Subárbol izquierdo y derecho
	TAVLCalendario iz, de ;
	// Factor de equilibrio
	int fe ;
};

using namespace std;



#endif /* TAVLCALENDARIO_H_ */
