package bullscows;

import java.util.*;

public class Main {
    public static String SECRET_CODE;

    public static char[] sc_array = {};

    static int codeLenght;
    static int possibleChars;


    public static void main(String[] args) {
        startGame();

        grader();

    }

    private static void startGame() {
        System.out.println("Please, enter the secret code's length:\n");
        String codelenstr = new Scanner(System.in).nextLine();
        try {
            codeLenght = Integer.parseInt(codelenstr);
        } catch (NumberFormatException e) {
            System.out.printf("Error: %s isn't a valid number.", codelenstr);
            System.exit(0);
        }
        if (codeLenght > 37) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
            codeLenght = new Scanner(System.in).nextInt();
        } else if (codeLenght <= 0) {
            System.out.println("Error");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        possibleChars = new Scanner(System.in).nextInt();

        if (codeLenght > possibleChars) {
            System.out.printf("Error: it's not possible to generate a code with a length of %s with %s unique symbols.\n", codeLenght, possibleChars);
            System.exit(0);
        } else if (possibleChars > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
            System.exit(0);

        }
        System.out.println("Okay, let's start a game!");
        SECRET_CODE = codeGeneretor(codeLenght, possibleChars);
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < codeLenght; i++)
            System.out.print("*");
        System.out.printf(" (0-9, a-%c).", ((char) (possibleChars + 'a' - 11)));

        System.out.println(SECRET_CODE);
        sc_array = SECRET_CODE.toCharArray();
    }

    public static String codeGeneretor(int lenght, int possibleChar) {


        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        ArrayList<Character> list = new ArrayList<>(lenght);

        int i = 0;

        while (i < lenght) {
            int a = random.nextInt(possibleChar);


            if (a < 10) {
                while (!list.contains((char) (a + '0'))) {
                    list.add((char) (a + '0'));
                    i++;
                }
            } else {
                while (!list.contains((char) (a + 'a' - 10))) {
                    list.add((char) (a + 'a' - 10));
                    i++;
                }
            }


        }
        for (char c : list) {
            sb.append(c);
        }

        return sb.toString();

    }


    public static void grader() {
        int bulls = 0;
        int cows = 0;
        do {

            bulls = 0;
            cows = 0;
            String input = new Scanner(System.in).nextLine();

            char[] list = input.toCharArray();


            for (int i = 0; i < input.length(); i++) {

                for (int j = 0; j < codeLenght; j++) {

                    if (list[i] == sc_array[j]) {
                        if (i == j)
                            bulls++;
                        else
                            cows++;
                    }


                }


            }

            if (bulls == 0 && cows == 0)
                System.out.println("Grade: None.");
            else
                System.out.println("Grade: " + (bulls > 0 ? bulls + " bull(s)" : "") + (cows > 0 ? cows + " cow(s)" : ""));

        } while (!(bulls == codeLenght));
        System.out.println("Congratulations! You guessed the secret code.");

    }

}