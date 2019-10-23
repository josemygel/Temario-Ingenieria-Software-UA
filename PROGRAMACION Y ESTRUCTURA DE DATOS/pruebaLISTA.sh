#!/bin/bash

fallos=0;
aciertos=0;

dir="./cuad3"

echo "====LISTA===="

for tad in `ls tads/lista`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/lista/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/lista/${tad}.sal
 
  comp=$(diff -b salida/lista/${tad}.sal salida_ok/lista/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "=>ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "=>OK"
    let aciertos++;
	fi

	echo ""
done

for tad in `ls tads/lista_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/lista_/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/lista_/${tad}.sal
 
  comp=$(diff -b salida/lista_/${tad}.sal salida_ok/lista_/${tad}.sal)
  if [ "$comp" != "" ]; then
    echo "=>ERROR: "
    echo $comp;
    let fallos++;
  else
    echo "=>OK"
    let aciertos++;
	fi

	echo ""
done

echo "====LISTA====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
