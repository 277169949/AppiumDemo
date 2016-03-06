import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AppDemo {
	private static AppiumDriver driver;

	public static void main(String[] args) throws MalformedURLException {
		// 第一步： 初始化驱动配置以及被测应用
		initDriver();

		// 第二步： 对被测应用进行操作
		testAPP();

		// 第三步： 退出应用
		// quit();
	}

	public static void initDriver() throws MalformedURLException {

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

		capabilities.setCapability("unicodeKeyboard", "True"); // for input
																// Chinese
		capabilities.setCapability("resetKeyboard", "True");

		// 设置app的主包名和主类包

		capabilities.setCapability("appPackage", "com.hw.smart.isdacceptance");
		capabilities.setCapability("appActivity", "com.hw.smart.isdacceptance.provider.activites.WelcomeActivity");
		capabilities.setCapability("appWaitActivity", "com.hw.smart.isdacceptance.provider.activites.LoginActivity");// 初始化AppiumDriver

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("配置成功，驱动初始化成功");
	}

	public static void testAPP() {
		System.out.println("开始操作app");
		driver.findElementById("et_login_uid").clear();
		driver.findElementById("et_login_uid").sendKeys("isdp1");
		driver.findElementById("et_login_pwd").clear();
		driver.findElementById("et_login_pwd").sendKeys("0.12345a");

		WebElement el = driver.findElementById("text1");
		el.click();
		List<WebElement> textFieldsList = driver.findElementsById("text1");
		textFieldsList.get(1).click();
		driver.findElementById("btn_login").click();
		// 判断登录是否成功
		String title = driver.findElementById("action_bar_title").getText();// 需要先判断是否存在钙元素
		if (title.equals("ISD-Acceptance")) {
			System.out.println("登录成功，进入项目列表");

		} else {
			System.out.println("登录失败");
		}
		System.out.println("操作结束");
	}

	public static void getScreenshot() {

		File screen = driver.getScreenshotAs(OutputType.FILE);
		File screenFile = new File("d:\\screen.png");
		try {
			FileUtils.copyFile(screen, screenFile); // commons-io-2.0.1.jar中的api
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void quit() {
		if (driver != null) {
			driver.quit();
			System.out.println("应用关闭！");
		}

	}
}
