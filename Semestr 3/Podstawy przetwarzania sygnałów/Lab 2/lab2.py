import time

def get_init_time():

    while True:
        try:
            H = int(input("Input Hours: "))
            M = int(input("Input Minutes: "))
            S = int(input("Input Seconds: "))

            if (H > 23 or H < 0) or (M > 59 or M < 0) or (S > 59 or S < 0):
                print("Invalid input.")
            else:
                return H, M, S
        except ValueError:
            print("Invalid input.")

def print_time(current_time):

    H = ""
    M = ""
    S = ""

    if current_time[0] < 10:
        H = "0" + str(current_time[0])
    else:
        H = str(current_time[0])

    if current_time[1] < 10:
        M = "0" + str(current_time[1])
    else:
        M = str(current_time[1])

    if current_time[2] < 10:
        S = "0" + str(current_time[2])
    else:
        S = str(current_time[2])

    print(f"{H}:{M}:{S}")

def clock_mode(input_time):
    print("Clock mode. Press Ctrl+C to exit.")
    H = input_time[0]
    M = input_time[1]
    S = input_time[2]

    while True:
        print_time((H, M, S))
        time.sleep(1)
        S += 1
        if S == 60:
            S = 0
            M += 1
            if M == 60:
                M = 0
                H += 1
                if H == 24:
                    H = 0


def timer_mode(init_time):
    print("Timer mode")
    H = init_time[0]
    M = init_time[1]
    S = init_time[2]
    while not(H == 0 and M == 0 and S == 0):
        print_time((H, M, S))
        time.sleep(1)
        S -= 1
        if S == -1:
            S = 59
            M -= 1
            if M == -1:
                M = 0
                H -= 1

    print("Timer ends")

    for i in range(5):
        time.sleep(1)
        print("\a")

def main():
    init_time = get_init_time()
    print("Select mode:")
    print("1. Clock mode.")
    print("2. Timer mode.")

    while True:
        try:
            mode = int(input("Mode: "))
            if mode == 1:
                clock_mode(init_time)
            elif mode == 2:
                timer_mode(init_time)
            else:
                print("Wrong input. Enter 1 or 2.")
        except ValueError:
            print("Wrong input. Enter 1 or 2.")

if __name__ == '__main__':
    main()
