import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PermutationPromenade {
    public static String dancers;
    public static void dance(String[] danceMoves) {

        for(int i = 0; i < danceMoves.length; i++){
            // check which move is to be made
            String move = danceMoves[i];
            char firstChar = move.charAt(0);
            switch (firstChar) {
                case 's':
                    // spin
                    spin(Integer.parseInt(move.substring(1)));
                    break;
                case 'x':
                    // exchange
                    String[] positions = move.substring(1).split("/");
                    exchange(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
                    break;
                case 'p':
                    // partner
                    partner(move.charAt(1), move.charAt(3));
                    break;
                default:
                    break;
            }
        }

    }
    public static void partner(char a, char b) {
        // swap dancer a with dancer b
        int a_pos = 0;
        int b_pos = 0;
        for (int i = 0; i < dancers.length(); i++) {
            if (dancers.charAt(i) == a) {
                a_pos = i;
            }
            if (dancers.charAt(i) == b) {
                b_pos = i;
            }
        }
        exchange(a_pos, b_pos);
    }

    public static void exchange(int x, int y) {
        // swap the dancer at x with the dancer at y
        // ensure x is the lower position
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        char dancersArray[] = new char[dancers.length()];
        for(int i = 0; i < dancers.length(); i++) {
            dancersArray[i] = dancers.charAt(i);
        }
        char dancer = dancersArray[y];
        dancersArray[y] = dancersArray[x];
        dancersArray[x] = dancer;
        // update dancers string
        dancers = "";
        for(int i = 0; i < dancersArray.length; i++) {
            dancers += dancersArray[i];
        }
    }

    public static void spin(int x) {
        // right circular shift x times
        for(int i = 0; i < x; i++) {
            dancers = dancers.charAt(dancers.length() - 1) + dancers.substring(0, dancers.length() - 1);
        }
    }
    public static void main(String args[]) {
        dancers = "";
        for(char i = 'a'; i <= 'p'; i++) {
            dancers += i;
        }

        Scanner sc = new Scanner(System.in);
        List<String> danceMoves = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] values = line.split(",");
            // this adds the currently parsed line to the 2-dimensional string array
            for(String s : values) {
                danceMoves.add(s);
            }
        }

        dance(danceMoves.toArray(new String[0]));
        System.out.println(dancers);

    }
}
