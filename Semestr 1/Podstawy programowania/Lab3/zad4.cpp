/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 3 (Zadanie 4)
Data: 22 listopada 2023 r.
 */

#include <iostream>
#include <conio.h>
#define LICZBA_LITER_W_ALFABECIE 26

int main() {
    printf("Autor: Gabriel Malanowski (PN/P 13:15)\n");

    printf("Laboratorium 3. (Zadanie 4) Zliczanie liter alfabetu angielskiego\n");

    int licznik_liter[LICZBA_LITER_W_ALFABECIE] = {0}; // Tablica przechowujaca liczbe wystapien danej litery
    int znak = 38; // Zmienna przechowujaca kod wprowadzonego znaku z klawiatury
    int licznik_znakow = 0; // Przechowuje liczbe wprowadzonych znakow

    printf("Wpisuj litery: ");

    do
    {
        znak = getch();
        printf("%c", znak);
        if(znak >= 65 && znak <=90)
        {
            licznik_liter[znak-65]++;
        }
        else if(znak >= 97 && znak <= 122)
        {
            licznik_liter[znak-97]++;
        }
        licznik_znakow++;
    }while(znak != 27);

    printf("\nWWpisano ogolem %d znakow w tym:\n", --licznik_znakow);

    int litera; // przechowuje pozycje litery w alfabecie

    for(litera = 0; litera < LICZBA_LITER_W_ALFABECIE; litera++)
    {
        printf("litera %c %d", litera+65, licznik_liter[litera]);
        for(int ilosc_hashow = 0; ilosc_hashow < licznik_liter[litera]; ilosc_hashow++) printf("#");
        printf("\n");
    }

    printf("\n");
    system("PAUSE");
    return 0;
}

