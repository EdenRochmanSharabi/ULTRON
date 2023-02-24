package Deepspell.Tokenization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

        System.out.println("Correct: " + run.getCorrectWords().size());
        System.out.println("Incorrect: " + run.getIncorrectWordsList().size());
        System.out.println("Correct word list: "+run.correctWordCount);
        System.out.println("Incorrect word list: "+run.inCorrectWordCount);

    }
    private List<String> words;
    private List<String> correctWords;
    private List<String> incorrectWordsList;
    private int correctWordCount;
    private int inCorrectWordCount;

    public TrainingData(String... fileNames) {
        Preprocessor preprocessor = new Preprocessor();
        words = new ArrayList<>();

        correctWords = new ArrayList<>();
        incorrectWordsList = new ArrayList<>();


        for (String fileName : fileNames) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                correctWordCount = 0;
                inCorrectWordCount = 0;
                while ((line = br.readLine()) != null) {
                    String[] wordsInLine = line.split("\\s+");

                    String correctWord = "";
                    List<String> incorrectWords = new ArrayList<>();

                    for (String word : wordsInLine) {
//                        String processedWord = preprocessor.process(word);
                        if (!word.isEmpty()) {
                            if (correctWord.isEmpty()) {
                                correctWord = word;
                                correctWords.add(word);
                                correctWordCount++;
                            } else {
                                incorrectWords.add(word);
                                incorrectWordsList.add(word);
                                inCorrectWordCount++;
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

    public List<String> getCorrectWords() {
        return correctWords;
    }

    public List<String> getIncorrectWordsList() {
        return incorrectWordsList;
    }
}