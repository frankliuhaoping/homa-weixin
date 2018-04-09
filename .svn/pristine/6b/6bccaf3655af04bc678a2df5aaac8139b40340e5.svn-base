package cn.cnyirui.framework.extension.hibernate.springcache.region;

import java.util.Properties;

import org.hibernate.cache.spi.QueryResultsRegion;
import org.springframework.cache.Cache;

import cn.cnyirui.framework.extension.hibernate.springcache.strategy.SpringCacheAccessStrategyFactory;

public class SpringCacheQueryResultsRegion extends SpringCacheGeneralDataRegion implements QueryResultsRegion {

    public SpringCacheQueryResultsRegion(SpringCacheAccessStrategyFactory accessStrategyFactory, Cache cache, boolean usePrefix, Properties properties) {
        super(accessStrategyFactory, cache, usePrefix, properties);
    }

}
