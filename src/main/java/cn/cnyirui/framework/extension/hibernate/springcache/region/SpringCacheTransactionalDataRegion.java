package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.TransactionalDataRegion;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;
import cn.cnyirui.framework.utils.SpringCacheUtils;

public class SpringCacheTransactionalDataRegion extends SpringCacheDataRegion implements TransactionalDataRegion {
    private final SessionFactoryOptions settings;
    protected final CacheDataDescription metadata;


    SpringCacheTransactionalDataRegion(
            SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, SessionFactoryOptions settings,
            CacheDataDescription metadata, Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, properties);
        this.settings = settings;
        this.metadata = metadata;
    }


    public SessionFactoryOptions getSettings() {
        return settings;
    }

    @Override
    public boolean isTransactionAware() {
        return false;
    }

    @Override
    public CacheDataDescription getCacheDataDescription() {
        return metadata;
    }


    public final Object get(Object key) {
        try {
            return SpringCacheUtils.getValue(getCache(), key);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    public final void put(Object key, Object value) throws CacheException {
        try {
            getCache().put(key, value);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    public final void remove(Object key) throws CacheException {
        try {
            getCache().evict(key);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    public final void clear() throws CacheException {
        try {
            getCache().clear();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }
}
