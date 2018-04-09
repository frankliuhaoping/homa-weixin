package cn.cnyirui.framework.extension.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 增加验证码验证
 * 
 * @author pengzhihua
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 2491409293457238695L;

	/**
	 * 验证码
	 */
	private String captcha;

	/**
	 * 验证码
	 * 
	 * @return captcha 验证码
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 验证码
	 * 
	 * @param captcha 验证码
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

}
