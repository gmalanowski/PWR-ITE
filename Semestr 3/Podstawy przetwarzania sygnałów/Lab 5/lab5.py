import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import sosfiltfilt, butter, spectrogram


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

    nyquist = 0.5 * sampling_rate

    # Low-pass filter
    cutoff_low = 5 / nyquist
    sos_low = butter(4, cutoff_low, btype='low', analog=False, output="sos")
    low_pass_signal = sosfiltfilt(sos_low, combined_signal)

    # Band-pass filter
    cutoff_band = [6 / nyquist, 8 / nyquist]
    sos_band = butter(4, cutoff_band, btype='band', analog=False, output="sos")
    band_pass_signal = sosfiltfilt(sos_band, combined_signal)

    # High-pass filter
    cutoff_high = 10 / nyquist
    sos_high = butter(4, cutoff_high, btype='high', analog=False, output="sos")
    high_pass_signal = sosfiltfilt(sos_high, combined_signal)

    plt.figure(figsize=(14, 6))

    plt.subplot(4, 1, 1)
    plt.plot(t, combined_signal, label="Signal (50 Hz + 150 Hz + 300 Hz)", color="blue")
    plt.title("Signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 2)
    plt.plot(t, low_pass_signal, label="Low-pass filter", color="green")
    plt.title("Low-pass signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 3)
    plt.plot(t, band_pass_signal, label="Band-pass filter", color="yellow")
    plt.title("Band-pass signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 4)
    plt.plot(t, high_pass_signal, label="High-pass filter", color="purple")
    plt.title("High-pass signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.tight_layout()
    plt.show()

    # Compute FFTs sequentially
    fft_result = np.fft.fft(combined_signal)
    freq = np.fft.fftfreq(len(combined_signal), 1 / sampling_rate)
    half_n = len(combined_signal) // 2

    fft_combined = np.abs(fft_result[:half_n])
    freq_combined = freq[:half_n]

    fft_low = np.abs(np.fft.fft(low_pass_signal)[:half_n])
    fft_high = np.abs(np.fft.fft(high_pass_signal)[:half_n])
    fft_band = np.abs(np.fft.fft(band_pass_signal)[:half_n])

    plt.figure(figsize=(14, 6))

    plt.subplot(4, 1, 1)
    plt.stem(freq_combined, fft_combined, label="Original Signal", linefmt="b-", markerfmt="bo", basefmt="k-")
    plt.title("FFT Signal")
    plt.xlabel("Frequency [Hz]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 2)
    plt.stem(freq_combined, fft_low, label="Low-pass Filtered", linefmt="g-", markerfmt="go", basefmt="k-")
    plt.title("FFT Low-pass Signal")
    plt.xlabel("Frequency [Hz]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 3)
    plt.stem(freq_combined, fft_band, label="Band-pass Filtered", linefmt="r-", markerfmt="ro", basefmt="k-")
    plt.title("FFT Band-pass Signal")
    plt.xlabel("Frequency [Hz]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.subplot(4, 1, 4)
    plt.stem(freq_combined, fft_high, label="High-pass Filtered", linefmt="m-", markerfmt="mo", basefmt="k-")
    plt.title("FFT High-pass Signal")
    plt.xlabel("Frequency [Hz]")
    plt.ylabel("Amplitude")
    plt.grid(True)

    plt.tight_layout()
    plt.show()

    plt.figure(figsize=(14, 6))

    plt.subplot(1, 1, 1)
    f, t_spec, Sxx = spectrogram(combined_signal, fs=sampling_rate, nperseg=128, noverlap=64)
    plt.pcolormesh(t_spec, f, 10 * np.log10(Sxx), shading='gouraud')
    plt.colorbar(label='Power [dB]')
    plt.title("Spectrogram of Original Signal")
    plt.xlabel("Time [s]")
    plt.ylabel("Frequency [Hz]")

    plt.tight_layout()
    plt.show()


if __name__ == '__main__':
    main()
