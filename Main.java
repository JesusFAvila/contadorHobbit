package Tema9.contadorHobbit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) throws IOException {
        contador();
    }

    public static void contador()  throws IOException {
        InputStream input = new FileInputStream("leeme.txt");
        PrintStream output = new PrintStream("output.txt");
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();

        try {
            int data = input.read();
            while (data != -1) {
                StringBuilder sb = new StringBuilder();
                while (Character.isLetter((char) data)) {
                    sb.append((char) data);
                    data = input.read();
                }
                String word = sb.toString();
                if (!word.isEmpty()) {
                    if (!map.containsKey(word)) {
                        map.put(word, 1);
                        list.add(word);
                    } else {
                        int count = map.get(word);
                        map.put(word, count + 1);
                    }
                }
                data = input.read();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Collections.sort(list);
        for (String word : list) {
            output.println(word + ": " + map.get(word));
        }

        try {
            input.close();
            output.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        out.println("Fin contador");

    }

}


