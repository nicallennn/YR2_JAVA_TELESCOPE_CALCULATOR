package tellscopeV4;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.Border;

public class TellServerGui04 extends JFrame {
	
	/*
	 * calcResults array - this array stores the results of the calculations in the following order:
	 * 
	 * index - description
	 * 0 - focal length
	 * 1 - tube diameter
	 * 2 - distance to secondary
	 * 3 - secondary size minor
	 * 4 - secondary size major
	 * 5 - minimum magnitude
	 * 6 - minimum resolution
	 * 7 - maximum visible magnification
	 * 8 - minimum visible magnification
	 * 9 - eyepiece magnification
	 * 10 - keyword "results" to tell client to sort and display results
	 * 
	 *  */
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****** RESULTS STORAGE ***************************************/
	protected static String[] calcResults = new String[11];				//array to store processed results
	
	protected static String resultTitles[] = 							//array to store results titles - test purpose only!!!!
		{	"Focal Length: ",
			"Tube Diamter: ",
			"Distance to Secondary: ",
			"Secondary Size Minor: ",
			"Secondary Size Major: ",
			"Minimum Magnitude: ",
			"Minimum Resolution: ",
			"Maximum Visible Magnification: ",
			"Minimum Visible Magnification: ",
			"Eyepiece Magnification: ",
			"Keyword For Clientside Processing: ",};
	
	/* class attributes and variables */
	protected static boolean completedResults = false;					//boolean value used to check if results calculations have been completed
	private static int port = 1234;										//default port number value
	
		
	private static ServerSocket ss;											//server socket to allow client to connect
	
	
	/**** LABELS **********************************************************************************************/
	
	/* input value labels */
	private static JLabel lblLensInput;									//label for lens diameter input
	private static JLabel lblFocalInput;								//label for focal ratio input
	private static JLabel lblEyeInput;									//label for eyepiece focal length
	
	/* result value labels */
	private static JLabel lblFocalLength;								//label for focal length result
	private static JLabel lblTubeDiameter;								//label for tube diameter result
	private static JLabel lblDistToSecond;								//label for distance to secondary result
	private static JLabel lblSecondSizeMaj;								//label for secondary size major result
	private static JLabel lblSecondSizeMin;								//label for secondary size minor result
	private static JLabel lblMinMagnitude;								//label for minimum magnitude result
	private static JLabel lblMinResolution;								//label for minimum resolution
	private static JLabel lblMaxVisMag;									//label for maximum visible magnification
	private static JLabel lblMinVisMag;									//label for minimum visible magnification
	private static JLabel lblEyePieceMag;								//label for eyepiece magnification 
	
	/**** END LABELS ******************************************************************************************/
	
	/**** TEXTFIELDS + TEXTAREAS ******************************************************************************/
	
	/* input value text fields */
	private static JTextField txtLensInput;									//textField for lens diameter input
	private static JTextField txtFocalInput;								//textField for focal ratio input
	private static JTextField txtEyeInput;									//textField for eyepiece focal length
	
	/* result value text fields */
	private static JTextField txtFocalLength;								//textField for focal length result
	private static JTextField txtTubeDiameter;								//textField for tube diameter result
	private static JTextField txtDistToSecond;								//textField for distance to secondary result
	private static JTextField txtSecondSizeMaj;								//textField for secondary size major result
	private static JTextField txtSecondSizeMin;								//textField for secondary size minor result
	private static JTextField txtMinMagnitude;								//textField for minimum magnitude result
	private static JTextField txtMinResolution;								//textField for minimum resolution
	private static JTextField txtMaxVisMag;									//textField for maximum visible magnification
	private static JTextField txtMinVisMag;									//textField for minimum visible magnification
	private static JTextField txtEyePieceMag;								//textField for eyepiece magnification 
	
	protected static JTextArea consoleView;									//textArea to display console messages
	
	/**** END TEXTFIELDS + TEXTAREAS ***************************************************************************/
	
	
	/**** BUTTONS **********************************************************************************************/
	
	//private static JButton btnClearButton;								//button to clear results set
	
	/**** END BUTTONS ******************************************************************************************/
	
	
	/* main method */
	public static void main(String args[])
	{
		new TellServerGui04();							//create new tell server gui
	}
	
