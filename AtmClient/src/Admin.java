
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;


class Admin extends JPanel implements ActionListener{

	LoginPage bankObj;
	JPanel newAcct,newAcc,freezeAcct,searchAccount,acctDetails,editAcct,branchDetails,entryFields ;
	JTextField firstName,lastName,ssn,occupation,emailID,address,initialBalChecking,initialBalSaving,userName,acctNumber,userNameForSearch,userNameForEdit,emailForEdit,firstNameForEdit,lastNameForEdit,occupationForEdit;
	String[] acctTypeList={"CHECKING","SAVINGS","BOTH"};
	JComboBox acctType,accountList;
	JPasswordField passwd;
	JButton createBtn,createAccBtn,backBtn,resetBtn,freezeBtn,activateBtn,searchBtn,acctDetailsBtn,acctEditBtn,searchForFreezeBtn,backBtnForAcct,logoutBtn,AddAccountBtn;
	JButton btnCreateNewUser,ExextingUser,btnGo;


	Admin(){
		setBackground(new Color(230, 230, 250));
		setLayout(null);

		btnCreateNewUser = new JButton("");
		btnCreateNewUser.setBounds(220, 46, 108, 50);
		btnCreateNewUser.setLayout(new BorderLayout());
		btnCreateNewUser.addActionListener(this);
		add(btnCreateNewUser);

		JLabel lblCreateNew = new JLabel("Create New");
		lblCreateNew.setHorizontalAlignment(SwingConstants.CENTER);
		btnCreateNewUser.add(BorderLayout.NORTH,lblCreateNew);

		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnCreateNewUser.add(BorderLayout.SOUTH,lblUser);

		ExextingUser = new JButton("");
		ExextingUser.setBounds(220, 108, 108, 50);
		ExextingUser.setLayout(new BorderLayout());
		ExextingUser.addActionListener(this);
		add(ExextingUser);


		JLabel lblCreateNe = new JLabel("Existing");
		lblCreateNe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNew.setHorizontalAlignment(SwingConstants.CENTER);
		ExextingUser.add(BorderLayout.NORTH,lblCreateNe);

		JLabel lblUse = new JLabel("User");
		lblUse.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		ExextingUser.add(BorderLayout.SOUTH,lblUse);

		logoutBtn = new JButton("Log out");
		logoutBtn.setBounds(220, 170, 108, 29);
		add(logoutBtn);
		logoutBtn.addActionListener(this);

	}
	public void setBankObj(LoginPage obj) {
		this.bankObj = obj;
	} 
	void createNewAccountPanel(){
		newAcc= new JPanel();
		newAcc.setBackground(new Color(230, 230, 250));
		newAcc.setLayout(null);

		JLabel lblFirstName = new JLabel("Account Type");
		lblFirstName.setBounds(230, 22, 86, 16);
		newAcc.add(lblFirstName);

		acctType = new JComboBox(acctTypeList);
		acctType.setBounds(215, 42, 116, 27);
		acctType.setMaximumSize( acctType.getPreferredSize() );
		acctType.setModel(new DefaultComboBoxModel(new String[] {"CHECKING", "SAVINGS"}));
		acctType.setSelectedIndex(0);

		initialBalChecking = new JTextField();
		initialBalChecking.setBounds(211, 108, 134, 28);
		newAcc.add(initialBalChecking);
		initialBalChecking.setColumns(10);

		JLabel lblUserName = new JLabel("Initial Amount");
		lblUserName.setBounds(228, 82, 144, 27);
		newAcc.add(lblUserName);



		newAcc.add(acctType);

		createAccBtn = new JButton("Create");
		createAccBtn.setBounds(228, 162, 103, 29);
		newAcc.add(createAccBtn);
		createAccBtn.addActionListener(this);

		backBtn = new JButton("Home");
		backBtn.setBounds(6, 220, 117, 29);
		backBtn.addActionListener(this);
		newAcc.add(backBtn);

	}

