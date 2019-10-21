import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCustomerAccount {
	
	private final String[] CustAcctTest = {"Mama Me", "1234567890"};
	private final String[] CustAcctTestDave = {"Dave Nothere", "1123456789"};
	private final String[] CustAcctTestCarl = {"Carl IsDead", "1112345678"};
	
	CustomerAccount objToTest;
	CustomerAccount objToTestMock;
	CustomerAccountDAO objToTestDAOMock;
	
	
	@BeforeEach
	void setUp() throws Exception {
		objToTest = new CustomerAccount();
		objToTestMock = mock(CustomerAccount.class);
		objToTestDAOMock = mock(CustomerAccountDAO.class);
	}
		
	
	@Test
	void testCreateAccount() throws SQLException, NoAccountCreatedException   {
		
		CustomerAccount cust = objToTest.createNewAccount(CustAcctTest[0], CustAcctTest[1]);
		
		assertEquals("Mama Me", cust.getCustName());
		assertEquals("1234567890", cust.getCustPhone());
		
	}
	
	@Test
	void testCreateAccount2() throws SQLException, NoAccountCreatedException, NoSuchAccountException, NoSuchCustomerAccountException {
		CustomerAccount cust2 = objToTest.createNewAccount(CustAcctTestDave[0], CustAcctTestDave[1]);
		String custAcctNum = cust2.getCustAcctNumber();
		
		
        assertNotNull(cust2.getCustAcctNumber(), "Dave Nothere");
        assertEquals(cust2.getCustAcctNumber(), custAcctNum);
        assertEquals(cust2.getCustName(), "Dave Nothere");		
		
	}
	

}