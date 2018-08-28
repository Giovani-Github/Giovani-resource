package com.example.news.domain;

public class News {
	
	private String title; // 标题
	private String detail;  // 正文
	private String comment; // 评论数
	private String image; // 新闻配图的地址
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "News [title=" + title + ", detail=" + detail + ", comment="
				+ comment + ", image=" + image + "]";
	}
	public News(String title, String detail, String comment, String image) {
		super();
		this.title = title;
		this.detail = detail;
		this.comment = comment;
		this.image = image;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}


}
