package testcases_signup_module;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.SignInPage;
import pages.OtpPage;
import pages.SignUpPage;
import wrappers.MobileAppWrappers;

public class TC03_SignIn_Flow extends MobileAppWrappers {

	LandingPage landingpage;
	SignInPage signinpage;
	HomePage homepage;
	OtpPage otppage;
	SignUpPage signuppage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "Sign In with unregistered username";
		testDescription = "Try to Sign In with unregistered username";
	}
	

	@Test
	public void signIn() throws FileNotFoundException, IOException, InterruptedException {
		
	signinpage = new SignInPage(driver);
	landingpage = new LandingPage(driver);
	otppage = new OtpPage(driver);
	signuppage =new SignUpPage(driver);
	
	landingpage.clickSignInButton();
	double rand=Math.random()*100000000;
	signinpage.enterUserName("testuser"+(int)rand);
	signinpage.checkUserNameNotFoundToast("User Not Found");
	
	
	}

}