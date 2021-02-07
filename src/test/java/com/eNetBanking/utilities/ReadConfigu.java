package com.eNetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigu {
	
	Properties pro;
	
	public ReadConfigu() {
		File src=new File("./Configurations\\config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is"+e.getMessage());
		}
	}
	
	public String getApplicationUrl() {
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername() {
		String username=pro.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password=pro.getProperty("password");
		return password;
	}
	
	public String getchromepath() {
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	
	public String getiepath() {
		String iepath=pro.getProperty("iepath");
		return iepath;
	}
	
	public String getfirefoxpath() {
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}

	
}
