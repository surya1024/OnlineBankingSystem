
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.*;

public class LoginPage implements ActionListener{
	protected JFrame baseFrame;
	protected JLabel title;
	protected JLabel status;
	protected JPanel controlPanel;
	protected CardLayout cards;
	protected JTextField username;
	protected JPasswordField password;
	protected JButton submit;
	protected ClientServerClass dbClient;
	protected ObjectAccount acctObj;
	protected ObjectAccount returnObj;

	LoginPage(){
		initialize();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==submit){

			String user = username.getText();
			String pass = password.getText();

			if(user==null || user.equals("") || pass==null || pass.equals("")){
				JOptionPane.showMessageDialog(controlPanel,
						"Username and Password can't be empty","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			else 
				dbClient = new ClientServerClass();

			acctObj = new ObjectAccount(); 
			acctObj.setUserName(user);
			acctObj.setMethodToInvoke("doesUserExists"); 
			returnObj=dbClient.makeServerCall(acctObj);

			if(returnObj!=null && !(returnObj.getMessage().equals("Error"))){
				acctObj = new ObjectAccount();
				acctObj.setUserName(user);
				acctObj.setPasswd(pass); 
				acctObj.setMethodToInvoke("validateUser");
				returnObj=null; 
				returnObj=dbClient.makeServerCall(acctObj);

				if(returnObj!=null && !(returnObj.getMessage().equals("Error"))){

					acctObj = new ObjectAccount();
					acctObj.setUserName(user); acctObj.setMethodToInvoke("isAdmin");
					returnObj=null;
					returnObj=dbClient.makeServerCall(acctObj);

					if(returnObj!=null)
						if(returnObj.getMessage().equals("true"))
						{

							controlPanel.setSize(550,256);

							cards.show(controlPanel, "AdminOptions");
							// baseFrame.setSize(900,700);
							baseFrame.setLayout(new BorderLayout());
							title.setText("What Would You Like To do"); 
							// status.setText(new Date().toString());
						}else 
							
						{
							cards.show(controlPanel, "UserOptions"); 
							controlPanel.setSize(900,700); 
							baseFrame.setLayout(new BorderLayout());	
							title.setText("WELCOME ");
							//	status.setText(new Date().toString());
						}

				}

				else{
					JOptionPane.showMessageDialog(controlPanel,"Authentication Error !!!",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(controlPanel," Authentication Error !!!",
						"Error",JOptionPane.ERROR_MESSAGE);
			}
		}		
	}


	private void initialize(){
		//code for initializing login screen
		baseFrame = new JFrame("ATM Simulator");
		baseFrame.setSize(550,350);
		baseFrame.setLayout(null); 
		baseFrame.setBackground(new Color(230, 230, 250));
		baseFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){ 
				if(dbClient!=null){
					dbClient.closeDBClient();
				}
				System.exit(0); 
			} 
		});

		cards = new CardLayout();
		controlPanel = new JPanel(); 
		controlPanel.setLayout(cards);
		controlPanel.setSize(550,256);
		cards.show(controlPanel, "UserSelection");

		title = new JLabel("ATM Simulator"); 
		title.setFont(new Font(title.getFont().getFontName(),Font.BOLD, 30));
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0,20,550,50);
		baseFrame.add(title);


		baseFrame.getContentPane().setSize(550, 256);

		baseFrame.getContentPane().setBackground(new Color(230, 230, 250));


		JPanel userSelectionPanel = new JPanel();


		userSelectionPanel.setLayout(null);
		userSelectionPanel.setBackground(new Color(230, 230, 250));



		username= new JTextField(15);
		username.setBounds(275, 63, 143, 29);
		userSelectionPanel.add(username);
		password= new JPasswordField(15);
		password.setBounds(275, 104, 143, 28);
		userSelectionPanel.add(password);
		JLabel userLbl = new JLabel("USERNAME ");
		userLbl.setBounds(157, 63, 93, 29);
		userSelectionPanel.	add(userLbl);
		JLabel passLbl = new JLabel("PASSWORD ");
		passLbl.setBounds(157, 104, 73, 29);
		userSelectionPanel.add(passLbl);
		submit = new JButton ("SIGN IN");
		submit.setBounds(198, 144, 143, 29);
		submit.addActionListener(this);
		userSelectionPanel.add(submit);

		controlPanel.setSize(550,256);
		controlPanel.add(userSelectionPanel, "UserSelection");
		controlPanel.setBounds(0,50,550,256);

		baseFrame.add(controlPanel);
		baseFrame.setVisible(true);

	}
}



