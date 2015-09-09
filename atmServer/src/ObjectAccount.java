

import java.sql.ResultSet;
import java.util.List;

public class ObjectAccount extends DataObject {

	private double totBalance;
	private double savingBal;

	private int accountNum;
	private String accountType;
	private String lastTransactionDate;

	private String firstName;
	private String lastName;

	private String address;
	private String ssn;
	private String occupation;
	private String emailID;

	private String userName;
	private String passwd;

	private String fromAccount;
	private String toAccount;

	private String invokingMethod;

	private String[] accountList;
	private String[] beneficiaryList;


	private List<Transactions> historyChecking;
	private List<Transactions> historySavings;

	private boolean isActive;
	
	private ResultSet rs;

	public double getBalance() {
		return totBalance;
	}

	public void setBalance(double balance) {
		this.totBalance = balance;
	}

	public int getAcctNumber() {
		return accountNum;
	}

	public void setAcctNumber(int acctNumber) {
		this.accountNum = acctNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAcctType() {
		return accountType;
	}

	public void setAcctType(String acctType) {
		this.accountType = acctType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public String getMethodToInvoke() {
		return invokingMethod;
	}

	public void setMethodToInvoke(String methodToInvoke) {
		this.invokingMethod = methodToInvoke;
	}

	public String getFromAcct() {
		return fromAccount;
	}

	public void setFromAcct(String fromAcct) {
		this.fromAccount = fromAcct;
	}

	public String getToAcct() {
		return toAccount;
	}

	public void setToAcct(String toAcct) {
		this.toAccount = toAcct;
	}

	public String[] getAcctList() {
		return accountList;
	}

	public void setAcctList(String[] acctList) {
		this.accountList = acctList;
	}

	public String[] getBeneficiaryList() {
		return beneficiaryList;
	}

	public void setBeneficiaryList(String[] beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	public List<Transactions> getCheckingHist() {
		return historyChecking;
	}

	public void setCheckingHist(List<Transactions> checkingHist) {
		this.historyChecking = checkingHist;
	}

	public List<Transactions> getSavingsHist() {
		return historySavings;
	}

	public void setSavingsHist(List<Transactions> savingsHist) {
		this.historySavings = savingsHist;
	}

	public String getLast_trans_date() {
		return lastTransactionDate;
	}

	public void setLast_trans_date(String last_trans_date) {
		this.lastTransactionDate = last_trans_date;
	}

	public double getSavingsbalance() {
		return savingBal;
	}

	public void setSavingsbalance(double savingsbalance) {
		this.savingBal = savingsbalance;
	}
	
	public ResultSet getResultset() {
		return rs;
	}

	public void setResultset(ResultSet rs) {
		this.rs = rs;
	}

}
