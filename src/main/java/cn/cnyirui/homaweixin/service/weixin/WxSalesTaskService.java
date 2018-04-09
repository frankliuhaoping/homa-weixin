package cn.cnyirui.homaweixin.service.weixin;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.weixin.WxSalesTaskDao;
import cn.cnyirui.homaweixin.model.po.SalesTask;

@Service
public class WxSalesTaskService extends BaseService<SalesTask> {

	@Resource
	WxSalesTaskDao wxSalesTaskDao;
	
	@Override
	public BaseDao<SalesTask> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unused")
	public ObjectNode getSalesTaskByEmployeeId(int year){
		EntityManager em = wxSalesTaskDao.getEntityManager();
		String employeeId = CurrentUserUtils.getEmployee(false).getId();
		
		//先查出员工某年的类型
		String sql1 = "select s.type,s.id from sales_task s where s.employeeId=?1 and s.year=?2";
		Query query1 = em.createNativeQuery(sql1);
		query1.setParameter(1, employeeId);
		query1.setParameter(2, year);
		List <Object []> o = query1.getResultList();
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		
		SalesTask salesTask = null;
		int type=0;
		if(o.size()>=1){
			Object [] ob = o.get(0);
		type =NumberUtils.toInt(ob[0].toString());
		salesTask = wxSalesTaskDao.findOne(ob[1].toString());
		}

		
		Double salesTask1 = 0.0;
		Double salesTask2 = 0.0;
		Double salesTask3 = 0.0;
		Double salesTask4 = 0.0;
		Double salesTask5 = 0.0;
		Double salesTask6 = 0.0;
		Double salesTask7 = 0.0;
		Double salesTask8 = 0.0;
		Double salesTask9 = 0.0;
		Double salesTask10 = 0.0;
		Double salesTask11 = 0.0;
		Double salesTask12 = 0.0;
		
		
		if(salesTask!=null){
			salesTask1 = salesTask.getMonth1().doubleValue();
			salesTask2 =salesTask.getMonth2().doubleValue();
			salesTask3 = salesTask.getMonth3().doubleValue();
			salesTask4 = salesTask.getMonth4().doubleValue();
			salesTask5 = salesTask.getMonth5().doubleValue();
			salesTask6 = salesTask.getMonth6().doubleValue();
			salesTask7 = salesTask.getMonth7().doubleValue();
			salesTask8 = salesTask.getMonth8().doubleValue();
			salesTask9 = salesTask.getMonth9().doubleValue();
			salesTask10 = salesTask.getMonth10().doubleValue();
			salesTask11 = salesTask.getMonth11().doubleValue();
			salesTask12 =salesTask.getMonth12().doubleValue();
		}
		
		
		

		if(type==0){

			//类型 0表示金额
			
			String sql = "select t.amount_month1,t.amount_month2,t.amount_month3,t.amount_month4,t.amount_month5,t.amount_month6"
					+ ",t.amount_month7,t.amount_month8,t.amount_month9,t.amount_month10,t.amount_month11,t.amount_month12"
					+ " from v_sales_order_h t where t.salerId=?1 and t.salesYear=?2";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, employeeId);
			query.setParameter(2, year);
			List<Object []> orgList = query.getResultList();
			//防止数据库一年有多条数据，默认去第一条，防止报错
			
			if(orgList.size()>=1){
			Object [] ob= orgList.get(0);
			
			Double month1 =(NumberUtils.toDouble(String.valueOf(ob[0]))/salesTask1);
			Double month2 =(NumberUtils.toDouble(String.valueOf(ob[1]))/salesTask2);
			Double month3 =(NumberUtils.toDouble(String.valueOf(ob[2]))/salesTask3);
			Double month4 =(NumberUtils.toDouble(String.valueOf(ob[3]))/salesTask4);
			Double month5 =(NumberUtils.toDouble(String.valueOf(ob[4]))/salesTask5);
			Double month6 =(NumberUtils.toDouble(String.valueOf(ob[5]))/salesTask6);
			Double month7 =(NumberUtils.toDouble(String.valueOf(ob[6]))/salesTask7);
			Double month8 =(NumberUtils.toDouble(String.valueOf(ob[7]))/salesTask8);
			Double month9 =(NumberUtils.toDouble(String.valueOf(ob[8]))/salesTask9);
			Double month10 =(NumberUtils.toDouble(String.valueOf(ob[9]))/salesTask10);
			Double month11 =(NumberUtils.toDouble(String.valueOf(ob[10]))/salesTask11);
			Double month12 =(NumberUtils.toDouble(String.valueOf(ob[11]))/salesTask12);
			
			
			if(month1.toString().equals("NaN")){
				month1=0.0;
			}
			
			if(month2.toString().equals("NaN")){
				month2=0.0;
			}
			
			if(month3.toString().equals("NaN")){
				month3=0.0;
			}
			
			if(month4.toString().equals("NaN")){
				month4=0.0;
			}
			
			if(month5.toString().equals("NaN")){
				month5=0.0;
			}
			
			if(month6.toString().equals("NaN")){
				month6=0.0;
			}
			
			if(month7.toString().equals("NaN")){
				month7=0.0;
			}
			
			if(month8.toString().equals("NaN")){
				month8=0.0;
			}
			
			if(month9.toString().equals("NaN")){
				month9=0.0;
			}
			
			if(month10.toString().equals("NaN")){
				month10=0.0;
			}
			
			if(month11.toString().equals("NaN")){
				month11=0.0;
			}
			
			if(month12.toString().equals("NaN")){
				month12=0.0;
			}
			
			if(month1.toString().equals("Infinity")){
				month1=0.0;
			}
			
			if(month2.toString().equals("Infinity")){
				month2=0.0;
			}
			
			if(month3.toString().equals("Infinity")){
				month3=0.0;
			}
			
			if(month4.toString().equals("Infinity")){
				month4=0.0;
			}
			
			if(month5.toString().equals("Infinity")){
				month5=0.0;
			}
			
			if(month6.toString().equals("Infinity")){
				month6=0.0;
			}
			
			if(month7.toString().equals("Infinity")){
				month7=0.0;
			}
			
			if(month8.toString().equals("Infinity")){
				month8=0.0;
			}
			
			if(month9.toString().equals("Infinity")){
				month9=0.0;
			}
			
			if(month10.toString().equals("Infinity")){
				month10=0.0;
			}
			
			if(month11.toString().equals("Infinity")){
				month11=0.0;
			}
			
			if(month12.toString().equals("Infinity")){
				month12=0.0;
			}
			
			//月度任务
			objectNode.put("taskmonth1", NumberUtils.toDouble(String.valueOf(ob[0])));
			objectNode.put("taskmonth2", NumberUtils.toDouble(String.valueOf(ob[1])));
			objectNode.put("taskmonth3", NumberUtils.toDouble(String.valueOf(ob[2])));
			objectNode.put("taskmonth4", NumberUtils.toDouble(String.valueOf(ob[3])));
			objectNode.put("taskmonth5", NumberUtils.toDouble(String.valueOf(ob[4])));
			objectNode.put("taskmonth6", NumberUtils.toDouble(String.valueOf(ob[5])));
			objectNode.put("taskmonth7", NumberUtils.toDouble(String.valueOf(ob[6])));
			objectNode.put("taskmonth8", NumberUtils.toDouble(String.valueOf(ob[7])));
			objectNode.put("taskmonth9", NumberUtils.toDouble(String.valueOf(ob[8])));
			objectNode.put("taskmonth10", NumberUtils.toDouble(String.valueOf(ob[9])));
			objectNode.put("taskmonth11", NumberUtils.toDouble(String.valueOf(ob[10])));
			objectNode.put("taskmonth12", NumberUtils.toDouble(String.valueOf(ob[11])));
			
			
			
			objectNode.put("totalmonth1", salesTask1);
			objectNode.put("totalmonth2", salesTask2);
			objectNode.put("totalmonth3", salesTask3);
			objectNode.put("totalmonth4", salesTask4);
			objectNode.put("totalmonth5", salesTask5);
			objectNode.put("totalmonth6", salesTask6);
			objectNode.put("totalmonth7", salesTask7);
			objectNode.put("totalmonth8", salesTask8);
			objectNode.put("totalmonth9", salesTask9);
			objectNode.put("totalmonth10", salesTask10);
			objectNode.put("totalmonth11", salesTask11);
			objectNode.put("totalmonth12", salesTask12);
			
			
			Double finishTotal=NumberUtils.toDouble(String.valueOf(ob[0]))
					+NumberUtils.toDouble(String.valueOf(ob[1]))
					+NumberUtils.toDouble(String.valueOf(ob[2]))
					+NumberUtils.toDouble(String.valueOf(ob[3]))
					+NumberUtils.toDouble(String.valueOf(ob[4]))
					+NumberUtils.toDouble(String.valueOf(ob[5]))
					+NumberUtils.toDouble(String.valueOf(ob[6]))
					+NumberUtils.toDouble(String.valueOf(ob[7]))
					+NumberUtils.toDouble(String.valueOf(ob[8]))
					+NumberUtils.toDouble(String.valueOf(ob[9]))
					+NumberUtils.toDouble(String.valueOf(ob[10]))
					+NumberUtils.toDouble(String.valueOf(ob[11]));
			
			Double taskTotal=salesTask1
					+salesTask2
					+salesTask3
					+salesTask4
					+salesTask5
					+salesTask6
					+salesTask7
					+salesTask8
					+salesTask9
					+salesTask10
					+salesTask11
					+salesTask12;
					
			objectNode.put("finishTotal", finishTotal.intValue()+"元");	
			objectNode.put("taskTotal", taskTotal.intValue()+"元");		
			
			//年度百分比
			
			Double rate = finishTotal/taskTotal;
			if(rate.toString().equals("NaN")){
				rate=0.0;
			}
			if(rate.toString().equals("Infinity")){
				rate=0.0;
			}
			
			objectNode.put("rate", rate);	
			
			
			
			objectNode.put("type", type);
			objectNode.put("month1", month1);
			objectNode.put("month2", month2);
			objectNode.put("month3", month3);
			objectNode.put("month4", month4);
			objectNode.put("month5", month5);
			objectNode.put("month6", month6);
			objectNode.put("month7", month7);
			objectNode.put("month8", month8);
			objectNode.put("month9", month9);
			objectNode.put("month10", month10);
			objectNode.put("month11", month11);
			objectNode.put("month12", month12);
			}else{
				//  视图没有数据 即为没有销售到任何东西
				
				objectNode.put("taskmonth1", 0);
				objectNode.put("taskmonth2",0);
				objectNode.put("taskmonth3", 0);
				objectNode.put("taskmonth4", 0);
				objectNode.put("taskmonth5",0);
				objectNode.put("taskmonth6", 0);
				objectNode.put("taskmonth7", 0);
				objectNode.put("taskmonth8", 0);
				objectNode.put("taskmonth9", 0);
				objectNode.put("taskmonth10",0);
				objectNode.put("taskmonth11",0);
				objectNode.put("taskmonth12", 0);
				
				
				objectNode.put("totalmonth1", salesTask1);
				objectNode.put("totalmonth2", salesTask2);
				objectNode.put("totalmonth3", salesTask3);
				objectNode.put("totalmonth4", salesTask4);
				objectNode.put("totalmonth5", salesTask5);
				objectNode.put("totalmonth6", salesTask6);
				objectNode.put("totalmonth7", salesTask7);
				objectNode.put("totalmonth8", salesTask8);
				objectNode.put("totalmonth9", salesTask9);
				objectNode.put("totalmonth10", salesTask10);
				objectNode.put("totalmonth11", salesTask11);
				objectNode.put("totalmonth12", salesTask12);
				
				objectNode.put("type", type);
				objectNode.put("month1", 0);
				objectNode.put("month2", 0);
				objectNode.put("month3", 0);
				objectNode.put("month4", 0);
				objectNode.put("month5", 0);
				objectNode.put("month6", 0);
				objectNode.put("month7", 0);
				objectNode.put("month8", 0);
				objectNode.put("month9", 0);
				objectNode.put("month10", 0);
				objectNode.put("month11", 0);
				objectNode.put("month12", 0);
				
				Double taskTotal=salesTask1+salesTask2+salesTask3+salesTask4+salesTask5+salesTask6+salesTask7+salesTask8+salesTask9+salesTask10+salesTask11+salesTask12;
				objectNode.put("finishTotal", 0+"元");	
				objectNode.put("taskTotal", taskTotal.intValue()+"元");	
				objectNode.put("rate", 0.0);	
			}
			
			
		}else if(type==1){
			//1表示数量
			String sql = "select t.qty_month1,t.qty_month2,t.qty_month3,t.qty_month4,t.qty_month5,t.qty_month6"
					+ ",t.qty_month7,t.qty_month8,t.qty_month9,t.qty_month10,t.qty_month11,t.qty_month12"
					+ " from v_sales_order_h t where t.salerId=?1 and t.salesYear=?2";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, employeeId);
			query.setParameter(2, year);
			List<Object []> orgList = query.getResultList();
			//防止数据库一年有多条数据，默认去第一条，防止报错
			
