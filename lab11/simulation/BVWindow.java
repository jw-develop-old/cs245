package simulation;

import javax.swing.*;
import java.awt.*;

public class BVWindow implements SimWindow {

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
    private BigView bView;

    public BVWindow(int width, int height, Model model) {
        window = new JFrame();
        window.setLocation(100, 100);
        String os = System.getProperty("os.name");
        if (os.equals("Mac OS X"))
            window.setSize(60 * 15 + 350, 30 * 20 + 125);
        else // if (os.equals("Linux"))
            window.setSize(60 * 15 + 30, 30 * 20 + 75);

        
        // Panel that contains the grid
        bView = new BigView(width, height, 5, model);

        window.setLayout(new FlowLayout());
        window.add(bView);
        button = new JButton("");
        window.add(button);
        
        /*
        // Panel that contains the two grid panels
        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new GridLayout(1, 2));
 
        bView = new BigView(width, height, 5, model);
        bigPanel.add(bView);

       // Panel that contains the grid
        gridPanel = new SmallView(width, height, model);

        JScrollPane scroller = new JScrollPane(gridPanel);
        scroller.setPreferredSize(new Dimension(450, 600));
        //bigPanel.add(scroller);
        bigPanel.add(new JLabel("."));

        window.setLayout(new FlowLayout());
        window.add(bigPanel);
        button = new JButton("");
        window.add(button);
        */
        //button.addActionListener(buttonListener);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }


    public JButton getButton() { return button; }

    public View getView() { return bView; }

}