package cn.cnyirui.framework.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {

	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			// key不带双引号处理
			objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
					true);
		}
		return objectMapper;
	}

	public static String toJson(Object object) {
		String json;
		try {
			json = getObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			json = null;
		}
		return json;
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return getObjectMapper().readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T fromJson(String json, TypeReference<T> valueTypeRef) {
		try {
			return getObjectMapper().readValue(json, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JsonFactory getJsonFactory() {
		return new JsonFactory(getObjectMapper());
	}

	public static JsonParser getJsonParser(String json) {
		JsonFactory jsonFactory = getJsonFactory();
		try {
			return jsonFactory.createParser(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
