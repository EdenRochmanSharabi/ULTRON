package Deepspell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitData {
//    public static void main(String[] args) {
//        TrainingData trainingData = new TrainingData();
//        SplitData splitData = new SplitData(trainingData);
//        System.out.println("Number of correct words: " + splitData.getCorrectWords().size());
//        System.out.println("Number of incorrect words: " + splitData.getIncorrectWords().size());
//        //triangular
//        System.out.println(splitData.contains("localy"));
//    }
    public static void main(String[] args) {
        TrainingData trainingData = new TrainingData(
                "src/main/java/Deepspell/Files/aspell.txt",
                "src/main/java/Deepspell/Files/birkbeck.txt",
                "src/main/java/Deepspell/Files/spell-testset1.txt",
                "src/main/java/Deepspell/Files/spell-testset2.txt",
                "src/main/java/Deepspell/Files/wikipedia.txt"
        );

        SplitData splitData = new SplitData(trainingData.getWords());
        System.out.println("Correct words: " + splitData.getCorrectWords());
        System.out.println("Incorrect words: " + splitData.getIncorrectWords());

    }

    public boolean contains(String word){
        for (String incorrect : incorrectWords){
            return incorrect.equals(word);
        }
        return false;
    }
    private List<String> correctWords;
    private List<String> incorrectWords;
    public SplitData(TrainingData trainingData) {
        Preprocessor preprocessor = new Preprocessor();
        correctWords = new ArrayList<>();
        incorrectWords = new ArrayList<>();
        for (String word : trainingData.getWords()) {
//            String processedWord = preprocessor.process(word);
            if (trainingData.contains(word)) {
                correctWords.add(word);
            } else {
                incorrectWords.add(word);
            }
        }
    }
    public SplitData(List<String> words){
        correctWords = new ArrayList<>();
        incorrectWords = new ArrayList<>();

        for (String word : words) {
            int index = word.indexOf(":");
//            int i = 0;
            if (index >= 0) {
                String correctWord = word.substring(1, index).trim();
                String[] incorrectWordsArr = word.substring(index + 1, word.length() - 2).split(",");
                List<String> incorrectWordsList = new ArrayList<>();
                for (String incorrectWord : incorrectWordsArr) {
                    incorrectWordsList.add(incorrectWord.trim());
                }
                correctWords.add(correctWord);
                incorrectWords.addAll(incorrectWordsList);
            }
        }
        for (String word : incorrectWords){
            if (word.equals("nevade")) System.out.println("nevade was found");
        }
    }
    public List<String> getCorrectWords(){
        return correctWords;
    }

    public String[] getIncorrectWords(int index){
        String word = incorrectWords.get(index);
        int dots = word.indexOf(":");
        if (dots >= 0){
            String[] incorrect = word.substring(dots+1, word.length()-2).split(",");
            for (int i = 0; i < incorrect.length; i++) {
                incorrect[i] = incorrect[i].trim();
            }
            return incorrect;
        }
        return new String[0];
    }
    public List<String> getIncorrectWords() {
        List<String> incorrectWordsList = new ArrayList<>();
        for (String word : incorrectWords) {
            String[] incorrect = getIncorrect(word);
            incorrectWordsList.addAll(Arrays.asList(incorrect));
        }
        return incorrectWordsList;
    }

    private String[] getIncorrect(String word) {
        int dots = word.indexOf(":");
        if (dots >= 0) {
            String[] incorrect = word.substring(dots + 1, word.length() - 2).split(",");
            for (int i = 0; i < incorrect.length; i++) {
                incorrect[i] = incorrect[i].trim();
            }
            return incorrect;
        }
        return new String[0];
    }

    public int getMaxNumIncorrectWords(){
        int maxNum = 0;
        for (String word : incorrectWords){
            String[] incorrectsArray = word.split(",");
            if (incorrectsArray.length > maxNum){
                maxNum = incorrectsArray.length;
            }
         }
        return maxNum;
    }
}