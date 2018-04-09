package cn.cnyirui.framework.dao.weixin;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.weixin.WeiXinQrCode;

/**
 * 微信二维码
 * 
 * @author pengzhihua
 *
 */
public interface WeiXinQrCodeDao extends BaseDao<WeiXinQrCode> {

	/**
	 * 按订单ID查找
	 * 
	 * @param salesOrderId
	 * @param pageable
	 * @return
	 */
	@Query("select q from SalesOrder as s join s.weiXinQrCode as q where s.id = ?1")
	List<WeiXinQrCode> findBySalesOrderId(String salesOrderId, Pageable pageable);

	/**
	 * 按场景ID查找
	 * 
	 * @param sceneId
	 * @return
	 */
	@Query("select q from WeiXinQrCode as q where q.sceneId = ?1")
	List<WeiXinQrCode> findBySceneId(Long sceneId, Pageable pageable);
}
