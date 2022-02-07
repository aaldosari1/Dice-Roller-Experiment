import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.table.JTableHeader;
import javax.swing.table.*;

public class Table extends JPanel {

    public int Repetion = 30; //Setting the defualt value of the number of 'Repetion' to 30
    public int Col_Num = 3; // Setting the defualt value of the number of colums 3, 'ID' column,'Die 1' column, and 'Occurances' column
    public int Selected_Num = 1; // Setting the defualt value of the selcted number that the user what to count the ooccurences of to 1
    public int First_Row[] = new int[10]; //To store the values of first row of the table 
    final int TableWidth = 520; //Table dimesions
    final int TableLength = 1580; //Table dimesions

    //Creating custom colors using RGB
    Color myWhite = new Color(235, 235, 235);
    Color myRED = new Color(255, 102, 102);
    Color myGray = new Color(120, 120, 120);

    Table() {

    }

    public void buildTable() {
        //data table that will store the random numbers. It is passed to the JTable Object
        String data[][] = new String[Repetion][Col_Num];
        //Array to save the columns' names of the table. It is passed to the JTable Object 
        String columns[] = new String[Col_Num];

        //Adding the columns names
        columns[0] = " Run ID";
        columns[Col_Num - 1] = "Occurrences";

        //Adding the columns names of the dice
        for (int k = 1; k < Col_Num - 1; k++) {
            columns[k] = "Dice " + (k);


        }
        // Creating the table object and passing the data names and the columns arrays 
        JTable jt = new JTable(data, columns) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                //The following code is used to highlight cells in the table with different colors as shown in the conditions 
                Component comp = super.prepareRenderer(renderer, row, col);
                Object value = getModel().getValueAt(row, col);
                if (col == 0) {
                    comp.setBackground(myWhite);
                } else if (!value.equals("" + 0) && col == Col_Num - 1) {
                    comp.setBackground(Color.green);
                } else {
                    comp.setBackground(Color.white);
                }

                return comp;
            }
        };
       // The three Lines below are used for used for cetring the values in the table 
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int x=0;x<Col_Num;x++){
         jt.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
       

        //Setting the font Style and the size of the font for the table header
        JTableHeader tableHeader = jt.getTableHeader();
        tableHeader.setFont(new Font("Cochin", Font.BOLD, 16));

        jt.setPreferredScrollableViewportSize(new Dimension(TableLength, TableWidth)); //passing the dimesions of the table 
        jt.setFont(new Font("Serif", Font.BOLD, 18)); //Font Style and size of the values inside the table 
        jt.setForeground(myGray); //Setting the color of the values inside table 
        jt.setColumnSelectionAllowed(true); // Allow the selction of table cells         
        JScrollPane scrollable = new JScrollPane(jt); // Allow the table to be scrollable 

        //A 'for' loop with nested 'for' loop to store the random numbers in the table
        //One loop for the rows and one for the columns
        for (int i = 0; i < Repetion; i++) {
            int Num_Occ = 0; // This variable is to store the number of occurences of a selected number in each row
            Dice Dice_Object = new Dice();
            data[i][0] = "#" + (i + 1); //Adds The row "ID" number for the first column of each row 

            for (int j = 1; j < Col_Num - 1; j++) {
                //This condition stores the values of the first row of the table in a seprate table to matche it later with dice faces
                if (i == 0)
                    First_Row[j - 1] = Dice_Object.get_num(j);

                data[i][j] = "" + Dice_Object.get_num(j); //Storing the random numbers in the "data" 2D array to be passed to the table
                if (Selected_Num == Dice_Object.get_num(j)) { // This condition will increment "Num_Occ" every time the selected number is found in a row 
                    Num_Occ++;
                }
                //This condition is to store the number of occurences of the selected number in the last column of each row
                if (j == Col_Num - 2)
                    data[i][Col_Num - 1] = "" + Num_Occ;
            }

        }
                 //Adding the table 
        add(scrollable, BorderLayout.CENTER);

    }


}