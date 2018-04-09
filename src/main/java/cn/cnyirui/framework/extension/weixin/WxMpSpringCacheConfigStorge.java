package cn.cnyirui.framework.extension.weixin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import cn.cnyirui.framework.utils.SpringCacheUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public class WxMpSpringCacheConfigStorge extends WxMpInMemoryConfigStorage {

	private CacheManager cacheManager;
	private Cache cache;
	private String baseUrl;

	private static final String ACCESS_TOKEN_KEY = "accessToken";
	private static final String JSAPI_TICKET_KEY = "jsapiTicket";

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	private Cache getCache() {
		if (cache == null) {
			cache = cacheManager.getCache("WxConfig:" + getAppId());
		}
		return cache;
	}

	@Override
	public String getAccessToken() {
		return SpringCacheUtils.getValue(getCache(), ACCESS_TOKEN_KEY);
	}

	@Override
	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
		long period = (expiresInSeconds - 200) * 1000l;
		long expiresTime = System.currentTimeMillis() + period;
		getCache().put(ACCESS_TOKEN_KEY, accessToken);
		SpringCacheUtils.expire(getCache(), ACCESS_TOKEN_KEY, period);

		getCache().put(ACCESS_TOKEN_KEY + "_expiresTime", expiresTime);
		SpringCacheUtils.expire(getCache(), ACCESS_TOKEN_KEY + "_expiresTime", period);
	}

	@Override
	public boolean isAccessTokenExpired() {
		Long expiresTime = SpringCacheUtils.getValue(getCache(), ACCESS_TOKEN_KEY + "_expiresTime");
		return System.currentTimeMillis() > (expiresTime == null ? 0 : expiresTime);
	}

	@Override
	public void expireAccessToken() {
		getCache().evict(ACCESS_TOKEN_KEY);
		getCache().evict(ACCESS_TOKEN_KEY + "_expiresTime");
	}

	/**
	 * JsapiTicket
	 */
	@Override
	public String getJsapiTicket() {
		return SpringCacheUtils.getValue(getCache(), JSAPI_TICKET_KEY);
	}

	@Override
	public boolean isJsapiTicketExpired() {
		Long expiresTime = SpringCacheUtils.getValue(getCache(), JSAPI_TICKET_KEY + "_expiresTime");
		return System.currentTimeMillis() > (expiresTime == null ? 0 : expiresTime);
	}

	@Override
	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
		long period = (expiresInSeconds - 200) * 1000l;
		// 预留200秒的时间
		long expiresTime = System.currentTimeMillis() + period;
		getCache().put(JSAPI_TICKET_KEY, jsapiTicket);
		SpringCacheUtils.expire(getCache(), JSAPI_TICKET_KEY, period);

		getCache().put(JSAPI_TICKET_KEY + "_expiresTime", expiresTime);
		SpringCacheUtils.expire(getCache(), JSAPI_TICKET_KEY + "_expiresTime", period);
	}

	@Override
	public void expireJsapiTicket() {
		getCache().evict(ACCESS_TOKEN_KEY);
		getCache().evict(JSAPI_TICKET_KEY + "_expiresTime");
	}

	/**
	 * baseUrl
	 * 
	 * @return baseUrl baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * baseUrl
	 * 
	 * @param baseUrl baseUrl
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String getOauth2redirectUri() {
		String url = super.getOauth2redirectUri();
		if (!StringUtils.isBlank(url)) {
			return url;
		} else {
			return getBaseUrl() + "weixin/oauth2";
		}
	}
}
