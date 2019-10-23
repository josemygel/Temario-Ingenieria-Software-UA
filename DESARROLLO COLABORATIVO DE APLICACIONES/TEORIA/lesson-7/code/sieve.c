
/**********************************************/
/* gcc -fprofile-arcs -ftest-coverage sieve.c */
/* ./a.out                                    */
/* gcov sieve.c                               */
/*                                            */
/*   File 'sieve.c'                           */
/*   Líneas ejecutadas:100.00% de 16          */
/*   Se crea 'sieve.c.gcov'                   */
/*                                            */
/* cat sieve.c.gcov                           */
/**********************************************/

#include<stdio.h>

int flags[8191];

int main()
{
    int i, prime, k, count, iter;

    printf("10 iterations\n");
    for (iter = 1; iter <= 10; iter++)
    {
        count = 0;
        for (int f = 0; f < 8191; f++)
          flags[f] = 1;
        
        for (i = 0; i < 8191; i++)
        {
            if (flags[i])
            {
                prime = i + i + 3;
                k = i + prime;
                while (k < 8191)
                {
                    flags[k] = 0;
                    k += prime;
                }
                count += 1;
            }
        }
    }
    printf("%d primes\n", count);
    return 0;
}
