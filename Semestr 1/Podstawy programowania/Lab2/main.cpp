/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 2
Data: 8 listopada 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

void zadanie1();
void zadanie2A();
void zadanie2B();
void zadanie3();
void zadanie4();
void zadanie5();

int main() {
    std::cout << "Autor: Gabriel Malanowski (PN/P 13:15)" << std::endl;

    unsigned short zadanie;

    do
    {
        std::cout << std::endl;
        std::cout << "-------------\nLaboratorium 2\n1 -> Zadanie 1\n2 -> Zadanie 2 (wariant tylko dla systemu dziesietnego)\n6 -> Zadanie 2 (wariant dla wszystkich systemow pozycyjnych)\n3 -> Zadanie 3\n4 -> Zadanie 4\n5 -> Zadanie 5\n0 -> Koniec programu\n-------------\n";
        std::cout << "Wybierz zadanie (1-6) lub 0 aby zakonczyc program: ";
        std::cin >> zadanie;
        std::cout << std::endl;

        switch (zadanie)
        {
            case 1:
                zadanie1();
                break;
            case 2:
                zadanie2A();
                break;
            case 3:
                zadanie3();
                break;
            case 4:
                zadanie4();
                break;
            case 5:
                zadanie5();
                break;
            case 6:
                zadanie2B();
                break;
            case 0:
                break;
            default:
                std::cout << "Podano zla opcje. Sprobuj jeszcze raz. " << std::endl;
                break;
        }

        fflush(stdin);

    }while(zadanie != 0);

    return 0;
}

void zadanie1()
{
    unsigned short wiersze, kolumny;
    printf("Program drukujacy tabliczke mnozenia \n");

    // Maksymalna liczbe wierszy i kolumn wyliczono na podstawie rozmiaru okna standardowej konsoli systemu Windows 11
    do
    {
        printf("Podaj liczbe wierszy (max. 19): "); scanf("%hu", &wiersze);
        printf("Podaj liczbe kolumn (max. 23): "); scanf("%hu", &kolumny);
        if(wiersze > 19 && kolumny > 23) printf("Nieprawidlowa liczba. Sprobuj ponownie\n");
    } while (wiersze > 19 && kolumny > 23);

    printf("\n    |");
    for(unsigned short kol = 1; kol <= kolumny; ++kol) printf("%5hu", kol);
    printf("\n----|");
    for(unsigned short kol = 1; kol <= kolumny; ++kol) printf("-----");
    printf("\n");
    for(unsigned short wier = 1; wier <= wiersze; ++wier)
    {
        printf("%3hu |", wier);
        for(unsigned short kol = 1; kol <= kolumny; ++kol) printf(" %4hu", wier*kol);
        printf("\n");
    }
}

void zadanie2A() // Wariant dla systemu dziesietnego
{
    using namespace std;
    unsigned long liczba;
    unsigned long suma_cyfr = 0;
    cout << "Program obliczajacy sume cyfr danej liczby w systemie dziesietnym" << endl;
    cout << "Wprowadz liczbe: "; cin >> liczba;

    while(liczba != 0)
    {
        suma_cyfr += liczba%10;
        liczba /= 10;
    }
    cout << "Suma cyfr: " << suma_cyfr << endl;
}

void zadanie2B() // Wariant dla dowolnego systemu pozycyjnego
{
    using namespace std;
    unsigned long liczba;
    unsigned long podstawa;
    unsigned long suma_cyfr = 0;
    cout << "Program obliczajacy sume cyfr danej liczby w dowolnym systemie pozycyjnym" << endl; // W systemach o wyzszej podstawie od 26, dla dostatecznie duzych liczb zapis za pomoca liter alfabetu moze nie byc mozliwy
    cout << "Wprowadz liczbe: "; cin >> liczba;
    cout << "Wprowadz podstawe systemu: "; cin >> podstawa;

    string liczba_skowertowana = "";

    while(liczba != 0)
    {
        char znak;
        unsigned long reszta = liczba % podstawa;
        suma_cyfr += reszta;
        if(reszta < 10) znak = reszta + 48;
        else znak = reszta + 55;
        liczba_skowertowana += znak;
        liczba /= podstawa;
    }

    cout << "Podana liczba w systemie o podstawie " << podstawa << " to ";
    for(int pozycja = liczba_skowertowana.size(); pozycja > 0; --pozycja) cout << liczba_skowertowana[pozycja-1];
    cout << endl;
    cout << "Suma cyfr: " << suma_cyfr << endl;
}

