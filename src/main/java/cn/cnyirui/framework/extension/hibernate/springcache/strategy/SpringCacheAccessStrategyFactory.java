
package cn.cnyirui.framework.extension.hibernate.springcache.strategy;

import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.CollectionRegionAccessStrategy;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;

import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheCollectionRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheEntityRegion;
import cn.cnyirui.framework.extension.hibernate.springcache.region.SpringCacheNaturalIdRegion;


public interface SpringCacheAccessStrategyFactory {


    public EntityRegionAccessStrategy createEntityRegionAccessStrategy(SpringCacheEntityRegion entityRegion,
            AccessType accessType);


    public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(SpringCacheCollectionRegion collectionRegion,
            AccessType accessType);


    public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(SpringCacheNaturalIdRegion naturalIdRegion,
            AccessType accessType);

}
