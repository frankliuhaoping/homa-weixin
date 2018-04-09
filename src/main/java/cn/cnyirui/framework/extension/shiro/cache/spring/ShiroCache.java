package cn.cnyirui.framework.extension.shiro.cache.spring;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import cn.cnyirui.framework.utils.SpringCacheUtils;

/**
 * shiro缓存(采用spring cache)
 * 
 * @author pengzhihua
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

    private org.springframework.cache.Cache springCache;
    private boolean usePrefix;

    public ShiroCache(org.springframework.cache.Cache springCache, boolean usePrefix) {
        this.springCache = springCache;
        this.usePrefix = usePrefix;
    }

    @Override
    public V get(K key) throws CacheException {
        return SpringCacheUtils.getValue(springCache, key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V v = get(key);
        springCache.put(key, value);
        return v;
    }

    @Override
    public V remove(K key) throws CacheException {
        V v = get(key);
        springCache.evict(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        springCache.clear();
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return SpringCacheUtils.getKeys(springCache, usePrefix);
    }

    @Override
    public Collection<V> values() {
        return SpringCacheUtils.getValues(springCache, usePrefix);
    }

}
