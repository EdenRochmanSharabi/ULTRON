package Deepspell.Decoded;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OneHotDecoder {
    private Map<String, Character> decoding;

    public OneHotDecoder() {
        decoding = new HashMap<>();
    }

    public void loadDecoding(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String word = parts[0].trim();
                String vector = parts[1].trim();
                int[] oneHot = getOneHotVector(vector);
                char decodedChar = getDecodedChar(oneHot);
                decoding.put(word, decodedChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String decode(String oneHotVector) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < oneHotVector.length(); i += 11) {
            String substring = oneHotVector.substring(i, i + 11);
            int[] oneHot = getOneHotVector(substring);
            char decodedChar = getDecodedChar(oneHot);
            sb.append(decodedChar);
        }
        return sb.toString();
    }

    private int[] getOneHotVector(String vector) {
        String[] parts = vector.replaceAll("\\[|\\]", "").split(", ");
        int[] oneHot = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            oneHot[i] = Integer.parseInt(parts[i]);
        }
        return oneHot;
    }

    private char getDecodedChar(int[] oneHot) {
        for (Map.Entry<String, Character> entry : decoding.entrySet()) {
            int[] encoded = getOneHotVector(entry.getKey());
            if (Arrays.equals(oneHot, encoded)) {
                return entry.getValue();
            }
        }
        return '-';
    }

    public static void main(String[] args) {
        OneHotDecoder decoder = new OneHotDecoder();
        decoder.loadDecoding("src/main/java/Deepspell/trainingSets/oneHotEncodedMiniTest.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/Deepspell/trainingSets/oneHotEncodedMiniTest.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/Deepspell/Decoded/miniTest_decoded.txt"))) {
             String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                String word = parts[0];
                String oneHotVector = parts[1];
                String decodedWord = decoder.decode(oneHotVector);
                writer.println(word + ": " + decodedWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
