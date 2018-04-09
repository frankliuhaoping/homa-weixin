package cn.cnyirui.homaweixin.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 获取properties的值
 * 
 * @author Administrator
 * 
 */
public class PropertiesUtils {

	private static final String WEB_PUSH_CONFIG_FILE_NAME = "webPush.properties";
	
	
	public static final String getValueByKeyFromWebPushConfig(String key) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(WEB_PUSH_CONFIG_FILE_NAME);
		InputStream in;
		Properties p = new Properties();
		try {
			in = url.openStream();
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

}
