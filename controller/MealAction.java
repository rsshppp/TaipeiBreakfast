package controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import misc.FileToByte;
import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.service.MealKindListService;
import model.service.MealService;
import model.service.ShopService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import form.MealForm;
import form.ShopForm;

public class MealAction extends ActionSupport implements ServletRequestAware{
	HttpSession session=ServletActionContext.getRequest().getSession();
	private String jsondata;
	private String mealName;
	private MealService mealservice;
	private MealKindListService mealKindservice;
	private String redata;
	private MealForm bean;
	private Gson gson=new Gson();
	private ShopService shopService;
	private HttpServletRequest request;
	private Map<String, String> map=new HashMap<String, String>();
	
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

	public void setBean(MealForm bean) {
		this.bean = bean;
	}
	
	public MealForm getBean() {
		return bean;
	}

	public void setMealKindservice(MealKindListService mealKindservice) {
		this.mealKindservice = mealKindservice;
	}
	public String getRedata() {
		return redata;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public void setMealservice(MealService mealservice) {
		this.mealservice = mealservice;
	}
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String mealadd() {
		try{
		MealBean mbean=new MealBean();
		byte[] image;
		if(bean!=null){			
			System.out.println("進來了"+bean);
			if(bean.getMealImage()!=null){
				File file=bean.getMealImage();
				FileToByte ftb=new FileToByte();
				image=ftb.loadFile(file);
				mbean.setMealImage(image);
				System.out.println(image);
				map.put("image", gson.toJson(image));
			}
			mbean.setMealKindID(bean.getMealKindID());
			mbean.setMealName(bean.getMealName());
			mbean.setPrice(bean.getPrice());
			mbean.setShopID(bean.getShopID());
			if(mealservice.addMeal(mbean)){
				map.put("Message", "新增成功");
			}else{
				map.put("Message", "新增失敗");
			}
			redata=gson.toJson(map);
			System.out.println(redata);
		}
			return "meal";
		}catch(IOException e){
			map.put("Message", "新增失敗");
			return "meal";
		}
	}
	public String mealKind(){
		List<MealKindListBean> list=mealKindservice.selectAllKind();
		Type listType = new TypeToken<List<MealKindListBean>>() {}.getType();
		redata=gson.toJson(list,listType);
		return "mealKind";
	}
	public String shoplist(){
		OwnerBean ownerBean=new OwnerBean();
		ownerBean.setOwnID(Integer.parseInt(request.getParameter("owerID")));
		List<ShopBean> list=shopService.getShops(ownerBean);
		Iterator<ShopBean> shopBlist=list.iterator();
		List<ShopForm> listform=new ArrayList<ShopForm>();
		while(shopBlist.hasNext()){
			ShopBean bean=shopBlist.next();
			ShopForm form=new ShopForm();
			form.setShopID(bean.getShopID());
			form.setShopName(bean.getShopName());
			listform.add(form);
		}
		Type listType = new TypeToken<List<ShopForm>>() {}.getType();
		redata=gson.toJson(listform,listType);
		System.out.println(redata);

		return "meal";
	}
	
	public String menulist(){
		List<MealBean>mlist=mealservice.selectShopMeal(Integer.parseInt(request.getParameter("owerID")));
		
		return "meal";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
