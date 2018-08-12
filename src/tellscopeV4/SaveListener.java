package tellscopeV4;

/* import libraries */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class SaveListener implements ActionListener {

	
	public void actionPerformed(ActionEvent e) {									//use action performed method
		
		String s;																	//create string to store filename
		
		s = JOptionPane.showInputDialog("Enter a filename");						//create input dialog box to prompt user for filename
																					//and store input to string s
		/* validate filename */
		if(s.matches(".*[./#',].*"))													//if filename only contains valid characters or numbers
		{	
			JOptionPane.showMessageDialog(TellScopeGui04.txtFocalInput, 			//create a new message dialog box
					"Filename contains invalid characters!\n" 
					+ "Only characters and numeric values are permitted!",			//print message to user
					"Invalid Filename",												//set title of dialog box
					JOptionPane.ERROR_MESSAGE);										//set box type to error
		}
		else																		//else if filename contains invalid characters
		{
			TellScopeGui04.storeResults(s);											//call storeResults() method to save the results set
			
		}
			
		
	}//end action performed
	
}//end SaveListener()
