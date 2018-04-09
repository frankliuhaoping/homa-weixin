package cn.cnyirui.framework.model.po.standardsetup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.BaseEntity;

@Entity
@Table(name = "STANDARD_SETUP_CONFIG")
@DynamicInsert
@DynamicUpdate
public class StandardSetupConfig extends BaseEntity {
    private static final long serialVersionUID = 4032073388635088867L;

    /**
     * 实体类类名(建议包括包名)
     */
    @Column(length = 300)
    private String entityClassName;
    /**
     * 实体类描述
     */
    @Column(length = 100)
    private String entityClassDesc;
    /**
     * 显示code字段
     */
    @Column
    private Boolean displayCode = Boolean.FALSE;
    /**
     * 显示name字段
     */
    @Column
    private Boolean displayName = Boolean.TRUE;
    /**
     * 显示remark字段
     */
    @Column
    private Boolean displayRemark = Boolean.TRUE;

    /**
     * 实体类类名(建议包括包名)
     * 
     * @return entityClassName 实体类类名(建议包括包名)
     */
    public String getEntityClassName() {
        return entityClassName;
    }

    /**
     * 实体类类名(建议包括包名)
     * 
     * @param entityClassName 实体类类名(建议包括包名)
     */
    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    /**
     * 实体类描述
     * 
     * @return 实体类描述
     */
    public String getEntityClassDesc() {
        return entityClassDesc;
    }

    /**
     * 实体类描述
     * 
     * @param 实体类描述
     */
    public void setEntityClassDesc(String entityClassDesc) {
        this.entityClassDesc = entityClassDesc;
    }

    /**
     * 显示code字段
     * 
     * @return displayCode 显示code字段
     */
    public Boolean getDisplayCode() {
        return displayCode;
    }

    /**
     * 显示code字段
     * 
     * @param displayCode 显示code字段
     */
    public void setDisplayCode(Boolean displayCode) {
        this.displayCode = displayCode;
    }

    /**
     * 显示name字段
     * 
     * @return displayName 显示name字段
     */
    public Boolean getDisplayName() {
        return displayName;
    }

    /**
     * 显示name字段
     * 
     * @param displayName 显示name字段
     */
    public void setDisplayName(Boolean displayName) {
        this.displayName = displayName;
    }

    /**
     * 显示remark字段
     * 
     * @return displayRemark 显示remark字段
     */
    public Boolean getDisplayRemark() {
        return displayRemark;
    }

    /**
     * 显示remark字段
     * 
     * @param displayRemark 显示remark字段
     */
    public void setDisplayRemark(Boolean displayRemark) {
        this.displayRemark = displayRemark;
    }

    @Transient
    public String getCRUDUrl() {
        String entityClassName = StringUtils.substringAfterLast(getEntityClassName(), ".");
        if (StringUtils.isEmpty(entityClassName)) {
            entityClassName = getEntityClassName();
        }
        return getCRUDUrl(entityClassName);
    }

    @Transient
    public static String getCRUDUrl(String entityClassName) {
        String url = "/standardSetup/%s/list";
        return String.format(url, StringUtils.uncapitalize(entityClassName));
    }

}
