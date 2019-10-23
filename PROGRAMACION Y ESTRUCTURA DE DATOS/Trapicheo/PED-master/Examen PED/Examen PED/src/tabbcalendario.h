/*
 * tabbcalendario.h
 *
 *  Created on: 25/3/2015
 *      Author: Alejandro Reyes Albillar
 */
#include "tcalendario.h"
#include "tvectorcalendario.h"
#include "tlistacalendario.h"
#include <queue>

#ifndef TABBCALENDARIO_H_
#define TABBCALENDARIO_H_

using namespace std;

class TNodoABB;//Reaccion Forward, que se va a implementar despues

class TABBCalendario {
	friend class TNodoABB;
public:
	/** Constructor por defecto*/
	TABBCalendario ();
	/** Constructor de copia*/
	TABBCalendario (const TABBCalendario &);
	/** Destructor*/
	~TABBCalendario ();
	/** Sobrecarga del operador asignación*/
	TABBCalendario & operator=(const TABBCalendario &);
	/** Sobrecarga del operador igualdad*/
	bool operator==(const TABBCalendario &);
	/** Devuelve TRUE si el árbol está vacío, FALSE en caso contrario*/
	bool EsVacio();
	/** Inserta el elemento en el árbol*/
	bool Insertar(const TCalendario &);
	/** Borra el elemento en el árbol*/
	bool Borrar(const TCalendario &);
	/** Devuelve TRUE si el elemento está en el árbol, FALSE en caso contrario*/
	bool Buscar(const TCalendario &);
	/** Devuelve el elemento en la raíz del árbol*/
	TCalendario Raiz();
	/** Devuelve la altura del árbol (la altura de un árbol vacío es 0)*/
	int Altura();
	/** Devuelve el número de nodos del árbol (un árbol vacío posee 0 nodos)*/
	int Nodos();
	/** Devuelve el número de nodos hoja en el árbol (la raíz puede ser nodo hoja)*/
	int NodosHoja();
	/** Devuelve el recorrido en inorden sobre un vector*/
	TVectorCalendario Inorden();
	/** Devuelve el recorrido en preorden sobre un vector*/
	TVectorCalendario Preorden();
	/** Devuelve el recorrido en postorden sobre un vector*/
	TVectorCalendario Postorden();
	/** Devuelve el recorrido en niveles*/
	TVectorCalendario Niveles();
	/** Sobrecarga del operador salida*/
	friend ostream & operator<<(ostream &,const TABBCalendario &);
	/** Suma de dos ABB*/
	TABBCalendario operator+(const TABBCalendario &);
	/** Resta de dos ABB*/
	TABBCalendario operator-(const TABBCalendario &);
	/**Dado un abb y una lista determina si la lista es un camino del ABB*/
	TListaCalendario examen(const TListaCalendario &);

private:
	/** Puntero al nodo raíz*/
	TNodoABB *raiz;
	/** Método de copia*/
	void copiar(const TABBCalendario &);
	/** Devuelve el recorrido en inorden*/
	void InordenAux(TVectorCalendario &,int &);
	/** Devuelve el recorrido en preorden*/
	void PreordenAux(TVectorCalendario &,int &);
	/** Devuelve el recorrido en postorden*/
	void PostordenAux(TVectorCalendario &,int &);
	/** Me devuelve el último hijo de la derecha, y si ese hijo tiene hijos a la izquierda, cambia la raiz por este y devuelve la copia temporal del hijo*/
	TABBCalendario* MayorArbol();
	/**Devuelve un ABB transformado en una lista*/
	TListaCalendario Aplana();
	//Te dice si una lista es un camino de un arbol
	bool EsCamino(const TListaCalendario &);
};

class TNodoABB{
	friend class TABBCalendario;
public:
	/**Constructor por defecto*/
	TNodoABB ();
	/**Constructor copia*/
	TNodoABB (const TNodoABB &);
	/**Destructor*/
	~TNodoABB ();
	/** Sobrecarga del operador asignación*/
	TNodoABB & operator=(const TNodoABB &);

private:

	/** Elemento del nodo*/
	TCalendario item;
	/** Subárbol izquierdo y derecho*/
	TABBCalendario iz, de;

};


#endif /* TABBCALENDARIO_H_ */
