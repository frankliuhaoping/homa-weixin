package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;

public class SpringCacheNaturalIdRegion extends SpringCacheTransactionalDataRegion implements NaturalIdRegion {

    public SpringCacheNaturalIdRegion(SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, SessionFactoryOptions settings, CacheDataDescription metadata,
            Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, settings, metadata, properties);
    }

    @Override
    public NaturalIdRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return getAccessStrategyFactory().createNaturalIdRegionAccessStrategy(this, accessType);
    }

}
