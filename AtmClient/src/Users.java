

import java.awt.Color;
import net.proteanit.sql.DbUtils;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Users  extends JPanel implements ActionListener{

	protected LoginPage loginObject;
	private String TRANSACTION_HIST_QUERY ="select tr.transaction_date,tr.amount,tr.transaction_type,tr.acct_num,a.account_type from transaction_history tr,user_info u,account_info a where tr.acct_num=? and u.user_name=a.user_id";
	JPanel FastCash,transfer,withdraw,deposit,history,changepin;
	private JPasswordField pwdNew,pwdOld,pwdConform;
	JButton home,back,transferBtn,withrawBtn,depositBtn,logoutBtn,btnFastCash,btnDeposit,btnBalense,btnWitdraw,btnTransfer,btnPinChange;
	JTextField Amount;
	JComboBox accountList,accountList2,accountList1;
	JTable table_1;
	 ObjectInputStream in;
	  ObjectOutputStream out;
	  Connection conn;
	  private Socket incoming;


	public Users(){


		setBackground(new Color(230, 230, 250));
		setLayout(null);

		btnFastCash = new JButton("Fast Cash");
		btnFastCash.setBounds(75, 33, 142, 29);
		btnFastCash.addActionListener(this);
		add(btnFastCash);

		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(75, 103, 142, 29);
		btnDeposit.addActionListener(this);
		add(btnDeposit);

		btnBalense = new JButton("Balance InquIry");
		btnBalense.setBounds(75, 173, 142, 29);
		btnBalense.addActionListener(this);
		add(btnBalense);

		btnWitdraw = new JButton("Witdraw");
		btnWitdraw.setBounds(320, 33, 142, 29);
		btnWitdraw.addActionListener(this);
		add(btnWitdraw);


		btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(320, 103, 142, 29);
		btnTransfer.addActionListener(this);
		add(btnTransfer);


		logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(320, 173, 142, 29);
		logoutBtn.addActionListener(this);
		add(logoutBtn);


	}  

	public void setloginObject(LoginPage obj) {
		this.loginObject = obj;
	}


	void createFastCashPanel(){

		FastCash = new JPanel(); 
		FastCash.setBackground(new Color(230, 230, 250));
		loginObject.controlPanel.setSize(800, 200);
		FastCash.setLayout(null);


		JButton button = new JButton("$40");
		button.setBounds(29, 69, 117, 29);
		FastCash.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(40);
			}
		});

		JButton button_1 = new JButton("$60");
		button_1.setBounds(213, 69, 117, 29);
		FastCash.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(60);
			}
		});
		JButton button_2 = new JButton("$80");
		button_2.setBounds(393, 69, 117, 29);
		FastCash.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(80);
			}
		});

		JButton button_3 = new JButton("$100");
		button_3.setBounds(29, 116, 117, 29);
		FastCash.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(100);
			}
		});

		JButton button_4 = new JButton("$200");
		button_4.setBounds(213, 116, 117, 29);
		FastCash.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(200);
			}
		});

		JButton button_5 = new JButton("&300");
		button_5.setBounds(393, 116, 117, 29);
		FastCash.add(button_5);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(300);
			}
		});

		JButton btnOtherAmount = new JButton("Other Amount");
		btnOtherAmount.setBounds(303, 184, 117, 29);
		FastCash.add(btnOtherAmount);
		btnOtherAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createWithdrawPanel();
				loginObject.controlPanel.add(withdraw, "Withdraw");
				loginObject.controlPanel.setSize(550,256);
				loginObject.title.setText("WithDraw Cash");
				loginObject.cards.show(loginObject.controlPanel, "Withdraw");
			}
		});

		home = new JButton ("Back");
		home.setBounds(119,184,117,29);
		home.addActionListener(this);
		FastCash.add(home);

		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setUserName(loginObject.username.getText());  		    
		loginObject.acctObj.setMethodToInvoke("getAccountList");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);	
		String[] accountNum =loginObject.returnObj.getAcctList();		            
		accountList = new JComboBox(accountNum);
		accountList.setBounds(268, 20, 137, 27);
		accountList.setSelectedIndex(0);
		FastCash.add(accountList);


		JLabel lblSeclectAccount = new JLabel("Seclect Account");
		lblSeclectAccount.setBounds(156, 20, 137, 21);
		FastCash.add(lblSeclectAccount);
	}
	void withdraw(double amount){
		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setFromAcct((String)accountList.getSelectedItem());
		loginObject.acctObj.setBalance(amount);
		loginObject.acctObj.setUserName(loginObject.username.getText());
		loginObject.acctObj.setMethodToInvoke("withdrawMoney");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);

		JOptionPane.showMessageDialog(loginObject.controlPanel, loginObject.returnObj.getMessage(),
				"Message",JOptionPane.INFORMATION_MESSAGE);
		loginObject.cards.show(loginObject.controlPanel, "UserOptions");


	}
	void createChangePinPanel(){

		changepin= new JPanel();	   
		changepin.setBackground(new Color(230, 230, 250));	    	 
		changepin.setLayout(null);

		JLabel lblOldPin = new JLabel("Old PIn");
		lblOldPin.setBounds(196, 36, 61, 16);
		changepin.add(lblOldPin);

		JLabel lblNewPin = new JLabel("New Pin");
		lblNewPin.setBounds(196, 76, 61, 16);
		changepin.add(lblNewPin);

		JLabel lblConformPin = new JLabel("Conform Pin");
		lblConformPin.setBounds(196, 116, 86, 16);
		changepin.add(lblConformPin);

		pwdNew = new JPasswordField();
		pwdNew.setBounds(294, 73, 71, 28);
		changepin.add(pwdNew);

		pwdConform = new JPasswordField();
		pwdConform.setBounds(294, 113, 71, 28);
		changepin.add(pwdConform);

		pwdOld = new JPasswordField();
		pwdOld.setBounds(294, 33, 71, 28);
		changepin.add(pwdOld);

		JButton btnChangePin = new JButton("Change Pin");
		btnChangePin.setBounds(228, 157, 117, 29);
		changepin.add(btnChangePin);

		home = new JButton ("Back");
		home.setBounds(6, 220, 79, 29);
		home.addActionListener(this);
		changepin.add(home);

	}
	void createTransferPanel(){

		transfer= new JPanel();	   
		transfer.setBackground(new Color(230, 230, 250));	    	 
		transfer.setLayout(null);


		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setUserName(loginObject.username.getText());  		    
		loginObject.acctObj.setMethodToInvoke("getAccountList");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);	
		String[] accountNum =loginObject.returnObj.getAcctList();		            
		accountList1 = new JComboBox(accountNum);
		accountList1.setBounds(163, 81, 137, 27);
		accountList1.setSelectedIndex(0);
		transfer.add(accountList1);


		JLabel lblSeclectAccount = new JLabel("Transfer From\n");
		lblSeclectAccount.setBounds(57, 83, 100, 21);
		transfer.add(lblSeclectAccount);


		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(312, 85, 26, 16);
		transfer.add(lblTo);

		accountList2 = new JComboBox(accountNum);
		accountList2.setBounds(350, 83, 137, 27);
		transfer.add(accountList2);
		
		JLabel amt = new JLabel("Amount");
		amt.setBounds(150, 115, 100, 16);
		transfer.add(amt);
		
		Amount = new JTextField();
		Amount.setBounds(209, 110, 134, 28);
		transfer.add(Amount);
		Amount.setColumns(10);

		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setBounds(219, 149, 117, 29);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accountList1.getSelectedItem().equals(accountList2.getSelectedItem())){
					JOptionPane.showMessageDialog(loginObject.controlPanel,"Cannot teansfer to same account !!!",
							"Error",JOptionPane.ERROR_MESSAGE);
				}else {
				    loginObject.acctObj = new ObjectAccount();
				    loginObject.acctObj.setFromAcct((String)accountList1.getSelectedItem());
				    loginObject.acctObj.setToAcct((String)accountList2.getSelectedItem());
				    loginObject.acctObj.setBalance(Double.parseDouble(Amount.getText()));
				    loginObject.acctObj.setUserName(loginObject.username.getText());
				    loginObject.acctObj.setMethodToInvoke("transferMoney");
				    loginObject.returnObj=null;
				    loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);
				    
								JOptionPane.showMessageDialog(loginObject.controlPanel,loginObject.returnObj.getMessage(),
				    "Message",JOptionPane.INFORMATION_MESSAGE);
								loginObject.cards.show(loginObject.controlPanel, "UserOptions");
				}
			
			}
		});
		transfer.add(btnTransfer);
		

		home = new JButton ("Back");
		home.setBounds(6, 220, 79, 29);
		home.addActionListener(this);
		transfer.add(home);
	}


	void createWithdrawPanel(){

		withdraw = new JPanel(); 
		withdraw.setBackground(new Color(230, 230, 250));
		loginObject.controlPanel.setSize(800, 200);
		loginObject.title.setText("Withdraw");
		withdraw.setLayout(null);

		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setUserName(loginObject.username.getText());  		    
		loginObject.acctObj.setMethodToInvoke("getAccountList");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);	
		String[] accountNum =loginObject.returnObj.getAcctList();		            
		accountList = new JComboBox(accountNum);
		accountList.setBounds(267, 36, 137, 27);
		accountList.setSelectedIndex(0);
		withdraw.add(accountList);

		JLabel lblSeclectAccount = new JLabel("Seclect Account");
		lblSeclectAccount.setBounds(155, 38, 100, 21);
		withdraw.add(lblSeclectAccount);

		Amount = new JTextField();
		Amount.setBounds(209, 112, 134, 28);
		withdraw.add(Amount);
		Amount.setColumns(10);

		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAmount.setBounds(209, 93, 134, 16);
		withdraw.add(lblEnterAmount);

		JButton btnWitdraw = new JButton("Witdraw");
		btnWitdraw.setBounds(209, 174, 134, 29);

		btnWitdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdraw(Double.parseDouble(Amount.getText()));
			}
		});
		withdraw.add(btnWitdraw);

		home = new JButton ("Back");
		home.setBounds(6, 220, 79, 29);
		home.addActionListener(this);
		withdraw.add(home);
	}

	void createDepositPanel(){

		deposit= new JPanel();	        
		deposit.setBackground(new Color(230, 230, 250));
		deposit.setLayout(null);

		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setUserName(loginObject.username.getText());  		    
		loginObject.acctObj.setMethodToInvoke("getAccountList");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);	
		String[] accountNum =loginObject.returnObj.getAcctList();		            
		accountList = new JComboBox(accountNum);
		accountList.setBounds(267, 36, 137, 27);
		accountList.setSelectedIndex(0);
		deposit.add(accountList);


		JLabel lblSeclectAccount = new JLabel("Seclect Account");
		lblSeclectAccount.setBounds(155, 38, 100, 21);
		deposit.add(lblSeclectAccount);

		Amount = new JTextField();
		Amount.setBounds(209, 112, 134, 28);
		deposit.add(Amount);
		Amount.setColumns(10);
		

		JLabel lblEnterAmount = new JLabel("Enter Amount");
		lblEnterAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterAmount.setBounds(209, 93, 134, 16);
		deposit.add(lblEnterAmount);

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(209, 174, 134, 29);
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginObject.acctObj = new ObjectAccount();
				loginObject.acctObj.setToAcct((String)accountList.getSelectedItem());
				loginObject.acctObj.setBalance(Double.parseDouble(Amount.getText()));
				loginObject.acctObj.setUserName(loginObject.username.getText());
				loginObject.acctObj.setMethodToInvoke("depositMoney");
				loginObject.returnObj=null;
				loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);

				JOptionPane.showMessageDialog(loginObject.controlPanel, loginObject.returnObj.getMessage(),
						"Message",JOptionPane.INFORMATION_MESSAGE);
				loginObject.cards.show(loginObject.controlPanel, "UserOptions");
			}
		});
		deposit.add(btnDeposit);

		home = new JButton ("Back");
		home.setBounds(6, 220, 79, 29);
		home.addActionListener(this);
		deposit.add(home);
	}

	void createTransactionHistoryPanel(){
		
		
		history = new JPanel();  
		history.setBackground(new Color(230, 230, 250));
		history.setLayout(null);
		//  history.setOpaque(false);

		loginObject.acctObj = new ObjectAccount();
		loginObject.acctObj.setUserName(loginObject.username.getText());  		    
		loginObject.acctObj.setMethodToInvoke("getAccountList");
		loginObject.returnObj=null;
		loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);	
		String[] accountNum =loginObject.returnObj.getAcctList();		            
		accountList = new JComboBox(accountNum);
		accountList.setBounds(265, 19, 137, 27);
		accountList.setSelectedIndex(0);
		history.add(accountList);

		JLabel lblSeclectAccount = new JLabel("Seclect Account");
		lblSeclectAccount.setBounds(153, 21, 100, 21);
		history.add(lblSeclectAccount);

		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.setBounds(209, 58, 117, 29);
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loginObject.acctObj = new ObjectAccount();
				loginObject.acctObj.setToAcct((String)accountList.getSelectedItem());
				loginObject.acctObj.setUserName(loginObject.username.getText());
				loginObject.acctObj.setMethodToInvoke("checkBal");
				loginObject.returnObj=null;
				loginObject.returnObj =loginObject.dbClient.makeServerCall(loginObject.acctObj);

				JOptionPane.showMessageDialog(loginObject.controlPanel,"Avealible Balence "+ loginObject.returnObj.getMessage(),
						"Message",JOptionPane.INFORMATION_MESSAGE);
				
				
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try 
			      { 	
				   
				   if(conn==null || conn.isClosed())
				    conn=ConnectionJDBC.getDBConnect();
					
			       in =	new ObjectInputStream(incoming.getInputStream());
				   out=	new ObjectOutputStream(incoming.getOutputStream());

			      }
				catch (Exception e1) 
			      {  
			    	 System.out.println(e1);
			      } 	
				try {
					stmt = conn.prepareStatement(TRANSACTION_HIST_QUERY);
					stmt.setString(1, (String)accountList.getSelectedItem());
					System.out.println("Running SQL :" + stmt);

					rs = stmt.executeQuery();
		

				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			
				
				
				
			//	table_1.setModel(buildTableModel(rs));
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			}
		});
		history.add(btnCheckBalance);


		
		table_1 = new JTable();
		table_1.setBounds(1, 1, 514, 101);
		
		
		JScrollPane scrollPane = new JScrollPane(table_1);
		scrollPane.setLayout(null);
		scrollPane.setBounds(17, 107, 514, 101);
		scrollPane.add(table_1);
		
		history.add(scrollPane);
		
		


		home = new JButton ("Back");
		home.setBounds(6, 220, 79, 29);
		home.addActionListener(this);
		history.add(home);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource()==home || e.getSource()==back  ){	
			loginObject.title.setText("What Would You Like To do"); 
			loginObject.controlPanel.setSize(800, 400);
			loginObject.cards.show(loginObject.controlPanel, "UserOptions");


		}else if (e.getSource()== btnFastCash) {
			createFastCashPanel();
			loginObject.controlPanel.add(FastCash, "FastCash");
			loginObject.controlPanel.setSize(550,256);
			loginObject.title.setText("FAST CASH");
			loginObject.cards.show(loginObject.controlPanel, "FastCash");
		}else if (e.getSource()== btnWitdraw) {
			createWithdrawPanel();
			loginObject.controlPanel.add(withdraw, "Withdraw");
			loginObject.controlPanel.setSize(550,256);
			loginObject.title.setText("WithDraw Cash");
			loginObject.cards.show(loginObject.controlPanel, "Withdraw");
		}else if (e.getSource()== btnDeposit) {
			createDepositPanel();
			loginObject.controlPanel.add(deposit, "Deposit");
			loginObject.controlPanel.setSize(550,256);
			loginObject.title.setText("Deposit CASH");
			loginObject.cards.show(loginObject.controlPanel, "Deposit");
		}else if (e.getSource()== btnBalense) {
			createTransactionHistoryPanel();
			loginObject.controlPanel.add(history, "History");
			loginObject.controlPanel.setSize(550,256);
			loginObject.title.setText("Check Balance");
			loginObject.cards.show(loginObject.controlPanel, "History");
		}else if (e.getSource()== btnTransfer) {
			createTransferPanel();
			loginObject.controlPanel.add(transfer, "Transfer");
			loginObject.controlPanel.setSize(550,256);
			loginObject.title.setText("Transfer Fund");
			loginObject.cards.show(loginObject.controlPanel, "Transfer");
		}else if (e.getSource()== btnPinChange) {
			createChangePinPanel();
			loginObject.controlPanel.add(changepin, "ChangePin");
			loginObject.title.setText("Chang Pin");
			loginObject.controlPanel.setSize(550,256);
			loginObject.cards.show(loginObject.controlPanel, "ChangePin");
		}else if(e.getSource()== logoutBtn){
			if(loginObject.dbClient!=null){
				loginObject.dbClient.closeDBClient();
			}

			JOptionPane.showMessageDialog(loginObject.controlPanel,"ThankYou for using ATM",
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



}


