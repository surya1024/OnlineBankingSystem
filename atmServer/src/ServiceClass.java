

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ServiceClass {
	//Class for all DB calls

	DataObject message = new DataObject();

	private  String CHECK_USER_QUERY ="select count(*) from user_info where user_name=?";
	private  String VALIDATE_USER_QUERY ="select password from user_info where user_name=?";
	private  String IS_ADMIN_QUERY ="select count(*) from user_info where user_name=? and is_admin=1";
	private  String ACC_DETAILS_QUERY ="select account_type,account_num,balance,last_transaction_date,is_active from account_info a,user_info u where u.user_name=? and u.user_id=a.user_id";
	private  String ACC_LIST_QUERY ="select distinct(account_num) from account_info a where a.user_id=?";
	private  String BEN_LIST_QUERY ="select distinct(account_num) from acc_beneficiaries a,user_info u where u.user_name=? and u.user_id=a.user_id";
	private  String ACC_BAL_QUERY ="select balance from account_info where account_num=?";
	private  String BAL_UPDATE_QUERY ="update account_info set balance=? where account_num=?";	
	private  String DEL_ACC_QUERY="delete from account_info where account_num=?";
	private String INSERT_ACC_INFO_QUERY="insert into account_info values (?,?,?,?,?)";
	private String INSERT_USER_INFO_QUERY="insert into user_info(user_name,password,email,is_admin,first_name,last_name,address,occupation,ssn) values (?,?,?,?,?,?,?,?,?)";
	private String GET_ACCOUNT_NUM_QUERY="select max(account_num) from account_info where 1=1";
	private String GET_USERID_QUERY ="select user_id from user_info where ssn=?";
	private String GET_USERID_FROM_NAME_QUERY ="select user_id from user_info where user_name=?";
	private String TRANSACTION_HIST_QUERY ="select tr.transaction_date,tr.amount,tr.transaction_type,tr.acct_num,a.account_type from transaction_history tr,user_info u,account_info a where tr.acct_num=? and u.user_name=a.user_id";
	private String ACC_DETAILS_BY_ID_QUERY="select a.account_type,a.account_num,a.balance,a.is_active,u.user_name,u.email,u.first_name,u.last_name,u.address,u.occupation from account_info a,user_info u where a.account_num=? and a.user_id=u.user_name";
	private String UPDATE_ACC_FREEZE="update account_info set is_active=0 where account_num=? ";
	private String UPDATE_ACC_ACTIVATE="update account_info set is_active=1 where account_num=? ";
	private String UPDATE_USER_INFO_QUERY="update user_info u,account_info a set user_name=?,email=?,first_name=?,last_name=?,address=?,occupation =? where a.account_num=? and a.user_id=u.user_id";
	private String INSERT_TRANSACTION_HIST="insert into transaction_history values (?,?,?,?,?)";
	private String INSERT_BEN_INFO_QUERY="insert into acc_beneficiaries values (?,?,?)";


	//method to check whether username exists  or not
	public ObjectAccount doesUserExists(Connection conn, String userName){
		if(conn!=null){
			ObjectAccount obj=new ObjectAccount();			
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(CHECK_USER_QUERY);
				stmt.setString(1, userName);
				System.out.println("Runnin SQL :" + stmt);
				rs = stmt.executeQuery();
				while(rs.next()){
					int count =rs.getInt(1);
					if(count==0){
						obj.setMessage("Error");
					}else{
						obj.setMessage("Success");
					}
				}
				return obj;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				try {
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		return null;

	}

	//method to validate username/password
	public ObjectAccount validateUser(Connection conn, String userName,String password){
		if(conn!=null){
			ObjectAccount obj=new ObjectAccount();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(VALIDATE_USER_QUERY);
				stmt.setString(1, userName);
				System.out.println("Runnin SQL :" + stmt);
				rs = stmt.executeQuery();
				while(rs.next()){
					String pass =rs.getString(1);
					if(password.equals(pass)){
						obj.setMessage("Success");
					}else{
						obj.setMessage("Error");
					}
				}
				return obj;
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			finally
			{
				try {
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	//method to check whether user has Admin access
	public ObjectAccount isAdmin(Connection conn, String userName){
		if(conn!=null){
			ObjectAccount obj=new ObjectAccount();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(IS_ADMIN_QUERY);
				stmt.setString(1, userName);
				System.out.println("Runnin SQL :" + stmt);
				rs = stmt.executeQuery();
				while(rs.next()){
					int count =rs.getInt(1);
					if(count>0){
						obj.setMessage("true");
						System.out.println("admin");

					}else{
						obj.setMessage("false");
						System.out.println("not a admin");
					}
				}

				return obj;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			finally
			{
				try {
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	//get Account Details for given username.
	public List<ObjectAccount> getAccountDetails(Connection conn, String userName){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<ObjectAccount> acctList = new ArrayList<ObjectAccount>();
		ObjectAccount obj;
		String[][] accInfo=new String[2][4];
		try {
			stmt = conn.prepareStatement(ACC_DETAILS_QUERY);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			System.out.println("Runnin SQL :" + stmt);
			while(rs.next()){
				obj = new ObjectAccount();
				obj.setAcctType(rs.getString(1));
				obj.setAcctNumber(rs.getInt(2));
				obj.setBalance(rs.getDouble(3));
				obj.setLast_trans_date(rs.getString(4));
				if(rs.getInt(5)==1){
					obj.setActive(true);
				}else{
					obj.setActive(false);
				}

				acctList.add(obj);
			}

			return acctList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	//get list of Account numbers for given username.

	public ObjectAccount getAccountList(Connection conn, String userName){
		PreparedStatement stmt = null;
		ArrayList a=new ArrayList();
		ResultSet rs = null;
		ObjectAccount obj=new ObjectAccount();
		try {

			stmt = conn.prepareStatement(ACC_LIST_QUERY);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			System.out.println("Runnin SQL :" + stmt);
			while(rs.next()){
				a.add(rs.getString(1));
			}			

			String[] accList = new String[a.size()];
			accList = (String[]) a.toArray(accList);

			obj.setAcctList(accList);

			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	//get list of Beneficiary Account numbers for given username.
	public ObjectAccount getBeneficiariesList(Connection conn, String userName){
		PreparedStatement stmt = null;
		ArrayList a=new ArrayList();
		ResultSet rs = null;
		ObjectAccount obj=new ObjectAccount();
		try {
			stmt = conn.prepareStatement(BEN_LIST_QUERY);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			System.out.println("Runnin SQL :" + stmt);
			while(rs.next()){
				a.add(rs.getString(1));
			}
			String[] benList = new String[a.size()];
			benList = (String[]) a.toArray(benList);

			obj.setBeneficiaryList(benList);	

			return obj;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public ObjectAccount checkBal(Connection conn, String fromAcc){

		ObjectAccount obj = new ObjectAccount();
		String oldSourceBal=null;
		double oldDestBal=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ACC_BAL_QUERY);			
			stmt.setString(1, fromAcc);
			System.out.println("Runnin SQL :" + stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				oldSourceBal = rs.getString(1);
			}

			

				obj.setMessage(oldSourceBal);
			
		} catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	
	
	//Transfer money between accounts
	public ObjectAccount transferMoney(Connection conn, String fromAcc,String toAcc,double amount,String userName ){

		ObjectAccount obj = new ObjectAccount();
		double oldSourceBal=0;
		double oldDestBal=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ACC_BAL_QUERY);			
			stmt.setString(1, fromAcc);
			System.out.println("Runnin SQL :" + stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				oldSourceBal = rs.getFloat(1);
			}

			if(oldSourceBal-amount <0){
				obj.setMessage("Insufficient Funds to continue transfer");
				return obj;
			}else{ 
				double newSourceBal = oldSourceBal-amount;				

				Calendar currenttime = Calendar.getInstance();
				Date sqldate = new Date((currenttime.getTime()).getTime());

				stmt = conn.prepareStatement(BAL_UPDATE_QUERY);
				stmt.setDouble(1, newSourceBal);
				stmt.setString(2, fromAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();

				stmt = conn.prepareStatement(ACC_BAL_QUERY);
				stmt.setString(1, toAcc);
				System.out.println("Runnin SQL :" + stmt);
				rs = stmt.executeQuery();
				while(rs.next()){
					oldDestBal = rs.getFloat(1);
				}

				double newDestBal =oldDestBal+amount;

				stmt = conn.prepareStatement(BAL_UPDATE_QUERY);
				stmt.setDouble(1, newDestBal);
				stmt.setString(2, toAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();

				//inserting transaction history records


				stmt = conn.prepareStatement(INSERT_TRANSACTION_HIST);
				stmt.setString(1, userName);
				stmt.setDate(2, sqldate);
				stmt.setDouble(3, amount);
				stmt.setString(4, "CREDIT");
				stmt.setString(5, toAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();

				stmt.setString(4, "DEBIT");
				stmt.setString(5, fromAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();

				obj.setMessage("Transaction Successful");
			}
		} catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	//withdraw money from a account
	public ObjectAccount withdrawMoney(Connection conn, String fromAcc,double amount,String userName ){

		ObjectAccount obj = new ObjectAccount();
		double oldSourceBal=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ACC_BAL_QUERY);
			stmt.setString(1, fromAcc);
			System.out.println("Runnin SQL :" + stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				oldSourceBal = rs.getFloat(1);
			}

			if(oldSourceBal-amount <0){
				obj.setMessage("Insufficient Funds to continue transfer");
				return obj;
			}else{ 
				double newSourceBal = oldSourceBal-amount;		

				Calendar currenttime = Calendar.getInstance();
				Date sqldate = new Date((currenttime.getTime()).getTime());

				stmt = conn.prepareStatement(BAL_UPDATE_QUERY);
				stmt.setDouble(1, newSourceBal);
				stmt.setString(2, fromAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();



				stmt = conn.prepareStatement(INSERT_TRANSACTION_HIST);
				stmt.setString(1, userName);
				stmt.setDate(2,sqldate);
				stmt.setDouble(3, amount);
				stmt.setString(4, "DEBIT");
				stmt.setString(5, fromAcc);
				System.out.println("Runnin SQL :" + stmt);
				stmt.executeUpdate();


				obj.setMessage("Transaction Successful\nBalance is "+ newSourceBal);
			}
		} catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}

		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	//deposit money to a account
	public ObjectAccount depositMoney(Connection conn,String toAcc,double amount, String userName ){

		ObjectAccount obj = new ObjectAccount();
		double oldDestBal=0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(ACC_BAL_QUERY);
			stmt.setString(1, toAcc);
			System.out.println("Runnin SQL :" + stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				oldDestBal = rs.getFloat(1);
			}

			double newDestBal =oldDestBal+amount;

			Calendar currenttime = Calendar.getInstance();
			Date sqldate = new Date((currenttime.getTime()).getTime());

			stmt = conn.prepareStatement(BAL_UPDATE_QUERY);
			stmt.setDouble(1, newDestBal);
			stmt.setString(2, toAcc);
			System.out.println("Runnin SQL :" + stmt);
			stmt.executeUpdate();



			stmt = conn.prepareStatement(INSERT_TRANSACTION_HIST);
			stmt.setString(1, userName);
			stmt.setDate(2, sqldate);
			stmt.setDouble(3, amount);
			stmt.setString(4, "CREDIT");
			stmt.setString(5, toAcc);
			System.out.println("Runnin SQL :" + stmt);
			stmt.executeUpdate();


			obj.setMessage("Transaction Successful\nBalence is "+newDestBal);

		} catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}

		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}


	public ObjectAccount createNewAcc(Connection conn,ObjectAccount acctObj){

		ObjectAccount obj = new ObjectAccount();
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			int accNum=getNextAvailableAcctNumber(conn,acctObj.getAcctType());
			stmt = conn.prepareStatement(INSERT_ACC_INFO_QUERY);	
			stmt.setString(1, acctObj.getAcctType());
			stmt.setInt(2, accNum);
			stmt.setDouble(3, acctObj.getBalance());
			stmt.setString(4,"1");
			stmt.setString(5, acctObj.getUserName());



			System.out.println("Runnin SQL :" + stmt);

			stmt.executeUpdate();
			obj.setMessage("Accout successfully created\n Account Number is  " + accNum);
		}
		catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}			
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}
	public ObjectAccount createNewAccount(Connection conn,ObjectAccount acctObj){

		ObjectAccount obj = new ObjectAccount();
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {

			stmt = conn.prepareStatement(INSERT_USER_INFO_QUERY);			

			stmt.setString(1, acctObj.getUserName());
			stmt.setString(2, acctObj.getPasswd());
			stmt.setString(3, acctObj.getEmailID());
			stmt.setString(4, "0");
			stmt.setString(5, acctObj.getFirstName());
			stmt.setString(6, acctObj.getLastName());
			stmt.setString(7, acctObj.getAddress());
			stmt.setString(8, acctObj.getOccupation());
			stmt.setString(9, acctObj.getSsn());

			System.out.println("Runnin SQL :" + stmt);

			stmt.executeUpdate();
			obj.setMessage("UserAccount  " +acctObj.getUserName() + " successfully created.");
		}
		catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}			
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}    
	private int getNextAvailableAcctNumber(Connection conn,String acctType){
		PreparedStatement stmt = null;
		int number=0;
		ResultSet rs=null;
		try {

			stmt = conn.prepareStatement(GET_ACCOUNT_NUM_QUERY);
			System.out.println("Runnin SQL :" + stmt);
			rs = stmt.executeQuery();

			while(rs.next()){
				number=rs.getInt(1);
			}

			return number+1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public ObjectAccount getTransactionHistory(Connection conn, String acctNum){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		ObjectAccount obj=new ObjectAccount();
		Transactions hist;
		try {
			stmt = conn.prepareStatement(TRANSACTION_HIST_QUERY);
			stmt.setString(1, acctNum);
			System.out.println("Running SQL :" + stmt);

			rs = stmt.executeQuery();
			obj.setResultset(rs);
			return obj;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public ObjectAccount getAccountDetailsByID(Connection conn, String acctNum){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		ObjectAccount obj=null;

		try {
			stmt = conn.prepareStatement(ACC_DETAILS_BY_ID_QUERY);
			stmt.setString(1, acctNum);
			rs = stmt.executeQuery();
			System.out.println("Runnin SQL :" + stmt);

			while(rs.next()){
				obj = new ObjectAccount();
				obj.setAcctType(rs.getString(1));
				obj.setAcctNumber(rs.getInt(2));
				obj.setBalance(rs.getDouble(3));
				obj.setLast_trans_date(rs.getString(4));
				if(rs.getInt(5)==1){
					obj.setActive(true);
				}else{
					obj.setActive(false);
				}
				obj.setUserName(rs.getString(6));
				obj.setEmailID(rs.getString(7));
				obj.setFirstName(rs.getString(8));
				obj.setLastName(rs.getString(9));
				obj.setAddress(rs.getString(10));
				obj.setOccupation(rs.getString(11));
			}

			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	public ObjectAccount deleteAccount(Connection conn, String acctNum) {
		PreparedStatement stmt = null;
	
		ObjectAccount obj=new ObjectAccount();
		try {
			stmt = conn.prepareStatement(DEL_ACC_QUERY);
			stmt.setString(1, acctNum);

			System.out.println("Runnin SQL :" + stmt);
			int a= stmt.executeUpdate();
			obj.setMessage("Account DELETED");
			//System.out.println("obj"obj.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}
	public ObjectAccount freezeOrActivateAccount(Connection conn, String acctNum){

		PreparedStatement stmt = null;
		ResultSet rs = null;
		ObjectAccount obj=new ObjectAccount();
		boolean active=false;
		try {
			stmt = conn.prepareStatement(ACC_DETAILS_BY_ID_QUERY);
			stmt.setString(1, acctNum);
			rs = stmt.executeQuery();
			System.out.println("Runnin SQL :" + stmt);

			while(rs.next()){
				if(rs.getInt(4)==1){
					active=true;
				}else{
					active=false;
				}

			}

			if(active){
				stmt = conn.prepareStatement(UPDATE_ACC_FREEZE);
				stmt.setString(1, acctNum);
				stmt.executeUpdate();
				System.out.println("Runnin SQL :" + stmt);
				obj.setMessage("Account # : " +acctNum + " Freeze complete");
			}else{
				stmt = conn.prepareStatement(UPDATE_ACC_ACTIVATE);
				stmt.setString(1, acctNum);
				stmt.executeUpdate();
				System.out.println("Runnin SQL :" + stmt);	
				obj.setMessage("Account # : " +acctNum+ " UnFreeze complete");
			}

			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public ObjectAccount updateAccountDetails(Connection conn,ObjectAccount acctObj ){

		ObjectAccount obj = new ObjectAccount();
		PreparedStatement stmt = null;

		try {

			stmt = conn.prepareStatement(UPDATE_USER_INFO_QUERY);			

			stmt.setString(1, acctObj.getUserName());
			stmt.setString(2, acctObj.getEmailID());
			stmt.setString(3, acctObj.getFirstName());
			stmt.setString(4, acctObj.getLastName());
			stmt.setString(5, acctObj.getAddress());
			stmt.setString(6, acctObj.getOccupation() );
			stmt.setInt(7, acctObj.getAcctNumber());
			System.out.println("Runnin SQL :" + stmt);

			stmt.executeUpdate();

			obj.setMessage("Account Details updated successfully");

		} catch (SQLException e) {
			obj.setMessage("Error in Transaction. Please contact System Administrator");
			e.printStackTrace();
			return obj;
		}			
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}





}
