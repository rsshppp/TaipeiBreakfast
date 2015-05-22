package controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.bean.MemberBean;
import model.misc.convert;
import model.service.TBService;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

import form.MemberForm;
import form.ShopForm;

public class MemberAction extends ActionSupport implements ServletRequestAware{
	HttpSession session=ServletActionContext.getRequest().getSession();
	private HttpServletRequest request;
	private TBService service=new TBService();
	private MemberForm mf=new MemberForm();
//	private ShopForm sf;
	private Map<String, String> errors=new HashMap<String, String>();
	private Gson gson=new Gson();
	private String redata;
//	private String jsondata;
//	private String mealName;

	public MemberForm getMf() {
		return mf;
	}
	public void setMb(MemberForm mf) {
		this.mf = mf;
	}
	public void setService(TBService service) {
		this.service = service;
	}
	public String getRedata() {
		return redata;
	}
//	public void setMealName(String mealName) {
//		this.mealName = mealName;
//	}
//	public void setJsondata(String jsondata) {
//		this.jsondata = jsondata;
//	}
	
	
	public String memberinsert() {

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
			return "memberinsert";
        }
		
		try{
		MemberBean bean=new MemberBean();
		byte[] image;
		if(mf!=null){	
			System.out.println("in:"+mf);

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
				System.out.println(image);
				errors.put("img", gson.toJson(image));
			}
			bean.setMemberStatus(true);
			bean.setMemberSuspend(false);

			MemberBean result = service.insertMember(bean);
			if(result != null){
				errors.put("action", "新增成功");
				//回首頁, 自動登入
				request.setAttribute("insert", result);
				return "index";
			}else{
				errors.put("action", "Insert fail");
			}
			redata=gson.toJson(errors);
			System.out.println(redata);
		}
			return "memberinsert";
		}catch(IOException e){
			errors.put("action", "新增失敗");
			return "error";
		}
	}

	public String memberUpdate() {
		
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
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
        	System.out.println(mf.getMemberEmail());
			return "memberupdate";
        }
        
		try{
			MemberBean bean = new MemberBean();
			byte[] image;
			if (mf != null) {
				System.out.println("in:" + mf);
				String[] mailStyle = mf.getMemberEmail().split("@");
				MemberBean t = service.selectMemberE(mailStyle[0]);
				if (t != null) {

					bean.setMemberID(t.getMemberID());
					bean.setMemberAcc(mf.getMemberEmail());
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
					} else {
						errors.put("action", "Update fail");
					}
					redata = gson.toJson(errors);
					System.out.println(redata);
				}
			}
			return "memberupdate";
		}catch(IOException e){
			errors.put("action", "Update失敗");
			return "error";
		}
	}

	public String changePassword() {
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
//		if(mf.getMemberPwd()!=null && mf.getMemberCwd()!=null){
//			byte[] temp = mf.getMemberPwd().getBytes();
//			byte[] pass = mf.getMemberCwd().getBytes();
//			if (!Arrays.equals(temp, pass)) {
//				errors.put("pwd", "password error");
//			}
//		}
        if (errors!=null && !errors.isEmpty()) {
        	// errors != null &&
			return "memberchangepass";
        }
        
		try{
			if (mf != null) {
				System.out.println("change:" + mf);
				MemberBean t = service.selectMemberE(mf.getMemberEmail().split("@")[0]);
				System.out.println("t= "+t);
				if (t != null) {
					if (service.changePassword(t.getMemberID(),t.getMemberPwd(),mf.getMemberPwd()) != false) {
						errors.put("action", "修改成功");
					} else {
						errors.put("action", "Change fail");
					}
					redata = gson.toJson(errors);
					System.out.println(redata);
				}
			}
			return "memberchangepass";
		}catch(Exception e){
			errors.put("action", "Change失敗");
			return "error";
		}
	}

	public String deleteMember() {
		if (mf.getMemberEmail() != null && mf.getMemberEmail().length() != 0) {
			System.out.println("Action mail pass");
		}else{
			errors.put("acc", "Please enter email");
		}
		if (errors != null && !errors.isEmpty()) {
			return "memberdelete";
		}
		try{
			if (mf != null) {
				System.out.println("delete :" + mf);
				MemberBean t = service.selectMemberE(mf.getMemberEmail().split("@")[0]);
				if (t != null) {
//					byte[] temp = mf.getMemberPwd().getBytes();
//					byte[] pass = t.getMemberPwd().getBytes();
//					if (!Arrays.equals(temp, pass)) {
//						errors.put("pwd", "password error");
//					}
//					if (errors != null && !errors.isEmpty()) {
//						// errors != null &&
//						return "memberdelete";
//					} else {
						if (service.deleteMember(t.getMemberID()) != false) {
							errors.put("action", "刪除成功");
							//若成功則返回首頁
							return "index";
						} else {
							errors.put("action", "Delete fail");
						}
//					}
					redata = gson.toJson(errors);
					System.out.println(redata);
				}
			}
			return "memberdelete";
		}catch(Exception e){
			errors.put("action", "刪除失敗");
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
			System.out.println("err");
			return "losepass";
		}
		try{
			if (mf != null) {
				System.out.println("lose :" + mf);
				MemberBean t = service.selectMemberE(mf.getMemberEmail().split("@")[0]);
				if (t != null) {
//					if(mf.getMemberFirstName().equals(t.getMemberFirstName())){
						service.losepassword(t.getMemberID());
						errors.put("action", "已幫您變更密碼,請前往電子郵件信箱收信");
						//若成功則彈出顯示action並返回首頁

						return "index";
//					} else {
//						errors.put("action", "Ooooooooooooops");
					}
					redata = gson.toJson(errors);
					System.out.println(redata);
//				}
			}
			return "losepass";
		}catch(Exception e){
			errors.put("action", "驗證失敗");
			return "error";
		}
	}

	public String allowShop() {
		
		try{
			errors.put("action", "Ooooooooooooops");
			redata = gson.toJson(errors);
			System.out.println(redata);
			return "allowshop";
		}catch(Exception e){
			errors.put("action", "驗證失敗");
			return "error";
		}
	}
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
