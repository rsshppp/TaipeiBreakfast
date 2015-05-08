package model.dao;

import java.util.List;

import model.bean.MessageBoardBean;

//Gary
public interface MessageBoardDAO {
	public List<MessageBoardBean> select();
	public List<MessageBoardBean> select(int memberID);
	public MessageBoardBean selectOne(int messageID);
	public boolean insert(MessageBoardBean bean);
	public boolean delete(int messageID);
}
