package simulation;

import javax.swing.*;
import java.awt.*;

public class SmallView extends JPanel implements View {


    private static final long serialVersionUID = 1L;

    /**
     * Textfields corresponding to positions in the simulation grid.
     */
    private JTextField[][] textFields;
    
    private Model model; 

    private int width, height;

    public SmallView(int width, int height, Model model) {
        this.model = model;
        this.width = width;
        this.height = height;

        textFields = new JTextField[width][height];
        
        setBackground(Color.BLACK);
        setLayout(new GridLayout(height, width));
        
        for (int j = textFields[0].length - 1; j >= 0; j--)
            for (int i = 0; i < textFields.length; i++) {
                textFields[i][j] = new JTextField(1);
                textFields[i][j].setEditable(false);
                textFields[i][j].setBackground(Color.BLACK);
                add(textFields[i][j]);
            }
    }

    public void display() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height ; j++) {
                textFields[i][j].setText(model.getIconAt(i, j));
                textFields[i][j].setBackground(model.getColorAt(i, j));
            }
    }

    public JTextField getField(int i, int j) { return textFields[i][j]; }
}