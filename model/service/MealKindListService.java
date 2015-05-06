package model.service;

import java.util.List;

import model.bean.MealKindListBean;
import model.dao.MealKindListDAO;
//Gary
public class MealKindListService {
	private MealKindListDAO dao;

	public void setDao(MealKindListDAO dao) {
		this.dao = dao;
	}
	//查詢所有種類
	public List<MealKindListBean> selectAllKind(){
		return dao.selectAll();
	}
}
