package Deepspell.Tokenization;

public class Preprocessor {
    public String process(String word){
        // Remove leading and trailing spaces
        word = word.trim();

        // Remove the first two characters
//        word = word.substring(2);

        // Remove punctuation
        word = word.replaceAll(":", "");
        word = word.replaceAll(" ", "");

        // Remove digits
        word = word.replaceAll("[0-9]", "");

        return word;
    }
}