	/* default TellServerGui04 constructor */
	TellServerGui04()
	{
		
		/* set up JFrame */
		this.setSize(500, 400);													//set default JFrame size
		this.setTitle("TellScope Server");											//set JFrame title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//set default close operation
		this.setLocationRelativeTo(null);											//center the frame on screen
		this.setVisible(true);
		
		/* create panels */
		
		//mainPanel
		JPanel mainPanel = new JPanel();											//initialise main panel		
			mainPanel.setLayout(new GridBagLayout());								//set layout for main panel
			
		//inputsPanel
		JPanel inputsPanel = new JPanel();											//initialise inputs panel
			inputsPanel.setLayout(new GridBagLayout());								//set layout for inputs panel
			
		//consolePanel
		JPanel consolePanel = new JPanel();											//initialise console panel
			consolePanel.setLayout(new GridBagLayout());							//set layouts for console panel
			
		//resultsPanel
		JPanel resultsPanel = new JPanel();											//initialise results panel
			resultsPanel.setLayout(new GridBagLayout());							//set layouts for results panel
			
		/* create borders for panels */
		
		Border inputsBorder = BorderFactory.createTitledBorder("Inputs");			//create border for inputs panel
			inputsPanel.setBorder(inputsBorder);									//add the border to the inputs panel
			
		Border consoleBorder = BorderFactory.createTitledBorder("Console");			//create border for console panel
			consolePanel.setBorder(consoleBorder); 									//add the border to the console panel
		
		Border resultsBorder = BorderFactory.createTitledBorder("Results");			//create border for results panel
			resultsPanel.setBorder(resultsBorder);									//add the border to the results panel
		
		
		/***** GRIDBAG LAYOUT CONSTRAINTS ***********************************************/
			
		/* create new grid bag constraints object to help set components in place */
		GridBagConstraints gc = new GridBagConstraints();	//create new grid constraints object
		gc.gridx = 0;										//set default grid x
		gc.gridy = 0;										//set default grid y
		gc.gridwidth = 1;									//set default grid width
		gc.gridheight = 1;									//set default grid height
		gc.weightx = 100.0;									//set default row width
		gc.weighty = 100.0;									//set default row height
		gc.insets = new Insets(5,25,15,25);					//set default padding
		gc.anchor = GridBagConstraints.WEST;				//set default alignment if component does not fill space
		gc.fill = GridBagConstraints.NONE;					//set default fill value (?fill available space)
			
			
		/********************************************************************************/		
		
		/**** INPUT PANEL SECTION *******************************************************/	
		
		/* initialise input labels */
		lblLensInput = new JLabel("Lens Diameter");						//initialise JLabel with title"Lens diameter"
		lblFocalInput = new JLabel("Focal Ratio");						//initialise JLabel with title"Lens diameter"
		lblEyeInput = new JLabel("Eyepiece Focal Length");				//initialise JLabel with title"Lens diameter"
		
		/* initialise input text fields */
		txtLensInput = new JTextField(10);								//create new text field, set number of columns
			txtLensInput.setEditable(false);							//make textField un-editable
		txtFocalInput = new JTextField(10);								//create new text field, set number of columns			
			txtFocalInput.setEditable(false);							//make textField un-editable
		txtEyeInput = new JTextField(10);								//create new text field, set number of columns
			txtEyeInput.setEditable(false);								//make textField un-editable
		
		gc.insets = new Insets(5,10,15,10);		
		/* add the components to the panel */
		//add labels
		inputsPanel.add(lblLensInput, gc);
		gc.gridy = 1;
		inputsPanel.add(lblFocalInput, gc);
		gc.gridy = 2;
		inputsPanel.add(lblEyeInput, gc);
		
		//add text fields
		gc.gridx = 1;
		gc.gridy = 0;
		inputsPanel.add(txtLensInput, gc);
		gc.gridy = 1;
		inputsPanel.add(txtFocalInput, gc);
		gc.gridy = 2;
		inputsPanel.add(txtEyeInput, gc);
		
		
		/**** END INPUT PANEL SECTION ***************************************************/	
		
		
		/**** OUTPUT PANEL SECTION ******************************************************/	
		
		/* initialise results labels */
		lblFocalLength = new JLabel("Focal Length");								//label for focal length result
		lblTubeDiameter = new JLabel("Tube Diameter");								//label for tube diameter result
		lblDistToSecond = new JLabel("Distance to Second");							//label for distance to secondary result
		lblSecondSizeMaj = new JLabel("Second Size Major");							//label for secondary size major result
		lblSecondSizeMin = new JLabel("Second Size Minor");							//label for secondary size minor result
		lblMinMagnitude = new JLabel("Min Magnitude");								//label for minimum magnitude result
		lblMinResolution = new JLabel("Min Resolution");							//label for minimum resolution
		lblMaxVisMag = new JLabel("Max Visible Magnification");						//label for maximum visible magnification
		lblMinVisMag = new JLabel("Min Visible Magnification");						//label for minimum visible magnification
		lblEyePieceMag = new JLabel("Eyepiece Magnification");						//label for eyepiece magnification 
	
		/* initialise results text fields */
		txtFocalLength = new JTextField(10);										//text field for focal length result
			txtFocalLength.setEditable(false);										//make textField un-editable
		txtTubeDiameter = new JTextField(10);										//text field tube diameter result
			txtTubeDiameter.setEditable(false);										//make textField un-editable
		txtDistToSecond = new JTextField(10);										//text field distance to secondary result
			txtDistToSecond.setEditable(false);										//make textField un-editable	
		txtSecondSizeMaj = new JTextField(10);										//text field secondary size major result
			txtSecondSizeMaj.setEditable(false);									//make textField un-editable
		txtSecondSizeMin = new JTextField(10);										//text field secondary size minor result
			txtSecondSizeMin.setEditable(false);									//make textField un-editable
		txtMinMagnitude = new JTextField(10);										//text field minimum magnitude result
			txtMinMagnitude.setEditable(false);										//make textField un-editable
		txtMinResolution = new JTextField(10);										//text field minimum resolution
			txtMinResolution.setEditable(false);									//make textField un-editable
		txtMaxVisMag = new JTextField(10);											//text field maximum visible magnification
			txtMaxVisMag.setEditable(false);										//make textField un-editable
		txtMinVisMag = new JTextField(10);											//text field minimum visible magnification
			txtMinVisMag.setEditable(false);										//make textField un-editable
		txtEyePieceMag = new JTextField(10);										//text field eyepiece magnification 
			txtEyePieceMag.setEditable(false);										//make textField un-editable
		
			
		/* add components to results panel */
		//reset layout constraints
		gc.insets = new Insets(5,10,15,10);		
		gc.gridx = 0;
		gc.gridy = 0;
		
		//add first 5 labels
		resultsPanel.add(lblFocalLength, gc);
		gc.gridy = 1;
		resultsPanel.add(lblTubeDiameter, gc);
		gc.gridy = 2;
		resultsPanel.add(lblDistToSecond, gc);
		gc.gridy = 3;
		resultsPanel.add(lblSecondSizeMin, gc);
		gc.gridy = 4;
		resultsPanel.add(lblSecondSizeMaj, gc);
		
		//add first five text fields
		gc.gridx = 1;
		gc.gridy = 0;
		resultsPanel.add(txtFocalLength, gc);
		gc.gridy = 1;
		resultsPanel.add(txtTubeDiameter, gc);
		gc.gridy = 2;
		resultsPanel.add(txtDistToSecond, gc);
		gc.gridy = 3;
		resultsPanel.add(txtSecondSizeMin, gc);
		gc.gridy = 4;
		resultsPanel.add(txtSecondSizeMaj, gc);
		
		//add final 5 labels
		gc.gridx = 2;
		gc.gridy = 0;
		resultsPanel.add(lblMinMagnitude, gc);
		gc.gridy = 1;
		resultsPanel.add(lblMinResolution, gc);
		gc.gridy = 2;
		resultsPanel.add(lblMaxVisMag, gc);
		gc.gridy = 3;
		resultsPanel.add(lblMinVisMag, gc);
		gc.gridy = 4;
		resultsPanel.add(lblEyePieceMag, gc);
		
		//add final 5 text fields
		gc.gridx = 3;
		gc.gridy = 0;
		resultsPanel.add(txtMinMagnitude, gc);
		gc.gridy = 1;
		resultsPanel.add(txtMinResolution, gc);
		gc.gridy = 2;
		resultsPanel.add(txtMaxVisMag, gc);
		gc.gridy = 3;
		resultsPanel.add(txtMinVisMag, gc);
		gc.gridy = 4;
		resultsPanel.add(txtEyePieceMag, gc);
		
		/**** END OUTPUT PANEL SECTION **************************************************/	
		
		/**** CONSOLE PANEL SECTION **************************************************/	
		
		consolePanel.setLayout(new BorderLayout());
		consoleView = new JTextArea();
			consoleView.setRows(7);
			consoleView.setColumns(22);
			consoleView.setEditable(false);
			
		JScrollPane scroll = new JScrollPane(consoleView);
		consolePanel.add(scroll, BorderLayout.CENTER);
		
		
		/**** END CONSOLE PANEL SECTION **************************************************/	
	
		
		/**** ADD SUB PANELS TO MAIN PANEL **********************************************/
		gc.insets = new Insets(5,10,10,10);	
		gc.gridx = 0;
		gc.gridy = 0;
		
		mainPanel.add(inputsPanel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 2;
		
		mainPanel.add(resultsPanel, gc);
		
		//add console window panel
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 1;
		mainPanel.add(consolePanel, gc);
		
		this.add(mainPanel);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
		/**** END ADD SUB PANELS TO MAIN PANEL **********************************************/
		
	/* call startServer() method to start the server running */
	startServer();
		
		
	}//end TellServer04 constructor
	
	
	/***** PUBLIC METHODS *******************************************************************/
	
	//test method to print results array
	public static void printResults()
	{
		for(int i=0;i<11;i++)
		{
			System.out.println(resultTitles[i] + calcResults[i]);
		}
		
	}
	
	/****************************/
	
	/***** END PUBLIC METHODS *******************************************************************/
	
	
	
	/***** PROTECTED METHODS *******************************************************************/
	
	//sendResultsToClient method - this method starts a thread to send the results to the client
	
		protected static void sendResultsToClient()
		{
			//create thread
			
			completedResults = true;
			
		}
		
		/****************************/
		
		protected static void dontSendResultsToClient()
		{
			//create thread
			
			completedResults = false;
			
		}
		
		/****************************/
		
		protected static void setInputs(String lensDiam, String focalRatio, String eyeMag)
		{
			
			txtLensInput.setText(lensDiam);
			
			txtFocalInput.setText(focalRatio);
			
			txtEyeInput.setText(eyeMag);
			
		}
		
		
		protected static void setResults()
		{
			/* set results values */
			txtFocalLength.setText(calcResults[0]);			//set focal length result
			txtTubeDiameter.setText(calcResults[1]); 		//set tube diam result
			txtDistToSecond.setText(calcResults[2]); 		//set dist to second result
			txtSecondSizeMin.setText(calcResults[3]); 		//set second size min result
			txtSecondSizeMaj.setText(calcResults[4]); 		//set second size maj result
			txtMinMagnitude.setText(calcResults[5]); 		//set min magnitude result
			txtMinResolution.setText(calcResults[6]); 		//set min res result
			txtMaxVisMag.setText(calcResults[7]); 			//set max vis mag result
			txtMinVisMag.setText(calcResults[8]); 			//set min vis mag result
			txtEyePieceMag.setText(calcResults[9]); 		//set eyepiece mag result
		}
		
		
		
		/***** END PROTECTED METHODS *******************************************************************/
		
		
		/**** PRIVATE METHODS ********************************************************************/
		
		
		private static void startServer()
		{
			/* start server and wait for connection to be made */
			try																			//try catch block to catch network connection errors
			{
				System.out.println("Tells Server");										//print message to console
				System.out.println("Listening on port: " + port);						//print port number to console
				consoleView.append("TellScope Server");
				consoleView.append("\nListening on port: " + port);
				ss = new ServerSocket(port);											//initialise server socket
				
				while(true)																//while loop to keep server running
				{
					Socket s = ss.accept();												//create socket to client when new client connects
					System.out.println("Connection established!");						//print message to console when new client connects
					
					/* create a new thread to wait for input, perform calculations and start sendResults thread */
					
					Thread calculateThread = new Thread(new TellCalcThread(s));			//instantiate new telescope calculation thread
					calculateThread.start();											//start new telescope calculation thread
					
				}//end while loop
				
			}
			catch(Exception e)															//catch any exceptions
			{
				System.out.println("System exception!");								//print error message
				
			}
			
		}//end startSever() method
			
		
		/**** END PRIVATE METHODS ********************************************************************/
	
}//end TellServer04 class
