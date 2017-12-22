import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Duet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String allInstructions = "";
        while(sc.hasNext()) {
            allInstructions += sc.nextLine() + ",";
        }
        String[] instructions = allInstructions.split(",");

        int frequency = 0;
        int result = 0; // to be set by first call to rcv when argument isn't 0
        Hashtable<String, Integer> registers = new Hashtable<>();
        boolean recovered = false;

        // deal with each instruction
        for(int i = 0; i < instructions.length && !recovered; i++) {
            String instruction = instructions[i];
            String function = instruction.substring(0, 3);
            // register and value used by some cases to improve readability
            String register = "";
            int value = 0;
            System.out.println(instruction);
            switch (function) {
                case "snd":
                    try {
                        frequency = Integer.parseInt(instruction.substring(4));
                    } catch (NumberFormatException e) {
                        frequency = registers.get(instruction.substring(4));
                    }
                    break;
                case "set":
                    try {
                        value = Integer.parseInt(instruction.substring(6));
                    } catch (NumberFormatException e) {
                        value = registers.get(instruction.substring(6));
                    }
                    registers.put(instruction.substring(4, 5), value);
                    break;
                case "add":
                    try {
                        value = Integer.parseInt(instruction.substring(6));
                    } catch (NumberFormatException e) {
                        value = registers.get(instruction.substring(6));
                    }
                    register = instruction.substring(4, 5);
                    // the conditional checks if this register has been initialized yet
                    registers.put(register, ((registers.get(register) == null) ? 0 : registers.get(register)) + value);
                    break;
                case "mul":
                    try {
                        value = Integer.parseInt(instruction.substring(6));
                    } catch (NumberFormatException e) {
                        value = registers.get(instruction.substring(6));
                    }
                    register = instruction.substring(4, 5);
                    registers.put(register, ((registers.get(register) == null) ? 0 : registers.get(register)) * value);
                    break;
                case "mod":
                    try {
                        value = Integer.parseInt(instruction.substring(6));
                    } catch (NumberFormatException e) {
                        value = registers.get(instruction.substring(6));
                    }
                    register = instruction.substring(4, 5);
                    registers.put(register, ((registers.get(register) == null) ? 0 : registers.get(register)) % value);
                    break;
                case "rcv":
                    register = instruction.substring(4, 5);
                    if (registers.get(register) != 0) {
                        recovered = true;
                        result = frequency;
                    }
                    break;
                case "jgz":
                    int firstArgument = 0;
                    try {
                        firstArgument = Integer.parseInt(instruction.substring(4, 5));
                    } catch (NumberFormatException e) {
                        firstArgument = (registers.get(instruction.substring(4, 5)));
                    }
                    try {
                        value = Integer.parseInt(instruction.substring(6));
                    } catch (NumberFormatException e) {
                        value = registers.get(instruction.substring(6));
                    }
                    if (firstArgument > 0) {
                        if (value < 0)
                            i--;
                        i += value;

                    }
                    break;
            }
        }
        System.out.println(result);
    }
}
