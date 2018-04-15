import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalorieCount {

    static final String DONE = "done";
    static final String PROTEIN_BAR = "proteinBar";
    static final String CARROT = "carrot";


    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String food = "";
        List<Munchable> munch = new ArrayList<>();

        System.out.println("I'm very interested in your food consumption!");
        System.out.println();

        while (true) {
            System.out.println("Please tell me the last thing you ate.");
            System.out.println("Due to our limited menu I assume it would either be " + CARROT + " or " + PROTEIN_BAR + ".");
            System.out.println("Please type your answer without spaces, like that: 'proteinbar'.");
            System.out.println("If you've already told me everything just type '" + DONE + "'.");
            System.out.println();

            food = userInput.next();
            if (DONE.equalsIgnoreCase(food)) {
                break;
            } else if (PROTEIN_BAR.equalsIgnoreCase(food)) {
                System.out.println("It has 42 calories. Coincidence? I don't think so!\n\n");
                munch.add(new ProteinBar());
            } else if (CARROT.equalsIgnoreCase(food)) {
                System.out.println("Do you remember how long it was?");
                Double carrotLength = Double.parseDouble(userInput.next());
                System.out.println("What about its diameter?");
                Double carrotDiameter = Double.parseDouble(userInput.next());
                munch.add(new Carrot(carrotLength, carrotDiameter));
            } else {
                System.out.println("Error\n \n");
            }
        }

        double totalCalories = 0.0;

        for(Munchable munchable : munch) {
            totalCalories += munchable.count();
        }

        System.out.println("You consumed " + Math.round(totalCalories) + " calories. Now work out.");
    }
}
