package cn.cnyirui.framework.extension.hibernate.springcache;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheCollectionRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheEntityRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheNaturalIdRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheQueryResultsRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheTimestampsRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;
import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactoryImpl;

public abstract class AbstractSpringCacheRegionFactory implements RegionFactory {
    private static final long serialVersionUID = -9041683983199652127L;
    protected volatile CacheManager cacheManager;
    protected boolean usePrefix;
    protected SessionFactoryOptions settings;

    protected final SpringCacheAccessStrategyFactory accessStrategyFactory = new SpringCacheAccessStrategyFactoryImpl();

    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        return true;
    }

    @Override
    public long nextTimestamp() {
        return System.nanoTime();
    }

    @Override
    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata)
            throws CacheException {
        return new SpringCacheEntityRegion(accessStrategyFactory, getCache(regionName), usePrefix, settings, metadata, properties);
    }

    @Override
    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        return new SpringCacheNaturalIdRegion(accessStrategyFactory, getCache(regionName), usePrefix, settings, metadata, properties);
    }

    @Override
    public CollectionRegion buildCollectionRegion(String regionName, Properties properties, CacheDataDescription metadata) throws CacheException {
        return new SpringCacheCollectionRegion(accessStrategyFactory, getCache(regionName), usePrefix, settings, metadata, properties);
    }

    @Override
    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
        return new SpringCacheQueryResultsRegion(accessStrategyFactory, getCache(regionName), usePrefix, properties);
    }

    @Override
    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
        return new SpringCacheTimestampsRegion(accessStrategyFactory, getCache(regionName), usePrefix, properties);
    }

    private Cache getCache(String name) throws CacheException {
        try {
            return cacheManager.getCache(name);
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }


    public AccessType getDefaultAccessType() {
        return AccessType.READ_WRITE;
    }
}
