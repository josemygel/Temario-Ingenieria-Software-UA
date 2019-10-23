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
echo ""


#!/bin/bash

fallos=0;
aciertos=0;

echo "====VECTOR===="


for tad in `ls tads/vector`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/vector/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/vector/${tad}.sal
 
  comp=$(diff -b salida/vector/${tad}.sal salida_ok/vector/${tad}.sal)
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

for tad in `ls tads/vector_`  
do
	echo "Probando [ $tad  ] ..." 
  
  g++ -Wall --no-warnings tads/vector_/${tad} ${dir}/lib/tcalendario.cpp ${dir}/lib/tvectorcalendario.cpp ${dir}/lib/tlistacalendario.cpp -I./${dir}/include -o main 
  ./main > salida/vector_/${tad}.sal
 
  comp=$(diff -b salida/vector_/${tad}.sal salida_ok/vector_/${tad}.sal)
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

echo "====VECTOR====";
echo "ACIERTOS: $aciertos";
echo "FALLOS: $fallos";
echo ""




#!/bin/bash

fallos=0;
aciertos=0;

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
echo ""



#!/bin/bash

fallos=0;
aciertos=0;

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
echo ""


#!/bin/bash

fallos=0;
aciertos=0;

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
echo ""

