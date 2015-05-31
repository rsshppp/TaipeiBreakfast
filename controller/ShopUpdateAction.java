package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import form.ShopFormByWeng;
import misc.FileToByte;
import model.bean.ShopBean;
import model.service.ShopService;

public class ShopUpdateAction extends ActionSupport{
	private ShopService service;
	private ShopFormByWeng bean;
	private List<ShopBean> list;
	
	public ShopFormByWeng getBean() {
		return bean;
	}
	public void setBean(ShopFormByWeng bean) {
		this.bean = bean;
	}
	public void setService(ShopService service) {
		this.service = service;
	}
	
	public List<ShopBean> getList() {
		return list;
	}
	public void setList(List<ShopBean> list) {
		this.list = list;
	}
	
	public String execute(){
		try {
			list=new LinkedList();
			System.out.println(bean);
//			System.out.println(bean.getLogoImage());
			ShopBean bean2=new ShopBean();
			bean2.setShopName(bean.getShopName());
			bean2.setShopPhone(bean.getShopPhone());
			bean2.setShopCity(bean.getShopCity());
			bean2.setShopArea(bean.getShopArea());
			bean2.setShopAddr(bean.getShopAddr());
			bean2.setShopID(bean.getShopID());
			
			byte[] image;
			if(bean.getLogoImage()!=null){
				File file=bean.getLogoImage();
				FileToByte ftb=new FileToByte();
				image=ftb.loadFile(file);
				bean2.setLogoImage(image);
			}
			System.out.println(bean2);
			if(service.update(bean2)!=null){
				list.add(bean2);
				System.out.println(list);
				return "success";
			}
			
			return "fail";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
	}
	
}
