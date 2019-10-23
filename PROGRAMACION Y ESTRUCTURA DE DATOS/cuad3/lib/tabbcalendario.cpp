#include "tabbcalendario.h"

/** TABBCALENDARIO */

//private
// AUXILIAR : devuelve el recorrido por niveles
void
TABBCalendario::NivelesAux(TVectorCalendario &vec, int n)const {
    if(!EsVacio() && n>=1){
        int pos = vec.Ocupadas()+1;
        if(n==1){
            vec[pos]=raiz->item;
        }else if(n>1){
            if(!raiz->iz.EsVacio())
                raiz->iz.NivelesAux(vec, n-1);
                
            if(!raiz->de.EsVacio())
                raiz->de.NivelesAux(vec, n-1);
        }
    }
}
// AUXILIAR : devuelve el recorrido en inorden
void 
TABBCalendario::InordenAux(TVectorCalendario &vec, int &n)const {
    //Si esta vacio se procede a insertar item (Vale para los hijos)
    if(!EsVacio()){
        //Insertamos el hijo izquierda
        raiz->iz.InordenAux(vec,n);
        //Insertamos el item
        vec[n++]=raiz->item;
        //Insertamos el hijo derecha
        raiz->de.InordenAux(vec,n);
    }
}
// AUXILIAR : devuelve el recorrido en preorden
void 
TABBCalendario::PreordenAux(TVectorCalendario &vec, int &n)const {
    //Si esta vacio se procede a insertar item (Vale para los hijos)
    if(!EsVacio()){
        //Insertamos el item
        vec[n++]=raiz->item;
        //Insertamos el hijo izquierda
        raiz->iz.PreordenAux(vec,n);
        //Insertamos el hijo derecha
        raiz->de.PreordenAux(vec,n);
    }
}
// AUXILIAR : devuelve el recorrido en postorden
void 
TABBCalendario::PostordenAux(TVectorCalendario &vec, int &n)const {
    //Si esta vacio se procede a insertar item (Vale para los hijos)
    if(!EsVacio()){
        //Insertamos el hijo izquierda
        raiz->iz.PostordenAux(vec,n);
        //Insertamos el hijo derecha
        raiz->de.PostordenAux(vec,n);
        //Insertamos el item
        vec[n++]=raiz->item;
    }
}

//public
// Constructor por defecto
TABBCalendario::TABBCalendario(){
    raiz=NULL;
}
// Constructor de copia
TABBCalendario::TABBCalendario(const TABBCalendario &abb){
    if(&abb!=NULL && abb.raiz != NULL)
        raiz=new TNodoABB(*(abb.raiz));
    else
        raiz=NULL;
}
// Destructor
TABBCalendario::~TABBCalendario(){
    delete raiz;
}
// Sobrecarga del operador asignación
TABBCalendario& 
TABBCalendario::operator=(const TABBCalendario &abb){
    
    if(&abb!=NULL && abb.raiz != NULL)
        raiz=new TNodoABB(*(abb.raiz));
    else
        raiz=NULL;
}

//MÉTODOS
// Sobrecarga del operador igualdad
bool 
TABBCalendario::operator==(const TABBCalendario &abb)const {
    TVectorCalendario a = Inorden();
    TVectorCalendario b = abb.Inorden();

    if(a.Tamano() != b.Tamano())
        return false; //No son iguales por que no tienen el mismo número de calendarios

    for(int i=1; i<=Nodos(); i++){
        if(!a.ExisteCal(b[i]))
            return false; //No son iguales por que no existe un cal en el otro
    }

    return true; //Son iguales
}
// Devuelve TRUE si el árbol está vacío, FALSE en caso contrario
bool 
TABBCalendario::EsVacio()const {
    return raiz==NULL ? true : Raiz().EsVacio();
}
// Inserta el elemento en el árbol
bool 
TABBCalendario::Insertar(const TCalendario &cal){
    TNodoABB *aux = new TNodoABB();

    if(Buscar(cal)) 
        return false;
    
    if(EsVacio()){
        raiz=aux;
        raiz->item=cal;

        return true;
    }else{
        return (cal < Raiz()) ? raiz->iz.Insertar(cal) : raiz->de.Insertar(cal);
    }
    
}
// Borra el elemento en el árbol
bool 
TABBCalendario::Borrar(const TCalendario &cal){

    if(EsVacio() || !Buscar(cal)) 
        return false;

    if(Raiz()==cal){

        if(!raiz->iz.EsVacio()){ //CAMBIAR POR MAYOR ARBOL IZQUIERDO
            raiz->item=raiz->iz.Inorden()[raiz->iz.Nodos()];
            raiz->iz.Borrar(Raiz());

        }else if(!raiz->de.EsVacio()){ //SI NO PONER EL ARBOL DERECHO COMO RAIZ
            raiz=raiz->de.raiz;
        }else{
            raiz=NULL;
        }

        return true;
    }else{
        return (cal < Raiz()) ? raiz->iz.Borrar(cal) : raiz->de.Borrar(cal);
    }
}

