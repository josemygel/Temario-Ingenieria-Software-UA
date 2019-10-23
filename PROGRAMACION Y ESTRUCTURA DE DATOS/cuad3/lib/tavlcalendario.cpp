#include "tavlcalendario.h"

/** CLASS TAVLCALENDARIO */

// Constructor por defecto 
TAVLCalendario::TAVLCalendario (){
  raiz=NULL;
}
// Constructor de copia 
TAVLCalendario::TAVLCalendario (const TAVLCalendario &avl){
  raiz=NULL;

  if(&avl!=NULL && !avl.EsVacio() && *this!=avl)
    *this=avl;
}
// Destructor
TAVLCalendario::~TAVLCalendario(){
  raiz=NULL;
}
// Sobrecarga del operador asignación 
TAVLCalendario& 
TAVLCalendario::operator=(const TAVLCalendario &avl){
  if(!avl.EsVacio())
    raiz=new TNodoAVL(*avl.raiz);

  return *this;
}


void
TAVLCalendario::DD(){
  TNodoAVL *aux = new TNodoAVL();
  //Aseguramos que existe rama izquierda a la que bajar la raiz
  if(raiz->iz.EsVacio())
    raiz->iz.raiz = aux;
  
  //Bajamos la raiz y su rama izquierda
  raiz->iz.raiz->iz = raiz->iz;
  raiz->iz.raiz->item = Raiz();

  //Trasladamos la rama izquierda del hijo derecho (si existe) a la rama izquierda
  if(!raiz->de.raiz->iz.EsVacio())
    raiz->iz.raiz->de = raiz->de.raiz->iz;

  //Subimos la rama derecha
  raiz->item = raiz->de.Raiz();
  raiz->de = raiz->de.raiz->de;
}

void
TAVLCalendario::DI(){
  TNodoAVL *aux = new TNodoAVL();
  
  //Aseguramos que existe rama izquierda a la que bajar la raiz
  if(raiz->iz.EsVacio())
    raiz->iz.raiz = aux;
  
  //Bajamos la raiz y su rama izquierda
  raiz->iz.raiz->iz = raiz->iz;
  raiz->iz.raiz->item = Raiz();

  //Trasladamos la rama izquierda del hijo derecho (si existe) a la rama izquierda
  if(!raiz->de.raiz->iz.raiz->iz.EsVacio())
    raiz->iz.raiz->de = raiz->de.raiz->iz.raiz->iz;

  //Subimos la rama derecha
  raiz->item = raiz->de.raiz->iz.Raiz();
  //Si hay una rama que subir se sube, si no se elimina esa rama
  if(raiz->de.raiz->iz.raiz->de.EsVacio())
    raiz->de.raiz->iz.raiz=NULL;
  else 
    raiz->de.raiz->iz = raiz->de.raiz->iz.raiz->de;
}


void
TAVLCalendario::II(){
  TNodoAVL *aux = new TNodoAVL();
  
  //Aseguramos que existe rama derecha a la que bajar la raiz
  if(raiz->de.EsVacio())
    raiz->de.raiz = aux;
  
  //Bajamos la raiz y su rama derecha
  raiz->de.raiz->de = raiz->de;
  raiz->de.raiz->item = Raiz();

  //Trasladamos la rama derecha del hijo izquierda (si existe) a la rama derecha
  if(!raiz->iz.raiz->de.EsVacio())
    raiz->de.raiz->iz = raiz->iz.raiz->de;

  //Subimos la rama izquierda
  raiz->item = raiz->iz.Raiz();
  raiz->iz = raiz->iz.raiz->iz;
}


void
TAVLCalendario::ID(){
  TNodoAVL *aux = new TNodoAVL();
  
  //Aseguramos que existe rama derecha a la que bajar la raiz
  if(raiz->de.EsVacio())
    raiz->de.raiz = aux;
  
  //Bajamos la raiz y su rama derecha
  raiz->de.raiz->de = raiz->de;
  raiz->de.raiz->item = Raiz();

  //Trasladamos la rama derecha del hijo izquierdo (si existe) a la rama derecha
  //if(!raiz->iz.raiz->de.raiz->de.EsVacio())
    raiz->de.raiz->iz = raiz->iz.raiz->de.raiz->de;

  //Subimos la rama izquierda
  raiz->item = raiz->iz.raiz->de.Raiz();
  
  //Si hay una rama que subir se sube, si no se elimina esa rama
  if(raiz->iz.raiz->de.raiz->iz.EsVacio())
    raiz->iz.raiz->de.raiz=NULL;
  else 
    raiz->iz.raiz->de = raiz->iz.raiz->de.raiz->iz;
}

