package cn.cnyirui.framework.utils;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

public class RedisUtils {
	private static final Logger logger = Logger.getLogger(RedisUtils.class);
	@SuppressWarnings("rawtypes")
	private static RedisOperations redisOperations;

	@SuppressWarnings("unchecked")
	public static <K, V> RedisOperations<K, V> getRedisOperations() {
		if (redisOperations == null) {
			redisOperations = SpringUtils.getBean("redisTemplate");
		}
		return redisOperations;
	}

	private RedisUtils() {
	}

	/**
	 * 按key设置缓存值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static <K, V> boolean set(K key, V value) {
		return set(key, value, 0, null);
	}

	/**
	 * 按key设置缓存值
	 * 
	 * @param key
	 * @param value
	 * @param timeout 过期时间
	 * @param unit 时间单位
	 * @return
	 */
	public static <K, V> boolean set(K key, V value, long timeout, TimeUnit unit) {
		boolean flag = false;
		try {
			if (unit == null) {
				getRedisOperations().opsForValue().set(key, value);
			} else {
				getRedisOperations().opsForValue().set(key, value, timeout, unit);
			}
			flag = true;
		} catch (Exception e) {
			logger.error("Redis set方法报错，key值：" + key);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取缓存key的值
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> V get(K key) {
		Object obj = null;
		try {
			obj = getRedisOperations().opsForValue().get(key);
			return (V) obj;
		} catch (Exception e) {
			logger.error("Redis get方法报错，key值：" + key);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按缓存key删除
	 * 
	 * @param key
	 * @return
	 */
	public static <K> boolean delete(K key) {
		boolean flag = false;
		try {
			getRedisOperations().delete(key);
			logger.debug("Redis delete " + key);
			flag = true;
		} catch (Exception e) {
			logger.error("Redis delete方法报错，key值：" + key);
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 清空所有缓存
	 * 
	 * @return
	 */
	public static void flushAll() {
		getRedisOperations().execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
	}

	/**
	 * 可用缓存key个数
	 * 
	 * @return
	 */
	public static long dbSize() {
		return dbSize(getRedisOperations());
	}

	/**
	 * 可用缓存key个数
	 * 
	 * @param redisOperations
	 * @return
	 */
	public static <K, V> long dbSize(RedisOperations<K, V> redisOperations) {
		return redisOperations.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * 获取缓存(cacheName)所有key
	 * 
	 * @param cacheName 缓存名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K> Set<K> getCacheKyes(K cacheName) {
		return (Set<K>) getCacheKyes(getRedisOperations(), cacheName, true);

	}

	/**
	 * 获取缓存(cacheName)所有key
	 * 
	 * @param redisOperations
	 * @param cacheName 缓存名称
	 * @param usePrefix key是否加cacheName前缀
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Set<K> getCacheKyes(final RedisOperations<K, V> redisOperations, final K cacheName,
	        final boolean usePrefix) {
		Assert.notNull(redisOperations, "redisOperations must not be null");
		K keyPrefix = cacheName;
		if (usePrefix) {
			RedisCachePrefix cachePrefix = SpringUtils.getBean("redisCachePrefix");
			byte[] keyPrefixByte = cachePrefix.prefix(keyPrefix.toString());
			keyPrefix = (K) new String(keyPrefixByte);
		}
		final K keyPrefixFinal = keyPrefix;

		final RedisSerializer<K> redisSerializer = (RedisSerializer<K>) redisOperations.getKeySerializer();
		Set<byte[]> tempKeys = redisOperations.execute(new RedisCallback<Set<byte[]>>() {

			@Override
			public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
				if (usePrefix) {
					return connection.keys(redisSerializer.serialize((K) (keyPrefixFinal + "*")));
				} else {
					return connection.zRangeByScore(redisSerializer.serialize((K) (cacheName + "~keys")), 0, 0);
				}
			}
		});

		if (tempKeys.isEmpty()) {
			return Collections.emptySet();
		} else {
			Set<K> keys = new HashSet<K>();
			for (byte[] bs : tempKeys) {
				K key = redisSerializer.deserialize(bs);
				if (usePrefix) {
					key = (K) StringUtils.replace(key.toString(), keyPrefix.toString(), "");
				}
				keys.add(key);
			}
			return keys;
		}
	}

	/**
	 * 按缓存名称删除
	 * 
	 * @param cacheName 缓存名称
	 */
	public static <K> void deleteCache(K cacheName) {
		Set<K> keys = getCacheKyes(cacheName);
		for (K key : keys) {
			delete(key);
		}
	}

	public static <K> void expire(K key, long timeout, TimeUnit unit) {
		getRedisOperations().expire(key, timeout, unit);
	}

	public static <K> void expireAt(K key, Date date) {
		getRedisOperations().expireAt(key, date);
	}

}
