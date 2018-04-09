package cn.cnyirui.framework.service.standardsetup;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.common.CommonDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.standardsetup.StandardSetupConfigDao;
import cn.cnyirui.framework.model.po.base.StandardSetupEntity;
import cn.cnyirui.framework.model.po.standardsetup.StandardSetupConfig;
import cn.cnyirui.framework.service.BaseService;

@Service
@Scope("request")
public class StandardSetupService extends BaseService<StandardSetupEntity> {


    @Resource
    private CommonDao commonDao;
    @Resource
    private StandardSetupConfigDao standardSetupConfigDao;

    @Override
    public void delete(String[] ids) {
        commonDao.delete(getEntityClass().getSimpleName(), ids);
    }

    @Override
    public Page<StandardSetupEntity> findAll(Searchable searchable) {
        return commonDao.findAll(getEntityClass().getSimpleName(), searchable);
    }

    @Override
    public StandardSetupEntity findOne(String id) {
        return commonDao.findOne(getEntityClass().getSimpleName(), id);
    }

    @Override
    public StandardSetupEntity save(StandardSetupEntity entity) {
        return commonDao.save(entity);
    }

    public StandardSetupEntity findOneByName(String name) {
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchFilter("name", SearchOperator.eq, name);

        Page<StandardSetupEntity> page = findAll(searchable);
        if (page.hasContent()) {
            return (StandardSetupEntity) page.getContent().get(0);
        }
        return null;
    }

    public StandardSetupConfig findByEntityClassName(String entityClassName) {
        entityClassName = StringUtils.capitalize(entityClassName);
        List<StandardSetupConfig> list = standardSetupConfigDao.findByEntityClassName("%" + entityClassName);
        if (!list.isEmpty()) {
            for (StandardSetupConfig standardSetupConfig : list) {
                String className = StringUtils.substringAfterLast(standardSetupConfig.getEntityClassName(), ".");
                if (StringUtils.isEmpty(className)) {
                    className = standardSetupConfig.getEntityClassName();
                }
                if (entityClassName.equalsIgnoreCase(className)) {
                    return standardSetupConfig;
                }
            }
        }
        return null;
    }

    @Override
    public BaseDao<StandardSetupEntity> getBaseDao() {
        return null;
    }

}
