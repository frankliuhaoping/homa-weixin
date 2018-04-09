package cn.cnyirui.framework.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisOperations;

public class SpringCacheUtils {

	/**
	 * cache的所有key
	 * 
	 * @param springCache
	 * @param usePrefix
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Set<K> getKeys(Cache springCache, boolean usePrefix) {
		Object nativeCache = springCache.getNativeCache();
		if (nativeCache instanceof RedisOperations) {
			return RedisUtils.getCacheKyes((RedisOperations<K, V>) nativeCache, (K) springCache.getName(), usePrefix);
		}
		throw new UnsupportedOperationException(nativeCache.getClass() + " not supported");
	}

	/**
	 * cache的key个数
	 * 
	 * @param springCache
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> long dbSize(Cache springCache) {
		Object nativeCache = springCache.getNativeCache();
		if (nativeCache instanceof RedisOperations) {
			return RedisUtils.dbSize((RedisOperations<K, V>) nativeCache);
		}
		throw new UnsupportedOperationException(nativeCache.getClass() + " not supported");
	}

	/**
	 * 获取key的值
	 * 
	 * @param springCache
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <V> V getValue(Cache springCache, Object key) {
		Object value = springCache.get(key);
		if (value instanceof SimpleValueWrapper) {
			return (V) ((SimpleValueWrapper) value).get();
		}
		return (V) value;
	}

	/**
	 * 获取cache的所值值
	 * 
	 * @param springCache
	 * @param usePrefix
	 * @return
	 */
	public static <K, V> Collection<V> getValues(Cache springCache, boolean usePrefix) {
		List<V> list = new ArrayList<V>();
		Set<K> keys = getKeys(springCache, usePrefix);
		for (K key : keys) {
			V o = getValue(springCache, key);
			if (o != null) {
				list.add(o);
			}
		}
		return list;
	}

	/**
	 * cache转map
	 * 
	 * @param springCache
	 * @param usePrefix
	 * @return
	 */
	public static <K, V> Map<K, V> toMap(Cache springCache, boolean usePrefix) {
		Map<K, V> map = new HashMap<K, V>();
		Set<K> keys = getKeys(springCache, usePrefix);
		for (K key : keys) {
			V value = getValue(springCache, key);
			if (value != null) {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 
	 * @param springCache
	 * @param key
	 * @param timeout 毫秒
	 */
	public static <K> void expire(Cache springCache, K key, long timeout) {
		Object nativeCache = springCache.getNativeCache();
		if (nativeCache instanceof RedisOperations) {
			RedisUtils.expire(key, timeout, TimeUnit.MILLISECONDS);
		}
	}
}
