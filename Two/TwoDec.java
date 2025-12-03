package Two;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 

public class TwoDec {

   public static boolean isInvalid(long id) {
    String idString = String.valueOf(id);
    int len = idString.length(); //den faktiske længde
    
    //checker for gentagelser
    for (int patternLen = 1; patternLen <= len / 2; patternLen++) { //loop i alle mulige pattern længder (op til halvdelen af længden)
        if (len % patternLen == 0) { //kan divideres helt med pattern længden
            String pattern = idString.substring(0, patternLen);
            boolean isRepeated = true;
            
            // Check if the pattern repeats throughout the entire string
            for (int i = patternLen; i < len; i += patternLen) {
                String segment = idString.substring(i, i + patternLen); //trækker delen af id
                if (!segment.equals(pattern)) { //tjekker om delen matcher pattern
                    isRepeated = false; 
                    break;
                }
            }
            
            // invalid hvis mønsteret gentages mindst to gange
            if (isRepeated && len / patternLen >= 2) {
                return true;
            }
        }
    }
    
    return false;
}

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("Two/input.txt"));
            long sum = 0;
            
            while (sc.hasNext()) {
                String[] ranges = sc.nextLine().split(",");
                for (String range : ranges) {
                    String[] parts = range.trim().split("-");
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    
                    // checker alle id'er i range
                    for (long id = start; id <= end; id++) {
                        if (isInvalid(id)) {
                            sum += id;
                        }
                    }
                }
            }
            
            System.out.println("Sum of invalid IDs: " + sum);
            sc.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}

/*
11-22 has two invalid IDs, 11 and 22. 11, 12, 13..,22 der er ikke dobbelt tal i range udover 11 og 22.

95-115 now has two invalid IDs, 99 and 111.
*/