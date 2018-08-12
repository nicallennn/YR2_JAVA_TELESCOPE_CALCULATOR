/* 
 * Class Name: TellScopeGui04
 * Date: 14 March 2017
 * Version: 1
 * 
 * Description: Final GUI design for Shutter Island FdEng 16/17 Coursework
 * 
 * Notes:
 * 
	 * Results array - this array stores the results of the calculations in the following order:
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
 * 
 * */


/* Define Package */
package tellscopeV4;

/* import required library's */
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.border.*;


/* define class and inherit from JFrame  */
public class TellScopeGui04 extends JFrame {
	
	/*** RESULTS STORAGE *****************************************************/
	//array list to store calculation results
	protected static ArrayList<String> results = new ArrayList<String>();
	//array to copy results to before clearing array list
	protected static String[] calcResults = new String[13];
	
	/*** END RESULTS STORAGE *****************************************************/
	
	
	/**** CONSTANT for network connection ************************/
	//private static final int PORT = 1234;		//constant for port number
	
	
	/* Strings to store user input */
	//private static String lensDiamter;				
	//private static String focalRatio; 
	//private static String eyeFocalLength;
	
	
	
	
	
	private static final long serialVersionUID = 1L;

	
	
	/* main method */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* call default constructor */
		new TellScopeGui04();
	
		
	}//end main

	/*** JLABELS *****************************************************/
	/* Create JLabel attributes for INPUTS */
	private static JLabel lblLensDiameter;		//label to display title Lens Diameter
	private static JLabel lblFocalRatio;		//label to display title Focal Ratio
	private static JLabel lblEyeFocalLength;	//label to display title Eyepiece Focal Length
	
	
	/* Create JLabel attributes for RESULTS */
	private static JLabel lblLensInput;			//label to display title Submitted Lens Diameter Input
	private static JLabel lblFocalInput;		//label to display title Submitted Focal Ratio Input
	private static JLabel lblEyeFocalInput;		//label to display title Submitted Eyepiece focal length
	
	private static JLabel lblFocalLength;		//label to display title Focal Length
	private static JLabel lblTubeDiameter;		//label to display title Tube Diameter
	private static JLabel lblDistToSecond;		//label to display title Distance to Second
	private static JLabel lblSecondSizeMinor;	//label to display title Secondary Size Minor
	private static JLabel lblSecondSizeMajor;	//Label to display title Secondary Size Major
	private static JLabel lblMinMagnitude;		//label to display title Minimum Magnitude
	private static JLabel lblMinResolution;		//label to display title Minimum Resolution
	private static JLabel lblMaxVisibleMag;		//label to display title Maximum Visible Magnification
	private static JLabel lblMinVisibleMag;		//label to display title Minimum Visible Magnification
	private static JLabel lblEyePieceMag;		//label to display title Eyepiece Magnification
	
	/*** END JLABELS ***************************************************/
	
	/*** JTEXTFIELDS ***************************************************/
	/* Create JTextField attributes for INPUTS */
	protected static JTextField txtLensDiameter;		//text field to take lens diameter input
	protected static JTextField txtFocalRatio;		//text field to take focal ratio input
	protected static JTextField txtEyeFocalLength;	//text field to take eyepiece magnification input
	
	/* Create JTextField attributes for RESULTS */
	
	private static JTextField txtLensInput;			//text field to display Lens Diameter Input
	protected static JTextField txtFocalInput;		//text field to display Focal Ratio Input
	private static JTextField txtEyeFocalInput;		//text field to display Eyepiece Magnification Input
	
	private static JTextField txtFocalLength;		//text field to display Focal Length
	private static JTextField txtTubeDiameter;		//text field to display Tube Diameter
	private static JTextField txtDistToSecond;		//text field to display Distance to Second
	private static JTextField txtSecondSizeMinor;	//text field to display Secondary Size Minor
	private static JTextField txtSecondSizeMajor;	//text field to display Secondary Size Major
	private static JTextField txtMinMagnitude;		//text field to display Minimum Magnitude
	private static JTextField txtMinResolution;		//text field to display Minimum Resolution
	private static JTextField txtMaxVisibleMag;		//text field to display Maximum Visible Magnification
	private static JTextField txtMinVisibleMag;		//text field to display Minimum Visible Magnification
	private static JTextField txtEyePieceMag;		//text field to display Eyepiece Magnification
	

	/*** END JTEXTFIELDS ************************************************/
	
	
	/*** BUTTONS ************************************************/
	
	/* JButton */
	private static JButton btnSubmit;				//button to submit users inputs to server
	private static JButton btnSave;					//button to save the results of the calculations
	private static JButton btnLoad;					//button to load previously calculated results
	private static JButton btnClear;				//button to clear the results set
	
	/* JRadioButton */
	
	protected static JRadioButton reflect, refract;	//radio buttons to select between reflecting or refracting telescope
	
	/*** END BUTTONS ************************************************/
	
	
	
	/***********************************************************************************************************************************************/
	/***********************************************************************************************************************************************/
	/***********************************************************************************************************************************************/
	
	/* TellScopeGUI4 Constructor */
	
	public TellScopeGui04()
	{
		
		
		/* set up JFrame */
		this.setSize(1100,600);												//set default JFrame size
		this.setTitle("Tell Scope");										//set JFrame title
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//set default close operation
		this.setLocationRelativeTo(null);									//center the frame on screen
		
		
		/* create JPanels */
		JPanel mainPanel = new JPanel();									//create main panel
			mainPanel.setLayout(new GridBagLayout());						//set layout for main panel
			//mainPanel.setBackground(Color.BLACK);
			
		JPanel inputPanel = new JPanel();									//create panel for inputs
			inputPanel.setLayout(new GridBagLayout());						//set layout for input panel
			//inputPanel.setForeground(Color.WHITE);
			
		JPanel resultsPanel = new JPanel();									//create panel for outputs
			resultsPanel.setLayout(new GridBagLayout());					//set layout for results panel
			//resultsPanel.setBackground(Color.WHITE);
			
		JPanel saveLoadPanel = new JPanel();								//create panel for save and load buttons
			saveLoadPanel.setLayout(new GridBagLayout());					//set layout for saveLoad panel			
		
			/* create borders for panels */
		Border inputBorder = BorderFactory.createTitledBorder("Inputs");	//create border for inputs panel
			inputPanel.setBorder(inputBorder);								//add the border to the panel
			
		Border resultsBorder = BorderFactory.createTitledBorder("Results");	//create border for results panel
			resultsPanel.setBorder(resultsBorder);							//add border to results panel
		
		/**** INPUT PANEL SECTION **************************************************************************/	
			
		/* create input labels */
		lblLensDiameter = new JLabel("Lens Diameter");					//create new label with title Lens Diameter
		lblFocalRatio = new JLabel("Focal Ratio");						//create new label with title Focal Ratio
		lblEyeFocalLength = new JLabel("Eyepiece Focal Length");		//create new label with title Eyepiece Focal Length
		
		/* create input text fields */
		txtLensDiameter = new JTextField(10);							//create new text field, set number of columns
		txtFocalRatio = new JTextField(10);								//create new text field, set number of columns			
		txtEyeFocalLength = new JTextField(10);							//create new text field, set number of columns
		
		/* create radio buttons */
		
		refract = new JRadioButton("Refracting");						//create new radio button with title Refracting
		reflect = new JRadioButton("Reflecting");						//create new radio button with title Reflecting
			reflect.setSelected(true);
		
		
		ButtonGroup teleType = new ButtonGroup();						//create new radio button group called teleType
			teleType.add(reflect);										//add the reflect radio button to the group
			teleType.add(refract);										//add the refract radio button to the group
		
		
		/* create submit button */
			
		btnSubmit = new JButton("Submit");								//create submit button
			btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 9));		//set font and font size of button
			
		SubmitListener submitListen = new SubmitListener();	
			btnSubmit.addActionListener(submitListen);
			
		
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
		gc.anchor = GridBagConstraints.CENTER;				//set default alignment if component does not fill space
		gc.fill = GridBagConstraints.NONE;					//set default fill value (?fill available space)
		
		
		/********************************************************************************/
		
		/* add components to the input panel */
		//labels
		inputPanel.add(lblLensDiameter, gc);				//add lens diameter label
		gc.gridx = 1;										//set grid x position for focal label
		inputPanel.add(lblFocalRatio, gc);					//add focal ratio label
		gc.gridx = 2;										//set grid x position for eye mag label
		inputPanel.add(lblEyeFocalLength, gc);				//add eye mag label
		
		//text fields				
		gc.gridx = 0;										//set grid x position for lens diam text field
		gc.gridy = 1;										//set grid y position for lens diam text field
		inputPanel.add(txtLensDiameter, gc);				//add lens diam text field to the panel
		gc.gridx = 1;										//set grid x position for focal ratio text field
		gc.gridy = 1;										//set grid y position for focal ratio text field
		inputPanel.add(txtFocalRatio, gc);					//add focal ratio text field to the panel	
		gc.gridx = 2;										//set grid x position for eye mag text field
		gc.gridy = 1;										//set grid y position for eye mag text field
		inputPanel.add(txtEyeFocalLength, gc);				//add eye mag text field to the panel
		
		//radio button group
		gc.gridx = 3;
		gc.gridy = 0;
		inputPanel.add(reflect, gc);
		
		gc.gridx = 3;
		gc.gridy = 1;
		inputPanel.add(refract, gc);
		
		//buttons	
		gc.gridx = 3;										//set grid x position for submit button
		gc.gridy = 2;										//set grid y position for submit button
		inputPanel.add(btnSubmit, gc);						//add the button to the input panel
		
		
		//reset the grid x and y values for grid bag constraints "gc"
		gc.gridx = 0;
		gc.gridy = 0;
		
		
		/**** END INPUT PANEL SECTION ****************************************************************************/
		
		/**** RESULTS PANEL SECTION **************************************************************************/	
		
		/* create results labels */
		
		//labels for submitted user inputs
		lblLensInput = new JLabel("Lens Diameter");						//create new label with title lens diameter
		lblFocalInput = new JLabel("Focal Ratio");						//create new label with title focal ratio
		lblEyeFocalInput = new JLabel("Eyepiece Focal Length");			//create new label with title eyepiece mag
		
		//calculation results labels
		lblFocalLength = new JLabel("Focal Length");					//create new label with title focal length
		lblTubeDiameter = new JLabel("Tube Diameter");					//create new label with title tube diam
		lblDistToSecond = new JLabel("Distance to Secondary");			//create new label with title dist to second
		lblSecondSizeMinor = new JLabel("Secondary Size Min");			//create new label with title second size minor
		lblSecondSizeMajor = new JLabel("Secondary Size Maj");			//create new label with title second size major
		lblMinMagnitude = new JLabel("Minimum Magnitude");				//create new label with title minimum mag
		lblMinResolution = new JLabel("Minimum Resolution");			//create new label with title min resolution
		lblMaxVisibleMag = new JLabel("Max Visible Magnification");		//create new label with title max visible mag
		lblMinVisibleMag = new JLabel("Min Visiable Magnification");	//create new label with title  min visible mag
		lblEyePieceMag = new JLabel("Eyepiece Magnification");			//create new label with title eyepiece magnification
		
		
		/* create results text fields */
		//text fields to display submitted user inputs
		txtLensInput = new JTextField(10);								//create new text field for submitted lens input
			txtLensInput.setEditable(false);							//make text field un-editable
		txtFocalInput = new JTextField(10);								//create new text field for submitted focal input
			txtFocalInput.setEditable(false);							//make text field un-editable
		txtEyeFocalInput = new JTextField(10);							//create new text field for submitted eyepiece mag
			txtEyeFocalInput.setEditable(false);						//make text field un-editable
		
		//calculation results text fields, not editable!!!
		txtFocalLength = new JTextField(10);							//create new text field for focal length
			txtFocalLength.setEditable(false);							//make text field un-editable
		txtTubeDiameter = new JTextField(10);							//create new text field for tube diameter
			txtTubeDiameter.setEditable(false);							//make text field un-editable
		txtDistToSecond = new JTextField(10);							//create new text field for dist to second
			txtDistToSecond.setEditable(false);							//make text field un-editable
		txtSecondSizeMinor = new JTextField(10);						//create new text field for second size minor
			txtSecondSizeMinor.setEditable(false);						//make text field un-editable
		txtSecondSizeMajor = new JTextField(10);						//create new text field for	second size major
			txtSecondSizeMajor.setEditable(false);						//make text field un-editable
		txtMinMagnitude = new JTextField(10);							//create new text field for min magnitude
			txtMinMagnitude.setEditable(false);							//make text field un-editable
		txtMinResolution = new JTextField(10);							//create new text field for	min resolution
			txtMinResolution.setEditable(false);						//make text field un-editable
		txtMaxVisibleMag = new JTextField(10);							//create new text field for max visible magnification
			txtMaxVisibleMag.setEditable(false);						//make text field un-editable
		txtMinVisibleMag = new JTextField(10);							//create new text field for min visible magnification
			txtMinVisibleMag.setEditable(false);						//make text field un-editable
		txtEyePieceMag = new JTextField(10);							//create new text field for eyepiece 
			txtEyePieceMag.setEditable(false);							//make text field un-editable
		
		/******** SAVE, LOAD AND CLEAR BUTTONS ***************/
		
		/* create save button */
		btnSave = new JButton("Save");									//create save button
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 9));			//set font and font size of button
		
		/* create load button */
		btnLoad = new JButton("Load");									//create load button
			btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 9));			//set font and size of button
			
		/* create clear button */
		btnClear = new JButton("Clear");								//create clear button to clear the results set
			btnClear.setFont(new Font("Tahoma", Font.PLAIN, 9));		//set font and size of button
		
		/******** SAVE, LOAD AND CLEAR ACTION LISTENERS ***************/
			
		/* add action listener to save button */	
		SaveListener sl = new SaveListener();							//create new SaveListener object
			btnSave.addActionListener(sl);								//add SaveListener object to the save button
			
		/* add action listener to load button */	
		LoadListener ll = new LoadListener();							//create new LoadListener object
			btnLoad.addActionListener(ll);								//add LoadListener to the load button
			
		/* add action listener to clear button */
		ClearListener cl = new ClearListener();							//create new ClearListener object
			btnClear.addActionListener(cl);								//add ClearListener to clear button
			
		/******** END SAVE, LOAD AND CLEAR ACTION LISTENERS ***************/
			
		/********************************************************************************/
			
		/* add components to the output panel */
		
		//labels
		//submitted inputs labels
		gc.gridx = 1;													//set grid x position
		resultsPanel.add(lblLensInput, gc);								//add submitted lens diameter label
		gc.gridx = 2;													//set grid x position
		resultsPanel.add(lblFocalInput, gc);							//add submitted focal length input
		gc.gridx = 3;													//set grid x position
		resultsPanel.add(lblEyeFocalInput, gc);							//add submitted eyepiece focal length
		
		//calculation results labels
		//first row (y = 2 for layout)
		gc.gridx = 0;													//set grid x position
		gc.gridy = 2;													//set grid y position
		resultsPanel.add(lblFocalLength, gc);							//add focal length label
		gc.gridx = 1;													//set grid x position
		gc.gridy = 2;													//set grid y position
		resultsPanel.add(lblTubeDiameter, gc);							//add tube diameter label
		gc.gridx = 2;													//set grid x position
		gc.gridy = 2;													//set grid y position
		resultsPanel.add(lblDistToSecond, gc);							//add dist to second label
		gc.gridx = 3;													//set grid x position
		gc.gridy = 2;													//set grid y position
		resultsPanel.add(lblSecondSizeMinor, gc);						//add second size min label
		gc.gridx = 4;													//set grid x position
		gc.gridy = 2;													//set grid y position
		resultsPanel.add(lblSecondSizeMajor, gc);						//add second size maj label
		
		//second row (y = 4 for layout)
		gc.gridx = 0;													//set grid x position	
		gc.gridy = 4;													//set grid y position
		resultsPanel.add(lblMinMagnitude, gc);							//add minimum magnitude label
		gc.gridx = 1;													//set grid x position
		gc.gridy = 4;													//set grid y position
		resultsPanel.add(lblMinResolution, gc);							//add minimum resolution label
		gc.gridx = 2;													//set grid x position
		gc.gridy = 4;													//set grid y position
		resultsPanel.add(lblMaxVisibleMag, gc);							//add max visible magnification label
		gc.gridx = 3;													//set grid x position
		gc.gridy = 4;													//set grid y position
		resultsPanel.add(lblMinVisibleMag, gc);							//add min visible magnification label
		gc.gridx = 4;													//set grid x position
		gc.gridy = 4;													//set grid y position
		resultsPanel.add(lblEyePieceMag, gc);							//add eyepiece magnification label
		
		
		//text fields		
		//submitted inputs text fields
		gc.gridx = 1;													//set grid x position
		gc.gridy = 1;													//set grid y position 

		resultsPanel.add(txtLensInput, gc);								//add lens input text field to results panel
		gc.gridx = 2;													//set grid x position
		gc.gridy = 1;													//set grid y position
		resultsPanel.add(txtFocalInput, gc);							//add focal ratio text field to results panel
		gc.gridx = 3;													//set grid x position
		gc.gridy = 1;													//set grid y position
		resultsPanel.add(txtEyeFocalInput, gc);							//add eye focal length text field to results panel
		
		//calculation results text fields
		// row 1 (y = 3)
		gc.gridx = 0;													//set grid x position
		gc.gridy = 3;													//set grid y position
		resultsPanel.add(txtFocalLength, gc);							//add focal length text field to results panel
		gc.gridx = 1;													//set grid x position
		gc.gridy = 3;													//set grid y position
		resultsPanel.add(txtTubeDiameter, gc);							//add tube diameter text field to results panel
		gc.gridx = 2;													//set grid x position
		gc.gridy = 3;													//set grid y position
		resultsPanel.add(txtDistToSecond, gc);							//add dist to second text field to results panel
		gc.gridx = 3;													//set grid x position
		gc.gridy = 3;													//set grid y position
		resultsPanel.add(txtSecondSizeMinor, gc);						//add secondary size minor text field to results panel
		gc.gridx = 4;													//set grid x position
		gc.gridy = 3;													//set grid y position
		resultsPanel.add(txtSecondSizeMajor, gc);						//add secondary size major text field to results panel
		
		
		
		//row 2 (y = 5)
		gc.gridx = 0;													//set grid x position
		gc.gridy = 5;													//set grid y position
		resultsPanel.add(txtMinMagnitude, gc);							//add minimum magnitude text field to results panel
		gc.gridx = 1;													//set grid x position
		gc.gridy = 5;													//set grid y position
		resultsPanel.add(txtMinResolution, gc);							//add min resolution text field to results panel
		gc.gridx = 2;													//set grid x position
		gc.gridy = 5;													//set grid y position
		resultsPanel.add(txtMaxVisibleMag, gc);							//add max visible mag text field to results panel
		gc.gridx = 3;													//set grid x position
		gc.gridy = 5;													//set grid y position
		resultsPanel.add(txtMinVisibleMag, gc);							//add min visible mag text field to results panel
		gc.gridx = 4;													//set grid x position
		gc.gridy = 5;													//set grid y position
		resultsPanel.add(txtEyePieceMag, gc);							//add eyepiece mag text field to results panel
		
		
		//buttons
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(5,5,5,5);
		saveLoadPanel.add(btnSave, gc);
		gc.gridx = 1;
		saveLoadPanel.add(btnLoad, gc);
		
		gc.insets = new Insets(5,5,5,5);
		gc.gridx = 0;													//set grid x position for save button
		gc.gridy = 6;													//set grid y position for save button
		resultsPanel.add(saveLoadPanel, gc);							//add the button to the results panel
		
			
		gc.gridx = 4;
		resultsPanel.add(btnClear, gc);
		
		/**** END RESULTS PANEL SECTION **************************************************************************/	
		
		/**** ADD SUB PANELS TO MAIN PANEL ***********************************************************************/	
			
		/* add sub panels to main panel */
		gc.gridx = 0;													//set grid x position for input panel
		gc.gridy = 0;													//set grid y position for input panel
		
		//add input panel
		mainPanel.add(inputPanel, gc);									//add input panel to main panel
		
		
		//add results panel
		gc.gridx = 0;													//set grid x position for results panel
		gc.gridy = 1;													//set grid y position for results panel
		mainPanel.add(resultsPanel, gc);								//add results panel to main panel
		
		/**** END ADD SUB PANELS TO MAIN PANEL ***********************************************************************/	
		
		
		/**** ADD PANEL TO FRAME AND MAKE FRAME VISIBLE **************************************************************/	
		
		/* add the main panel to this JFrame */
		this.add(mainPanel);											//add main panel to the frame
		//this.pack();
		/* set JFrame visibility to visible */
		this.setVisible(true);											//set the frame visibility to true
		
		/**** END ADD PANEL TO FRAME AND MAKE FRAME VISIBLE **************************************************************/	
		
	}//end TellcopeGui04 constructor
	
	
	
	/***************** PUBLIC METHODS **************************************************/
	
	/*
	 * Method name: printResults()
	 * Description: test method that prints the results to the console
	 * Return: void
	 * 
	 *  */
	public static void printResults()
	{
				
		for (String s : results)
		{
			System.out.println(s);
					
		}
				
				
	}
	
	/*
	 * Method name: checkInputForNull()
	 * Description: checks if any of the user input boxes are null
	 * Return: boolean ---> true if fields not null, false if any fields are null
	 * 
	 *  */
	public static boolean checkInputForNull()
	{
				
		if(txtLensDiameter.getText().equals("") | txtFocalRatio.getText().equals("") 
				| txtEyeFocalLength.getText().equals(""))			//check if any of the text fields are empty
		{
			return false;											//return false if there are empty fields
		}
				
		else
		{
			return true;											//return true if all fields are not null
		}
	}
		
	/************************************/
	
	/*
	 * Method name: checkInputForChars()
	 * Description: checks if any of the user input boxes contain any values other than numeric values
	 * Return: boolean ---> true if fields only contain 1-9, false if any fields contain chars or symbols
	 * 
	 *  */
	
	public static boolean checkInputForChars()
	{
		/* check if any of the input boxes contain anything other than numeric values  */
		if(txtLensDiameter.getText().matches(".*[1-9].*") | txtFocalRatio.getText().matches(".*[1-9].*") 
				| txtEyeFocalLength.getText().matches(".*[1-9].*"))				
		{
			return true;											//return true if only numeric values
		}
		else
		{
			/* clear user input fields */
			txtFocalRatio.setText(null);							//clear user focal ratio input
			txtLensDiameter.setText(null);							//clear user lens diam input
			txtEyeFocalLength.setText(null);						//clear user eye focal length input
			
			return false;											//return false if contains chars or symbols
		}
			
	}
			
			
	/*****************  END PUBLIC METHODS **************************************************/
	
	
	
	
	/***************** PROTECTED METHODS **************************************************/
	
	/*
	 * Method name: getSocket()
	 * Description: takes the port number as an argument, creates a socket based on the host 
	 * 				and port number
	 * Return: socket
	 * 
	 *  */	
	protected static Socket getSocket(int port)
	{
		Socket s;										//create new socket attribute
		String host = "localhost";						//create new string "host" and set value to "localhost"
		InetAddress ip;									//create new inetAddress object
		
		
		Scanner sc = new Scanner(System.in);			//create new scanner object 
		while(true)										//start new while true loop
		{
			
		
			try											//try catch block for getting new socket
			{	
				ip = InetAddress.getByName(host);		//use getByName() method to get ip address of localhost
				s = new Socket(ip, port);				//create socket object based on ip and port number
				return s;								//return the socket	
				
			}
			catch(IOException e)																	//catch exception if there is a network error
			{
				System.out.println("Network Error");												//print to console message "Network Error"
				JOptionPane.showMessageDialog(txtFocalInput, "No network connection, make sure you"	//open dialog box and display error message to user
						+ " have started the server!");
				
				break;
			}
			
			
		}
		sc.close();
		return null;
	}//end getSocket()
	
	/************************************/
	
	/*
	 * Method name: copyResults()
	 * Description: protected method to copy results from array list to array, 
	 * 				as array list is cleared ready for next operation
	 * Return: void
	 * 
	 *  */
	protected static void copyResults()					
	{
		int j = 3;										//create dummy int for counter
		
		for (String s : results)						//for each string....loop
		{
			calcResults[j] = s;							//use j as index to copy value
			j++;										//increment j
			
		}
	}//end copyResults()
	
	/************************************/
	
	/*
	 * Method name: setResults()
	 * Description: protected method to print results to the appropriate text field
	 * Return: void
	 * 
	 *  */
	protected static void setResults()
	{
		/* set user input values */
		txtLensInput.setText(calcResults[0]);			//set user lens diam input to results area
		txtFocalInput.setText(calcResults[1]);			//set user focal ratio input to results area
		txtEyeFocalInput.setText(calcResults[2]);		//set user eye focal input to results area
		
		/* set results values */
		txtFocalLength.setText(calcResults[3]);			//set focal length result
		txtTubeDiameter.setText(calcResults[4]); 		//set tube diam result
		txtDistToSecond.setText(calcResults[5]); 		//set dist to second result
		txtSecondSizeMinor.setText(calcResults[6]); 	//set second size min result
		txtSecondSizeMajor.setText(calcResults[7]); 	//set second size maj result
		txtMinMagnitude.setText(calcResults[8]); 		//set min magnitude result
		txtMinResolution.setText(calcResults[9]); 		//set min res result
		txtMaxVisibleMag.setText(calcResults[10]); 		//set max vis mag result
		txtMinVisibleMag.setText(calcResults[11]); 		//set min vis mag result
		txtEyePieceMag.setText(calcResults[12]); 		//set eyepiece mag result
		
		
	}//end setResults
	
	/*
	 * Method name: resetInputs()
	 * Description: calls private method >>> copyAndStoreInputs
	 * Return: void
	 * 
	 *  */
	protected static void resetInputs()
	{
		copyAndStoreInputs();										//call private method >>> copy and store inputs
	}
	
	/************************************/
	
	/*
	 * Method name: storeResults()
	 * Description: writes the results array to file, user prompted
	 * 				for filename in save listener class, which calls 
	 * 				this method.
	 * Input: String >>> filename
	 * Return: void
	 * 
	 *  */
	protected static void storeResults(String s)
	{
		
		//create local attribute for filename acquired from user
		String filename = s;													//store input filename to filename attribute
		
		/* validation */
		/* check there are values to save */
		if(txtLensInput.getText().equals(""))									//if text field is empty
		{
			JOptionPane.showMessageDialog(txtFocalInput, "No values to save!"	//display error message in JDialogBox
					,"Save file error", JOptionPane.ERROR_MESSAGE);				
			
		}
		
			else															//else if text field is not empty
			{
				try															//try catch to handle exceptions		
				{
					FileWriter fw = new FileWriter(filename + ".txt");		//create new file writer object and pass filename
					PrintWriter pw = new PrintWriter(fw);					//create new print writer object and pass file writer
					
					for(int j = 0;j<13;j++)									//for loop to count through array
					{
						pw.println(calcResults[j]);							//write results set to file
						//System.out.println(calcResults[j]);				//used for testing only! check the values that are being saved
						
						
					}
					
					System.out.println("!!! File saved !!!");				//print file saved message
					pw.close();												//close the print writer
					
				}
				catch(IOException e)										//catch any exceptions
				{
					System.out.println("Error!!!");							//print error message to console
				}
					
			}
		
	}//end storeResults()
	
	
	/************************************/

	/* 
	 * Method Name: loadResults() 
	 * Function: loads results from a text file
	 * Input: Filename from inputMessageDialog user entry
	 * Return: void
	 * 
	 * */
	protected static void loadResults(String s)						//declare method and arguments
	{
		String filename = s;										//create string to store filename and set == to users filename input
		int j = 0;													//create int to use as counter for the array
		
		try															//try catch to to catch any exceptions
		{	
			FileReader fr = new FileReader(filename + ".txt");		//create new filereader object and pass filename 
			BufferedReader br = new BufferedReader(fr);				//create new buffered reader object and pass filereader
			String str ;											//create string to store each value from readLine() method
			
			while((str = br.readLine()) != null)					//while loop >>> while input != null, continue
			{
				calcResults[j] = str;								//store input into results array
				j++;												//increment counter used as array index
			}
			
			System.out.println("!!! File loaded !!!");				//print "file loaded" message to console
			setResults();											//call set results method to allocate the result set to correct txt fields
			
			br.close();												//close the buffered reader
			
		}
		catch(IOException e)																	//catch any exceptions
		{	
			System.out.println("Error!!!");														//print error message to console			
			JOptionPane.showMessageDialog(txtFocalInput, "File not found, please try again."
					,"File Loading Error", JOptionPane.ERROR_MESSAGE);							//open dialog box to display error msg to user
		}
	}
	
	/************************************/
	
	
	/***************** END PROTECTED METHODS **************************************************/
	
	/***************** PRIVATE METHODS **************************************************/
	
	/* 
	 * Method Name: setInputs() 
	 * Function: Print the user inputs to the inputs text fields in results section,
	 * 			 Clear the input text fields.
	 * */
	private static void copyAndStoreInputs()
	{
		/* store users input to results array */
		calcResults[0] = txtLensDiameter.getText();				//get user input for lens diameter and store in local attribute
		calcResults[1]= txtFocalRatio.getText();				//get user input for lens focal ratio and store in local attribute
		calcResults[2] = txtEyeFocalLength.getText();			//get user input for lens eyepiece focal length and store in local attribute
		
		/* clear user input fields */
		txtFocalRatio.setText(null);							//clear user focal ratio input
		txtLensDiameter.setText(null);							//clear user lens diam input
		txtEyeFocalLength.setText(null);						//clear user eye focal length input
		
	}//end copyInputs
	
	/***************** END PRIVATE METHODS **************************************************/
	
}//end class
