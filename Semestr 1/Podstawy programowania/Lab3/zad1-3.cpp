/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 3 (Zadania 1 - 3)
Data: 22 listopada 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define ROZMIAR 5

void wczytywanie(float*);
void wypisanie(float*);
void sprawdzenia_do_zad_1(float*);
void uporzadkowanie(float*);
void wypelnianie_liczbami_losowymi(float*);
void sortowanie(float*);


int main() {
    std::cout << "Autor: Gabriel Malanowski (PN/P 13:15)" << std::endl;

    unsigned short zadanie; //przechowuje numer zadania

    //Tablica do zadan 1,2,3
    float Tab[ROZMIAR];

    std::cout << std::endl;

    do
    {
        std::cout << "-------------\nLaboratorium 3 (Zadania 1 - 3)\n1 -> Wczytanie z klawiatury liczb do tablicy\n2 -> Wyswietlenie zawartosci tablicy\n3 -> Sprawdzenie ilosci, sumy oraz sredniej liczb dodatnich i ujemnych\n4 -> Sprawdzenie uporzadkowania elementow w tablicy\n5 -> Wypelnienie tablicy liczbami pseudolosowymi z zadanego przedzialu\n6 -> Sortowanie tablicy rosnaco\n0 -> Koniec programu\n-------------\n";
        std::cout << "Wybierz odpowiednia funkcje (1-6) lub 0 aby zakonczyc program: ";
        std::cin >> zadanie;
        std::cout << std::endl;

        switch (zadanie)
        {
            case 1:
                wczytywanie(Tab);
                break;
            case 2:
                wypisanie(Tab);
                break;
            case 3:
                sprawdzenia_do_zad_1(Tab);
                break;
            case 4:
                uporzadkowanie(Tab);
                break;
            case 5:
                wypelnianie_liczbami_losowymi(Tab);
                break;
            case 6:
                sortowanie(Tab);
                break;
            case 0:
                break;
            default:
                std::cout << "Podano zla opcje. Sprobuj jeszcze raz. " << std::endl;
                break;
        }
        std::cout << std::endl;
        fflush(stdin);

    }while(zadanie != 0);

    return 0;
}

void wczytywanie(float Tab[]) // Wczytywanie z klawiatury elementów tablicy
{
    printf("Funkcja wczytujaca liczby z klawiatury do tablicy.\n");
    int licznik; // Przechowuje biezacy indeks tablicy
    for(licznik = 0; licznik < ROZMIAR; licznik++)
    {
        printf("Tab[%d] = ",licznik);
        scanf("%f",&Tab[licznik]);
    }
}

void wypisanie(float Tab[]) // Wypisanie zawartosci tablicy
{
    printf("Funkcja wypisujaca zawartosc tablicy\n");
    int licznik; // Przechowuje biezacy index tablicy
    printf("Tab = [  ");
    for(licznik = 0; licznik < ROZMIAR; licznik++)
    {
        printf("%.1f  ", Tab[licznik]);
    }
    printf("]\n");
}

void sprawdzenia_do_zad_1(float Tab[]) // Wykonuje sprawdzenia warunkow okreslonych w tresci zadania
{
    printf("Funkcja sprawdzajaca ilosc, sume i srednia liczb dodatnich oraz ujemnych\n");
    // Zakladam, ze 0 nalezy do zbioru liczb dodatnich i ujemnych
    int licznik_liczb_dodatnich = 0; // zapamietuje ilosc liczb dodatnich
    int licznik_liczb_ujemnych = 0; // zapamietuje ilosc liczb ujemnych
    float suma_liczb_dodatnich = 0; // przechowuje sume liczb dodatnich
    float suma_liczb_ujemnych = 0; // przechowuje sume liczb ujemnych

    int licznik; // przechowuje biezacy index tablicy

    for(licznik = 0; licznik < ROZMIAR; licznik++)
    {
        if(Tab[licznik] > 0)
        {
            licznik_liczb_dodatnich++;
            suma_liczb_dodatnich += Tab[licznik];
        }
        else if(Tab[licznik] < 0)
        {
            licznik_liczb_ujemnych++;
            suma_liczb_ujemnych += Tab[licznik];
        }
    }

    printf("Liczba wszystkich liczb dodatnich to %d, a wszystkich liczb ujemnych to %d\n", licznik_liczb_dodatnich, licznik_liczb_ujemnych);
    if(licznik_liczb_dodatnich == 0 && licznik_liczb_ujemnych == 0)
    {
        printf("Brak liczb dodatnich oraz liczb ujemnych, wiec suma i srednia tych liczb nie istnieje\n");
    }
    else if(licznik_liczb_dodatnich == 0)
    {
        printf("Suma liczb dodatnich nie istnieje, suma liczb ujemnych wynosi %f\n", suma_liczb_ujemnych);
        printf("Srednia liczb dodatnich nie istnieje, srednia liczb ujemnych wynosi %f\n", suma_liczb_ujemnych/((float)licznik_liczb_ujemnych));
    }
    else if(licznik_liczb_ujemnych == 0)
    {
        printf("Suma liczb dodatnich wynosi %f, suma liczb ujemnych nie istnieje\n", suma_liczb_dodatnich);
        printf("Srednia liczb dodatnich wynosi %f, srednia liczb ujemnych nie istnieje\n", suma_liczb_dodatnich/((float)licznik_liczb_dodatnich));
    }
    else
    {
        printf("Suma liczb dodatnich to %f, a liczb ujemnych to %f\n", suma_liczb_dodatnich, suma_liczb_ujemnych);
        printf("Srednia liczb dodatnich to %f, a liczb ujemnych to %f\n", suma_liczb_dodatnich/((float)licznik_liczb_dodatnich), suma_liczb_ujemnych/((float)licznik_liczb_ujemnych));
    }

}

