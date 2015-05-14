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

}
