import java.net.*;
import java.util.List;
import java.io.*;

public class ClientServerClass
{
	//Client class that talks to server program running in afsconnect1/3023 location.
	
	//String serverName = "osl9.njit.edu";
	
	String serverName = "osl82";
    int port = 2482;    
    Socket client; 
    ObjectOutputStream outputStream;
    ObjectInputStream  inputStream;
    
   //method to get data from DB
    public ObjectAccount makeServerCall(ObjectAccount obj){

    	 try {
    	       	client= new Socket(serverName, port);
    		  } catch (UnknownHostException e) {
    			 e.printStackTrace();
    		  } catch (IOException e) {
    			 e.printStackTrace();
    		  }	
    	 
    	try {
    		
    	outputStream = new ObjectOutputStream(client.getOutputStream());
        inputStream = new ObjectInputStream(client.getInputStream());

		outputStream.writeObject(obj);

		ObjectAccount returnObj;		
		returnObj = (ObjectAccount)inputStream.readObject();
		
      	return returnObj;
		
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return null;

    }
    
    // another method to get List data from DB
    public List makeServerCallForList(ObjectAccount obj){

   	 try {
   	        client= new Socket(serverName, port);
   		  } catch (UnknownHostException e) {
   			 e.printStackTrace();
   		  } catch (IOException e) {
   			 e.printStackTrace();
   		  }	
   	 
   	try {
   		
   	   outputStream = new ObjectOutputStream(client.getOutputStream());
       inputStream = new ObjectInputStream(client.getInputStream());

		outputStream.writeObject(obj);

		List returnObj;		
		returnObj = (List)inputStream.readObject();
      
		return returnObj;
		
   	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
   	
   	return null;

   }
    
    
    //method to close all streams and socket. called when application closes
    public void closeDBClient(){
        try {
			client.close();
			outputStream.close();
			inputStream.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}