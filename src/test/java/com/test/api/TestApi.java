package com.test.api;

import java.io.FileInputStream;
import java.util.Properties;

public class TestApi {
	public Properties prop;
	public String excelPath;
	public String host;

	// 构造函数
	public void testAPI() {
		try {
			// 数据流的形式读取配置文件config.properties
			prop = new Properties();
			//FileInputStream fis = new FileInputStream(
					//System.getProperty("user.dir") + "/src/main/resource/config.properties");
			FileInputStream fis = new FileInputStream(
					"D:/eclipse2017/Workspaces/interfacetest/src/main/resource/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		host = prop.getProperty("Host");
		excelPath = prop.getProperty("testData");
	}
}