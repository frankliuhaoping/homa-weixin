package cn.cnyirui.ims.task;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.common.CommonDao;
import cn.cnyirui.framework.dao.rbac.SysUserDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.exception.SearchException;
import cn.cnyirui.framework.extension.weixin.WxMpSpringCacheConfigStorge;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.homaweixin.dao.backend.EmployeeWageDao;
import cn.cnyirui.homaweixin.dao.backend.IMSLogDao;
import cn.cnyirui.homaweixin.dao.backend.OrganizationDao;
import cn.cnyirui.homaweixin.dao.backend.ProductCategoryDao;
import cn.cnyirui.homaweixin.dao.backend.ProductDao;
import cn.cnyirui.homaweixin.dao.backend.SalesOrderDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.EmployeeWage;
import cn.cnyirui.homaweixin.model.po.IMSImportedEntity;
import cn.cnyirui.homaweixin.model.po.IMSLog;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.Product;
import cn.cnyirui.homaweixin.model.po.ProductCategory;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;
import cn.cnyirui.ims.dao.IMSCommonDao;
import cn.cnyirui.ims.entity.IMSBusinessOrganization;
import cn.cnyirui.ims.entity.IMSEmployee;
import cn.cnyirui.ims.entity.IMSEmployeeEntity;
import cn.cnyirui.ims.entity.IMSEmployeeTye;
import cn.cnyirui.ims.entity.IMSOffice;
import cn.cnyirui.ims.entity.IMSOrganizationType;
import cn.cnyirui.ims.entity.IMSProduct;
import cn.cnyirui.ims.entity.IMSProductCategory;
import cn.cnyirui.ims.entity.IMSReadedEntity;
import cn.cnyirui.ims.entity.IMSSaler;
import cn.cnyirui.ims.entity.IMSSalerWage;
import cn.cnyirui.ims.entity.IMSSalesOrder;
import cn.cnyirui.ims.entity.IMSStore;
import cn.cnyirui.ims.entity.IMSTaskType;

/**
 * 同步IMS系统数据
 * 
 * @author pengzhihua
 * 
 *
 */
public class SynchronizeIMSDataTask {

	@Resource
	private IMSCommonDao imsCommonDao;
	@Resource
	private CommonDao commonDao;

	@Resource
	private ProductCategoryDao productCategoryDao;
	@Resource
	private ProductDao productDao;

	@Resource
	private OrganizationDao organizationDao;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private EmployeeWageDao employeeWageDao;
	@Resource
	private SalesOrderDao salesOrderDao;
	@Resource
	private IMSLogDao imsLogDao;
	@Resource
	private WxMpService wxMpService;
	@Resource
	private WxMpInMemoryConfigStorage wxMpConfigStorage;

	private Long IMSOrganizationRootId = 10L;
	private String synType = "BYNAME";

	public void setIMSOrganizationRootId(Long IMSOrganizationRootId) {
		this.IMSOrganizationRootId = IMSOrganizationRootId;
	}

	/**
	 * 添加同步日志
	 * 
	 * @param content
	 */
	@Transactional
	private void addImsLog(IMSTaskType imsTaskType, String errorMessage) {
		boolean successed = StringUtils.isEmpty(errorMessage);
		IMSLog imsLog = new IMSLog();
		imsLog.setSuccessed(successed);
		imsLog.setTaskType(imsTaskType.getValue());

		String content = "同步数据 [" + imsTaskType.getText() + (successed ? "] 成功！" : "] 失败!");
		if (!successed) {
			content += "\r\n" + errorMessage;
		}
		imsLog.setContent(content);
		imsLogDao.save(imsLog);
	}

	private void setCommonProperty(IMSReadedEntity source, IMSImportedEntity target) {
		target.setDeleted(0);
		target.setIsIMSData(true);
		target.setIMSId(source.getId());
		target.setName(source.getName());
	}

	/**
	 * 同步内销系统的基础数据
	 */
	@Transactional
	public void synchronizeIMSBasicData() {
		// 产品分类
		synchronizeIMSProductCategory();
		// 产品
		synchronizeIMSProduct();
		// 组织架构(业务组织, 办事处, 门店)
		synchronizeIMSOrganization();
		// 导购员和员工
		synchronizeIMSEmployee();
		// 员工工资
		synchronizeIMSEmployeeWage();
	}

