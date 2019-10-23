#include "stdafx.h"

LARGE_INTEGER m_nFreq;
LARGE_INTEGER m_nBeginTime;

void tic()
{
        QueryPerformanceFrequency(&m_nFreq);
        QueryPerformanceCounter(&m_nBeginTime);
}
__int64 toc()
{
        LARGE_INTEGER nEndTime;
        __int64 nCalcTime;

        QueryPerformanceCounter(&nEndTime);
        nCalcTime = (nEndTime.QuadPart - m_nBeginTime.QuadPart) * 1000 / m_nFreq.QuadPart;

        wprintf(L" processing time: %d ms\n", nCalcTime);
        return nCalcTime;
}

unsigned int size = 15000000;

float sse_inner(const float* a, const float* b, unsigned int size)
{
        float z = 0.0f, fres = 0.0f;
        __declspec(align(16)) float ftmp[4] = { 0.0f, 0.0f, 0.0f, 0.0f };
        __m128 mres;

        if ((size / 4) != 0) {
                mres = _mm_load_ss(&z);
                for (unsigned int i = 0; i < size / 4; i++)
                        mres = _mm_add_ps(mres, _mm_mul_ps(_mm_loadu_ps(&a[4*i]), _mm_loadu_ps(&b[4*i])));

                //mres = a,b,c,d
                __m128 mv1 = _mm_movelh_ps(mres, mres);     //a,b,a,b
                __m128 mv2 = _mm_movehl_ps(mres, mres);     //c,d,c,d
                mres = _mm_add_ps(mv1, mv2);                //res[0],res[1]

                _mm_store_ps(ftmp, mres);                

                fres = ftmp[0] + ftmp[1];
        }

        if ((size % 4) != 0) {
                for (unsigned int i = size - size % 4; i < size; i++)
                        fres += a[i] * b[i];
        }

        return fres;
}

double sse_inner_d(const double* a, const double* b, unsigned int size)
{
        double z = 0.0, fres = 0.0;
        __declspec(align(16)) double ftmp[2] = { 0.0, 0.0 };
        __m128d mres;
        
        if ((size / 2) != 0) {
                mres = _mm_load_sd(&z);
                for (unsigned int i = 0; i < size / 2; i++)
                        mres = _mm_add_pd(mres, _mm_mul_pd(_mm_loadu_pd(&a[2*i]), _mm_loadu_pd(&b[2*i])));                

                _mm_store_pd(ftmp, mres);                

                fres = ftmp[0] + ftmp[1];
        }

        if ((size % 2) != 0) {
                for (unsigned int i = size - size % 2; i < size; i++)
                        fres += a[i] * b[i];
        }

        return fres;
}


float sse3_inner(const float* a, const float* b, unsigned int size)
{
        float z = 0.0f, fres = 0.0f;
        
        if ((size / 4) != 0) {
                const float* pa = a;
                const float* pb = b;
                __asm {
                        movss   xmm0, xmmword ptr[z]
                }
                for (unsigned int i = 0; i < size / 4; i++) {
                        __asm {
                                mov     eax, dword ptr[pa]		//GUARDAMOS EL PUNTERO DE pa EN eax
                                mov     ebx, dword ptr[pb]		//GUARDAMOS EL PUNTERO DE pb EN ebx
                                movups  xmm1, [eax]				//GUARDAMOS EL CONJUNTO EN UNA VARIABLE XMM1
                                movups  xmm2, [ebx]				//GUARDAMOS EL CONJUNTO EN UNA VARIABLE XMM2
                                mulps   xmm1, xmm2				//MULTIPLICACION DE XMM2 Y XMM1 GUARDADO EN XMM1
                                addps   xmm0, xmm1				//SUMA DE XMM1 A XMM0
                        }
                        pa += 4;	//ACCEDEMOS AL SIGUIENTE ELEMENTO
                        pb += 4;	//ACCEDEMOS AL SIGUIENTE ELEMENTO
                }  
                __asm {
                        haddps  xmm0, xmm0			//SUMA DE TODOS LOS ELEMENTOS DEL VECTOR DE LA SIGUIENTE FORMA
													/*
													xmm1[0..31] = xmm1[0..31] + xmm1[32..63];
													xmm1[32..63] = xmm1[64..95] + xmm1[96..127];
													xmm1[64..95] = xmm2/m128[0..31] + xmm2/m128[32..63];
													xmm1[96..127] = xmm2/m128[64..95] + xmm2/m128[96..127];
													*/
                        haddps  xmm0, xmm0			//POR LO QUE AL USAR HADDPS DOS VECES, LA PRIMERA PALABRA DE XMM0 ES EL 
													//RESULTADO DE LA SUMA DE LAS 4 PALABRAS QUE CABEN EN LOS 128 BITS DE
													//UN REGISTRO XMM
                        movss   dword ptr[fres], xmm0                        
                }                
        }

        return fres;
}

