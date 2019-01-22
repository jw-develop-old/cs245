package ctrl;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class RealVCWindow implements VCWindow {

    private JLabel current;
    private JLabel max;
    private JTextArea area;
    private JButton previous;
    private JButton commit;
    private JButton next;
    private JButton parallel;
    private JButton goTo;
    private JTextField whereTo;

    private class SuperButton extends JButton implements ActionListener{
        /**
		 * Generated so Eclipse won't make a yellow squiggle (note irony)
		 */
		private static final long serialVersionUID = 1979061031664637850L;
		private HashSet<ActionListener> listeners = new HashSet<ActionListener>();
        private JTextField field;
        public SuperButton(String title, JTextField field) { 
            super(title);
            super.addActionListener(this);
            this.field = field;
        }
        public void addActionListener(ActionListener l) {
            listeners.add(l);
        }
        public void actionPerformed(ActionEvent e) {
            ActionEvent ee = new ActionEvent(e.getSource(), 
                                             ActionEvent.ACTION_PERFORMED, field.getText());
            for (Iterator<ActionListener> it = listeners.iterator(); it.hasNext(); )
                it.next().actionPerformed(ee);
        }
    }

    public RealVCWindow() {
        JFrame window = new JFrame("Version Control");
        //window.setLayout(new GridLayout(3, 1));
        window.setLayout(new FlowLayout());
        window.setSize(400, 650);
        window.setLocation(100, 100);

        JPanel upper = new JPanel();
        upper.setLayout(new BorderLayout());
        current = new JLabel("Current");
        max = new JLabel("Max");        
        upper.add(current, BorderLayout.WEST);

        JPanel goToPanel = new JPanel();
        goToPanel.setLayout(new FlowLayout());
        whereTo = new JTextField(3);
        goTo = new SuperButton("Go to", whereTo);
        goToPanel.add(new JLabel("           "));
        goToPanel.add(goTo);
        goToPanel.add(whereTo);
        goToPanel.add(new JLabel("           "));
       //upper.add(new JLabel("                                                  "), BorderLayout.CENTER);
        upper.add(goToPanel, BorderLayout.CENTER);

        upper.add(max, BorderLayout.EAST);
        window.add(upper);

        JPanel middle = new JPanel();
        middle.setLayout(new FlowLayout());
        area = new JTextArea(32, 30);
        middle.add(area);
        window.add(middle);

        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(2, 1));
        
        JPanel firstLower = new JPanel();
        firstLower.setLayout(new FlowLayout());
        commit = new JButton("Commit");
        firstLower.add(commit);
        lower.add(firstLower);

        JPanel secondLower = new JPanel();
        secondLower.setLayout(new FlowLayout());
        previous = new JButton("<");
        parallel = new JButton("-");
        next = new JButton(">");
        secondLower.add(previous);
        secondLower.add(parallel);
        secondLower.add(next);
        lower.add(secondLower);

        window.add(lower);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    public void setCurrentVersion(int version) {
        current.setText("Current: " + version);
    }

    public void setMaxVersion(int version) {
        max.setText("Max: " + version);
    }

    public void writeText(String txt) { area.setText(txt); }

    public String getText() { return area.getText(); }

    public JButton getCommitButton() { return commit; }

    public JButton getPreviousButton() { return previous; }

    public JButton getParallelButton() { return parallel; }

    public JButton getNextButton() { return next; }

    public JButton getGoToButton() { return goTo; }
}
