package model.dao;

public interface EmailDAO {
	public boolean send(String MemberMail,String Subject,String Text);
}
