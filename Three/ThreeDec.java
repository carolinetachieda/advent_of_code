package Three;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class ThreeDec {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Three/input.txt"));
            // Scanner sc = new Scanner(System.in);
            int totalJolts = 0;
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[][] banks = Arrays.stream(line.split("\n")).map(bank -> bank.split("")).toArray(String[][] :: new);

                for (String[] bank : banks) {
                    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //max heap

                    for (int i = 0; i < bank.length; i++) { // alle i en bank
                        for (int j = i+1; j < bank.length; j++) { // starter fra den næste efter i
                            int joltage = Integer.parseInt(bank[i] + bank[j]); 
                            pq.add(joltage); //lægger til kø
                        }
                    }
                    totalJolts += pq.poll(); //anden største//fjerner største
                }
              
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