	void createSearchAccountPanel(){
		searchAccount= new JPanel();
		searchAccount.setBackground(new Color(230, 230, 250));
		searchAccount.setLayout(null);

		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(244, 36, 61, 16);
		searchAccount.add(lblUserId);

		userNameForSearch = new JTextField();
		userNameForSearch.setBounds(207, 64, 134, 28);
		searchAccount.add(userNameForSearch);
		userNameForSearch.setColumns(10);

		searchBtn = new JButton("Search");
		searchBtn.setEnabled(true);
		searchBtn.setBounds(217, 99, 117, 29);
		searchBtn.addActionListener(this);
		searchAccount.	add(searchBtn);

		backBtn = new JButton("Home");
		backBtn.setBounds(6, 220, 117, 29);
		backBtn.addActionListener(this);
		searchAccount.add(backBtn);

	}

	void createNewUserPanel(){


		newAcct= new JPanel();	    
		newAcct.setBackground(new Color(230, 230, 250));
		newAcct.setLayout(null);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(28, 43, 87, 16);
		newAcct.add(lblFirstName);

		firstName = new JTextField();
		firstName.setBounds(127, 37, 134, 28);
		newAcct.add(firstName);
		firstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(28, 83, 87, 16);
		newAcct.add(lblLastName);

		lastName = new JTextField();
		lastName.setBounds(127, 77, 134, 28);
		newAcct.add(lastName);
		lastName.setColumns(10);

		userName = new JTextField();
		userName.setBounds(127, 117, 134, 28);
		newAcct.add(userName);
		userName.setColumns(10);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(28, 123, 87, 16);
		newAcct.add(lblUserName);

		JLabel lblPin = new JLabel("PIn");
		lblPin.setBounds(28, 163, 61, 16);
		newAcct.add(lblPin);

		passwd = new JPasswordField();
		passwd.setBounds(127, 157, 134, 28);
		newAcct.add(passwd);

		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setBounds(314, 43, 61, 16);
		newAcct.add(lblEmailId);

		emailID = new JTextField();
		emailID.setBounds(397, 37, 134, 28);
		newAcct.add(emailID);
		emailID.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(314, 83, 61, 16);
		newAcct.add(lblAddress);

		address = new JTextField();
		address.setBounds(397, 77, 134, 28);
		newAcct.add(address);
		address.setColumns(10);

		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setBounds(314, 123, 74, 16);
		newAcct.add(lblOccupation);

		occupation = new JTextField();
		occupation.setBounds(397, 117, 134, 28);
		newAcct.add(occupation);
		occupation.setColumns(10);

		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setBounds(314, 163, 61, 16);
		newAcct.add(lblSsn);

		ssn = new JTextField();
		ssn.setBounds(397, 157, 134, 28);
		newAcct.add(ssn);
		ssn.setColumns(10);

		backBtn = new JButton("Back");
		backBtn.setBounds(74, 207, 117, 29);
		backBtn.addActionListener(this);
		newAcct.add(backBtn);

		createBtn = new JButton("Create");
		createBtn.setBounds(216, 207, 117, 29);
		createBtn.addActionListener(this);
		newAcct.add(createBtn);

		resetBtn = new JButton("Reset");
		resetBtn.setBounds(358, 207, 117, 29);
		resetBtn.addActionListener(this);
		newAcct.add(resetBtn);

	}

