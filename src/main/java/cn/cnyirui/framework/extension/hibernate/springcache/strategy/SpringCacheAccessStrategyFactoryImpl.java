package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheCollectionRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheEntityRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheNaturalIdRegion;

public class SpringCacheAccessStrategyFactoryImpl implements SpringCacheAccessStrategyFactory {

    @Override
    public EntityRegionAccessStrategy createEntityRegionAccessStrategy(SpringCacheEntityRegion entityRegion,
            AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                return new ReadOnlySpringCacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteSpringCacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteSpringCacheEntityRegionAccessStrategy(entityRegion, entityRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalSpringCacheEntityRegionAccessStrategy(entityRegion, entityRegion.getCache(), entityRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

    @Override
    public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(SpringCacheCollectionRegion collectionRegion,
            AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                return new ReadOnlySpringCacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteSpringCacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteSpringCacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalSpringCacheCollectionRegionAccessStrategy(collectionRegion, collectionRegion.getCache(), collectionRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }

    @Override
    public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(SpringCacheNaturalIdRegion naturalIdRegion,
            AccessType accessType) {
        switch (accessType) {
            case READ_ONLY:
                return new ReadOnlySpringCacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case READ_WRITE:
                return new ReadWriteSpringCacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case NONSTRICT_READ_WRITE:
                return new NonStrictReadWriteSpringCacheNaturalIdRegionAccessStrategy(naturalIdRegion, naturalIdRegion.getSettings());
            case TRANSACTIONAL:
                return new TransactionalSpringCacheNaturalIdRegionAccessStrategy(naturalIdRegion,
                        naturalIdRegion.getCache(), naturalIdRegion.getSettings());
            default:
                throw new IllegalArgumentException("unrecognized access strategy type [" + accessType + "]");
        }
    }
}
