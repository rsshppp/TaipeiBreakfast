package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import misc.FileToByte;
import model.bean.ShopBean;
import model.service.ShopService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import form.ShopFormByWeng;

public class ShopInsertAction extends ActionSupport implements SessionAware{
    private ShopService service;                         //ServletRequestAware不需要
//  private HttpServletRequest request;
    
    private String jsonString;

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
    
	public void setService(ShopService service) {
		this.service = service;
	}
//	public void setServletRequest(HttpServletRequest request) {
//		this.request = request;
//	}
	
	private ShopFormByWeng sbean;

	public ShopFormByWeng getSbean() {
		return sbean;
	}

	public void setSbean(ShopFormByWeng sbean) {
		this.sbean = sbean;
	}

	private Map<String, Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public String execute(){

			try {
				System.out.println(sbean);
				ShopBean bean=new ShopBean();
				bean.setShopName(sbean.getShopName());
				bean.setShopPhone(sbean.getShopPhone());
				bean.setShopCity(sbean.getShopCity());
				bean.setShopArea(sbean.getShopArea());
				bean.setShopAddr(sbean.getShopAddr());

				bean.setLastOrderNoon(sbean.getLastOrderNoon());
				bean.setLastOrderNight(sbean.getLastOrderNight());
				
				bean.setOwnID(sbean.getOwnID());
				
				
				byte[] image;
				if(sbean.getLogoImage()!=null){
					File file=sbean.getLogoImage();
					FileToByte ftb=new FileToByte();
					image=ftb.loadFile(file);
					bean.setLogoImage(image);
				}
				
				bean.setBeginBusinessTime(sbean.getBeginBusinessTime());
				bean.setEndBusinessTime(sbean.getEndBusinessTime());
				
				bean.setBusinessTimeNote(sbean.getBusinessTimeNote());
  //---------------------------------以上為塞值入bean----------------------------------
				if(service.insert(bean)){
					Gson gson=new Gson();
					jsonString=gson.toJson(bean);
					System.out.println(jsonString);
				}
				
				
				return "success";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "success";
			}

	}
    
}
