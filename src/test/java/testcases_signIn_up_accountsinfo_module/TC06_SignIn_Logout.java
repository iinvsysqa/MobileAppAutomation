package testcases_signIn_up_accountsinfo_module;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.SignInPage;
import pages.SignUpPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC06_SignIn_Logout extends MobileAppWrappers {

	LandingPage landingpage;
	SignInPage loginpage;
	HomePage homepage;
	OtpPage otppage;
	AddDevicePage adddevicepage;
	DeviceMenuPage devicemenupage;
	SignUpPage signuppage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC06 - Login, Pair and logout from device";
		testDescription = "Check after Login, Pair and logout from device is working";
	}
	

	@Test
	public void login() throws Exception {
		initAndriodDriver();
		loginpage = new SignInPage(driver);
		landingpage = new LandingPage(driver);
		otppage = new OtpPage(driver);
		adddevicepage= new AddDevicePage(driver);
		devicemenupage= new DeviceMenuPage(driver);
		homepage=new HomePage(driver);
		signuppage =new SignUpPage(driver);
		
		logReadandWrite readwrite=new logReadandWrite("COM4");
		try {
		readwrite.openPort();
		readwrite.read();
		Thread.sleep(2000);
		readwrite.write("factory_reset\r");
		
		signuppage.uninstall_reinstall();
		landingpage.clickSignInButton();
		loginpage.enterUserName("testuser007@gmail.com");
		loginpage.clickSignInButton();
		otppage.verifyOTPVerificationTitle("OTP Verification");
		otppage.enterOTPField1("1");
		otppage.enterOTPField2("2");
		otppage.enterOTPField3("3");
		otppage.enterOTPField4("4");
		otppage.submitButton();

		adddevicepage.pair(1);
		adddevicepage.clickNextButtonsZephyrInfo();
		adddevicepage.clickSubmitButtonDeviceSetting();
		for(int i=0;i<2;i++) {
			homepage.clickONOFFButton();
			Thread.sleep(1000);
			}
		
		homepage.clickMenuBarButton();
		devicemenupage.clickDeviceSettingsButton();
		devicemenupage.clickResetDeviceButton();
		devicemenupage.clickResetConfirmationYesButton();
		homepage.clickMenuBarButton();
		devicemenupage.clickLogoutButtonAfterReset();
		devicemenupage.clickLogoutConfirmationButton();
		signuppage.clickSignUpButton();
		readwrite.closePort();
		}
		catch (Exception e) {
			e.printStackTrace();
			readwrite.closePort();
		}
	}
		
	
}
