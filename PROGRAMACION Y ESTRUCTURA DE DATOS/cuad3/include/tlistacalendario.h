#include "tcalendario.h"

#ifndef TLISTACALENDARIO_H_
#define TLISTACALENDARIO_H_



//Prototipo de la Clase “TNodoCalendario”:
class TNodoCalendario{
    friend class TListaPos;
    friend class TListaCalendario;

    private:
        TCalendario c; // Uso de LAYERING sobre la clase (AMISTAD)
        TNodoCalendario *siguiente;

    public:
    // Constructor por defecto
    TNodoCalendario():c(){}
    // Constructor de copia
    TNodoCalendario(const TNodoCalendario &nodo);
    // Destructor
    ~TNodoCalendario();
    // Sobrecarga del operador asignación
    TNodoCalendario& operator=(const TNodoCalendario &nodo);
};


//Prototipo de la Clase “TListaPos”:
class TListaPos{
    friend class TListaCalendario;

    private:
    // Para implementar la POSICIÓN a NODO de la LISTA de TCalendario
    TNodoCalendario *pos;

    public:
    //FORMA CANÓNICA
    // Constructor por defecto
    TListaPos(){pos=NULL;}
    // Constructor de copia
    TListaPos(const TListaPos &list);
    // Destructor
    ~TListaPos();
    // Sobrecarga del operador asignación
    TListaPos& operator=(const TListaPos &list);

    //MÉTODOS (TODOS IN_LINE)
    // Sobrecarga del operador igualdad (POSIBLES PROBLEMAS CON VALORES NULOS O BUCLE INFINITO)
    bool operator==(const TListaPos &lista) const;
    // Sobrecarga del operador desigualdad
    bool operator!=(const TListaPos &lista) const { return !operator==(lista); }
    // Devuelve la posición siguiente
    TListaPos Siguiente()const;
    // Posición vacía
    bool EsVacia()const{ return pos==NULL; }
};

//Prototipo de la Clase “TListaCalendario”:
class TListaCalendario{
    private:
    // Primer item de la lista
    TNodoCalendario *primero;	
    // APOYO PARA IMPRIMIR
	std::ostream& imprimirTodos(std::ostream &os)const;

    public:
    //FORMA CANÓNICA
    // Constructor por defecto
    TListaCalendario(){ primero=NULL; }
    // Constructor de copia
    TListaCalendario(const TListaCalendario &list);
    //Destructor
    ~TListaCalendario();
    // Sobrecarga del operador asignación
    TListaCalendario& operator=(const TListaCalendario &list);

    //MÉTODOS
    // Sobrecarga del operador igualdad
    bool operator==(const TListaCalendario &list)const;
    //Sobrecarga del operador suma
    TListaCalendario operator+ (const TListaCalendario &list)const;
    //Sobrecarga del operador resta
    TListaCalendario operator- (const TListaCalendario &list)const;

    //TListaPos NO VACIO
    // Inserta el elemento en la posición que le corresponda dentro de la lista
    bool Insertar(const TCalendario &cal);
    // Busca y borra el elemento
    bool Borrar(const TCalendario &cal);
    // Borra el elemento que ocupa la posición indicada
    bool Borrar (const TListaPos &pos);
    //Borra todos los Calendarios con fecha ANTERIOR a la pasada.
    bool Borrar(int d,int m,int a);
    // Devuelve true si la lista está vacía, false en caso contrario
    bool EsVacia() const { return primero==NULL; }
    // Obtiene el elemento que ocupa la posición indicada
    TCalendario Obtener(const TListaPos &pos);

    // Devuelve true si el Calendario está en la lista.
    bool Buscar(const TCalendario &cal)const;
    // Devuelve la longitud de la lista
    int Longitud()const;
    // Devuelve la primera posición en la lista
    TListaPos Primera()const;
    // Devuelve la última posición en la lista
    TListaPos Ultima()const;
    // Suma de dos sublistas en una nueva lista
    TListaCalendario SumarSubl(int I_L1, int F_L1, TListaCalendario& L2, int I_L2, int F_L2)const;
    // Extraer un rango de nodos de la lista
    TListaCalendario ExtraerRango(int n1, int n2);
    //FUNCIONES AMIGAS
    //Sobrecarga del operador salida
    friend std::ostream& operator<<(std::ostream &os,const TListaCalendario &list);
};

#endif /*TLISTACALENDARIO_H_*/