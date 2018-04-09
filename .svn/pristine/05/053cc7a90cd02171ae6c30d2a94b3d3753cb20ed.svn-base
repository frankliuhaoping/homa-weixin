package cn.cnyirui.framework.controller.upload;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.model.vo.Plupload;
import cn.cnyirui.framework.utils.PluploadUtils;

@Controller
public class UploadController {
	private static final String DEFAULT_UPLOAD_DIR = "upload/";

	@RequestMapping(value = "/plupload", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {
		plupload = UploadController.pluploadHandler(plupload, request, response);
		if (plupload != null) {
			return JsonResult.success("/" + plupload.getHttpPath() + plupload.getName());
		} else {
			return JsonResult.error("文件上传失败，请重试！！");
		}
	}

	public static Plupload pluploadHandler(Plupload plupload, HttpServletRequest request,
			HttpServletResponse response) {
		plupload.setRequest(request);
		String path = ServletRequestUtils.getStringParameter(request, "dir", "temp");
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		plupload.setHttpPath(DEFAULT_UPLOAD_DIR + path);
		plupload.setRealPath(request.getSession().getServletContext().getRealPath("/") + plupload.getHttpPath());
		// 文件存储路径
		File dir = new File(plupload.getRealPath());
		try {
			// 上传文件
			PluploadUtils.upload(plupload, dir);
			// 判断文件是否上传成功（被分成块的文件是否全部上传完成）
			if (PluploadUtils.isUploadFinish(plupload)) {
				return plupload;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
