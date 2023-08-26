import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger countLen3 = new AtomicInteger(0);
    private static AtomicInteger countLen4 = new AtomicInteger(0);
    private static AtomicInteger countLen5 = new AtomicInteger(0);

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isPolindrom(texts[i])) {
                    incCounter(texts[i].length());
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isOneChars(texts[i])) {
                    incCounter(texts[i].length());
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (isAscending(texts[i])) {
                    incCounter(texts[i].length());
                }
            }
        }).start();
        System.out.println("Красивых слов с длиной 3: " + countLen3.get());
        System.out.println("Красивых слов с длиной 4: " + countLen4.get());
        System.out.println("Красивых слов с длиной 5: " + countLen5.get());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPolindrom(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    public static boolean isOneChars(String word) {
        char chars[] = word.toCharArray();
        boolean bool = true;
        if (chars.length > 0) {
            for (char chr : chars) {
                if (chars[0] != chr) {
                    bool = false;
                    break;
                }
            }
        } else {
            bool = false;
        }
        return bool;
    }

    public static boolean isAscending(String word) {
        char chars[] = word.toCharArray();
        boolean bool = true;
        if (chars.length > 0) {
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] > chars[i + 1]) {
                    bool = false;
                    break;
                }
            }
        } else {
            bool = false;
        }
        return bool;
    }

    public static void incCounter(int countWord) {
        switch (countWord) {
            case 3:
                countLen3.getAndIncrement();
                break;
            case 4:
                countLen4.getAndIncrement();
                break;
            case 5:
                countLen5.getAndIncrement();
                break;
        }
    }
}