package controller;

import javax.servlet.http.HttpServletRequest;

import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.service.MealKindListService;
import model.service.MealService;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
 
public class ImageAction extends ActionSupport implements ServletRequestAware {
	private MealService mealservice;
	private MealKindListService mealKindService;
	byte[] imageInByte = null;
	String imageId;
	String typeId;
	
	public MealKindListService getMealKindService() {
		return mealKindService;
	}

	public void setMealKindService(MealKindListService mealKindService) {
		this.mealKindService = mealKindService;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public void setMealservice(MealService mealservice) {
		this.mealservice = mealservice;
	}

	private HttpServletRequest servletRequest;
 
	public String getImageId() {
		return imageId;
	}
 
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
 
	public ImageAction() {
		System.out.println("ImageAction");
	}
 
	public String execute() {
		return SUCCESS;
	}
	
	public String getMealImage(){
		return "image";
	}
	
	public byte[] getCustomImageInBytes() {
		System.out.println(imageId);
		if(imageId!=null&&typeId==null){
			MealBean mbean=mealservice.selectMealByMealID(Integer.parseInt(imageId));
			if(mbean.getMealImage()==null){
				imageInByte=mbean.getMealKindListBean().getDefaultImage();
			}else{
				imageInByte=mbean.getMealImage();
			}
		}else if(imageId==null&&typeId!=null){
			MealKindListBean mbean=mealKindService.selectOneKind(Integer.parseInt(typeId));
			imageInByte=mbean.getDefaultImage();
		}
		imageId=null;
		typeId=null;
		return imageInByte;
	}
	public String getCustomContentType() {
		return "image/*";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
 
	}
 
}
