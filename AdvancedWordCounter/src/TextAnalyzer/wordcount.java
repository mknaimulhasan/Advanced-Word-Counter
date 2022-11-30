package TextAnalyzer;

public class wordcount implements IWordDatafile {

    private final String analyzed_Word;
    private long count;

    public wordcount(long count, String text) {
        this.count = count;
        this.analyzed_Word = text;
    }

    public void updateFrequencyCount() {
        this.count++;
    }

    @Override
    public long getFrequencyCount() {
        return count;
    }

    @Override
    public String getText() {
        return analyzed_Word;
    }

}
