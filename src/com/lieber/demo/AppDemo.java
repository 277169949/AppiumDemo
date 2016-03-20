package com.lieber.demo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.lieber.resources.ConstantsResources;
import com.lieber.tools.LieberTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppDemo {
	private static AppiumDriver driver;

	public static void main(String[] args) throws MalformedURLException {
		// 第一步： 初始化驱动配置以及被测应用
		initDriver();

		// 第二步： 对被测应用进行操作
		System.out.println("开始操作app");

		if (app_login("SIT")) {

			app_selectProject("ISDP   B3对接项目");

			updateBasicData();

		}
		swipeDownRefreshData();
		createTask("site 27");
		// 第三步： 退出应用
		quit();
	}

	/**
	 * 
	 * @param duid要进行任务创建的DUID
	 */
	public static void createTask(String duid) {

		// 先判断是否处于Colect页面
		if (driver.findElementById("android:id/action_bar_title").getText()
				.equals(ConstantsResources.ActionBar_Title_Collect)) {

			driver.findElementById("com.hw.smart.isdacceptance:id/menu_todo_add").click();
			LieberTools.snapshot(driver, "createTask");
			driver.findElementById("com.hw.smart.isdacceptance:id/tv_add_site_task_create").click();

			driver.findElementById("com.hw.smart.isdacceptance:id/actv_task_add_duid").click();

			driver.findElementById("com.hw.smart.isdacceptance:id/actv_task_add_duid").click();

			driver.findElementById("com.hw.smart.isdacceptance:id/actv_task_add_duid").sendKeys(duid);
			TouchAction action = new TouchAction(driver);
			action.press(150, 289).release().perform();
			driver.findElementById("com.hw.smart.isdacceptance:id/forms_textbox_icon_do").click();
			driver.findElementById("android:id/button1").click();
			LieberTools.snapshot(driver, "createTask");
			driver.findElementById("com.hw.smart.isdacceptance:id/btn_task_add_save").click();

		}

	}

	public static void swipeDownRefreshData() {
		LieberTools.swipeToDown((AndroidDriver) driver, 6000);
		LieberTools.snapshot(driver, "swipeToDown");
		LieberTools.swipeToDown((AndroidDriver) driver, 6000);
		LieberTools.snapshot(driver, "swipeToDown");
	}

	public static void initDriver() {

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

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		// 设置app的主包名和主类包

		capabilities.setCapability("appPackage", "com.hw.smart.isdacceptance");
		capabilities.setCapability("appActivity", "com.hw.smart.isdacceptance.provider.activites.WelcomeActivity");
		capabilities.setCapability("appWaitActivity", "com.hw.smart.isdacceptance.provider.activites.LoginActivity");// 初始化AppiumDriver

		try {
			driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("配置成功，驱动初始化成功");

	}

	/**
	 * login
	 */
	public static boolean app_login(String centerServerName) {
		LieberTools.snapshot(driver, "app_launch");
		driver.findElementById("et_login_uid").clear();
		driver.findElementById("et_login_uid").sendKeys("isdp1");

		driver.findElementById("et_login_pwd").clear();
		driver.findElementById("et_login_pwd").sendKeys("0.12345a");

		WebElement el = driver.findElementById("text1");
		el.click();
		List<WebElement> centerServerList = driver.findElementsById("text1");
		for (int i = 0; i < centerServerList.size(); i++) {
			if (centerServerList.get(i).getText().equals(centerServerName)) {
				centerServerList.get(i).click();
				break;
			}
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElementById("btn_login").click();
		// 判断登录是否成功
		String title = driver.findElementById("action_bar_title").getText();// 需要先判断是否存在钙元素
		if (title.equals("ISD-Acceptance")) {
			System.out.println("登录成功，进入项目列表");
			LieberTools.snapshot(driver, "app_login_success");
			return true;
		} else {
			System.out.println("登录失败");
			LieberTools.snapshot(driver, "app_login_fail");
			return false;
		}

	}

	/**
	 * app_selectProject 从登录用户所获取到的项目列表选择项目进入
	 */
	public static void app_selectProject(String projectName) {

		List<WebElement> projectNameList = driver.findElementsById("com.hw.smart.isdacceptance:id/tv_projectCode");

		for (int i = 0; i < projectNameList.size(); i++) {
			if (projectNameList.get(i).getText().equals(projectName)) {
				projectNameList.get(i).click();
				break;
			}
		}

		boolean isExistsElements = LieberTools.isExistsElementsById(driver, "android:id/message");

		if (isExistsElements || driver.findElementById("android:id/action_bar_title").getText() == "Task List") {
			System.out.println("进入项目成功");
			LieberTools.snapshot(driver, "app_selectProject_intoproject");
		}

	}

	/**
	 * 更新版本、更新模板、更新站点
	 */
	public static void updateBasicData() {
		// 判断登录后进入项目是否会有弹出框

		LieberTools.isExistsElementsById(driver, "android:id/message");

		List<WebElement> remindMessageList = driver.findElementsById("android:id/message");
		/**
		 * [1]android:id/button2 Cancel按钮 [2] android:id/button1 OK按钮
		 */
		for (int i = 0; i < remindMessageList.size(); i++) {

			// 更新版本
			if (remindMessageList.get(i).getText().equals(ConstantsResources.TextView_VersionUplate)) {
				LieberTools.snapshot(driver, "updateBasicData_Version");
				// 默认不更新应用,点击取消按钮
				driver.findElementById("android:id/button2").click();
				LieberTools.snapshot(driver, "updateBasicData_Version_after");
			}
			// 更新模板
			if (remindMessageList.get(i).getText().equals(ConstantsResources.TextView_TemplateUplate)) {
				LieberTools.snapshot(driver, "updateBasicData_Template");
				driver.findElementById("android:id/button1").click();
				LieberTools.snapshot(driver, "updateBasicData_Template_updating");
				// 先判断是否出现更新成功的提示，如果出现，则点击地确定，如果没出现，则一直等待

				driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);

				// if (driver.findElementById("android:id/message").getText()
				// .equals(ConstantsResources.TextView_TemplateUplate_Success))
				// {
				LieberTools.snapshot(driver, "updateBasicData_Template_update_success");
				driver.findElementById("android:id/button1").click();
				LieberTools.snapshot(driver, "updateBasicData_Template_update_after");
				// }

			}
			// 更新站点
			if (remindMessageList.get(i).getText().equals(ConstantsResources.TextView_SiteUplate)) {
				LieberTools.snapshot(driver, "updateBasicData_Site");
				driver.findElementById("android:id/button1").click();
				LieberTools.snapshot(driver, "updateBasicData_Site_updating");

				// if (driver.findElementById("android:id/message").getText()
				// .equals(ConstantsResources.TextView_SiteUplate_Success)) {
				LieberTools.snapshot(driver, "updateBasicData_Site_update_success");
				driver.findElementById("android:id/button1").click();
				LieberTools.snapshot(driver, "updateBasicData_Site_update_after");
				// }
			}
		}

	}

	public static void quit() {
		if (driver != null) {
			driver.quit();
			System.out.println("应用关闭！");
		}
		System.out.println("操作结束");
	}
}
