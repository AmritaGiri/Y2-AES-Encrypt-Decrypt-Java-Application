package classical.shift.cipher.java.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class cipher {

    public static void main(String ar[]) {
        int option = 1;
        try (Scanner key_inp = new Scanner(System.in)) {
            // option = key_inp.nextInt();
            String lines[];
            try {
                lines = readLines("ciphertext.txt");
                while (option != 3) {
                    System.out.print("\nEnter  \t| 1: Task 1 | 2: Task 2 | 3: Exit | :");
                    option = key_inp.nextInt();
                    System.out.println("");

                    switch (option) {
                        case 1: {
                            System.out.println("task 1\n output:");
                            System.out.println(decryptKey(lines[0], 3));
                            break;
                        }
                        case 2: {
                            System.out.println("task 2");
                            int k = decryptExhaustive(lines[1], "DONE");
                            if (k != -1) {
                                System.out.println("key: " + String.valueOf(k));
                                System.out.println("output:");
                                System.out.println(decryptKey(lines[1], k));
                            } else {
                                System.out.println("no key found for test term");
                            }
                            break;
                        }
                        case 3: {
                            System.out.println("exiting...");
                            break;
                        }
                        default: {
                            System.out.println("input error, try again");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("error occurred");
                e.printStackTrace();
            }
        }
    }

    public static String decryptKey(String inp, int k) {
        char[] characters = inp.toUpperCase().toCharArray();
        char[] ch = new char[characters.length];
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] >= 'A' && characters[i] <= 'Z') {
                ch[i] = (char) (Math.floorMod(characters[i] - 65 - k, 26) + 65);
            }
        }
        String str = new String(ch);

        return str;
    }

    public static int decryptExhaustive(String inp, String tst) {
        String dec;
        int key_f = -1;
        for (int i = 1; i <= 25; i++) {
            dec = decryptKey(inp, i);
            if (dec.contains(tst)) {
                key_f = i;
                break;
            }
        }
        return key_f;
    }

    public static String[] readLines(String f_name) throws IOException {
        FileReader fileReader = new FileReader(f_name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        bufferedReader.close();

        return lines.toArray(new String[lines.size()]);
    }

}
