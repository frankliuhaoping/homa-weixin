package cn.cnyirui.framework.extension.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

    private static String CAPTCHA_SESSION_KEY = "CAPTCHA__CODE__SESSION__KEY";

    /**
     * 验证码可选随机字符
     */
    private String charString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 验证码可字符个数
     */
    private Integer charLength = 5;
    /**
     * 验证码字体
     */
    private String fontName = "Fixedsys";
    /**
     * 验证码字体大小
     */
    private Integer fontSize = 24;
    /**
     * 验证码图片宽度
     */
    private Integer imageWidth = 100;
    /**
     * 验证码图片高度
     */
    private Integer imageHeight = 40;
    /**
     * 验证码干拢线数量
     */
    private Integer noiseLineNum = 30;

    private Random random = new Random();

    /**
     * 验证码可选随机字符
     * 
     * @return charString 验证码可选随机字符
     */
    public String getCharString() {
        return charString;
    }

    /**
     * 验证码可选随机字符
     * 
     * @param charString 验证码可选随机字符
     */
    public void setCharString(String charString) {
        this.charString = charString;
    }

    /**
     * 验证码可字符个数
     * 
     * @return charLength 验证码可字符个数
     */
    public Integer getCharLength() {
        return charLength;
    }

    /**
     * 验证码可字符个数
     * 
     * @param charLength 验证码可字符个数
     */
    public void setCharLength(Integer charLength) {
        this.charLength = charLength;
    }

    /**
     * 验证码字体
     * 
     * @return fontName 验证码字体
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * 验证码字体
     * 
     * @param fontName 验证码字体
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    /**
     * 验证码字体大小
     * 
     * @return fontSize 验证码字体大小
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * 验证码字体大小
     * 
     * @param fontSize 验证码字体大小
     */
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * 验证码图片宽度
     * 
     * @return imageWidth 验证码图片宽度
     */
    public Integer getImageWidth() {
        return imageWidth;
    }

    /**
     * 验证码图片宽度
     * 
     * @param imageWidth 验证码图片宽度
     */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * 验证码图片高度
     * 
     * @return imageHeight 验证码图片高度
     */
    public Integer getImageHeight() {
        return imageHeight;
    }

    /**
     * 验证码图片高度
     * 
     * @param imageHeight 验证码图片高度
     */
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * 验证码干拢线数量
     * 
     * @return noiseLineNum 验证码干拢线数量
     */
    public Integer getNoiseLineNum() {
        return noiseLineNum;
    }

    /**
     * 验证码干拢线数量
     * 
     * @param noiseLineNum 验证码干拢线数量
     */
    public void setNoiseLineNum(Integer noiseLineNum) {
        if (noiseLineNum != 0) {
            this.noiseLineNum = noiseLineNum;
        }
    }



    /**
     * 生成随机图片
     * 
     * @param randomCode
     * @return
     */
    public BufferedImage createCaptchaImage(HttpServletRequest request) {
        // BufferedImage类是具有缓冲区的Image类
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_BGR);
        // 获取Graphics对象,便于对图像进行各种绘制操作
        Graphics g = image.getGraphics();
        // 设置背景色
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, imageWidth, imageHeight);

        // 设置干扰线的颜色
        g.setColor(getRandomColor(110, 120));

        // 绘制干扰线
        for (int i = 0; i <= noiseLineNum; i++) {
            drawLine(g);
        }
        StringBuilder sb = new StringBuilder();
        // 绘制随机字符
        g.setFont(new Font(fontName, Font.ROMAN_BASELINE, fontSize));
        for (int i = 1; i <= charLength; i++) {
            sb.append(drawCaptcha(g, i));
        }
        request.getSession().setAttribute(CAPTCHA_SESSION_KEY, sb.toString());
        g.dispose();
        return image;
    }

    /**
     * 验证验证码
     * 
     * @param session
     * @param targetCaptcha
     * @return
     */
    public boolean validateCaptcha(Session session, String targetCaptcha) {
        Object value = session.getAttribute(CAPTCHA_SESSION_KEY);
        if (value == null) {
            return false;
        }
        String captcha = (String) value;
        return StringUtils.equalsIgnoreCase(captcha, targetCaptcha);
    }

    /**
     * 验证码值
     * 
     * @param request
     * @return
     */
    public String getCaptchaValue(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(CAPTCHA_SESSION_KEY);
    }



    /**
     * 给定范围获得随机颜色
     * 
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandomColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     * 
     * @param g
     * @param i
     * @return
     */
    private String drawCaptcha(Graphics g, int i) {
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(charString.length())));
        g.translate(random.nextInt(3), random.nextInt(3));
        int lineHeight = g.getFontMetrics().getHeight();
        g.drawString(rand, 3 + 18 * (i - 1), lineHeight - (imageHeight - lineHeight) / 2);
        return rand;
    }

    /**
     * 绘制干扰线
     * 
     * @param g
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(imageWidth);
        int y = random.nextInt(imageHeight);
        int x0 = random.nextInt(16);
        int y0 = random.nextInt(16);
        g.drawLine(x, y, x + x0, y + y0);
    }

    /**
     * 获取随机的字符
     * 
     * @param num
     * @return
     */
    private String getRandomString(int num) {
        return String.valueOf(charString.charAt(num));
    }
}
