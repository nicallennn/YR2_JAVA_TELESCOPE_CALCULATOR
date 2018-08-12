package tellscopeV4;

import java.io.PrintWriter;
import java.net.*;
//import java.util.Scanner;



public class SendResultsThread implements Runnable {
	
	public SendResultsThread(Socket socket)
	{
		this.s = socket;
		
	}

	//declare socket
	private Socket s;
	
	public void run() {
		
		//print connection message
		String client = s.getInetAddress().toString();
		System.out.println("Thread Connected to client: " + client);
		TellServerGui04.consoleView.append("\nSending results to client: " + client);
		
		
		//send results to client
		
		//while(true)
		//{
			try{
				//create print writer object to send results to client
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				
	
						//for loop to send results to client
						for(int i=0;i<11;i++)
						{
						out.println(TellServerGui04.calcResults[i]);
						}
						
						TellServerGui04.printResults();
						
						//TellServer.dontSendResultsToClient();
						
						//do calculations
						//break;
			}
			
			catch(Exception e)
			{
				
			}
		
						
			
		//}
		
	}
	
	

}
