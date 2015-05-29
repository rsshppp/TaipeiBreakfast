package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONValue;

import model.bean.ShopBean;
import model.service.ReportService;
import model.service.ShopService;

import com.opensymphony.xwork2.ActionSupport;

public class GetMapDetailAction extends ActionSupport{
	private ReportService reportService;
	private ShopService shopService;
	private String shopArea;
	private String jsonString;
	private Integer year;
	private Integer month;
	
	
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public String execute(){
		try {
			List<Object> listAll=new ArrayList();
		System.out.println(shopArea);
//		System.out.println(year);
//		System.out.println(month);
			List<ShopBean> list=shopService.selectShopArea(shopArea);
		    System.out.println(list);
		
			Iterator ite=list.iterator();
			while(ite.hasNext()){
			List<Object> listOne=new ArrayList();	
				
			ShopBean bean=(ShopBean)ite.next();
			System.out.println(bean.getShopName());
			System.out.println(bean.getShopArea());
			
			
			
//			System.out.println(bean.getShopID());
				//
				List<Object[]> listOneShop=reportService.getMonthlyReport(bean.getShopID(), year, month);
//				System.out.println(listOne);
				Iterator ite2=listOneShop.iterator();
				while(ite2.hasNext()){
					Object[] item=(Object[])ite2.next();
					System.out.print(item[0]+"  ");
					System.out.print(item[1]+" ");
					System.out.print(item[2]+" ");
					System.out.println(item[3]);
					
//					Map m2 = new HashMap<String, Object>();
					Map m1 = new HashMap<String, Object>();
					m1.put("ShopName",bean.getShopName());   
					m1.put("ShopArea",bean.getShopArea()); 
					m1.put("mealName",item[0]);   
					m1.put("count", item[1]); 
					m1.put("price",item[2]); 
					m1.put("totalprice",item[3]); 
					listOne.add(m1);
				}
				listAll.add(listOne);
			}
			jsonString=JSONValue.toJSONString(listAll);
			
			return "success";
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return "success";
		}
	}
}
