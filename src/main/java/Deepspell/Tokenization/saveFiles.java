package Deepspell.Tokenization;

import Deepspell.Tokenization.Multimap;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;


public class saveFiles {
    public static void saveToFile(String fileName, Multimap<String, String> data) {
        try (PrintWriter out = new PrintWriter("src/main/java/Deepspell/Encoded" + fileName)) {
            for (Map.Entry<String, Collection<String>> entry : data.entrySet()) {
                String key = entry.getKey();
                Collection<String> values = entry.getValue();
                out.print(key + ": ");
                out.println(String.join(", ", values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
        }
        public static void saveToFile(String fileName, Map<Character, int[]> data, int n) {
            try (PrintWriter out = new PrintWriter("src/main/java/Deepspell/Encoded/" + fileName)) {
                for (Map.Entry<Character, int[]> entry : data.entrySet()) {
                    char key = entry.getKey();
                    int[] values = entry.getValue();
                    out.print(key + ": ");
                    out.println(Arrays.toString(values));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static void saveToFile(String fileName, Map<?, ?> data) {
            try (PrintWriter out = new PrintWriter("src/main/java/Deepspell/Encoded/" + fileName)) {
                for (Map.Entry<?, ?> entry : data.entrySet()) {
                    Object key = entry.getKey();
                    String[] valueArray = ((String) entry.getValue()).replaceAll("[\\[\\]]", "").split(", ");
                    Collection<String> values = new ArrayList<>(Arrays.asList(valueArray));
                    out.print(key + ": ");
                    out.println(String.join(", ", values));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    public static void saveToFile(String fileName, String data) {
        try (PrintWriter out = new PrintWriter("src/main/java/Deepspell/Encoded/" + fileName)) {
            out.print(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(String fileName, String data) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/main/java/Deepspell/Encoded/" + fileName, true)))) {
            out.print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
