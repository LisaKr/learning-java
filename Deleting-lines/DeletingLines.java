import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeletingLines {

    final String fileName; //file name
    final Integer startingLine; //starting line
    final Integer numberOfLines; //number of lines
    String strLine; //to iterate through lines
    List<String> lines = new ArrayList<String>();


    public DeletingLines(String[] args) {

        this.fileName = "/Users/user/Desktop/" + args[0] + ".txt";
        this.startingLine = Integer.valueOf(args[1]);
        this.numberOfLines = Integer.valueOf(args[2]);
    }

    public static void main(String[] args) throws IOException {
        DeletingLines fileToEdit = new DeletingLines(args); //will provide the constructor with the arguments from the command line
        fileToEdit.deleteLines();

    }

    public void deleteLines() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((strLine = br.readLine()) != null) {
            lines.add(strLine);
        }
       // for (int i = startingLine; i < numberOfLines; i++) {
            lines.subList(startingLine, numberOfLines).clear();
            System.out.println(lines);


    }

    }
