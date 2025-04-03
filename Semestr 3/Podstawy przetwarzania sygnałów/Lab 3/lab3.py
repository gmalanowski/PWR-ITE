import numpy as np
import matplotlib.pyplot as plt


def main():
    duration_time = 3
    sampling_rate = 100
    frequency = 7
    amplitude = 5

    frequencyA = 14
    frequencyB = 3.5

    t = np.linspace(0, duration_time, int(duration_time * sampling_rate), endpoint=False)
    base_signal = amplitude * np.sin(2 * np.pi * frequency * t)
    signalA = amplitude * np.sin(2 * np.pi * frequencyA * t)
    signalB = amplitude * np.sin(2 * np.pi * frequencyB * t)
    combined_signal = base_signal + signalA + signalB

    plt.figure(figsize=(12,8))
    plt.subplot(2,1,1)
    plt.plot(t, base_signal, color = 'blue')
    plt.title("Base signal")
    plt.xlabel("Time")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(2,1,2)
    plt.plot(t, combined_signal, color = 'red')
    plt.title("Combined signal")
    plt.xlabel("Time")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.tight_layout()
    plt.show()

if __name__ == '__main__':
    main()
