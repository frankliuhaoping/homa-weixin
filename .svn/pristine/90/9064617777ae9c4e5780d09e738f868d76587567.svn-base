package cn.cnyirui.framework.extension.log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.service.rbac.SysLogService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.EntityUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.framework.utils.ServletUtils;
import cn.cnyirui.framework.utils.CurrentUserUtils;

@Aspect
@Component
public class SysLogAspect {

	@Resource
	private SysLogService sysLogService;

	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	/**
	 * controller日志切点(保存和删除)
	 */
	// @Pointcut("@annotation(cn.cnyirui.common.extension.log.SysLog)")
	@Pointcut("execution(* cn.cnyirui.*.controller..*Controller.save*(..)) || execution(* cn.cnyirui.*.controller..*Controller.delete*(..))")
	public void controllerSysLogAspect() {
	}

	/**
	 * service日志切点(保存和删除)
	 */
	@Pointcut("execution(* cn.cnyirui.*.service..*Service.save*(..)) || execution(* cn.cnyirui.*.service..*Service.delete*(..))")
	public void serviceSysLogAspect() {
	}

	/**
	 * 前置通知，用于记录用户的操作
	 * 
	 * @param joinPoint 切点
	 */
	@Before("controllerSysLogAspect()")
	public void doBefore(JoinPoint joinPoint) {
		SysUser sysUser = CurrentUserUtils.getSysUser();
		if (sysUser == null) {
			return;
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		        .getRequest();
		String ip = ServletUtils.getRemortIP(request);
		String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
		String description = getMthodDescription(joinPoint);

		if (joinPoint.getTarget() instanceof BaseCRUDController) {
			description = ((BaseCRUDController<?>) joinPoint.getTarget()).getControllerConfig(request).getPageTitle()
			        + " - " + StringUtils.defaultIfEmpty(description, "");
		}
		logger.debug("=====前置AOP开始=====");
		logger.debug("请求方法：" + methodName);
		logger.debug("方法描述：" + description);
		logger.debug("操作人：" + sysUser.getRealName());
		logger.debug("操作时间：" + DateFormatUtils.format(new Date(), Constants.DATE_TIME_FORMAT));
		logger.debug("IP：" + ip);
		logger.debug("=====前置AOP结束=====");

		sysLogService.saveSysLog(methodName, argsToJson(joinPoint), description, ip);
	}

	/**
	 * 获取注解中对方法的描述信息
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getMthodDescription(JoinPoint joinPoint) {
		Class<?> tagretClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		if (StringUtils.indexOfIgnoreCase(methodName, "save") != -1) {
			String[] parameterNames = methodSignature.getParameterNames();
			int index = ArrayUtils.indexOf(parameterNames, "action");
			if (index != -1) {
				Object[] arguments = joinPoint.getArgs();
				if ("add".equalsIgnoreCase(arguments[index].toString())) {
					return "添加";
				}
				if ("edit".equalsIgnoreCase(arguments[index].toString())) {
					return "修改";
				}
			}
		}
		Method method = MethodUtils.getMatchingAccessibleMethod(tagretClass, methodName,
		        methodSignature.getParameterTypes());
		if (method != null) {
			Annotation annotation = method.getAnnotation(SysLog.class);
			if (annotation != null) {
				return AnnotationUtils.getValue(annotation, "description").toString();
			}
		}
		return null;
	}

	private String argsToJson(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		String[] parameterNames = methodSignature.getParameterNames();
		Object[] arguments = joinPoint.getArgs();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parameterNames.length; i++) {
			sb.append(parameterNames[i]).append(" = ");
			if (!(arguments[i] instanceof ServletRequest || arguments[i] instanceof ServletResponse)) {
				if (arguments[i] instanceof BaseEntity) {
					sb.append(
					        EntityUtils.toObjectNode(arguments[i], EntityUtils.defaultToObjectNodeEntityConfig(null)));
				} else {
					sb.append(JsonUtil.toJson(arguments[i]));
				}

			} else {
				sb.append(arguments[i].getClass().getSimpleName());
			}
			if (i != parameterNames.length - 1) {
				sb.append("，");
			}
		}
		return sb.toString();
	}
}
