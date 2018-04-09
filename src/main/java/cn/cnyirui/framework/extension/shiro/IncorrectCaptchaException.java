package cn.cnyirui.framework.extension.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常
 * 
 * @author pengzhihua
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 5021595978934678891L;

    public IncorrectCaptchaException(String message) {
        super(message);
    }

}
