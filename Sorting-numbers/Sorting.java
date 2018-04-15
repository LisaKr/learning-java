import java.io.*;
import java.util.*;


public class Sorting {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/user/Desktop/sortMe1.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String strLine; //to read the lines
        List<ArrayList<Integer>> finalNumbers = new ArrayList<>(); //list of lists for all the lines

        //convert the input into a list of lists of integers and sort every line in the ascending order
        while ((strLine = br.readLine()) != null) {
            ArrayList<Integer> tempNumbers = new ArrayList<>(); //list of integers for each line
            for (String element : strLine.split(" ")) {       //now this is an array of strings (numbers that were on the same line), e.g. [2, 1, 3]
                int newElement = Integer.parseInt(element); //convert this element to integer
                tempNumbers.add(newElement);    //add it to a list for this line
            }
            Collections.sort(tempNumbers); 
            finalNumbers.add(tempNumbers);
        }
        //sort list of strings by length AND by ascending order for every length
        Collections.sort(finalNumbers, Sorting::compareLists);

        writeOutput(finalNumbers);
    }

    public static int compareLists(ArrayList<Integer> a1, ArrayList<Integer> a2) {
        if (a1.size() < a2.size()) {
            return -1;
        } else if (a1.size() > a2.size()) {
            return 1;
        } else {
            for (int i = 0; i < a1.size(); i++) {
                if (a1.get(i) < a2.get(i)) {
                    return -1;
                } else if (a1.get(i) > a2.get(i)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    public static void writeOutput(List<ArrayList<Integer>> result) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/user/Desktop/sortedNumbers.txt"));
        for (ArrayList<Integer> line : result) {
            for (Integer element : line) {
                bw.write(String.valueOf(element) + " ");
            }
            bw.newLine();
        }
        bw.close();


    }
}
	
