package cn.cnyirui.homaweixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class VoiceUtils {

	Logger log = Logger.getLogger(VoiceUtils.class);
	/**
	 * amr文件转mp3
	 * @param localPath
	 * @param targetFilePath
	 * @return
	 */
	public boolean amrToMp3(String localPath, String targetFilePath) {  
		InputStream stderr = null;  
		InputStreamReader isReader = null;
		BufferedReader bufferReader = null;
        try {  
            java.lang.Runtime rt = Runtime.getRuntime();  
            String command = "ffmpeg -i " + localPath + " " + targetFilePath;  
  
            log.debug("ffmpeg exec command = " + command);  
  
            Process proc = rt.exec(command);  
            stderr = proc.getErrorStream();  
            isReader = new InputStreamReader(stderr);  
            bufferReader = new BufferedReader(isReader);  
            String line = null;  
            StringBuffer sb = new StringBuffer();  
            while ((line = bufferReader.readLine()) != null)  
                sb.append(line);  
  
            log.debug("ffmpeg Process errorInfo: " + sb.toString());  
  
            int exitVal = proc.waitFor();  
            log.debug("ffmpeg Process exitValue: " + exitVal);  
  
            return true;  
        } catch (Exception e) {  
        	log.debug("ffmpeg exec cmd Exception " + e.toString());  
        } finally{
        	try{
        		if(bufferReader != null){
            		bufferReader.close();
            	}
        		if(isReader != null){
        			isReader.close();
            	}
        		if(stderr != null){
        			stderr.close();
            	}
        	}catch(IOException ex){
        		ex.printStackTrace();
        	}
        }
        return false;  
    }
}
