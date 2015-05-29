package model.bean;

import java.io.Serializable;

//會員最愛表
public class FavoriteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer favoriteID;
	private Integer memberID;
	private Integer mealID;

	private MealBean mealBean;
	private MemberBean memberBean;

	public FavoriteBean() {

	}

	@Override
	public String toString() {
		return "{\"favoriteID\":" + favoriteID + ", \"memberID\":" + memberID
				+ ", \"mealID\":" + mealID + ", \"MealBean\":"
				+ ", \"MemberBean\":" + memberBean + "}";
	}

	public Integer getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(Integer favoriteID) {
		this.favoriteID = favoriteID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getMealID() {
		return mealID;
	}

	public void setMealID(Integer mealID) {
		this.mealID = mealID;
	}

	public MealBean getMealBean() {
		return mealBean;
	}

	public void setMealBean(MealBean mealBean) {
		this.mealBean = mealBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
}
