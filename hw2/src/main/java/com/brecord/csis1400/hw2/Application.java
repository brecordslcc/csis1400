package com.brecord.csis1400.hw2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final List<String> titles = Arrays.asList("number", "square", "cube");

        for (byte i = -1, columns = 3, columnWidth = 8; i <= 10; i++) {
            sb.append("\n");
            for (byte j = 0; j < columns; j++) {
                final String val = String.valueOf((short) Math.pow(i, j + 1));
                for (byte k = 0; k < columnWidth; k++) {
                    if (i == -1)
                        sb.append(titles.get(j).length() <= k ? " " : titles.get(j).charAt(k));
                    else
                        sb.append(val.length() <= k ? " " : val.charAt(k));
                }
            }
        }

        sb.append("\nPress any key to continue . . .");
        System.out.println(sb.toString());
        System.in.read();
    }
}
