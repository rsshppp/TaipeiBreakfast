package model.bean;

import java.io.Serializable;
import java.sql.Date;

//最新消息表
public class NewsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer newsID;
	private String title;
	private String content;
	private Date date;
	private Byte[] image;
	
	public NewsBean() {
		
	}

	public Integer getNewsID() {
		return newsID;
	}

	public void setNewsID(Integer newsID) {
		this.newsID = newsID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}
}
