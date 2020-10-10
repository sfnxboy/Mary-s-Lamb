# Mary’s Lamb
## Programs Used
Java

### Libraries used
```
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.*;
import java.awt.*;
```

## Overview
The purpose of this project is to filter a given text file based on the validity of the words within the text file and to the sort the words within the files into two arrays, one by the name of “valid” to hold words containing only alphabetical letters, and another called “invalid” to hold words that contain non-alphabetical characters. To present our results, we will output our results into a graphical user interface.

## Project1.java Process
We first initialize three arrays. A master array to hold all the words in the text file, a “valid” array to hold words containing only letters, and an “invalid” array to hold words containing non-alphabetical letters.
```
        // Initialize array variables that hold their respective words
        String[] master = new String[300];
        int masterCount = 0;

        String[] valid = new String[300];
        int validCount = 0;

        String[] invalid = new String[300];
        int invalidCount = 0;
```

There are a number of Java classes used to read the text file. To first access the file we use the File class. The File class allows the Java Virtual Machine to access files, file attributes and file systems. The BufferedReader class reads text from a character-input stream efficiently. The StringTokenizer class allows an application to bread a string into tokens, or in our case words. These words are then appended to the master list. The reader.close() method closes the stream and releases any system resources associated with it. In the case that any methods are used on the text file after closing the stream, the IOException class will signal that an I/O exception of some sort has occurred by failed or interrupted I/O operations. 
```
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
```

A nested for loop is used to loop through the words in the master array, and through the characters within each word. The Boolean variable isValid will be used to determine whether a word contains only alphabetical letters or not.  The isLetter() method of the Character class determines whether the specified character is a letter or not. The if-else block towards the end of the outer for loop appends the given word to the correct array on the basis of the isValid variable.
```
        // Loops through words
        for (int i = 0; i < masterCount; i++) {
            boolean isValid = true;
            // Loops through chars to test for validity
            for (int j = 0; j < master[i].length(); j++) {
                if (!Character.isLetter(master[i].charAt(j))) {
                    isValid = false;
                }
            }

            // appends word to Array depending on bool value
            if (isValid) {
                valid[validCount++] = master[i];
            }
            else {
                invalid[invalidCount++] = master[i];
            }
        }
```


To enhance readability of the valid array, we use a simple selection sort.
```
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
```

I call the WordGUI class from the WordGUI.java file to input the results into the GUI. Three for loops are used, one for each array, to input each arrays elements into their corresponding section.
```
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
```

## WordGUI.java Process
We first initialize three JTextArea variables, one for each array.
```
    JTextArea master = new JTextArea();
    JTextArea valid = new JTextArea();
    JTextArea invalid = new JTextArea();
```

Below is the WordGUI class used to construct the GUI with given specifications. The class must be public so that other programs may be able to call it, such as the Program1.java file.
```
    public WordGUI() {
        this.setSize(800, 600);
        this.setTitle("Project 1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,3));

        JScrollPane scrollPane1 = new JScrollPane();
        JScrollPane scrollPane2 = new JScrollPane();
        JScrollPane scrollPane3 = new JScrollPane();

        scrollPane1.setViewportView(master);
        scrollPane2.setViewportView(valid);
        scrollPane3.setViewportView(invalid);

        this.add(scrollPane1);
        this.add(scrollPane2);
        this.add(scrollPane3);
        this.setVisible(true);
    }
```

The code below includes three constructor methods that will be used by the Project1.java file to input each array into its respective GUI panel. Within each method, we append the respective JTextArea method.
```
    // WordGUI methods to add JTextArea variables to GUI panels
    public void addToMasterArea(String str) {
        master.append(str);
    }
    public void addToValidArea(String str) {
        valid.append(str);
    }
    public void addToInvalidArea(String str) {
        invalid.append(str);
    }
```

## Results
![image](https://user-images.githubusercontent.com/68082808/95660127-bc744800-0af3-11eb-81b9-2e03213b941e.png)

The arrays upload properly. The Master List panel contains all the words contained within the .txt file in the order in which they appear. The Valid List panel contains all words containing only alphabetical characters, and lastly the Invalid List panel contains all words containing non-alphabetical characters.
