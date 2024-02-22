/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 4 (Zadania 2 - 4)
Data: 7 grudnia 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <string.h>
#include <conio.h>
#include <cstdlib>

// Deklaracja zmiennych globalnych do zadania 4
char C = 'A';
int I = 100;
long L = 2147483643;
float F = 3.14;
double D = 12345678.99;

//  Funkcja wczytaj_tekst wczytuje podany z klawiatury tekst do tablicy znakow
//      Parametry:
//          tab - tablica znakow
void wczytaj_tekst(char *tab);

// Funkcja wypisz_tekst drukuje z tablicy znakow tekst na ekran
//      Parametry:
//          tab - tablica znakow
void wypisz_tekst(char *tab);

// Funkcja zadanie_2 usuwa z zadanego lancucha znakow wszystkie cyfry
//      Parametry:
//          tab - lancuch znakow
//      Zwracane wartosci:
//          cyfry - liczba znalezionych i usunietych liczb
int zadanie_2(char *tab);

//  Funkcja zadanie_3 usuwa z zadanego lancucha znakow komentarze
//      Parametry:
//          tab - lancuch znakow
//      Zwracane wartosci:
//          adres - adres poczatku lancucha wynikowego
char* zadanie_3(char *tab);

//  Funkcja zadanie_4 wykonuje operacje okreslone w tresci zadania 4 tj:
//      * wypisanie na ekranie adresow zmiennych lokalnych i globalnych
//      * wypisanie w kodzie heksadecymalnym wartosci bajtow kodujacych owe zmienne
//      * Zapis nowej wartosci zmiennej za pomoca adresu tej zmiennej
//      * Zapis wartosci zmiennej za pomoca adresu innej zmiennej
void zadanie_4();

// Funkcja wypisz_zmienne powoduje wypisanie na ekranie adresow zmiennych lokalnych i globalnych
// oraz wypisanie w kodzie heksadecymalnym wartosci bajtow kodujacych owe zmienne
//      Parametry:
//          CC,II,LL,FF,DD,cc,ii,ll,ff,dd - adresy zmiennych C,I,L,F,D,c,i,l,f,d
void wypisz_zmienne(char* CC,int* II,long* LL,float* FF,double* DD,char* cc,int* ii,long* ll,float* ff,double* dd);

