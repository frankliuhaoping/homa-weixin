
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionImplementor;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheTransactionalDataRegion;

public class AbstractReadWriteSpringCacheAccessStrategy<T extends SpringCacheTransactionalDataRegion>
        extends AbstractSpringCacheAccessStrategy<T> {


    public AbstractReadWriteSpringCacheAccessStrategy(T region, SessionFactoryOptions settings) {
        super(region, settings);
    }


    public final Object get(SessionImplementor session, Object key, long txTimestamp) throws CacheException {
        return region.get(key);
    }

    @Override
    public final boolean putFromLoad(
            SessionImplementor session,
            Object key,
            Object value,
            long txTimestamp,
            Object version,
            boolean minimalPutOverride)
                    throws CacheException {
        region.put(key, value);
        return true;
    }


    public final SoftLock lockItem(SessionImplementor session, Object key, Object version) throws CacheException {
        region.remove(key);
        return null;
    }

    public final void unlockItem(SessionImplementor session, Object key, SoftLock lock) throws CacheException {
        region.remove(key);
    }
}
