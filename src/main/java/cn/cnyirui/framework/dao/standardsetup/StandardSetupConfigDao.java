package cn.cnyirui.framework.dao.standardsetup;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.standardsetup.StandardSetupConfig;

/**
 * 执行标准配置
 * 
 * @author pengzhihua
 *
 */
public interface StandardSetupConfigDao extends BaseDao<StandardSetupConfig> {

	/**
	 * 按类名查找，模糊匹配
	 * 
	 * @param entityClassName
	 * @return
	 */
	@Query("select s from StandardSetupConfig as s where s.entityClassName like ?1")
	List<StandardSetupConfig> findByEntityClassName(String entityClassName);

}