void zadanie3()
{
    using namespace std;
    double eps, s1 = 0, s2 = 2;
    cout << "Program wyswietlajacy wartosc sumy danych szeregow z zadana dokladnoscia" << endl;
    cout << "Wprowadz wartosc eps: "; cin >> eps;

    // Szereg S1
    double wyraz_szeregu = -1;
    double k = 1;

    do
    {
        wyraz_szeregu = (wyraz_szeregu * -1) / (2*k++ - 1);
        s1 += wyraz_szeregu;
    } while (fabs(wyraz_szeregu) >= eps);

    s1 *= 4;

    //Szereg S2
    wyraz_szeregu = 1;
    k = 2;

    do
    {
        wyraz_szeregu = wyraz_szeregu / k++;
        s2 += wyraz_szeregu;
    } while (fabs(wyraz_szeregu) >= eps);

    cout << "Wartosc S1 = " << s1 << endl << "Wartosc S2 = " << s2 << endl;
}

void zadanie4()
{
    using namespace std;
    int min, max, ilosc, suma_dodatnich = 0, suma_ujemnych = 0, ilosc_dodatnich = 0, ilosc_ujemnych = 0; double srednia_dodatnich, srednia_ujemnych;

    cout << "Program obliczajacy srednia liczb generowanych w sposob losowy" << endl;
    cout << "Podaj minimalny zakres losowania: "; cin >> min;
    cout << "Podaj maksymalny zakres losowania: "; cin >> max;
    cout << "Podaj ilosc liczb do wygenerowania: "; cin >> ilosc;

    srand(time(NULL));

    for(int licznik_wygenerowanych_liczb = 0; licznik_wygenerowanych_liczb < ilosc; ++licznik_wygenerowanych_liczb)
    {
        int losowa = min + rand()%(max - min + 1);
        cout << losowa << endl;
        if(losowa >= 0) // Zalozenie ze 0 nalezy do zbioru liczb dodatnich
        {
            suma_dodatnich += losowa;
            ++ilosc_dodatnich;
        }
        else
        {
            suma_ujemnych += losowa;
            ++ilosc_ujemnych;
        }
    }

    cout << "PODSUMOWANIE:" << endl;
    cout << "\tLiczby dodatnie:" << endl;
    if(ilosc_dodatnich == 0) cout << "\t\tBrak wystapien" << endl;
    else
    {
        srednia_dodatnich = ((double)suma_dodatnich) / ilosc_dodatnich;
        cout << "\t\tIlosc: " << ilosc_dodatnich << " Srednia: " << srednia_dodatnich << endl;
    }
    cout << "\tLiczby ujemne:" << endl;
    if(ilosc_ujemnych == 0) cout << "\t\tBrak wystapien" << endl;
    else
    {
        srednia_ujemnych = ((double)suma_ujemnych) / ilosc_ujemnych;
        cout << "\t\tIlosc: " << ilosc_ujemnych << " Srednia: " << srednia_ujemnych << endl;
    }
}

void zadanie5()
{
    using namespace std;

    unsigned short szerokosc_szachownicy, wysokosc_szachownicy, szerokosc_pola, wysokosc_pola;

    cout << "Program drukujacy dwukolorowa szachownice" << endl;
    cout << "Podaj szerokosc szachownicy: "; cin >> szerokosc_szachownicy;
    cout << "Podaj wysokosc szachownicy: "; cin >> wysokosc_szachownicy;
    cout << "Podaj szerokosc pola: "; cin >> szerokosc_pola;
    cout << "Podaj wysokosc pola: "; cin >> wysokosc_pola;

    cout << "\033[32m";
    bool czy_drugi_kolor = true;
    for(unsigned short wys = 1; wys <= (wysokosc_pola * wysokosc_szachownicy); ++wys)
    {
        for(unsigned short szer = 1; szer <= (szerokosc_pola * szerokosc_szachownicy); ++szer)
        {
            cout << "#";
            if(szer % szerokosc_pola == 0)
            {
                if(czy_drugi_kolor == true)
                {
                    cout << "\033[31m";
                    czy_drugi_kolor = false;
                }
                else
                {
                    cout << "\033[32m";
                    czy_drugi_kolor = true;
                }
            }
        }
        if(wys % wysokosc_pola == 0)
        {
            if(czy_drugi_kolor == true)
            {
                cout << "\033[31m";
                czy_drugi_kolor = false;
            }
            else
            {
                cout << "\033[32m";
                czy_drugi_kolor = true;
            }
        }
        cout << endl;
    }
    cout << "\033[0m";
}