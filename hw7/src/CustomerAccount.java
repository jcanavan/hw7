import java.sql.SQLException;

// Uses the CustomerAccountDAO to save to the database

public class CustomerAccount {
	
	private String custName;
	private String custPhone;
	private String custAcctNumber;
	
	public CustomerAccount() {
		// create empty CustomerAccount
	}
	

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}


	public String getCustAcctNumber() {
		return custAcctNumber;
	}


	public void setCustAcctNumber(String custAcctNumber) {
		this.custAcctNumber = custAcctNumber;
	}
	
	public CustomerAccount createNewAccount(String name, String phone) throws SQLException, NoAccountCreatedException {
		CustomerAccount newAcct = new CustomerAccount();
		String acctNum = "";
		
		CustomerAccountDAO cad = new CustomerAccountDAO();
		try {
			acctNum = cad.newAcctNumber(name, phone);
		} catch (SQLException se) {
			// retry the call -- it always works the second time
			try {
				acctNum  = cad.newAcctNumber(name, phone);
			} catch (SQLException se1) {
				throw new NoAccountCreatedException(String.format("Account for %s at %s not created", name, phone));
			}
		}
		
		newAcct.setCustName(name);
		newAcct.setCustPhone(phone);
		newAcct.setCustAcctNumber(acctNum);
		
		try {
			cad.saveAccount(this);
		} catch (SQLException se2) {
			if (se2.getErrorCode() != 803) 
				throw new NoAccountCreatedException(String.format("Account for %s at %s not created with account number %s", name, phone, acctNum));
		}
		
		return newAcct;
	}
	
	public CustomerAccount updateCustomerName(String acctNum, String name) throws NoSuchCustomerAccountException {
		CustomerAccountDAO cad = new CustomerAccountDAO();
		
		try {
			custName = name;
			cad.updateAccount(this);
		} catch (SQLException se) {
			// unable to find the record to be updated
			throw new NoSuchCustomerAccountException(String.format("No customer record with acctount number %s ", acctNum));
		}
		
		return null;
	}

}
