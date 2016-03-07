package com.lieber.tools;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

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

}
