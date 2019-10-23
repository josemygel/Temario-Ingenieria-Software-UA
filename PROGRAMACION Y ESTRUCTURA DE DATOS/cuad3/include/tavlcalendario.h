#include "tcalendario.h"
#include "tvectorcalendario.h"

#ifndef TAVLCALENDARIOH_
#define TAVLCALENDARIOH_

class TNodoAVL;

class TAVLCalendario
{

private:
  // Puntero al nodo raíz
  TNodoAVL *raiz;
  // Devuelve el recorrido en inorden
	void InordenAux(TVectorCalendario &v, int &pos)const; 
  // Devuelve el recorrido en preorden
	void PreordenAux(TVectorCalendario &v, int &pos)const;
  // Devuelve el recorrido en postorden
	void PostordenAux(TVectorCalendario &v, int &pos)const;
  
  // Auxiliares del equilibrador
  void DD();
  void DI();
  void ID();
  void II();
  //equilibrar FE 
  bool Equilibrador();
  //Actualiza todos los FE y devuelve el FE del arbol
  int Fe();

public:
  
// Constructor por defecto 
  TAVLCalendario();
// Constructor de copia 
  TAVLCalendario(const TAVLCalendario &avl);
// Destructor
  ~TAVLCalendario();
// Sobrecarga del operador asignación 
  TAVLCalendario& operator=(const TAVLCalendario &avl);

//Metodos
// Sobrecarga del operador igualdad 
  bool operator==(const TAVLCalendario &avl)const;
// Sobrecarga del operador desigualdad 
  bool operator!=(const TAVLCalendario &avl)const;
// Devuelve TRUE si el árbol está vacío, FALSE en caso contrario
  bool EsVacio()const;
// Inserta el elemento en el árbol 
  bool Insertar(const TCalendario &cal);
// Devuelve true si el elemento está en el árbol, false en caso contrario 
  bool Buscar(const TCalendario &cal)const;
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
// Borra un TCalendario del árbol AVL 
  bool Borrar(const TCalendario &cal);
// Devuelve el elemento TCalendario raíz del árbol AVL 
  TCalendario Raiz()const;
  //limpiador clear
  void Clear();
// Sobrecarga del operador salida
friend std::ostream& operator<<(std::ostream &os, const TAVLCalendario &avl){ return os << avl.Inorden(); }
  
};

class TNodoAVL
{
friend class TAVLCalendario;
  
private:
	TCalendario item;
	TAVLCalendario iz, de;
	int fe;

public:
  
//Constructor por defecto 
  TNodoAVL ();
//Constructor copia 
  TNodoAVL (const TNodoAVL &nodo);
//Destructor
~TNodoAVL ();
// Sobrecarga del operador asignación 
  TNodoAVL & operator=(const TNodoAVL &nodo);
  void Clear();
  

};



#endif //TAVLCALENDARIO_H_
