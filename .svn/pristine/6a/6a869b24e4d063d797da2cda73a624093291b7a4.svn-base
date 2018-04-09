package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;

public class SpringCacheCollectionRegion extends SpringCacheTransactionalDataRegion implements CollectionRegion {

    public SpringCacheCollectionRegion(SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, SessionFactoryOptions settings, CacheDataDescription metadata,
            Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, settings, metadata, properties);
    }

    @Override
    public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return getAccessStrategyFactory().createCollectionRegionAccessStrategy(this, accessType);
    }

}
