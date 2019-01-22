package simulation;

import javax.swing.*;
import java.awt.*;

public class SVWindow implements SimWindow {


    /**
     * The window itself.
     */
    private JFrame window;

    /**
     * Button to start and stop the simulation
     */
    private JButton button;

    /**
     * The portion of the window showing the grid
     */
    private SmallView gridPanel;

    public SVWindow(int width, int height, Model model) {
        window = new JFrame();
        window.setLocation(100, 100);
        String os = System.getProperty("os.name");
        if (os.equals("Mac OS X"))
            window.setSize(width * 15 + 350, height * 20 + 125);
        else // if (os.equals("Linux"))
            window.setSize(width * 15 + 30, height * 20 + 75);

        // Panel that contains the grid
        gridPanel = new SmallView(width, height, model);

        window.setLayout(new FlowLayout());
        window.add(gridPanel);
        button = new JButton("");
        window.add(button);

        //button.addActionListener(buttonListener);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }


    public JButton getButton() { return button; }

    public View getView() { return gridPanel; }


}