
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UnlockTest {
	private AppiumDriver driver;

	public UnlockTest() {
	}

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.settings");
		capabilities.setCapability("appActivity", ".Settings");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		// driver.sendKeyEvent(26); // 按power键锁屏
		// driver.sendKeyEvent(26); // 按power键点亮屏幕
		driver.tap(1, 540, 960, 500); // 触摸屏幕中间，显示图案解锁框
		// 图案解锁
		TouchAction action = new TouchAction(driver);
		action.press(215, 870).moveTo(540, 870).moveTo(862, 870).moveTo(540, 1195).moveTo(215, 1195).moveTo(215, 1518)
				.moveTo(540, 1518).moveTo(862, 1518).moveTo(862, 1195).release().perform();
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}