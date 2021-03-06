package com.dje.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	//这个类作为所有接口请求的父类，加载读取properties文件
	public Properties prop;
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_201=201;
	public int RESPONSE_STATUS_CODE_404=404;
	public int RESPONSE_STATUS_CODE_500=500;
	//构造函数
	public TestBase() {
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
					"/src/main/java/com/dje/config/config.properties");
			
				prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}			
	}


