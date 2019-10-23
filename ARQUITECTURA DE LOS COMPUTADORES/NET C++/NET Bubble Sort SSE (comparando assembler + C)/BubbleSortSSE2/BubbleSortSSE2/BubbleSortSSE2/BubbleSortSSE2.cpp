// Copyright 2008 Rory Plaire (codekaizen@gmail.com)

// BubbleSortSSE2.cpp : main project file.

#include "stdafx.h"

using namespace System;
using namespace System::Diagnostics;

void runDoubleTests(int itemCount, Random^ random);
void sortDoubleArraySSE(int itemCount, Random^ random);
void sortDoubleArrayC(int itemCount, Random^ random);
void sortDoublesSSE(int byteCount, double* values);
void sortDoublesC(int itemCount, double* values);

int main(array<System::String ^> ^args)
{
	int MaxItems = 66537;
	int ItemCount = 2;
	int Rate = 2;
	Random^ _random = gcnew Random();

    Int32 items = ItemCount;

	Console::WriteLine("");
    items = ItemCount;
    while (items < MaxItems)
    {
		Console::WriteLine(String::Format("Bubble sorting {0} double float items.", items));
        runDoubleTests(items, _random);
        items *= Rate;
    }

    Console::WriteLine("Done.");
    Console::ReadKey();
	return 0;
}

void runDoubleTests(int itemCount, Random^ random)
{
    Stopwatch^ timer = gcnew Stopwatch();
	double cms, ssems;

	timer->Reset();
    timer->Start();
    sortDoubleArraySSE(itemCount, random);
    timer->Stop();

	Console::WriteLine("\tSorting array SSE: " + timer->ElapsedMilliseconds + " ms.");
	ssems = timer->ElapsedMilliseconds;

	timer->Reset();
    timer->Start();
    sortDoubleArrayC(itemCount, random);
    timer->Stop();
	
	Console::WriteLine("\tSorting array C: " + timer->ElapsedMilliseconds + " ms.");
	cms = timer->ElapsedMilliseconds;

	Console::WriteLine("\tGanancia SSE/C: " + ( ( cms / ssems ) * 100 - 100 ) + "%" );

}

void sortDoubleArrayC(int itemCount, Random^ random)
{
	Double* values = new Double[ itemCount ];

    for (int i = 0; i < itemCount; i++)
    {
        values[i] = random->NextDouble();
    }

	sortDoublesC(itemCount, values);

	free( values );
}


void sortDoublesC(int itemCount, double* values)
{
	int i, j;
	double aux;

	for ( i = 0; i < itemCount - 1; i++ )
		for ( j = i + 1; j < itemCount; j++ ){
			if ( values[ j ] < values[ i ] ){
				aux = values[ i ];
				values[ i ] = values[ j ];
				values[ j ] = aux;
			}
		}
}

void sortDoubleArraySSE(int itemCount, Random^ random)
{
	int byteCount = sizeof(Double) * itemCount;

	Double* values = (Double*) _aligned_malloc(byteCount, 16);

    for (int i = 0; i < itemCount; i++)
    {
        values[i] = random->NextDouble();
    }

	sortDoublesSSE(byteCount, values);

	_aligned_free(values);
}

#pragma unmanaged
#pragma warning(push)
#pragma warning(disable:4018 4102)

void sortDoublesSSE(Int32 byteCount, double* values)
{
#pragma region SSE2 Bubble Sort
	_asm
	{
		// save state
		pusha;
begin:
		mov  esi, values;		// get pointer to data

		// init loop counters to itemCount
		//	ECX: outer loop counter
		//	EDX: inner loop counter
		mov  ecx, 0;

outer_loop:
		// check if counter is 0; end sorting if true
		cmp  ecx, byteCount;
		je   end_outer;

		// reset inner loop counter
		mov  edx, byteCount;
		sub	 edx, ecx;

		// setup data indexer
		mov ebx, 0

inner_loop:
		cmp	edx, 16;
		je  end_inner;

body:
		// load xmm registers with data to sort
		movapd xmm0, [esi+ebx];
		movapd xmm1, xmm0;

		movapd xmm2, [esi+ebx+16];
		movapd xmm3, xmm2;
		
		// reverse values to compare
		shufpd xmm1, xmm1, 1;
		shufpd xmm3, xmm3, 1;

		// save value for use in max compare
		movapd xmm4, xmm0;

		// push the minimum values down into xmm[0-1]
		minpd xmm0, xmm2;
		minpd xmm1, xmm2;

		// push the maximum values up into xmm[2-3]
		maxpd xmm2, xmm0;
		maxpd xmm3, xmm0;
		maxpd xmm2, xmm1;
		maxpd xmm3, xmm1;
		maxpd xmm2, xmm4;
		maxpd xmm3, xmm4;

		// order the doubles in xmm[0-1]
order_min0:
		movapd xmm4, xmm0;
		shufpd xmm4, xmm4, 1;
		cmpltpd xmm4, xmm0;
		movmskpd eax, xmm4;
		cmp eax, 2;
		je order_min1;
		shufpd xmm0, xmm0, 1;
order_min1:
		movapd xmm4, xmm1;
		shufpd xmm4, xmm4, 1;
		cmpltpd xmm4, xmm1;
		movmskpd eax, xmm4;
		cmp eax, 2;
		je finish_min;
		shufpd xmm1, xmm1, 1;
finish_min:
		minpd xmm0, xmm1;

		// order the doubles in xmm[2-3]
order_max0:
		movapd xmm4, xmm2;
		shufpd xmm4, xmm4, 1;
		cmpltpd xmm4, xmm2;
		movmskpd eax, xmm4;
		cmp eax, 2;
		je order_max1;
		shufpd xmm2, xmm2, 1;
order_max1:
		movapd xmm4, xmm3;
		shufpd xmm4, xmm4, 1;
		cmpltpd xmm4, xmm3;
		movmskpd eax, xmm4;
		cmp eax, 2;
		je finish_max;
		shufpd xmm3, xmm3, 1;
finish_max:
		maxpd xmm2, xmm3;

		// Save the sorted registers back to memory
		movapd [esi+ebx], xmm0;
		movapd [esi+ebx+16], xmm2;

loop_inner:
		sub edx, 16;
		add ebx, 16;
		jmp inner_loop;

end_inner:
		// decrement outer loop counter by 16 for the byte count of the two doubles
		// moved to the end of the array
		add  ecx, 16;
		jmp  outer_loop;

end_outer:
		jmp finish;
finish:
		// restore
		popa;
	}
#pragma endregion
}
#pragma managed