package cn.cnyirui.framework.extension.captcha;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.model.vo.JsonResult;

@Controller
public class CaptchaController {

    @Resource
    private CaptchaService captchaService;

    @RequestMapping(value = "/static/captcha/{noiseLineNum}")
    public void generateJpegCaptcha(HttpServletRequest request, HttpServletResponse response, @PathVariable("noiseLineNum") int noiseLineNum) {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 不缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
            captchaService.setNoiseLineNum(noiseLineNum);
            BufferedImage image = captchaService.createCaptchaImage(request);
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/static/captcha/get")
    public JsonResult getCaptchaValue(HttpServletRequest request) {
        return JsonResult.success(captchaService.getCaptchaValue(request));
    }
}
