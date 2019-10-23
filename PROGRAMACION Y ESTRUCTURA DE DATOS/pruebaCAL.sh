#!/bin/bash

fallos=0;
aciertos=0;

dir="./cuad3"

echo "====TCALENDARIO===="

for tad in `ls tads/calendario`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/calendario/${tad} ${dir}/lib/tcalendario.cpp  -I./${dir}/include -o main 
  ./main > salida/calendario/${tad}.sal
 
  comp=$(diff -b salida/calendario/${tad}.sal salida_ok/calendario/${tad}.sal)
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

for tad in `ls tads/calendario_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/calendario_/${tad} ${dir}/lib/tcalendario.cpp  -I./${dir}/include -o main 
  ./main > salida/calendario_/${tad}.sal
 
  comp=$(diff -b salida/calendario_/${tad}.sal salida_ok/calendario_/${tad}.sal)
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

echo "====CALENDARIO====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
