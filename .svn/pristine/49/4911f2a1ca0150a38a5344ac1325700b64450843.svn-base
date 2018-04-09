package cn.cnyirui.framework.model.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * Plupload是一个上传插件。
 * 这是一个bean类,主要存储Plupload插件上传时需要的参数。
 * 属性名不可随意改动.
 * 这里主要使用MultipartFile文件上传方法
 */
public class Plupload {

	/** 文件临时名(打文件被分解时)或原名 */
	private String name;
	/** 总的块数 */
	private int chunks = -1;
	/** 当前块数（从0开始计数） */
	private int chunk = -1;
	/** HttpServletRequest对象，不能直接传入进来，需要手动传入 */
	private HttpServletRequest request;
	/** 保存文件上传信息，不能直接传入进来，需要手动传入 */
	private MultipartFile multipartFile;
	/** 文件存储路径 */
	private String realPath;
	/** http路径 */
	private String httpPath;

	/**
	 * 文件临时名或原名
	 * 
	 * @return name 文件临时名或原名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 文件临时名(打文件被分解时)或原名
	 * 
	 * @param name 文件临时名(打文件被分解时)或原名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 总的块数
	 * 
	 * @return chunks 总的块数
	 */
	public int getChunks() {
		return chunks;
	}

	/**
	 * 总的块数
	 * 
	 * @param chunks 总的块数
	 */
	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	/**
	 * 当前块数（从0开始计数）
	 * 
	 * @return chunk 当前块数（从0开始计数）
	 */
	public int getChunk() {
		return chunk;
	}

	/**
	 * 当前块数（从0开始计数）
	 * 
	 * @param chunk 当前块数（从0开始计数）
	 */
	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	/**
	 * HttpServletRequest对象，不能直接传入进来，需要手动传入
	 * 
	 * @return request HttpServletRequest对象，不能直接传入进来，需要手动传入
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * HttpServletRequest对象，不能直接传入进来，需要手动传入
	 * 
	 * @param request HttpServletRequest对象，不能直接传入进来，需要手动传入
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 保存文件上传信息，不能直接传入进来，需要手动传入
	 * 
	 * @return multipartFile 保存文件上传信息，不能直接传入进来，需要手动传入
	 */
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	/**
	 * 保存文件上传信息，不能直接传入进来，需要手动传入
	 * 
	 * @param multipartFile 保存文件上传信息，不能直接传入进来，需要手动传入
	 */
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	/**
	 * 文件存储路径
	 * 
	 * @return realPath 文件存储路径
	 */
	public String getRealPath() {
		return realPath;
	}

	/**
	 * 文件存储路径
	 * 
	 * @param realPath 文件存储路径
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	/**
	 * http路径
	 * 
	 * @return httpPath http路径
	 */
	public String getHttpPath() {
		return httpPath;
	}

	/**
	 * http路径
	 * 
	 * @param httpPath http路径
	 */
	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}

}