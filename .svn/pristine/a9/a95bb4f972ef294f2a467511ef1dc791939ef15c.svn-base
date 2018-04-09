package cn.cnyirui.framework.extension.hibernate.springcache;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;

import cn.cnyirui.framework.extension.spring.CacheManagerFactory;

public class SpringCacheRegionFactory extends AbstractSpringCacheRegionFactory {
    private static final long serialVersionUID = -2752996303920394689L;


    public SpringCacheRegionFactory() {}


    public SpringCacheRegionFactory(Properties prop) {
        super();
    }

    @Override
    public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
        try {
            this.settings = settings;
            this.cacheManager = CacheManagerFactory.getCacheManager();
            this.usePrefix = CacheManagerFactory.isUsePrefix();
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public void stop() {
        cacheManager = null;
    }

}
