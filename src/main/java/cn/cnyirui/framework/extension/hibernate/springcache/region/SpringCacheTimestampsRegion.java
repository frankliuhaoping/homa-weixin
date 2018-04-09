package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.cache.spi.TimestampsRegion;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;

public class SpringCacheTimestampsRegion extends SpringCacheGeneralDataRegion implements TimestampsRegion {

    public SpringCacheTimestampsRegion(SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, properties);
    }

}
