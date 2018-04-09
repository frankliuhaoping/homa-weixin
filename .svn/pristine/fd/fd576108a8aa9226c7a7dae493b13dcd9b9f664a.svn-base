package cn.cnyirui.homaweixin.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


/**
 * 图片下载工具
 * @author Administrator
 *
 */
public class PictureUtils {
    
/**
 * 转换为图片
 * @param token
 * @param serverid
 * @param request
 * @return
 */
	public static String StreamToImage(String token,String serverid,HttpServletRequest request,String name)
	{
		String url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		url = url.replace("ACCESS_TOKEN", token).replace( 
			      "MEDIA_ID", serverid);//替换
		HttpURLConnection conn = null; 
		  try { 
		    URL requesturl = new URL(url); //新建连接
		    conn = (HttpURLConnection) requesturl.openConnection(); //打开链接额
		    conn.setDoInput(true); 
		    conn.setRequestMethod("GET"); //get请求
		    conn.setConnectTimeout(30000); //连接超时
		    conn.setReadTimeout(30000); //读取超时
		    BufferedInputStream bis = new BufferedInputStream( 
		        conn.getInputStream()); //打开流
		    ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
		    byte[] buff = new byte[100];  
		    int rc = 0;  
		    while ((rc = bis.read(buff, 0, 100)) > 0) {  
		      swapStream.write(buff, 0, rc);  
		    }  
		    byte[] filebyte = swapStream.toByteArray(); 
		    Date now = new Date(); 
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
		    SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		    String locationUrl= "D:"+request.getContextPath()+"/signImage/"+dateFormat.format(now);
		    File dirFile = new File(locationUrl);//存储目录
            if (!dirFile.exists()) {
                dirFile.mkdirs();//目录不存在就创建
                System.out.println(dirFile);
            }
		    String locationImageUrl=locationUrl+"/"+name+dateFormats.format(now)+".jpg";//保存路径
		    File imageFile = new File(locationImageUrl);
			//创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			//写入数据
			outStream.write(filebyte);
			//关闭输出流
			outStream.close();
		    //return PictureStore.getInstance().getImageServerUrl() + PictureStore.getInstance().store(filebyte); 
		  } catch (Exception e) { 
		    e.printStackTrace(); 
		  } finally { 
		    if(conn != null){ 
		      conn.disconnect(); 
		    } 
		  } 
		  return ""; 
	}
}
