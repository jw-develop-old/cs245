package predprey;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.io.*;
import simulation.*;


public class PredPrey {

    private View view;
    private SimulationGrid<Agent> model;
    volatile private boolean isRunning;
    private int width, height;
    private int simSize;
    
    
    /**
     * @param simSize 0="small", 60x30; 1="medium", 90x120; 2="big", 180x120
     */
    private PredPrey(int simSize) {
        isRunning = false;
        this.simSize = simSize;
        
        switch (simSize) {
        case 2: 
            width = 180; 
            height = 120; 
            break;
        case 1:
            width = 90;
            height = 120;
            break;
        case 0: default:
            width = 60;
            height = 30; 
        }

        model = new SimulationGrid<Agent>(width, height, new Agent[width][height]);
        
        File dir = new File("predprey");
        File[] files = dir.listFiles();
        
        final ArrayList<Class<Agent>> agentClasses = new ArrayList<Class<Agent>>();
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<Agent> agentInterface = null;
            try {
                agentInterface = (Class<Agent>) loader.loadClass("predprey.Agent");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Cannot find Agent interface.");
                System.exit(-1);
            } catch (ClassCastException cce) {
                System.out.println("Error loading Agent interface.");
                System.exit(-1);
            }

            
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (fileName.endsWith(".class")) {
                    Class<Agent> clazz = null;
                    try {
                        clazz = (Class<Agent>) loader.loadClass("predprey." + fileName.substring(0, fileName.length()-6));
                    } catch (ClassNotFoundException cnfe) {
                        System.out.println("Problems with  " + fileName);
                        System.exit(-1);
                    } catch (ClassCastException cce) {
                        System.out.println("Problems with  " + fileName);
                        System.exit(-1);
                    }
                    
                    if (! clazz.isInterface() && ! Modifier.isAbstract(clazz.getModifiers())
                        && agentInterface.isAssignableFrom(clazz))
                        agentClasses.add((Class<Agent>) clazz);  // here's the cast
                }
            }

        
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                getInitialPopulation(agentClasses);
            }
        });
         

 
    }


    // If we were programming in ML, we wouldn't have to do this.
    private class InitialSpeciesPopulation {
        public Class<Agent> species;
        public int population;
        public InitialSpeciesPopulation(Class<Agent> species, int population) {
            this.species = species;
            this.population = population;
        }
    }

    private void getInitialPopulation(final ArrayList<Class<Agent>> agentClasses) {
        final ArrayList<InitialSpeciesPopulation> specs = 
            new ArrayList<InitialSpeciesPopulation>();


            final JFrame queryWindow = new JFrame();
            queryWindow.setLocation(100, 100);
            queryWindow.setSize(400, 400);
            queryWindow.setLayout(new FlowLayout());
            
            queryWindow.add(new JLabel("Please enter the initial population for each species."));

            final HashMap<Class<Agent>, JTextField> popMap = 
                new HashMap<Class<Agent>, JTextField>();

            JPanel speciesPanel = new JPanel();
            speciesPanel.setLayout(new GridLayout(agentClasses.size(), 2));
            for (Iterator<Class<Agent>> it = agentClasses.iterator(); it.hasNext(); ) {
                JTextField field = new JTextField(6);
                Class<Agent> currentSpecies = it.next();
                speciesPanel.add(new JLabel(currentSpecies.getName()));
                speciesPanel.add(field);
                popMap.put(currentSpecies, field);
            }
            
            queryWindow.add(speciesPanel);
            JButton okButton = new JButton("Ok");

            okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        for (Iterator<Class<Agent>> it = agentClasses.iterator(); it.hasNext(); ) {
                            Class<Agent> currentSpecies = it.next();
                            JTextField field = popMap.get(currentSpecies);
                            int population = 0;
                            try {
                                population = Integer.parseInt(field.getText());
                            } catch (NumberFormatException nfe) {
                                // silently ignore errors, stick with zero
                            }
                            specs.add(new InitialSpeciesPopulation(currentSpecies, population));
                        }
                        queryWindow.setVisible(false);
                        (new Thread(new Runnable() {
                            public void run() { setUp(specs); }
                        })).start();
                    }
                });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(okButton);
            queryWindow.add(buttonPanel);
            queryWindow.setVisible(true);

    }

    private void setUp(ArrayList<InitialSpeciesPopulation> specs) {
        synchronized(model) {
            for (Iterator<InitialSpeciesPopulation> it = specs.iterator(); it.hasNext(); ) {
                InitialSpeciesPopulation isp = it.next();
                for (int i = 0; i < isp.population; i++) {               
                    int x = (int) Math.floor(width * Math.random());
                    int y = (int) Math.floor(height * Math.random());
                    Agent current = null;
                    try {
                        current = isp.species.newInstance();
                    } catch(Throwable th) {
                        System.out.println("Got the following " + th.getClass().toString().substring(6) + 
                                " when trying to instantiate " + isp.species + ":");
                        System.out.println(th.getMessage());
                        break;
                    }
                    // It's possible we'll overwrite (effectively kill) another agent that
                    // happened to be initialized to the same position. Seems to be
                    // the best way to deal with the possibility of such a collision.
                    // If we keep looking for an empty space, then we could loop if the
                    // user asks for too many initial individuals; giving preference to the
                    // first agent in the space requires slightly longer code. Instead I wrote an even
                    // longer comment.
                    current.enterGrid(model, x, y);  
                    model.setAgentAt(x, y, current);
                    //(new Thread(current)).start();
                }
            }
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // make the window 
                SimWindow window;
        
                switch (simSize) {
                case 2:
                    window = new BVWindow(width, height, model);
                    break;
/*
                case 1:
                 window = new SplitWindow(width, height, model);
                 break;
*/
                case 0: default:
                    window = new SVWindow(width, height, model);
                }

                //get the view.
                view = window.getView();

                // connect to the button
                final JButton button = window.getButton();
                button.setText("Start");
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        isRunning = !isRunning;
                        if (isRunning)
                            button.setText("Stop");
                        else
                            button.setText("Start");
                    }
                });
                view.display();
            }});

        // turn into the "go" thread
        go();
    }
    

    private void go() {

        for (;;) {
            while (!isRunning)
                try { Thread.sleep(10); }
            catch(InterruptedException ie) { }

            HashSet<Agent> movedAlready = new HashSet<Agent>();

            for (int i = 0; i < width; i++)
                for (int j = 0; j < height ; j++) {
                    Agent current = model.getAgentAt(i, j);
                    if (current != null && ! movedAlready.contains(current)) {
                        current.act();
                        movedAlready.add(current);
                    }
                }


            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    view.display();
                }
            });
        
            try { Thread.sleep(1000); }
            catch(InterruptedException ie) { }

        }
    }            
        

    public static void main(String[] args) {

        int simSize = 0;
        if (args.length > 0) {
            if (args[0].equals("-big")) simSize = 2;
            else if (args[0].equals("-medium")) simSize = 1;
            else if (args[0].equals("-small")) simSize = 0;
            else {
                System.out.println("Usage: java predprey [option]\n" +
                                   "where options are\n" +
                                   "\t-small\t60 x 30 grid with \"small view\" window (default)\n" +
                                   "\t-medium\t90 x 120 grid with \"split view\" window\n" +
                                   "\t-big\t180 x 120 grid with \"big view\" window");
                System.exit(0);
            }
        }

        new PredPrey(simSize);
    }

}