bool
TAVLCalendario::Equilibrador(){
	bool reinicio = false;
  
  if(EsVacio())
		return false;
    
	//ACTUALIZAR FE
  Fe();
  
  //Trabajamos con los hijos por estar ya equilibrado
  //Si se realiza algun trabajo en los hijos se vuelve a realizar Equilibrador
  if(raiz->fe <=1 && raiz->fe >= -1)
  	return (raiz->iz.Equilibrador() || raiz->de.Equilibrador()) ? (Equilibrador() || true) : false;

  //El equilibrio tiende a la derecha
  if(raiz->fe == +2)
    (raiz->de.raiz->fe == +1 || raiz->de.raiz->fe == 0) ? DD() : DI();
  
  //El equilibrio tiende a la izquierda
  else if(raiz->fe == -2){
  	(raiz->iz.raiz->fe == -1 || raiz->iz.raiz->fe == 0) ? II() : ID();
  }

  Equilibrador();
  //Se devuelve TRUE por que se ha trabajado seguro el equilibrio
  return true;
}

int
TAVLCalendario::Fe(){
  int izq=0;
  int der=0;
  
  if(EsVacio() || (raiz->de.EsVacio() && raiz->iz.EsVacio()))
    return 0;
  
  if(!raiz->iz.EsVacio())
  	izq=raiz->iz.Altura();

  if(!raiz->de.EsVacio())
  	der=raiz->de.Altura();
    
  raiz->iz.Fe();
  raiz->de.Fe();
  
  return raiz->fe = der-izq;
}

// Sobrecarga del operador igualdad 
bool 
TAVLCalendario::operator==(const TAVLCalendario &avl)const{
  return Inorden()==avl.Inorden();
}

// Sobrecarga del operador desigualdad 
bool 
TAVLCalendario::operator!=(const TAVLCalendario &avl)const { return !(operator==(avl)); }

// Devuelve el recorrido en inorden
void 
TAVLCalendario::InordenAux(TVectorCalendario &v, int &pos)const{
  if(!EsVacio()){
		raiz->iz.InordenAux(v, pos);
		v[pos]=Raiz();
		pos++;
		raiz->de.InordenAux(v, pos);
	}
}

// Devuelve el recorrido en preorden
void 
TAVLCalendario::PreordenAux(TVectorCalendario &v, int &pos)const{
  if(!EsVacio()){
		v[pos]=Raiz();
		pos++;
		raiz->iz.PreordenAux(v, pos);
		raiz->de.PreordenAux(v, pos);
	}
}

// Devuelve el recorrido en postorden
void 
TAVLCalendario::PostordenAux(TVectorCalendario &v, int &pos)const{
  if(!EsVacio()){
		raiz->iz.PostordenAux(v, pos);
		raiz->de.PostordenAux(v, pos);
		v[pos]=Raiz();
		pos++;
	}
}
// Devuelve TRUE si el árbol está vacío, FALSE en caso contrario
bool 
TAVLCalendario::EsVacio()const{
	return (raiz==NULL || raiz->item == TCalendario()); 
} 

// Inserta el elemento en el árbol 
bool 
TAVLCalendario::Insertar(const TCalendario &cal){
    TNodoAVL *aux = new TNodoAVL();
    bool ins = false;

    if(Buscar(cal)) 
        return ins;
    
    if(EsVacio()){
        raiz = aux;
        raiz->item = cal;
        ins = true;

    }else{
        cal < Raiz() ? ins=raiz->iz.Insertar(cal) : ins=raiz->de.Insertar(cal);
    }
  
    Equilibrador();
    
    return ins;
}

