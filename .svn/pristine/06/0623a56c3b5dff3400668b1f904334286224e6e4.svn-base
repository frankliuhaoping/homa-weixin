package cn.cnyirui.framework.controller.baidu;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import cn.cnyirui.framework.service.baidu.BaiduAPIService;

@RestController
@Lazy(true)
public class BaiduAPIController {

	@Resource
	private BaiduAPIService baiduAPIService;

	@RequestMapping("/baidu/api/reverseGeocode/{longitude}/{latitude}")
	public JsonNode getFormattedAddress(@PathVariable("longitude") Double longitude,
	        @PathVariable("latitude") Double latitude) {
		return baiduAPIService.reverseGeocode(longitude, latitude, null);
	}
}