void uporzadkowanie(float Tab[])
{
    printf("Funkcja sprawdzajaca sposob uporzadkowania tablicy\n");
    // Zmienne przechowujace informacje o biezacym uporzadkowaniu tablicy
    bool rosnaco = true;
    bool niemalejaco = true;
    bool jednakowe = true;
    bool nierosnaco = true;
    bool malejaco = true;

    int licznik; // Przechowuje biezacy index tablicy

    for(licznik = 1; licznik < ROZMIAR; licznik++)
    {
        // Sprawdzam, czy dwa kolejne elementy tablicy sa uporzadkowane:
        // rosnaco
        if(Tab[licznik-1] < Tab[licznik])
        {
            jednakowe = false;
            nierosnaco = false;
            malejaco = false;
        }
        // jednakowe
        else if(Tab[licznik-1] == Tab[licznik])
        {
            rosnaco = false;
            malejaco = false;
        }
        // malejaco
        else if(Tab[licznik-1] > Tab[licznik])
        {
            rosnaco = false;
            niemalejaco = false;
            jednakowe = false;
        }
    }

    if(!rosnaco && !niemalejaco && !jednakowe && !nierosnaco && !malejaco) printf("Elementy tablicy sa nie uporzadkowane\n");
    else
    {
        if(rosnaco) printf("Wszystkie elementy tablicy sa uporzadkowane rosnaco\n");
        if(niemalejaco) printf("Wszystkie elementy tablicy sa uporzadkowane niemalejaco\n");
        if(jednakowe) printf("Wszystkie elementy tablicy maja jednakowa wartosc\n");
        if(nierosnaco) printf("Wszystkie elementy tablicy sa uporzadkowane nierosnaco\n");
        if(malejaco) printf("Wszystkie elementy tablicy sa uporzadkowane malejaco\n");
    }
}

void wypelnianie_liczbami_losowymi(float Tab[])
{
    printf("Funkcja wypelniajaca tablice luczbami pseudolosowymi z zadanego przedzialu\n");
    srand(time(NULL));
    float min, max; // Minimalny i maksymalny zakres losowania
    printf("Podaj minimalny zakres losowania liczby rzeczywistej: ");
    scanf("%f", &min);
    printf("Podaj maksymalny zakres losowania liczby rzeczywistej: ");
    scanf("%f", &max);

    int licznik; // Przechowuje biezacy index tablicy

    for(licznik = 0; licznik < ROZMIAR; licznik++)
    {
        Tab[licznik] = min + (max - min)*rand() / ((double)RAND_MAX);
    }
    printf("Zakonczono operacje\n");
}

void sortowanie(float Tab[])
{
    printf("Funkcja sortujaca rosnaco tablice\n");
    //Wykorzystano algorytm sortowania babelkowego
    int poz1; // Przechowuje pozycje porownywanego elementu
    int poz2; // Przechowuje pozycje drugiego porownywanego elementu

    for(poz1 = 0; poz1 < ROZMIAR; poz1++)
    {
        for(poz2 = 1; poz2 < ROZMIAR - poz1; poz2++)
        {
            if(Tab[poz2-1]>Tab[poz2])
            {
                float bufor; // Tymczasowo przechowuje wartosc wiekszego elementu
                bufor = Tab[poz2-1];
                Tab[poz2-1] = Tab[poz2];
                Tab[poz2] = bufor;
            }
        }
    }
    printf("Zakonczono sortowanie.\n");
}
