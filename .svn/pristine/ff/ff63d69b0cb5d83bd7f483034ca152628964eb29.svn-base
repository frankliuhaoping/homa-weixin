package cn.cnyirui.homaweixin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @category spring 上传组件
 * @author kenshinhu
 */

public class Base64Utils {
	/**
	 * 保存图片byte[]到文件
	 * 
	 * @category
	 *           @author eric
	 * @date 2015年1月20日 上午11:26:41
	 * @param imageBytes
	 * @param path
	 * @return
	 */
	public static String saveImage(byte[] imageBytes, String path) {

		String lastFileName = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";

		String fullPath = path + "/" + lastFileName;

		File file = new File(fullPath);

		FileOutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(imageBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lastFileName;
	}

	public static byte[] decode(String s) {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = null;
		try {
			bytes = decoder.decodeBuffer(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile
	 * @return
	 */
	public static String imgToBase64(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
}
