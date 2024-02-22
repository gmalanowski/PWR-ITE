/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 5 (Zadanie 2)
Data: 27 grudnia 2023 r.
 */

#include <stdio.h>
#include <string.h>

// STR_LEN zawiera informacje o dlugosci lancucha znakow.
// Domyslnie ustawiony na 81 znakow. (dziala poprawnie dla srodowisk CLion,
// Visual Studio, Code::Blocks)
// W starszych srodowiskach (np. Falcon C++) program dziala poprawnie
// dla wartosci mniejszych/rownych 31, powyzej tej wartosci proba wykonania
// jakiejkolwiek operacji skutkuje bledem ochrony pamieci (heap corruption)
#define STR_LEN 81

// Funkcja inicjalizujaca dynamiczna tablice dwuwymiarowa
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona
void InitTab(char **&wsk);

// Funkcja dodajaca nowe imie do tablicy dynamicznej
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona,
//          - buf - wskaznik na tablice znakow w ktorej jest wprowadzone
//                  z klawiatury imie
void AddName(char *buf, char **&wsk);

// Funkcja usuwajaca imie znajdujace sie w tablicy na danej pozycji
//      Parametry:
//          - nr - indeks pozycji na ktorej znajduje sie imie
//                 ktore ma zostac usuniete
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona,
void RemoveName(int nr, char **&wsk);

// Funkcja usuwajaca z tablicy imie, wprowadzone z klawiatury
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona,
//          - buf - wskaznik na tablice znakow w ktorej jest wprowadzone
//                  z klawiatury imie
void RemoveName(char *buf, char **&wsk);

// Funkcja drukujaca na ekranie wszystkie imiona
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona
void PrintAllNames(char **wsk);

// Funkcja drukujaca na ekranie imiona rozpoczynajace sie na wskazana litere
//      Parametry:
//          - first_letter - znak okreslajacy pierwsza litere imion,
//                           ktore maja byc drukowane
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona
void PrintNames(char first_letter, char **wsk);

// Funkcja sortujaca imiona w tablicy wedlug alfabetu
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona
void SortAlphabet(char **wsk);

// Funkcja sortujaca imiona w tablicy wedlug dlugosci
//      Parametry:
//          - wsk - wskaznik na dynamiczna dwuwymiarowa tablice znakow,
//                  w ktorej sa pamietane wszystkie imiona
void SortLength(char **wsk);

int main() {
    printf("Autor: Gabriel Malanowski (PN/P 13:15)\n");
    printf("Laboratorium 5. (Zadanie 2)\n");

    // wskaznik na dwuwymiarowa tablice dynamiczna,
    // w ktorej beda pamietane wszystkie imiona
    char **WSK;

    //bufor na dane wczytywane z klawiatury
    char buf[STR_LEN];

    // inicjalizacja tablicy dynamicznej
    InitTab(WSK);

    // menu do zadania
    unsigned short opcja = 1;

    do
    {
        fflush(stdin);
        printf("----------------------------\n");
        printf("1) dodawanie do slownika nowego imienia wprowadzanego z klawiatury\n");
        printf("2) drukowanie na ekranie wszystkich imion zapamietanych w slowniku\n");
        printf("3) drukowanie na ekranie wszystkich imion na wskazana litere alfabetu\n");
        printf("4) usuwanie imienia znajdujacego sie na wskazanej pozycji w tablicy\n");
        printf("5) usuwanie z tablicy imienia, wprowadzonego z klawiatury\n");
        printf("6) sortowanie wszystkich imion zawartych w tablicy wedlug alfabetu\n");
        printf("7) sortowanie wszystkich imion zawartych w tablicy wedlug dlugosci lancucha znakow\n");
        printf("0) zakonczenie programu\n");
        printf("----------------------------\n\n");

        printf("Wprowadz numer wybranej opcji (0-7): ");
        scanf("%u", &opcja);

        switch(opcja)
        {
            case 1:
            {
                printf("Wprowadz imie: ");
                fflush(stdin);
                fgets (buf, STR_LEN, stdin);
                AddName(buf,WSK);
                break;
            }
            case 2:
            {
                PrintAllNames(WSK);
                break;
            }
            case 3:
            {
                char znak; // przechowuje litere alfabetu
                printf("Podaj litere alfabetu: ");
                fflush(stdin);
                scanf("%c", &znak);
                PrintNames(znak,WSK);
                break;
            }
            case 4:
            {
                int nr; // Pozycja imienia w slowniku
                printf("Podaj pozycje imienia w slowniku (pozycje liczone od 0): ");
                fflush(stdin);
                scanf("%d", &nr);
                RemoveName(nr, WSK);
                break;
            }
            case 5:
            {
                printf("Wprowadz imie: ");
                fflush(stdin);
                fgets (buf, STR_LEN, stdin);
                RemoveName(buf,WSK);
                break;
            }
            case 6:
            {
                SortAlphabet(WSK);
                break;
            }
            case 7:
            {
                SortLength(WSK);
                break;
            }
            case 0:
            {
                break;
            }
            default:
            {
                printf("Wprowadzono zla opcje\n");
                break;
            }
        }
    }while(opcja != 0);

    delete [] WSK;

    return 0;
}

