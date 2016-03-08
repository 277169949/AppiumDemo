package com.lieber.tools;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LieberTools {

	/**
	 * 这个方法用来对手机进行截屏
	 * 
	 * @author lieber
	 * @param drivername
	 * @param filename
	 */
	public static void snapshot(TakesScreenshot drivername, String operationName) {
		String filePath = "D:\\AppiumTestScreenshot/";

		File file = new File("D:\\AppiumTestScreenshot");
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			// 目录不存在 ,创建文件夹
			file.mkdir();
		} else {
			// 文件夹目录已经存在
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String filename = df.format(new Date()).toString() + "-" + operationName + ".png";
		filename = filename.replaceAll(":", "-");
		filename = filename.replaceAll(" ", "-");

		File scrFile = drivername.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(filePath + "\\" + filename));
		} catch (IOException e) {
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * 
	 * @param driver
	 * @param elementsId
	 * @return
	 */
	public static boolean isExistsElementsById(AppiumDriver<WebElement> driver, String elementsId) {
		List<WebElement> remindMessageList = driver.findElementsById(elementsId);
		if (remindMessageList != null && remindMessageList.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * This Method for swipe up
	 * 
	 * 
	 * @param driver
	 * @param swipeTime
	 *            滑动时间，单位毫秒
	 */
	public static void swipeToUp(AndroidDriver driver, int swipeTime) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4, swipeTime);
		// wait for page loading
	}

	/**
	 * This Method for swipe down
	 * 
	 * @param driver
	 * @param swipeTime
	 *            滑动时间，单位毫秒
	 */
	public static void swipeToDown(AndroidDriver driver, int swipeTime) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height / 4, width / 2, height * 3 / 4, swipeTime);
		// wait for page loading
	}

	/**
	 * This Method for swipe Left
	 * 
	 * @param driver
	 * @param swipeTime
	 *            滑动时间，单位毫秒
	 */
	public static void swipeToLeft(AndroidDriver driver, int swipeTime) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, swipeTime);
		// wait for page loading
	}

	/**
	 * This Method for swipe Right
	 * 
	 * @param driver
	 * @param swipeTime
	 *            滑动时间，单位毫秒
	 */
	public static void swipeToRight(AndroidDriver driver, int swipeTime) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 4, height / 2, width * 3 / 4, height / 2, swipeTime);
		// wait for page loading
	}

}
