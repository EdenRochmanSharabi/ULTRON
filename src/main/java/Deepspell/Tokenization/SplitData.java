package Deepspell.Tokenization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitData {

    public static void main(String[] args) {
        SplitData splitData = new SplitData();
        System.out.println("Correct words: " + splitData.getCorrectWords());
        System.out.println("Incorrect words: " + splitData.getIncorrectWords());
    }

    public boolean contains(String word){
        Preprocessor pre = new Preprocessor();
        for (String correct : correctWords){
            if (correct.equals(word)) return true;
        }
        for (String incorrect : incorrectWords){
            String preInc = pre.process(incorrect);
            if (preInc.equals(word)) return true;
        }
        return false;
    }
    private List<String> correctWords;
    private List<String> incorrectWords;
    public SplitData(){
        TrainingData trainingData = new TrainingData(
                "src/main/java/Deepspell/Files/aspell.txt",
                "src/main/java/Deepspell/Files/birkbeck.txt",
                "src/main/java/Deepspell/Files/spell-testset1.txt",
                "src/main/java/Deepspell/Files/spell-testset2.txt",
                "src/main/java/Deepspell/Files/wikipedia.txt"
        );
        List<String> words = trainingData.getWords();
        correctWords = new ArrayList<>();
        incorrectWords = new ArrayList<>();

        for (String word : words) {
            int index = word.indexOf(":");
            if (index >= 0) {
                String correctWord = word.substring(1, index).trim();
                correctWords.add(correctWord);
                String[] incorrectWordsArr = word.substring(index + 1, word.length() - 2).split(",");
                List<String> incorrectWordsList = new ArrayList<>();
                for (String incorrectWord : incorrectWordsArr) {

                    incorrectWordsList.add(incorrectWord.trim());
                }

                incorrectWords.addAll(incorrectWordsList);

            }
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
    public List<String> getIncorrectWords(String aaa) {
        List<String> incorrectWordsList = new ArrayList<>();
        for (String word : incorrectWords) {
            String[] incorrect = getIncorrect(word);
            incorrectWordsList.addAll(Arrays.asList(incorrect));
        }
        return incorrectWordsList;
    }

    public List<String> getIncorrectWords(){
        return incorrectWords;
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