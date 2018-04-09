
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionImplementor;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheTransactionalDataRegion;


abstract class AbstractSpringCacheAccessStrategy<T extends SpringCacheTransactionalDataRegion> {

    protected final T region;
    protected final SessionFactoryOptions settings;

    AbstractSpringCacheAccessStrategy(T region, SessionFactoryOptions settings) {
        this.region = region;
        this.settings = settings;
    }

    protected SessionFactoryOptions settings() {
        return settings;
    }


    public final boolean putFromLoad(SessionImplementor session, Object key, Object value, long txTimestamp, Object version) throws CacheException {
        return putFromLoad(session, key, value, txTimestamp, version, settings.isMinimalPutsEnabled());
    }


    public abstract boolean putFromLoad(SessionImplementor session, Object key, Object value, long txTimestamp, Object version, boolean minimalPutOverride)
            throws CacheException;


    public final SoftLock lockRegion() {
        return null;
    }


    public final void unlockRegion(SoftLock lock) throws CacheException {
        region.clear();
    }

    public void remove(SessionImplementor session, Object key) throws CacheException {}


    public final void removeAll() throws CacheException {
        region.clear();
    }


    public final void evict(Object key) throws CacheException {
        region.remove(key);
    }


    public final void evictAll() throws CacheException {
        region.clear();
    }
}
