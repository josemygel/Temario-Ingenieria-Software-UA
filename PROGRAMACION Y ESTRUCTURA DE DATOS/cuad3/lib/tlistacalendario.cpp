#include "tlistacalendario.h"


//Prototipo de la Clase “TNodoCalendario”:
// Constructor por defecto IN_LINE
//TNodoCalendario():c(){}

TNodoCalendario::TNodoCalendario(const TNodoCalendario &nodo):c(nodo.c){
    siguiente = NULL;
}

// Destructor
TNodoCalendario::~TNodoCalendario(){
    c.~TCalendario();
    siguiente = NULL;
}
// Sobrecarga del operador asignación
TNodoCalendario& 
TNodoCalendario::operator=(const TNodoCalendario &nodo){
    c=nodo.c;
    siguiente=nodo.siguiente;
}


//Prototipo de la Clase “TListaPos”:
//FORMA CANÓNICA
// Constructor por defecto IN_LINE
//TListaPos(){pos=NULL;}
// Constructor de copia
TListaPos::TListaPos(const TListaPos &list){
    pos=new TNodoCalendario(*list.pos);
}
// Destructor
TListaPos::~TListaPos(){
    pos=NULL;
}
// Sobrecarga del operador asignación
TListaPos& 
TListaPos::operator=(const TListaPos &list){
    pos=list.pos;
    return *this;
}

//MÉTODOS
// Sobrecarga del operador igualdad
bool 
TListaPos::operator==(const TListaPos &posi)const{
    while(!EsVacia() && !posi.EsVacia()){
        if(EsVacia())
            return false;
            
        if(posi.EsVacia())
            return false;

        if(pos->c == posi.pos->c){
            return Siguiente()==posi.Siguiente();
        }else{
            return false;
        }

    }
    return true;
}

// Sobrecarga del operador desigualdad IN_LINE
//bool operator!=(const TListaPos &pos)const{return operator==(pos);}

// Devuelve la posición siguiente
TListaPos 
TListaPos::Siguiente() const { 
    TListaPos *aux = new TListaPos();
    
    if (pos==NULL)
        return *aux;
    else{
        aux->pos=pos->siguiente; 
    }

    return *aux;
}

// Posición vacía IN_LINE
//bool EsVacia()const{ return pos==NULL? true:false; }


//Prototipo de la Clase “TListaCalendario”:

//FORMA CANÓNICA

//Constructor de copia
TListaCalendario::TListaCalendario(const TListaCalendario &list){
    TNodoCalendario *aux = list.primero;
    primero = NULL;

    while(aux != NULL){
        Insertar(aux->c);
        aux = aux->siguiente;
    }
}

//Destructor
TListaCalendario::~TListaCalendario(){
    
    TNodoCalendario *temp;
    TNodoCalendario *aux = primero;
    
    while(aux!=NULL){
        temp=aux;
        aux=aux->siguiente;
        delete temp;
    }
    primero = NULL;
}
// Sobrecarga del operador asignación
TListaCalendario& 
TListaCalendario::operator=(const TListaCalendario &list){
    TNodoCalendario *aux = list.primero;
    primero = NULL;
    
    if(this != &list){
        while(aux != NULL){
            Insertar(aux->c);
            aux = aux->siguiente;
        }
    }

    return *this;
}
//MÉTODOS
// Sobrecarga del operador igualdad
bool TListaCalendario::operator==(const TListaCalendario &list)const{
    TListaPos pos1=Primera();
    TListaPos pos2=list.Primera();

    while(!pos1.EsVacia() && !pos2.EsVacia()){

        if(pos2.EsVacia())
            return false;
        if(pos1.EsVacia())
            return false;

        if(pos1 != pos2)
            return false;

        pos1 = pos1.Siguiente();
        pos2 = pos2.Siguiente();
    }

    return true;
}
//Sobrecarga del operador suma
TListaCalendario 
TListaCalendario::operator+ (const TListaCalendario &list)const{
    TListaCalendario calAux(*this);
    TNodoCalendario *nodoAux=list.primero;

    while(nodoAux!=NULL){
        calAux.Insertar(nodoAux->c);
        nodoAux=nodoAux->siguiente;
    }

    return calAux;
    
}
//Sobrecarga del operador resta
TListaCalendario 
TListaCalendario::operator- (const TListaCalendario &list)const{
    TListaCalendario calAux(*this);
    TNodoCalendario *nodoAux=list.primero;

    while(nodoAux!=NULL){
        calAux.Borrar(nodoAux->c);
        nodoAux=nodoAux->siguiente;
    }

    return calAux;
}
// Inserta el elemento en la posición que le corresponda dentro de la lista
bool 
TListaCalendario::Insertar(const TCalendario &cal){
    TNodoCalendario *aux = primero;
    TNodoCalendario *nuevo = new TNodoCalendario();
    bool insertado=false;

    if(Buscar(cal))
        return false;

    if(primero==NULL){
        primero=new TNodoCalendario();
        primero->c=cal;
        return true;

    }else if(primero->siguiente==NULL){
        if(aux->c > cal){
            nuevo->c=cal;
            nuevo->siguiente=primero;
            primero=nuevo;
            insertado=true;

        }else{
            nuevo->c=cal;
            primero->siguiente=nuevo;
            insertado=true;
        }
    }else if(!insertado && primero->c > cal){
        nuevo->c=cal;
        nuevo->siguiente = primero;
        primero=nuevo;
        return true;
    }

    while(!insertado && aux != NULL){
        if(aux->c < cal && (aux->siguiente == NULL || aux->siguiente->c > cal)){
            nuevo->c=cal;
            nuevo->siguiente=aux->siguiente;
            aux->siguiente = nuevo;
            insertado=true;
            return insertado;
        }

        aux = aux->siguiente;
    }



    return insertado;
}

