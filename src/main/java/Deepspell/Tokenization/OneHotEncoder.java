package Deepspell.Tokenization;

import java.io.*;
import java.util.*;

public class OneHotEncoder {
    private static String TRAINING_DATA;
    private Map<Character, int[]> encoding;
    private int vectorSize;
    public OneHotEncoder() {
        encoding = new HashMap<>();
        vectorSize = 0;
    }
    public void encodeFullListOfWords(String TRAINING_DATA, int n) {
        try {
            OneHotEncoder.TRAINING_DATA = TRAINING_DATA;
            File file = new File(TRAINING_DATA);
            Scanner scanner = new Scanner(file);
            Set<String> encodedWords = new HashSet<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(": ");
                String word = splitLine[0];
                if (encodedWords.contains(word)) {
                    continue;
                }
                encodeWord(word, n);
                encodedWords.add(word);
                String[] variations = splitLine[1].split(", ");
                for (String variation : variations) {
                    if (!encodedWords.contains(variation)) {
                        encodeWord(variation, n);
                        encodedWords.add(variation);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void encodeWord(String word, int n) {
        StringBuilder encoded = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (!encoding.containsKey(c)) {
                int[] newVector = new int[vectorSize + 1];
                newVector[vectorSize] = 1;
                encoding.put(c, newVector);
                vectorSize++;
            }
            int[] vector = encoding.get(c);
            for (int i = 0; i < vector.length; i++) {
                encoded.append(vector[i]);
                if (i < vector.length - 1) {
                    encoded.append(",");
                }
            }
            encoded.append("|");
        }
        switch (n){
            case 1 -> saveFiles.appendToFile("oneHotEncodedListOfTrainingWords.txt", word + ": " + encoded.toString() + "\n");
            case 2 -> saveFiles.appendToFile("oneHotEncodedListOfTestingWords.txt", word + ": " + encoded.toString() + "\n");
            case 3 -> saveFiles.appendToFile("oneHotEncodedListOfAllWords.txt", word + ": " + encoded.toString() + "\n");
            case 4 -> saveFiles.appendToFile("oneHotEncodedMiniTest.txt", word + ": " + encoded.toString() + "\n");
            case 5 -> saveFiles.appendToFile("oneHotEncodedMiniTest2.txt", word + ": " + encoded.toString() + "\n");
        }
    }
    public double[][] encodeWord(String word) {
        double[][] encoded = new double[word.length()][vectorSize];
        int index = 0;
        for (char c : word.toCharArray()) {
            if (!encoding.containsKey(c)) {
                int[] newVector = new int[vectorSize + 1];
                newVector[vectorSize] = 1;
                encoding.put(c, newVector);
                vectorSize++;
            }
            int[] vector = encoding.get(c);
            for (int i = 0; i < vector.length; i++) {
                encoded[index][i] = vector[i];
            }
            index++;
        }
        return encoded;
    }

    private int[] combineVectors(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
    public static void main(String[] args) {
        OneHotEncoder encoder = new OneHotEncoder();
        encoder.encodeFullListOfWords("src/main/java/Deepspell/trainingSets/ListOfTrainingWords.txt", 1);
        encoder.encodeFullListOfWords("src/main/java/Deepspell/trainingSets/ListOfTestingWords.txt", 2);
        encoder.encodeFullListOfWords("src/main/java/Deepspell/trainingSets/ListOfAllWords.txt", 3);
        encoder.encodeFullListOfWords("src/main/java/Deepspell/trainingSets/MiniTest.txt", 4);
        encoder.encodeFullListOfWords("src/main/java/Deepspell/trainingSets/MiniTest2.txt", 5);
    }
}
