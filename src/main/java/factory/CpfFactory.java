package factory;

import java.util.Random;

public class CpfFactory {

    public static String generateCPF() {
        Random random = new Random();
        int[] digits = new int[11];

        for (int i = 0; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        digits[9] = calculateVerifierDigit(digits, 10);
        digits[10] = calculateVerifierDigit(digits, 11);

        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                digits[0], digits[1], digits[2],
                digits[3], digits[4], digits[5],
                digits[6], digits[7], digits[8],
                digits[9], digits[10]);
    }

    private static int calculateVerifierDigit(int[] digits, int weight) {
        int sum = 0;
        for (int i = 0; i < weight - 1; i++) {
            sum += digits[i] * (weight - i);
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }
}
