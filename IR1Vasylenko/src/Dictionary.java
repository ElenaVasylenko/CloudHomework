import sort.QuickSort;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ovasylenko on 22-Jan-17.
 */
public class Dictionary {

    static int uniqueWords = 0;
    static int words = 0;
    static int doccounter = 0;
    static double sizeOfCollection = 0;

    public Word[] dictionary;
    public int[][] incedenceMatrix;
    static final int STANDART_D_SIZE = 9000;

    Dictionary(Word[] dictionary){
        this.dictionary = dictionary;
    }

    Dictionary(){}

    //compare two words
    public boolean compareW(Word a, Word b){

        String a1 = a.word;
        String b1 = b.word;

        boolean isSame = true;


        if(a1.length() != b1.length()) return false;
        for (int i = 0; i <a1.length() ; i++) {
            if(!a1.equals(b1)){
                isSame = false;
                return false;

            }
        }
        return true;
    }



    //resizing of array
    public Word[] resizeArray(Word[] a){
        int resizeCoef = STANDART_D_SIZE/2;
        Word[] copy ;
        copy = Arrays.copyOf(a,resizeCoef+a.length);
        a = copy;
        return a;
    }

    //adding to dictionary
    public void addToDict(Word[] dict, Word a, int docId){

        boolean isAlready = false;

        for (int i = 0; i < uniqueWords; i++) {
            if(compareW(a, dict[i])) {
                isAlready = true;
                dict[i].docID.add(docId);
                break;
            }
        }

        //add to dictionary if not exist
        if(isAlready == false) {

            dict[uniqueWords] = a;
            a.docID.add(docId);
            uniqueWords++;

        }
    }

    //sotring
    public void sortThis(Word[] d){
        QuickSort.sortD(d,0,uniqueWords-1);
    }

    //
    public int[][] buidIncedenceMatrix(HashMap<Integer, String> docs, Word[] dict){
        incedenceMatrix = new int[uniqueWords][docs.size()];

        for (int i = 1; i <uniqueWords ; i++) {
            for (int j = 0; j < docs.size(); j++) {
               if(dict[i].docID.contains(j)) incedenceMatrix[i][j] = 1;  //!!!!!!!!!!!!
               else incedenceMatrix[i][j] = 0;
            }
        }

        return incedenceMatrix;
    }

    public int getMatrixValue(int i, int j){
        return incedenceMatrix[i][j];
    }

    public int[] getMatrixRowByWord(String w, Word[] dict){
        int id = 0;
        int[] row ;
        for (int i = 0; i <dict.length ; i++) {
            if(w.equals(dict[i].getWord())){
                id = i;
                break;
            }
        }

        row = new int[doccounter];
        for (int i = 0; i < doccounter; i++) {
            row[i] = getMatrixValue(id,i);
        }
        return row;
    }

    public int[] boolSearch(String[] a, Word[] dict){
        int[] res = new int[doccounter];

        if(a[0].equals("-")) res = NOT(getMatrixRowByWord(a[1], dict)); // for ocasions like "- lena"

        for (int i = 1; i <a.length ; i++) {
            if(i == 1) {

                if (a[i].equals("-")) res = NOT(getMatrixRowByWord(a[i + 1], dict));
                if (a[i].equals("+")) res = AND(getMatrixRowByWord(a[i - 1], dict), getMatrixRowByWord(a[i + 1], dict));
                if (a[i].equals("||")) res = OR(getMatrixRowByWord(a[i - 1], dict), getMatrixRowByWord(a[i + 1], dict));
            }else {

                if (a[i].equals("-")) res = AND(NOT(getMatrixRowByWord(a[i + 1], dict)),res);
                if (a[i].equals("+")) res = AND(res, getMatrixRowByWord(a[i + 1], dict));
                if (a[i].equals("||")) res = OR(res, getMatrixRowByWord(a[i + 1], dict));
            }
        }
        return res;
    }

    //get docs names by matrix
    public List<String> getDocNameByMatrix(File[] files, int res[]){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < res.length; i++) {
            if(res[i] == 1) list.add(files[i].getName());
        }
        return list;
    }

    public int[] AND(int[] a ,int b []){
        int res[]= new int[doccounter];
        for (int i = 0; i < a.length; i++) {
            if(a[i] == 1 && b[i] ==1) res[i] = 1;
            else res[i] =0;
        }
        return res;
    }

    public int[] OR(int[] a, int[] b){
        int res[]= new int[doccounter];
        for (int i = 0; i < a.length; i++) {
            if(a[i] == 1 || b[i] == 1) res[i] = 1;
            else res[i] = 0;
        }
        return res;
    }

    public int[] NOT(int[] a){
        int res[]= new int[doccounter];
        for (int i = 0; i < a.length; i++) {
            if(a[i] == 1 ) res[i] = 0;
            else res[i] = 1;
        }
        return res;
    }

}


