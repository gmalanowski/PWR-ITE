/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 6
Data: 21 stycznia 2024 r.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Towar
{
    char nazwa[20];
    float cena;
    int ilosc;
};

// Funkcja inicjalizujaca dynamiczna tablice struktur
//      Parametry:
//          - Tab - wskaznik na wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - wskaznik na zmienna przechowujaca aktualny
//                      rozmiar tablicy
void utworz_tablice(Towar **Tab, int *Rozmiar);

// Funkcja wypisujaca zawartosc dynamicznej tablicy struktur
//      Parametry:
//          - Tab - wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - zmienna przechowujaca aktualny
//                      rozmiar tablicy
void wypisz_tablice(Towar *Tab, int Rozmiar);

// Funkcja dopisujaca nowy towar na koniec dynamicznej tablicy struktur
//      Parametry:
//          - Tab - wskaznik na wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - wskaznik na zmienna przechowujaca aktualny
//                      rozmiar tablicy
void dopisz_towar(Towar **Tab, int *Rozmiar);

// Funkcja usuwajaca wybrany towar z dynamicznej tablicy struktur
//      Parametry:
//          - Tab - wskaznik na wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - wskaznik na zmienna przechowujaca aktualny
//                      rozmiar tablicy
void usun_towar(Towar **Tab, int *Rozmiar);

// Funkcja zwalniajaca pamiec zajmowana przez dynamiczna tablice struktur
//      Parametry:
//          - Tab - wskaznik na wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - wskaznik na zmienna przechowujaca aktualny
//                      rozmiar tablicy
void zwolnij_tablice(Towar **Tab, int *Rozmiar);

// Funkcja wyszukujaca i wypisujaca towary spelniajace wybrane kryteria
//      Parametry:
//          - Tab - wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - zmienna przechowujaca aktualny
//                      rozmiar tablicy
void wyszukaj_towar(Towar *Tab, int Rozmiar);

// Funkcja sprawdzajaca, czy dany towar spelnia podane warunki
//      Parametry:
//          - t - wskaznik na strukture Towar
//          - kryterium - zmienna okreslajaca kryterium wyszukiwania
//          - cena - zmienna przechowujaca podana cene
//          - ilosc - zmienna przechowujaca podana ilosc
//          - nazwa - wskaznik na ciag znakow zawierajacy podana nazwe
//      Zwracana wartosc:
//          - 1, jesli towar spelnia kryterium, lub 0 w przeciwnym wypadku
int sprawdz_towar(Towar *towar, int kryterium, float cena, int ilosc, char* nazwa);

// Funkcja zapisujaca dane z dynamicznej tablicy struktur do pliku tekstowego
//      Parametry:
//          - Tab - wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - zmienna przechowujaca aktualny
//                      rozmiar tablicy
void zapisz_tablice(Towar *Tab, int Rozmiar);

// Funkcja wczytujaca dane z pliku tekstowego do dynamicznej tablicy struktur
//      Parametry:
//          - Tab - wskaznik na wskaznik na dynamiczna tablice
//                  struktur Towar
//          - Rozmiar - wskaznik na zmienna przechowujaca aktualny
//                      rozmiar tablicy
void wczytaj_tablice(Towar **Tab, int *Rozmiar);

