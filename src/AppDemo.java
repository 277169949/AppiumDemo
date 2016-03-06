import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppDemo {
	private static AppiumDriver driver;

	public static void main(String[] args) throws MalformedURLException {

		// 设置apk的路径
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		// File app = new File(appDir, "ISDAcceptance.apk");

		// 设置自动化相关参数
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");

		// 测试手机操作系统
		capabilities.setCapability("platformName", "Android");

		// 模拟器类型
		// capabilities.setCapability("deviceName", "582af7877b5a");
		capabilities.setCapability("deviceName", "76P4C15708006345");
		// 设置安卓系统版本
		capabilities.setCapability("platformVersion", "4.4");

		// 设置apk路径
		// capabilities.setCapability("app", app.getAbsolutePath());
		// 设置udid
		// capabilities.setCapability("udid", "76P4C15708006345");

		capabilities.setCapability("unicodeKeyboard", "True"); // for input
																// Chinese
		capabilities.setCapability("resetKeyboard", "True");

		// 设置app的主包名和主类包

		capabilities.setCapability("appPackage", "com.hw.smart.isdacceptance");
		capabilities.setCapability("appActivity", "com.hw.smart.isdacceptance.provider.activites.WelcomeActivity");
		capabilities.setCapability("appWaitActivity", "com.hw.smart.isdacceptance.provider.activites.LoginActivity");// 初始化AppiumDriver

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		// 开始操作
		driver.findElementById("et_login_uid").sendKeys("isdp1");

		
		
		//driver.quit();
		
		
		
	}
	
	
}
