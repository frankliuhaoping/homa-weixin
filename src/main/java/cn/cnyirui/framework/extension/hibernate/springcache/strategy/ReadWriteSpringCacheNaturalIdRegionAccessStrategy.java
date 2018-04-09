
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheNaturalIdRegion;


public class ReadWriteSpringCacheNaturalIdRegionAccessStrategy
        extends AbstractReadWriteSpringCacheAccessStrategy<SpringCacheNaturalIdRegion>
        implements NaturalIdRegionAccessStrategy {

    /**
     * Creates a read/write cache access strategy around the given cache region.
     */
    public ReadWriteSpringCacheNaturalIdRegionAccessStrategy(SpringCacheNaturalIdRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    @Override
    public NaturalIdRegion getRegion() {
        return region;
    }

    @Override
    public boolean insert(SessionImplementor session, Object key, Object value) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean afterInsert(SessionImplementor session, Object key, Object value) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean update(SessionImplementor session, Object key, Object value) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public boolean afterUpdate(SessionImplementor session, Object key, Object value, SoftLock lock) throws CacheException {
        region.put(key, value);
        return true;
    }

    @Override
    public Object generateCacheKey(Object[] naturalIdValues, EntityPersister persister, SessionImplementor session) {
        return DefaultCacheKeysFactory.createNaturalIdKey(naturalIdValues, persister, session);
    }

    @Override
    public Object[] getNaturalIdValues(Object cacheKey) {
        return DefaultCacheKeysFactory.getNaturalIdValues(cacheKey);
    }
}