int main()
{
    printf("Autor: Gabriel Malanowski (PN/P 13:15)\n");
    printf("Laboratorium 6. \n");

    int Rozmiar = 0;
    Towar *Tab = NULL;

    int opcja; // zmienna do przechowywania wyboru uzytkownika

    do
    {
        printf("----------------------------\n");
        printf("Adres tablicy: %p\n", Tab);
        printf("Rozmiar tablicy: %d\n", Rozmiar);
        printf("----------------------------\n");
        printf("1) utworzenie dynamicznej tablicy stuktury Towar\n");
        printf("2) wyswietlanie zawartosci tablicy dynamicznej\n");
        printf("3) dopisanie towaru do tablicy dynamicznej\n");
        printf("4) usuwanie wskazanego towaru z tablicy\n");
        printf("5) usuwanie tablicy dynamicznej z pamieci\n");
        printf("6) wyszukiwanie towaru na podstawie kryterium\n");
        printf("7) zapis danych z tablicy dynamicznej do pliku dyskowego\n");
        printf("8) odczyt danych z pliku dyskowego do tablicy dynamicznej\n");
        printf("0) zakonczenie programu\n");
        printf("----------------------------\n\n");

        printf("Wybierz opcje (0-8): ");
        scanf("%d", &opcja);

        switch (opcja)
        {
            case 1:
                utworz_tablice(&Tab, &Rozmiar);
                break;
            case 2:
                wypisz_tablice(Tab, Rozmiar);
                break;
            case 3:
                dopisz_towar(&Tab, &Rozmiar);
                break;
            case 4:
                usun_towar(&Tab, &Rozmiar);
                break;
            case 5:
                zwolnij_tablice(&Tab, &Rozmiar);
                break;
            case 6:
                wyszukaj_towar(Tab, Rozmiar);
                break;
            case 7:
                zapisz_tablice(Tab, Rozmiar);
                break;
            case 8:
                wczytaj_tablice(&Tab, &Rozmiar);
                break;
            case 0:
                break;
            default:
                printf("Nieprawidlowa opcja.\n");
                break;
        }
    }while(opcja != 0);

    return 0;
}

void utworz_tablice(Towar **Tab, int *Rozmiar)
{
    int nowy_rozmiar; // zmienna do przechowywania nowego rozmiaru
    printf("Podaj nowy rozmiar tablicy: ");
    scanf("%d", &nowy_rozmiar);

    if (nowy_rozmiar <= 0)
    {
        printf("Nieprawidlowy rozmiar. Rozmiar musi byc dodatni.\n");
        return;
    }

    if (*Tab != NULL)
    {
        free(*Tab);
        *Tab = NULL;
        *Rozmiar = 0;
    }

    *Tab = (Towar *) malloc(nowy_rozmiar * sizeof(Towar));

    if (*Tab == NULL)
    {
        printf("Nie udalo sie zaalokowac pamieci na tablice.\n");
        return;
    }
    *Rozmiar = nowy_rozmiar;

    int licznik; // zmienna pomocnicza do iterowania petli
    for (licznik = 0; licznik < *Rozmiar; licznik++)
    {
        printf("Podaj dane o %d. towarze:\n", licznik + 1);
        printf("Nazwa: ");
        scanf("%s", (*Tab)[licznik].nazwa);
        printf("Cena: ");
        scanf("%f", &(*Tab)[licznik].cena);
        printf("Ilosc: ");
        scanf("%d", &(*Tab)[licznik].ilosc);
    }
}

void wypisz_tablice(Towar *Tab, int Rozmiar)
{
    if (Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }

    printf("|----------------------|------------|------------|\n");
    printf("| %-20s | %-10s | %-10s |\n", "Nazwa", "Cena", "Ilosc");
    printf("|----------------------|------------|------------|\n");

    int licznik; // zmienna pomocnicza do iterowania petli
    for (licznik = 0; licznik < Rozmiar; licznik++)
    {
        printf("| %-20s | %10.2f | %10d |\n", Tab[licznik].nazwa, Tab[licznik].cena, Tab[licznik].ilosc);
    }
    printf("|----------------------|------------|------------|\n");
}

