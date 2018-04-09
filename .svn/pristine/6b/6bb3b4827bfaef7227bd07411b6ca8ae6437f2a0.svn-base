package cn.cnyirui.framework.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.base.Sequenceable;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.utils.EntityUtils;
import cn.cnyirui.framework.utils.ReflectUtils;

/**
 * 抽象service层基类 提供一些简便方法
 */
public abstract class BaseService<T extends BaseEntity> {
    protected Class<T> entityClass;

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public BaseService() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
    }

    public T newEntity() {
        try {
            return getEntityClass().newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + getEntityClass(), e);
        }
    }

    /**
     * 为默认实现提供基本 DAO 操作
     * 
     * @return
     */
    public abstract BaseDao<T> getBaseDao();

    /**
     * toObjectNode方法的配置信息
     * 
     * @param request
     * @return
     */
    public EntityConfig getToObjectNodeEntityConfig(HttpServletRequest request) {
        return EntityUtils.defaultToObjectNodeEntityConfig(request);
    }

    /**
     * 实体类转jackson ObjectNode
     * 
     * @param entity
     * @return
     */
    public ObjectNode entityToObjectNode(T entity, EntityConfig config) {
        ObjectNode objectNode = EntityUtils.toObjectNode(entity, config);
        return objectNode;
    }

    /**
     * 保存单个实体
     */
    public T save(T entity) {
        return getBaseDao().save(entity);
    }

    public T saveAndFlush(T entity) {
        return getBaseDao().saveAndFlush(entity);
    }

    /**
     * copyEntity方法的配置信息
     * 
     * @return
     */
    public EntityConfig getCopyEntityConfig() {
        return EntityUtils.defaultCopyEntityConfig();
    }

    /**
     * 保存页面数据
     * 
     * @param action
     * @param pageModel
     * @param request
     * @return
     */
    public T savePageModel(String action, T pageModel, HttpServletRequest request) {
        T toSavePageModel = null;
        if (pageModel.isNew()) {
            toSavePageModel = newEntity();
        } else {
            toSavePageModel = findOne(pageModel.getId());
            if (toSavePageModel == null) {
                toSavePageModel = newEntity();
            }
        }
        EntityUtils.copyEntity(pageModel, toSavePageModel, getCopyEntityConfig(), request);
        return doSavePageModel(action, pageModel, toSavePageModel, request);
    }

    /**
     * 保存页面数据
     * 
     * @param action
     * @param pageModel
     * @param toSavePageModel 保存的Entity
     * @param request
     * @return
     */
    @Transactional
    public T doSavePageModel(String action, T pageModel, T toSavePageModel, HttpServletRequest request) {
        return save(toSavePageModel);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */

    public void delete(String id) {
        getBaseDao().delete(id);
    }

    /**
     * 删除实体
     *
     * @param t 实体
     */
    public void delete(T entity) {
        getBaseDao().delete(entity);
    }

    /**
     * 根据主键删除相应实体
     *
     * @param ids 主鍵
     */
    public void delete(String[] ids) {
        getBaseDao().delete(ids);
    }

    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public T findOne(String id) {
        return getBaseDao().findOne(id);
    }

    /**
     * 查询所有实体
     *
     * @return
     */
    public List<T> findAll() {
        return getBaseDao().findAll();
    }

    /**
     * 按照顺序查询所有实体
     *
     * @param sort
     * @return
     */
    public List<T> findAll(Sort sort) {
        return getBaseDao().findAll(sort);
    }

    /**
     * 分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return
     */
    public Page<T> findAll(Pageable pageable) {
        return getBaseDao().findAll(pageable);
    }

    /**
     * 按条件分页并排序查询实体
     *
     * @param searchable 条件
     * @return
     */
    public Page<T> findAll(Searchable searchable) {
        return getBaseDao().findAll(searchable);
    }

    /**
     * 实体是否存在
     *
     * @param id 主键
     * @return 存在 返回true，否则false
     */
    public boolean exists(String id) {
        return getBaseDao().exists(id);
    }

    /**
     * 
     * @param idList
     * @param swapSeq false 重新设置值，true 交换值
     */
    @Transactional
    public void sequence(List<String> idList, boolean swapSeq) {
        if (!swapSeq) {
            for (String id : idList) {
                T t = getBaseDao().findOne(id);
                if (!Sequenceable.class.isAssignableFrom(t.getClass())) {
                    return;
                }
                ((Sequenceable) t).setDefaultSeq();
                getBaseDao().save(t);
            }
        } else {
            List<T> list = getBaseDao().findAll(idList);
            if (list.size() == 2) {
                if (Sequenceable.class.isAssignableFrom(list.get(0).getClass())) {
                    ((Sequenceable) list.get(0)).swapSeq((Sequenceable) list.get(1));
                    getBaseDao().save(list);
                }
            }
        }
    }
}
