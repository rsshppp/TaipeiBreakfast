package model.dao;

import java.util.List;

import model.bean.MessageBoardBean;

//Gary
public interface MessageBoardDAO {
	//查詢全部留言
	public List<MessageBoardBean> select();
	//查詢特定會員ID留言
	public List<MessageBoardBean> select(int memberID);
	//查詢特定留言
	public MessageBoardBean selectOne(int messageID);
	//新增留言
	public boolean insert(MessageBoardBean bean);
	//刪除特定留言
	public boolean delete(int messageID);
}
