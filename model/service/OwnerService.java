package model.service;


import java.util.ArrayList;
import java.util.List;

import model.bean.OwnerBean;
import model.bean.ShopBean;
import model.dao.OwnerDAO;

public class OwnerService {        //by宗鈺
    private OwnerDAO ownerDAO;
    
    
	public void setOwnerDAO(OwnerDAO ownerDAO) {
		this.ownerDAO = ownerDAO;
	}

	//利用賣家帳號查詢賣家資料-先寫上,專題前沒用到再刪
	public List<OwnerBean> select(OwnerBean ownerBean){ 
		List<OwnerBean> list=null;
		if(ownerBean!=null && ownerBean.getOwnAcc().trim().length()!=0){
			OwnerBean bean=ownerDAO.select(ownerBean.getOwnAcc());
			if(bean!=null){
				list=new ArrayList();
				list.add(bean);
			}
		}else{
			list=ownerDAO.selectAll();
		}
		return list;
	}
	
	
	//利用賣家ID 查詢 其所有店鋪資料
	public List<ShopBean> getShops(OwnerBean ownerBean){
		if(ownerBean!=null && ownerBean.getOwnID()!=0){
			return ownerDAO.getShops(ownerBean.getOwnID());
		}
		return null;
	}
	
	//新增賣家資料
	public boolean insert(OwnerBean ownerBean){
		if(ownerBean!=null){
			if(ownerDAO.insert(ownerBean)){
				return true;
			}		
		}
		return false;
	}
	
	//修改賣家資料
	public OwnerBean update(OwnerBean ownerBean){
		if(ownerBean!=null){
			return ownerDAO.update(ownerBean);
		}
		return null;
	}
	
	//刪除賣方帳號(設賣方狀態為false)
	public boolean delete(OwnerBean ownerBean){
		if(ownerBean!=null && ownerBean.getOwnAcc().trim().length()!=0){
			if(ownerDAO.delete(ownerBean.getOwnAcc())){
				return true;
			}
		}
		return false;
	}
	
	//為後台管理停權所使用,可以停權,也可以取消停權
	public boolean suspendOrCancel(OwnerBean ownerBean){
		if(ownerBean!=null && ownerBean.getOwnAcc().trim().length()!=0){
			if(ownerDAO.suspendOrCancel(ownerBean.getOwnAcc())){
				return true;
			}
		}
		return false;
	}

}
