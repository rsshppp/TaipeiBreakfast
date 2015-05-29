package controller;

import java.util.List;

import model.bean.deliverValuesOnly.HistoryRecordBean;
import model.service.TransactionHistoryRecordService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class GetShopHistoryAction extends ActionSupport {
    private TransactionHistoryRecordService service;
    private Integer shopID;
    private String jsonString;   //一般Struts之json傳遞方法無法適用
    
	public Integer getShopID() {
		return shopID;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	
	public void setService(TransactionHistoryRecordService service) {
		this.service = service;
	}
    
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	
	public String execute(){
		List<HistoryRecordBean> list=service.selectHistoryRecord(shopID, 4);
		System.out.println(list);
		Gson gson=new Gson();
		jsonString=gson.toJson(list);
		return "success";
	}
}