void dopisz_towar(Towar **Tab, int *Rozmiar)
{
    int nowy_rozmiar;

    if (*Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }

    printf("Podaj ilosc nowych towarow: ");
    scanf("%d", &nowy_rozmiar);

    if (nowy_rozmiar <= 0)
    {
        printf("Nieprawidlowa ilosc. Ilosc musi byc dodatnia.\n");
        return;
    }

    Towar *tmp; // tymczasowy wskaznik na tablice

    tmp = (Towar *) realloc(*Tab, (*Rozmiar + nowy_rozmiar) * sizeof(Towar));

    if (tmp == NULL)
    {
        printf("Nie udalo sie powiekszyc tablicy.\n");
        return;
    }

    *Tab = tmp;

    int licznik; // zmienna pomocnicza do iterowania petli
    for (licznik = 0; licznik < nowy_rozmiar; licznik++)
    {
        printf("Podaj dane o %d. nowym towarze:\n", licznik + 1);
        printf("Nazwa: ");
        scanf("%s", (*Tab)[*Rozmiar + licznik].nazwa);
        printf("Cena: ");
        scanf("%f", &(*Tab)[*Rozmiar + licznik].cena);
        printf("Ilosc: ");
        scanf("%d", &(*Tab)[*Rozmiar + licznik].ilosc);
    }

    *Rozmiar += nowy_rozmiar;
}

void usun_towar(Towar **Tab, int *Rozmiar)
{
    if (*Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }

    int index; // zmienna do przechowywania indeksu usuwanego elementu
    printf("Podaj indeks elementu do usuniecia (towary sa indeksowane od 0): ");
    scanf("%d", &index);

    if (index < 0 || index >= *Rozmiar)
    {
        printf("Nieprawidlowy indeks. Indeks musi byc z zakresu 0 do %d.\n", *Rozmiar - 1);
        return;
    }

    int licznik; // zmienna pomocnicza do iterowania petli
    for (licznik = index; licznik < *Rozmiar - 1; licznik++)
    {
        (*Tab)[licznik] = (*Tab)[licznik + 1];
    }

    Towar *tmp; // tymczasowy wskaznik na tablice
    tmp = (Towar *) realloc(*Tab, (*Rozmiar - 1) * sizeof(Towar));

    if (tmp == NULL)
    {
        printf("Nie udalo sie pomniejszyc tablicy.\n");
        return;
    }

    *Tab = tmp;

    (*Rozmiar)--;
}

void zwolnij_tablice(Towar **Tab, int *Rozmiar)
{
    if (*Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }
    free(*Tab);
    *Tab = NULL;
    *Rozmiar = 0;
}

int sprawdz_towar(Towar *towar, int kryterium, float cena, int ilosc, char* nazwa)
{
    switch (kryterium)
    {
        case 1: // cena mniejsza niz podana
            if (towar->cena < cena)
            {
                return 1;
            }
            break;
        case 2: // cena wieksza niz podana
            if (towar->cena > cena)
            {
                return 1;
            }
            break;
        case 3: // cena rowna podanej
            if (towar->cena == cena)
            {
                return 1;
            }
            break;
        case 4: // nazwa zawiera podany ciag znakow
            if (strstr(towar->nazwa, nazwa) != NULL)
            {
                return 1;
            }
            break;
        case 5: // ilosc mniejsza niz podana
            if (towar->ilosc < ilosc)
            {
                return 1;
            }
            break;
        case 6: // ilosc wieksza niz podana
            if (towar->ilosc > ilosc)
            {
                return 1;
            }
            break;
        case 7: // ilosc rowna podanej
            if (towar->ilosc == ilosc)
            {
                return 1;
            }
            break;
    }
    return 0;
}

