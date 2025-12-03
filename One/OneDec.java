package One;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class OneDec {
    private String rotation; //L (går mod mindre tal) or R (går mod højere tal)
    private int distance; //Hvor mange pladser der skal rykkes
    private int position; //Start position på drejeknappen
    private int zeroCount; //Tæller hvor mange gange den rammer 0

    public OneDec(){
        this.rotation = "";
        this.distance = 0;
        this.position = 50; //start position
        this.zeroCount = 0;
    }

    public void rotate(String[] commands) {
        this.rotation = commands[0];
        this.distance = Integer.parseInt(commands[1]);

        if (rotation.equals("R")) {
            for (int i = 0; i < distance; i++) {
                position = (position + 1) % 100;
                if (position == 0) {
                    zeroCount++;
                }
            }
        } else if (rotation.equals("L")) {
            for (int i = 0; i < distance; i++) {
                position = (position - 1 + 100) % 100;
                if (position == 0) {
                    zeroCount++;
                }
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public void countZero(String[] commands) {
        rotate(commands);
            // if (getPosition() == 0) {
            // this.zeroCount++;
            // }
        }

    public static void main(String[] args) {
        try{
        Scanner sc = new Scanner(new File("One/input.txt"));
        OneDec dial = new OneDec();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            String rot = line.substring(0, 1);
            String dir = line.substring(1);
            String[] commands = { rot, dir };
            dial.countZero(commands);
        }
        sc.close();
        System.out.println("Zero count: " + dial.zeroCount);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
/*
Eksempel input/output:
if the dial were pointing at 11, a rotation of R8 would cause the dial to point at 19. After that, a rotation of L19 would cause it to point at 0
Because the dial is a circle, turning the dial left from 0 one click makes it point at 99. Similarly, turning the dial right from 99 one click makes it point at 0.

The dial starts by pointing at 50.
The dial is rotated L68 to point at 82; during this rotation, it points at 0 once.
*/