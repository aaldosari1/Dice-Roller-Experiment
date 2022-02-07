import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;


public class Images extends JPanel {

    JPanel Panel = new JPanel();
    GridLayout grid; //GridLayout grid is used to change the layout of the Dice as the number of Dice selected is changed 
    final int DICE_H_GAP = 35; //DICE_H_GAP stores the size of horizontal gaps between the Dice 
    final int DICE_V_GAP = 5; //DICE_V_GAP stores the size of vertical gaps between the Dice
    int NUM_COLUMS = 1; // This variable stores the number of columns that is passed to "grid" as a parameter to set the number of columns for the GridLayout 
    public int vis = 1; //  The number of visible dice. It is set to 1 by default
    int X_arr[] = new int[10]; // Array to store a variable x in all the 10 places. Used inside the timer and passed to "SetImage()"  method to display the animation
    int sequence[] = {
        1,
        2,
        3,
        4,
        5,
        6,
        1,
        2,
        3,
        4
    }; // Storing arbitrary numbers to display which dice face is selected when the user changes the number of dice selected.  
    JLabel pic1 = new JLabel();
    JLabel pic2 = new JLabel();
    JLabel pic3 = new JLabel();
    JLabel pic4 = new JLabel();
    JLabel pic5 = new JLabel(); //Instantiating the label objects that will store the pictures
    JLabel pic6 = new JLabel();
    JLabel pic7 = new JLabel();
    JLabel pic8 = new JLabel();
    JLabel pic9 = new JLabel();
    JLabel pic10 = new JLabel();
    JLabel Objects[] = {
        pic1,
        pic2,
        pic3,
        pic4,
        pic5,
        pic6,
        pic7,
        pic8,
        pic9,
        pic10
    }; // Storing JLabel variables in an array, so they can be used inside a loop. 

    final int DIMENSION = 150; //The dimensions for the dice

    //Images In Array
    String[] list = {
        "dice-six-faces-six.png",
        "dice-six-faces-one.png", //1
        "dice-six-faces-two.png", //2
        "dice-six-faces-three.png", //3
        "dice-six-faces-four.png", //4
        "dice-six-faces-five.png", //5
        "dice-six-faces-six.png" //6
    };

    //constructor 
    public Images() {
        //This loop is used to set the size of the images
        for (int a = 0; a < Objects.length; a++) {
            Objects[a].setSize(DIMENSION, DIMENSION);
        }

    }

    // Roller method that takes an array of intgers to match them with dice faces. The boolean is used to whether to run the timer or not  
    public void Roller(int[] arr, boolean Swtch) {

        //Call The method SetImage. This if statment will excute 
        //When "Swtch" is false no animation will show, only constant dice face  
        if (Swtch == false) {
            SetImage(sequence);
        }
        //This is the timer code where the "SetImage()" method is called and the "X_arr" is passed to, to show the animation   
        java.util.Timer time = new java.util.Timer();
        time.scheduleAtFixedRate(new java.util.TimerTask() {
            //This variable is initially equal to zero and it is the variable that will be stored in "X_arr" and incremented every time the timer ticks     
            int x = 0;
            @Override
            public void run() {


                if (x < list.length - 1 && Swtch == true) {
                    // Every time the x is incremented it will be stored in the "X_arr" array  
                    x += 1;
                    for (int i = 0; i < X_arr.length; i++) {
                        X_arr[i] = x;
                    }
                    // The method "SetImage()" will be called and the array will be passed every time the timer is ticking.    
                    SetImage(X_arr);
                    // When the x value is equal to "list.length - 1" it means shuffling animation is done
                } else if (x == list.length - 1 && Swtch == true) {
                    //This is calling "SetImage(arr)" method and passing "arr" array which was passed to the "Roller()" method
                    // "arr" contains the values of the first row of the table. it is passed to the "Roller()" method to make the 
                    // dice faces correspond to the first row of the table when the dice animation stop
                    SetImage(arr);
                    // to cancel the timer
                    time.cancel();
                    // to let the timer stop when the program is closed 
                } else
                    time.cancel();
            }
        }, 110, 110); //The first parameter is the delay before the timer starts, the second parameter is the time between each timer ticks  

        // Conditions to set the number of columns for the GridLayout based on the value of "vis"
        if (vis < 6)
            NUM_COLUMS = vis;
        else if (vis == 6 || vis == 8)
            NUM_COLUMS = vis / 2;
        else if (vis == 7)
            NUM_COLUMS = 4;
        else
            NUM_COLUMS = 5;
        //NUM_COLUMS ,DICE_H_GAP and DICE_V_GAP are now passed variable "grid", the 0 is for the row. I kept zero and evry thing works fine. 
        grid = new GridLayout(0, NUM_COLUMS, DICE_H_GAP, DICE_V_GAP);
        //Removes the components from the Panel to refresh it every time the "Roller()" method is called  
        Panel.removeAll();

        //  Adding the pic labels to the Panel
        for (int i = 0; i < Objects.length; i++) {
            Panel.add(Objects[i]);
        }
        // Adding the Panel
        add(Panel);
        // Setting all the pictures' visibility to 'false' 
        for (int j = 0; j < Objects.length; j++) {
            Objects[j].setVisible(false);
        }
        // Setting pictures' visibility to 'true' based on the number of selected dice         
        for (int k = 0; k < vis; k++) {
            Objects[k].setVisible(true);
        }
        //Passing the "grid" variable to the Panel to set the layout of the dice
        Panel.setLayout(grid);

    }

    public void SetImage(int arr[]) {

        //This loop stores the pictures in the "newImcs"to the "pics" labels so it can be added to the JPanel
        for (int i = 0; i < 10; i++) {
            //Creating ImageIcon variable to hold the pictures
            ImageIcon icon1 = new ImageIcon(list[arr[i]]);
            //Creating Image variables and setting them equal to the images stored in the 'icon1', so the dimensions can be changed 
            Image img1 = icon1.getImage();
            //Storing the imgaes in a ImageIcon variable below and setting the dimensions by using getWidth() getHeight()
            ImageIcon newImc1 = new ImageIcon(img1.getScaledInstance(pic1.getWidth(), pic1.getHeight(), Image.SCALE_SMOOTH));
            //Stores the pictures in the "pic1, pic2, ... pic10" labels that are created at line 22, so it can be added to the JPanel
            Objects[i].setIcon(newImc1);

        }

    }
}