// Busca y borra el elemento
bool 
TListaCalendario::Borrar(const TCalendario &cal){
    TNodoCalendario *aux=primero;

    if(!Buscar(cal)) {
        return false;
    }else{
        if(primero->c==cal){
            primero = primero->siguiente;
            return true;
        }
        while(aux->siguiente != NULL){
            if(aux->siguiente->c == cal){
                aux->siguiente = aux->siguiente->siguiente;
                return true;
            }
            aux = aux->siguiente;
        }
    }

    return false;
}

// Borra el elemento que ocupa la posición indicada
bool 
TListaCalendario::Borrar(const TListaPos &pos){
    return Borrar(pos.pos->c);
}

//Borra todos los Calendarios con fecha ANTERIOR a la pasada.
bool 
TListaCalendario::Borrar(int d,int m,int a){
    TCalendario aux(d,m,a,NULL);
    TNodoCalendario *pos=primero;
    TNodoCalendario *temp;
    bool borrado = false;

    if(Ultima().pos->c < aux){
        primero=NULL;
        return true;
    }

    while(pos != NULL && pos->c < aux)
    {
        borrado = true;
        pos = pos->siguiente;
        primero = pos;

    }

    return borrado;
}
// Devuelve true si la lista está vacía, false en caso contrario IN_LINE
//bool TListaCalendario::EsVacia() const {return primero.EsVacia()}

// Obtiene el elemento que ocupa la posición indicada
TCalendario 
TListaCalendario::Obtener(const TListaPos &pos){
    return Buscar(pos.pos->c) ? pos.pos->c : TCalendario();
}
// Devuelve true si el Calendario está en la lista.
bool 
TListaCalendario::Buscar(const TCalendario &cal)const{
    TNodoCalendario *aux=primero;
    
    while (aux != NULL){
        if (aux->c == cal)
            return true;
        aux=aux->siguiente;
    }

    return false;
}
// Devuelve la longitud de la lista
int 
TListaCalendario::Longitud()const{
    int l=0;
    TNodoCalendario *aux=primero;

    while(aux!=NULL){
        aux=aux->siguiente;
        l++;
    }
    
    return l;
}
// Devuelve la primera posición en la lista
TListaPos 
TListaCalendario::Primera()const{
    TListaPos pos;
    if (primero == NULL) {
        return pos;
    }else{
        pos.pos = primero;
        return pos;
    }
}
// Devuelve la última posición en la lista
TListaPos 
TListaCalendario::Ultima()const{
    TListaPos aux;
    aux.pos = primero;
    
    while(aux.pos!=NULL && aux.pos->siguiente!=NULL){
        aux.pos=aux.pos->siguiente;
    }

    return aux;
}
// Suma de dos sublistas en una nueva lista
TListaCalendario 
TListaCalendario::SumarSubl(int I_L1, int F_L1, TListaCalendario &L2, int I_L2, int F_L2)const{
    TListaCalendario *Lista1 = new TListaCalendario(*this);
    TListaCalendario *Lista2 = new TListaCalendario(L2);

    //Preparamos las listas extrayendoles los elementos que no nos interesan
    Lista1 = new TListaCalendario(Lista1->ExtraerRango(I_L1, F_L1));
    Lista2 = new TListaCalendario(Lista2->ExtraerRango(I_L2, F_L2));


    return (*Lista1)+(*Lista2);
}
// Extraer un rango de nodos de la lista
TListaCalendario 
TListaCalendario::ExtraerRango(int n1, int n2){
    TListaCalendario *nueva = new TListaCalendario();
    TListaPos aux;
    
    if (n1 <= 0) n1=1;
    if (n2 > Longitud()) n2=Longitud();

    for (int i=0; i <= n2-n1; i++){
        aux.pos = primero;
        
        for(int j=1; j < n1; j++)
            aux.pos = aux.pos->siguiente;
        
        nueva->Insertar(aux.pos->c);
        Borrar(aux.pos->c);
    }

    return nueva->EsVacia() ? TListaCalendario() : *nueva;
    
}
//class TListaCom
	// PRIVATE METHOD
	// APOYO PARA IMPRIMIR
	std::ostream&
	TListaCalendario::imprimirTodos(std::ostream &os)const{
		TNodoCalendario *aux;

		aux = primero;

		while(aux!=NULL){
			os << aux->c;
			aux=aux->siguiente;
			if(aux!=NULL)
				os<<" ";
		}

        return os;
	}

//FUNCIONES AMIGAS
//Sobrecarga del operador salida
std::ostream& operator<<(std::ostream &os,const TListaCalendario &list){
    os << "<";
    if(list.Longitud() > 0){
        list.imprimirTodos(os);
    }
    os << ">";
}