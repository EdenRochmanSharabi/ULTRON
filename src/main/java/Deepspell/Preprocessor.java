package Deepspell;

public class Preprocessor {
    public String process(String word){
        word = word.toLowerCase();

        // Remove punctuation
        word = word.replaceAll("[^a-z,:]", "");

        // Remove digits
        word = word.replaceAll("[0-9]", "");

        return word;
    }
}
