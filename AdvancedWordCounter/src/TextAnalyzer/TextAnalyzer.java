package TextAnalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer implements ITextAnalyzerfile {

    private Map<String, IWordDatafile> words;

   
    public TextAnalyzer(String mapType) {
        if (mapType.equalsIgnoreCase("hashmap")) {
            words = new HashMap();

        } else {
        }
    }

    @Override
    public Collection<IWordDatafile> allWordsOrdedByFrequencyCount() {
        List<IWordDatafile> sortedWords = new LinkedList<>(words.values());
        Collections.sort(sortedWords, new Comparator<IWordDatafile>() {

            @Override
            public int compare(IWordDatafile o1, IWordDatafile o2) {
                long frequency1 = o1.getFrequencyCount();
                long frequency2 = o2.getFrequencyCount();
                return (int) (frequency2 - frequency1);
            }
        });
        return sortedWords;
    }

    @Override
    public Collection<IWordDatafile> allWordsOrderByText() {
        List<IWordDatafile> sortedText = new LinkedList<>(words.values());

        Collections.sort(sortedText, new Comparator<IWordDatafile>() {
            public int compare(IWordDatafile o1, IWordDatafile o2) {
                return o1.getText().compareTo(o2.getText());
            }
        });
        return sortedText;
    }

    private String removeSpecialCharacters(String s) {
        return s.replaceAll("[\\!\\@\\[\\]\\.\\,\\:\\;\"\'\\-\\?\\#\\$\\(\\)\\*]", "");
    }

    @Override
    public void analyzeText(String filename) throws FileNotFoundException, IOException {
        Scanner inp = new Scanner(new FileReader(filename));
        while (inp.hasNext()) {
            String chunk = removeSpecialCharacters(inp.next());
            if (!words.containsKey(chunk)) {
                words.put(chunk, new wordcount(1, chunk));
            } else {
                wordcount key = (wordcount) words.get(chunk);
                key.updateFrequencyCount();
            }
        }
    }

    @Override
    public IWordDatafile findWord(String word) {
        if (words.containsKey(word)) {
            return words.get(word);
        } else {
            return null;
        }
    }

    @Override
    public long getUniqueWordCount() {
        return words.keySet().size();
    }

    @Override
    public long getWordCount() {
        long totalWordCount = 0;
        for (Map.Entry<String, IWordDatafile> entry : words.entrySet()) {
            totalWordCount += entry.getValue().getFrequencyCount();
        }
        return totalWordCount;
    }
}
