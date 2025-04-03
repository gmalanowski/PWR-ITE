import math

def input_coefficients():
    while True:
        try:
            a = float(input("Enter coefficient a: "))
            if a == 0:
                raise ValueError("Coefficient a cannot be 0.")
            b = float(input("Enter coefficient b: "))
            c = float(input("Enter coefficient c: "))
            return a, b, c
        except ValueError as e:
            print("Invalid type of data.", e)
            continue

def calculate_roots(a, b, c):
    delta = b**2 - 4*a*c
    if delta < 0:
        print("There aren't any roots")
    elif delta == 0:
        x = (-b)/(2*a)
        print("x = ",x)
    else:
        x1 = (-b - math.sqrt(delta)) / (2 * a)
        x2 = (-b + math.sqrt(delta)) / (2 * a)
        print("x1 = ",x1," x2 = ",x2)

def main():
    while True:
        a,b,c = input_coefficients()
        calculate_roots(a,b,c)
        continueCalculations = input("Do you want to continue? (y/n): ")
        if continueCalculations != "y":
            break


if __name__ == '__main__':
    main()
