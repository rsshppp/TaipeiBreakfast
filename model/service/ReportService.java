package model.service;

import java.text.ParseException;
import java.util.List;

import model.dao.OrderSumDAO;

public class ReportService {                        //宗鈺
        private OrderSumDAO orderSumDAO;
        
        
	public void setOrderSumDAO(OrderSumDAO orderSumDAO) {
			this.orderSumDAO = orderSumDAO;
		}

	//特定店鋪日報表
	public List<Object[]> getDailyReport(Integer shopID, Integer year,  
			Integer month, Integer day) throws ParseException{
		if(shopID!=0 && year!=0 && month!=0 && day!=0){
			return orderSumDAO.getDailyReport(shopID, year, month, day);
		}else{
			return null;
		}		
	}
	
	//特定店鋪月報表
	public List<Object[]> getMonthlyReport(Integer shopID, Integer year,
			Integer month) throws ParseException {
		if(shopID!=0 && year!=0 && month!=0){
			return orderSumDAO.getMonthlyReport(shopID, year, month);
		}else{
			return null;
		}
	}

	//特定店鋪當日不同時段報表--宗鈺
	public List<Object> getTimeReport(Integer shopID, Integer year,
			Integer month, Integer day) throws ParseException {
		if(shopID!=0 && year!=0 && month!=0 && day!=0){
			return orderSumDAO.getTimeReport(shopID, year, month, day);
		}else{
			return null;
		}	
	}

	//特定店鋪特定時段報表--宗鈺
	public List<Object[]> getDetailTimeReport(Integer shopID, Integer year,  
			Integer month, Integer day, Integer hour) throws ParseException{
		if(shopID!=0 && year!=0 && month!=0 && day!=0 && hour!=0){
			return orderSumDAO.getDetailTimeReport(shopID, year, month, day, hour);
		}else{
			return null;
		}		
	}
}
