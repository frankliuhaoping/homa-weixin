package cn.cnyirui.framework.extension.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.cache.CacheManager;

import cn.cnyirui.framework.utils.SpringUtils;

public class CacheManagerFactory implements DisposableBean {
    private static CacheManager CACH_EMANAGER;
    private static boolean usePrefix;

    @Override
    public void destroy() throws Exception {
        CACH_EMANAGER = null;
    }

    public static CacheManager getCacheManager() {
        if (CACH_EMANAGER == null) {
            CACH_EMANAGER = SpringUtils.getBean("springCacheManager");
        }
        return CACH_EMANAGER;
    }

    public void setCacheManager(CacheManager cacheManager) {
        CacheManagerFactory.CACH_EMANAGER = cacheManager;
    }

    public static boolean isUsePrefix() {
        return usePrefix;
    }


    public void setUsePrefix(boolean usePrefix) {
        CacheManagerFactory.usePrefix = usePrefix;
    }

}
