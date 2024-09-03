package utils;

import java.util.Random;

public class Helpers {
    public static Integer randomNumber() {
        // Generate a random number with 4 digits
        int min = 1000; // Minimum value (inclusive)
        int max = 9999; // Maximum value (inclusive)

        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
