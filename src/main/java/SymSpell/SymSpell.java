package SymSpell;

import Damerau_Levenshtein_Distance.DamerauLevenshtein;

import java.io.IOException;
import java.util.*;

public class SymSpell {
    public static void main(String[] args) throws IOException {
        SymSpell dl = new SymSpell();
        String word = "imagine";
        if (dl.getSimilarWordsDistance(word, 3, 5)!=null){
            List<String> similarWords = dl.getSimilarWordsDistance(word, 3, 5);
            System.out.println("Words within 3 Damerau-Levenshtein distance of " + word + ": " + similarWords);
        } else {
            System.out.println("The word is correct");
        }

    }
    DictionaryFrecuency dict;
    public SymSpell() throws IOException { this.dict = new DictionaryFrecuency();}

    public List<String> getSimilarWordsDistance(String word, int maximumDistance, int n){
        HashMap<String, Long> closeWords = new HashMap<>();
        HashMap<String, Long> dictionary = dict.getWords();
        DamerauLevenshtein dl = new DamerauLevenshtein();

        for (String candidate : dictionary.keySet()){
            if (Objects.equals(candidate, word)){
                return null;
            }
            int distance = dl.getDistance(word, candidate);
            if (distance <= maximumDistance && isSimilar(word, candidate)){
                closeWords.put(candidate, dictionary.get(candidate));
            }
        }

        List<String> closestFrequency = new ArrayList<>();
        closeWords.entrySet().parallelStream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(n).forEach(entry -> closestFrequency.add(entry.getKey()));

        return closestFrequency;
    }
    private boolean isSimilar(String word1, String word2) {
        if (word1.equals(word2)) {
            return false;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }

        int diffCount = 0;
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (word1.charAt(i) != word2.charAt(j)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
                if (len1 < len2) {
                    j++;
                } else if (len1 > len2) {
                    i++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }

        return diffCount == 0 && Math.abs(len1 - len2) == 1 || diffCount == 1;
    }
}
