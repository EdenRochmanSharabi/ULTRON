package Deepspell.Tokenization;

import java.util.*;

import static Deepspell.Tokenization.saveFiles.saveToFile;

public class DataGenerator {
    public static void main(String[] args) {
        DataGenerator generator = new DataGenerator();

        Multimap<String, String> fullListOfWords = generator.getFullListOfWords();
        Multimap<String, String> trainingData = generator.getTrainingData();
        Multimap<String, String> testingData = generator.getTestingData();

        saveToFile("ListOfAllWords.txt", fullListOfWords);

        saveToFile("ListOfTrainingWords.txt", trainingData);

        saveToFile("ListOfTestingWords.txt", testingData);
    }
    private Multimap<String, String> trainingData;
    private Multimap<String, String> testingData;
    public Multimap<String, String> fullListOfWords;
    public DataGenerator(){

        SplitData sd = new SplitData();

        List<String> words = sd.getCorrectWords();
        this.trainingData = new Multimap<>();
        this.testingData = new Multimap<>();

        fullListOfWords = new Multimap<>();
        fullListOfWords = generateAllVariationWords(fullListOfWords, words);


        for (String word : words) {
            Collection<String> variants = fullListOfWords.get(word);
            int size = variants.size();
            int count = 0;
            for (String variant : variants) {
                if (count++ < size * 0.8) {
                    trainingData.put(word, variant);
                } else {
                    testingData.put(word, variant);
                }
            }
        }
    }
    public Multimap<String, String> generateAllVariationWords(Multimap<String, String> fullListOfWords, List<String> words) {
        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                StringBuilder sb = new StringBuilder(word);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != sb.charAt(j)) {
                        sb.setCharAt(j, c);
//                        fullListOfWords.put(word, sb.toString());
                        String variant = sb.toString();
                        if (!variant.equals(word)) {
                            fullListOfWords.put(word, variant);
                        }
                    }
                }
            }
        }
        return fullListOfWords;
    }
    public Multimap<String, String> getFullListOfWords(){
        return fullListOfWords;
    }
    public Multimap<String, String> getTrainingData() {
        return trainingData;
    }
    public Multimap<String, String> getTestingData() {
        return testingData;
    }
}
