
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.persister.collection.CollectionPersister;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheCollectionRegion;


public class ReadWriteSpringCacheCollectionRegionAccessStrategy
        extends AbstractReadWriteSpringCacheAccessStrategy<SpringCacheCollectionRegion>
        implements CollectionRegionAccessStrategy {

    public ReadWriteSpringCacheCollectionRegionAccessStrategy(SpringCacheCollectionRegion region, SessionFactoryOptions settings) {
        super(region, settings);
    }

    @Override
    public CollectionRegion getRegion() {
        return region;
    }

    @Override
    public Object generateCacheKey(Object id, CollectionPersister persister, SessionFactoryImplementor factory, String tenantIdentifier) {
        return DefaultCacheKeysFactory.createCollectionKey(id, persister, factory, tenantIdentifier);
    }

    @Override
    public Object getCacheKeyId(Object cacheKey) {
        return DefaultCacheKeysFactory.getCollectionId(cacheKey);
    }
}
