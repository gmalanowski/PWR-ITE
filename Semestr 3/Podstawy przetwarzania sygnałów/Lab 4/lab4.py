import time

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

    N = 300
    n = np.arange(N)
    k = n.reshape((N, 1))

    start_time_dft = time.perf_counter()
    exponent = -2j * np.pi * k * n / N
    dft_result = np.dot(np.exp(exponent), combined_signal)
    end_time_dft = time.perf_counter()

    start_time_fft = time.perf_counter()
    fft_result = np.fft.fft(combined_signal)
    end_time_fft = time.perf_counter()

    print("Execution time of DFT: ", end_time_dft - start_time_dft, "s")
    print("Execution time of FFT: ", end_time_fft - start_time_fft, "s")

    freq = np.fft.fftfreq(N, 1 / sampling_rate)
    half_n = N // 2
    dft_half = dft_result[:half_n]
    freqs_half = freq[:half_n]

    plt.figure(figsize=(14, 6))

    plt.subplot(2, 1, 1)
    plt.plot(t, combined_signal, label="Signal", color="blue")
    plt.title("Signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(2, 1, 2)
    plt.stem(freqs_half, np.abs(dft_half), linefmt="r-", markerfmt="ro", basefmt="k-", label="DFT")
    plt.title("DFT (First Half of Spectrum)")
    plt.ylabel("Amplitude")
    plt.xlabel("Frequency [Hz]")
    plt.grid(True)

    plt.tight_layout()
    plt.show()


if __name__ == '__main__':
    main()
