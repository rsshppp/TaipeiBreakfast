package controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.service.ReportService;

import com.opensymphony.xwork2.ActionSupport;

public class GetReportAction extends ActionSupport{
    private ReportService service;
    private List<Map> list;
    private List<Object> numberList;
    private Integer shopID;
    private Integer year;
    private Integer month;
    private Integer date;
    private String shopArea;
    private String shopName;
    private Integer hour;
    
    public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}
    
	
	public List<Object> getNumberList() {
		return numberList;
	}

	public void setNumberList(List<Object> numberList) {
		this.numberList = numberList;
	}

	public void setService(ReportService service) {
		this.service = service;
	}

	public Integer getShopID() {
		return shopID;
	}

	public void setShopID(Integer shopID) {
		this.shopID = shopID;
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

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}
	
	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
    
	
	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public String getDaily(){
		try {
			list = new LinkedList();
//			List list2=service.getDailyReport(3, 2015, 5, 3);
			List list2=service.getDailyReport(shopID, year, month, date);
			
			List listTemp=list2;           //to get the sum of totalPrice
			Iterator iteTemp=listTemp.iterator();
			Double sumTotalP=0.0;
			while(iteTemp.hasNext()){
				Object[] item=(Object[])iteTemp.next();
				System.out.println(item[3]);
				sumTotalP=sumTotalP+(Double)item[3];
			}
			System.out.println(sumTotalP);
			
			Iterator ite=list2.iterator();
			while(ite.hasNext()){
				Map m1 = new HashMap<String, Object>();
				Object[] item=(Object[])ite.next();
//				shopID=(Integer)item[0];   //for understaning the type of item only
				System.out.print(item[0]+"  ");
				System.out.print(item[1]+" ");
				System.out.print(item[2]+" ");
				System.out.println(item[3]);
			    Double percentPrice=(((Double)item[3]/sumTotalP)*100);
			    percentPrice=round(percentPrice, 1);  //取到小數第一位  //使用上述方法
			    Object percent=percentPrice;          //為了擺入自訂格式map
			    System.out.println(percentPrice);
				m1.put("mealName",item[0]);   
				m1.put("countSum", item[1]); 
				m1.put("price",item[2]); 
				m1.put("totalPrice",item[3]);
				m1.put("percentPrice",percentPrice);
				list.add(m1);
			}
			return "success";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "success";
		}
    }
	
	public String getMonthly(){
		try {
			list = new LinkedList();
//			List list2=service.getDailyReport(3, 2015, 5);
			List list2=service.getMonthlyReport(shopID, year, month);
			
			List listTemp=list2;           //to get the sum of totalPrice
			Iterator iteTemp=listTemp.iterator();
			Double sumTotalP=0.0;
			while(iteTemp.hasNext()){
				Object[] item=(Object[])iteTemp.next();
				System.out.println(item[3]);
				sumTotalP=sumTotalP+(Double)item[3];
			}
			System.out.println(sumTotalP);
			
			Iterator ite=list2.iterator();
			while(ite.hasNext()){
				Map m1 = new HashMap<String, Object>();
				Object[] item=(Object[])ite.next();
//				shopID=(Integer)item[0];   //for understaning the type of item only
				System.out.print(item[0]+"  ");
				System.out.print(item[1]+" ");
				System.out.print(item[2]+" ");
				System.out.println(item[3]);
			    Double percentPrice=(((Double)item[3]/sumTotalP)*100);
			    percentPrice=round(percentPrice, 1);  //取到小數第一位  //使用上述方法
			    Object percent=percentPrice;          //為了擺入自訂格式map
			    System.out.println(percentPrice);
				m1.put("mealName",item[0]);   
				m1.put("countSum", item[1]); 
				m1.put("price",item[2]); 
				m1.put("totalPrice",item[3]);
				m1.put("percentPrice",percentPrice);
				list.add(m1);
			}
			return "success";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "success";
		}
	}
	
	public String getTimeReport(){
		try {
			numberList=service.getTimeReport(shopID, year, month, date);
			numberList.add(shopID);
			numberList.add(shopArea);
			numberList.add(shopName);
			return "success2";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "success2";
		}
	}
	
	public String getDetailTimeReport(){
		try {
			list = new LinkedList();
			List list2=service.getDetailTimeReport(shopID, year, month, date, hour);
			
			List listTemp=list2;           //to get the sum of totalPrice
			Iterator iteTemp=listTemp.iterator();
			Double sumTotalP=0.0;
			while(iteTemp.hasNext()){
				Object[] item=(Object[])iteTemp.next();
				System.out.println(item[3]);
				sumTotalP=sumTotalP+(Double)item[3];
			}
			System.out.println(sumTotalP);
			
			Iterator ite=list2.iterator();
			while(ite.hasNext()){
				Map m1 = new HashMap<String, Object>();
				Object[] item=(Object[])ite.next();
//				shopID=(Integer)item[0];   //for understaning the type of item only
				System.out.print(item[0]+"  ");
				System.out.print(item[1]+" ");
				System.out.print(item[2]+" ");
				System.out.println(item[3]);
			    Double percentPrice=(((Double)item[3]/sumTotalP)*100);
			    percentPrice=round(percentPrice, 1);  //取到小數第一位  //使用上述方法
			    Object percent=percentPrice;          //為了擺入自訂格式map
			    System.out.println(percentPrice);
				m1.put("mealName",item[0]);   
				m1.put("countSum", item[1]); 
				m1.put("price",item[2]); 
				m1.put("totalPrice",item[3]);
				m1.put("percentPrice",percentPrice);
				list.add(m1);
			}
			return "success";
		} catch (ParseException e) {
			e.printStackTrace();
			return "success";
		}
    }
}