//INLINE
// Devuelve TRUE si el elemento está en el árbol, FALSE en caso contrario
bool TABBCalendario::Buscar(const TCalendario &cal)const {
    if(EsVacio())
        return false; 
        
    return Raiz()==cal ? true : (raiz->iz.Buscar(cal) || raiz->de.Buscar(cal));
}

// Devuelve el elemento de la raíz del árbol
TCalendario
TABBCalendario::Raiz()const{
    TCalendario aux;
    return raiz==NULL ? aux : raiz->item;
}
// Devuelve la 3ra del árbol (la altura de un árbol vacío es 0)
int 
TABBCalendario::Altura()const {
    if(EsVacio())
        return 0;
    
    return (raiz->iz.EsVacio() && raiz->de.EsVacio()) ? 1 : 1+std::max(raiz->iz.Altura(),raiz->de.Altura());
}
// Devuelve el número de nodos del árbol (un árbol vacío posee 0 nodos)
int 
TABBCalendario::Nodos()const {
    return EsVacio() ? 0 : 1+raiz->iz.Nodos()+raiz->de.Nodos();
}
// Devuelve el número de nodos hoja en el árbol (la raíz puede ser nodo hoja)
int 
TABBCalendario::NodosHoja()const {
    if(EsVacio()) 
        return 0;
    return raiz->iz.EsVacio() && raiz->de.EsVacio() ? 1 : raiz->iz.NodosHoja() + raiz->de.NodosHoja();
}

// Devuelve el recorrido en inorden sobre un vector
TVectorCalendario 
TABBCalendario::Inorden()const {
    int posicion = 1;

    // Vector del tamaño adecuado para almacenar todos los nodos
    TVectorCalendario v(Nodos());
    InordenAux(v, posicion);
    return v;
}
// Devuelve el recorrido en preorden sobre un vector
TVectorCalendario 
TABBCalendario::Preorden()const {
    int posicion = 1;
    
    // Vector del tamaño adecuado para almacenar todos los nodos
    TVectorCalendario v(Nodos());
    PreordenAux(v, posicion);
    return v;
}
// Devuelve el recorrido en postorden sobre un vector
TVectorCalendario 
TABBCalendario::Postorden()const {
    int posicion = 1;

    // Vector del tamaño adecuado para almacenar todos los nodos
    TVectorCalendario v(Nodos());
    PostordenAux(v, posicion);
    return v;
}


// Devuelve el recorrido en niveles
TVectorCalendario 
TABBCalendario::Niveles()const {
    int cant = Nodos();
    int altura = Altura();
    TVectorCalendario v(Nodos());

    for(int nivel=1; nivel <= altura; nivel++){
        NivelesAux(v, nivel);
    }

    return v;
}

// Suma de dos ABB
TABBCalendario 
TABBCalendario::operator+(const TABBCalendario &abb)const {
    TABBCalendario nuevo(*this);
    TVectorCalendario vec;
    
    if(!abb.EsVacio())
        vec = abb.Inorden();

    for(int i=1; i<=abb.Nodos(); i++){
        nuevo.Insertar(vec[i]);
    }

    return nuevo;
}
// Resta de dos ABB
TABBCalendario 
TABBCalendario::operator-(const TABBCalendario &abb)const {
    TABBCalendario nuevo(*this);
    TVectorCalendario vec;
    
    if(!abb.EsVacio())
        vec = abb.Inorden();

    for(int i=1; i <= abb.Nodos(); i++){
        nuevo.Borrar(vec[i]);
    }

    return nuevo;
}

void
TABBCalendario::clear(){
    if(raiz != NULL){
        raiz->clear();
        raiz = NULL;
    }
}
/** TNODOABB */

//Constructor por defectoINLINE
//TNodoABB::TNodoABB():item(),iz(),de(){}
//Constructor copiaINLINE
//TNodoABB::TNodoABB(const TNodoABB &nodo):item(nodo.item),iz(nodo.iz),de(nodo.de){}

//Destructor
TNodoABB::~TNodoABB(){
    TCalendario aux;
    item = aux;
    iz.clear();
    de.clear();
}
// Sobrecarga del operador asignación
TNodoABB& 
TNodoABB::operator=(const TNodoABB &nodo){
    clear();

    if(&nodo != NULL){
        item = nodo.item;
        iz = nodo.iz;
        de = nodo.de;
    }

    return *this;
}

void
TNodoABB::clear(){
    item.~TCalendario();
    iz.~TABBCalendario();
    de.~TABBCalendario();
}