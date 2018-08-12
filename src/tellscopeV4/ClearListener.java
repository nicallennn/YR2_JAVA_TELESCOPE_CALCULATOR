package tellscopeV4;
/* import libraries */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {				//use action performed method
		
		for(int i=0;i<13;i++)									//loop to clear current results set
		{
			TellScopeGui04.calcResults[i] = "";					//set all elements of the results array to null
		}
		
		TellScopeGui04.setResults();											//call setResults method
		
	}

}
