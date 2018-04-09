
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheEntityRegion;


public class ReadWriteSpringCacheEntityRegionAccessStrategy
        extends AbstractReadWriteSpringCacheAccessStrategy<SpringCacheEntityRegion>
        implements EntityRegionAccessStrategy {

    public ReadWriteSpringCacheEntityRegionAccessStrategy(SpringCacheEntityRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    @Override
    public EntityRegion getRegion() {
        return region;
    }

    @Override
    public boolean insert(SessionImplementor session, Object key, Object value, Object version) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean afterInsert(SessionImplementor session, Object key, Object value, Object version) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean update(SessionImplementor session, Object key, Object value, Object currentVersion, Object previousVersion)
            throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean afterUpdate(SessionImplementor session, Object key, Object value, Object currentVersion, Object previousVersion, SoftLock lock)
            throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public Object generateCacheKey(Object id, EntityPersister persister, SessionFactoryImplementor factory, String tenantIdentifier) {
        return DefaultCacheKeysFactory.createEntityKey(id, persister, factory, tenantIdentifier);
    }

    @Override
    public Object getCacheKeyId(Object cacheKey) {
        return DefaultCacheKeysFactory.getEntityId(cacheKey);
    }
}
