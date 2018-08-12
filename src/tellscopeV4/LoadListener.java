package tellscopeV4;

/* import libraries */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class LoadListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {					//use action performed method
		
		String s;													//create string to store filename
																	
		s = JOptionPane.showInputDialog("Enter a filename");		//prompt user for a filename and store in s
		
		TellScopeGui04.loadResults(s);								//call loadResults method to load results set

	}//end action performed

}//end LoadListener()
