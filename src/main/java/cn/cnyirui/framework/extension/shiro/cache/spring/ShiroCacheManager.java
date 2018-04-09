package cn.cnyirui.framework.extension.shiro.cache.spring;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * shiro缓存管理(采用spring cache)
 * 
 * @author pengzhihua
 *
 */
public class ShiroCacheManager extends AbstractCacheManager {
    private org.springframework.cache.CacheManager cacheManager;
    private boolean usePrefix;

    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * usePrefix
     * 
     * @param usePrefix usePrefix
     */
    public void setUsePrefix(boolean usePrefix) {
        this.usePrefix = usePrefix;
    }



    @SuppressWarnings("rawtypes")
    @Override
    protected Cache createCache(String name) throws CacheException {
        name = "shiro_" + name;
        org.springframework.cache.Cache springCache = cacheManager.getCache(name);
        return new ShiroCache(springCache, usePrefix);
    }


}
