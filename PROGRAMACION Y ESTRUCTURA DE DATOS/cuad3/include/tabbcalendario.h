#include "tcalendario.h"
#include "tvectorcalendario.h"

#ifndef TABBCALENDARIO_H_
#define TABBCALENDARIO_H_


class TNodoABB;

class TABBCalendario{
    //PARTE PRIVADA
    private:
    // Puntero al nodo raíz
    TNodoABB *raiz;
    // AUXILIAR : devuelve el recorrido por niveles
    void NivelesAux(TVectorCalendario &vec, int n)const;
    // AUXILIAR : devuelve el recorrido en inorden
    void InordenAux(TVectorCalendario &vec, int &n)const;
    // AUXILIAR : devuelve el recorrido en preorden
    void PreordenAux(TVectorCalendario &vec, int &n)const;
    // AUXILIAR : devuelve el recorrido en postorden
    void PostordenAux(TVectorCalendario &vec, int &n)const;
    
    //FORMA CANÓNICA
    public:
    // Constructor por defecto
    TABBCalendario();
    // Constructor de copia
    TABBCalendario(const TABBCalendario &abb);
    // Destructor
    ~TABBCalendario();
    // Sobrecarga del operador asignación
    TABBCalendario& operator=(const TABBCalendario &abb);
    
    //MÉTODOS
    // Sobrecarga del operador igualdad
    bool operator==(const TABBCalendario &abb)const;
    // Devuelve TRUE si el árbol está vacío, FALSE en caso contrario
    bool EsVacio()const;
    // Inserta el elemento en el árbol
    bool Insertar(const TCalendario &cal);
    // Borra el elemento en el árbol
    bool Borrar(const TCalendario &cal);
    // Devuelve TRUE si el elemento está en el árbol, FALSE en caso contrario
    bool Buscar(const TCalendario &cal)const;
    // Devuelve el elemento de la raíz del árbol
    TCalendario Raiz()const;
    // Devuelve la altura del árbol (la altura de un árbol vacío es 0)
    int Altura()const;
    // Devuelve el número de nodos del árbol (un árbol vacío posee 0 nodos)
    int Nodos()const;
    // Devuelve el número de nodos hoja en el árbol (la raíz puede ser nodo hoja)
    int NodosHoja()const;
    // Devuelve el recorrido en inorden sobre un vector
    TVectorCalendario Inorden()const;
    // Devuelve el recorrido en preorden sobre un vector
    TVectorCalendario Preorden()const;
    // Devuelve el recorrido en postorden sobre un vector
    TVectorCalendario Postorden()const;
    // Devuelve el recorrido en niveles
    TVectorCalendario Niveles()const;
    // Sobrecarga del operador salida
    friend std::ostream& operator<<(std::ostream &os, const TABBCalendario &abb){return os << abb.Inorden();}
    // Suma de dos ABB
    TABBCalendario operator+(const TABBCalendario &abb)const;
    // Resta de dos ABB
    TABBCalendario operator-(const TABBCalendario &abb)const;
    // CLEAR
    void clear();
};


class TNodoABB{
    friend class TABBCalendario;
    //PARTE PRIVADA
    private:
    // Elemento del nodo
    TCalendario item;
    // Subárbol izquierdo y derecho
    TABBCalendario iz;
    TABBCalendario de;
    
    //FORMA CANÓNICA
    public:
    //Constructor por defecto
    TNodoABB():item(),iz(),de(){}
    //Constructor copia
    TNodoABB(const TNodoABB &nodo):item(nodo.item),iz(nodo.iz),de(nodo.de){}
    //Destructor
    ~TNodoABB();
    // Sobrecarga del operador asignación
    TNodoABB& operator=(const TNodoABB &nodo);
    // Operador Clear
    void clear();
};

#endif /*TABBCALENDARIO_H_*/