int main() {
    std::cout << "Autor: Gabriel Malanowski (PN/P 13:15)" << std::endl;

    unsigned short zadanie; //przechowuje numer zadania

    std::cout << std::endl;

    char lancuch_znakow[100] = {'\0'};
    int wynik; // przechowuje zwrocona wartosc z funkcji do zadania 2
    char* adres; // przechowuje adres poczatku lancucha wynikowego z zadania 3

    do
    {
        std::cout << "-------------\nLaboratorium 4 (Zadania 2 - 4)\n2 -> Usuwanie z lancucha znakow liczb (Zadanie 2)\n3 -> Usuwanie z lancucha znakow komentarzy (Zadanie 3)\n4 -> Zmienne lokalne i globalne (Zadanie 4)\n5 -> Wpisanie z klawiatury lancucha znakow (do zad. 2,3)\n6 -> Wypisanie lancucha znakow (do zad. 2,3)\n0 -> Koniec programu\n-------------\n";
        std::cout << "Wybierz odpowiednia funkcje (2-6) lub 0 aby zakonczyc program: ";
        std::cin >> zadanie;
        std::cout << std::endl;

        switch (zadanie)
        {
            case 2:
                wynik = zadanie_2(lancuch_znakow);
                std::cout << "Znaleziono i usunieto " << wynik << " liczb(y)" << std::endl;
                break;
            case 3:
                adres = zadanie_3(lancuch_znakow);
                std::cout << "Zmodyfikowana tablica znajduje sie pod adresem: " << &adres << std::endl;
                break;
            case 4:
                zadanie_4();
                break;
            case 5:
                wczytaj_tekst(lancuch_znakow);
                break;
            case 6:
                wypisz_tekst(lancuch_znakow);
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

void wczytaj_tekst(char *tab)
{
    char znak = 27; //przechowuje wprowadzony znak, w celach inicjalizacyjnych klawisz ESC
    std::cout << "Wprowadz zdanie: ";
    int index = 0; // przechowuje obecny indeks tablicy
    while(znak != 13) // Kod 13 to klawisz ENTER
    {
        znak = getch();
        tab[index++] = znak;
        std::cout << znak;
    }
    tab[index] = '\0';
}

void wypisz_tekst(char *tab)
{
    std::cout << tab;
}

int zadanie_2(char *tab)
{
    char kopia[100] = {'\0'};
    int cyfry = 0; // licznik znalezionych i usunietych cyfr
    int index; // przechowuje obecny indeks tablicy pierwotnej;
    int index_kopii; // przechowuje indeks tablicy skopiowanej
    for(index = 0, index_kopii = 0; tab[index] != '\0'; index++)
    {
        if(tab[index] < '0' || tab[index] > '9')
        {
            kopia[index_kopii++] = tab[index];
        }
        else
        {
            cyfry++;
        }
    }
    strcpy(tab,kopia);
    return cyfry;
}

char* zadanie_3(char* tab)
{
    int index; // przechowuje obecny indeks tablicy pierwotnej
    int index_podmiany; // przechowuje indeks tablicy od ktorego nalezy zaczac podmieniac wartosci
    bool czy_komentarz = false; // zmienna zawierajaca informacje czy wykryto poczatek komentarza
    for(index = 0, index_podmiany = 0; tab[index] != '\0'; index++)
    {

        if(!czy_komentarz)
        {
            if(tab[index] == '/' && tab[index+1] == '/')
            {
                tab[index_podmiany] = '\0';
                index++;
                break;
            }
            else if(tab[index] == '/' && tab[index+1] == '*')
            {
                czy_komentarz = true;
                index++;
            }
            else
            {
                tab[index_podmiany++] = tab[index];
            }
        }
        else
        {
            if(tab[index] == '*' && tab[index+1] == '/')
            {
                czy_komentarz = false;
                index++;
            }
        }
    }
    return tab;
}

void zadanie_4()
{
    // Deklaracja zmiennych lokalnych
    char c = 'a';
    int i = 1;
    long l = 2000000000;
    float f = 1.245;
    double d = 987654321.00;

    // Deklaracja wskaznikow
    char *CC = &C; char *cc = &c;
    int *II = &I; int *ii = &i;
    long *LL = &L; long *ll = &l;
    float *FF = &F; float *ff = &f;
    double *DD = &D; double *dd = &d;

    wypisz_zmienne(CC,II,LL,FF,DD,cc,ii,ll,ff,dd);
    printf("\n");
    system("PAUSE");
    printf("\nWyniki operacji zapisu wartosci zmiennej za pomoca adresu tej zmiennej: \n\n");

    // instrukcje zapisu nowej wartoœci ka¿dej zmiennej za pomoc¹ adresu tej zmiennej
    *CC = 'B';
    *cc = 'b';
    *II = i+1;
    *ii = I + 1;
    *LL = *ll;
    *ll = *LL;
    *FF = *ff/(*FF);
    *ff = (*FF) * (*FF);
    *DD += *dd;
    *dd -= *DD;

    wypisz_zmienne(CC,II,LL,FF,DD,cc,ii,ll,ff,dd);
    printf("\n");
    system("PAUSE");
    printf("\nWyniki operacji zapisu wartosci zmiennej za pomoca adresu innej zmiennej: \n\n");

    // instrukcje zapisu nowej wartosci zmiennej za pomoca adresu innej zmiennej
    *(&C+4) = 15; // I = 15;
    *(&l+1) = -5; // i = -5;
    *(&I+1) = *(&i-1)+1; // L = l+1;
    *(&i-1) = *(&L-1) - *(&I+1); // l = I - L;

    printf("Wartosc zmiennej C: %c\n", C);
    printf("Wartosc zmiennej I: %d\n", I);
    printf("Wartosc zmiennej L: %ld\n", L);
    printf("Wartosc zmiennej F: %f\n", F);
    printf("Wartosc zmiennej D: %lf\n", D);
    printf("Wartosc zmiennej c: %c\n", c);
    printf("Wartosc zmiennej i: %d\n", i);
    printf("Wartosc zmiennej l: %ld\n", l);
    printf("Wartosc zmiennej f: %f\n", f);
    printf("Wartosc zmiennej d: %lf\n", d);

}

void wypisz_zmienne(char* CC,int* II,long* LL,float* FF,double* DD,char* cc,int* ii,long* ll,float* ff,double* dd)
{
    printf("Zmienna globalna C: Adres: 0x%p Wartosc: %c\t\t", CC, *CC); printf("Bajty: 0x%X\n", *CC);
    printf("Zmienna globalna I: Adres: 0x%p Wartosc: %d\t\t", II, *II); printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(CC+4), *(CC+5), *(CC+6), *(CC+7));
    printf("Zmienna globalna L: Adres: 0x%p Wartosc: %ld\t", LL, *LL); printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(CC+8), *(CC+9), *(CC+10), *(CC+11));
    printf("Zmienna globalna F: Adres: 0x%p Wartosc: %f\t\t", FF, *FF); printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(CC+12), *(CC+13), *(CC+14), *(CC+15));
    printf("Zmienna globalna D: Adres: 0x%p Wartosc: %lf\t", DD, *DD); printf("Bajty: 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X\n\n", *(CC+16), *(CC+17), *(CC+18), *(CC+19), *(CC+20), *(CC+21), *(CC+22), *(CC+23));

    printf("Zmienna lokalna c: Adres: 0x%p Wartosc: %c\t\t\t", cc, *cc); printf("Bajty: 0x%X\n", *cc);
    printf("Zmienna lokalna i: Adres: 0x%p Wartosc: %d\t\t\t", ii, *ii); printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(cc-4), *(cc-5), *(cc-6), *(cc-7));
    printf("Zmienna lokalna l: Adres: 0x%p Wartosc: %ld\t", ll, *ll);  printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(cc-8), *(cc-9), *(cc-10), *(cc-11));
    printf("Zmienna lokalna f: Adres: 0x%p Wartosc: %f\t\t", ff, *ff); printf("Bajty: 0x%X 0x%X 0x%X 0x%X\n", *(cc-12), *(cc-13), *(cc-14), *(cc-15));
    printf("Zmienna lokalna d: Adres: 0x%p Wartosc: %lf\t", dd, *dd);   printf("Bajty: 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X 0x%X\n", *(cc-16), *(cc-17), *(cc-18), *(cc-19), *(cc-20), *(cc-21), *(cc-22), *(cc-23));
}

