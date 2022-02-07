import java.util.Random;
public class Dice{
             // Instantiating Random object to be used with int variables to generate random numbers from 1 to 6 
               Random rand = new Random();
               int d1= rand.nextInt(6)+1;
               int d2= rand.nextInt(6)+1;
               int d3= rand.nextInt(6)+1;
               int d4= rand.nextInt(6)+1;
               int d5= rand.nextInt(6)+1;
               int d6= rand.nextInt(6)+1;
               int d7= rand.nextInt(6)+1;
               int d8= rand.nextInt(6)+1;
               int d9= rand.nextInt(6)+1;
               int d10= rand.nextInt(6)+1;
               
               public Dice(){
               }
               // Method returns an intger of random numbers based on the passed paramter
               public int get_num(int i){
               int arr []={d1,d2,d3,d4,d5,d6,d7,d8,d9,d10};
     
               return arr[i-1];
       }
       }        