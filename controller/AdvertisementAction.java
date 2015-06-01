package controller;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import misc.FileToByte;
import model.bean.AdvertisementBean;
import model.bean.AdvertisementStatusBean;
import model.bean.MealBean;
import model.bean.MealKindListBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.service.AdvertisementService;
import model.service.AdvertisementStatusService;
import model.service.OwnerService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import form.AdForm;
import form.AdminADForm;

public class AdvertisementAction extends ActionSupport implements ServletRequestAware {
	private HttpSession session=ServletActionContext.getRequest().getSession();
	private AdvertisementService advertisementService;
	private AdvertisementStatusService advertisementStatusService;
	private OwnerService ownerService;
	private Gson gson=new Gson();
	private String redata;
	private AdForm readform;
	private HttpServletRequest request;
	private String imageId;
	private int typeID;
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public AdForm getReadform() {
		return readform;
	}
	public void setReadform(AdForm readform) {
		this.readform = readform;
	}
	public String getRedata() {
		return redata;
	}
	public void setOwnerService(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	public void setAdvertisementService(AdvertisementService advertisementService) {
		this.advertisementService = advertisementService;
	}
	public void setAdvertisementStatusService(
			AdvertisementStatusService advertisementStatusService) {
		this.advertisementStatusService = advertisementStatusService;
	}
	public String addAD(){
		try{
			AdvertisementBean bean=new AdvertisementBean();
			bean.setAdvertisementStatusID(1);
			bean.setContext(readform.getTitle()+"xxx"+readform.getContext());
			bean.setDays(readform.getDays());
			bean.setShopID(readform.getShopID());
			if(readform.getAdImage()!=null){
				File file=readform.getAdImage();
				FileToByte ftb=new FileToByte();
				byte[] image=ftb.loadFile(file);
				bean.setImage(image);
			}
			advertisementService.addAd(bean);
		}catch(Exception e){
			System.out.println("錯誤");
		}
		readform=null;
		return"AD";
	}
	
	public String aDTable(){
		if(session.getAttribute("user")!=null){
			int ownerID=((OwnerBean)session.getAttribute("user")).getOwnID();
			//int ownerID=1;
			Iterator<ShopBean> shoplist=ownerService.getShops(ownerID).iterator();
			ShopBean shop;
			AdForm adform;
			List<AdForm> adformlist=new ArrayList<AdForm>();
			while(shoplist.hasNext()){
				shop=shoplist.next();
				Iterator<AdvertisementBean> adlist=advertisementService.selectShopAd(shop.getShopID()).iterator();
				while(adlist.hasNext()){
					adform=new AdForm();
					adform.setShopName(shop.getShopName());
					AdvertisementBean adbean=adlist.next();
					if(adbean.getAdvertisementID()!=1){
					//System.out.println(adbean.getAdvertisementID()+" , "+adbean.getContext());
						if(!adbean.getContext().equals("xxx")){
							adform.setTitle(adbean.getContext().split("xxx")[0]);
							adform.setContext(adbean.getContext().split("xxx")[1]);
						}else{
							adform.setTitle("廣告申請");
							adform.setContext("廣告申請");
						}
						adform.setDays(adbean.getDays());
						adform.setAdStatus(adbean.getAdvertisementStatusBean().getAdvertisementStatus());
						adformlist.add(adform);
					}
				}
			}
			Type listType = new TypeToken<List<AdForm>>() {}.getType();
			redata=gson.toJson(adformlist,listType);

		}
		return "AD";
	}
	public String checkAD(){
		System.out.println(typeID);
			List<AdminADForm> adformlist=new ArrayList<AdminADForm>();
			AdminADForm adform;
			Iterator<AdvertisementBean> adlist=advertisementService.selectAllAd().iterator();
			while(adlist.hasNext()){
				adform=new AdminADForm();
				AdvertisementBean adbean=adlist.next();
				if(adbean.getAdvertisementID()!=1&&(typeID==0||adbean.getAdvertisementStatusID()==typeID)){
					adform.setAdID(adbean.getAdvertisementID());
					adform.setOwerID(adbean.getShopBean().getOwnID());
					adform.setShopName(adbean.getShopBean().getShopName());
					if(!adbean.getContext().equals("xxx")){
						adform.setTitle(adbean.getContext().split("xxx")[0]);
						adform.setContext(adbean.getContext().split("xxx")[1]);
					}else{
						adform.setTitle("廣告申請");
						adform.setContext("廣告申請");
					}
					adform.setDays(adbean.getDays());
					adform.setAdStatus(adbean.getAdvertisementStatusBean().getAdvertisementStatus());
					adform.setAdStatusID(adbean.getAdvertisementStatusID());
					adformlist.add(adform);
				}
			}
		
		Type listType = new TypeToken<List<AdminADForm>>() {}.getType();
		redata=gson.toJson(adformlist,listType);

		return "AD";
	}
	public String allStatus(){
		List<AdvertisementStatusBean> list=advertisementStatusService.selectAllStatus();
		Type listType = new TypeToken<List<AdvertisementStatusBean>>() {}.getType();
		redata=gson.toJson(list,listType);
		return "AD";
	}
	
	public String changeStatus(){
		String adID=request.getParameter("adID");
		String adStatusID=request.getParameter("atatusID");
		if(adID!=null&&adID.trim().length()!=0
			&&adStatusID!=null&&adStatusID.trim().length()!=0){
			advertisementService.changeAdStatus(Integer.parseInt(adID), Integer.parseInt(adStatusID));
		}
		return "AD";
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	public byte[] getCustomImageInBytes() {
		byte[] imageInByte = null;
		if(imageId!=null){
			AdvertisementBean mbean=advertisementService.selectAd(Integer.parseInt(imageId));
			if(mbean.getImage()!=null){
				imageInByte=mbean.getImage();
			}else{
				mbean=advertisementService.selectAd(1);
				imageInByte=mbean.getImage();
			}
		}else{
			AdvertisementBean mbean=advertisementService.selectAd(1);
			imageInByte=mbean.getImage();
		}
		imageId=null;
		return imageInByte;
	}
	public String getCustomContentType() {
		return "image/*";
	}
	public String adimage(){
		return "image";
	}
}
