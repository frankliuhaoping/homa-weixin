package cn.cnyirui.framework.controller.rbac;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysRoleService;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.framework.utils.CurrentUserUtils;

@Controller
@RequestMapping("/rbac/sysUser")
public class SysUserController extends BaseCRUDController<SysUser> {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    @Override
    protected BaseService<SysUser> getBaseService() {
        return sysUserService;
    }

    @Override
    protected void setCommonModel(ModelAndView m, String viewName) {
        super.setCommonModel(m, viewName);
        m.addObject("sysRoleList", sysRoleService.findAll(new Sort("name")));
    }

    @Override
    public JsonResult doSave(String action, SysUser voModel, HttpServletRequest request) {
        SysUser sysUser = sysUserService.findByLoginName(voModel.getLoginName());
        if (assertExists(action, voModel, sysUser)) {
            return JsonResult.error("登录名称：" + voModel.getLoginName() + " 已经被占用，请更换一个！");
        }
        return super.doSave(action, voModel, request);
    }

    @RequestMapping("/showModifyPasswordForm")
    public String showModifyPasswordForm() {
        return "/rbac/modifyPassword_form";
    }

    @RequestMapping("/saveModifyPasswordForm")
    @ResponseBody
    public JsonResult saveModifyPasswordForm(String oldPassword, String newPassword) {
        SysUser sysUser = CurrentUserUtils.getSysUser(true);
        if (!StringUtils.equalsIgnoreCase(DigestUtils.md5Hex(oldPassword), sysUser.getPassword())) {
            return JsonResult.error("旧密码输入不正确，请重试！");
        }
        sysUser.setPassword(DigestUtils.md5Hex(newPassword));
        try {
            sysUserService.save(sysUser);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("修改密码失败，请重试！");
        }
    }

}
