/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 4 (Zadanie 1b)
Data: 7 grudnia 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <conio.h>
using namespace std;

/*
    Program rozwiazuje uklad dwoch rownan liniowych metoda wyznacznikow
 */

//  Funkcja CzytajRownanie wczytuje wspolczyniki liczbowe przy zmiennych x i y
//      Parametry:
//          a, b, c - wspolczyniki liczbowe rownania
void CzytajRownanie(float *a, float *b, float *c);

//  Funkcja WypiszRozwiazanie wypisuje rozwiazania ukladu rownan (czyli wartosci zmiennych x i y)
//      Parametry:
//          N - liczba rozwiazan rowania
//              2 - nieskonczenie wiele rozwiazan
//              1 - jest jedno rozwiazanie
//              0 - brak rozwiazan (uklad sprzeczny)
//          x - wartosc zmiennej x ukladu rownan
//          y - wartosc zmiennej y ukladu rownan
void WypiszRozwiazanie(int N, float x, float y);

//  Funkcja ObliczWyznacznik oblicza wyznacznik macierzy utworzonej przez podanie odpowiednich wspolczynnikow liczbowych ukladu rownan
//      Parametry:
//          p1, p2, p3, p4 - wspolczynniki ukladu rownan liniowych
//      Zwracana wartosc:
//          - wartosc wyznacznika utworzonej macierzy
float ObliczWyznacznik(float p1, float p2, float p3, float p4);

// Funkcja ObliczRozwiazanie oblicza ilosc mozliwych rozwiazan ukladu rownan
//      Parametry:
//          a1, b1, c1 - wspolczynniki przy pierwszym rownaniu
//          a2, b2, c2 - wspolczynniki przy drugim rownaniu
//          x, y - zmienne przechowywujace rozwiazania ukladu
//      Zwracana wartosc:
//          2 - nieskonczenie wiele rozwiazan
//          1 - jest jedno rozwiazanie
//          0 - brak rozwiazan (uklad sprzeczny)
int ObliczRozwiazanie(float a1, float b1, float c1, float a2, float b2, float c2, float *x, float *y);


int main() {
    printf("Autor: Gabriel Malanowski (PN/P 13:15)\n");
    printf("Laboratorium 4. (Zadanie 1b) Rozwiazywanie ukladow rownan\n");

    float A1, B1, C1; // pierwsze rownanie
    float A2, B2, C2; // drugie rownanie
    float X, Y;       // rozwiazanie
    int N;            // liczba rozwiazan

    CzytajRownanie(&A1, &B1, &C1);
    CzytajRownanie(&A2, &B2, &C2);
    N = ObliczRozwiazanie(A1, B1, C1, A2, B2, C2, &X, &Y);
    WypiszRozwiazanie(N, X, Y);
    getch();
    return 0;
}

void CzytajRownanie(float *a, float *b, float *c)
{
    cout << "Podaj wartosc wspolczynnika A: ";
    cin >> *a;
    cout << "Podaj wartosc wspolczynnika B: ";
    cin >> *b;
    cout << "Podaj wartosc wspolczynnika C: ";
    cin >> *c;
}

void WypiszRozwiazanie(int N, float x, float y)
{
    if(N == 2)
    {
        cout << "Istnieje nieskonczenie wiele rozwiazan" << endl;
    }
    else if(N == 1)
    {
        cout << "Istnieje jedno rozwiazanie" << endl;
        cout << "Wartosc X: " << x << endl;
        cout << "Wartosc Y: " << y << endl;
    }
    else
    {
        cout << "Brak rozwiazania. Uklad sprzeczny" << endl;
    }
}

float ObliczWyznacznik(float p1, float p2, float p3, float p4)
{
    return p1*p4 - p3*p2;
}

int ObliczRozwiazanie(float a1, float b1, float c1, float a2, float b2, float c2, float *x, float *y)
{
    float W, Wx, Wy; // Wyznaczniki utworzonych macierzy
    W = ObliczWyznacznik(a1,a2,b1,b2);
    Wx = ObliczWyznacznik(c1,b1,c2,b2);
    Wy = ObliczWyznacznik(a1,c1,a2,c2);

    if(W != 0)
    {
        *x = Wx/W;
        *y = Wy/W;
        return 1;
    }
    else if(Wx == 0 && Wy == 0)
    {
        return 2;
    }
    else
    {
        return 0;
    }
}