			if(orgList.size()>=1){
		
			Object [] ob= orgList.get(0);
			
			Double month1 =(NumberUtils.toDouble(String.valueOf(ob[0]))/salesTask1);
			Double month2 =(NumberUtils.toDouble(String.valueOf(ob[1]))/salesTask2);
			Double month3 =(NumberUtils.toDouble(String.valueOf(ob[2]))/salesTask3);
			Double month4 =(NumberUtils.toDouble(String.valueOf(ob[3]))/salesTask4);
			Double month5 =(NumberUtils.toDouble(String.valueOf(ob[4]))/salesTask5);
			Double month6 =(NumberUtils.toDouble(String.valueOf(ob[5]))/salesTask6);
			Double month7 =(NumberUtils.toDouble(String.valueOf(ob[6]))/salesTask7);
			Double month8 =(NumberUtils.toDouble(String.valueOf(ob[7]))/salesTask8);
			Double month9 =(NumberUtils.toDouble(String.valueOf(ob[8]))/salesTask9);
			Double month10 =(NumberUtils.toDouble(String.valueOf(ob[9]))/salesTask10);
			Double month11 =(NumberUtils.toDouble(String.valueOf(ob[10]))/salesTask11);
			Double month12 =(NumberUtils.toDouble(String.valueOf(ob[11]))/salesTask12);
			
			
			if(month1.toString().equals("NaN")){
				month1=0.0;
			}
			
			if(month2.toString().equals("NaN")){
				month2=0.0;
			}
			
			if(month3.toString().equals("NaN")){
				month3=0.0;
			}
			
			if(month4.toString().equals("NaN")){
				month4=0.0;
			}
			
			if(month5.toString().equals("NaN")){
				month5=0.0;
			}
			
			if(month6.toString().equals("NaN")){
				month6=0.0;
			}
			
			if(month7.toString().equals("NaN")){
				month7=0.0;
			}
			
			if(month8.toString().equals("NaN")){
				month8=0.0;
			}
			
			if(month9.toString().equals("NaN")){
				month9=0.0;
			}
			
			if(month10.toString().equals("NaN")){
				month10=0.0;
			}
			
			if(month11.toString().equals("NaN")){
				month11=0.0;
			}
			
			if(month12.toString().equals("NaN")){
				month12=0.0;
			}
			
			if(month1.toString().equals("Infinity")){
				month1=0.0;
			}
			
			if(month2.toString().equals("Infinity")){
				month2=0.0;
			}
			
			if(month3.toString().equals("Infinity")){
				month3=0.0;
			}
			
			if(month4.toString().equals("Infinity")){
				month4=0.0;
			}
			
			if(month5.toString().equals("Infinity")){
				month5=0.0;
			}
			
			if(month6.toString().equals("Infinity")){
				month6=0.0;
			}
			
			if(month7.toString().equals("Infinity")){
				month7=0.0;
			}
			
			if(month8.toString().equals("Infinity")){
				month8=0.0;
			}
			
			if(month9.toString().equals("Infinity")){
				month9=0.0;
			}
			
			if(month10.toString().equals("Infinity")){
				month10=0.0;
			}
			
			if(month11.toString().equals("Infinity")){
				month11=0.0;
			}
			
			if(month12.toString().equals("Infinity")){
				month12=0.0;
			}
			
			//月度任务
			objectNode.put("taskmonth1", NumberUtils.toDouble(String.valueOf(ob[0])));
			objectNode.put("taskmonth2", NumberUtils.toDouble(String.valueOf(ob[1])));
			objectNode.put("taskmonth3", NumberUtils.toDouble(String.valueOf(ob[2])));
			objectNode.put("taskmonth4", NumberUtils.toDouble(String.valueOf(ob[3])));
			objectNode.put("taskmonth5", NumberUtils.toDouble(String.valueOf(ob[4])));
			objectNode.put("taskmonth6", NumberUtils.toDouble(String.valueOf(ob[5])));
			objectNode.put("taskmonth7", NumberUtils.toDouble(String.valueOf(ob[6])));
			objectNode.put("taskmonth8", NumberUtils.toDouble(String.valueOf(ob[7])));
			objectNode.put("taskmonth9", NumberUtils.toDouble(String.valueOf(ob[8])));
			objectNode.put("taskmonth10", NumberUtils.toDouble(String.valueOf(ob[9])));
			objectNode.put("taskmonth11", NumberUtils.toDouble(String.valueOf(ob[10])));
			objectNode.put("taskmonth12", NumberUtils.toDouble(String.valueOf(ob[11])));
			
			
			
			objectNode.put("totalmonth1", salesTask1);
			objectNode.put("totalmonth2", salesTask2);
			objectNode.put("totalmonth3", salesTask3);
			objectNode.put("totalmonth4", salesTask4);
			objectNode.put("totalmonth5", salesTask5);
			objectNode.put("totalmonth6", salesTask6);
			objectNode.put("totalmonth7", salesTask7);
			objectNode.put("totalmonth8", salesTask8);
			objectNode.put("totalmonth9", salesTask9);
			objectNode.put("totalmonth10", salesTask10);
			objectNode.put("totalmonth11", salesTask11);
			objectNode.put("totalmonth12", salesTask12);
            
			
			double finishTotal=NumberUtils.toDouble(String.valueOf(ob[0]))
					+NumberUtils.toDouble(String.valueOf(ob[1]))
					+NumberUtils.toDouble(String.valueOf(ob[2]))
					+NumberUtils.toDouble(String.valueOf(ob[3]))
					+NumberUtils.toDouble(String.valueOf(ob[4]))
					+NumberUtils.toDouble(String.valueOf(ob[5]))
					+NumberUtils.toDouble(String.valueOf(ob[6]))
					+NumberUtils.toDouble(String.valueOf(ob[7]))
					+NumberUtils.toDouble(String.valueOf(ob[8]))
					+NumberUtils.toDouble(String.valueOf(ob[9]))
					+NumberUtils.toDouble(String.valueOf(ob[10]))
					+NumberUtils.toDouble(String.valueOf(ob[11]));
			
			double taskTotal=salesTask1
					+salesTask2
					+salesTask3
					+salesTask4
					+salesTask5
					+salesTask6
					+salesTask7
					+salesTask8
					+salesTask9
					+salesTask10
					+salesTask11
					+salesTask12;
					
			objectNode.put("finishTotal", finishTotal+"(台)");	
			objectNode.put("taskTotal", taskTotal+"(台)");	
	        //年度百分比
			Double rate = finishTotal/taskTotal;
			if(rate.toString().equals("NaN")){
				rate=0.0;
			}
			if(rate.toString().equals("Infinity")){
				rate=0.0;
			}
			
			objectNode.put("rate", rate);	
		
			
			objectNode.put("month1", month1);
			objectNode.put("month2", month2);
			objectNode.put("month3", month3);
			objectNode.put("month4", month4);
			objectNode.put("month5", month5);
			objectNode.put("month6", month6);
			objectNode.put("month7", month7);
			objectNode.put("month8", month8);
			objectNode.put("month9", month9);
			objectNode.put("month10", month10);
			objectNode.put("month11", month11);
			objectNode.put("month12", month12);
			objectNode.put("type", type);
			
			}else{
				//  视图没有数据 即为没有销售到任何东西
				
				objectNode.put("taskmonth1", 0);
				objectNode.put("taskmonth2",0);
				objectNode.put("taskmonth3", 0);
				objectNode.put("taskmonth4", 0);
				objectNode.put("taskmonth5",0);
				objectNode.put("taskmonth6", 0);
				objectNode.put("taskmonth7", 0);
				objectNode.put("taskmonth8", 0);
				objectNode.put("taskmonth9", 0);
				objectNode.put("taskmonth10",0);
				objectNode.put("taskmonth11",0);
				objectNode.put("taskmonth12", 0);
				
				
				objectNode.put("totalmonth1", salesTask1);
				objectNode.put("totalmonth2", salesTask2);
				objectNode.put("totalmonth3", salesTask3);
				objectNode.put("totalmonth4", salesTask4);
				objectNode.put("totalmonth5", salesTask5);
				objectNode.put("totalmonth6", salesTask6);
				objectNode.put("totalmonth7", salesTask7);
				objectNode.put("totalmonth8", salesTask8);
				objectNode.put("totalmonth9", salesTask9);
				objectNode.put("totalmonth10", salesTask10);
				objectNode.put("totalmonth11", salesTask11);
				objectNode.put("totalmonth12", salesTask12);
				
				objectNode.put("type", type);
				objectNode.put("month1", 0);
				objectNode.put("month2", 0);
				objectNode.put("month3", 0);
				objectNode.put("month4", 0);
				objectNode.put("month5", 0);
				objectNode.put("month6", 0);
				objectNode.put("month7", 0);
				objectNode.put("month8", 0);
				objectNode.put("month9", 0);
				objectNode.put("month10", 0);
				objectNode.put("month11", 0);
				objectNode.put("month12", 0);
				
				double taskTotal=salesTask1+salesTask2+salesTask3+salesTask4+salesTask5+salesTask6+salesTask7+salesTask8+salesTask9+salesTask10+salesTask11+salesTask12;
				objectNode.put("finishTotal", 0+"台");	
				objectNode.put("taskTotal", taskTotal+"台");	
				objectNode.put("rate", 0.0);	
			}
			
		}
		

		return objectNode;	
	}
}
