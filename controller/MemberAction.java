package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.bean.MealBean;
import model.bean.MemberBean;
import model.bean.OrderDetailBean;
import model.bean.OrderSumBean;
import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.misc.convert;
import model.service.OwnerService;
import model.service.TBService;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import form.MemberForm;
import form.OderSumForm;
import form.OrderDetailForm;
import form.OwnerForm;
import form.ShopForm;

public class MemberAction extends ActionSupport implements ServletRequestAware{
	HttpSession session=ServletActionContext.getRequest().getSession();
	private HttpServletRequest request;
	private TBService service=new TBService();
	private OwnerService ownser=new OwnerService();
	private MemberForm mf=new MemberForm();
	private ShopForm sf;
	private OderSumForm osf;
	private OwnerForm owf;
	private Map<String, String> errors=new HashMap<String, String>();
	private Gson gson=new Gson();
	private String redata;
	private String keyword;
	private Integer page;
	private byte[] image;
	private byte[] imageInByte;
	private Integer shopID;
	
	public MemberForm getMf() {
		return mf;
	}
	public void setMf(MemberForm mf) {
		this.mf = mf;
	}
	public ShopForm getSf() {
		return sf;
	}
	public void setSf(ShopForm sf) {
		this.sf = sf;
	}
	public void setService(TBService service) {
		this.service = service;
	}
	public void setOwnser(OwnerService ownser) {
		this.ownser = ownser;
	}
	public String getRedata() {
		return redata;
	}
	public void setRedata(String redata) {
		this.redata = redata;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setShopID(Integer shopID) {
		this.shopID = shopID;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public byte[] getImageInByte() {
		return imageInByte;
	}
	public void setImageInByte(byte[] imageInByte) {
		this.imageInByte = imageInByte;
	}
	public void setOsf(OderSumForm osf) {
		this.osf = osf;
	}
	public OwnerForm getOwf() {
		return owf;
	}
	public void setOwf(OwnerForm owf) {
		this.owf = owf;
	}
	
	public String getImgType() {
		return "image/*";
	}
	public String shopImage(){
		if (shopID != null) {
			ShopBean bean=service.selectSByID(shopID);
			image = bean.getLogoImage();
		}
		return "imageee";
	}
	
	public String memberInsert() {
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
	        //mail 型態驗證
	        String[] mailStyle = mf.getMemberEmail().split("@");
	        if(!service.CheackAcc(mailStyle[0])){
				errors.put("acc", "This email had been used");
	        }
		}else{
			errors.put("acc", "Please enter email");
		}
//		byte[] temp = mf.getMemberPwd().getBytes();
//		byte[] pass = mf.getMemberCwd().getBytes();
//		if(!Arrays.equals(temp, pass)) {
//			errors.put("pwd", "password error");
//		}
		int tel = 0;
		if (mf.getMemberTel() != null && mf.getMemberTel().length() != 0) {
			tel = convert.convertInt(mf.getMemberTel());
			if (tel == -1000) {
				errors.put("tel", "Tel must be integer");
			}
		}
		int pho = 0;
		if (mf.getMemberPhone() != null && mf.getMemberPhone().length() != 0) {
			pho = convert.convertInt(mf.getMemberPhone());
			if (pho == -1000) {
				errors.put("pho", "Phone must be integer");
			}
		}
        if (errors!=null && !errors.isEmpty()) {
        	// errors != null &&
			request.setAttribute("errors", errors);
			return "memberinsert";
        }
		
		try{
		MemberBean bean=new MemberBean();
		if(mf!=null){	
//			System.out.println("in:"+mf);

			bean.setMemberAcc(mf.getMemberEmail());
			bean.setMemberEmail(mf.getMemberEmail());
			bean.setMemberPwd(mf.getMemberPwd());
			bean.setMemberLastName(mf.getMemberLastName());
			bean.setMemberFirstName(mf.getMemberFirstName());
			bean.setMemberPhone(mf.getMemberPhone());
//			bean.setMemberTel(mf.getMemberTel());
			bean.setMemberAddr(mf.getMemberAddr());
			if(mf.getMemberImage()!=null){
				File file=mf.getMemberImage();
				convert ftb=new convert();
				image=ftb.loadFile(file);
				bean.setMemberImage(image);
//				System.out.println(image);
				errors.put("img", gson.toJson(image));
			}
			bean.setMemberStatus(true);
			bean.setMemberSuspend(false);

			MemberBean result = service.insertMember(bean);
			if(result != null){
				errors.put("action", "新增成功");
				//回首頁, 自動登入
				return "index";
			}else{
				errors.put("action", "Insert fail");
			}
		}
		redata=gson.toJson(errors);
//		System.out.println(redata);
			request.setAttribute("errors", errors);
			return "memberinsert";
		}catch(IOException e){
			return "error";
		}
	}
	
	public String memberUpdate() {
		MemberBean be=(MemberBean)session.getAttribute("user");
//		System.out.println(be.getMemberEmail());
//    	System.out.println(mf.getMemberEmail());
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
//			if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
		int tel = 0;
//		if (mf.getMemberTel() != null && mf.getMemberTel().length() != 0) {
//			tel = convert.convertInt(mf.getMemberTel());
//			if (tel == -1000) {
//				errors.put("tel", "Tel must be integer");
//			}
//		}
//		int pho = 0;
//		if (mf.getMemberPhone() != null && mf.getMemberPhone().length() != 0) {
//			pho = convert.convertInt(mf.getMemberPhone());
//			if (pho == -1000) {
//				errors.put("pho", "Phone must be integer");
//			}
//		}
        if (errors!=null && !errors.isEmpty()) {
        	// errors != null &&
			request.setAttribute("errors", errors);
			return "memberupdate";
        }
        
		try{
			MemberBean bean = new MemberBean();
			if (mf != null) {
//				System.out.println("in:" + mf);
				String[] mailStyle = be.getMemberEmail().split("@");
//				String[] mailStyle = mf.getMemberEmail().split("@");
				MemberBean t = service.selectMemberE(mailStyle[0]);
				if (t != null) {

					bean.setMemberID(t.getMemberID());
					bean.setMemberAcc(t.getMemberAcc());
					bean.setMemberEmail(mf.getMemberEmail());
					bean.setMemberLastName(mf.getMemberLastName());
					bean.setMemberFirstName(mf.getMemberFirstName());
					bean.setMemberPhone(mf.getMemberPhone());
					bean.setMemberTel(mf.getMemberTel());
					bean.setMemberAddr(mf.getMemberAddr());
					if (mf.getMemberImage() != null) {
						File file = mf.getMemberImage();
						convert ftb = new convert();
						image = ftb.loadFile(file);
						bean.setMemberImage(image);
						errors.put("img", gson.toJson(image));
					}

					if (service.updateMember(bean) != null) {
						errors.put("action", "Update成功");
						session.setAttribute("user",bean);
					} else {
						errors.put("action", "Update fail");
					}
					redata = gson.toJson(errors);
//					System.out.println(redata);
				}else{
					errors.put("action", "無此mail");
				}
			}
			request.setAttribute("errors", errors);
			return "memberupdate";
		}catch(IOException e){
			return "error";
		}
	}

	public String changePassword() {
		MemberBean be=(MemberBean)session.getAttribute("user");
		if (be.getMemberEmail() != null && be.getMemberEmail().length() != 0) {
//			if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
				System.out.println("Action mail pass");
//			}
		}else{
			errors.put("acc", "Please enter email");
		}
		if(mf.getMemberPwd()!=null && mf.getMemberCwd()!=null){
			byte[] temp = mf.getMemberPwd().getBytes();
			byte[] pass = mf.getMemberCwd().getBytes();
			if (!Arrays.equals(temp, pass)) {
				errors.put("pwd", "password error");
			}
		}
        if (errors!=null && !errors.isEmpty()) {
        	// errors != null &&
			request.setAttribute("errors", errors);
			return "memberchangepass";
        }
        
		try{
			if (mf != null) {
//				System.out.println("change:" + mf);
				MemberBean t = service.selectMemberE(be.getMemberEmail().split("@")[0]);
//				System.out.println("t= "+t);
				if (t != null) {
					if (service.changePassword(t.getMemberID(),t.getMemberPwd(),mf.getMemberPwd()) != false) {
						errors.put("action", "修改成功");
					} else {
						errors.put("action", "Change fail");
					}
				}else{
					errors.put("action", "無此mail");
				}
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			request.setAttribute("errors", errors);
			return "memberchangepass";
		}catch(Exception e){
			return "error";
		}
	}

	public String deleteMember() {
		MemberBean be=(MemberBean)session.getAttribute("user");
		if (be.getMemberEmail() != null && be.getMemberEmail().length() != 0) {
//		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "memberdelete";
		}
		try{
			if (mf != null) {
//				System.out.println("delete :" + mf);
				MemberBean t = service.selectMemberE(be.getMemberEmail().split("@")[0]);
				if (t != null) {
					byte[] temp = mf.getMemberPwd().getBytes();
					byte[] pass = t.getMemberPwd().getBytes();
					if (!Arrays.equals(temp, pass)) {
						errors.put("pwd", "password error");
					}
					if (errors != null && !errors.isEmpty()) {
						// errors != null &&
						return "memberdelete";
					} else {
						if (service.deleteMember(t.getMemberID()) != false) {
							errors.put("action", "刪除成功");
							session.removeAttribute("user");
							session.removeAttribute("type");
							return "index";
						} else {
							errors.put("action", "Delete fail");
						}
					}
				}else{
					errors.put("action", "無此mail");
				}
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			return "memberdelete";
		}catch(Exception e){
			return "error";
		}
	}

	public String losePassword() {
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "losepass";
		}
		try{
			if (mf != null) {
//				System.out.println("lose :" + mf);
				MemberBean t = service.selectMemberE(mf.getMemberEmail().split("@")[0]);
				if (t != null) {
//					if(mf.getMemberFirstName().equals(t.getMemberFirstName())){
						service.losepassword(t.getMemberID());
						errors.put("action", "已幫您變更密碼,請前往電子郵件信箱收信");
						return "losepass";
					}else{
						errors.put("action", "無此mail");
					}
//				}
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			request.setAttribute("errors", errors);
			return "losepass";
		}catch(Exception e){
			return "error";
		}
	}

	public String losePassOwner() {
//		System.out.println(owf.getOwnAcc());
		if (owf.getOwnAcc() != null && owf.getOwnAcc().length() != 0) {
			if (owf.getOwnEmail() != null && owf.getOwnEmail().length() != 0) {
			System.out.println("Action mail pass");
			}else{
				errors.put("mail", "Please enter email");
			}
		}else{
			errors.put("acc", "Please enter Acc");
		}
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "losepassown";
		}
		try{
			if (owf != null) {
//				System.out.println("lose :" + owf);
				OwnerBean t = ownser.select(owf.getOwnAcc());
				if (t != null) {
//					System.out.println("t="+t.getOwnEmail());
					//if(t.getOwnEmail.equals(owf.getOwnEmail())){
					service.losepassown(t.getOwnID());
					errors.put("action", "已幫您變更密碼,請前往電子郵件信箱收信");
					return "losepassown";
				}else{
					errors.put("action", "無此mail");
				}
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			request.setAttribute("errors", errors);
			return "losepassown";
		}catch(Exception e){
			return "error";
		}
	}

	public String shoplist() {
//		System.out.println("slist");
		try{
			List<ShopBean> list=service.selectAllowShop();
			Iterator<ShopBean> shopBlist=list.iterator();
			List<ShopForm> listform=new ArrayList<ShopForm>();
			while(shopBlist.hasNext()){
				ShopBean bean=shopBlist.next();
				ShopForm form=new ShopForm();
				form.setShopID(bean.getShopID());
				form.setShopName(bean.getShopName());
				form.setOwnID(bean.getOwnID());
				form.setLogoImage(bean.getLogoImage());
				form.setShopPhone(bean.getShopPhone());
				form.setShopCity(bean.getShopCity());
				form.setShopArea(bean.getShopArea());
				form.setBeginBusinessTime(bean.getBeginBusinessTime());
				form.setBusinessTimeNote(bean.getBusinessTimeNote());
				listform.add(form);
			}
			Type listType = new TypeToken<List<ShopForm>>(){}.getType();
//			redata = gson.toJson(errors);
			redata=gson.toJson(listform,listType);
//			System.out.println("re="+redata);
//			errors.put("action", redata);
//			request.setAttribute("errors", errors);
			return "allowshop";
		}catch(Exception e){
			return "error";
		}
	}

	public String allowShop() {
		if(sf.getShopID()!=null && sf.getShopID()!=0){
			System.out.println("Action id pass");
		}else{
			errors.put("action", "no ID");
			return "allowshop";
		}
		try{
			if (service.allowShop(sf.getShopID()) != false) {
				errors.put("action", "操作成功");
			} else {
				errors.put("action", "Ooooooooooooops");
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			return "allowshop";
		}catch(Exception e){
			return "error";
		}
	}

	public String notallowShop() {
		if(sf.getShopID()!=null && sf.getShopID()!=0){
			System.out.println("Action mail pass");
		}else{
			errors.put("action", "no ID");
			return "allowshop";
		}
		try{
			if (service.notallowShop(sf.getShopID()) != false) {
				errors.put("action", "操作成功");
			} else {
				errors.put("action", "Ooooooooooooops");
			}
			redata = gson.toJson(errors);
//			System.out.println(redata);
			return "allowshop";
		}catch(Exception e){
			return "error";
		}
	}

	public String selectShopArea() {
		try{
//			System.out.println(1+":"+sf.getShopArea());
			if (sf.getShopArea()!= null && sf.getShopArea().length()!= 0) {
				List<ShopBean> list=service.selectSByArea(sf.getShopArea());
				Iterator<ShopBean> shopBlist=list.iterator();
				List<ShopForm> listform=new ArrayList<ShopForm>();
				while (shopBlist.hasNext()) {
					ShopBean bean = shopBlist.next();
					ShopForm form = new ShopForm();
					form.setShopID(bean.getShopID());
					form.setShopName(bean.getShopName());
					listform.add(form);
				}
				Type listType = new TypeToken<List<ShopForm>>(){}.getType();
				redata=gson.toJson(listform,listType);
			} else {
				errors.put("action", "area=null");
				redata = gson.toJson(errors);
			}
//			System.out.println(redata+","+errors);
			return "searea";
		}catch(Exception e){
			return "error";
		}
	}

	public String selectShop() {
		try{
//			System.out.println(11+":"+sf.getShopID()+":"+keyword+":"+sf.getShopArea());
			if (sf.getShopID() != null && sf.getShopID() != 0) {
				ShopBean bean=service.selectSByID(sf.getShopID());
				errors.put("action", "s by id");
				//跳到 bean.getShopID 所指向的shop進入頁(施工中,dead end)
				return "searea";
			} else {
				if(keyword!=null && keyword.length()!=0){
					List<ShopBean> list=service.selectSByKeyword(keyword, sf.getShopArea());
//					System.out.println(13+":"+list);
					Iterator<ShopBean> shopBlist=list.iterator();
					List<ShopForm> listform=new ArrayList<ShopForm>();
					while (shopBlist.hasNext()) {
						ShopBean bean = shopBlist.next();
						ShopForm form = new ShopForm();
						form.setShopID(bean.getShopID());
						form.setLogoImage(bean.getLogoImage());
						form.setShopName(bean.getShopName());
						form.setShopCity(bean.getShopCity());
						form.setShopArea(bean.getShopArea());
						form.setBeginBusinessTime(bean.getBeginBusinessTime());
						form.setBusinessTimeNote(bean.getBusinessTimeNote());
						listform.add(form);
					}
					Type listType = new TypeToken<List<ShopForm>>(){}.getType();
					redata=gson.toJson(listform,listType);
					errors.put("action", "s by keyword");
				}else{
					List<ShopBean> list=service.selectSByArea(sf.getShopArea());
//					System.out.println(14+":"+list);
					Iterator<ShopBean> shopBlist=list.iterator();
					List<ShopForm> listform=new ArrayList<ShopForm>();
					while (shopBlist.hasNext()) {
						ShopBean bean = shopBlist.next();
						ShopForm form = new ShopForm();
						form.setShopID(bean.getShopID());
						form.setLogoImage(bean.getLogoImage());
						form.setShopName(bean.getShopName());
						form.setShopCity(bean.getShopCity());
						form.setShopArea(bean.getShopArea());
						form.setBeginBusinessTime(bean.getBeginBusinessTime());
						form.setBusinessTimeNote(bean.getBusinessTimeNote());
						listform.add(form);
					}
					Type listType = new TypeToken<List<ShopForm>>(){}.getType();
					redata=gson.toJson(listform,listType);
					errors.put("action", "s by area");
				}
			}
			errors.put("re", redata);
//			System.out.println(redata+":"+errors);
			request.setAttribute("re", redata);
			return "selects";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String newoders0(){
//		System.out.println("n01:"+page);
		try{
			List<OrderSumBean> list=service.selectOrdersByTime(page);
//			System.out.println("n02:"+list);
			Iterator<OrderSumBean> oblist=list.iterator();
			List<OderSumForm> listform=new ArrayList<OderSumForm>();
			while (oblist.hasNext()) {
				OrderSumBean bean = oblist.next();
				OderSumForm form = new OderSumForm();
				form.setOrderSumID(bean.getOrderSumID());
				form.setOrderTime(bean.getOrderTime());
				form.setMemo(bean.getMemo());
				listform.add(form);
			}
			Type listType = new TypeToken<List<ShopForm>>(){}.getType();
			redata=gson.toJson(listform,listType);
//			System.out.println(redata);
			return "newods";
		}catch(Exception e){
			return "error";
		}
	}
	
	public String newoderDetail(){
//		System.out.println("d1:"+osf.getOrderSumID());
		try{
			if(osf.getOrderSumID()!=0 && osf.getOrderSumID()!=null){
				List<OrderDetailBean> list = service.selectOrderDetail(osf.getOrderSumID());
				Iterator<OrderDetailBean> odlist = list.iterator();
				List<OrderDetailForm> listform = new ArrayList<OrderDetailForm>();
				while (odlist.hasNext()) {
					OrderDetailBean bean = odlist.next();
					OrderDetailForm form = new OrderDetailForm();
					form.setMealName(bean.getMealBean().getMealName());
					form.setCount(bean.getCount());
					form.setPrice(bean.getPrice());
					listform.add(form);
				}
				Type listType = new TypeToken<List<ShopForm>>() {}.getType();
				redata = gson.toJson(listform, listType);
//				System.out.println(redata);
			}
			return "newods";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
