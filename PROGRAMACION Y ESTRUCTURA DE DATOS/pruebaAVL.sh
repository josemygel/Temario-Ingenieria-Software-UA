#!/bin/bash

fallos=0;
aciertos=0;

dir="./cuad3"

echo "====AVL===="

for tad in `ls tads/avl`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/avl/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp ${dir}/lib/tavlcalendario.cpp -I./${dir}/include -o main 
  ./main > salida/avl/${tad}.sal
 
  comp=$(diff -b salida/avl/${tad}.sal salida_ok/avl/${tad}.sal)
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

for tad in `ls tads/avl_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/avl_/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp ${dir}/lib/tavlcalendario.cpp -I./${dir}/include -o main 
  ./main > salida/avl_/${tad}.sal
 
  comp=$(diff -b salida/avl_/${tad}.sal salida_ok/avl_/${tad}.sal)
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

echo "====AVL====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
