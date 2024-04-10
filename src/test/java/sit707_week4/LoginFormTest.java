package sit707_week4;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests functions in LoginForm.
 * @author Ahsan Habib
 */
public class LoginFormTest 
{

	@Test
	public void testStudentIdentity() {
	    String studentId = "s222196167";
	    Assert.assertNotNull(studentId);
	}


	@Test
	public void testStudentName() {
	    String studentName = "Apurv Gurudatta Sapar";
	    Assert.assertNotNull(studentName);
	}

	
	@Test
    public void testFailEmptyUsernameAndEmptyPasswordAndDontCareValCode()
    {
		LoginStatus status = LoginForm.login(null, null);
		Assert.assertTrue( status.isLoginSuccess() == false );
    }
	
	// Test cases for Username: -, Password: -, Validation Code: X (Expected: Login Failed)
    @Test
    public void testEmptyUsernameAndPassword() {
        LoginStatus status = LoginForm.login(null, null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Test cases for Username: -, Password: W, Validation Code: X (Expected: Login Failed)
    @Test
    public void testEmptyUsernameAndWrongPassword() {
        LoginStatus status = LoginForm.login(null, "wrong_password");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Test cases for Username: -, Password: C, Validation Code: X (Expected: Login Failed)
    @Test
    public void testEmptyUsernameAndCorrectPassword() {
        LoginStatus status = LoginForm.login(null, "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Username", status.getErrorMsg());
    }

    // Test cases for Username: W, Password: -, Validation Code: X (Expected: Login Failed)
    @Test
    public void testWrongUsernameAndEmptyPassword() {
        LoginStatus status = LoginForm.login("wrong_username", null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
    }

    // Test cases for Username: W, Password: W, Validation Code: X (Expected: Login Failed)
    @Test
    public void testWrongUsernameAndWrongPassword() {
        LoginStatus status = LoginForm.login("wrong_username", "wrong_password");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Test cases for Username: W, Password: C, Validation Code: X (Expected: Login Failed)
    @Test
    public void testWrongUsernameAndCorrectPassword() {
        LoginStatus status = LoginForm.login("wrong_username", "ahsan_pass");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Test cases for Username: C, Password: -, Validation Code: X (Expected: Login Failed)
    @Test
    public void testCorrectUsernameAndEmptyPassword() {
        LoginStatus status = LoginForm.login("ahsan", null);
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Empty Password", status.getErrorMsg());
    }

    // Test cases for Username: C, Password: W, Validation Code: X (Expected: Login Failed)
    @Test
    public void testCorrectUsernameAndWrongPassword() {
        LoginStatus status = LoginForm.login("ahsan", "wrong_password");
        Assert.assertFalse(status.isLoginSuccess());
        Assert.assertEquals("Credential mismatch", status.getErrorMsg());
    }

    // Test cases for Username: C, Password: C, Validation Code: X (Expected: Login Failed)
    @Test
    public void testCorrectUsernameAndCorrectPassword() {
        LoginStatus status = LoginForm.login("ahsan", "ahsan_pass");
        Assert.assertTrue(status.isLoginSuccess());
        Assert.assertEquals("123456", status.getErrorMsg()); // Validation code returned upon successful login

        // Additional validation code tests (for successful login scenario)
        Assert.assertFalse(LoginForm.validateCode(null)); // Empty code
        Assert.assertFalse(LoginForm.validateCode("wrong_code")); // Wrong code
        Assert.assertTrue(LoginForm.validateCode("123456")); // Correct code
    }
}