	void createEditaccount(){

		editAcct = new JPanel();
		editAcct.setBackground(new Color(230, 230, 250));
		editAcct.setLayout(null);


		JLabel lblFirstName = new JLabel("Select Account");
		lblFirstName.setBounds(226, 29, 86, 16);
		editAcct.add(lblFirstName);


		bankObj.acctObj = new ObjectAccount();
		bankObj.acctObj.setUserName(userNameForSearch.getText());  		    
		bankObj.acctObj.setMethodToInvoke("getAccountList");
		bankObj.returnObj=null;
		bankObj.returnObj =bankObj.dbClient.makeServerCall(bankObj.acctObj);	

		String[] accountNum =bankObj.returnObj.getAcctList();		            
		accountList = new JComboBox(accountNum);

		accountList.setBounds(211, 49, 116, 27);
		accountList.setSelectedIndex(0);

		editAcct.add(accountList);

		btnGo = new JButton("Go ->");
		btnGo.setBounds(238, 88, 63, 29);
		editAcct.add(btnGo);
		btnGo.addActionListener(this);


	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource()==backBtn  ){	
			bankObj.controlPanel.setSize(800, 400);
			bankObj.cards.show(bankObj.controlPanel, "AdminOptions");

		}else if(e.getSource()== resetBtn){			
			firstName.setText("");
			lastName.setText("");
			address.setText("");
			ssn.setText("");
			occupation.setText("");
			emailID.setText("");
			userName.setText("");
			passwd.setText("");


		}else if(e.getSource()==backBtnForAcct){	
			bankObj.controlPanel.setSize(800, 400);
			bankObj.cards.show(bankObj.controlPanel, "SearchAccount");

		}else if (e.getSource()==ExextingUser) {
			bankObj.title.setText("Search User"); 
			createSearchAccountPanel();
			bankObj.controlPanel.add(searchAccount, "SearchAcc");
			bankObj.cards.show(bankObj.controlPanel, "SearchAcc");

		}else if (e.getSource()==btnCreateNewUser) {
			bankObj.title.setText("Creat New User"); 
			createNewUserPanel();
			bankObj.controlPanel.add(newAcct, "NewUser");
			bankObj.title.setText("Create New User");
			bankObj.cards.show(bankObj.controlPanel, "NewUser");

		}else if(e.getSource()==createBtn){

			bankObj.acctObj = populateAccountObject();  			  
			bankObj.acctObj.setMethodToInvoke("createNewAccount");
			bankObj.returnObj=null;
			bankObj.returnObj =bankObj.dbClient.makeServerCall(bankObj.acctObj);	

			JOptionPane.showMessageDialog(bankObj.controlPanel,bankObj.returnObj.getMessage(),
					"Message",JOptionPane.INFORMATION_MESSAGE);
			bankObj.cards.show(bankObj.controlPanel, "AdminOptions");

		}else if(e.getSource()== searchBtn){

			bankObj.acctObj = new ObjectAccount();
			bankObj.acctObj.setUserName(userNameForSearch.getText());
			bankObj.acctObj.setMethodToInvoke("doesUserExists");
			bankObj.returnObj=null;
			bankObj.returnObj =bankObj.dbClient.makeServerCall(bankObj.acctObj);
			if(bankObj.returnObj!=null && !(bankObj.returnObj.getMessage().equals("Error"))){
				searchAccount.remove(searchBtn);
				userNameForSearch.enable(false);

				AddAccountBtn = new JButton("Add Account");
				AddAccountBtn.setBounds(217, 99, 117, 29);
				searchAccount.add(AddAccountBtn);
				AddAccountBtn.addActionListener(this);

				acctEditBtn = new JButton("Edit Acc");
				acctEditBtn.setBounds(219, 138, 117, 29);
				searchAccount.add(acctEditBtn);
				acctEditBtn.addActionListener(this);


				bankObj.controlPanel.add(searchAccount, "SearchAcc");
				bankObj.cards.show(bankObj.controlPanel, "SearchAcc");

			}else{
				JOptionPane.showMessageDialog(bankObj.controlPanel,"User Not Found !!!",
						"Error",JOptionPane.ERROR_MESSAGE);
			}


		}else if (e.getSource()==AddAccountBtn) {
			bankObj.title.setText("Create New Account"); 
			createNewAccountPanel();
			bankObj.controlPanel.add(newAcc, "NewAccount");
			bankObj.title.setText("Create New User");
			bankObj.cards.show(bankObj.controlPanel, "NewAccount");

		}else if (e.getSource()==createAccBtn) {
			bankObj.acctObj.setAcctType((String)acctType.getSelectedItem());
			bankObj.acctObj.setBalance(Double.parseDouble(initialBalChecking.getText()));
			bankObj.acctObj.setMethodToInvoke("createNewAcc");
			bankObj.returnObj=null;
			bankObj.returnObj =bankObj.dbClient.makeServerCall(bankObj.acctObj);	

			JOptionPane.showMessageDialog(bankObj.controlPanel,bankObj.returnObj.getMessage(),
					"Message",JOptionPane.INFORMATION_MESSAGE);
			bankObj.cards.show(bankObj.controlPanel, "AdminOptions");


		}else if (e.getSource()==acctEditBtn) {
			bankObj.title.setText("Edit Account"); 
			createEditaccount();
			bankObj.controlPanel.add(editAcct, "EditAcc");
			bankObj.cards.show(bankObj.controlPanel, "EditAcc");
		}else if (e.getSource()==btnGo) {
			accountList.setEditable(false);

			freezeBtn = new JButton("Acvate/Inacvatie");
			freezeBtn.setBounds(286, 136, 134, 29);
			editAcct.add(freezeBtn);
			freezeBtn.addActionListener(this);

			activateBtn = new JButton("Delete");
			activateBtn.setBounds(140, 136, 134, 29);
			editAcct.add(activateBtn);
			activateBtn.addActionListener(this);
			bankObj.controlPanel.add(editAcct, "EditAcc");
			bankObj.cards.show(bankObj.controlPanel, "EditAcc");

		}else if (e.getSource()==activateBtn) {
			bankObj.acctObj = new ObjectAccount();
			bankObj.acctObj.setAcctNumber(Integer.parseInt((String) accountList.getSelectedItem()));
			bankObj.acctObj.setMethodToInvoke("deleteAccount");	  			 
			bankObj.returnObj=null;
			bankObj.returnObj=bankObj.dbClient.makeServerCall(bankObj.acctObj);

			JOptionPane.showMessageDialog(bankObj.controlPanel,bankObj.returnObj.getMessage(),
					"Message",JOptionPane.INFORMATION_MESSAGE);
			bankObj.cards.show(bankObj.controlPanel, "AdminOptions");
		}else if(e.getSource()== freezeBtn){

			bankObj.acctObj = new ObjectAccount();
			bankObj.acctObj.setAcctNumber(Integer.parseInt((String) accountList.getSelectedItem()));
			bankObj.acctObj.setMethodToInvoke("freezeOrActivateAccount");	  			 
			bankObj.returnObj=null;
			bankObj.returnObj=bankObj.dbClient.makeServerCall(bankObj.acctObj);

			JOptionPane.showMessageDialog(bankObj.controlPanel,bankObj.returnObj.getMessage(),
					"Message",JOptionPane.INFORMATION_MESSAGE);
			bankObj.cards.show(bankObj.controlPanel, "AdminOptions");
		}else if(e.getSource()== logoutBtn){
			if(bankObj.dbClient!=null){
				bankObj.dbClient.closeDBClient();
			}

			JOptionPane.showMessageDialog(bankObj.controlPanel,"ThankYou for using ATM",
					"Message",JOptionPane.INFORMATION_MESSAGE);
			LoginPage  login= new LoginPage(); 

			Users userOptions = new Users();
			userOptions.setloginObject(login);
			login.controlPanel.add(userOptions, "UserOptions");

			Admin admOptions = new Admin();
			admOptions.setBankObj(login);
			login.controlPanel.add(admOptions, "AdminOptions"); 

		}

	}

	private ObjectAccount populateAccountObject(){

		ObjectAccount acct = new ObjectAccount();

		acct.setFirstName(firstName.getText());
		acct.setLastName(lastName.getText());
		acct.setAddress(address.getText());
		acct.setSsn(ssn.getText());
		acct.setOccupation(occupation.getText());
		acct.setEmailID(emailID.getText());
		acct.setUserName(userName.getText());
		acct.setPasswd(passwd.getText());	   
		return acct;

	}
}