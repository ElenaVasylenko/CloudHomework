import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by ovasylenko on 22-Jan-17.
 */
public class CollectDictionary1 {


    static int uniqueWords = 0;
    static int words = 0;
    static int doccounter = 0;
    static double sizeOfCollection = 0;

    Set<String> dictionary;


    CollectDictionary1(Set<String> w){
        dictionary = w;
    }


    public static void main(String[] args) throws IOException {

        File newTextFile = new File("D:/dictionaryC.txt");
        FileWriter fw = new FileWriter(newTextFile);
        Scanner fileScan = new Scanner(new File("1.txt"));
        File path = new File("src/s/");
        //fngkngkjkngI am irochka keklol
        
        File[] files = pajfhfusksl();

        Set<String> dict = new LinkedHashSet<>();
        CollectDictionary1 c = new CollectDictionary1(dict);


        //read all files in folder s
        for (int z = 0; z < files.length; z++) {

            if (files[z].isFile())//this line weeds out other directories/folders
                fileScan = new Scanner(new File(String.valueOf(files[z])));
            System.out.println(String.valueOf(files[z]));
            doccounter++;
            sizeOfCollection+= files[z].length();

            System.out.println("thinking..");

            String word;
            Word w;
            while (fileScan.hasNext()) {
                word = fileScan.next();
                word = word.replaceAll("[\\[\\]\\-`,.!?\"%'$*#()&_:;]", "");
                word = word.toLowerCase();
                words++;

                if(!dict.contains(word)){
                    fw.append(word);
                    fw.append(System.lineSeparator());
                }
                dict.add(word);
            }
        }

                //print in txt file
                double docSize = newTextFile.length();

                fw.append("WORDS Number:" + words);
                fw.append(System.lineSeparator());
                fw.append("UNIQUE WORDS: " + dict.size());
                fw.append(System.lineSeparator());
                fw.append("SIZE OF COLLECTION(NUM OF DOCS): " + doccounter);
                fw.append(System.lineSeparator());
                fw.append("SIZE OF COLLECTION(MB): " + sizeOfCollection / 1000000);
                fw.append(System.lineSeparator());
                fw.append("SIZE OF DICTIONARY(bytes): " + docSize);

                fw.close();

                System.out.println("WORDS: " + words);
                System.out.println("UNIQUE WORDS: " + dict.size());
                System.out.println("SIZE OF COLLECTION(NUM OF DOCS): " + doccounter);
                System.out.println("SIZE OF COLLECTION(MB): " + sizeOfCollection / 1000000);
                System.out.println("SIZE OF DICTIONARY(bytes): " + docSize);
                System.out.println("FIND DICTIONARY FILE IN  D:/ ");


    }
}
