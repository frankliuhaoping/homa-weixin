package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Map;
import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.Region;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;
import cn.cnyirui.framework.utils.SpringCacheUtils;
import net.sf.ehcache.util.Timestamper;

public class SpringCacheDataRegion implements Region {
    private static final String CACHE_LOCK_TIMEOUT_PROPERTY = "springcache.hibernate.cache_lock_timeout";
    private static final int DEFAULT_CACHE_LOCK_TIMEOUT = 60000;

    private Cache cache;
    private boolean usePrefix;
    private final SpringCacheAccessStrategyFactory accessStrategyFactory;
    private final int cacheLockTimeout;


    SpringCacheDataRegion(SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, Properties properties) {
        this.accessStrategyFactory = accessStrategyFactory;
        this.cache = cache;
        this.usePrefix = usePrefix;
        final String timeout = properties.getProperty(
                CACHE_LOCK_TIMEOUT_PROPERTY,
                Integer.toString(DEFAULT_CACHE_LOCK_TIMEOUT));
        this.cacheLockTimeout = Timestamper.ONE_MS * Integer.decode(timeout);
    }

    public Cache getCache() {
        return cache;
    }

    protected SpringCacheAccessStrategyFactory getAccessStrategyFactory() {
        return accessStrategyFactory;
    }

    @Override
    public String getName() {
        return getCache().getName();
    }

    @Override
    public void destroy() throws CacheException {
        try {
            getCache().clear();
        } catch (IllegalStateException e) {
            // When Spring and Hibernate are both involved this will happen in normal shutdown
            // operation.
            // Do not throw an exception, simply log this one.
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public long getSizeInMemory() {
        try {
            return SpringCacheUtils.dbSize(cache);
        } catch (Throwable t) {
            return -1;
        }
    }

    @Override
    public long getElementCountInMemory() {
        try {
            return SpringCacheUtils.getKeys(cache, usePrefix).size();
        } catch (Exception ce) {
            throw new CacheException(ce);
        }
    }

    @Override
    public long getElementCountOnDisk() {
        return getElementCountInMemory();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map toMap() {
        try {
            return SpringCacheUtils.toMap(cache, usePrefix);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public long nextTimestamp() {
        return System.nanoTime();
    }

    @Override
    public int getTimeout() {
        return cacheLockTimeout;
    }

    @Override
    public boolean contains(Object key) {
        return SpringCacheUtils.getKeys(cache, usePrefix).contains(key);
    }
}