	/**
	 * 执行失败的任务
	 */
	@Transactional
	public void executeFailedIMSTask() {
		Calendar calendar = Calendar.getInstance();

		Searchable searchable = null;
		try {
			searchable = Searchable.newSearchable().addSearchFilter("successed", SearchOperator.eq, false)
					.addSearchFilter("createdTime", SearchOperator.gte,
							DateUtils.parseDate(DateFormatUtils.format(calendar, "yyyy-MM-dd ") + " 00:00:00",
									"yyyy-MM-dd HH:mm:ss"))
					.addSearchFilter("createdTime", SearchOperator.lte, DateUtils.parseDate(
							DateFormatUtils.format(calendar, "yyyy-MM-dd ") + " 05:00:00", "yyyy-MM-dd HH:mm:ss"));
		} catch (SearchException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<IMSLog> imsLogList = imsLogDao.findAll(searchable).getContent();
		for (IMSLog imsLog : imsLogList) {
			if (imsLog.getTaskType() == IMSTaskType.PRODUCT_CATEGORY.getValue()) {
				synchronizeIMSProductCategory();
			}
			if (imsLog.getTaskType() == IMSTaskType.PRODUCT.getValue()) {
				synchronizeIMSProduct();
			}
			if (imsLog.getTaskType() == IMSTaskType.ORGANIZATION.getValue()) {
				synchronizeIMSOrganization();
			}
			if (imsLog.getTaskType() == IMSTaskType.EMPLOYEE.getValue()) {
				synchronizeIMSEmployee();
			}
			if (imsLog.getTaskType() == IMSTaskType.EMPLOYEE_WAGE.getValue()) {
				synchronizeIMSEmployeeWage();
			}
			if (imsLog.getTaskType() == IMSTaskType.SALES_ORDER.getValue()) {
				synchronizeIMSSalesOrder();
			}
		}
	}

	/**
	 * 同步产品分类的数据
	 * 
	 * @param parentId
	 */
	@Transactional
	private void synchronizeIMSProductCategory(Long parentId) {
		// List<IMSProductCategory> imsProductCategoryList =
		// imsCommonDao.findAllIMSProductCategory(parentId);
		List<IMSProductCategory> imsProductCategoryList = imsCommonDao.findAllIMSProductCategory();
		for (IMSProductCategory imsProductCategory : imsProductCategoryList) {
			// ProductCategory productCategory =
			// productCategoryDao.findByIMSId(imsProductCategory.getId());
			ProductCategory productCategory = productCategoryDao.findByName(imsProductCategory.getName());
			if (productCategory == null) {
				productCategory = new ProductCategory();
			}
			setCommonProperty(imsProductCategory, productCategory);
			productCategory.setRemark(imsProductCategory.getReamrk());
			productCategory.setParent(productCategoryDao.findByIMSId(imsProductCategory.getParentId()));
			productCategoryDao.save(productCategory);
			// synchronizeIMSProductCategory(imsProductCategory.getId());
		}
	}

	/**
	 * 同步产品分类数据，先全部逻辑删除同步过来的数据，再重新同步
	 * 
	 */
	@Transactional
	public void synchronizeIMSProductCategory() {
		try {
			Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
			commonDao.logicDeleteAll(ProductCategory.class.getSimpleName(), searchable);
			synchronizeIMSProductCategory(null);
			addImsLog(IMSTaskType.PRODUCT_CATEGORY, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(IMSTaskType.PRODUCT_CATEGORY, throwable.getMessage());
			throw new RuntimeException(throwable);
		}
	}

	/**
	 * 同步产品数据，先全部逻辑删除同步过来的数据，再重新同步
	 * 
	 */
	@Transactional
	public void synchronizeIMSProduct() {
		try {
			// 先删除
			Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
			commonDao.logicDeleteAll(Product.class.getSimpleName(), searchable);

			List<IMSProduct> imsProductList = imsCommonDao.findAllIMSProduct();
			for (IMSProduct imsProduct : imsProductList) {
				// Product product = productDao.findByIMSId(imsProduct.getId());
				Product product = productDao.findByName(imsProduct.getName());
				if (product == null) {
					product = new Product();
				}
				setCommonProperty(imsProduct, product);
				product.setCode(imsProduct.getCode());
				product.setDescription(imsProduct.getDescription());
				product.setCategory(productCategoryDao.findByName(imsProduct.getBoxType()));
				productDao.save(product);
			}
			addImsLog(IMSTaskType.PRODUCT, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(IMSTaskType.PRODUCT, throwable.getMessage());
			throw new RuntimeException(throwable);

		}

	}

	/**
	 * 同步组织架构的数据
	 * 
	 */
	@Transactional
	public void synchronizeIMSOrganization() {
		try {
			synchronizeIMSBusinessOrganization();
			// synchronizeIMSOffice();
			synchronizeIMSStore();
			updateOrganizationParentIdsNames();

			addImsLog(IMSTaskType.ORGANIZATION, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(IMSTaskType.ORGANIZATION, throwable.getMessage());
			throw new RuntimeException(throwable);
		}
	}

	@Transactional
	private void updateOrganizationParentIdsNames() {
		// 更新parentIds, parentNames
		String callId = organizationService.createRootSubOrganizationList(null);

		String ql = "update organization a set(a.parentIds, a.parentNames, a.depth) = (select b.parentIds, b.parentNames, b.depth from organization_tree_temp b"
				+ " where b.callId ='" + callId + "' and a.id = b.organizationId)"
				+ " where exists(select 1 from organization_tree_temp b" + " where b.callId ='" + callId
				+ "' and a.id = b.organizationId)";
		organizationDao.executeUpdate(ql, null, true);
	}

	@Transactional
	public void deleteTempData() {
		updateOrganizationParentIdsNames();
		organizationDao.executeUpdate("delete from ORGANIZATION_TREE_TEMP", null, true);
		organizationDao.executeUpdate("delete from PRODUCT_CATEGORY_TREE_TEMP", null, true);
	}

	/**
	 * 同步业务组织的数据
	 * 
	 * @param parentId
	 */
	@Transactional
	private void synchronizeIMSBusinessOrganization(Long parentId) {
		Integer imsType = IMSOrganizationType.BUSIORG.getValue();
		List<IMSBusinessOrganization> imsBusinessOrganizationList = imsCommonDao
				.findAllIMSBusinessOrganization(parentId);
		for (IMSBusinessOrganization imsBusinessOrganization : imsBusinessOrganizationList) {
			// 先按ID匹配，再按NAME匹配
			Organization organization = organizationDao.findByIMSIdAndType(imsBusinessOrganization.getId(), imsType);
			Organization parent = organizationDao.findByIMSIdAndType(imsBusinessOrganization.getParentId(), imsType);
			if (organization == null) {
				organization = organizationService.findOneByName(imsBusinessOrganization.getName());
			}
			if (parent == null) {
				parent = organizationService.findOneByName(imsBusinessOrganization.getParentName());
			}

			if (organization == null) {
				organization = new Organization();
			}
			setCommonProperty(imsBusinessOrganization, organization);// 设置delete
																		// id
																		// name；
			organization.setIMSType(imsType);// 0 业务组织
			organization.setRemark(imsBusinessOrganization.getDescription());// 描述
			organization.setParent(parent);//
			organization.setIMSCode(imsBusinessOrganization.getCode());
			organizationDao.save(organization);
			organizationService.setParents(organization);
			organizationDao.save(organization);
			synchronizeIMSBusinessOrganization(imsBusinessOrganization.getId());
		}
	}

	/**
	 * 同步业务组织的数据，先全部逻辑删除同步过来的数据，再重新同步
	 * 
	 */
	@Transactional
	private void synchronizeIMSBusinessOrganization() {
		Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
		searchable.addSearchFilter("IMSType", SearchOperator.eq, IMSOrganizationType.BUSIORG.getValue());
		commonDao.logicDeleteAll(Organization.class.getSimpleName(), searchable);
		synchronizeIMSBusinessOrganization(IMSOrganizationRootId);
	}

	/**
	 * 同步办事处数据，先全部逻辑删除同步过来的数据，再重新同步
	 * 
	 */
	@Transactional
	private void synchronizeIMSOffice() {
		Integer imsType = IMSOrganizationType.OFFICE.getValue();
		// 先删除
		Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
		searchable.addSearchFilter("IMSType", SearchOperator.eq, imsType);
		commonDao.logicDeleteAll(Organization.class.getSimpleName(), searchable);

		List<IMSOffice> imsOfficeList = imsCommonDao.findAllIMSOffice();
		for (IMSOffice imsOffice : imsOfficeList) {
			// 先按ID匹配，再按NAME匹配
			Organization organization = organizationDao.findByIMSIdAndType(imsOffice.getId(), imsType);
			Organization parent = organizationDao.findByIMSIdAndType(imsOffice.getParentId(), imsType);
			if (organization == null) {
				organization = organizationService.findOneByName(imsOffice.getName());
			}
			if (parent == null) {
				parent = organizationService.findOneByName(imsOffice.getParentName());
			}
			if (organization == null) {
				organization = new Organization();
			}
			setCommonProperty(imsOffice, organization);
			organization.setIMSType(imsType);
			organization.setParent(parent);
			organizationDao.save(organization);
			organizationService.setParents(organization);
			organizationDao.save(organization);
		}
	}

	/**
	 * 同步门店数据，先全部逻辑删除同步过来的数据，再重新同步
	 * 
	 */
	@Transactional
	private void synchronizeIMSStore() {
		Integer imsType = IMSOrganizationType.STORE.getValue();
		// 先删除
		Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
		searchable.addSearchFilter("IMSType", SearchOperator.eq, imsType);
		commonDao.logicDeleteAll(Organization.class.getSimpleName(), searchable);

		List<IMSStore> imsStoreList = imsCommonDao.findAllIMSStore();
		for (IMSStore imsStore : imsStoreList) {
			// 先按ID匹配，再按NAME匹配
			Organization organization = organizationDao.findByIMSIdAndType(imsStore.getId(), imsType);
			Organization parent = organizationDao.findByIMSIdAndType(imsStore.getParentId(), imsType);
			if (organization == null) {
				organization = organizationService.findOneByName(imsStore.getName());
			}
			if (parent == null) {
				parent = organizationService.findOneByName(imsStore.getParentName());
			}
			if (organization == null) {
				organization = new Organization();
			}
			setCommonProperty(imsStore, organization);
			organization.setIMSType(imsType);
			organization.setParent(parent);
			organization.setIMSCode(imsStore.getCode());
			organizationDao.save(organization);
			organizationService.setParents(organization);
			organizationDao.save(organization);
		}
	}

	@Transactional
	private <T extends IMSEmployeeEntity> void synchronizeIMSEmployee(List<T> imsEmployeeEntityList,
			IMSEmployeeTye imsEmployeeTye) {
		for (IMSEmployeeEntity imsEmployeeEntity : imsEmployeeEntityList) {
			if (imsEmployeeEntity == null) {
				continue;
			}
			Employee employee = employeeService.findByIMSIdAndType(imsEmployeeEntity.getId(),
					imsEmployeeTye.getValue());

			// if (employee == null) {
			// List<Employee> employees =
			// employeeService.findByName(imsEmployeeEntity.getName());
			// if (employees.size() > 1) {
			// addImsLog(IMSTaskType.OTHER, String.format("%s 名字重复！",
			// imsEmployeeEntity.getName()));
			// continue;
			// } else if (employees.size() > 0) {
			// employee = employees.get(0);
			// }
			// }

			String sysRoleType = null;
			if (employee == null) {
				employee = new Employee();
				if (imsEmployeeEntity instanceof IMSEmployee) {
					sysRoleType = SysRoleType.DIRECTOR.getValue();
				} else {
					sysRoleType = SysRoleType.SALER.getValue();
				}
			} else {
				sysRoleType = employee.getSysRoleType();
			}

			setCommonProperty(imsEmployeeEntity, employee);
			employee.setCode(imsEmployeeEntity.getCode());
			employee.setIdNumber(imsEmployeeEntity.getCardNo());
			employee.setAddress(imsEmployeeEntity.getAddress());
			employee.setBirthdate(imsEmployeeEntity.getBirthdate());
			employee.setInDate(imsEmployeeEntity.getInDate());
			employee.setMobileNo(imsEmployeeEntity.getMobileNo());
			employee.setOfficeTel(imsEmployeeEntity.getOfficeTel());
			employee.setIMSType(imsEmployeeTye.getValue());

			if (imsEmployeeEntity instanceof IMSEmployee) {
				employee.setSex("1".equals(imsEmployeeEntity.getSex()) ? 0 : 1);
				employee.setSalerType(0);
			} else {
				employee.setSex("M".equals(imsEmployeeEntity.getSex()) ? 0 : 1);
				String position = ((IMSSaler) imsEmployeeEntity).getPosition();
				if ("1".equalsIgnoreCase(position)) {
					employee.setSalerType(0);
				} else if ("2".equalsIgnoreCase(position)) {
					employee.setSalerType(1);
				} else {
					employee.setSalerType(null);
				}
			}

			Organization organization = organizationDao.findByIMSIdAndType(imsEmployeeEntity.getParentId(),
					IMSOrganizationType.BUSIORG.getValue());
			if (organization == null) {
				organization = organizationService.findOneByName(imsEmployeeEntity.getParentName());
			}
			employee.setOrganization(organization);
			employeeService.setEmployeeSysUser(employee, sysRoleType);
			sysUserDao.save(employee.getSysUser());
			employeeService.save(employee);
		}
	}

	/**
	 * 同步导购员和员工信息，先全部逻辑删除同步过来的数据，再重新同步
	 */
	@Transactional
	public void synchronizeIMSEmployee() {
		try {
			// 先删除
			Searchable searchable = Searchable.newSearchable().addSearchFilter("isIMSData", SearchOperator.eq, true);
			commonDao.logicDeleteAll(Employee.class.getSimpleName(), searchable);

			List<IMSEmployee> imsEmployeeList = imsCommonDao.findAllIMSEmployee();
			synchronizeIMSEmployee(imsEmployeeList, IMSEmployeeTye.EMPLOYEE);

			List<IMSSaler> imsSalerList = imsCommonDao.findAllIMSSaler();
			synchronizeIMSEmployee(imsSalerList, IMSEmployeeTye.SALER);

			addImsLog(IMSTaskType.EMPLOYEE, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(IMSTaskType.EMPLOYEE, throwable.getMessage());
			throw new RuntimeException(throwable);

		}
	}

	/**
	 * 同步员工工资数据
	 */
	@Transactional
	public void synchronizeIMSEmployeeWage() {
		Calendar calendar = Calendar.getInstance();
		// 上一个月
		calendar.add(Calendar.MONTH, -1);
		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH) + 1;
		synchronizeIMSEmployeeWage(year, month);
	}

	/**
	 * 同步员工工资数据
	 */
	@Transactional
	public void synchronizeIMSEmployeeWage(Integer year, Integer month) {
		IMSTaskType taskType = IMSTaskType.EMPLOYEE_WAGE;
		taskType.setText(String.format("同步 %d年%d月 员工工资数据", year, month));
		try {
			// 第一次工资数据是否存在
			Integer wageVersion = 1;
			/*
			 * Integer wageVersion = 1; Searchable searchable =
			 * Searchable.newSearchable() .addSearchFilter("wageYear",
			 * SearchOperator.eq, year) .addSearchFilter("wageMonth",
			 * SearchOperator.eq, month) .addSearchFilter("wageVersion",
			 * SearchOperator.eq, wageVersion); if
			 * (employeeWageDao.exists(searchable)) { wageVersion = 2;
			 * searchable.setValue("wageVersion", SearchOperator.eq,
			 * wageVersion); if (employeeWageDao.exists(searchable)) { return; }
			 * }
			 */
			Calendar calendar = Calendar.getInstance();//获取日期
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month - 1);
			String wageYearMonth = DateFormatUtils.format(calendar, "yyyyMM");
			List<IMSSalerWage> imsEmployeeWageList = imsCommonDao.findAllIMSSalerWage(wageYearMonth);
			for (IMSSalerWage imsEmployeeWage : imsEmployeeWageList) {
				Employee employee = employeeService.findByIMSIdAndType(imsEmployeeWage.getEmployeeId(),
						IMSEmployeeTye.SALER.getValue());//寻找对应会员 
				Searchable searchables = Searchable.newSearchable()//查询过滤
						.addSearchFilter("employee", SearchOperator.eq, employee)
						.addSearchFilter("wageMonth", SearchOperator.eq, month)
						.addSearchFilter("wageYear", SearchOperator.eq, year);
				EmployeeWage employeeWage = employeeWageDao.findOne(searchables);	//是否存在会员工资数据			
				wageVersion = 1;//初始化工资次数
				if (employeeWage != null) {
					if (employeeWage.getWageVersion().equals(1)) {
						wageVersion = 2;//第一次工资数据存在
					}
					if (employeeWage.getWageVersion().equals(2))
						continue;//第二次存在跳出
				}
				else
                {
					employeeWage=new EmployeeWage();//不存在 新建
                }
				employeeWage.setWageYear(year);
				employeeWage.setWageMonth(month);
				employeeWage.setWageVersion(wageVersion);
				employeeWage.setActualWage(imsEmployeeWage.getActualWage());
				employeeWage.setAgeWage(imsEmployeeWage.getAgeWage());
				employeeWage.setAwardWage(imsEmployeeWage.getAwardWage());
				employeeWage.setBasicSalary(imsEmployeeWage.getBasicSalary());
				employeeWage.setCompanyInsurance(imsEmployeeWage.getCompanyInsurance());
				employeeWage.setExamineWage(imsEmployeeWage.getExamineWage());
				employeeWage.setHolidayFee(imsEmployeeWage.getHolidayFee());
				employeeWage.setOtherFee(imsEmployeeWage.getOtherFee());
				employeeWage.setOvertimeWage(imsEmployeeWage.getOvertimeWage());
				employeeWage.setRoyaltyWage(imsEmployeeWage.getRoyaltyWage());
				employeeWage.setSocialInsurance(imsEmployeeWage.getSocialInsurance());
				employeeWage.setTaxDeduction(imsEmployeeWage.getTaxDeduction());
				employeeWage.setVirtualWage(imsEmployeeWage.getVirtualWage());

				// Employee employee =
				// employeeService.findByIMSIdAndType(imsEmployeeWage.getEmployeeId(),
				// IMSEmployeeTye.SALER.getValue());
				if (employee != null) {
					employeeWage.setEmployee(employee);
					employeeWageDao.save(employeeWage);//保存

					WeiXinUser weiXinUser = employee.getWeiXinUser();
					if (weiXinUser != null) {
						WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
						templateMessage.setToUser(weiXinUser.getOpenId());
						templateMessage.setTemplateId("etNWdGeGTR2tTa_W9Z09c7B-qYraTRO_FvHD1iHeuQA");
						if (wxMpConfigStorage instanceof WxMpSpringCacheConfigStorge) {
							templateMessage.setUrl(((WxMpSpringCacheConfigStorge) wxMpConfigStorage).getBaseUrl()
									+ "weixin/workbench/employee/wage/index");
						}

						templateMessage.setTopColor("");
						templateMessage.getDatas().add(
								new WxMpTemplateData("first", String.format("%d年%d月工资如下：", year, month), "#173177"));
						templateMessage.getDatas().add(new WxMpTemplateData("keyword1", employee.getName(), "#173177"));
						templateMessage.getDatas().add(
								new WxMpTemplateData("keyword2", "实发工资：" + employeeWage.getActualWage(), "#173177"));
						templateMessage.getDatas().add(new WxMpTemplateData("remark", "如有疑问，请您及时提出，谢谢！", "#173177"));
						try {
							String msgId = wxMpService.templateSend(templateMessage);
							if (StringUtils.isNotEmpty(msgId)) {
								employeeWage.setIsInformed(true);
								employeeWageDao.save(employeeWage);
							}
						} catch (WxErrorException e) {
							e.printStackTrace();
						}
					}
				}
			}
			addImsLog(taskType, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(taskType, throwable.getMessage());
			throw new RuntimeException(throwable);
		}
	}

	/**
	 * 要上报的订单数据
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> findSalesOrder(Integer year, Integer month) {
		Searchable searchable = Searchable.newSearchable()
				.addSearchFilter("s.invoiceImageUrl", SearchOperator.isNotNull, null)
				.addSearchFilter("s.invoiceNo", SearchOperator.isNotNull, null)
				.addSearchFilter("s.customerName", SearchOperator.isNotNull, null);

		String sql = "select to_char(s.salesTime, 'yyyy-mm-dd') as salesTime, o.IMSCode as organizationCode,"
				+ " e.code as employeeCode, p.code as prodcuctCode, p.name as prodcuctName,"
				+ " SUM(sd.qty) as qty from sales_order s, sales_order_detail sd, employee e, product p, organization o"
				+ " where s.id = sd.salesOrderId" + " and s.salerId = e.id" + " and s.organizationId = o.id"
				+ " and sd.productId = p.id" + " and e.IMSId is not null " + " and extract(year from s.salesTime) = "
				+ year + " and extract(month from s.salesTime) = " + month
				+ " group by to_char(s.salesTime, 'yyyy-mm-dd'), o.IMSCode, p.name, p.code, e.code";
		return (List<Map<String, Object>>) salesOrderDao.findAll(sql, searchable, true, HashMap.class).getContent();
	}

	/**
	 * 更新订单为已经上传
	 * 
	 * @param year
	 * @param month
	 */
	@Transactional
	private void updateSalesOrderUploaded(Integer year, Integer month) {
		Searchable searchable = Searchable.newSearchable()
				.addSearchFilter("extract(year from s.salesTime)", SearchOperator.eq, year)
				.addSearchFilter("extract(month from s.salesTime)", SearchOperator.eq, month)
				.addSearchFilter("s.invoiceImageUrl", SearchOperator.isNotNull, null)
				.addSearchFilter("s.invoiceNo", SearchOperator.isNotNull, null)
				.addSearchFilter("s.customerName", SearchOperator.isNotNull, null);
		salesOrderDao.executeUpdate("update sales_order s set isUpload = 1", searchable, true);
	}

	/**
	 * 同步订单数据
	 */
	@Transactional(transactionManager = "imsTransactionManager")
	public void synchronizeIMSSalesOrder() {
		Calendar calendar = Calendar.getInstance();
		// 上一个月
		calendar.add(Calendar.MONTH, -1);
		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH) + 1;
		synchronizeIMSSalesOrder(year, month);
	}

	/**
	 * 同步订单数据
	 */
	@Transactional(transactionManager = "imsTransactionManager")
	public void synchronizeIMSSalesOrder(Integer year, Integer month) {
		IMSTaskType taskType = IMSTaskType.SALES_ORDER;
		taskType.setText(String.format("上报 %d年%d月 销量", year, month));
		try {
			List<Map<String, Object>> salesOrderList = findSalesOrder(year, month);

			if (!salesOrderList.isEmpty()) {
				imsCommonDao.deleteAllSalesOrder(year, month);
			}
			for (Map<String, Object> salesOrder : salesOrderList) {
				IMSSalesOrder imsSalesOrder = new IMSSalesOrder();
				Object orgObj = salesOrder.get("ORGANIZATIONCODE");
				Object proObj = salesOrder.get("PRODCUCTCODE");

				if (orgObj == null || proObj == null) {
					continue;
				}

				imsSalesOrder.setOrganizationCode(String.valueOf(orgObj));
				imsSalesOrder.setEmployeeCode(String.valueOf(salesOrder.get("EMPLOYEECODE")));
				try {
					imsSalesOrder.setSalesTime(
							DateUtils.parseDate(String.valueOf(salesOrder.get("SALESTIME")), "yyyy-MM-dd"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				imsSalesOrder.setQty(NumberUtils.toDouble(String.valueOf(salesOrder.get("QTY"))));
				imsSalesOrder.setProductCode(String.valueOf(proObj));
				imsSalesOrder.setProdcuctName(String.valueOf(salesOrder.get("PRODCUCTNAME")));
				imsSalesOrder.setImportDate(new Date());
				imsSalesOrder.setImportFlag("N");
				imsCommonDao.save(imsSalesOrder);
			}
			updateSalesOrderUploaded(year, month);
			addImsLog(IMSTaskType.SALES_ORDER, null);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable throwable = ExceptionUtils.getRootCause(e.getCause());
			addImsLog(IMSTaskType.SALES_ORDER, throwable.getMessage());
			throw new RuntimeException(throwable);
		}

	}
}
