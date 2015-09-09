


public class Main {

	      
	 public static void main(String[] args){
		 
		
		 LoginPage  login= new LoginPage(); 
		 
		 Users userOptions = new Users();
		 userOptions.setloginObject(login);
		 login.controlPanel.add(userOptions, "UserOptions");
	      
	     Admin admOptions = new Admin();
		 admOptions.setBankObj(login);
		 login.controlPanel.add(admOptions, "AdminOptions"); 
	    	
	 }	 
	
}