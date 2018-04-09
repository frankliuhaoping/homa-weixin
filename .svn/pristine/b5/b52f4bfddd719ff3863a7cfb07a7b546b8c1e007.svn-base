package cn.cnyirui.homaweixin.dao.backend;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.SalesOrder;

/**
 * 员工
 * 
 * @author liumuya
 *
 */
public interface SalesOrderDao extends BaseDao<SalesOrder> {
	@Query("select s from SalesOrder s where s.saler.id=?1 and s.id=?2")
	public SalesOrder findOneBySalerIdAndOrderId(String salerId, String orderId);

	@Query("from SalesOrder s where s.saler.id=?1 and (s.customerName like (?2) or s.customerTel like ( ?2) or s.OrderNoN like (?2))")
	Page<SalesOrder> findOrdersByTypeAll(String salerId, String wod, Pageable pageable);

	/**
	 * 按微信二维码的场景ID查找
	 * 
	 * @param sceneId
	 * @return
	 */
	@Query("select s from SalesOrder as s join s.weiXinQrCode as q where q.sceneId = ?1")
	List<SalesOrder> findByWeiXinQrCodeSceneId(Long sceneId, Pageable pageable);

	@Query("select s from SalesOrder s where s.saler.id=?1 and s.salesTime between ?2 and ?3 order by s.salesTime desc")
	public List<SalesOrder> findOrderByMonth(String employeeId, Date fdate, Date ldate);

	@Query("from SalesOrder s where s.saler.id=?1 and (s.salesTime between ?3 and ?4) and  (s.customerName like (?2) or s.customerTel like ( ?2) or s.OrderNoN like (?2))")
	public Page<SalesOrder> findOrdersByTime(String salerId, String keyWord, Date fdate, Date ldate,Pageable pageable);

	/*@Query("select s from SalesOrder s where s.")
	public Page<SalesOrder> getCountOfDepartment(String callId, String eid);*/

}
