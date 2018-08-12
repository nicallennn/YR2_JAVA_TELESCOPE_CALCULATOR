package tellscopeV4;

import java.net.*;
//import java.util.ArrayList;
import java.util.Scanner;

public class ClientListeningThread implements Runnable{
	
	//default constructor
	public ClientListeningThread(Socket socket)
	{
		this.s = socket;
			
	}
	
	//socket for incoming data from the client
	private Socket s;
	private static Scanner in;
	
	
	//private static int inputCount = 0;

	
	public void run() {
		
		String server = s.getInetAddress().toString();
		System.out.println("Connected to " + server);
		
		try
		{
			//create new scanner object and set it to get input stream from socket
			in = new Scanner(s.getInputStream());
			
			
			
			// loop to test if the socket input reads "result"
			
			while(true)
			{
				//store next line in "input" string
				String input = in.nextLine();
				//check for equality with string "calculate"
				if(input.equalsIgnoreCase("result"))
				{
					
					
					//print results to console
					TellScopeGui04.printResults();
					
					//copy results from array list to array
					TellScopeGui04.copyResults();
					
					//once results have been copied to the array, clear the array list
					TellScopeGui04.results.clear();
					
					//set the results in the correct text fields
					TellScopeGui04.setResults();
					
					
					break;
				}
				
				
				
				else
				{
					TellScopeGui04.results.add(input);
					
					
					
				}
				
			}	
			
		}	
		catch(Exception e)
		{
			//do nothing
		}
		
		
		}//end run
	
	
		
	}//end client listening thread class