void wyszukaj_towar(Towar *Tab, int Rozmiar) {
    int kryterium;
    float cena;
    int ilosc;
    char nazwa[21];
    int licznik; // zmienna pomocnicza do itereacji po petli
    int znaleziono = 0; // zmienna do sprawdzania, czy znaleziono jakies towary

    if (Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }

    // Wypisanie dostêpnych kryteriów
    printf("Wybierz kryterium wyszukiwania:\n");
    printf("1. Cena mniejsza niz podana\n");
    printf("2. Cena wieksza niz podana\n");
    printf("3. Cena rowna podanej\n");
    printf("4. Nazwa zawiera podany ciag znakow\n");
    printf("5. Ilosc mniejsza niz podana\n");
    printf("6. Ilosc wieksza niz podana\n");
    printf("7. Ilosc rowna podanej\n");

    printf("Wybierz opcje (1-7): ");
    scanf("%d", &kryterium);

    if (kryterium < 1 || kryterium > 7)
    {
        printf("Nieprawidlowe kryterium. Kryterium musi byc z zakresu 1 do 7.\n");
        return;
    }
    else if (kryterium >= 1 && kryterium <= 3)
    {
        printf("Podaj cene: ");
        scanf("%f", &cena);
    }
    else if(kryterium == 4)
    {
        printf("Podaj ciag znakow: ");
        scanf("%s", nazwa);
    }
    else if(kryterium >= 5 || kryterium <= 7)
    {
        printf("Podaj ilosc: ");
        scanf("%d", &ilosc);
    }

    printf("|----------------------|------------|------------|\n");
    printf("| %-20s | %-10s | %-10s |\n", "Nazwa", "Cena", "Ilosc");
    printf("|----------------------|------------|------------|\n");

    for (licznik = 0; licznik < Rozmiar; licznik++)
    {
        if (sprawdz_towar(&Tab[licznik], kryterium, cena, ilosc, nazwa) == 1)
        {
            printf("| %-20s | %10.2f | %10d |\n", Tab[licznik].nazwa, Tab[licznik].cena, Tab[licznik].ilosc);
            znaleziono = 1;
        }
    }
    printf("|----------------------|------------|------------|\n");

    if (znaleziono == 0)
    {
        printf("|Nie znaleziono towarow spelniajacych kryterium. |\n");
    }
}

void zapisz_tablice(Towar *Tab, int Rozmiar)
{
    char nazwa[21]; // zmienna do przechowywania nazwy pliku
    FILE *plik;

    if (Tab == NULL)
    {
        printf("Brak tablicy.\n");
        return;
    }

    printf("Podaj nazwe pliku: ");
    scanf("%s", nazwa);

    plik = fopen(nazwa, "w");

    if (plik == NULL)
    {
        printf("Nie udalo sie utworzyc pliku.\n");
        return;
    }

    fprintf(plik, "%d\n", Rozmiar);

    int licznik; // zmienna pomocnicza do iteracji po petli
    for (licznik = 0; licznik < Rozmiar; licznik++)
    {
        fprintf(plik, "%s %f %d\n", Tab[licznik].nazwa, Tab[licznik].cena, Tab[licznik].ilosc);
    }

    fclose(plik);

    printf("Zapisano tablice do pliku %s.\n", nazwa);
}

// Definicja funkcji wczytaj_tablice
void wczytaj_tablice(Towar **Tab, int *Rozmiar) {
    char nazwa[21]; // zmienna do przechowywania nazwy pliku
    FILE *plik;
    int nowy_rozmiar;

    Towar *tmp; // tymczasowy wskaŸnik na tablicê

    printf("Podaj nazwe pliku: ");
    scanf("%s", nazwa);

    plik = fopen(nazwa, "r");

    if (plik == NULL)
    {
        printf("Nie udalo sie otworzyc pliku.\n");
        return;
    }

    fscanf(plik, "%d", &nowy_rozmiar);

    // Sprawdzenie, czy rozmiar podany w pliku jest prawidlowy
    if (nowy_rozmiar <= 0)
    {
        printf("Nieprawidlowy rozmiar. Rozmiar musi byc dodatni.\n");
        fclose(plik);
        return;
    }

    if (*Tab != NULL)
    {
        free(*Tab);
        *Tab = NULL;
        *Rozmiar = 0;
    }

    tmp = (Towar *) malloc(nowy_rozmiar * sizeof(Towar));

    if (tmp == NULL)
    {
        printf("Nie udalo sie zaalokowac pamieci na tablice.\n");
        fclose(plik);
        return;
    }

    *Tab = tmp;
    *Rozmiar = nowy_rozmiar;

    int licznik; // zmienna pomocnicza do iteracji po petli
    for (licznik = 0; licznik < *Rozmiar; licznik++)
    {
        fscanf(plik, "%s %f %d", (*Tab)[licznik].nazwa, &(*Tab)[licznik].cena, &(*Tab)[licznik].ilosc);
    }

    fclose(plik);

    printf("Wczytano tablice z pliku %s.\n", nazwa);
}
