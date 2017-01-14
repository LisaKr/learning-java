import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class WordCounter {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            File file = new File("/Users/user/Desktop/testt.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //System.out.println(file.canRead());

            String strLine;
            List<String> listOfWords = new ArrayList<String>();


            while ((strLine = br.readLine()) != null) {
                for (String word : strLine.split(" ")) {
                    if (word.length() >= 1 && word.matches(".*[a-z].*") == true) {
                        listOfWords.add(word);
                    }
                }
            }
            //System.out.println(listOfWords);
            System.out.println("There are " + listOfWords.size() + " words in this textfile.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}