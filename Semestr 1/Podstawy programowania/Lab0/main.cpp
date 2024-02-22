/*
Autor: Gabriel Malanowski
Grupa: PN/P 9:15
Temat: Laboratorium 0
Data: 10 października 2023 r.
 */

#include <iostream>
#include <stdio.h>
#include <math.h>

void zadanie1();
void zadanie2();
void zadanie3();

int main() {
    std::cout << "Autor: Gabriel Malanowski (PN/P 9:15)" << std::endl << std::endl;

    zadanie1();
    system("PAUSE");

    zadanie2();
    system("PAUSE");

    zadanie3();
    system("PAUSE");

    return 0;
}

void zadanie1()
{
    // Podpunkt a, biblioteka iostream
    std::cout << "Gabriel Malanowski" << std::endl;
    std::cout << "ul. Programowa 11 50-371 Wroclaw" << std::endl;
    std::cout << "+48 256 102 486" << std::endl;
    std::cout << "malanowski@example.com" << std::endl;
    std::cout << std::endl;

    // Podpunkt b, biblioteka stdio.h
    printf("Jan Przykladowski\n");
    printf("ul. Zielona 64a 45-001 Opole\n");
    printf("+48 205 311 127\n");
    printf("przykladowski@example.com");
    printf("\n\n");
}

void zadanie2()
{
    // Wariant z biblioteka iostream
    int a, b, c;
    int suma, iloczyn;
    float liczba = 3;
    float srednia;

    std::cout << "Podaj pierwsza wartosc:\t"; std::cin >> a;
    std::cout << "Podaj druga wartosc:\t"; std::cin >> b;
    std::cout << "Podaj trzecia wartosc:\t"; std::cin >> c;

    suma = a + b + c;
    iloczyn = a * b * c;
    srednia = suma / liczba;

    std::cout << "Suma: " << suma << std::endl;
    std::cout << "Iloczyn: " << iloczyn << std::endl;
    std::cout << "Srednia arytmetyczna: " << srednia << std::endl;
    std::cout << std::endl;

    // Wariant z biblioteka stdio.h
    int x, y, z;
    int sumaB, iloczynB;
    float liczbaB = 3;
    float sredniaB;

    printf("Podaj pierwsza wartosc:\t"); scanf("%d", &x);
    printf("Podaj druga wartosc:\t"); scanf("%d", &y);
    printf("Podaj trzecia wartosc:\t"); scanf("%d", &z);

    sumaB = x + y + z;
    iloczynB = x * y * z;
    sredniaB = sumaB / liczbaB;

    printf("Suma: %d\n", sumaB);
    printf("Iloczyn: %d\n", iloczynB);
    printf("Srednia arytmetyczna %f\n", sredniaB);
    printf("\n\n");
}

void zadanie3()
{
    // Wariant z biblioteka stdio.h
    float promien;
    printf("Podaj promien kola:\t"); scanf("%f", &promien);

    float obwod = 2 * M_PI * promien;
    float pole = M_PI * pow(promien, 2);

    printf("Obwod kola: %f\n", obwod);
    printf("Pole kola: %f\n", pole);
    printf("\n\n");

    //Wariant z biblioteka iostream
    float promienB;
    std::cout << "Podaj promien kola:\t"; std::cin >> promienB;

    float obwodB = 2 * M_PI * promienB;
    float poleB = M_PI * pow(promienB, 2);

    std::cout << "Obwod kola: " << obwodB << std::endl;
    std::cout << "Pole kola: " << poleB << std::endl;
}