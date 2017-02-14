import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ovasylenko on 22-Jan-17.
 */
public class DictionaryTester {

    public static void main(String[] args) throws IOException {

        Word[] dict = new Word[Dictionary.STANDART_D_SIZE];
        Dictionary d = new Dictionary(dict);
        HashMap<Integer, String> docs = new HashMap<>();

        File newTextFile = new File("D:/dictionary.txt");
        FileWriter fw = new FileWriter(newTextFile);
        Scanner fileScan = new Scanner(new File("src/s/1.txt"));
        File path = new File("src/s/");
        File [] files = path.listFiles();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i <files.length ; i++) {
            docs.put(i,files[i].getName());
        }

        System.out.println("List of docs and it's id: "+"\n"+docs);

        //read all files in folder s
        for (int z = 0; z < files.length; z++){
            d.doccounter = files.length;
            if (files[z].isFile())//this line weeds out other directories/folders
                fileScan = new Scanner(new File(String.valueOf(files[z])));
            d.sizeOfCollection += files[z].length();
            System.out.println(String.valueOf(files[z]));

            System.out.println("thinking..");

            String word;
            Word w ;
            while(fileScan.hasNext()){
                word = fileScan.next();
                word = word.replaceAll("[\\[\\]\\-`,.!?\"%$*'#()&_:;]", "");
                word = word.toLowerCase();
                d.words ++;


                if(d.uniqueWords == 0) {
                    w = new Word(word);
                    dict[0] = w;
                    w.docsId.add(z);
                    d.uniqueWords++;
                }

                w = new Word(word);
                w.docsId.add(z);
                d.addToDict(dict,w,z);
                if(d.uniqueWords == dict.length-1){
                    dict = d.resizeArray(dict);
                }
            }
        }

        d.sortThis(dict);
        d.buidIncedenceMatrix(docs,dict);


//print incedence matrix
        System.out.println("---------------------INCEDENCE MATRIX---------------------");
        for (int i = 0; i <Dictionary.uniqueWords ; i++) {
            System.out.println("");
            System.out.print(dict[i].word+", ");
            for (int j = 0; j <docs.size() ; j++) {
                System.out.print(d.getMatrixValue(i,j)+" ");
            }
        }

        //print in txt file
        for (int i = 0; i < d.uniqueWords; i++) {
            System.out.println(dict[i]);
            fw.write(dict[i].word);
            fw.append(System.lineSeparator());
        }

        double docSize = newTextFile.length()/1000;

        fw.append("WORDS:" +d.words);
        fw.append(System.lineSeparator());
        fw.append("UNIQUE WORDS: "+ d.uniqueWords);
        fw.append(System.lineSeparator());
        fw.append("SIZE OF COLLECTION(NUM OF DOCS): "+d.doccounter);
        fw.append(System.lineSeparator());
        fw.append("SIZE OF COLLECTION(MB): "+ d.sizeOfCollection /1000000);
        fw.append(System.lineSeparator());
        fw.append("SIZE OF DICTIONARY(KB): "+ docSize);

        fw.close();

        System.out.println("WORDS: "+ d.words);
        System.out.println("UNIQUE WORDS: "+ d.uniqueWords);
        System.out.println("SIZE OF COLLECTION(NUM OF DOCS): "+d.doccounter);
        System.out.println("SIZE OF COLLECTION(MB): "+ d.sizeOfCollection /1000000);
        System.out.println("SIZE OF DICTIONARY(KB): "+ docSize);
        System.out.println("FIND DICTIONARY FILE IN  D:/ ");


        System.out.println("---------------------BOOLEAN SEARCH---------------------");

        System.out.println("How many words do you want to check in text?, example (a + zoo - zoe) = 3 words : ");
        int size = scanner.nextInt();
        if(size == 1) size = 2;  // for ocasions like NOT lena
        else size += size - 1;

        System.out.println("You can use following operators: || = or, + = and, - = not");
        System.out.println("Enter you querry(with spaces between word and operator!), example (writer || x + wrongs) : ");
        List<String> boolL = new ArrayList<>();
        String[] boolS = new String[size];
        for (int i = 0; i <boolS.length ; i++) {
            boolS[i] = scanner.next();
        }


        int[] res = d.boolSearch(boolS, dict);
        System.out.println("Solution of boolean search: ");
        System.out.println(d.getDocNameByMatrix(files, res));
        for (int i = 0; i <res.length ; i++) {
            System.out.print(res[i]+" ");
        }
        System.out.println();
    }
}
