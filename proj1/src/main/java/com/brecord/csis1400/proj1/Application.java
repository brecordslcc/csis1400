package com.brecord.csis1400.proj1;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Application {


    private static int randomNumber;
    private static Scanner scanner;
    private static boolean debugMode;

    public static void main(final String[] args) throws InterruptedException, IOException {
        scanner = new Scanner(System.in);
        boolean playAgain = true;

        printMessageDuration("Welcome to Guess The Number!", 750);
        Thread.sleep(250);

        processDebug();

        while (playAgain) {
            String guess;
            Integer guessInt = -1,
                    guessCount = 0;

            randomNumber = new Random().nextInt(1000) + 1;

            if (debugMode)
                printMessageDuration(String.format("<DEBUG>\tRandom number to guess: %s", randomNumber), 500);

            do {
                if (guessInt > 0) {
                    printMessageDuration(
                            guessInt > randomNumber
                                    ? "Too high, try again."
                                    : "Too low, try again.", 500);
                }

                printMessageDuration("\n\nGuess a number: ", 250);

                guess = scanner.next();

                try {
                    guessInt = Integer.parseInt(guess);
                    guessCount++;
                } catch (final NumberFormatException ex) {
                    printMessageDuration(String.format("\n<ERROR> '%s' is not a valid input.", guess), 500);
                    guessInt = -1;
                }
            } while (guessInt != randomNumber);

            printMessageDuration(String.format("Congratulations, you guessed the number after only %s guesses!", guessCount), 500);

            System.out.println("\n\n");
            Thread.sleep(250);
            printMessageDuration("Play again? [Y/N]: ", 250);

            playAgain = !scanner.next().equalsIgnoreCase("N");
        }
    }

    private static void processDebug() throws InterruptedException, IOException {
        printMessageDuration("\n\nDEBUG MODE? [Y/N]: ", 100);
        final String inptut = scanner.next();
        if (inptut.trim().equalsIgnoreCase("Y"))
            debugMode = true;
    }

    private static void printMessageDuration(final String msg, final double duration) throws InterruptedException, IOException {
        final double start = System.currentTimeMillis();
        final int msgLength = msg.length();
        long current = System.currentTimeMillis();
        int charCount;
        StringBuilder oldMsg = new StringBuilder();
        String newMsg;

        do {
            Thread.sleep(10);
            charCount = (int) Math.ceil(msgLength * ((current - start) / duration));
            newMsg = msg.substring(oldMsg.length(), charCount);

            System.out.print(newMsg);

            oldMsg.append(msg.substring(oldMsg.length(), charCount));
        } while ((current = System.currentTimeMillis()) - start <= duration);

        if (!msg.equals(oldMsg.toString()))
            System.out.print("\r".concat(msg));
    }
}
