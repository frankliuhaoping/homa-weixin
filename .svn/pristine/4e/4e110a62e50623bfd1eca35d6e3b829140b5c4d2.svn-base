package cn.cnyirui.framework.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import cn.cnyirui.framework.dao.rbac.SysMenuDao;
import cn.cnyirui.framework.dao.rbac.SysPermissionDao;
import cn.cnyirui.framework.dao.rbac.SysUserDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.service.baidu.BaiduAPIService;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.framework.service.weixin.WeiXinQrCodeService;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.backend.OrganizationDao;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;
import cn.cnyirui.homaweixin.service.backend.ProductCategoryService;
import cn.cnyirui.ims.dao.IMSCommonDao;
import cn.cnyirui.ims.task.SynchronizeIMSDataTask;
import me.chanjar.weixin.mp.api.WxMpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class SysUserDaoTest {

	@Resource
	private SysUserDao sysUserDao;

	@Resource
	private SysMenuDao sysMenuDao;

	@Resource
	private SysPermissionDao sysPermissionDao;

	@Resource
	private OrganizationDao organizationDao;

	@Resource
	private OrganizationService organizationService;

	@Resource
	private EmployeeService employeeService;

	@Resource
	private SysUserService sysUserService;

	@Resource
	private EmployeeDao employeeDao;

	@Resource
	private ProductCategoryService productCategoryService;

	@Resource
	private WeiXinQrCodeService weiXinQrCodeService;

	@Resource
	private SynchronizeIMSDataTask synchronizeIMSDataTask;

	@Resource
	private BaiduAPIService baiduAPIService;

	@Resource
	private WxMpService wxMpService;

	@Resource
	private IMSCommonDao imsCommonDao;

	@Test
	public void testJoin() {
		// synchronizeIMSDataTask.synchronizeIMSOrganization();

		// 、、、wgs84
		JsonNode jsonNode = baiduAPIService.reverseGeocode(113.3603, 22.51561, null);
		System.out.println(jsonNode.get("result").get("formatted_address"));

		// synchronizeIMSDataTask.synchronizeIMSProductCategory();
		// synchronizeIMSDataTask.synchronizeIMSProduct();
		// synchronizeIMSDataTask.synchronizeIMSOrganization();
		// synchronizeIMSDataTask.synchronizeIMSEmployee();
		// synchronizeIMSDataTask.synchronizeIMSSalesOrder();
		// employeeDao.createCanViewOrganizationList("123456", "2");
		// Searchable searchable =
		// Searchable.newSearchable().addSearchFilter("p.name",
		// SearchOperator.like, "电");
		// searchable.setPageable(new PageRequest(1, 1));
		// Page<ProductCategory> page =
		// productCategoryService.findAllProductCategory(true, searchable);
		// for (ProductCategory productCategory : page.getContent()) {
		// System.out.println(productCategory.getName());
		// }
		// for (int i = 0; i < 200; i++) {
		// System.out.println(Generators.timeBasedGenerator().generate().timestamp());
		// }
		// weiXinQrCodeService.createWeiXinQrCode(WeiXinQrCodeSceneType.SALESORDER,
		// WeiXinQrCodeType.TMEP, null);
	}

	// @Test
	@Rollback(false)
	public void textCommonDao() {

		Organization zb = organizationDao.findOne("654d972f-58f6-4a2f-8b8d-d1796a646d7a");
		for (int i = 0; i <= 10; i++) {
			Organization zb1 = new Organization();
			zb1.setName("区域中心_" + i);
			zb1.setParent(zb);
			organizationDao.save(zb1);
			int max = RandomUtils.nextInt(10, 20);
			int maxk = RandomUtils.nextInt(40, 100);
			for (int j = 0; j <= max; j++) {
				Organization zb2 = new Organization();
				zb2.setName("区域中心_" + i + "_办事处_" + j);
				zb2.setParent(zb1);
				organizationDao.save(zb2);
				for (int k = 0; k <= maxk; k++) {
					Organization zb3 = new Organization();
					zb3.setName("区域中心_" + i + "_办事处_" + j + "_门店_" + k);
					zb3.setParent(zb2);
					organizationDao.save(zb3);
				}
			}
		}

	}

	// @Test
	@Transactional
	@Rollback(false)
	public void initData() {
		SysUser sysUser = sysUserDao.findByLoginName("admin");
		if (sysUser == null) {
			sysUser = new SysUser();
			sysUser.setLoginName("admin");
			sysUser.setRealName("管理员");
			sysUser.setPassword(DigestUtils.md5Hex("admin"));
			sysUser.setIsAdmin(true);
			sysUser.setIsDisabled(false);
			sysUserDao.save(sysUser);
		}
		// SysMenu sysMenu = findSysMenuByName("系统管理");
		// if (sysMenu == null) {
		// sysMenu = new SysMenu();
		// sysMenu.setName("系统管理");
		// sysMenuDao.save(sysMenu);
		// }
		//
		// SysMenu sysMenu1 = findSysMenuByName("菜单管理");
		// if (sysMenu1 == null) {
		// sysMenu1 = new SysMenu();
		// sysMenu1.setName("菜单管理");
		// sysMenu1.setUrl("/rbac/sysMenu/list");
		// sysMenu1.setPermissionCode("sysMenu");
		// sysMenu1.setParent(sysMenu);
		// sysMenuDao.save(sysMenu1);
		//
		// SysPermission sysPermission = new SysPermission();
		// sysPermission.setName("查看");
		// sysPermission.setPermissionValue("sysMenu:view");
		// sysPermission.setSysMenu(sysMenu1);
		// sysPermissionDao.save(sysPermission);
		//
		// sysPermission = new SysPermission();
		// sysPermission.setName("添加");
		// sysPermission.setPermissionValue("sysMenu:add");
		// sysPermission.setSysMenu(sysMenu1);
		// sysPermissionDao.save(sysPermission);
		//
		// sysPermission = new SysPermission();
		// sysPermission.setName("修改");
		// sysPermission.setPermissionValue("sysMenu:edit");
		// sysPermission.setSysMenu(sysMenu1);
		// sysPermissionDao.save(sysPermission);
		//
		// }
	}

	private SysMenu findSysMenuByName(String name) {
		Searchable searchable = Searchable.newSearchable();
		searchable.addSearchFilter("name", SearchOperator.eq, name);
		return sysMenuDao.findOne(searchable);
	}

	// @Test
	@Transactional
	@Rollback
	public void demo() {
		// 所有SysUser
		sysUserDao.findAll();

		// id为1或2的SysUser
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		sysUserDao.findAll(list);

		// 分页(第1页，每页20条)
		Pageable pageable = new PageRequest(0, 20);
		sysUserDao.findAll(pageable);

		// 排序
		// (id和loginName升序)
		Sort sort = new Sort("id", "loginName");
		// (id和loginName降序)
		sort = new Sort(Direction.DESC, "id", "loginName");
		// id升级，loginName降序
		sort = new Sort(new Sort.Order(Direction.ASC, "id"), new Sort.Order(Direction.DESC, "loginName"));
		sysUserDao.findAll(sort);

		// 分页+排序
		pageable = new PageRequest(0, 20, sort);
		pageable = new PageRequest(0, 20, Direction.DESC, "id", "loginName");
		sysUserDao.findAll(pageable);

		/////////////////////////////////////////////////////
		// 条件查询+分页+排序
		Searchable searchable = Searchable.newSearchable();
		searchable.addSearchFilter("loginName", SearchOperator.eq, "kk");
		searchable.setPageable(pageable);
		sysUserDao.findAll(searchable);

	}

}
