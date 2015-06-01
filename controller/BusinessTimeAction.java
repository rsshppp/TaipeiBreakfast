package controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.bean.DaysoffBean;
import model.bean.ShopBean;
import model.service.ShopService;

public class BusinessTimeAction {
	private ShopService shopService;
	//struts2傳入參數
	private Integer shopID;
	private String beginBusinessTime;
	private String endBusinessTime;
	private String businessTimeNote;
	private String daysoff;
	private String[] removeday;
	
	//struts2傳回結果
	private List<ShopBean> list;

	private String result;
	private InputStream inputStream;
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	
	public void setBeginBusinessTime(String beginBusinessTime) {
		this.beginBusinessTime = beginBusinessTime;
	}

	public void setEndBusinessTime(String endBusinessTime) {
		this.endBusinessTime = endBusinessTime;
	}

	public void setBusinessTimeNote(String businessTimeNote) {
		this.businessTimeNote = businessTimeNote;
	}

	public List<ShopBean> getList() {
		return list;
	}

	public String getResult() {
		return result;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setDaysoff(String daysoff) {
		this.daysoff = daysoff;
	}

	public void setRemoveday(String[] removeday) {
		this.removeday = removeday;
	}

	public String queryShopBusinessTime(){
		ShopBean sbean = new ShopBean();
		sbean.setShopID(shopID);
		ShopBean result =shopService.queryShopBusinessTimeAndDaysoff(sbean);
		sbean.setBeginBusinessTime(result.getBeginBusinessTime());
		sbean.setBusinessTimeNote(result.getBusinessTimeNote());
		Set<DaysoffBean> orderResult = new TreeSet<DaysoffBean>(new Comparator<DaysoffBean>(){
			@Override
			public int compare(DaysoffBean o1, DaysoffBean o2) {
				if(o1.getDaysoff().getTime()>o2.getDaysoff().getTime()){
					return 1;
				}else if(o1.getDaysoff().getTime()<o2.getDaysoff().getTime()){
					return -1;
				}
				return 0;
			}
		});
		Set<DaysoffBean> beans =result.getDaysoffBeans();
		Iterator iterator =  beans.iterator();
		while(iterator.hasNext()){
			DaysoffBean temp = (DaysoffBean) iterator.next();
			orderResult.add(temp);
		}
		sbean.setDaysoffBeans(orderResult);
		sbean.setEndBusinessTime(result.getEndBusinessTime());
		sbean.setMealBeans(null);
		list = new ArrayList<ShopBean>();
		list.add(sbean);
		return "businessTime";
	}
	public String updateBusinessTime(){
		ShopBean sbean = new ShopBean();
		sbean.setShopID(shopID);
		if(beginBusinessTime != null && endBusinessTime != null){
			System.out.println(beginBusinessTime);
			System.out.println(endBusinessTime);
			sbean.setBeginBusinessTime(Time.valueOf(beginBusinessTime));
			sbean.setEndBusinessTime(Time.valueOf(endBusinessTime));
			if(shopService.updateShopBusinessTime(sbean)){
				this.result ="true";
			}else{
				this.result ="false";
			}
		}
		if (businessTimeNote != null){
			sbean.setBusinessTimeNote(businessTimeNote);
			sbean.setShopID(shopID);
			if(shopService.updateShopBusinessTimeNote(sbean)){
				this.result ="true";
			}else{
				this.result ="false";
			}
		}
		this.inputStream = new StringBufferInputStream(this.result);
		return "updateBusinessTime";
	}
	public String updateDayoff(){
		DaysoffBean bean = new DaysoffBean();
		bean.setDaysoff(Date.valueOf(daysoff));
		bean.setShopID(shopID);
		if(shopService.insertDaysoff(bean)){
			this.result ="true";
		}else{
			this.result ="false";
		}
		this.inputStream = new StringBufferInputStream(this.result);
		return "updateDayoff";
	}
	public String removeDaysoff(){
		DaysoffBean bean;
		if(removeday != null){
			for(int i = 0; i < this.removeday.length;i++){
				bean = new DaysoffBean();
				bean.setDaysoff(Date.valueOf(this.removeday[i]));
				bean.setShopID(shopID);
				shopService.deleteDaysoff(bean);
			}
			try {
				this.inputStream = new ByteArrayInputStream("true".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			try {
				this.inputStream = new ByteArrayInputStream("false".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "removeDaysoff";
	}
}
