package model.bean.deliverValuesOnly;

import java.io.Serializable;
                                       
                                       //交易歷史專用之訂單明細(值 從 其他Bean拿)
public class HistoryOrderDetailBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mealName;           //餐點名稱 from MealBean <=
	private Double price;              //餐點價格 from MealBean(隨物價變動而變) <=
	private Integer count;             //數量 from OrderdetailBean
	
	
	public HistoryOrderDetailBean(){
		
	}

	@Override
	public String toString() {
		return "{ mealName:"+mealName+", price:"+price+", count:"+count+" }";
	}
	
	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	

	
	
	public static void main(String[] args) {
		HistoryOrderDetailBean bean=new HistoryOrderDetailBean();
		System.out.println(bean);

	}

}
