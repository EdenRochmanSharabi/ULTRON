package Deepspell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        List<String> trainingList = dataGenerator.getTrainingList();
        List<String> testingList = dataGenerator.getTestingList();

        System.out.println("Training List:");
        for (String example : trainingList) {
            System.out.println(example);
        }

        System.out.println("Testing List:");
        for (String example : testingList) {
            System.out.println(example);
        }
    }
    private List<String> trainingList;
    private List<String> testingList;
    public DataGenerator(){
        TrainingData trainingData = new TrainingData(
                "src/main/java/Deepspell/Files/aspell.txt",
                "src/main/java/Deepspell/Files/birkbeck.txt",
                "src/main/java/Deepspell/Files/spell-testset1.txt",
                "src/main/java/Deepspell/Files/spell-testset2.txt",
                "src/main/java/Deepspell/Files/wikipedia.txt"
        );

        SplitData splitData = new SplitData(trainingData.getWords());
        List<String> correctWords = splitData.getCorrectWords();
        List<String> incorrectWords = splitData.getIncorrectWords();

        int numTrain = (int) (correctWords.size() * 0.8);
        int numTest = correctWords.size() - numTrain;

        this.trainingList = new ArrayList<>(numTrain * (splitData.getMaxNumIncorrectWords()+1));
        this.testingList = new ArrayList<>(numTest * (splitData.getIncorrectWords().size()+1));

        Random random = new Random();

        for (int i = 0; i < correctWords.size(); i++) {
            String correctWord = correctWords.get(i);
            List<String> incorrectArray = splitData.getIncorrectWords();
//            List<String> correctWord = new ArrayList<>(correctWords);
//            List<String> incorrectArray = new ArrayList<>(incorrectWords);
            System.out.println(incorrectArray);
            System.out.println(correctWord);
//            trainingList.add(correctWord + "\t"+ correctWord);
//            testingList.add(correctWord + "\t"+ correctWord);
            for (int j = 0; j < incorrectArray.size(); j++) {
                trainingList.add(correctWord + "\t"+ incorrectArray.get(j));
                testingList.add(correctWord + "\t"+ incorrectArray.get(j));
            }

//            for (String incorrectWord : incorrectArray) {
//                String inputWord = (random.nextBoolean() ? correctWord : incorrectWord);
//                String outputWord = (inputWord.equals(correctWord) ? correctWord : incorrectWord);
//
//                if (i < numTrain) {
//                    trainingList.add(inputWord + "\t" + outputWord);
//                } else {
//                    testingList.add(inputWord + "\t" + outputWord);
//                }
//            }
        }
    }
    public List<String> getTrainingList() {
        return trainingList;
    }

    public List<String> getTestingList() {
        return testingList;
    }
}
