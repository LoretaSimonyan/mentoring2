package utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomData {
    Random random;

    public String randomStringGenerator(int maxLength) {
        random = new Random();
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String string = "";
        for (int i = 0; i < maxLength; i++) {
            int num = random.nextInt(52);
            string += alphabet[num];
        }
        return string;
    }
}
