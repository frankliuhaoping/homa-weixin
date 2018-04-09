package cn.cnyirui.framework.service.baidu;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.utils.JsonUtil;
import me.chanjar.weixin.common.util.http.Utf8ResponseHandler;

@Service
@Lazy(true)
public class BaiduAPIService {

	private static final Logger logger = LoggerFactory.getLogger(BaiduAPIService.class);

	private String AK = "lFSausrDkbO6gTVapckAjL3S";

	/**
	 * 用于需将非百度地图坐标的坐标进行转化，进而将其运用到百度地图开发的用户
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param fromCoordType 取值为如下：
	 * 
	 *            <pre>
	 *            1：GPS设备获取的角度坐标，wgs84坐标;
	 *            2：GPS获取的米制坐标、sogou地图所用坐标;
	 *            3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局坐标;
	 *            4：3中列表地图坐标对应的米制坐标;
	 *            5：百度地图采用的经纬度坐标;
	 *            6：百度地图采用的米制坐标;
	 *            7：mapbar地图坐标;
	 *            8：51地图坐标
	 *            </pre>
	 * 
	 * @param toCoordType 有两种可供选择：5、6。
	 * 
	 *            <pre>
	 *            5：bd09ll(百度经纬度坐标),
	 *            6：bd09mc(百度米制经纬度坐标);
	 *            </pre>
	 * 
	 * @return
	 */
	public JsonNode geocodeConvert(Double longitude, Double latitude, String fromCoordType, String toCoordType) {
		StringBuilder sb = new StringBuilder("http://api.map.baidu.com/geoconv/v1/?coords=");
		sb.append(longitude).append(",").append(latitude);
		sb.append("&from=").append(fromCoordType).append("&to=").append(toCoordType);
		sb.append("&ak=").append(AK);

		JsonNode jsonNode = httpPost(sb.toString());
		if (jsonNode != null) {
			String status = String.valueOf(jsonNode.get("status")).replaceAll("\"", "");
			if ("0".equalsIgnoreCase(status)) {
				return jsonNode;
			}
		}
		return null;
	}

	/**
	 *
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param coordType 坐标类型
	 *            默认为bd09经纬度坐标。允许的值为bd09ll、bd09mc、gcj02、wgs84。bd09ll表示百度经纬度坐标，
	 *            bd09mc表示百度墨卡托坐标，gcj02表示经过国测局加密的坐标，wgs84表示gps获取的坐标。
	 * @return
	 */
	public JsonNode reverseGeocode(Double longitude, Double latitude, String coordType) {
		JsonNode location = geocodeConvert(longitude, latitude, "1", "5");
		if (!"0".equalsIgnoreCase(String.valueOf(location.get("status")))) {
			return null;
		}
		ArrayNode result = (ArrayNode) location.get("result");
		StringBuilder sb = new StringBuilder("http://api.map.baidu.com/geocoder?location=");
		sb.append(result.get(0).get("y")).append(",").append(result.get(0).get("x"));
		sb.append("&output=json");
		if (StringUtils.isNotBlank(coordType)) {
			sb.append("&coord_type=").append(coordType);
		}
		sb.append("&src=kk");
		sb.append("&ak=").append(AK);

		JsonNode jsonNode = httpPost(sb.toString());
		if (jsonNode != null) {
			String status = String.valueOf(jsonNode.get("status")).replaceAll("\"", "");
			if ("OK".equalsIgnoreCase(status)) {
				return jsonNode;
			} else {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNode.put("status", "ERROR");
				objectNode.put("message", String.valueOf(jsonNode.get("status")));
				return objectNode;
			}
		}
		return null;
	}

	public JsonNode httpPost(String url) {
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
			logger.debug(url);
			logger.debug(responseContent);
			JsonNode jsonNode = JsonUtil.getObjectMapper().readTree(responseContent);
			return jsonNode;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
