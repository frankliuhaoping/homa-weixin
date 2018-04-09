package cn.cnyirui.framework.extension.spring;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import cn.cnyirui.framework.utils.Constants;

/**
 * spring 日期时间转换
 * 
 * @author pengzhihua
 *
 */
public class DateConverter implements Converter<String, Date> {

	private static final String[] formates = new String[] { Constants.DATE_FORMAT, Constants.DATE_TIME_FORMAT,
			"yyyy-MM", "yyyy-MM-dd hh:mm" };

	@Override
	public Date convert(String source) {
		try {
			return DateUtils.parseDate(source, formates);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
