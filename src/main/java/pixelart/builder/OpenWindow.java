package pixelart.builder;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class OpenWindow extends JPanel {
 
    public OpenWindow() {
        super(new BorderLayout());
    }
    
    private File createAndShowGUI() {
        JFrame frame = new JFrame("FileChooser");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new OpenWindow());
        
        JFileChooser fc = new JFileChooser();
        
        int returnVal = fc.showOpenDialog(OpenWindow.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            return file;
        } else {
            return null;
        }
    }
    
    public File runWindow() {
    	UIManager.put("swing.boldMetal", Boolean.FALSE);
    	return createAndShowGUI();
    }
}