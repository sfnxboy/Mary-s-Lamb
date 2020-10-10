/**
* 
* @author Amir ElTabakh <amir.eltabakh73@qmail.cuny.edu>
* @since 10/09/2020
* @version 1
* @desription Sort words in a textfile based on validity and output to a GUI
* 
*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Project1 {

    public static void main(String[] args) {

        // Initialize array variables that hold their respective words
        String[] master = new String[300];
        int masterCount = 0;

        String[] valid = new String[300];
        int validCount = 0;

        String[] invalid = new String[300];
        int invalidCount = 0;

        // Initialize File variable to hold .txt file
        File file = new File("MarysLamb.txt");

        
        //Read the file with a try-except block
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String word = reader.readLine();

            while(word != null) {
                StringTokenizer tokenizer = new StringTokenizer(word);
                while(tokenizer.hasMoreTokens()) {
                    master[masterCount] = tokenizer.nextToken();
                    masterCount++;
                }
                word = reader.readLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Loops through words
        for (int i = 0; i < masterCount; i++) {
            boolean isvalid = true;
            // Loops through chars to test for validity
            for (int j = 0; j < master[i].length(); j++) {
                if (!Character.isLetter(master[i].charAt(j))) {
                    isvalid = false;
                }
            }

            // appends word to Array depending on bool value
            if (isvalid) {
                valid[validCount++] = master[i];
            }
            else {
                invalid[invalidCount++] = master[i];
            }
        }

        // Selection Sort
        int lowestIndex;
        for (int i = 0; i < validCount; i++) {
            lowestIndex = i;
            for (int j = i; j < validCount; j++) {
                if(valid[j].compareToIgnoreCase(valid[lowestIndex]) < 0) {
                    lowestIndex = j;
                }
            }

            String holder = valid[i];
            valid[i] = valid[lowestIndex];
            valid[lowestIndex] = holder;
        }

        // Input results into GUI
        WordGUI gui = new WordGUI();

        gui.addToMasterArea("***Master List*** \n\n");
        gui.addToValidArea("*** Valid List *** \n\n");
        gui.addToInvalidArea("*** Invalid List *** \n\n");


        for (int i = 0; i < masterCount; i++) {
            gui.addToMasterArea(master[i] + "\n");
        }
        for (int i = 0; i < validCount; i++) {
            gui.addToValidArea(valid[i] + "\n");
        }
        for (int i = 0; i < invalidCount; i++) {
            gui.addToInvalidArea(invalid[i] + "\n");
        }
    }
}