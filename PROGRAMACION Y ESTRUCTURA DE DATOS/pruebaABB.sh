#!/bin/bash

fallos=0;
aciertos=0;

dir="./cuad3"

echo "====ABB===="

for tad in `ls tads/abb`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/abb/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp ${dir}/lib/tabbcalendario.cpp -I./${dir}/include -o main 
  ./main > salida/abb/${tad}.sal
 
  comp=$(diff -b salida/abb/${tad}.sal salida_ok/abb/${tad}.sal)
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

for tad in `ls tads/abb_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/abb_/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp ${dir}/lib/tabbcalendario.cpp -I./${dir}/include -o main 
  ./main > salida/abb_/${tad}.sal
 
  comp=$(diff -b salida/abb_/${tad}.sal salida_ok/abb_/${tad}.sal)
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

echo "====ABB====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
