package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.GeneralDataRegion;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;
import cn.cnyirui.framework.utils.SpringCacheUtils;

public class SpringCacheGeneralDataRegion extends SpringCacheDataRegion implements GeneralDataRegion {


    public SpringCacheGeneralDataRegion(
            SpringCacheAccessStrategyFactory accessStrategyFactory,
            Cache cache, boolean usePrefix,
            Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, properties);
    }

    @Override
    public Object get(SessionImplementor session, Object key) throws CacheException {
        try {
            return SpringCacheUtils.getValue(getCache(), key);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void put(SessionImplementor session, Object key, Object value) throws CacheException {
        try {
            getCache().put(key, value);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void evict(Object key) throws CacheException {
        try {
            getCache().evict(key);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void evictAll() throws CacheException {
        try {
            getCache().clear();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }
}
