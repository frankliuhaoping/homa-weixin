package cn.cnyirui.framework.model.po.standardsetup;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

@Entity
@Table(name = "STANDARD_SETUP_TEST")
@DynamicInsert
@DynamicUpdate
public class StandardSetupTest extends StandardSetupEntity {

	private static final long serialVersionUID = -8622577952217111039L;

}
