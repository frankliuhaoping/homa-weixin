
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheNaturalIdRegion;
import cn.cnyirui.framework.utils.SpringCacheUtils;


public class TransactionalSpringCacheNaturalIdRegionAccessStrategy
        extends AbstractSpringCacheAccessStrategy<SpringCacheNaturalIdRegion>
        implements NaturalIdRegionAccessStrategy {


    private final Cache cache;

    public TransactionalSpringCacheNaturalIdRegionAccessStrategy(SpringCacheNaturalIdRegion region, Cache cache, SessionFactoryOptions settings) {
        super(region, settings);
        this.cache = cache;
    }

    @Override
    public boolean afterInsert(SessionImplementor session, Object key, Object value) {
        return false;
    }

    @Override
    public boolean afterUpdate(SessionImplementor session, Object key, Object value, SoftLock lock) {
        return false;
    }

    @Override
    public Object get(SessionImplementor session, Object key, long txTimestamp) throws CacheException {
        return SpringCacheUtils.getValue(cache, key);
    }

    @Override
    public NaturalIdRegion getRegion() {
        return region;
    }

    @Override
    public boolean insert(SessionImplementor session, Object key, Object value) throws CacheException {
        cache.put(key, value);
        return true;
    }

    @Override
    public SoftLock lockItem(SessionImplementor session, Object key, Object version) throws CacheException {
        cache.evict(key);
        return null;
    }

    @Override
    public boolean putFromLoad(
            SessionImplementor session, Object key,
            Object value,
            long txTimestamp,
            Object version,
            boolean minimalPutOverride) throws CacheException {
        if (minimalPutOverride && SpringCacheUtils.getValue(cache, key) != null) {
            return false;
        }
        cache.put(key, value);
        return true;
    }


    @Override
    public void remove(SessionImplementor session, Object key) throws CacheException {
        cache.evict(key);
    }

    @Override
    public void unlockItem(SessionImplementor session, Object key, SoftLock lock) throws CacheException {
        cache.evict(key);
    }

    @Override
    public boolean update(SessionImplementor session, Object key, Object value) throws CacheException {
        cache.put(key, value);
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
