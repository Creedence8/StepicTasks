package ru.ya.creedence8.Training;

/**
 * Created by Cole S' Offe on 08.11.2016.
 */
public interface TextAnalyzer {
   Label processText(String text);
}

enum Label {
    SPAM, NEGATIVE_TEXT, TOO_LONG, OK
}

abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();

    @Override
    public Label processText(String text){
        for (String str : this.getKeywords()){
        if (text.contains(str)) return this.getLabel();
        }
        return Label.OK;
    }
}

class SpamAnalyzer extends KeywordAnalyzer {
    private String[] keywords;
    private Label label = Label.SPAM;

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public String[] getKeywords() {
        return keywords;
    }

    @Override
    public Label getLabel() {
        return label;
    }
}

class NegativeTextAnalyzer  extends KeywordAnalyzer{
    private String[] keywords = new String[] {":(", "=(",  ":|"};
    private Label label = Label.NEGATIVE_TEXT;

    @Override
    public String[] getKeywords(){
        return keywords;
    }

    @Override
    public Label getLabel(){
        return label;
    }
}

class TooLongTextAnalyzer implements TextAnalyzer {
   private int maxLength;
   private Label label = Label.TOO_LONG;

   public TooLongTextAnalyzer(int maxLength) {
       this.maxLength = maxLength;
   }

   @Override
   public Label processText(String text) {
       if (text.length() > maxLength)
           return label;
       return Label.OK;
   }


}
