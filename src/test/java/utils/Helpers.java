package utils;

import java.util.Random;

public class Helpers {
    public static Integer randomNumber() {
        return randomNumber(9999);
    }

    public static Integer randomNumber(int maxValue) {
        int min = 0;

        Random random = new Random();
        return random.nextInt(maxValue - min + 1) + min;
    }
}
