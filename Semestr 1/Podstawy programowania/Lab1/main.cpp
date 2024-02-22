/*
Autor: Gabriel Malanowski
Grupa: PN/P 13:15
Temat: Laboratorium 1
Data: 24 października 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <math.h>

void zadanie1();
void zadanie2();
void zadanie3();
void zadanie4();

int main() {
    std::cout << "Autor: Gabriel Malanowski (PN/P 13:15)" << std::endl << std::endl;

    bool czy_zakonczyc = false;

    while(czy_zakonczyc==false)
    {
        fflush(stdin);
        short zadanie;
        std::cout << "Aby zakonczyc wybierz 0. Wybierz zadanie (0-4): ";
        std::cin >> zadanie;

        switch(zadanie)
        {
            case 1:
                zadanie1();
                system("PAUSE");
                break;
            case 2:
                zadanie2();
                system("PAUSE");
                break;
            case 3:
                zadanie3();
                system("PAUSE");
                break;
            case 4:
                zadanie4();
                system("PAUSE");
                break;
            case 0:
                czy_zakonczyc = true;
                break;
            default:
                std::cout << "Wprowadzono zly numer zadania. Sprobuj ponownie." << std::endl;
                break;
        }
        std::cout << std::endl;
    }

    return 0;
}

void zadanie1()
{
    using namespace std;
    float a,b,c;
    cout << "Podaj wspolczynniki a, b, c" << endl;
    cin >> a >> b >> c;

    if(a==0)
    {
        cout << "To nie jest rownanie kwadratowe." << endl;
    }
    else
    {
        float delta = pow(b,2) - 4*a*c;

        if(delta < 0)
        {
            cout << "Brak pierwiastkow rzeczywistych" << endl;
        }
        else if(delta == 0)
        {
            float x = -b/(2*a);
            cout << "x1 = x2 = " << x << endl;
        }
        else
        {
            float x1 = (-b-sqrt(delta))/(2*a);
            float x2 = (-b+sqrt(delta))/(2*a);
            cout << "x1 = " << x1 << " x2 = " << x2 << endl;
        }

    }
}

void zadanie2()
{
    using namespace std;
    int dzien, miesiac, rok;
    cout << "Wprowadz dzien, miesiac, rok" << endl;
    cin >> dzien >> miesiac >> rok;
    cout << "Utworzona data: " << dzien << "-" << miesiac << "-" << rok << endl;

    if(miesiac > 12 || miesiac < 1 || dzien < 1 || dzien > 31 || rok < 1) // Sprawdzenie, czy zmienne zawieraja sie w odpowiednich przedzialach
    {
        cout << "Data niepoprawna" << endl;
    }
    else if(miesiac%2 == 1 || miesiac == 8) // Sprawdzenie, czy dany miesiac jest parzysty lub czy to jest sierpien, jesli tak to ma maksymalnie 31 dni
    {
        if(dzien < 32)
        {
            cout << "Data poprawna" << endl;
        }
        else
        {
            cout << "Data  niepoprawna" << endl;
        }
    }
    else
    {
        if(miesiac != 2) // Sprawdzenie, czy miesiac nie jest lutym
        {
            if(dzien < 30)
            {
                cout << "Data poprawna" << endl;
            }
            else
            {
                cout << "Data niepoprawna" << endl;
            }
        }
        else
        {
            if(rok%4 != 0 || (rok%100 == 0 && rok%400 != 0)) // Sprawdzenie, czy jest to rok zwykly
            {
                if(dzien < 29) // jesli tak to ma maksymalnie 28 dni
                {
                    cout << "Data poprawna" << endl;
                }
                else
                {
                    cout << "Data niepoprawna" << endl;
                }
            }
            else
            {
                if(dzien < 30) // jesli nie to moze miec maksymalnie 29 dni
                {
                    cout << "Data poprawna" << endl;
                }
                else
                {
                    cout << "Data niepoprawna" << endl;
                }
            }
        }
    }
}

void zadanie3()
{
    using namespace std;
    unsigned int a,b,c;
    cout << "Wprowadz dlugosci a,b,c" << endl;
    cin >> a >> b >> c;

    if(a + b > c && b + c > a && a + c > b) // Sprawdzenie, czy zachodzi tzw. warunek trojkata
    {
        cout << "Z podanych odcinkow mozna stworzyc trojkat" << endl;

        // Kolejne sprawdzenia typu trojkata
        if(a == b || b == c || a == c)
        {
            cout << "Trojkat rownoramienny" << endl;
        }
        if(a == b && b == c && a == c)
        {
            cout << "Trojkat rownoboczny" << endl;
        }
        if(pow(a,2) + pow(b,2) == pow(c,2) || pow(c,2) + pow(b,2) == pow(a,2) || pow(a,2) + pow(c,2) == pow(b,2))
        {
            cout << "Trojkat prostokatny" << endl;
        }
        if(pow(a,2) + pow(b,2) < pow(c,2) || pow(c,2) + pow(b,2) < pow(a,2) || pow(a,2) + pow(c,2) < pow(b,2))
        {
            cout << "Trojkat rozwartokatny" << endl;
        }
        if(pow(a,2) + pow(b,2) > pow(c,2) && pow(c,2) + pow(b,2) > pow(a,2) && pow(a,2) + pow(c,2) > pow(b,2))
        {
            cout << "Trojkat ostrokatny" << endl;
        }
    }
    else
    {
        cout << "Z podanych odcinkow NIE mozna stworzyc trojkata" << endl;
    }
}

void zadanie4()
{
    float minr, maxr;
    int wiersze;
    printf("Wprowadz minimalny promien kola:");
    scanf("%f", &minr);
    printf("Wprowadz maksymalny promien kola:");
    scanf("%f", &maxr);
    printf("Wprowadz liczbe wierszy:");
    scanf("%d", &wiersze);

    for(int i = 0; i<42; i++) printf("="); // Drukowanie naglowka tabeli
    printf("\n");

    printf("| Lp | promien | obwod kola | pole kola  |\n");

    for(int i = 0; i<42; i++) printf("=");
    printf("\n");

    float podzialka = (maxr - minr) / (wiersze-1); // Wskazuje o jaka wartosc nalezy powiekszac promien w tabeli

    for(int i = 1; i<=wiersze; i++)
    {
        printf("| %2d | %7.2f | %10.2f | %10.2f |\n", i, minr, 2*M_PI*minr, M_PI*pow(minr,2));
        minr += podzialka;
    }

    for(int i = 0; i<42; i++) printf("=");
    printf("\n");
}