short sse2_inner_s(const short* p1, const short* p2, unsigned int size)
{
        __m128i* mp1 = (__m128i *)p1;
        __m128i* mp2 = (__m128i *)p2;
        __m128i mres = _mm_set_epi16(0, 0, 0, 0, 0, 0, 0, 0);
        
        for(unsigned int i = 0; i < size/8; i++) {                 
                __m128i mtmp = _mm_mullo_epi16(_mm_loadu_si128(mp1), _mm_loadu_si128(mp2)); 
                mres = _mm_add_epi16(mres, mtmp);
                mp1++;
                mp2++;
        }

        short res[8];
        __m128i* pmres = (__m128i *)res;
        _mm_storeu_si128(pmres, mres);

        return res[0]+res[1]+res[2]+res[3]+res[4]+res[5]+res[6]+res[7];
}

void vchar()
{
        char* pv1;
        char* pv2;
        std::vector<char> v1;
        std::vector<char> v2;
        v1.resize(size);
        v2.resize(size);
        pv1 = &v1[0];
        pv2 = &v2[0];

        for (unsigned int i = 0; i < size; i++) {
                pv1[i] = char(rand() % 64 - 32);
                pv2[i] = char(rand() % 64 - 32);
        }

        char sum = 0;
        wprintf(L" chars\t\t");
        tic();
        for (unsigned int i = 0; i < size; i++)
                sum += pv1[i] * pv2[i];
        toc();

        wprintf(L" %d\n", sum);
}

void vshort()
{
        short* pv1;
        short* pv2;
        std::vector<short> v1;
        std::vector<short> v2;
        v1.resize(size);
        v2.resize(size);
        pv1 = &v1[0];
        pv2 = &v2[0];

        for (unsigned int i = 0; i < size; i++) {
                pv1[i] = short(rand() % 64 - 32);
                pv2[i] = short(rand() % 64 - 32);
        }

        short sum = 0;
        wprintf(L" shorts\t\t");
        tic();
        for (unsigned int i = 0; i < size; i++)
                sum += pv1[i] * pv2[i];
        toc();
        wprintf(L" %d\n", sum);

        wprintf(L" shorts sse2\t");
        tic();
        sum = sse2_inner_s(pv1, pv2, size);
        toc();
        wprintf(L" %d\n", sum);
}

void vint()
{
        int* pv1;
        int* pv2;
        std::vector<int> v1;
        std::vector<int> v2;
        v1.resize(size);
        v2.resize(size);
        pv1 = &v1[0];
        pv2 = &v2[0];

        for (unsigned int i = 0; i < size; i++) {
                pv1[i] = int(rand() % 64 - 32);
                pv2[i] = int(rand() % 64 - 32);
        }

        int sum = 0;
        wprintf(L" ints\t\t");
        tic();
        for (unsigned int i = 0; i < size; i++)
                sum += pv1[i] * pv2[i];
        toc();

        wprintf(L" %d\n", sum);
}

void vfloat()
{
        float* pv1;
        float* pv2;
        std::vector<float> v1;
        std::vector<float> v2;
        v1.resize(size);
        v2.resize(size);
        pv1 = &v1[0];
        pv2 = &v2[0];

        for (unsigned int i = 0; i < size; i++) {
                pv1[i] = float(rand() % 64 - 32);
                pv2[i] = float(rand() % 64 - 32);
        }

        float sum = 0;
        wprintf(L" floats\t\t");
        tic();
        for (unsigned int i = 0; i < size; i++)
                sum += pv1[i] * pv2[i];
        toc();
        wprintf(L" %d\n", (int)sum);

        wprintf(L" sse2 intrin\t");
        tic();
        sum = sse_inner(pv1, pv2, size);
        toc();
        wprintf(L" %d\n", (int)sum);

        wprintf(L" sse3 assembly\t");
        tic();
        sum = sse3_inner(pv1, pv2, size);
        toc();
        wprintf(L" %d\n", (int)sum);        
}

void vdouble()
{
        double* pv1;
        double* pv2;
        std::vector<double> v1;
        std::vector<double> v2;
        v1.resize(size);
        v2.resize(size);
        pv1 = &v1[0];
        pv2 = &v2[0];

        for (unsigned int i = 0; i < size; i++) {
                pv1[i] = double(rand() % 64 - 32);
                pv2[i] = double(rand() % 64 - 32);
        }

        double sum = 0;
        wprintf(L" doubles\t");
        tic();
        for (unsigned int i = 0; i < size; i++)
                sum += pv1[i] * pv2[i];
        toc();
        wprintf(L" %d\n", (int)sum);

        wprintf(L" doubles sse2\t");
        tic();
        sum = sse_inner_d(pv1, pv2, size);
        toc();
        wprintf(L" %d\n", (int)sum);

}

int _tmain(int argc, _TCHAR* argv[])
{
        if (argc >= 1 + 1)
                size = _wtoi(argv[1]);

        srand(time(0));
        
        vchar();
        __try {
                vshort();
        }
        __except(EXCEPTION_EXECUTE_HANDLER) {
                wprintf(L" seh failure!\n");
        }
        vint();
        __try {
                vfloat();
        }
        __except(EXCEPTION_EXECUTE_HANDLER) {
                wprintf(L" seh failure!\n");
        }
        __try {
                vdouble();
        }
        __except(EXCEPTION_EXECUTE_HANDLER) {
                wprintf(L" seh failure!\n");
        }

		int a = getchar();
        return 0;
}

