package cn.cnyirui.homaweixin.service.weixin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.weixin.WxSignRecordDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.SignRecord;
import cn.cnyirui.homaweixin.utils.PictureUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

@Service
public class WxSignRecordService extends BaseService<SignRecord> {

	@Resource
	private WxMpService wxMpService;
	@Resource
	private WxMpInMemoryConfigStorage wxMpConfigStorage;
	@Resource

	private WxSignRecordDao wxsignRecordDao;
	@Override
	public BaseDao<SignRecord> getBaseDao() {
		// TODO Auto-generated method stub
		return wxsignRecordDao;
	}

	/**
	 * 获取是否签到的方法
	 */
	public void getSign(Model model, String emloyeeId) {
		Date date = new Date();
		String date0 = DateFormatUtils.format(date, "yyyy-MM-dd");
		String date1 = date0 + " 00:00:00";
		String date2 = date0 + " 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date3 = null;
		Date date4 = null;
		try {
			date3 = sdf.parse(date1);
			date4 = sdf.parse(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 上班签到记录
		List<SignRecord> startSignList = wxsignRecordDao.startWorkSignRecord(date3, date4, emloyeeId);
		model.addAttribute("startSignList", startSignList);

		// 下班签到记录
		List<SignRecord> offSignList = wxsignRecordDao.offWorkSignRecord(date3, date4, emloyeeId);
		model.addAttribute("offSignList", offSignList);

	}

	// 签上班卡
	public ObjectNode signStartWork(String address,String serverid,HttpServletRequest request) throws WxErrorException {
		Employee employee = CurrentUserUtils.getEmployee(false);
		Organization organ = CurrentUserUtils.getOrganization(false);
		SignRecord signRecord = new SignRecord();
		Date date = new Date();
		signRecord.setSignTime(date);
		signRecord.setEmployee(employee);
		signRecord.setSignType(0);
		signRecord.setOrganization(organ);
		signRecord.setAddress(address);
		//System.out.println(serverid);
		
		if(serverid!=null&&serverid!="")//如果media_id不为空
		{
			String ImageUrl=PictureUtils.StreamToImage(wxMpService.getAccessToken(), serverid, request,employee.getName());//从服务器下载图片			
		}
		wxsignRecordDao.save(signRecord);
		ObjectNode objn= JsonUtil.getObjectMapper().createObjectNode();
        objn.put("yes", true);
        String strdate = DateFormatUtils.format(date, "HH:mm:ss");
        objn.put("date", strdate);
		return objn;

	}

	// 签下班卡
	public ObjectNode signOffWork(String address) {
		Employee employee = CurrentUserUtils.getEmployee(false);
		Organization organ = CurrentUserUtils.getOrganization(false);
		SignRecord signRecord = new SignRecord();
		Date date = new Date();
		signRecord.setSignTime(date);
		signRecord.setEmployee(employee);
		signRecord.setSignType(1);
		signRecord.setOrganization(organ);
		signRecord.setAddress(address);
		wxsignRecordDao.save(signRecord);
		
		ObjectNode objn= JsonUtil.getObjectMapper().createObjectNode();
        objn.put("yes", true);
        String strdate = DateFormatUtils.format(date, "HH:mm:ss");
        objn.put("date", strdate);
		return objn;

	}

	// 按月份获取某个员工的当月所有签到记录，默认是现在的月份
	public void getEmployeeSignList(Model model, String month) {

		Employee employee = CurrentUserUtils.getEmployee(false);
		if (month == null || month.equals("")) {
			Date date = new Date();
			String strDate = DateFormatUtils.format(date, "yyyy-MM");
			List<SignRecord> signList = wxsignRecordDao.getEmployeeSignlist(strDate, employee.getId());
			model.addAttribute("signList", signList);

		} else {

			List<SignRecord> signList = wxsignRecordDao.getEmployeeSignlist(month, employee.getId());
			model.addAttribute("signList", signList);

		}
	}

	
	//异步获取员工某个月的签到记录
	public ObjectNode getEmployeeSignJsonList(String month, HttpServletRequest request) {
		Employee employee = CurrentUserUtils.getEmployee(false);
		List<SignRecord> signList = wxsignRecordDao.getEmployeeSignlist(month, employee.getId());
		ArrayNode arrayNode = JsonUtil.getObjectMapper().createArrayNode();
		EntityConfig config = getToObjectNodeEntityConfig(request);
		for (SignRecord sig : signList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			arrayNode.add(entityToObjectNode(sig, config));
		}
		ObjectNode obNode = JsonUtil.getObjectMapper().createObjectNode();
		obNode.putArray("list").addAll(arrayNode);
		return obNode;
	}
	
	public List<SignRecord> getEmployeeSignList(int year,int month,String eid){
		return wxsignRecordDao.getEmployeeSignlist(year +"-"+ month, eid);
	}
}
