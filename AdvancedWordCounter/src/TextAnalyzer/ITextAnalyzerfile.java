package TextAnalyzer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface ITextAnalyzerfile {

 
    void analyzeText(String filename) throws FileNotFoundException, IOException;

    long getWordCount();

   
    long getUniqueWordCount();

   
    IWordDatafile findWord(String word);

    
    Collection<IWordDatafile> allWordsOrdedByFrequencyCount();

   
    Collection<IWordDatafile> allWordsOrderByText();

}
