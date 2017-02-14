import java.util.*;

/**
 * Created by ovasylenko on 22-Jan-17.
 */
public class Word implements Comparable  {

    String word;
    public Set<Integer> docID = new LinkedHashSet<>();


    Word(String word)
    {
        this.word = word;
    }

    Word(){

    }

    public Set<Integer> getDocID() {
        return docID;
    }

    public void setDocID(Set<Integer> docID) {
        this.docID = docID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        //Collections.sort(docID);
        return  word + ", docs:"+docID.toString();
    }

    @Override
    public int compareTo(Object o) {
        Word entry = (Word)o;
        return word.compareTo(entry.getWord());
    }
}
