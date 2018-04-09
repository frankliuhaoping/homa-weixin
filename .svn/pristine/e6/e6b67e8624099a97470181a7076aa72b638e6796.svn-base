package cn.cnyirui.homaweixin.service.backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.ChatContentDao;
import cn.cnyirui.homaweixin.model.po.ChatContent;


@Service
public class ChatContentService extends BaseService<ChatContent> {
    
    
	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");
	
	@Resource
	private ChatContentDao chatContentDao;
	
	@Override
	public BaseDao<ChatContent> getBaseDao() {
		// TODO Auto-generated method stub
		return chatContentDao;
	}
	@Override
	public ObjectNode entityToObjectNode(ChatContent entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		objectNode.put("senderName", entity.getSender().getName());
		Date date=new Date(entity.getSendTime());
	    String lastTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	    objectNode.put("sendTime", lastTime);
		return objectNode;
	}
	
	

	
	public ObjectNode exportExcel(HttpServletRequest request, HttpServletResponse response,String hhid) {
		
       //获取域名
		StringBuffer stringBuffer = request.getRequestURL();
		String baseUrl = stringBuffer
		        .delete(stringBuffer.length() - request.getRequestURI().length(), stringBuffer.length())
		        .append(request.getServletContext().getContextPath()).toString();
		
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "聊天记录_" + System.currentTimeMillis() + ".xlsx";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		try {
			// 输出流
			OutputStream os = new FileOutputStream(filePath);
			// 工作区
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("聊天记录");
			
			XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("姓名");
  			// 给这一行的第一列赋值
			
			row1.createCell(1).setCellValue("聊天内容");
			row1.createCell(2).setCellValue("发送时间");
			row1.createCell(3).setCellValue("内容类型");
		
			
			
			
			List<ChatContent> chatContent1 =chatContentDao.findChatContentByHhId(hhid);
			
			for (ChatContent chatContent : chatContent1) {
				// 创建第一个sheet
				// 生成第2行
				XSSFRow row = sheet.createRow(chatContent1.indexOf(chatContent)+1);
				// 给这一行的第一列赋值
				row.createCell(0).setCellValue(chatContent.getSender().getName());
	  			// 给这一行的第2列赋值
				if(chatContent.getContentType()==2){
					//图片设置超链接
					 //单元格
					 XSSFCell cell=row.createCell(1);
					 cell.setCellValue(chatContent.getContent());
					 cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
					 cell.setCellFormula("HYPERLINK(\"" + baseUrl+chatContent.getContent()+ "\",\"" + "图片"+ "\")");
					 XSSFCellStyle linkStyle = wb.createCellStyle();
					 XSSFFont cellFont= wb.createFont();
					 cellFont.setUnderline((byte) 1);
					 cellFont.setColor(HSSFColor.BLUE.index);
					 linkStyle.setFont(cellFont);
					 cell.setCellStyle(linkStyle);
				}else{
					row.createCell(1).setCellValue(chatContent.getContent());
				}
				
				Date date=new Date(chatContent.getSendTime());
			    String lastTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
				
				row.createCell(2).setCellValue(lastTime);
				row.createCell(3).setCellValue(chatContent.getContentType());
				//System.out.println(i);
			}
			// 写文件
			wb.write(os);
			// 关闭输出流
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		download(filePath, response);
		
		return null;		
	}
	
	private void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 根据会话ID获取聊天内容
	 * @return jiny 2015-11-27
	 */
	public ObjectNode getChatContent(String hhid){
		List<ObjectNode> objectNodeList= new ArrayList<ObjectNode>();
		List<ChatContent> chatContent1 =chatContentDao.findChatContentByHhId(hhid);
		for (ChatContent chatContent : chatContent1) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("content", chatContent.getContent());
			objectNode.put("contentType", chatContent.getContentType());
			Date date=new Date(chatContent.getSendTime());
		    String lastTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
			objectNode.put("sendTime",lastTime);
			objectNode.put("voiceTime", chatContent.getVoiceTime());
			objectNode.put("chatSessionId", chatContent.getChatSession().getId());
			objectNode.put("senderName", chatContent.getSender().getName());
			objectNodeList.add(objectNode);
		}
		
		ObjectNode Node = JsonUtil.getObjectMapper().createObjectNode();
		Node.putArray("rows").addAll(objectNodeList);
		Node.put("total", objectNodeList.size());
		return Node;
	}
	
	

}
