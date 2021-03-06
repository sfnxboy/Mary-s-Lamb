import javax.swing.*;
import java.awt.*;

public class WordGUI extends JFrame{
    
    /**
     * The line below is generated by VSCode
     */
    private static final long serialVersionUID = 1L;
    JTextArea master = new JTextArea();
    JTextArea valid = new JTextArea();
    JTextArea invalid = new JTextArea();

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
}
