import java.util.*;

/**
 * Created by ovasylenko on 22-Jan-17.
 */
public class Word implements Comparable  {

    String word;
    public Set<Integer> docsId = new LinkedHashSet<>();


    Word(String word)
    {
        this.word = word;
    }

    Word(){

    }

    public Set<Integer> getDocsId() {
        return docsId;
    }

    public void setDocsId(Set<Integer> docsId) {
        this.docsId = docsId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        //Collections.sort(docsId);
        return  word + ", docs:"+ docsId.toString();
    }

    @Override
    public int compareTo(Object o) {
        Word entry = (Word)o;
        return word.compareTo(entry.getWord());
    }
}
