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
import form.MealListForm;
import form.ShopForm;

public class MealAction extends ActionSupport implements ServletRequestAware{
	private HttpSession session=ServletActionContext.getRequest().getSession();
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
		System.out.println("新增"+bean);
		try{
		MealBean mbean=new MealBean();
		byte[] image;
		if(bean!=null){			
			//System.out.println("進來了"+bean);
			if(bean.getMealImage()!=null){
				File file=bean.getMealImage();
				FileToByte ftb=new FileToByte();
				image=ftb.loadFile(file);
				mbean.setMealImage(image);
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
			//System.out.println(redata);
		}
			return "meal";
		}catch(IOException e){
			map.put("Message", "新增失敗");
			return "meal";
		}
	}
	public String mealKind(){
		Iterator<MealKindListBean> list=mealKindservice.selectAllKind().iterator();
		List<MealListForm> formlist=new ArrayList<MealListForm>();
		while(list.hasNext()){
			MealKindListBean bean=list.next();
			MealListForm form=new MealListForm();
			form.setMealKindID(bean.getMealKindID());
			form.setMealKindName(bean.getMealKindName());
			formlist.add(form);
		}
		Type listType = new TypeToken<List<MealListForm>>() {}.getType();
		redata=gson.toJson(formlist,listType);
		return "mealKind";
	}
	public String shoplist(){
		if(session.getAttribute("user")!=null){
			OwnerBean ownerBean=new OwnerBean();
			ownerBean.setOwnID(((OwnerBean)session.getAttribute("user")).getOwnID());
			//ownerBean.setOwnID(Integer.parseInt(request.getParameter("owerID")));
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
			//System.out.println(redata);
			return "meal";
		}else{
			redata=null;
			return "meal";
		}
	}
	
	public String menulist(){
		String shopID=request.getParameter("shopID");
		System.out.println(shopID);
		if(!shopID.equals("")&&shopID!=null){
			Iterator<MealBean> mlist=mealservice.selectShopMeal(Integer.parseInt(request.getParameter("shopID"))).iterator();
			List<MealListForm> mflist=new ArrayList<MealListForm>();
			while(mlist.hasNext()){
				MealBean mbean=mlist.next();
				MealListForm mform=new MealListForm();
				mform.setMealID(mbean.getMealID());
				//mform.setMealImage(mbean.getMealImage());
				mform.setMealKindName(mbean.getMealKindListBean().getMealKindName());
				mform.setMealName(mbean.getMealName());
				if(mbean.getMealStatus()){				
					mform.setMealStatus("販售中");
				}else{
					mform.setMealStatus("下架中");
				}
				mform.setPrice(mbean.getPrice());
				mflist.add(mform);
			}
			Type listType = new TypeToken<List<MealListForm>>() {}.getType();
			redata=gson.toJson(mflist,listType);
			return "meal";
		}else{
			redata=null;
			return "meal";
		}
	}
	
	public String mealchange(){
		System.out.println("修改"+bean);
		try{
			MealBean mbean=new MealBean();
			mbean.setMealID(bean.getMealID());
			mbean.setMealKindID(bean.getMealKindID());
			mbean.setMealName(bean.getMealName());
			mbean.setPrice(bean.getPrice());
			System.out.println("xxxxxxx="+bean.getImageChange());
			if(bean.getImageChange()){
				if(bean.getMealImage()!=null){
					File file=bean.getMealImage();
					FileToByte ftb=new FileToByte();
					byte[] image=ftb.loadFile(file);
					mbean.setMealImage(image);
				}else{
					mbean.setMealImage(null);
				}
			}else{
				mbean.setMealImage(mealservice.selectMealByMealID(bean.getMealID()).getMealImage());
			}
			if(mealservice.modifyMeal(mbean)){
				map.put("Message", "修改成功");
			}else{
				map.put("Message", "修改失敗");
			}
			redata=gson.toJson(map);
			System.out.println(redata);
			return "meal";
		}catch(IOException e){
			map.put("Message", "修改失敗");
			return "meal";
		}
	}
	public String onemeal(){
		System.out.println(request.getParameter("shopID"));
		System.out.println(request.getParameter("mealID"));
		MealBean mbean=mealservice.selectMealByMealID(Integer.parseInt((request.getParameter("mealID"))));
		//System.out.println(mbean);
		MealListForm mform=new MealListForm();
		if(mbean!=null){
			mform.setMealID(mbean.getMealID());
			mform.setMealName(mbean.getMealName());
//			if(mbean.getMealImage()==null){
//				mform.setMealImage(mbean.getMealKindListBean().getDefaultImage());
//			}else{
//				mform.setMealImage(mbean.getMealImage());
//			}
			mform.setShopID(mbean.getShopID());
			mform.setMealKindID(mbean.getMealKindID());
			mform.setPrice(mbean.getPrice());
			mform.setShopName(mbean.getShopBean().getShopName());
		}
		Type listType = new TypeToken<MealListForm>() {}.getType();
		redata=gson.toJson(mform,listType);
		System.out.println(redata);
		return "meal";
	}
	
	public String changestatus(){
		int mealID=Integer.parseInt(request.getParameter("mealID"));
		System.out.println(request.getParameter("mealID"));
		boolean mealStatus=mealservice.selectMealByMealID(mealID).getMealStatus();
		mealservice.modifyMealStatus(mealID, !mealStatus);
		return "meal";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
