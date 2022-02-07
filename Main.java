import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Main {
	
   public static void main(String[] args) {
		//Start the program from the "main()" method
      SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Frame();

			}
		});


	}
}

