package Deepspell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingData {
    public static void main(String[] args) {
        TrainingData run = new TrainingData(
                "src/main/java/Deepspell/Files/aspell.txt",
                "src/main/java/Deepspell/Files/birkbeck.txt",
                "src/main/java/Deepspell/Files/spell-testset1.txt",
                "src/main/java/Deepspell/Files/spell-testset2.txt",
                "src/main/java/Deepspell/Files/wikipedia.txt"
        );

        System.out.println(run.getWords());
        System.out.println(run.contains("|Nevada:: nevade |"));
    }
    private List<String> words;

    public TrainingData(String... fileNames) {
        Preprocessor preprocessor = new Preprocessor();
        words = new ArrayList<>();

        for (String fileName : fileNames) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] wordsInLine = line.split("\\s+");

                    String correctWord = "";
                    List<String> incorrectWords = new ArrayList<>();

                    for (String word : wordsInLine) {
//                        String processedWord = preprocessor.process(word);
                        if (!word.isEmpty()) {
                            if (correctWord.isEmpty()) {
                                correctWord = word;
                            } else {
                                incorrectWords.add(word);
                            }
                        }
                    }

                    if (!correctWord.isEmpty()) {
                        String wordString = "|" + correctWord + ":";
                        for (String incorrectWord : incorrectWords) {
                            wordString += " " + incorrectWord + ",";
                        }
                        wordString = wordString.substring(0, wordString.length() - 1) + " |";
                        words.add(wordString);
                    }

                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean contains(String word) {
        return this.words.contains(word);
    }

    public List<String> getWords(){
        return words;
    }
}