void InitTab(char **&wsk)
{
    wsk = new char* [1];
    wsk[0] = NULL;
}

void AddName(char *buf, char **&wsk)
{
    int poz = 0; // pozycja w tablicy zawierajaca NULL
    while(wsk[poz] != NULL) poz++;

    char **bufor = new char*[poz+2];
    bufor[poz+1] = NULL;

    // Kopiowanie zawartosci tablicy
    for(int i = 0; i < poz; i++)
        bufor[i] = wsk[i];

    bufor[poz] = new char[strlen(buf)+1];
    strcpy(bufor[poz], buf);

    delete [] wsk;
    wsk = bufor;
}

void RemoveName(int nr, char **&wsk)
{
    int poz = 0; // pozycja zawierajaca NULL
    while(wsk[poz] != NULL) poz++;
    if(nr >= poz)
    {
        printf("Na tej pozycji nie znajduje sie zadno imie. Akcja nie zostala wykonana\n");
        return;
    }
    delete [] wsk[nr];

    // Przesuniecie imion o 1 pozycje nizej
    for(int i=nr; i < poz; i++) wsk[i] = wsk[i+1];

    char **bufor = new char* [poz];
    bufor[poz-1] = NULL;

    // Kopiowanie zawartosci tablicy
    for(int i = 0; i < poz-1; i++)
        bufor[i] = wsk[i];

    delete [] wsk;

    wsk = bufor;
}

void RemoveName(char *buf, char **&wsk)
{
    int poz = 0; // pozycja usuwanego imienia
    bool brak = true; // sprawdza czy istnieje imie w slowniku
    while(wsk[poz] != NULL)
    {
        if(strcmp(wsk[poz],buf) == 0)
        {
            RemoveName(poz,wsk);
            brak = false;
            continue;
        }
        poz++;
    }
    if(brak)
    {
        printf("Brak podanego imienia w slowniku.\n");
        return;
    }
    printf("Akcja zakonczona pomyslnie.\n");
}

void PrintAllNames(char **wsk)
{
    int poz = 0; // pozycja zawierajaca NULL
    while(wsk[poz] != NULL)
        printf("%s", wsk[poz++]);
}

void PrintNames(char first_letter, char **wsk)
{
    int poz = 0; // pozycja zawierajaca NULL
    while(wsk[poz] != NULL)
    {
        if(wsk[poz][0] == first_letter)
            printf("%s", wsk[poz]);
        poz++;
    }
}

void SortAlphabet(char **wsk)
{
    for(int poz_imie1 = 0; wsk[poz_imie1] != NULL; poz_imie1++)
    {
        for(int poz_imie2 = 0; wsk[poz_imie2] != NULL; poz_imie2++)
        {
            if(strcmp(wsk[poz_imie1],wsk[poz_imie2]) < 0)
            {
                char *buf = wsk[poz_imie1];
                wsk[poz_imie1] = wsk[poz_imie2];
                wsk[poz_imie2] = buf;
            }
        }
    }
    printf("Akcja zakonczona pomyslnie\n");
}

void SortLength(char **wsk)
{
    for(int poz_imie1 = 0; wsk[poz_imie1] != NULL; poz_imie1++)
    {
        for(int poz_imie2 = 0; wsk[poz_imie2] != NULL; poz_imie2++)
        {
            if(strlen(wsk[poz_imie1]) < strlen(wsk[poz_imie2]))
            {
                char *buf = wsk[poz_imie1];
                wsk[poz_imie1] = wsk[poz_imie2];
                wsk[poz_imie2] = buf;
            }
        }
    }
    printf("Akcja zakonczona pomyslnie\n");
}