// Borra un TCalendario del árbol AVL 
bool 
TAVLCalendario::Borrar(const TCalendario &cal){
    //ins representa si se inserto el calendario
    bool ins = false;

    if(EsVacio() || !Buscar(cal)) 
        return ins;

    //ITEM ENCONTRADO!
    if(Raiz()==cal){
        if(!raiz->iz.EsVacio()){ //CAMBIAR POR MAYOR ARBOL IZQUIERDO
            raiz->item = raiz->iz.Inorden()[raiz->iz.Nodos()];
            raiz->iz.Borrar(Raiz());

        }else if(!raiz->de.EsVacio()){ //SI NO PONER EL ARBOL DERECHO COMO RAIZ
            raiz=raiz->de.raiz;
        }else{
            raiz = NULL;
        }

        ins = true;
    }else{
        cal < Raiz() ? ins = raiz->iz.Borrar(cal) : ins = raiz->de.Borrar(cal);
    }

    Equilibrador();
    return ins;
}

// Devuelve true si el elemento está en el árbol, false en caso contrario 
bool 
TAVLCalendario::Buscar(const TCalendario &cal)const{
  return Inorden().ExisteCal(cal); 
}

// Devuelve la altura del árbol (la altura de un árbol vacío es 0) 
int 
TAVLCalendario::Altura()const{
    if(EsVacio())
        return 0;
    
    return (raiz->iz.EsVacio() && raiz->de.EsVacio()) ? 1 : 1+std::max(raiz->iz.Altura(),raiz->de.Altura());
}

// Devuelve el número de nodos del árbol (un árbol vacío posee 0 nodos) 
int 
TAVLCalendario::Nodos()const{ 
//Si el nodo está vacio se devuelve 0, si no 1 más la suma de los nodos hijos
  return EsVacio() ? 0 : 1+raiz->iz.Nodos()+raiz->de.Nodos(); 
}

// Devuelve el número de nodos hoja en el árbol (la raíz puede ser nodo hoja) 
int 
TAVLCalendario::NodosHoja()const{
//Si es vacío no es un nodo hoja
  if(EsVacio())
    return 0;

  //Si ambos hijos son vacios, estamos ante un nodo hoja, si no obtenemos la suma de sus hojas
  return (raiz->iz.EsVacio() && raiz->de.EsVacio()) ? 1 : raiz->iz.NodosHoja() + raiz->de.NodosHoja();
}

// Devuelve el recorrido en inorden sobre un vector 
TVectorCalendario 
TAVLCalendario::Inorden()const
{
  TVectorCalendario vc(Nodos());
  int pos=1;

  InordenAux(vc, pos);

  return vc;
}

// Devuelve el recorrido en preorden sobre un vector 
TVectorCalendario 
TAVLCalendario::Preorden()const{
  TVectorCalendario vc(Nodos());
  int pos=1;

  PreordenAux(vc, pos);

  return vc;
}

// Devuelve el recorrido en postorden sobre un vector 
TVectorCalendario 
TAVLCalendario::Postorden()const{
  TVectorCalendario vc(Nodos());
  int pos=1;

  PostordenAux(vc, pos);

  return vc;
}


// Devuelve el elemento TCalendario raíz del árbol AVL
TCalendario 
TAVLCalendario::Raiz()const{
  return EsVacio() ? TCalendario() : raiz->item;
}

//limpiador clear
void 
TAVLCalendario::Clear(){
  if(raiz != NULL){
    raiz->Clear();
    delete raiz;
  }
}
  


/** CLASS TNODOAVL */

//Constructor por defecto 
TNodoAVL::TNodoAVL ():de(),iz(),item()
{
  fe=0;
}
//Constructor copia 
TNodoAVL::TNodoAVL (const TNodoAVL &nodo):de(nodo.de),iz(nodo.iz),item(nodo.item){
  fe=nodo.fe;
}
//Destructor
TNodoAVL::~TNodoAVL ()
{
    fe=0;
    item.~TCalendario();
    if(!iz.EsVacio())
      iz.Clear();
    if(!de.EsVacio())
      de.Clear();
}
// Sobrecarga del operador asignación 
TNodoAVL& 
TNodoAVL::operator=(const TNodoAVL &nodo){
  if(&nodo != NULL){
    item=nodo.item;
    de=nodo.de;
    iz=nodo.iz;
    fe=nodo.fe;
  }
  
  return *this;
}

void 
TNodoAVL::Clear()
{
    fe=0;
    item.~TCalendario();
    if(!iz.EsVacio())
      iz.Clear();
    if(!de.EsVacio())
      de.Clear();
}
