package cn.cnyirui.homaweixin.dao.weixin;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.SignRecord;

public interface WxSignRecordDao extends BaseDao<SignRecord> {
	
	/**
	 * jiny
	 * 查询某天所有上班签到记录
	 * @param date
	 * @return
	 */
	@Query("from SignRecord s where s.signTime >= ?1 and s.signTime<=?2 and s.signType=0 and s.employee.id=?3 order by s.signTime asc")
	List<SignRecord> startWorkSignRecord(Date date,Date date3,String employeeId);
	
	/**
	 * jiny
	 * 查询某天所有下班签到记录
	 * @param date
	 * @return
	 */
	@Query("from SignRecord s where s.signTime >= ?1 and s.signTime<=?2 and s.signType=1 and s.employee.id=?3 order by s.signTime desc")
	List<SignRecord> offWorkSignRecord(Date date,Date date1,String employeeId);
	
	//按月份获取某个员工的当月所有签到记录，默认是现在的月份
	@Query("from SignRecord s where to_char(s.signTime,'yyyy-MM') = ?1 and s.employee.id=?2 order by s.signTime desc")
	List<SignRecord> getEmployeeSignlist(String month,String employeeId);
	
	
}
