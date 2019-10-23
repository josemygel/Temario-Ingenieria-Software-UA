#include "stdafx.h"
#include <iostream>
using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{
	int n, m, Result;
	char stop;
	// How to perform an addition on two integers
	// Result = n + m
	cout << "Introduzca primer numero:"; cin >> n;
	cout << "Introduzca segundo numero:"; cin >> m;

	__asm
	{
		MOV EAX, n	// Put the value of n in 32-bit register EAX
		MOV EBX, m	// Put the value of m in 32-bit register EBX
		ADD EAX, EBX	// Add the contents of EBX to the contents of EAX and store the result in register EAX
		MOV Result, EAX	// Retrieve the contents of EAX and put it in Result
	}

	cout << "n = " << n << endl;
	cout << "m = " << m << endl;
	cout << "Result: " << Result << "\n\n";

	cin >> stop;
	return 0;
}

