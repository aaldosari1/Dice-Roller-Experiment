import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import java.awt.*;
//import javax.swing.BoxLayout;
import javax.swing.*;

public class Frame extends JFrame {
    //Creating the buttons 
    private JButton Run_btn, Clear_btn;
    //Creating panels  
    JPanel PramBar = new JPanel();
    JPanel TablePanel = new JPanel();

    // Creating the frame and the dropdown menus 
    private JFrame frame;
    private JComboBox Runs_Num;
    private JComboBox Dice_Num;
    private JComboBox Occ_Num;
    //Saving the dimesions of the frame
    final int FrameWidth = 1080;
    final int FrameLength = 1920;


    public Frame() {
        //Instantiating the frame and giving it a title 
        frame = new JFrame("Dice Roller Experiment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the program to close when the close botton is clicked  
        frame.setSize(FrameLength, FrameWidth); //Passing the variables of the dimesions to the frame
        //Setting the icon of the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("SU_twitter.jpg");
        frame.setIconImage(icon);
        // Number of runs stored in array to be passed to the JComboBox object
        String[] runs = {
            "30",
            "50",
            "100"
        };
        // Number of dice stored in array to be passed to the JComboBox object
        String[] dice = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
        };
        //Occurences values to be passed to the JComboBox object
        String[] Occ = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"
        };

        Table table = new Table(); //Instantiating the table object from the Table class
        Images images = new Images(); //Instantiating the image object from the Image class
        //Instantiating the drop down menus and the labels and passing the arrays to the JComboBox objects
        Runs_Num = new JComboBox(runs);
        Dice_Num = new JComboBox(dice);
        Occ_Num = new JComboBox(Occ);
        JLabel Runs = new JLabel("Runs:");
        JLabel Dice = new JLabel("No. of Dice:");
        JLabel Occurrences = new JLabel("Occurrences of:");

        //Instantiating the buttons 
        Run_btn = new JButton("Run");
        Clear_btn = new JButton("Clear");


        //Adding components to the Panels 

        PramBar.add(Runs);
        PramBar.add(Runs_Num);
        PramBar.add(Box.createRigidArea(new Dimension(35, 0))); // Adding space between components
        PramBar.add(Dice);
        PramBar.add(Dice_Num);
        PramBar.add(Box.createRigidArea(new Dimension(35, 0)));
        PramBar.add(Occurrences);
        PramBar.add(Occ_Num);
        PramBar.add(Box.createRigidArea(new Dimension(35, 0)));
        PramBar.add(Run_btn);
        PramBar.add(Clear_btn);
        TablePanel.add(table);


        // Setting the Vetrical gap between panels in the frame to 55 while keeping the Horizontal gap = 0
        frame.setLayout(new BorderLayout(0, 55));
        //Adding the panels to the frame and specifying the locations using BorderLayout 
        frame.add(PramBar, BorderLayout.NORTH);
        frame.add(TablePanel, BorderLayout.SOUTH);
        frame.add(images, BorderLayout.CENTER);
        frame.setVisible(true);
        //Calling the "Roller" method from Image class to display the picture of the a die when the program starts 
        images.Roller(table.First_Row, false);

        // Action listner for "Runs" drop down menu 
        ActionListener Runs_Num_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = (String) Runs_Num.getSelectedItem();

                switch (msg) {
                    case "30":
                        table.Repetion = 30;

                        break;
                    case "50":
                        table.Repetion = 50;
                        break;

                    case "100":
                        table.Repetion = 100;
                        break;
                }
            }
        };

        // Action listner for 'Run' button
        ActionListener Run_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //This timer is used to delay the display of the table to allow the dice animation to finish
                java.util.Timer time = new java.util.Timer();

                time.scheduleAtFixedRate(new java.util.TimerTask() {
                    int time_cout = 0;
                    @Override
                    public void run() {
                        //The timer ticks for five times before the table is displayed, then the timer will cancel
                        if (time_cout == 5) {
                            table.setVisible(true);
                            time.cancel();
                        }
                        time_cout++; //incrementing 
                    }
                }, 200, 220);

                table.setVisible(false); //To hide the table when the 'Run' button is clicked
                table.removeAll(); //remove the table  before calling the new table
                images.removeAll(); //remove the dice  before calling the new dice animation
                table.buildTable(); // Calling 'buildTable()' method to create the table
                images.Roller(table.First_Row, true); // Calling "Roller" method to display dice. This method takes the array from the table class
                // and a boolean value "true" which means the animation will run


            }
        };
        // Action listner for 'Clear' button
        ActionListener Clear_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                table.removeAll(); //Removes the table
                images.removeAll(); //Removes the dice

                SwingUtilities.updateComponentTreeUI(TablePanel); // Refersh/update TablePanel

            }
        };

        // Action listner for "Dice" drop down menu 
        ActionListener Dice_Num_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = (String) Dice_Num.getSelectedItem();
                switch (msg) {
                    case "1":
                        table.Col_Num = 3; //Setting the number of columns for the table. When one dice is selected there will be a total of 3 columns
                        //one for the ID number, one for dice 1, and one for the occurences.                                       
                        images.vis = 1; //number of visible dice 
                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false); //Calling 'Roller()' method to update the number of dice displayed
                        // as the user changes the number of dice selected and before clicking the 'Run' button 
                        break;

                    case "2":
                        table.Col_Num = 4; //When two dice is selected there will be a total of 4 columns
                        //one for the ID number, one for dice 1,one for dice 2, and one for the occurences. And so on for the other cases below
                        images.vis = 2;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "3":
                        table.Col_Num = 5;
                        images.vis = 3;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "4":
                        table.Col_Num = 6;
                        images.vis = 4;
                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "5":
                        table.Col_Num = 7;
                        images.vis = 5;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "6":
                        table.Col_Num = 8;
                        images.vis = 6;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "7":
                        table.Col_Num = 9;
                        images.vis = 7;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "8":
                        table.Col_Num = 10;
                        images.vis = 8;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "9":
                        table.Col_Num = 11;
                        images.vis = 9;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;

                    case "10":
                        table.Col_Num = 12;
                        images.vis = 10;

                        table.removeAll();
                        images.removeAll();
                        images.Roller(table.First_Row, false);
                        break;
                }

            }
        };
        // Action listner for "Occurences" drop down menu
        ActionListener Occ_Num_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String msg = (String) Occ_Num.getSelectedItem();

                switch (msg) {
                    case "1":
                        table.Selected_Num = 1; //This set the number that the user want to track the occurnces of   
                        break;

                    case "2":
                        table.Selected_Num = 2;
                        break;

                    case "3":
                        table.Selected_Num = 3;
                        break;

                    case "4":
                        table.Selected_Num = 4;
                        break;

                    case "5":
                        table.Selected_Num = 5;
                        break;

                    case "6":
                        table.Selected_Num = 6;
                        break;

                }

            }
        };
        //Adding all the action listner so the can be functional  
        Runs_Num.addActionListener(Runs_Num_ActionListener);
        Dice_Num.addActionListener(Dice_Num_ActionListener);
        Occ_Num.addActionListener(Occ_Num_ActionListener);
        Run_btn.addActionListener(Run_ActionListener);
        Clear_btn.addActionListener(Clear_ActionListener);


    }


}