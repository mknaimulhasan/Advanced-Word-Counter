package TextAnalyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class hashmap {

    public static void main(String[] args) {
        TextAnalyzer treeAnalyzer = new TextAnalyzer("hashmap");
        try {
            treeAnalyzer.analyzeText("txt/sample.txt");
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        Collection<IWordDatafile> wordsOrderedByFrequency = treeAnalyzer.allWordsOrdedByFrequencyCount();
        wordsOrderedByFrequency.forEach((word) -> {
            System.out.println(word.getFrequencyCount());
        });
       
        Collection<IWordDatafile> wordsOrderedByText = treeAnalyzer.allWordsOrderByText();
        wordsOrderedByText.forEach((word) -> {
            System.out.println(word.getText());
        });
       
        try {
            System.out.println(treeAnalyzer.findWord("test").getFrequencyCount()); 
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(treeAnalyzer.findWord("the").getFrequencyCount()); 
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(treeAnalyzer.getUniqueWordCount());
        System.out.println(treeAnalyzer.getWordCount());

        
    }
}
