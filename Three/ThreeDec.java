package Three;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ThreeDec {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Three/input.txt"));
            // Scanner sc = new Scanner(System.in); 

            long totalJolts = 0L; 

            //gå igennem alle linjer (banker) i inputfilen
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                String bank = line;//hele rækken af batterier som en streng
                int n = bank.length();    
                int keep = 12;             
                int toRemove = n - keep; 
                StringBuilder sb = new StringBuilder(); //stack til det bedste tal

                // gå venstre -> højre igennem alle digits i banken
                for (int i = 0; i < n; i++) {
                    char d = bank.charAt(i); //nuværende

                    while (toRemove > 0
                            && sb.length() > 0
                            && sb.charAt(sb.length() - 1) < d) {
                        sb.deleteCharAt(sb.length() - 1); //fjern sidste d
                        toRemove--;                     
                    }

                    //tilføj d til stack
                    sb.append(d);
                }
                while (toRemove > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    toRemove--;
                }

                if (sb.length() > keep) {
                    sb.setLength(keep);
                }

                String joltageStr = sb.toString();
                long joltage = Long.parseLong(joltageStr);

                //læg til totalen
                totalJolts += joltage;
            }
            System.out.println("Total jolts: " + totalJolts);

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

/*
 * joltage rating, a value from 1 to 9.
 * For example, if you have a bank like 12345 and you turn on batteries 2 and 4,
 * the bank would produce 24 jolts. (You cannot rearrange batteries.)
 * 
 * Now, you need to make the largest joltage by turning on exactly twelve batteries within each bank.
 */