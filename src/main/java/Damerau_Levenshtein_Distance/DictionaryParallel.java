package Damerau_Levenshtein_Distance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryParallel {
    public static void main(String[] args) {
        DictionaryParallel run = new DictionaryParallel();
        System.out.println(run.getWords());
    }
    private List<String> words;

    public DictionaryParallel() {
//        String filePath = "src/main/java/Damerau_Levenshtein_Distance/list_dict/words_alpha.txt";
//        String filePath = "src/main/java/Damerau_Levenshtein_Distance/list_dict/corncob_lowercase.txt";
//        String filePath = "src/main/java/Damerau_Levenshtein_Distance/list_dict/most_common_words.txt";
        String filePath = "src/main/java/All_List_Words/list_dict/words_alpha.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            this.words = br.lines().parallel().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean contains(String word) {
        return this.words.contains(word);
    }
    public List<String> getWords(){
        return words;
    }
}
